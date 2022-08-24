package br.com.professorisidro.isilanguage.ast;

import java.util.ArrayList;

public class CommandFacaEnquanto extends AbstractCommand {

    private String loopCondition;
    private ArrayList<AbstractCommand> loopCommands;

    public CommandFacaEnquanto(String loopCondition, ArrayList<AbstractCommand> loopCommands)
    {
        this.loopCondition = loopCondition;
        this.loopCommands = loopCommands;
    }

    @Override
    public String generateJavaCode() {

        StringBuilder str = new StringBuilder();

        str.append("do {\n");
        for(AbstractCommand cmd : loopCommands) {
            str.append(cmd.generateJavaCode());
        }
        str.append("\n} while ("+loopCondition+");");
        return str.toString();

    }

    @Override
    public String generatePythonCode() {

        StringBuilder str = new StringBuilder();

        for(AbstractCommand cmd : loopCommands) {
            str.append(cmd.generatePythonCode());
        }
        str.append("while ("+loopCondition+"):\n");
        for(AbstractCommand cmd : loopCommands) {
            str.append("    "+cmd.generatePythonCode());
        }
        return str.toString();

    }

    @Override
    public String toString() {
        return "CommandEnquanto [faça=" + loopCommands + ", loopCondition " + loopCondition
                + "]";
    }

}