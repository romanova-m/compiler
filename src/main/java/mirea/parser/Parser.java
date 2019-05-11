package mirea.parser;

import mirea.lexer.Token;

import java.util.List;

public class Parser {
    List<Token> inp;
    Token curToken;
    int num;

    public Parser(List<Token> inp) {
        this.inp = inp;
        this.num = 0;
    }

    public void lang() {
        while (true) {
            expr();
        }
    }

    public boolean expr() {
        int begNum = num;
        if (declar_stmt() || assign_stmt() || while_stmt() ||  if_stmt() || print_stmt()) {
            return true;
        }
        num = begNum;
        return false;
    }

    public boolean declar_stmt() {
        int begNum = num;
        if (TYPE() && VAR() && SEMI()) {
            return true;
        }
        num = begNum;
        return false;
    }

    public boolean assign_stmt() {
        int begNum = num;
        if (VAR() && ASSIGN_OP() && value_stmt()) {
            return true;
        }
        num = begNum;
        return false;
    }

    public boolean while_stmt() {
        int begNum = num;
        if (WHILE() && condition() && DO()) {
            int cycleNum = begNum;
            while (true) {
                if (expr()) cycleNum = num;
                else break;
            }
            if (cycleNum == begNum) {
                num = begNum;
                return false;
            }
            if (SEMI()) return true;
        }
        num = begNum;
        return false;
    }

    public boolean if_stmt() {
        int begNum = num;
        if (IF() && condition() && THEN()) {
            int cycleNum = begNum;
            while(expr()) {
                cycleNum = num;
            }
            if (cycleNum == begNum) {
                num = begNum;
                return false;
            }
            if (ELSE()) {
                while (expr()) {
                    cycleNum = num;
                }
            }
            num = cycleNum;
            if (SEMI()) return true;
        }
        num = begNum;
        return false;
    }

    public boolean for_stmt () {
        return false;
    }


    public boolean DO() {
        return checkToken("DO");
    }

    public boolean condition() {
        int begNum = num;
        if (value_stmt() && COMP_OP() && value_stmt()) {
            int cycleNum = num;
            while (LOG_OP() && condition()) {
                cycleNum = num;
            }
            num = cycleNum;
            return true;
        }
        num = begNum;
        return false;
    }

    public boolean print_stmt() {
        return false;
    }

    public boolean TYPE() {
        return checkToken("TYPE");
    }

    public boolean COMP_OP() {
        return checkToken("COMP_OP");
    }

    public boolean LOG_OP() {
        return checkToken("LOG_OP");
    }


    public boolean WHILE() {
        return checkToken(("WHILE"));
    }

    public boolean IF() {
        return checkToken("IF");
    }

    public boolean SEMI() {
        return checkToken("SEMI");
    }

    public boolean THEN() {
        return checkToken("THEN");
    }

    public boolean ELSE() {
        return checkToken("ELSE");
    }

    public boolean VAR() {
        return checkToken("VAR");
    }

    public boolean ASSIGN_OP() {
        return checkToken("ASSIGN_OP");
    }

    public boolean value_stmt() {
        int begNum = num;
        if (stmt() || b_stmt()) {
            return true;
        }
        num = begNum;
        return false;
    }

    public boolean stmt() {
        int begNum = num;
        if (value()) {
            int cycleNum = num;
            while (OP() && value()) {
                cycleNum = num;
            }
            num = cycleNum;
            return true;
        }
        num = begNum;
        return false;
    }

    //to add b_stmt
    public boolean value() {
        int begNum = num;
        if (VAR() || num()) {
            return true;
        }
        num = begNum;
        return false;
    }

    public boolean num() {

        int begNum = num;
        if (DOUBLE() || INT()) {
            return true;
        }
        num = begNum;
        return false;
    }

    public boolean DOUBLE() {
        return checkToken("DOUBLE");
    }

    public boolean INT() {
        return checkToken("INT");
    }

    public boolean b_stmt() {
        int begNum = num;
        if (L_B() && stmt() && R_B()) {
            return true;
        }
        num = begNum;
        return false;
    }

    public boolean L_B() {
        return checkToken("L_B");
    }

    public boolean R_B() {
        return checkToken("R_B");
    }

    public boolean checkToken(String reqTokenTypeName) {
        if (num == inp.size()) return false;
        int begNum = num;
        match();
        if (curToken.getTokenType().name().equals(reqTokenTypeName)) {
            return true;
        }
        num = begNum;
        return false;
    }

    public boolean OP() {
        return checkToken("OP");
    }

    public void match() {
        curToken = inp.get(num++);
    }

}
