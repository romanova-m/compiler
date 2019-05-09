package mirea.lexer;

import org.junit.Assert;
import org.junit.Test;

public class TokenTypeTest {

    @Test
    public void endOfMatchTest() {
        String example = "123456789 if";
        Assert.assertEquals(example.indexOf(" "), TokenType.INTEGER.endOfMatch(example));
    }
}