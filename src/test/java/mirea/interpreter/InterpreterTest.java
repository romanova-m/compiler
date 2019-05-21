package mirea.interpreter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class InterpreterTest {

    @Test
    public void count() throws Exception {
        Interpreter interpreter = new Interpreter();
        List<ElementInterface> inp = new ArrayList<>();
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "ADR";
            }

            @Override
            public String getValue() {
                return "a";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "DEF";
            }

            @Override
            public String getValue() {
                return "INT";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "ADR";
            }

            @Override
            public String getValue() {
                return "a";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "INT";
            }

            @Override
            public String getValue() {
                return "2";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "OP";
            }

            @Override
            public String getValue() {
                return "=";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "INT";
            }

            @Override
            public String getValue() {
                return "3";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "VAR";
            }

            @Override
            public String getValue() {
                return "a";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "OP";
            }

            @Override
            public String getValue() {
                return "+";
            }
        });
        // a int a 2 = 3 a +
        assertEquals("5", interpreter.count(inp));
    }
    @Test
    public void count1() throws Exception {
        Interpreter interpreter = new Interpreter("test_files/out.txt");
        List<ElementInterface> inp = new ArrayList<>();
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "ADR";
            }

            @Override
            public String getValue() {
                return "a";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "DEF";
            }

            @Override
            public String getValue() {
                return "List";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "ADR";
            }

            @Override
            public String getValue() {
                return "a";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "INT";
            }

            @Override
            public String getValue() {
                return "2";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "OP";
            }

            @Override
            public String getValue() {
                return "add";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "ADR";
            }

            @Override
            public String getValue() {
                return "a";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "INT";
            }

            @Override
            public String getValue() {
                return "0";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "OP";
            }

            @Override
            public String getValue() {
                return "get";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "OP";
            }

            @Override
            public String getValue() {
                return "print";
            }
        });
        // a int a 2 = 3 a +
        interpreter.count(inp);
    }

    @Test
    public void count2() throws Exception {
        Interpreter interpreter = new Interpreter();
        List<ElementInterface> inp = new ArrayList<>();
        inp.add(new ElementInterface() {

            @Override
            public String getType() {
                return "INT";
            }

            @Override
            public String getValue() {
                return "0";
            }
        });
        inp.add(new ElementInterface() {

            @Override
            public String getType() {
                return "INT";
            }

            @Override
            public String getValue() {
                return "5";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "!F";
            }

            @Override
            public String getValue() {
                return "!F";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "INT";
            }

            @Override
            public String getValue() {
                return "3";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "OP";
            }

            @Override
            public String getValue() {
                return "print";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "INT";
            }

            @Override
            public String getValue() {
                return "4";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "OP";
            }

            @Override
            public String getValue() {
                return "print";
            }
        });
        interpreter.count(inp);
    }

    @Test
    public void count3() throws Exception {
        Interpreter interpreter = new Interpreter();
        List<ElementInterface> inp = new ArrayList<>();
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "ADR";
            }

            @Override
            public String getValue() {
                return "a";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "DEF";
            }

            @Override
            public String getValue() {
                return "DOUBLE";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "ADR";
            }

            @Override
            public String getValue() {
                return "b";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "DEF";
            }

            @Override
            public String getValue() {
                return "DOUBLE";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "ADR";
            }

            @Override
            public String getValue() {
                return "a";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "DOUBLE";
            }

            @Override
            public String getValue() {
                return "9.0";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "OP";
            }

            @Override
            public String getValue() {
                return "=";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "ADR";
            }

            @Override
            public String getValue() {
                return "b";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "DOUBLE";
            }

            @Override
            public String getValue() {
                return "3.0";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "OP";
            }

            @Override
            public String getValue() {
                return "=";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "VAR";
            }

            @Override
            public String getValue() {
                return "a";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "VAR";
            }

            @Override
            public String getValue() {
                return "b";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "OP";
            }

            @Override
            public String getValue() {
                return "/";
            }
        });
        assertEquals("3.0", interpreter.count(inp));
    }

    @Test
    public void count4() throws Exception {
        Interpreter interpreter = new Interpreter();
        List<ElementInterface> inp = new ArrayList<>();
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "INT";
            }

            @Override
            public String getValue() {
                return "1";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "INT";
            }

            @Override
            public String getValue() {
                return "0";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "OP";
            }

            @Override
            public String getValue() {
                return "||";
            }
        });

        assertEquals("0", interpreter.count(inp));
    }

    @Test
    public void countDiv() throws Exception {
        Interpreter interpreter = new Interpreter();
        List<ElementInterface> inp = new ArrayList<>();
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "DOUBLE";
            }

            @Override
            public String getValue() {
                return "6.0";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "DOUBLE";
            }

            @Override
            public String getValue() {
                return "2.0";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "OP";
            }

            @Override
            public String getValue() {
                return "/";
            }
        });
        assertEquals("3.0", interpreter.count(inp));
    }

    @Test
    public void countSum() throws Exception {
        Interpreter interpreter = new Interpreter();
        List<ElementInterface> inp = new ArrayList<>();
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "DOUBLE";
            }

            @Override
            public String getValue() {
                return "6.0";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "DOUBLE";
            }

            @Override
            public String getValue() {
                return "2.0";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "OP";
            }

            @Override
            public String getValue() {
                return "+";
            }
        });
        assertEquals("8.0", interpreter.count(inp));
    }

    @Test
    public void scopesTest() throws Exception {
        Interpreter interpreter = new Interpreter("test_files/out.txt");
        List<ElementInterface> inp = new ArrayList<>();
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "ADR";
            }

            @Override
            public String getValue() {
                return "a";
            }
        });inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "DEF";
            }

            @Override
            public String getValue() {
                return "STRING";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "ADR";
            }

            @Override
            public String getValue() {
                return "a";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "STRING";
            }

            @Override
            public String getValue() {
                return "Hello from outer scope";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "OP";
            }

            @Override
            public String getValue() {
                return "=";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "(";
            }

            @Override
            public String getValue() {
                return "(";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "ADR";
            }

            @Override
            public String getValue() {
                return "a";
            }
        });inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "DEF";
            }

            @Override
            public String getValue() {
                return "STRING";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "ADR";
            }

            @Override
            public String getValue() {
                return "a";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "STRING";
            }

            @Override
            public String getValue() {
                return "Hello from inner scope";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "OP";
            }

            @Override
            public String getValue() {
                return "=";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "VAR";
            }

            @Override
            public String getValue() {
                return "a";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "OP";
            }

            @Override
            public String getValue() {
                return "println";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return ")";
            }

            @Override
            public String getValue() {
                return ")";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "VAR";
            }

            @Override
            public String getValue() {
                return "a";
            }
        });
        inp.add(new ElementInterface() {
            @Override
            public String getType() {
                return "OP";
            }

            @Override
            public String getValue() {
                return "println";
            }
        });
        interpreter.count(inp);
    }
}