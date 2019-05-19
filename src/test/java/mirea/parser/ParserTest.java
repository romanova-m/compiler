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
        List<Element> out = (parser.lang());
        assertEquals(tokenList.size()-1, parser.num);
        for (int i=0; i<out.size(); i++) {
            System.out.printf("%d: type: %s, value: %s\n", i, out.get(i).getType(), out.get(i).getValue());
        }
    }
}