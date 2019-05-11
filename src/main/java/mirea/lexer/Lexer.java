package mirea.lexer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Lexer {
    private StringBuilder input = new StringBuilder();
    private TokenType tokenType;
    private String lexema;
    private boolean exausthed = false;
    private String errorMessage = "";
    private Set<Character> blankChars = new HashSet<>();

    /**
     * Converts program on specified language to tokens
     *
     * @param filePath location of file with input code
     */
    public Lexer(String filePath) {
        try (Stream<String> st = Files.lines(Paths.get(filePath))) {
            st.forEach(input::append);
        } catch (IOException ex) {
            exausthed = true;
            errorMessage = "Could not read file: " + filePath;
            return;
        }
        /* Initializing blank chars array */
        blankChars.add('\r');
        blankChars.add('\n');
        blankChars.add((char) 8 );
        blankChars.add((char) 9);
        blankChars.add((char) 11);
        blankChars.add((char) 12);
        blankChars.add((char) 32);

        moveAhead();
    }

    public void moveAhead() {
        if (exausthed) {
            return;
        }

        if (input.length() == 0) {
            exausthed = true;
            return;
        }

        ignoreWhiteSpaces();

        if (findNextToken()) {
            return;
        }

        exausthed = true;

        if (input.length() > 0) {
            errorMessage = "Unexpected symbol: '" + input.charAt(0) + "'";
        }
    }

    private void ignoreWhiteSpaces() {
        int charsToDelete = 0;

        while (blankChars.contains(input.charAt(charsToDelete))) {
            charsToDelete++;
        }

        if (charsToDelete > 0) {
            input.delete(0, charsToDelete);
        }
    }

    private boolean findNextToken() {
        for (TokenType t : TokenType.values()) {
            int end = t.endOfMatch(input.toString());

            if (end != -1) {
                tokenType = t;
                lexema = input.substring(0, end);
                input.delete(0, end);
                return true;
            }
        }

        return false;
    }

    public TokenType currentTokenType() {
        return tokenType;
    }

    public String currentLexema() {
        return lexema;
    }

    public boolean isSuccessful() {
        return errorMessage.length() == 0;
    }

    public String errorMessage() {
        return errorMessage;
    }

    public boolean isExausthed() {
        return exausthed;
    }

    public List<Token> getAllTokens() {
        List<Token> allTokens = new ArrayList<>();
        while (!isExausthed()){
            allTokens.add(new Token(currentTokenType(), currentLexema()));
            moveAhead();
        }
        return allTokens;
    }
}