grammar IsiLang;

@header{
	import br.com.professorisidro.isilanguage.datastructures.IsiSymbol;
	import br.com.professorisidro.isilanguage.datastructures.IsiVariable;
	import br.com.professorisidro.isilanguage.datastructures.IsiSymbolTable;
	import br.com.professorisidro.isilanguage.exceptions.IsiSemanticException;
	import br.com.professorisidro.isilanguage.ast.IsiProgram;
	import br.com.professorisidro.isilanguage.ast.AbstractCommand;
	import br.com.professorisidro.isilanguage.ast.CommandLeitura;
	import br.com.professorisidro.isilanguage.ast.CommandEscrita;
	import br.com.professorisidro.isilanguage.ast.CommandEnquanto;
	import br.com.professorisidro.isilanguage.ast.CommandAtribuicao;
	import br.com.professorisidro.isilanguage.ast.CommandDecisao;
	import br.com.professorisidro.isilanguage.ast.CommandFacaEnquanto;
	import java.util.ArrayList;
	import java.util.Stack;
}

@members{
	private int _tipo;
	private String _varName;
	private String _varValue;
	private IsiSymbolTable symbolTable = new IsiSymbolTable();
	private IsiSymbol symbol;
	private IsiProgram program = new IsiProgram();
	private ArrayList<AbstractCommand> curThread;
	private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
	private String _readID;
	private String _writeID;
	private String _exprID;
	private String _exprContent;
	private String _exprDecision;
	private String _exprWhile;
	private int _exprType;
	private ArrayList<AbstractCommand> listaTrue;
	private ArrayList<AbstractCommand> listaFalse;
	private ArrayList<AbstractCommand> commandEnq;
	private ArrayList<AbstractCommand> listaLoop;

	private String[] typeDict = new String[] {"numero", "texto", "booleano"};
	
	public void verificaID(String id){
		if (!symbolTable.exists(id)){
			throw new IsiSemanticException("Symbol "+id+" not declared");
		}
	}
	
	public void exibeComandos(){
		for (AbstractCommand c: program.getComandos()){
			System.out.println(c);
		}
	}

	public void exibeWarnings(){ 
		for (IsiSymbol symbol: symbolTable.getAll()) {
			if(symbol instanceof IsiVariable && ((IsiVariable)symbol).getAttributed()==false) {
				System.out.println(String.format("Variable %s declared but never used", symbol.getName()));
			}
		}
	}
	
	public void generateCode(){
		program.generateJavaCode();
		program.generatePythonCode();
	}
}

prog	: 'programa' decl bloco  'fimprog;'
			{  program.setVarTable(symbolTable);
				program.setComandos(stack.pop());
			}
		;
		
decl    :  (declaravar)+
        ;
        
        
declaravar :  tipo ID  {
					_varName = _input.LT(-1).getText();
					_varValue = null;
					symbol = new IsiVariable(_varName, _tipo, _varValue);
					if (!symbolTable.exists(_varName)){
						symbolTable.add(symbol);	
					}
					else{
						throw new IsiSemanticException("Symbol "+_varName+" already declared");
					}
				} 
			(  VIR 
				ID {
					_varName = _input.LT(-1).getText();
					_varValue = null;
					symbol = new IsiVariable(_varName, _tipo, _varValue);
					if (!symbolTable.exists(_varName)){
						symbolTable.add(symbol);	
					}
					else{
						throw new IsiSemanticException("Symbol "+_varName+" already declared");
					}
				}
			)* 
			SC
		;
	
tipo       	: 'numero' { _tipo = IsiVariable.NUMBER;  }
			| 'texto'  { _tipo = IsiVariable.TEXT;  }
			| 'booleano'  { _tipo = IsiVariable.BOOLEAN;  }
			;
        
bloco	: 	{ 	curThread = new ArrayList<AbstractCommand>(); 
				stack.push(curThread);  
			}
			(cmd)+
		;
		

cmd		:  cmdleitura  
		|  cmdescrita 
		|  cmdattrib
		|  cmdselecao
		|  cmdenquanto
		|  cmdloop  
		;
		
cmdleitura	: 'leia' 	AP
						ID { verificaID(_input.LT(-1).getText());
							_readID = _input.LT(-1).getText();
                        } 
						FP 
						SC 
						
				{
					IsiVariable var = (IsiVariable)symbolTable.get(_readID);
					CommandLeitura cmd = new CommandLeitura(_readID, var);
					stack.peek().add(cmd);
					var.setAttributed(true);
				}   
				;
			
cmdescrita	: 'escreva' 
					AP 
					ID { verificaID(_input.LT(-1).getText());
						_writeID = _input.LT(-1).getText();
						} 
					FP 
					SC
				{
					CommandEscrita cmd = new CommandEscrita(_writeID);
					stack.peek().add(cmd);
					IsiVariable var = (IsiVariable)symbolTable.get(_writeID);
					var.setAttributed(true);
				}
				;
			
cmdattrib	:  ID 	{ verificaID(_input.LT(-1).getText());
						_exprID = _input.LT(-1).getText();
					} 
				ATTR { _exprContent = ""; } 
				expr 
				SC
				{	
					IsiVariable var = (IsiVariable)symbolTable.get(_exprID);
					
					if (var.getType() != _exprType) {
						throw new IsiSemanticException(String.format("Variable %s expects a %s but received a %s instead", var.getName(), typeDict[var.getType()], typeDict[_exprType]));
					}
					CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
					stack.peek().add(cmd);
					var.setAttributed(true);
				}
			;
			
			
cmdselecao  :  'se' AP
                    ID    { _exprDecision = _input.LT(-1).getText(); }
                    OPREL { _exprDecision += _input.LT(-1).getText(); }
                    (ID | NUMBER) {_exprDecision += _input.LT(-1).getText(); }
                    FP 
                    ACH 
                    { 	curThread = new ArrayList<AbstractCommand>(); 
						stack.push(curThread);
                    }
                    (cmd)+ 
                    
                    FCH 
                    {
						listaTrue = stack.pop();	
                    } 
					('senao' 
					ACH
					{
						curThread = new ArrayList<AbstractCommand>();
						stack.push(curThread);
					} 
					(cmd+) 
					FCH
					{
						listaFalse = stack.pop();
						CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
						stack.peek().add(cmd);
					}
				)?
            ;

cmdenquanto  : 'enquanto' AP
						ID { _exprDecision = _input.LT(-1).getText(); }
						OPREL { _exprDecision += _input.LT(-1).getText(); }
						(ID | NUMBER) {_exprDecision += _input.LT(-1).getText(); }
						FP 
						ACH 
						{ 
						curThread = new ArrayList<AbstractCommand>();
						stack.push(curThread);
						}
						(cmd)+ 
				
						FCH 
						{
						commandEnq = stack.pop();
						CommandEnquanto cmd = new CommandEnquanto(_exprDecision, commandEnq);
						stack.peek().add(cmd);	
						}
						; 

cmdloop     :     'enquanto' AP 
                expr {_exprWhile = _input.LT(-1).getText();} 
                OPREL {_exprWhile += _input.LT(-1).getText();}
                expr {_exprWhile += _input.LT(-1).getText();}
                FP 
                AP 
                {     curThread = new ArrayList<AbstractCommand>(); 
                    stack.push(curThread);
                }
                (cmd)+
				FP
				{
					listaLoop = stack.pop();
					CommandEnquanto cmd = new CommandEnquanto(_exprWhile, listaLoop);
					stack.peek().add(cmd);
                }

            | 'faca' 
                AP
                {     curThread = new ArrayList<AbstractCommand>(); 
                    stack.push(curThread);
				}
				(cmd)+ 
				FP 
				'enquanto' AP
				expr {_exprWhile = _input.LT(-1).getText();}
				OPREL{_exprWhile += _input.LT(-1).getText();}
				expr {_exprWhile += _input.LT(-1).getText();}
				FP
					{   listaLoop = stack.pop();
						CommandFacaEnquanto cmd = new CommandFacaEnquanto(_exprWhile, listaLoop);
						stack.peek().add(cmd);
					}
            ;

expr		:  termo ( 
				OP  { 	_exprContent += _input.LT(-1).getText();
						_exprType = 0;
					}
				termo
				)*
			|
				TEXT { _exprContent += _input.LT(-1).getText();
						_exprType = 1;
						}
			|
				BOOLEAN { _exprContent += _input.LT(-1).getText();
						_exprType = 2;
						}
			;
			
termo		: ID { verificaID(_input.LT(-1).getText());
					_exprContent += _input.LT(-1).getText();
					} 
			| 
			NUMBER
			{
				_exprContent += _input.LT(-1).getText();
			}
			;	


	
AP	: '('
	;
	
FP	: ')'
	;
	
// AC	: '{'
// 	;
	
// FC	: '}'
// 	;

SC	: ';'
	;
	
OP	: '+' | '-' | '*' | '/'
	;
	
ATTR: '='
	;
	
VIR : ','
	;
	
ACH : '{'
	;
	
FCH : '}'
    ;


OPREL 	: '>' | '<' | '>=' | '<=' | '==' | '!='
		;

ID		: [a-z] ([a-z] | [A-Z] | [0-9])*
		;
	
NUMBER	: [0-9]+ ('.' [0-9]+)?
		;
		
WS		: (' ' | '\t' | '\n' | '\r') -> skip;

QUOTES 	: ('"');

TEXT: ["]([a-z] | [A-Z] | [0-9] | ' ')*["];

BOOLEAN : 'Verdade' | 'Falso';