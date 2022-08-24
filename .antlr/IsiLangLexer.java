// Generated from c:\Users\barro\OneDrive\Documentos\UFABC\2022.2\compiladores\IsiLanguageProjetoFinal-1\IsiLang.g4 by ANTLR 4.9.2

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
	import java.util.ArrayList;
	import java.util.Stack;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		AP=10, FP=11, SC=12, OP=13, ATTR=14, VIR=15, ACH=16, FCH=17, OPREL=18, 
		ID=19, NUMBER=20, WS=21, QUOTES=22, TEXTO=23;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"AP", "FP", "SC", "OP", "ATTR", "VIR", "ACH", "FCH", "OPREL", "ID", "NUMBER", 
			"WS", "QUOTES", "TEXTO"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'fimprog;'", "'numero'", "'texto'", "'leia'", "'escreva'", 
			"'se'", "'senao'", "'enquanto'", "'('", "')'", "';'", null, "'='", "','", 
			"'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "AP", "FP", 
			"SC", "OP", "ATTR", "VIR", "ACH", "FCH", "OPREL", "ID", "NUMBER", "WS", 
			"QUOTES", "TEXTO"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


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
		private int _exprType;
		private ArrayList<AbstractCommand> listaTrue;
		private ArrayList<AbstractCommand> listaFalse;

		private String[] typeDict = new String[] {"numero", "text"};
		
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
			program.generateTarget();
		}


	public IsiLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\31\u00ad\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16"+
		"\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\5\23\u0089\n\23\3\24\3\24\7\24\u008d\n\24\f\24\16\24\u0090"+
		"\13\24\3\25\6\25\u0093\n\25\r\25\16\25\u0094\3\25\3\25\6\25\u0099\n\25"+
		"\r\25\16\25\u009a\5\25\u009d\n\25\3\26\3\26\3\26\3\26\3\27\3\27\3\30\3"+
		"\30\7\30\u00a7\n\30\f\30\16\30\u00aa\13\30\3\30\3\30\2\2\31\3\3\5\4\7"+
		"\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\25)\26+\27-\30/\31\3\2\t\5\2,-//\61\61\4\2>>@@\3\2c|\5\2\62"+
		";C\\c|\3\2\62;\5\2\13\f\17\17\"\"\3\2$$\2\u00b5\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\3\61\3\2\2\2\5:\3\2"+
		"\2\2\7C\3\2\2\2\tJ\3\2\2\2\13P\3\2\2\2\rU\3\2\2\2\17]\3\2\2\2\21`\3\2"+
		"\2\2\23f\3\2\2\2\25o\3\2\2\2\27q\3\2\2\2\31s\3\2\2\2\33u\3\2\2\2\35w\3"+
		"\2\2\2\37y\3\2\2\2!{\3\2\2\2#}\3\2\2\2%\u0088\3\2\2\2\'\u008a\3\2\2\2"+
		")\u0092\3\2\2\2+\u009e\3\2\2\2-\u00a2\3\2\2\2/\u00a4\3\2\2\2\61\62\7r"+
		"\2\2\62\63\7t\2\2\63\64\7q\2\2\64\65\7i\2\2\65\66\7t\2\2\66\67\7c\2\2"+
		"\678\7o\2\289\7c\2\29\4\3\2\2\2:;\7h\2\2;<\7k\2\2<=\7o\2\2=>\7r\2\2>?"+
		"\7t\2\2?@\7q\2\2@A\7i\2\2AB\7=\2\2B\6\3\2\2\2CD\7p\2\2DE\7w\2\2EF\7o\2"+
		"\2FG\7g\2\2GH\7t\2\2HI\7q\2\2I\b\3\2\2\2JK\7v\2\2KL\7g\2\2LM\7z\2\2MN"+
		"\7v\2\2NO\7q\2\2O\n\3\2\2\2PQ\7n\2\2QR\7g\2\2RS\7k\2\2ST\7c\2\2T\f\3\2"+
		"\2\2UV\7g\2\2VW\7u\2\2WX\7e\2\2XY\7t\2\2YZ\7g\2\2Z[\7x\2\2[\\\7c\2\2\\"+
		"\16\3\2\2\2]^\7u\2\2^_\7g\2\2_\20\3\2\2\2`a\7u\2\2ab\7g\2\2bc\7p\2\2c"+
		"d\7c\2\2de\7q\2\2e\22\3\2\2\2fg\7g\2\2gh\7p\2\2hi\7s\2\2ij\7w\2\2jk\7"+
		"c\2\2kl\7p\2\2lm\7v\2\2mn\7q\2\2n\24\3\2\2\2op\7*\2\2p\26\3\2\2\2qr\7"+
		"+\2\2r\30\3\2\2\2st\7=\2\2t\32\3\2\2\2uv\t\2\2\2v\34\3\2\2\2wx\7?\2\2"+
		"x\36\3\2\2\2yz\7.\2\2z \3\2\2\2{|\7}\2\2|\"\3\2\2\2}~\7\177\2\2~$\3\2"+
		"\2\2\177\u0089\t\3\2\2\u0080\u0081\7@\2\2\u0081\u0089\7?\2\2\u0082\u0083"+
		"\7>\2\2\u0083\u0089\7?\2\2\u0084\u0085\7?\2\2\u0085\u0089\7?\2\2\u0086"+
		"\u0087\7#\2\2\u0087\u0089\7?\2\2\u0088\177\3\2\2\2\u0088\u0080\3\2\2\2"+
		"\u0088\u0082\3\2\2\2\u0088\u0084\3\2\2\2\u0088\u0086\3\2\2\2\u0089&\3"+
		"\2\2\2\u008a\u008e\t\4\2\2\u008b\u008d\t\5\2\2\u008c\u008b\3\2\2\2\u008d"+
		"\u0090\3\2\2\2\u008e\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f(\3\2\2\2"+
		"\u0090\u008e\3\2\2\2\u0091\u0093\t\6\2\2\u0092\u0091\3\2\2\2\u0093\u0094"+
		"\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u009c\3\2\2\2\u0096"+
		"\u0098\7\60\2\2\u0097\u0099\t\6\2\2\u0098\u0097\3\2\2\2\u0099\u009a\3"+
		"\2\2\2\u009a\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009d\3\2\2\2\u009c"+
		"\u0096\3\2\2\2\u009c\u009d\3\2\2\2\u009d*\3\2\2\2\u009e\u009f\t\7\2\2"+
		"\u009f\u00a0\3\2\2\2\u00a0\u00a1\b\26\2\2\u00a1,\3\2\2\2\u00a2\u00a3\7"+
		"$\2\2\u00a3.\3\2\2\2\u00a4\u00a8\t\b\2\2\u00a5\u00a7\t\5\2\2\u00a6\u00a5"+
		"\3\2\2\2\u00a7\u00aa\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9"+
		"\u00ab\3\2\2\2\u00aa\u00a8\3\2\2\2\u00ab\u00ac\t\b\2\2\u00ac\60\3\2\2"+
		"\2\13\2\u0088\u008c\u008e\u0094\u009a\u009c\u00a6\u00a8\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}