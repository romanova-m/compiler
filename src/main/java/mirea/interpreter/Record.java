package mirea.interpreter;

/**
 * Basic record for SymbolTable class.
 * Contains name, value and type of variable.
 * @see SymbolTable
 */
public class Record {
    private String name;
    private Object value;
    private String type;

    Record(String name, Object value, String type){
        this.name = name;
        this.value = value;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
