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
        while (expr()) {
        }
    }

    public boolean expr() {
        int begNum = num;
        if (declar_stmt() || assign_stmt() || while_stmt() ||  if_stmt() || objectOp_stmt() || print_stmt()) {
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
        if (VAR() && ASSIGN_OP() && (value_stmt() || STRING()) && SEMI()) {
            return true;
        }
        num = begNum;
        return false;
    }

    public boolean objectOp_stmt() {
        int begNum = num;
        if ((objectOneArg() || objectOneArg() || objectPut()) && SEMI()) {
            return true;
        }
        num = begNum;
        return false;
    }

    public boolean objectOneArg() {
        int begNum = num;
        if (VAR() && DOT() && (ADD() || GET()) && value_stmt()) {
            return true;
        }
        num = begNum;
        return false;
    }

    public boolean objectPut() {
        int begNum = num;
        if (VAR() && DOT() && PUT() && value_stmt() && COMMA() && value_stmt()) {
            return true;
        }
        num = begNum;
        return false;
    }

    public boolean PUT() {
        return checkToken("PUT");
    }

    public boolean ADD() {
        return checkToken("ADD");
    }

    public boolean GET() {
        return checkToken("GET");
    }

    public boolean DOT() {
        return checkToken("DOT");
    }

    public boolean while_stmt() {
        int begNum = num;
        if (WHILE() && condition() && DO() && L_CB()) {
            int cycleNum = num;
            while (expr()) {
            }
            if (cycleNum == begNum) {
                num = begNum;
                return false;
            }
            if (R_CB()) return true;
        }
        num = begNum;
        return false;
    }

    public boolean L_CB() {
        return checkToken("L_CB");
    }

    public boolean R_CB() {
        return checkToken("R_CB");
    }

    public boolean if_stmt() {
        int begNum = num;
        if (IF() && condition() && THEN() && L_CB()) {
            int cycleNum = num;
            while(expr()) {
            }
            if (!R_CB()) {
                num = begNum;
                return false;
            }
            if (ELSE() && L_CB()) {
                while (expr()) {
                    cycleNum = num;
                }
            }
            num = cycleNum;
            if (R_CB()) return true;
        }
        num = begNum;
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
        int begNum = num;
        if (PRINT() && (STRING() || value_stmt()) && SEMI()) {
            return true;
        }
        num = begNum;
        return false;
    }

    public boolean COMMA() {
        return checkToken("COMMA");
    }

    public boolean PRINT() {
        return checkToken("PRINT");
    }

    public boolean STRING() {
        return checkToken("STRING");
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

    public boolean value() {
        int begNum = num;
        if (VAR() || num() || b_stmt()) {
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
        if (L_RB() && stmt() && R_RB()) {
            return true;
        }
        num = begNum;
        return false;
    }

    public boolean L_RB() {
        return checkToken("L_RB");
    }

    public boolean R_RB() {
        return checkToken("R_RB");
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
