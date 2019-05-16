package mirea.parser;

import mirea.lexer.Token;
import mirea.lexer.TokenType;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class RPN {
    int i; //Текущий элемент
    List<Token> tokenList;


    public RPN(List<Token> tokenList) {
        this.tokenList = tokenList;

    }
    public List<Element> toInfix() {

        //Преобразование листа типа Token в лист типа Element
        List<Element> out = new ArrayList();
        for (int i = 0; i < tokenList.size(); i++) {
            switch (tokenList.get(i).getTokenType().name()) {
                case "ASSIGN_OP":
                    out.add(new Element("OP", tokenList.get(i).getLexema()));
                    break;
                case "COMP_OP":
                    out.add(new Element("OP", tokenList.get(i).getLexema()));
                    break;
                case "ADD":
                    out.add(new Element("OP", tokenList.get(i).getLexema()));
                    break;
                case "GET":
                    out.add(new Element("OP", tokenList.get(i).getLexema()));
                    break;
                case "PUT":
                    out.add(new Element("OP", tokenList.get(i).getLexema()));
                    break;
                default:
                    out.add(new Element(tokenList.get(i)));
                    break;
            }
        }
        return elToInfix(out, new ArrayList<>(),0);
    }

    public List<Element> elToInfix (List<Element> inp, List<Element> out, int beg) {
        Stack <Element> s = new Stack<>();

        //Возвращаемый лист
        String prevElType;

        for (i=beg; i<inp.size(); i++) {

            switch(inp.get(i).getType()) {
                case "R_B":
                    while (!s.peek().equals("(")) {
                        out.add(s.pop());
                    }
                    s.pop();
                    break;

                case "OP":
                    prevElType = inp.get(out.size()-1).getType();
                    if ((prevElType.equals("L_B") || prevElType.equals("=")) && inp.get(i).getValue().equals("-")) {
                        out.add(new Element("INT", "0"));
                    }
                    if (!s.isEmpty()) {
                        if (priority(inp.get(i)) <= priority(s.peek())) {
                            out.add(s.pop());
                        }
                    }
                    s.add(inp.get(i));
                    break;
                case "COMP_OP":
                    if (!s.isEmpty()) {
                        if (priority(inp.get(i)) <= priority(s.peek())) {
                            out.add(s.pop());
                        }
                    }

                    s.add(inp.get(i));
                    break;
                case "DOUBLE":
                    out.add(inp.get(i));
                    break;
                case "INT":
                    out.add(inp.get(i));
                    break;
                case "STRING":
                    out.add(inp.get(i));
                    break;
                case "TYPE":
                    s.add(inp.get(i));
                    break;
                case "SEMI":
                    while (!s.isEmpty())
                        out.add(s.pop());
                    break;
                case "VAR":
                    out.add(inp.get(i));
                    break;
                case "IF":
                    out = ifRPN(inp, out);
                    break;
                case "THEN":
                    while (!s.isEmpty()) {
                        out.add(s.pop());
                    }
                    return out;
                case "ELSE":
                    return out;
                case "L_CB":
                    return out;
                case "R_CB":
                    if (i == inp.size() - 1 || !inp.get(i+1).getType().equals("ELSE")) return out;
                    break;
            }
        }
        while (!s.isEmpty()) {
            out.add(s.pop());
        }
        return out;
    }

    public static Element trans() {
        return new Element("TRANS", "!");
    }

    public static Element transFalse() {
        return new Element("TRANS", "!F");
    }

    public static Element ref() {
        return new Element("REF", "NULL");
    }


    public List<Element> ifRPN (List<Element> inp, List<Element> out) {

        int elseBeg = -1;
        int bodyEndRef = -1;
        int ifEndRef = -1;


        out = elToInfix(inp, out, i+1);
        if (inp.get(i).getType().equals("THEN")) {
            out.add(ref()); //Переход в случае лжи
            ifEndRef = out.size() - 1;
            out.add(transFalse());
            out = elToInfix(inp, out, i+2);
            if (inp.get(i).getType().equals("ELSE")) {
                out.add(ref());
                bodyEndRef = out.size() - 1;
                out.add(trans());
                elseBeg = out.size();
                out = elToInfix(inp, out, i+2);
            }
            if (bodyEndRef != -1) {
                out.set(ifEndRef, new Element("REF", elseBeg + ""));
                out.set(bodyEndRef, new Element("REF", out.size() + ""));
            } else {
                out.set(ifEndRef, new Element("REF", out.size() + ""));
            }

        }
        return out;
    }

    public static int priority(Element el) {
        if (el.getValue().equals("="))
            return 1;
        if (el.getValue().equals("-") || el.getValue().equals("+"))
            return 2;
        if (el.getValue().equals("*") || el.getValue().equals("/"))
            return 3;
        if (el.getType().equals("COMP_OP"))
            return 4;
        return 0;
    }

}
