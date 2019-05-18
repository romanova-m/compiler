package mirea.interpreter;

import java.util.*;
import java.util.logging.Logger;

public class Interpreter {

    LinkedList<InpInterface> stack = new LinkedList<>();
    SymbolTable symbolTable = new SymbolTable();
    Logger logger = Logger.getLogger(Interpreter.class.getName());

    private final String INT_TYPE = "INT";
    private final String DOUBLE_TYPE = "DOUBLE";
    private final String STRING_TYPE = "STRING";
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
                 /* Обработка операторов(вычисления) */
                 case OP_TYPE:      processOp(record.getValue()); break;
                 /* Обработка операндов(положить в стек) */
                 case INT_TYPE:     stack.push(record); break;
                 case DOUBLE_TYPE:  stack.push(record); break;
                 case STRING_TYPE:  stack.push(record); break;
                 case VAR_TYPE:     stack.push(record); break;
                 /* Объявления переменных */
                 case DEF_TYPE:     insertSym(stack.pop().getValue(), record.getValue()); break;
                 /* Безусловный переход */
                 case TR_TYPE: i = intVal(stack.pop().getValue()) - 1; break;
                 /* Переход по лжи */
                 case TRF_TYPE:
                     int index = intVal(stack.pop().getValue()) - 1;
                     if (!isTrue(stack.pop())) i = index; break;
             }
             logger.info("Stack: " + strVal(stack));
         }
         return stack.isEmpty() ? null : stack.pop().getValue();
     }

    private boolean isTrue(InpInterface pop) throws Exception {
         pop = getData(pop);
         if (!pop.getType().equals(INT_TYPE)){
             logger.severe("Conditions of " + pop.getType() + " type are not supported");
             throw new Exception("Condition type " + pop.getType() + "not supported");
         }
         return intVal(pop.getValue()) != 0;
    }

    /* Makes string with stack values */
    private String strVal(LinkedList<InpInterface> stack) {
        StringBuilder stringBuilder = new StringBuilder("[ ");
        for (InpInterface inp: stack) {
            stringBuilder.append(inp.getValue() + " ");
        }
        return stringBuilder.append("]").toString();
    }


    private void processOp(String value) throws Exception {
        switch (value){
            /* Default operations */
            case "+": stack.push(sum(stack.pop(), stack.pop())); break;
            case "-": stack.push(dif(stack.pop(), stack.pop())); break;
            case "*": stack.push(mul(stack.pop(), stack.pop())); break;
            case "/": stack.push(div(stack.pop(), stack.pop())); break;
            case "=": assignVal(stack.pop(), stack.pop()); break;
            case "add":/* TODO Operations with collections*/
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
                System.out.println("Program says: " + stack.pop().getValue() + "\n");
                break;
            default: logger.warning("Operator " + value + " not supported");
        }
    }

    /**
     * Counts multiplication of arg1 and arg2. Result type is defined by arg1 type.
     * @param arg2 multiplier 2
     * @param arg1 multiplier 1
     * @throws Exception when arg1 type is not double or int
     */
    private InpInterface mul(InpInterface arg2, InpInterface arg1) throws Exception{
        arg1 = getData(arg1); arg2 = getData(arg2);
        if (!arg1.getType().equals(arg2.getType()))
            logger.warning("Types mismatch(" + arg1.getType() + "*" + arg2.getType() +
                    "): consider reviewing your code");
        switch (arg1.getType()) {
            case INT_TYPE:
                return mkInp(arg1.getType(), String.valueOf(
                        intVal(arg1.getValue()) * intVal(arg2.getValue())));
            case DOUBLE_TYPE:
                return mkInp(arg1.getType(), String.valueOf(
                        doubleVal(arg1.getValue()) * doubleVal(arg2.getValue())));
            default:
                logger.severe("Can not multiply type " + arg1.getType());
                throw new Exception("MULTIPLY OF TYPE " + arg1.getType() + " IS NOT SUPPORTED");
        }
    }

    /**
     * Counts difference of arg1 and arg2. Result type is defined by arg1 type.
     * @param arg2 subtrahend
     * @param arg1 minuend
     * @throws Exception when arg1 type is not double or int
     */
    private InpInterface dif(InpInterface arg2, InpInterface arg1) throws Exception{
        arg1 = getData(arg1); arg2 = getData(arg2);
        if (!arg1.getType().equals(arg2.getType()))
            logger.warning("Types mismatch(" + arg1.getType() + "-" + arg2.getType() +
                    "): consider reviewing your code");
        switch (arg1.getType()) {
            case INT_TYPE:
                return mkInp(arg1.getType(), String.valueOf(
                        intVal(arg1.getValue()) - intVal(arg2.getValue())));
            case DOUBLE_TYPE:
                return mkInp(arg1.getType(), String.valueOf(
                        doubleVal(arg1.getValue()) - doubleVal(arg2.getValue())));
            default:
                logger.severe("Can not subtract type " + arg1.getType());
                throw new Exception("DIFFERENCE OF TYPE " + arg1.getType() + " IS NOT SUPPORTED");
        }
    }

    /**
     * Receives value from symbol table variable
     * @param inp element for converting
     * @return actual value of the variable or initial element
     */
    private InpInterface getData(InpInterface inp) throws Exception {
        if (!inp.getType().equals(VAR_TYPE)) return inp;
        Record rec = symbolTable.lookup(inp.getValue());
        if (rec == null) {
            logger.severe("Variable " + inp.getValue() + " does not exist");
            throw new Exception("Variable " + inp.getValue() + " not defined in this scope.");
        }
        return mkInp(rec.getType(), rec.getValue().toString());
    }

    /**
     * Counts sum of arg1 and arg2. Result type is defined by arg1 type.
     * @param arg2 addend 2
     * @param arg1 addend 1
     * @throws Exception when arg1 type is not double or int
     */
    private InpInterface sum(InpInterface arg2, InpInterface arg1) throws Exception{
        arg1 = getData(arg1); arg2 = getData(arg2);
        if (!arg1.getType().equals(arg2.getType()))
            logger.warning("Types mismatch(" + arg1.getType() + "+" + arg2.getType() +
                    "): consider reviewing your code");
        switch (arg1.getType()) {
            case INT_TYPE:
                return mkInp(arg1.getType(), String.valueOf(
                        intVal(arg1.getValue()) + intVal(arg2.getValue())));
            case DOUBLE_TYPE:
                return mkInp(arg1.getType(), String.valueOf(
                        doubleVal(arg1.getValue()) + doubleVal(arg2.getValue())));
            default:
                logger.severe("Can not sum type " + arg1.getType());
                throw new Exception("SUM OF TYPE " + arg1.getType() + " IS NOT SUPPORTED");
        }
    }

    /**
     * Divides arg1 by arg2. Result type is defined by arg1 type.
     * @param arg2 divider
     * @param arg1 dividend
     * @throws Exception when arg1 type is not double or int
     */
    private InpInterface div(InpInterface arg2, InpInterface arg1) throws Exception {
        arg1 = getData(arg1); arg2 = getData(arg2);
        if (!arg1.getType().equals(arg2.getType()))
            logger.warning("Types mismatch(" + arg1.getType() + "/" + arg2.getType() +
                    "): consider reviewing your code");
        switch (arg1.getType()) {
            case INT_TYPE:
                return mkInp(arg1.getType(), String.valueOf(
                        intVal(arg1.getValue()) / intVal(arg2.getValue())));
            case DOUBLE_TYPE:
                return mkInp(arg1.getType(), String.valueOf(
                        doubleVal(arg1.getValue()) / doubleVal(arg2.getValue())));
            default:
                logger.severe("Can not divide type " + arg1.getType());
                throw new Exception("DIVISION OF TYPE " + arg1.getType() + " IS NOT SUPPORTED");
        }
    }

    private void insertSym(String name, String type) {
        Object value = null;
         logger.warning("Symbol table insert symbol " + name + " type " + type);
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

    private int intVal(String s){
         return Integer.parseInt(s);
    }
    private double doubleVal(String s){
         return Double.parseDouble(s);
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

    private void assignVal(InpInterface element, InpInterface destination) throws Exception {
        element = getData(element);
        Record destRec = symbolTable.lookup(destination.getValue());
        if (destRec == null) {
            logger.severe("Variable " + destination.getValue() + " does not exist");
            throw new Exception("Variable " + destination.getValue() + " not defined in this scope.");
        }
        if (!destRec.getType().equals(element.getType())){
            logger.severe("Type mismatch [" + destRec.getType() + ", " + element.getType() + "]");
            throw new Exception("Assigning type " + element.getType() + " to " + destRec.getType());
        }
        destRec.setValue(element.getValue());
    }
}