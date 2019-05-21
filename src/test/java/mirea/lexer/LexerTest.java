package mirea.lexer;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class LexerTest {
    private String testFolder = "test_files/";


    @Test
    public void getAllTokens() {
        Lexer lexer = new Lexer(testFolder + "typesTest");
        List<Token> tokenList = lexer.getAllTokens();
        for (int i = 0; i < tokenList.size(); i++) {
            System.out.printf("tokenType: %s, lexema: %s\n", tokenList.get(i).getTokenType(), tokenList.get(i).getLexema());
        }
    }
}