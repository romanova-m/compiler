package mirea.lexer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TokenType {

    TK_MINUS ("-"),
    TK_PLUS ("\\+"),
    TK_MUL ("\\*"),
    TK_DIV ("/"),
    TK_NOT ("~"),
    TK_AND ("&"),
    TK_OR ("\\|"),
    DIFFERENT ("<>"),
    TK_LEG ("<="),
    TK_LESS ("<"),
    TK_GEQ (">="),
    TK_GT (">"),
    TK_EQ ("=="),
    TK_ASSIGN ("="),
    TK_OPEN ("\\("),
    TK_CLOSE ("\\)"),
    TK_SEMI (";"),
    TK_COMMA (","),
    TK_KEY_DEFINE ("define"),
    TK_KEY_AS ("as"),
    TK_KEY_IS ("is"),
    TK_KEY_IF ("if"),
    TK_KEY_THEN ("then"),
    TK_KEY_ELSE ("else"),
    TK_KEY_ENDIF ("endif"),
    OPEN_BRACKET ("\\{"),
    CLOSE_BRACKET ("\\}"),

    STRING ("\"[^\"]+\""),
    FLOAT ("\\d+\\.\\d+"), // ORDER IS IMPORTANT
    INTEGER ("\\d+"),
    IDENTIFIER ("\\w+");

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
