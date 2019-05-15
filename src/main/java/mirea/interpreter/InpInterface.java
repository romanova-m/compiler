package mirea.interpreter;

public interface InpInterface{
    String getType(); // OP | ID_VAL | ID_ADR | VAL
    String getValue();
    String getTokenType();
}
