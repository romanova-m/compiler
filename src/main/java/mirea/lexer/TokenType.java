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
    COMMA(","),
    SEMI (";"),
    IF ("if"),
    WHILE ("while"),
    THEN ("then"),
    ELSE ("else"),
    PRINT ("print"),
    TYPE ("(double|int|LinkedList|HashSet)"),
    VAR ("([a-zA-Z]|_)+\\w*"),
    DOUBLE ("(0|[1-9][0-9]*)\\.[0-9]+"), // ORDER IS IMPORTANT
    INT ("(0|[1-9][0-9]*)"),
    STRING ("\"[^\"]*\"");

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