package mirea.lexer;

public class Token {
    private TokenType tokenType;
    private String lexema;

    Token (TokenType tokenType, String lexema){
        this.tokenType = tokenType;
        this.lexema = lexema;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public static Token mkIntToken(int value){
        return new Token(TokenType.INT, String.valueOf(value));
    }
    public static Token mkVarToken(String value){
        return new Token(TokenType.VAR, value);
    }
}
