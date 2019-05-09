package mirea.lexer;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class LexerTest {

    private String testFolder = "test_files/";
    @Test
    public void currentToken() {
        Lexer lexer = new Lexer(testFolder + "test.txt");
        Assert.assertEquals(Token.TK_KEY_IF,lexer.currentToken());
        Assert.assertEquals(Token.TK_KEY_IF, lexer.currentToken());
        lexer.moveAhead();
        Assert.assertEquals(Token.TK_OPEN, lexer.currentToken());
    }

    @Test
    public void currentLexema() {
        Lexer lexer = new Lexer(testFolder + "test.txt");
        Assert.assertEquals("if", lexer.currentLexema());
        lexer.moveAhead();
        Assert.assertEquals("(", lexer.currentLexema());
    }

    @Test
    public void isSuccessful() {
        Lexer lexer = new Lexer(testFolder + "test.txt");
        while(!lexer.isExausthed()) {
            lexer.moveAhead();
        }
        Assert.assertTrue(lexer.isSuccessful());
    }

    @Test
    public void errorMessage() {
        Lexer lexer = new Lexer( testFolder + "test1.txt");
        lexer.currentToken();
        Assert.assertEquals("Unexpected symbol: '$'", lexer.errorMessage());
    }

    @Test
    public void isExausthed() {
        Lexer lexer = new Lexer(testFolder + "test1.txt");
        lexer.currentToken();
        Assert.assertTrue(lexer.isExausthed()); // Unexpected symbol

        lexer = new Lexer(testFolder + "test3.txt"); // Empty file
        Assert.assertTrue(lexer.isExausthed());

        lexer = new Lexer(testFolder + "test.txt"); // Normal file
        Assert.assertFalse(lexer.isExausthed());
    }

    @Test
    public void getAllTokens() {
        Lexer lexer = new Lexer(testFolder + "test3.txt");
        Assert.assertTrue(lexer.getAllTokens().isEmpty());

        lexer = new Lexer(testFolder + "test1.txt");
        Assert.assertTrue(lexer.getAllTokens().isEmpty());

        lexer = new Lexer(testFolder + "test.txt");
        Assert.assertFalse(lexer.getAllTokens().isEmpty());

        lexer = new Lexer(testFolder + "test.txt");
        List<Token> list = lexer.getAllTokens();
        Token[] ar = {Token.TK_KEY_IF, Token.TK_OPEN, Token.IDENTIFIER,
                Token.TK_CLOSE, Token.IDENTIFIER, Token.TK_PLUS, Token.INTEGER, Token.TK_SEMI};
        for (int i = 0; i < ar.length; i++) {
            Assert.assertEquals(ar[i],list.get(i));
        }

        lexer = new Lexer(testFolder + "test2.txt");
        list = lexer.getAllTokens();
        Token[] ar1 = {Token.IDENTIFIER, Token.TK_LEG, Token.IDENTIFIER};
        for (int i = 0; i < ar1.length; i++) {
            Assert.assertEquals(ar1[i],list.get(i));
        }
    }
}