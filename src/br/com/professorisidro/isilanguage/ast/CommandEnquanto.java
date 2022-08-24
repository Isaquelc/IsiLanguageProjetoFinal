package br.com.professorisidro.isilanguage.ast;

import java.util.ArrayList;
public class CommandEnquanto extends AbstractCommand {
	private String condition;
	private ArrayList<AbstractCommand> commandsEnq;

	public CommandEnquanto(String condition, ArrayList<AbstractCommand> commandsEnq) {
		this.condition = condition;
		this.commandsEnq = commandsEnq;

	}

	public String generateJavaCode() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append("while (" + condition + ") {\n");
		for (AbstractCommand cmd : commandsEnq) {
			str.append(cmd.generateJavaCode());
		}
		str.append("}\n");
		return str.toString();
	}

	public String generatePythonCode() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append("while (" + condition + "):\n");
		for (AbstractCommand cmd : commandsEnq) {
			str.append("	"+cmd.generatePythonCode());
		}
		return str.toString();
	}
	
	@Override
	public String toString() {
		return "CommandEnquanto [condition=" + condition + ", commandsEnq="
				+commandsEnq+"]";
	}
}
