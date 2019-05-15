package mirea.interpreter;

import mirea.lexer.Token;
import mirea.lexer.TokenType;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class Interpreter {
    LinkedList<Token> stack = new LinkedList<>();
    Logger logger = Logger.getLogger(Interpreter.class.getName());
    SymbolTable symbolTable = new SymbolTable();
     public Integer count(List<InpInterface> input){
         for (int i = 0; i < input.size(); i++) {
             logger.fine("On element " + i);
             InpInterface record = input.get(i);
             switch (record.getType()) {
                 case "OP":
                     processOp(record.getValue());
                     break;
                 case "VAL":
                     pushNum(record.getValue());
                     break;
                 case "VAR":
                     pushVar(Token.mkVarToken(record.getValue()));
                     break;
                 case "DEF":
                     insertSym(input.get(i+1).getValue(), record.getValue(), null);
                     i++;
                     break;
                 case "!":
                     i = popInt() - 1;
                     break;
                 case "!F":
                     int index = popInt() - 1;
                     if (!(popInt() > 0)) i = index;
                     break;
             }
             logger.fine("Stack: " + Arrays.toString(stack.toArray()));
         }
         return popInt();
     }

    private void insertSym(String name, String type, Object value) {
         logger.finer("symbol table insert var " + name + " type " + type);
        symbolTable.insertSymbol(new Record(name, value, type));
    }

    private int popInt() {
         Token element = stack.pop();
        logger.finer("popped element of type " + element.getTokenType());
         if (element.getTokenType().equals(TokenType.IDENTIFIER)){
             return (Integer) symbolTable.lookup(element.getLexema()).getValue();
         } else return intVal(element.getLexema());
    }

    private void pushInt(int a) {
        stack.push(Token.mkIntToken(a));
    }

    int intVal(String s){
         return Integer.parseInt(s);
    }

    private void processOp(String value) {
         switch (value){
             /* Default operations */
             case "+": pushInt(popInt() + popInt()); break;
             case "-": pushInt(-1 * popInt() + popInt()); break;
             case "*": pushInt(popInt()*popInt());
             case "/":
                 int b = popInt();
                 pushInt(popInt()/b);
                 break;
             case "=":
                 int c = popInt();
                 assignVal(stack.pop(), c);
                 break;
             case "ADD": break;/* Operations with collections*/
             case "PUT": break;
             default: logger.warning("Operator " + value + " not supported");
         }
    }

    private void assignVal(Token destination, int element) {
        Record rec = symbolTable.lookup(destination.getLexema());
        rec.setValue(element);
    }

    private void pushVar(Token token) {
        logger.fine("Pushed " + token.getLexema() + " to stack.");
        stack.push(token);
    }

    private void pushNum(String value) {
         logger.fine("Pushed " + value + " to stack.");
         pushInt(Integer.parseInt(value));
    }

}
