package mirea.interpreter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class InterpreterTest {

    @Test
    public void count() {
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

            @Override
            public String getTokenType() {
                return null;
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

            @Override
            public String getTokenType() {
                return null;
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

            @Override
            public String getTokenType() {
                return null;
            }
        });
        inp.add(new InpInterface() {
            @Override
            public String getType() {
                return "VAL";
            }

            @Override
            public String getValue() {
                return "2";
            }

            @Override
            public String getTokenType() {
                return null;
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

            @Override
            public String getTokenType() {
                return null;
            }
        });
        inp.add(new InpInterface() {
            @Override
            public String getType() {
                return "VAL";
            }

            @Override
            public String getValue() {
                return "3";
            }

            @Override
            public String getTokenType() {
                return null;
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

            @Override
            public String getTokenType() {
                return null;
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

            @Override
            public String getTokenType() {
                return null;
            }
        });
        // a int a 2 = 3 a +
        assertEquals((Object) 5, interpreter.count(inp));
    }
    @Test
    public void count1() {
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

            @Override
            public String getTokenType() {
                return null;
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

            @Override
            public String getTokenType() {
                return null;
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

            @Override
            public String getTokenType() {
                return null;
            }
        });
        inp.add(new InpInterface() {
            @Override
            public String getType() {
                return "VAL";
            }

            @Override
            public String getValue() {
                return "2";
            }

            @Override
            public String getTokenType() {
                return null;
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

            @Override
            public String getTokenType() {
                return null;
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

            @Override
            public String getTokenType() {
                return null;
            }
        });
        inp.add(new InpInterface() {
            @Override
            public String getType() {
                return "VAL";
            }

            @Override
            public String getValue() {
                return "0";
            }

            @Override
            public String getTokenType() {
                return null;
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

            @Override
            public String getTokenType() {
                return null;
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

            @Override
            public String getTokenType() {
                return null;
            }
        });
        // a int a 2 = 3 a +
        interpreter.count(inp);
    }
}