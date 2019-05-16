package mirea.parser;

import mirea.lexer.Token;

public class Element {
    String type;
    String value;

    public Element (String type, String value) {
        this.type = type;
        this.value = value;
    }

    public Element (Token token) {
        this.type = token.getTokenType().name();
        this.value = token.getLexema();
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
