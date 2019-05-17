package mirea.parser;

import mirea.lexer.Lexer;
import mirea.lexer.Token;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class RPNTest {
    private String testFolder = "test_files/";

    @Test
    public void PRN() {
        Lexer lexer = new Lexer(testFolder + "whileTest");
        List<Token> tokenList = lexer.getAllTokens();
        RPN rpn = new RPN(tokenList);
        List<Element> out = rpn.toInfix();
        for (int i=0; i<out.size(); i++) {
            System.out.printf("%d: %s\n", i, out.get(i).getValue());
        }
    }
}