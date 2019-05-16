package mirea.interpreter;

import mirea.lexer.Token;
import mirea.lexer.TokenType;

import java.util.*;
import java.util.logging.Logger;

public class Interpreter {
    LinkedList<Token> stack = new LinkedList<>();
    Logger logger = Logger.getLogger(Interpreter.class.getName());
    SymbolTable symbolTable = new SymbolTable();
     public Integer count(List<InpInterface> input){
         for (int i = 0; i < input.size(); i++) {
             logger.info("On element " + i);
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
             logger.info("Stack: " + Arrays.toString(stack.toArray()));
         }
         return stack.isEmpty() ? null : popInt();
     }

    private void insertSym(String name, String type, Object value) {
         logger.info("symbol table insert var " + name + " type " + type);
         if (type.equals("List")) value = new LinkedList<Integer>(); // int list
         if (type.equals("Map")) value = new HashMap<Integer, Integer>(); // int int map
        symbolTable.insertSymbol(new Record(name, value, type));
    }

    private int popInt() {
         Token element = stack.pop();
        logger.info("Popped element of type " + element.getTokenType());
         if (element.getTokenType().equals(TokenType.VAR)){
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
             case "add":/* Operations with collections*/
                 int d = popInt();
                 addEl(stack.pop(), d);
                 break;
             case "get":
                 int e = popInt();
                 getEl(stack.pop(), e);
                 break;
             case "put":
                 int val = popInt();
                 int key = popInt();
                 putEl(stack.pop(), key, val);
                 break;
             case "print":
                 System.out.println("Printed value: " + popInt());
                 break;
             default: logger.warning("Operator " + value + " not supported");
         }
    }

    private void getEl(Token token, int e) {
        Record record = symbolTable.lookup(token.getLexema());
        Integer val = null;
        if (record.getType().equals("List")){
            List<Integer> list = (LinkedList<Integer>) symbolTable.lookup(token.getLexema()).getValue();
            val = list.get(e);
            logger.info("Got " + token.getLexema() + "[" + e + "]=" + val);
        }
        else if (record.getType().equals("Map")){
            Map<Integer, Integer> map =
                    (HashMap<Integer, Integer>) record.getValue();
            val = map.get(e);
            logger.info("Got " + token.getLexema() + "[" + e + "]=" + val);
        }
        else logger.warning("Trying to add to non-list element");
        if (val != null) stack.push(Token.mkIntToken(val));
    }

    private void putEl(Token token, int key, int val) {
        Record record = symbolTable.lookup(token.getLexema());
        if (record.getType().equals("Map")){
            Map<Integer, Integer> map =
                    (HashMap<Integer, Integer>) record.getValue();
            map.put(key, val);
            logger.info("Put to " + token.getLexema() + " [" + key + "," + val + "]");
        }
        else logger.warning("Trying to pup to non-map element");
    }

    private void addEl(Token token, int d) {
         Record record = symbolTable.lookup(token.getLexema());
         if (record.getType().equals("List")){
             List<Integer> list = (LinkedList<Integer>) symbolTable.lookup(token.getLexema()).getValue();
             list.add(d);
             logger.info("Put to " + token.getLexema() + " " + d);
         }
         else logger.warning("Trying to add to non-list element");
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
