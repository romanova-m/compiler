/*package mirea.parser;

import mirea.lexer.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RPN {

    public static List<Element> toInfix(List<Token> tokenList) {

        //Преобразование листа типа Token в лист типа Element
        List<Element> inp = new ArrayList();
        for (int i=0; i<inp.size(); i++) {
            inp.set(i, new Element(tokenList.get(i)));
        }
        Stack <Element> s = new Stack<>();
        //Возвращаемый лист
        List<Element> out = new ArrayList();
        String prevElType;

        for (int i=0; i<inp.size(); i++) {

            switch(inp.get(i).getType()) {
                case "R_B":
                    while (!s.peek().equals("(")) {
                        out.add(s.pop());
                    }
                    s.pop();
                    break;

                case "OP":
                    prevElType = inp.get(out.size()-1).getType();
                    if (prevElType.equals("L_B") || prevElType.equals("=")) {
                        out.add(new Element("INT", "0"));
                    }
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
                case ";":
                    prevElType = inp.get(out.size()-1).getType();
                    if (prevElType.equals("ASSIGN") || prevElType.equals)
            }


        }


        while (!s.isEmpty()) {
            out.add(s.pop());
        }
        return out;
    }

    public static int priority(Element el) {

    }

}*/
