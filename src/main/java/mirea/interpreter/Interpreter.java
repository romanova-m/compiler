package mirea.interpreter;

import java.util.*;
import java.util.logging.Logger;

public class Interpreter {

    LinkedList<InpInterface> stack = new LinkedList<>();
    SymbolTable symbolTable = new SymbolTable();
    Logger logger = Logger.getLogger(Interpreter.class.getName());

    private final String INT_TYPE = "INT";
    final String DOUBLE_TYPE = "DOUBLE";
    final String STRING_TYPE = "STRING";
    private final String LIST_TYPE = "List";
    private final String MAP_TYPE = "Map";

    private final String OP_TYPE = "OP";
    private final String VAR_TYPE = "VAR";
    private final String DEF_TYPE = "DEF";
    private final String TR_TYPE = "!";
    private final String TRF_TYPE = "!F";

     public String count(List<InpInterface> input) throws Exception {
         for (int i = 0; i < input.size(); i++) {
             logger.info("On element " + i);
             InpInterface record = input.get(i);
             switch (record.getType()) {
                 case OP_TYPE:
                     processOp(record.getValue());
                     break;
                 case INT_TYPE:
                     stack.push(record);
                     break;
                 case DOUBLE_TYPE:
                     stack.push(record);
                     break;
                 case STRING_TYPE:
                     stack.push(record);
                     break;
                 case VAR_TYPE:
                     stack.push(record);
                     break;
                 case DEF_TYPE:
                     insertSym(input.get(i+1).getValue(), record.getValue(), null);
                     i++;
                     break;
                 case TR_TYPE:
                     i = popInt() - 1;
                     break;
                 case TRF_TYPE:
                     int index = popInt() - 1;
                     if (!(popInt() > 0)) i = index;
                     break;
             }
             logger.info("Stack: " + Arrays.toString(stack.toArray()));
         }
         return stack.isEmpty() ? null : stack.pop().getValue();
     }

    private void processOp(String value) throws Exception {
        switch (value){
            /* Default operations */
            case "+": pushInt(popInt() + popInt()); break;
            case "-": pushInt(-1 * popInt() + popInt()); break;
            case "*": pushInt(popInt()*popInt());
            case "/":
                stack.push(div(stack.pop(), stack.pop()));
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
                System.out.println("Program says: " + stack.pop().getValue());
                break;
            default: logger.warning("Operator " + value + " not supported");
        }
    }

    private void insertSym(String name, String type, Object value) {
         logger.warning("Symbol table insert var " + name + " type " + type);
         if (type.equals(LIST_TYPE)) value = new LinkedList<Integer>(); // int list
         if (type.equals(MAP_TYPE)) value = new HashMap<Integer, Integer>(); // int int map
        symbolTable.insertSymbol(new Record(name, value, type));
    }

    private int popInt() {
         InpInterface element = stack.pop();
        logger.info("Popped element of type " + element.getType());
         if (element.getType().equals(VAR_TYPE)){
             return (Integer) symbolTable.lookup(element.getValue()).getValue();
         } else return intVal(element.getValue());
    }

    InpInterface mkInp(String type, String value){
         return new InpInterface() {
             @Override
             public String getType() {
                 return type;
             }

             @Override
             public String getValue() {
                 return value;
             }
         };
    }

    private void pushInt(int a) {
        stack.push(mkInp(INT_TYPE, "" + a));
    }

    private int intVal(String s){
         return Integer.parseInt(s);
    }
    private double doubleVal(String s){
         return Double.parseDouble(s);
    }

    /**
     * Divides arg1 by arg2. Result type is defined by arg1 type.
     * @param arg2 divider
     * @param arg1 dividend
     */
    private InpInterface div(InpInterface arg2, InpInterface arg1) throws Exception {
        if (!arg1.getType().equals(arg2.getType()))
            logger.warning("Types mismatch(" + arg1.getType() + "/" + arg2.getType() +
                    "): consider reviewing your code");
        switch (arg1.getType()) {
            case INT_TYPE:
                return mkInp(arg1.getType(), String.valueOf(intVal(arg1.getValue()) /
                        intVal(arg2.getValue())));
            case DOUBLE_TYPE:
                return mkInp(arg1.getType(), String.valueOf(doubleVal(arg1.getValue()) /
                        doubleVal(arg2.getValue())));
            default:
                logger.severe("Can not divide type " + arg1.getType());
                throw new Exception("DIVISION OF TYPE " + arg1.getType() + " IS NOT SUPPORTED");
        }
    }

    private void getEl(InpInterface inp, int index) {
        Record record = symbolTable.lookup(inp.getValue());
        Integer val = null;
        switch (record.getType()) {
            case LIST_TYPE:
                List<Integer> list = (LinkedList<Integer>) record.getValue();
                val = list.get(index);
                logger.info("Got " + inp.getValue() + "[" + index + "]=" + val);
                break;
            case MAP_TYPE:
                Map<Integer, Integer> map =
                        (HashMap<Integer, Integer>) record.getValue();
                val = map.get(index);
                logger.info("Got " + inp.getValue() + "[" + index + "]=" + val);
                break;
            default:
                logger.warning("Trying to add to non-list element");
                break;
        }
        if (val != null) stack.push(mkInp(INT_TYPE,val.toString()));
    }

    private void putEl(InpInterface inp, int key, int val) {
        Record record = symbolTable.lookup(inp.getValue());
        if (record.getType().equals(MAP_TYPE)){
            Map<Integer, Integer> map = (HashMap<Integer, Integer>) record.getValue();
            map.put(key, val);
            logger.info("Put to " + inp.getValue() + " [" + key + "," + val + "]");
        }
        else logger.warning("Trying to pup to non-map element");
    }

    private void addEl(InpInterface inp, int d) {
         Record record = symbolTable.lookup(inp.getValue());
         if (record.getType().equals(LIST_TYPE)){
             List<Integer> list = (LinkedList<Integer>) record.getValue();
             list.add(d);
             logger.info("Put to " + inp.getValue() + " " + d);
         }
         else logger.warning("Trying to add to non-list element");
    }

    private void assignVal(InpInterface destination, int element) {
        Record rec = symbolTable.lookup(destination.getValue());
        rec.setValue(element);
    }
}