package mirea.interpreter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class InterpreterTest {

    @Test
    public void count() throws Exception {
        Interpreter interpreter = new Interpreter();
        List<InpInterface> inp = new ArrayList<>();
        inp.add(new InpInterface() {
            @Override
            public String getType() {
                return "VAR";
            }

            @Override
            public String getValue() {
                return "a";
            }
        });
        inp.add(new InpInterface() {
            @Override
            public String getType() {
                return "DEF";
            }

            @Override
            public String getValue() {
                return "int";
            }
        });
        inp.add(new InpInterface() {
            @Override
            public String getType() {
                return "VAR";
            }

            @Override
            public String getValue() {
                return "a";
            }
        });
        inp.add(new InpInterface() {
            @Override
            public String getType() {
                return "INT";
            }

            @Override
            public String getValue() {
                return "2";
            }
        });
        inp.add(new InpInterface() {
            @Override
            public String getType() {
                return "OP";
            }

            @Override
            public String getValue() {
                return "=";
            }
        });
        inp.add(new InpInterface() {
            @Override
            public String getType() {
                return "INT";
            }

            @Override
            public String getValue() {
                return "3";
            }
        });
        inp.add(new InpInterface() {
            @Override
            public String getType() {
                return "VAR";
            }

            @Override
            public String getValue() {
                return "a";
            }
        });
        inp.add(new InpInterface() {
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
        Interpreter interpreter = new Interpreter();
        List<InpInterface> inp = new ArrayList<>();
        inp.add(new InpInterface() {
            @Override
            public String getType() {
                return "VAR";
            }

            @Override
            public String getValue() {
                return "a";
            }
        });
        inp.add(new InpInterface() {
            @Override
            public String getType() {
                return "DEF";
            }

            @Override
            public String getValue() {
                return "List";
            }
        });
        inp.add(new InpInterface() {
            @Override
            public String getType() {
                return "VAR";
            }

            @Override
            public String getValue() {
                return "a";
            }
        });
        inp.add(new InpInterface() {
            @Override
            public String getType() {
                return "INT";
            }

            @Override
            public String getValue() {
                return "2";
            }
        });
        inp.add(new InpInterface() {
            @Override
            public String getType() {
                return "OP";
            }

            @Override
            public String getValue() {
                return "add";
            }
        });
        inp.add(new InpInterface() {
            @Override
            public String getType() {
                return "VAR";
            }

            @Override
            public String getValue() {
                return "a";
            }
        });
        inp.add(new InpInterface() {
            @Override
            public String getType() {
                return "INT";
            }

            @Override
            public String getValue() {
                return "0";
            }
        });
        inp.add(new InpInterface() {
            @Override
            public String getType() {
                return "OP";
            }

            @Override
            public String getValue() {
                return "get";
            }
        });
        inp.add(new InpInterface() {
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
    public void countDiv() throws Exception {
        Interpreter interpreter = new Interpreter();
        List<InpInterface> inp = new ArrayList<>();
        inp.add(new InpInterface() {
            @Override
            public String getType() {
                return "DOUBLE";
            }

            @Override
            public String getValue() {
                return "6.0";
            }
        });
        inp.add(new InpInterface() {
            @Override
            public String getType() {
                return "DOUBLE";
            }

            @Override
            public String getValue() {
                return "2.0";
            }
        });
        inp.add(new InpInterface() {
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
}