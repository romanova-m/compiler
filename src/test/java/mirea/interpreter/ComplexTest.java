package mirea.interpreter;

import mirea.lexer.Lexer;
import mirea.lexer.Token;
import mirea.parser.Element;
import mirea.parser.Parser;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ComplexTest {
    String testFolder = "test_files/";

    @Test
    public void compTest() throws Exception {
            Lexer lexer = new Lexer(testFolder + "compTest.txt");
            List<Token> tokenList = lexer.getAllTokens();
            assertFalse(tokenList.isEmpty());

            for (int i=0; i<tokenList.size(); i++) {
                System.out.printf("tokenType: %s, lexema: %s\n", tokenList.get(i).getTokenType(), tokenList.get(i).getLexema());
            }

            Parser parser = new Parser(tokenList);
            List<Element> out = (parser.lang());

            for (int i=0; i<out.size(); i++) {
                System.out.printf("%d: type: %s, value: %s\n", i, out.get(i).getType(), out.get(i).getValue());
            }

            Interpreter interpreter = new Interpreter();
            interpreter.count(out);
    }

    @Test
    public void compTest1() throws Exception {
        Lexer lexer = new Lexer(testFolder + "langTest");
        List<Token> tokenList = lexer.getAllTokens();
        assertFalse(tokenList.isEmpty());

        for (int i=0; i<tokenList.size(); i++) {
            System.out.printf("tokenType: %s, lexema: %s\n", tokenList.get(i).getTokenType(), tokenList.get(i).getLexema());
        }

        Parser parser = new Parser(tokenList);
        List<Element> out = (parser.lang());

        for (int i=0; i<out.size(); i++) {
            System.out.printf("%d: type: %s, value: %s\n", i, out.get(i).getType(), out.get(i).getValue());
        }

        Interpreter interpreter = new Interpreter(testFolder + "out.txt");
        interpreter.count(out);
    }

    @Test
    public void compTest2() throws Exception {
        Lexer lexer = new Lexer(testFolder + "compTest1.txt");
        List<Token> tokenList = lexer.getAllTokens();
        assertFalse(tokenList.isEmpty());
        for (int i=0; i<tokenList.size(); i++) {
            System.out.printf("tokenType: %s, lexema: %s\n", tokenList.get(i).getTokenType(), tokenList.get(i).getLexema());
        }
        Parser parser = new Parser(tokenList);
        List<Element> out = (parser.lang());
        for (int i=0; i<out.size(); i++) {
            System.out.printf("%d: type: %s, value: %s\n", i, out.get(i).getType(), out.get(i).getValue());
        }
        Interpreter interpreter = new Interpreter(testFolder + "out.txt");
        interpreter.count(out);
    }
}
