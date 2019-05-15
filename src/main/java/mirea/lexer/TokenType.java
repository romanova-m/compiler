package mirea.lexer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TokenType {

    OP ("^(\\-|\\+|\\*|\\/)"),
    COMP_OP("(==|!=|<=|>=|<|>)"),
    LOG_OP("\\&\\&|\\|\\|"),
    ASSIGN_OP ("="),
    L_B ("\\("),
    R_B ("\\)"),
    SEMI (";"),
    IF ("if"),
    WHILE ("while"),
    THEN ("then"),
    ELSE ("else"),
    VAR ("([a-zA-Z]|_)+\\w*"),
    NUM ("0|[1-9][0-9]*");


    /*STRING ("\"[^\"]+\""),
    FLOAT ("\\d+\\.\\d+"), // ORDER IS IMPORTANT
    INTEGER ("\\d+"),
    IDENTIFIER ("\\w+");*/

    private final Pattern pattern;

    TokenType(String regex) {
        pattern = Pattern.compile("^" + regex);
    }

    public int endOfMatch(String s) {
        Matcher m = pattern.matcher(s);

        if (m.find()) {
            return m.end();
        }
        return -1;
    }
}
