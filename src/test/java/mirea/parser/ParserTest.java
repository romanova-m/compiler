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
    public void assign_stmt() {
        Lexer lexer = new Lexer(testFolder + "test4");
        List<Token> tokenList = lexer.getAllTokens();
        assertFalse(tokenList.isEmpty());
        for (int i=0; i<tokenList.size(); i++) {
            System.out.printf("tokenType: %s, lexema: %s\n", tokenList.get(i).getTokenType(), tokenList.get(i).getLexema());
        }
        Parser parser = new Parser(tokenList);
        assertTrue(parser.assign_stmt());
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