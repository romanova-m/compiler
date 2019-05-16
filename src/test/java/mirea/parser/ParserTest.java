package mirea.parser;

import mirea.lexer.Lexer;
import mirea.lexer.Token;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ParserTest {
    private String testFolder = "test_files/";

    @Test
    public void expr() {
    }

    @Test
    public void lang() {
        Lexer lexer = new Lexer(testFolder + "langTest");
        List<Token> tokenList = lexer.getAllTokens();
        assertFalse(tokenList.isEmpty());
        for (int i=0; i<tokenList.size(); i++) {
            System.out.printf("tokenType: %s, lexema: %s\n", tokenList.get(i).getTokenType(), tokenList.get(i).getLexema());
        }
        Parser parser = new Parser(tokenList);
        parser.lang();
        assertEquals(tokenList.size(), parser.num);
    }

    @Test
    public void print_stmt() {
        Lexer lexer = new Lexer(testFolder + "printTest");
        List<Token> tokenList = lexer.getAllTokens();
        assertFalse(tokenList.isEmpty());
        for (int i=0; i<tokenList.size(); i++) {
            System.out.printf("tokenType: %s, lexema: %s\n", tokenList.get(i).getTokenType(), tokenList.get(i).getLexema());
        }
        Parser parser = new Parser(tokenList);
        assertTrue(parser.print_stmt());
    }

    @Test
    public void objectOp_stmt() {
        Lexer lexer = new Lexer(testFolder + "objectTest");
        List<Token> tokenList = lexer.getAllTokens();
        assertFalse(tokenList.isEmpty());
        for (int i=0; i<tokenList.size(); i++) {
            System.out.printf("tokenType: %s, lexema: %s\n", tokenList.get(i).getTokenType(), tokenList.get(i).getLexema());
        }
        Parser parser = new Parser(tokenList);
        assertTrue(parser.objectOp_stmt());
    }

    @Test
    public void assign_stmt() {
        Lexer lexer = new Lexer(testFolder + "typesTest");
        List<Token> tokenList = lexer.getAllTokens();
        assertFalse(tokenList.isEmpty());
        for (int i=0; i<tokenList.size(); i++) {
            System.out.printf("tokenType: %s, lexema: %s\n", tokenList.get(i).getTokenType(), tokenList.get(i).getLexema());
        }
        Parser parser = new Parser(tokenList);
        assertTrue(parser.assign_stmt());
    }

    @Test
    public void declar_stmt() {
        Lexer lexer = new Lexer(testFolder + "declarTest");
        List<Token> tokenList = lexer.getAllTokens();
        assertFalse(tokenList.isEmpty());
        for (int i=0; i<tokenList.size(); i++) {
            System.out.printf("tokenType: %s, lexema: %s\n", tokenList.get(i).getTokenType(), tokenList.get(i).getLexema());
        }
        Parser parser = new Parser(tokenList);
        assertTrue(parser.declar_stmt());
    }

    @Test
    public void if_stmt() {
        Lexer lexer = new Lexer(testFolder + "ifTest");
        List<Token> tokenList = lexer.getAllTokens();
        for (int i=0; i<tokenList.size(); i++) {
            System.out.printf("tokenType: %s, lexema: %s\n", tokenList.get(i).getTokenType(), tokenList.get(i).getLexema());
        }
        assertFalse(tokenList.isEmpty());
        Parser parser = new Parser(tokenList);
        assertTrue(parser.if_stmt());

    }
}