import java.io.BufferedReader;
import java.io.InputStreamReader;
import nl.tudelft.instrumentation.patching.*;
import nl.tudelft.instrumentation.runner.CallableTraceRunner;

public class Problem1 implements CallableTraceRunner<Void> {

    static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

    private String[] inputs = { "iA", "iB", "iC", "iD", "iE", "iF", "iG", "iH", "iI", "iJ" };

    public int a1122863037 = 10;

    public int a691849188 = 10;

    public int a1305805768 = 13;

    public int a612577343 = 8;

    public String a1855872761 = "g";

    public boolean cf = true;

    public String a547336540 = "g";

    private void calculateOutputm1(String input) {
        if (((((a547336540.equals("e")) && cf) && (input.equals("iE"))) && (OperatorTracker.myOperator("==", a691849188, 4, 0)))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 8;
            OperatorTracker.output("oX");
        }
        if (((input.equals("iJ")) && (((OperatorTracker.myOperator("==", a691849188, 4, 1)) && cf) && (a547336540.equals("e"))))) {
            cf = false;
            a547336540 = "h";
            a1855872761 = "e";
            OperatorTracker.output("oZ");
        }
        if ((((OperatorTracker.myOperator("==", a691849188, 4, 2)) && ((input.equals("iB")) && cf)) && (a547336540.equals("e")))) {
            cf = false;
            a691849188 = 7;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a691849188, 4, 3)) && ((a547336540.equals("e")) && ((input.equals("iC")) && cf)))) {
            cf = false;
            a691849188 = 7;
            OperatorTracker.output("oU");
        }
        if (((((OperatorTracker.myOperator("==", a691849188, 4, 4)) && cf) && (input.equals("iF"))) && (a547336540.equals("e")))) {
            cf = false;
            a691849188 = 7;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a691849188, 4, 5)) && ((a547336540.equals("e")) && (cf && (input.equals("iH")))))) {
            cf = false;
            a691849188 = 7;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a691849188, 4, 6)) && ((input.equals("iI")) && ((a547336540.equals("e")) && cf)))) {
            cf = false;
            a691849188 = 7;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("e")) && ((input.equals("iA")) && (cf && (OperatorTracker.myOperator("==", a691849188, 4, 7)))))) {
            cf = false;
            a691849188 = 7;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iG")) && (((OperatorTracker.myOperator("==", a691849188, 4, 8)) && cf) && (a547336540.equals("e"))))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 15;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iD")) && ((cf && (a547336540.equals("e"))) && (OperatorTracker.myOperator("==", a691849188, 4, 9))))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 14;
            OperatorTracker.output("oU");
        }
    }

    private void calculateOutputm2(String input) {
        if (((OperatorTracker.myOperator("==", a691849188, 5, 10)) && (((a547336540.equals("e")) && cf) && (input.equals("iH"))))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 13;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("e")) && ((OperatorTracker.myOperator("==", a691849188, 5, 11)) && ((input.equals("iB")) && cf)))) {
            cf = false;
            OperatorTracker.output("oY");
        }
        if (((a547336540.equals("e")) && ((OperatorTracker.myOperator("==", a691849188, 5, 12)) && (cf && (input.equals("iC")))))) {
            cf = false;
            OperatorTracker.output("oY");
        }
        if ((((input.equals("iD")) && ((OperatorTracker.myOperator("==", a691849188, 5, 13)) && cf)) && (a547336540.equals("e")))) {
            cf = false;
            OperatorTracker.output("oY");
        }
        if ((((input.equals("iE")) && (cf && (a547336540.equals("e")))) && (OperatorTracker.myOperator("==", a691849188, 5, 14)))) {
            cf = false;
            OperatorTracker.output("oY");
        }
        if (((input.equals("iF")) && ((a547336540.equals("e")) && (cf && (OperatorTracker.myOperator("==", a691849188, 5, 15)))))) {
            cf = false;
            OperatorTracker.output("oY");
        }
        if (((input.equals("iG")) && (((a547336540.equals("e")) && cf) && (OperatorTracker.myOperator("==", a691849188, 5, 16))))) {
            cf = false;
            OperatorTracker.output("oY");
        }
        if ((((a547336540.equals("e")) && ((OperatorTracker.myOperator("==", a691849188, 5, 17)) && cf)) && (input.equals("iI")))) {
            cf = false;
            OperatorTracker.output("oY");
        }
        if ((((cf && (OperatorTracker.myOperator("==", a691849188, 5, 18))) && (input.equals("iJ"))) && (a547336540.equals("e")))) {
            cf = false;
            OperatorTracker.output("oY");
        }
        if ((((cf && (OperatorTracker.myOperator("==", a691849188, 5, 19))) && (a547336540.equals("e"))) && (input.equals("iA")))) {
            cf = false;
            OperatorTracker.output("oY");
        }
    }

    private void calculateOutputm3(String input) {
        if (((((OperatorTracker.myOperator("==", a691849188, 6, 20)) && cf) && (input.equals("iD"))) && (a547336540.equals("e")))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 14;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iB")) && ((a547336540.equals("e")) && (cf && (OperatorTracker.myOperator("==", a691849188, 6, 21)))))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 13;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a691849188, 6, 22)) && ((cf && (a547336540.equals("e"))) && (input.equals("iC"))))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 13;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iE")) && ((OperatorTracker.myOperator("==", a691849188, 6, 23)) && ((a547336540.equals("e")) && cf)))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 13;
            OperatorTracker.output("oU");
        }
        if ((((OperatorTracker.myOperator("==", a691849188, 6, 24)) && ((a547336540.equals("e")) && cf)) && (input.equals("iF")))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 13;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a691849188, 6, 25)) && ((input.equals("iH")) && (cf && (a547336540.equals("e")))))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 13;
            OperatorTracker.output("oU");
        }
        if ((((cf && (a547336540.equals("e"))) && (OperatorTracker.myOperator("==", a691849188, 6, 26))) && (input.equals("iI")))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 13;
            OperatorTracker.output("oU");
        }
        if (((((input.equals("iJ")) && cf) && (a547336540.equals("e"))) && (OperatorTracker.myOperator("==", a691849188, 6, 27)))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 13;
            OperatorTracker.output("oU");
        }
        if (((((a547336540.equals("e")) && cf) && (OperatorTracker.myOperator("==", a691849188, 6, 28))) && (input.equals("iA")))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 13;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a691849188, 6, 29)) && ((a547336540.equals("e")) && ((input.equals("iG")) && cf)))) {
            cf = false;
            a691849188 = 5;
            OperatorTracker.output("oU");
        }
    }

    private void calculateOutputm4(String input) {
        if ((((OperatorTracker.myOperator("==", a691849188, 7, 30)) && ((input.equals("iB")) && cf)) && (a547336540.equals("e")))) {
            cf = false;
            a691849188 = 6;
            OperatorTracker.output("oU");
        }
        if ((((cf && (a547336540.equals("e"))) && (OperatorTracker.myOperator("==", a691849188, 7, 31))) && (input.equals("iC")))) {
            cf = false;
            a691849188 = 6;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a691849188, 7, 32)) && (((a547336540.equals("e")) && cf) && (input.equals("iE"))))) {
            cf = false;
            a691849188 = 6;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a691849188, 7, 33)) && (((input.equals("iF")) && cf) && (a547336540.equals("e"))))) {
            cf = false;
            a691849188 = 6;
            OperatorTracker.output("oU");
        }
        if (((((input.equals("iH")) && cf) && (a547336540.equals("e"))) && (OperatorTracker.myOperator("==", a691849188, 7, 34)))) {
            cf = false;
            a691849188 = 6;
            OperatorTracker.output("oU");
        }
        if ((((cf && (OperatorTracker.myOperator("==", a691849188, 7, 35))) && (input.equals("iI"))) && (a547336540.equals("e")))) {
            cf = false;
            a691849188 = 6;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a691849188, 7, 36)) && ((a547336540.equals("e")) && (cf && (input.equals("iJ")))))) {
            cf = false;
            a691849188 = 6;
            OperatorTracker.output("oU");
        }
        if ((((cf && (input.equals("iA"))) && (OperatorTracker.myOperator("==", a691849188, 7, 37))) && (a547336540.equals("e")))) {
            cf = false;
            a691849188 = 6;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a691849188, 7, 38)) && ((input.equals("iG")) && ((a547336540.equals("e")) && cf)))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 9;
            OperatorTracker.output("oU");
        }
        if ((((OperatorTracker.myOperator("==", a691849188, 7, 39)) && (cf && (input.equals("iD")))) && (a547336540.equals("e")))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 14;
            OperatorTracker.output("oU");
        }
    }

    private void calculateOutputm5(String input) {
        if (((((a547336540.equals("e")) && cf) && (OperatorTracker.myOperator("==", a691849188, 8, 40))) && (input.equals("iE")))) {
            cf = false;
            a691849188 = 10;
            OperatorTracker.output("oU");
        }
        if ((((OperatorTracker.myOperator("==", a691849188, 8, 41)) && ((input.equals("iH")) && cf)) && (a547336540.equals("e")))) {
            cf = false;
            a691849188 = 10;
            OperatorTracker.output("oU");
        }
        if (((((input.equals("iG")) && cf) && (OperatorTracker.myOperator("==", a691849188, 8, 42))) && (a547336540.equals("e")))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 13;
            OperatorTracker.output("oU");
        }
        if ((((a547336540.equals("e")) && (cf && (OperatorTracker.myOperator("==", a691849188, 8, 43)))) && (input.equals("iD")))) {
            cf = false;
            a547336540 = "h";
            a1855872761 = "g";
            OperatorTracker.output("oU");
        }
        if ((((cf && (a547336540.equals("e"))) && (input.equals("iC"))) && (OperatorTracker.myOperator("==", a691849188, 8, 44)))) {
            cf = false;
            a691849188 = 10;
            OperatorTracker.output("oY");
        }
        if ((((a547336540.equals("e")) && ((input.equals("iF")) && cf)) && (OperatorTracker.myOperator("==", a691849188, 8, 45)))) {
            cf = false;
            a691849188 = 10;
            OperatorTracker.output("oY");
        }
        if (((input.equals("iI")) && ((cf && (a547336540.equals("e"))) && (OperatorTracker.myOperator("==", a691849188, 8, 46))))) {
            cf = false;
            a691849188 = 10;
            OperatorTracker.output("oY");
        }
        if (((((OperatorTracker.myOperator("==", a691849188, 8, 47)) && cf) && (a547336540.equals("e"))) && (input.equals("iJ")))) {
            cf = false;
            a691849188 = 10;
            OperatorTracker.output("oY");
        }
        if (((OperatorTracker.myOperator("==", a691849188, 8, 48)) && ((a547336540.equals("e")) && ((input.equals("iA")) && cf)))) {
            cf = false;
            a691849188 = 10;
            OperatorTracker.output("oY");
        }
    }

    private void calculateOutputm6(String input) {
        if (((OperatorTracker.myOperator("==", a691849188, 9, 49)) && ((cf && (input.equals("iC"))) && (a547336540.equals("e"))))) {
            cf = false;
            a691849188 = 11;
            OperatorTracker.output("oZ");
        }
        if (((a547336540.equals("e")) && ((cf && (OperatorTracker.myOperator("==", a691849188, 9, 50))) && (input.equals("iE"))))) {
            cf = false;
            a691849188 = 11;
            OperatorTracker.output("oZ");
        }
        if (((a547336540.equals("e")) && ((cf && (input.equals("iF"))) && (OperatorTracker.myOperator("==", a691849188, 9, 51))))) {
            cf = false;
            a691849188 = 11;
            OperatorTracker.output("oZ");
        }
        if ((((input.equals("iI")) && (cf && (a547336540.equals("e")))) && (OperatorTracker.myOperator("==", a691849188, 9, 52)))) {
            cf = false;
            a691849188 = 11;
            OperatorTracker.output("oZ");
        }
        if ((((cf && (a547336540.equals("e"))) && (OperatorTracker.myOperator("==", a691849188, 9, 53))) && (input.equals("iJ")))) {
            cf = false;
            a691849188 = 11;
            OperatorTracker.output("oZ");
        }
        if ((((cf && (a547336540.equals("e"))) && (input.equals("iA"))) && (OperatorTracker.myOperator("==", a691849188, 9, 54)))) {
            cf = false;
            a691849188 = 11;
            OperatorTracker.output("oZ");
        }
        if (((OperatorTracker.myOperator("==", a691849188, 9, 55)) && ((a547336540.equals("e")) && (cf && (input.equals("iH")))))) {
            cf = false;
            a691849188 = 10;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iG")) && (((a547336540.equals("e")) && cf) && (OperatorTracker.myOperator("==", a691849188, 9, 56))))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 13;
            OperatorTracker.output("oU");
        }
        if ((((a547336540.equals("e")) && (cf && (input.equals("iD")))) && (OperatorTracker.myOperator("==", a691849188, 9, 57)))) {
            cf = false;
            a547336540 = "h";
            a1855872761 = "g";
            OperatorTracker.output("oU");
        }
    }

    private void calculateOutputm7(String input) {
        if ((((OperatorTracker.myOperator("==", a691849188, 10, 58)) && (cf && (input.equals("iG")))) && (a547336540.equals("e")))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 13;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("e")) && ((OperatorTracker.myOperator("==", a691849188, 10, 59)) && (cf && (input.equals("iD")))))) {
            cf = false;
            a547336540 = "h";
            a1855872761 = "g";
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a691849188, 10, 60)) && ((a547336540.equals("e")) && (cf && (input.equals("iC")))))) {
            cf = false;
            OperatorTracker.output("oZ");
        }
        if (((a547336540.equals("e")) && ((cf && (input.equals("iE"))) && (OperatorTracker.myOperator("==", a691849188, 10, 61))))) {
            cf = false;
            OperatorTracker.output("oZ");
        }
        if (((a547336540.equals("e")) && ((cf && (OperatorTracker.myOperator("==", a691849188, 10, 62))) && (input.equals("iF"))))) {
            cf = false;
            OperatorTracker.output("oZ");
        }
        if ((((cf && (OperatorTracker.myOperator("==", a691849188, 10, 63))) && (input.equals("iH"))) && (a547336540.equals("e")))) {
            cf = false;
            OperatorTracker.output("oZ");
        }
        if (((input.equals("iI")) && ((a547336540.equals("e")) && ((OperatorTracker.myOperator("==", a691849188, 10, 64)) && cf)))) {
            cf = false;
            OperatorTracker.output("oZ");
        }
        if ((((input.equals("iJ")) && ((a547336540.equals("e")) && cf)) && (OperatorTracker.myOperator("==", a691849188, 10, 65)))) {
            cf = false;
            OperatorTracker.output("oZ");
        }
        if (((((a547336540.equals("e")) && cf) && (input.equals("iA"))) && (OperatorTracker.myOperator("==", a691849188, 10, 66)))) {
            cf = false;
            OperatorTracker.output("oZ");
        }
    }

    private void calculateOutputm8(String input) {
        if (((a547336540.equals("e")) && ((OperatorTracker.myOperator("==", a691849188, 11, 67)) && (cf && (input.equals("iG")))))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 13;
            OperatorTracker.output("oU");
        }
        if ((((a547336540.equals("e")) && ((OperatorTracker.myOperator("==", a691849188, 11, 68)) && cf)) && (input.equals("iC")))) {
            cf = false;
            a691849188 = 10;
            OperatorTracker.output("oZ");
        }
        if ((((cf && (input.equals("iE"))) && (a547336540.equals("e"))) && (OperatorTracker.myOperator("==", a691849188, 11, 69)))) {
            cf = false;
            a691849188 = 10;
            OperatorTracker.output("oZ");
        }
        if ((((a547336540.equals("e")) && ((OperatorTracker.myOperator("==", a691849188, 11, 70)) && cf)) && (input.equals("iF")))) {
            cf = false;
            a691849188 = 10;
            OperatorTracker.output("oZ");
        }
        if (((a547336540.equals("e")) && ((input.equals("iH")) && (cf && (OperatorTracker.myOperator("==", a691849188, 11, 71)))))) {
            cf = false;
            a691849188 = 10;
            OperatorTracker.output("oZ");
        }
        if (((((OperatorTracker.myOperator("==", a691849188, 11, 72)) && cf) && (input.equals("iI"))) && (a547336540.equals("e")))) {
            cf = false;
            a691849188 = 10;
            OperatorTracker.output("oZ");
        }
        if ((((input.equals("iJ")) && ((a547336540.equals("e")) && cf)) && (OperatorTracker.myOperator("==", a691849188, 11, 73)))) {
            cf = false;
            a691849188 = 10;
            OperatorTracker.output("oZ");
        }
        if (((OperatorTracker.myOperator("==", a691849188, 11, 74)) && (((input.equals("iA")) && cf) && (a547336540.equals("e"))))) {
            cf = false;
            a691849188 = 10;
            OperatorTracker.output("oZ");
        }
    }

    private void calculateOutputm9(String input) {
        if ((((OperatorTracker.myOperator("==", a1305805768, 9, 75)) && ((input.equals("iH")) && cf)) && (a547336540.equals("f")))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 13;
            OperatorTracker.output("oU");
        }
        if (((((input.equals("iC")) && cf) && (OperatorTracker.myOperator("==", a1305805768, 9, 76))) && (a547336540.equals("f")))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 14;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iD")) && (((OperatorTracker.myOperator("==", a1305805768, 9, 77)) && cf) && (a547336540.equals("f"))))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 14;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("f")) && (((input.equals("iE")) && cf) && (OperatorTracker.myOperator("==", a1305805768, 9, 78))))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 14;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a1305805768, 9, 79)) && ((a547336540.equals("f")) && (cf && (input.equals("iF")))))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 14;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("f")) && ((OperatorTracker.myOperator("==", a1305805768, 9, 80)) && ((input.equals("iI")) && cf)))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 14;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iJ")) && (((a547336540.equals("f")) && cf) && (OperatorTracker.myOperator("==", a1305805768, 9, 81))))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 14;
            OperatorTracker.output("oU");
        }
        if ((((OperatorTracker.myOperator("==", a1305805768, 9, 82)) && (cf && (input.equals("iA")))) && (a547336540.equals("f")))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 14;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a1305805768, 9, 83)) && (((input.equals("iB")) && cf) && (a547336540.equals("f"))))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 5;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a1305805768, 9, 84)) && (((input.equals("iG")) && cf) && (a547336540.equals("f"))))) {
            cf = false;
            a1305805768 = 13;
            OperatorTracker.output("oX");
        }
    }

    private void calculateOutputm10(String input) {
        if ((((cf && (OperatorTracker.myOperator("==", a1305805768, 10, 85))) && (a547336540.equals("f"))) && (input.equals("iC")))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("f")) && ((input.equals("iD")) && ((OperatorTracker.myOperator("==", a1305805768, 10, 86)) && cf)))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if ((((input.equals("iE")) && (cf && (a547336540.equals("f")))) && (OperatorTracker.myOperator("==", a1305805768, 10, 87)))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("f")) && (((input.equals("iF")) && cf) && (OperatorTracker.myOperator("==", a1305805768, 10, 88))))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if ((((cf && (a547336540.equals("f"))) && (input.equals("iI"))) && (OperatorTracker.myOperator("==", a1305805768, 10, 89)))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("f")) && (((OperatorTracker.myOperator("==", a1305805768, 10, 90)) && cf) && (input.equals("iJ"))))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((((OperatorTracker.myOperator("==", a1305805768, 10, 91)) && cf) && (a547336540.equals("f"))) && (input.equals("iA")))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("f")) && ((cf && (input.equals("iB"))) && (OperatorTracker.myOperator("==", a1305805768, 10, 92))))) {
            cf = false;
            a1305805768 = 14;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a1305805768, 10, 93)) && (((input.equals("iH")) && cf) && (a547336540.equals("f"))))) {
            cf = false;
            a1305805768 = 14;
            OperatorTracker.output("oU");
        }
    }

    private void calculateOutputm11(String input) {
        if (((OperatorTracker.myOperator("==", a1305805768, 11, 94)) && ((input.equals("iD")) && ((a547336540.equals("f")) && cf)))) {
            cf = false;
            a1305805768 = 10;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("f")) && ((cf && (OperatorTracker.myOperator("==", a1305805768, 11, 95))) && (input.equals("iB"))))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 13;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a1305805768, 11, 96)) && ((input.equals("iH")) && (cf && (a547336540.equals("f")))))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 13;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("f")) && ((OperatorTracker.myOperator("==", a1305805768, 11, 97)) && ((input.equals("iG")) && cf)))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 14;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iC")) && ((a547336540.equals("f")) && ((OperatorTracker.myOperator("==", a1305805768, 11, 98)) && cf)))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 10;
            OperatorTracker.output("oX");
        }
        if (((((input.equals("iE")) && cf) && (a547336540.equals("f"))) && (OperatorTracker.myOperator("==", a1305805768, 11, 99)))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 10;
            OperatorTracker.output("oX");
        }
        if (((OperatorTracker.myOperator("==", a1305805768, 11, 100)) && ((a547336540.equals("f")) && ((input.equals("iF")) && cf)))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 10;
            OperatorTracker.output("oX");
        }
        if (((OperatorTracker.myOperator("==", a1305805768, 11, 101)) && ((cf && (input.equals("iI"))) && (a547336540.equals("f"))))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 10;
            OperatorTracker.output("oX");
        }
        if (((a547336540.equals("f")) && ((cf && (input.equals("iJ"))) && (OperatorTracker.myOperator("==", a1305805768, 11, 102))))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 10;
            OperatorTracker.output("oX");
        }
        if (((OperatorTracker.myOperator("==", a1305805768, 11, 103)) && ((cf && (input.equals("iA"))) && (a547336540.equals("f"))))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 10;
            OperatorTracker.output("oX");
        }
    }

    private void calculateOutputm12(String input) {
        if (((((input.equals("iB")) && cf) && (OperatorTracker.myOperator("==", a1305805768, 12, 104))) && (a547336540.equals("f")))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 15;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iC")) && ((OperatorTracker.myOperator("==", a1305805768, 12, 105)) && (cf && (a547336540.equals("f")))))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 15;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iE")) && ((cf && (OperatorTracker.myOperator("==", a1305805768, 12, 106))) && (a547336540.equals("f"))))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 15;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iF")) && ((cf && (OperatorTracker.myOperator("==", a1305805768, 12, 107))) && (a547336540.equals("f"))))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 15;
            OperatorTracker.output("oU");
        }
        if ((((OperatorTracker.myOperator("==", a1305805768, 12, 108)) && (cf && (a547336540.equals("f")))) && (input.equals("iG")))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 15;
            OperatorTracker.output("oU");
        }
        if ((((cf && (input.equals("iI"))) && (a547336540.equals("f"))) && (OperatorTracker.myOperator("==", a1305805768, 12, 109)))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 15;
            OperatorTracker.output("oU");
        }
        if ((((cf && (input.equals("iJ"))) && (a547336540.equals("f"))) && (OperatorTracker.myOperator("==", a1305805768, 12, 110)))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 15;
            OperatorTracker.output("oU");
        }
        if ((((input.equals("iA")) && ((OperatorTracker.myOperator("==", a1305805768, 12, 111)) && cf)) && (a547336540.equals("f")))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 15;
            OperatorTracker.output("oU");
        }
        if ((((OperatorTracker.myOperator("==", a1305805768, 12, 112)) && (cf && (a547336540.equals("f")))) && (input.equals("iH")))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 7;
            OperatorTracker.output("oU");
        }
        if (((((OperatorTracker.myOperator("==", a1305805768, 12, 113)) && cf) && (a547336540.equals("f"))) && (input.equals("iD")))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 15;
            OperatorTracker.output("oU");
        }
    }

    private void calculateOutputm13(String input) {
        if ((((cf && (a547336540.equals("f"))) && (input.equals("iC"))) && (OperatorTracker.myOperator("==", a1305805768, 13, 114)))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((((a547336540.equals("f")) && cf) && (input.equals("iD"))) && (OperatorTracker.myOperator("==", a1305805768, 13, 115)))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if ((((a547336540.equals("f")) && ((OperatorTracker.myOperator("==", a1305805768, 13, 116)) && cf)) && (input.equals("iE")))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a1305805768, 13, 117)) && ((input.equals("iF")) && ((a547336540.equals("f")) && cf)))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if ((((a547336540.equals("f")) && ((OperatorTracker.myOperator("==", a1305805768, 13, 118)) && cf)) && (input.equals("iG")))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((((a547336540.equals("f")) && cf) && (OperatorTracker.myOperator("==", a1305805768, 13, 119))) && (input.equals("iI")))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if ((((cf && (OperatorTracker.myOperator("==", a1305805768, 13, 120))) && (input.equals("iJ"))) && (a547336540.equals("f")))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if ((((input.equals("iA")) && ((a547336540.equals("f")) && cf)) && (OperatorTracker.myOperator("==", a1305805768, 13, 121)))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a1305805768, 13, 122)) && ((cf && (a547336540.equals("f"))) && (input.equals("iH"))))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 10;
            OperatorTracker.output("oZ");
        }
    }

    private void calculateOutputm14(String input) {
        if (((a547336540.equals("f")) && ((OperatorTracker.myOperator("==", a1305805768, 14, 123)) && ((input.equals("iB")) && cf)))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a1305805768, 14, 124)) && (((input.equals("iC")) && cf) && (a547336540.equals("f"))))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((((input.equals("iD")) && cf) && (a547336540.equals("f"))) && (OperatorTracker.myOperator("==", a1305805768, 14, 125)))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((((a547336540.equals("f")) && cf) && (input.equals("iE"))) && (OperatorTracker.myOperator("==", a1305805768, 14, 126)))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((((OperatorTracker.myOperator("==", a1305805768, 14, 127)) && cf) && (input.equals("iF"))) && (a547336540.equals("f")))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((((input.equals("iH")) && cf) && (OperatorTracker.myOperator("==", a1305805768, 14, 128))) && (a547336540.equals("f")))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if ((((input.equals("iI")) && ((a547336540.equals("f")) && cf)) && (OperatorTracker.myOperator("==", a1305805768, 14, 129)))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iJ")) && ((cf && (OperatorTracker.myOperator("==", a1305805768, 14, 130))) && (a547336540.equals("f"))))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iA")) && ((OperatorTracker.myOperator("==", a1305805768, 14, 131)) && (cf && (a547336540.equals("f")))))) {
            cf = false;
            OperatorTracker.output("oU");
        }
    }

    private void calculateOutputm15(String input) {
        if (((((input.equals("iH")) && cf) && (OperatorTracker.myOperator("==", a1305805768, 15, 132))) && (a547336540.equals("f")))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 7;
            OperatorTracker.output("oV");
        }
        if (((OperatorTracker.myOperator("==", a1305805768, 15, 133)) && (((input.equals("iD")) && cf) && (a547336540.equals("f"))))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 11;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a1305805768, 15, 134)) && ((input.equals("iJ")) && (cf && (a547336540.equals("f")))))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 4;
            OperatorTracker.output("oW");
        }
        if (((OperatorTracker.myOperator("==", a1305805768, 15, 135)) && ((cf && (a547336540.equals("f"))) && (input.equals("iC"))))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 11;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("f")) && ((cf && (OperatorTracker.myOperator("==", a1305805768, 15, 136))) && (input.equals("iE"))))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 11;
            OperatorTracker.output("oU");
        }
        if ((((a547336540.equals("f")) && (cf && (input.equals("iF")))) && (OperatorTracker.myOperator("==", a1305805768, 15, 137)))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 11;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("f")) && ((OperatorTracker.myOperator("==", a1305805768, 15, 138)) && ((input.equals("iG")) && cf)))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 11;
            OperatorTracker.output("oU");
        }
        if ((((OperatorTracker.myOperator("==", a1305805768, 15, 139)) && ((input.equals("iI")) && cf)) && (a547336540.equals("f")))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 11;
            OperatorTracker.output("oU");
        }
        if (((((input.equals("iA")) && cf) && (a547336540.equals("f"))) && (OperatorTracker.myOperator("==", a1305805768, 15, 140)))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 11;
            OperatorTracker.output("oU");
        }
        if ((((cf && (OperatorTracker.myOperator("==", a1305805768, 15, 141))) && (a547336540.equals("f"))) && (input.equals("iB")))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 15;
            OperatorTracker.output("oU");
        }
    }

    private void calculateOutputm16(String input) {
        if ((((OperatorTracker.myOperator("==", a1305805768, 16, 142)) && (cf && (a547336540.equals("f")))) && (input.equals("iH")))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 13;
            OperatorTracker.output("oU");
        }
        if ((((OperatorTracker.myOperator("==", a1305805768, 16, 143)) && (cf && (input.equals("iC")))) && (a547336540.equals("f")))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 14;
            OperatorTracker.output("oU");
        }
        if ((((cf && (a547336540.equals("f"))) && (input.equals("iE"))) && (OperatorTracker.myOperator("==", a1305805768, 16, 144)))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 14;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a1305805768, 16, 145)) && (((input.equals("iF")) && cf) && (a547336540.equals("f"))))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 14;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a1305805768, 16, 146)) && (((input.equals("iG")) && cf) && (a547336540.equals("f"))))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 14;
            OperatorTracker.output("oU");
        }
        if ((((cf && (OperatorTracker.myOperator("==", a1305805768, 16, 147))) && (input.equals("iI"))) && (a547336540.equals("f")))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 14;
            OperatorTracker.output("oU");
        }
        if ((((input.equals("iJ")) && ((OperatorTracker.myOperator("==", a1305805768, 16, 148)) && cf)) && (a547336540.equals("f")))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 14;
            OperatorTracker.output("oU");
        }
        if (((((input.equals("iA")) && cf) && (OperatorTracker.myOperator("==", a1305805768, 16, 149))) && (a547336540.equals("f")))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 14;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("f")) && ((input.equals("iB")) && (cf && (OperatorTracker.myOperator("==", a1305805768, 16, 150)))))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 5;
            OperatorTracker.output("oY");
        }
    }

    private void calculateOutputm17(String input) {
        if (((input.equals("iC")) && (((OperatorTracker.myOperator("==", a612577343, 4, 151)) && cf) && (a547336540.equals("g"))))) {
            cf = false;
            a612577343 = 5;
            OperatorTracker.output("oU");
        }
        if ((((input.equals("iD")) && (cf && (OperatorTracker.myOperator("==", a612577343, 4, 152)))) && (a547336540.equals("g")))) {
            cf = false;
            a612577343 = 5;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iE")) && ((a547336540.equals("g")) && (cf && (OperatorTracker.myOperator("==", a612577343, 4, 153)))))) {
            cf = false;
            a612577343 = 5;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("g")) && ((input.equals("iF")) && (cf && (OperatorTracker.myOperator("==", a612577343, 4, 154)))))) {
            cf = false;
            a612577343 = 5;
            OperatorTracker.output("oU");
        }
        if (((((OperatorTracker.myOperator("==", a612577343, 4, 155)) && cf) && (input.equals("iG"))) && (a547336540.equals("g")))) {
            cf = false;
            a612577343 = 5;
            OperatorTracker.output("oU");
        }
        if ((((cf && (OperatorTracker.myOperator("==", a612577343, 4, 156))) && (a547336540.equals("g"))) && (input.equals("iI")))) {
            cf = false;
            a612577343 = 5;
            OperatorTracker.output("oU");
        }
        if ((((input.equals("iJ")) && (cf && (a547336540.equals("g")))) && (OperatorTracker.myOperator("==", a612577343, 4, 157)))) {
            cf = false;
            a612577343 = 5;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iB")) && ((OperatorTracker.myOperator("==", a612577343, 4, 158)) && (cf && (a547336540.equals("g")))))) {
            cf = false;
            a612577343 = 9;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a612577343, 4, 159)) && ((cf && (input.equals("iH"))) && (a547336540.equals("g"))))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 8;
            OperatorTracker.output("oX");
        }
        if (((OperatorTracker.myOperator("==", a612577343, 4, 160)) && ((input.equals("iA")) && ((a547336540.equals("g")) && cf)))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 9;
            OperatorTracker.output("oY");
        }
    }

    private void calculateOutputm18(String input) {
        if (((input.equals("iH")) && ((a547336540.equals("g")) && ((OperatorTracker.myOperator("==", a612577343, 5, 161)) && cf)))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 13;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a612577343, 5, 162)) && (((input.equals("iC")) && cf) && (a547336540.equals("g"))))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 14;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iD")) && ((a547336540.equals("g")) && ((OperatorTracker.myOperator("==", a612577343, 5, 163)) && cf)))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 14;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("g")) && ((OperatorTracker.myOperator("==", a612577343, 5, 164)) && ((input.equals("iE")) && cf)))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 14;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a612577343, 5, 165)) && (((input.equals("iF")) && cf) && (a547336540.equals("g"))))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 14;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iG")) && ((OperatorTracker.myOperator("==", a612577343, 5, 166)) && (cf && (a547336540.equals("g")))))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 14;
            OperatorTracker.output("oU");
        }
        if ((((cf && (a547336540.equals("g"))) && (OperatorTracker.myOperator("==", a612577343, 5, 167))) && (input.equals("iI")))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 14;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iJ")) && ((cf && (a547336540.equals("g"))) && (OperatorTracker.myOperator("==", a612577343, 5, 168))))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 14;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("g")) && ((input.equals("iA")) && ((OperatorTracker.myOperator("==", a612577343, 5, 169)) && cf)))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 14;
            OperatorTracker.output("oU");
        }
        if ((((OperatorTracker.myOperator("==", a612577343, 5, 170)) && (cf && (a547336540.equals("g")))) && (input.equals("iB")))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 5;
            OperatorTracker.output("oU");
        }
    }

    private void calculateOutputm19(String input) {
        if (((input.equals("iB")) && ((a547336540.equals("g")) && ((OperatorTracker.myOperator("==", a612577343, 6, 171)) && cf)))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 15;
            OperatorTracker.output("oU");
        }
        if (((((input.equals("iC")) && cf) && (OperatorTracker.myOperator("==", a612577343, 6, 172))) && (a547336540.equals("g")))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 15;
            OperatorTracker.output("oU");
        }
        if ((((a547336540.equals("g")) && (cf && (input.equals("iD")))) && (OperatorTracker.myOperator("==", a612577343, 6, 173)))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 15;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iE")) && ((OperatorTracker.myOperator("==", a612577343, 6, 174)) && ((a547336540.equals("g")) && cf)))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 15;
            OperatorTracker.output("oU");
        }
        if ((((cf && (input.equals("iF"))) && (OperatorTracker.myOperator("==", a612577343, 6, 175))) && (a547336540.equals("g")))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 15;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("g")) && ((input.equals("iG")) && ((OperatorTracker.myOperator("==", a612577343, 6, 176)) && cf)))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 15;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a612577343, 6, 177)) && ((a547336540.equals("g")) && (cf && (input.equals("iI")))))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 15;
            OperatorTracker.output("oU");
        }
        if ((((OperatorTracker.myOperator("==", a612577343, 6, 178)) && (cf && (input.equals("iJ")))) && (a547336540.equals("g")))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 15;
            OperatorTracker.output("oU");
        }
        if ((((a547336540.equals("g")) && (cf && (OperatorTracker.myOperator("==", a612577343, 6, 179)))) && (input.equals("iA")))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 15;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a612577343, 6, 180)) && (((a547336540.equals("g")) && cf) && (input.equals("iH"))))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 7;
            OperatorTracker.output("oU");
        }
    }

    private void calculateOutputm20(String input) {
        if ((((a547336540.equals("g")) && (cf && (input.equals("iJ")))) && (OperatorTracker.myOperator("==", a612577343, 7, 181)))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 9;
            OperatorTracker.output("oX");
        }
        if ((((OperatorTracker.myOperator("==", a612577343, 7, 182)) && ((input.equals("iC")) && cf)) && (a547336540.equals("g")))) {
            cf = false;
            a612577343 = 10;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iE")) && (((a547336540.equals("g")) && cf) && (OperatorTracker.myOperator("==", a612577343, 7, 183))))) {
            cf = false;
            a612577343 = 10;
            OperatorTracker.output("oU");
        }
        if ((((cf && (a547336540.equals("g"))) && (input.equals("iF"))) && (OperatorTracker.myOperator("==", a612577343, 7, 184)))) {
            cf = false;
            a612577343 = 10;
            OperatorTracker.output("oU");
        }
        if ((((a547336540.equals("g")) && (cf && (input.equals("iI")))) && (OperatorTracker.myOperator("==", a612577343, 7, 185)))) {
            cf = false;
            a612577343 = 10;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iB")) && ((a547336540.equals("g")) && (cf && (OperatorTracker.myOperator("==", a612577343, 7, 186)))))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 6;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("g")) && ((OperatorTracker.myOperator("==", a612577343, 7, 187)) && ((input.equals("iH")) && cf)))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 6;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iA")) && ((a547336540.equals("g")) && ((OperatorTracker.myOperator("==", a612577343, 7, 188)) && cf)))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 11;
            OperatorTracker.output("oW");
        }
        if ((((OperatorTracker.myOperator("==", a612577343, 7, 189)) && ((a547336540.equals("g")) && cf)) && (input.equals("iG")))) {
            cf = false;
            a547336540 = "h";
            a1855872761 = "h";
            OperatorTracker.output("oZ");
        }
        if (((input.equals("iD")) && (((OperatorTracker.myOperator("==", a612577343, 7, 190)) && cf) && (a547336540.equals("g"))))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 10;
            OperatorTracker.output("oU");
        }
    }

    private void calculateOutputm21(String input) {
        if ((((a547336540.equals("g")) && (cf && (input.equals("iE")))) && (OperatorTracker.myOperator("==", a612577343, 8, 191)))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 15;
            OperatorTracker.output("oV");
        }
        if ((((input.equals("iH")) && ((OperatorTracker.myOperator("==", a612577343, 8, 192)) && cf)) && (a547336540.equals("g")))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 4;
            OperatorTracker.output("oY");
        }
        if (((input.equals("iD")) && (((OperatorTracker.myOperator("==", a612577343, 8, 193)) && cf) && (a547336540.equals("g"))))) {
            cf = false;
            a612577343 = 6;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a612577343, 8, 194)) && ((a547336540.equals("g")) && (cf && (input.equals("iC")))))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 12;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("g")) && ((input.equals("iF")) && (cf && (OperatorTracker.myOperator("==", a612577343, 8, 195)))))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 12;
            OperatorTracker.output("oU");
        }
        if ((((input.equals("iG")) && (cf && (a547336540.equals("g")))) && (OperatorTracker.myOperator("==", a612577343, 8, 196)))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 12;
            OperatorTracker.output("oU");
        }
        if ((((a547336540.equals("g")) && ((OperatorTracker.myOperator("==", a612577343, 8, 197)) && cf)) && (input.equals("iJ")))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 12;
            OperatorTracker.output("oU");
        }
        if ((((cf && (a547336540.equals("g"))) && (input.equals("iA"))) && (OperatorTracker.myOperator("==", a612577343, 8, 198)))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 12;
            OperatorTracker.output("oU");
        }
        if ((((cf && (input.equals("iB"))) && (OperatorTracker.myOperator("==", a612577343, 8, 199))) && (a547336540.equals("g")))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 12;
            OperatorTracker.output("oU");
        }
        if (((((OperatorTracker.myOperator("==", a612577343, 8, 200)) && cf) && (a547336540.equals("g"))) && (input.equals("iI")))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 12;
            OperatorTracker.output("oU");
        }
    }

    private void calculateOutputm22(String input) {
        if (((OperatorTracker.myOperator("==", a612577343, 9, 201)) && (((input.equals("iH")) && cf) && (a547336540.equals("g"))))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 13;
            OperatorTracker.output("oU");
        }
        if ((((cf && (OperatorTracker.myOperator("==", a612577343, 9, 202))) && (a547336540.equals("g"))) && (input.equals("iB")))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 5;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a612577343, 9, 203)) && ((input.equals("iC")) && (cf && (a547336540.equals("g")))))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 5;
            OperatorTracker.output("oU");
        }
        if ((((input.equals("iD")) && (cf && (OperatorTracker.myOperator("==", a612577343, 9, 204)))) && (a547336540.equals("g")))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 5;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iE")) && ((a547336540.equals("g")) && ((OperatorTracker.myOperator("==", a612577343, 9, 205)) && cf)))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 5;
            OperatorTracker.output("oU");
        }
        if ((((a547336540.equals("g")) && (cf && (OperatorTracker.myOperator("==", a612577343, 9, 206)))) && (input.equals("iF")))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 5;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iG")) && (((a547336540.equals("g")) && cf) && (OperatorTracker.myOperator("==", a612577343, 9, 207))))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 5;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("g")) && ((input.equals("iI")) && (cf && (OperatorTracker.myOperator("==", a612577343, 9, 208)))))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 5;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a612577343, 9, 209)) && ((input.equals("iJ")) && (cf && (a547336540.equals("g")))))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 5;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iA")) && ((cf && (a547336540.equals("g"))) && (OperatorTracker.myOperator("==", a612577343, 9, 210))))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 5;
            OperatorTracker.output("oU");
        }
    }

    private void calculateOutputm23(String input) {
        if ((((cf && (input.equals("iC"))) && (OperatorTracker.myOperator("==", a612577343, 10, 211))) && (a547336540.equals("g")))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a612577343, 10, 212)) && ((input.equals("iE")) && ((a547336540.equals("g")) && cf)))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a612577343, 10, 213)) && (((a547336540.equals("g")) && cf) && (input.equals("iF"))))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if ((((cf && (OperatorTracker.myOperator("==", a612577343, 10, 214))) && (input.equals("iI"))) && (a547336540.equals("g")))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iJ")) && (((OperatorTracker.myOperator("==", a612577343, 10, 215)) && cf) && (a547336540.equals("g"))))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if ((((input.equals("iA")) && ((a547336540.equals("g")) && cf)) && (OperatorTracker.myOperator("==", a612577343, 10, 216)))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if ((((cf && (input.equals("iD"))) && (a547336540.equals("g"))) && (OperatorTracker.myOperator("==", a612577343, 10, 217)))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 10;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iB")) && (((a547336540.equals("g")) && cf) && (OperatorTracker.myOperator("==", a612577343, 10, 218))))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 13;
            OperatorTracker.output("oU");
        }
        if ((((input.equals("iH")) && (cf && (OperatorTracker.myOperator("==", a612577343, 10, 219)))) && (a547336540.equals("g")))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 13;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a612577343, 10, 220)) && ((input.equals("iG")) && (cf && (a547336540.equals("g")))))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 14;
            OperatorTracker.output("oU");
        }
    }

    private void calculateOutputm24(String input) {
        if ((((OperatorTracker.myOperator("==", a612577343, 11, 221)) && ((input.equals("iH")) && cf)) && (a547336540.equals("g")))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 6;
            OperatorTracker.output("oU");
        }
        if ((((a547336540.equals("g")) && ((input.equals("iC")) && cf)) && (OperatorTracker.myOperator("==", a612577343, 11, 222)))) {
            cf = false;
            a612577343 = 5;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("g")) && ((OperatorTracker.myOperator("==", a612577343, 11, 223)) && ((input.equals("iD")) && cf)))) {
            cf = false;
            a612577343 = 5;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iE")) && (((a547336540.equals("g")) && cf) && (OperatorTracker.myOperator("==", a612577343, 11, 224))))) {
            cf = false;
            a612577343 = 5;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("g")) && (((input.equals("iF")) && cf) && (OperatorTracker.myOperator("==", a612577343, 11, 225))))) {
            cf = false;
            a612577343 = 5;
            OperatorTracker.output("oU");
        }
        if ((((input.equals("iG")) && (cf && (a547336540.equals("g")))) && (OperatorTracker.myOperator("==", a612577343, 11, 226)))) {
            cf = false;
            a612577343 = 5;
            OperatorTracker.output("oU");
        }
        if ((((cf && (a547336540.equals("g"))) && (input.equals("iI"))) && (OperatorTracker.myOperator("==", a612577343, 11, 227)))) {
            cf = false;
            a612577343 = 5;
            OperatorTracker.output("oU");
        }
        if ((((cf && (a547336540.equals("g"))) && (input.equals("iJ"))) && (OperatorTracker.myOperator("==", a612577343, 11, 228)))) {
            cf = false;
            a612577343 = 5;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("g")) && ((input.equals("iA")) && (cf && (OperatorTracker.myOperator("==", a612577343, 11, 229)))))) {
            cf = false;
            a612577343 = 5;
            OperatorTracker.output("oU");
        }
        if ((((a547336540.equals("g")) && (cf && (input.equals("iB")))) && (OperatorTracker.myOperator("==", a612577343, 11, 230)))) {
            cf = false;
            a612577343 = 9;
            OperatorTracker.output("oU");
        }
    }

    private void calculateOutputm25(String input) {
        if ((((cf && (a547336540.equals("h"))) && (input.equals("iE"))) && (a1855872761.equals("e")))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 12;
            OperatorTracker.output("oV");
        }
        if ((((cf && (a547336540.equals("h"))) && (a1855872761.equals("e"))) && (input.equals("iG")))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 9;
            OperatorTracker.output("oV");
        }
        if (((input.equals("iB")) && ((a1855872761.equals("e")) && ((a547336540.equals("h")) && cf)))) {
            cf = false;
            a1855872761 = "f";
            OperatorTracker.output("oV");
        }
        if (((a547336540.equals("h")) && (((a1855872761.equals("e")) && cf) && (input.equals("iC"))))) {
            cf = false;
            a1855872761 = "f";
            OperatorTracker.output("oV");
        }
        if (((input.equals("iF")) && ((a1855872761.equals("e")) && (cf && (a547336540.equals("h")))))) {
            cf = false;
            a1855872761 = "f";
            OperatorTracker.output("oV");
        }
        if ((((cf && (input.equals("iH"))) && (a1855872761.equals("e"))) && (a547336540.equals("h")))) {
            cf = false;
            a1855872761 = "f";
            OperatorTracker.output("oV");
        }
        if (((input.equals("iI")) && ((a547336540.equals("h")) && ((a1855872761.equals("e")) && cf)))) {
            cf = false;
            a1855872761 = "f";
            OperatorTracker.output("oV");
        }
        if ((((a1855872761.equals("e")) && (cf && (input.equals("iJ")))) && (a547336540.equals("h")))) {
            cf = false;
            a1855872761 = "f";
            OperatorTracker.output("oV");
        }
        if (((input.equals("iA")) && ((a1855872761.equals("e")) && ((a547336540.equals("h")) && cf)))) {
            cf = false;
            a1855872761 = "f";
            OperatorTracker.output("oV");
        }
    }

    private void calculateOutputm26(String input) {
        if ((((cf && (input.equals("iB"))) && (a547336540.equals("h"))) && (a1855872761.equals("f")))) {
            cf = false;
            a1855872761 = "i";
            OperatorTracker.output("oV");
        }
        if (((input.equals("iC")) && (((a547336540.equals("h")) && cf) && (a1855872761.equals("f"))))) {
            cf = false;
            a1855872761 = "i";
            OperatorTracker.output("oV");
        }
        if (((((a547336540.equals("h")) && cf) && (a1855872761.equals("f"))) && (input.equals("iE")))) {
            cf = false;
            a1855872761 = "i";
            OperatorTracker.output("oV");
        }
        if (((a1855872761.equals("f")) && ((cf && (input.equals("iF"))) && (a547336540.equals("h"))))) {
            cf = false;
            a1855872761 = "i";
            OperatorTracker.output("oV");
        }
        if (((a1855872761.equals("f")) && ((cf && (a547336540.equals("h"))) && (input.equals("iH"))))) {
            cf = false;
            a1855872761 = "i";
            OperatorTracker.output("oV");
        }
        if (((a547336540.equals("h")) && ((cf && (a1855872761.equals("f"))) && (input.equals("iI"))))) {
            cf = false;
            a1855872761 = "i";
            OperatorTracker.output("oV");
        }
        if ((((cf && (a1855872761.equals("f"))) && (input.equals("iJ"))) && (a547336540.equals("h")))) {
            cf = false;
            a1855872761 = "i";
            OperatorTracker.output("oV");
        }
        if (((((a1855872761.equals("f")) && cf) && (input.equals("iA"))) && (a547336540.equals("h")))) {
            cf = false;
            a1855872761 = "i";
            OperatorTracker.output("oV");
        }
        if (((a547336540.equals("h")) && (((input.equals("iG")) && cf) && (a1855872761.equals("f"))))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 10;
            OperatorTracker.output("oX");
        }
    }

    private void calculateOutputm27(String input) {
        if ((((cf && (input.equals("iC"))) && (a1855872761.equals("g"))) && (a547336540.equals("h")))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("h")) && ((cf && (a1855872761.equals("g"))) && (input.equals("iD"))))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if ((((a1855872761.equals("g")) && ((input.equals("iE")) && cf)) && (a547336540.equals("h")))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if ((((cf && (input.equals("iF"))) && (a1855872761.equals("g"))) && (a547336540.equals("h")))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("h")) && ((cf && (a1855872761.equals("g"))) && (input.equals("iH"))))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if ((((cf && (a547336540.equals("h"))) && (a1855872761.equals("g"))) && (input.equals("iI")))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if ((((a1855872761.equals("g")) && ((input.equals("iJ")) && cf)) && (a547336540.equals("h")))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iA")) && ((a1855872761.equals("g")) && (cf && (a547336540.equals("h")))))) {
            cf = false;
            OperatorTracker.output("oU");
        }
    }

    private void calculateOutputm28(String input) {
        if (((input.equals("iC")) && ((a547336540.equals("h")) && ((a1855872761.equals("h")) && cf)))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 13;
            OperatorTracker.output("oX");
        }
        if ((((cf && (a1855872761.equals("h"))) && (a547336540.equals("h"))) && (input.equals("iD")))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 13;
            OperatorTracker.output("oX");
        }
        if (((input.equals("iF")) && ((cf && (a547336540.equals("h"))) && (a1855872761.equals("h"))))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 13;
            OperatorTracker.output("oX");
        }
        if ((((a547336540.equals("h")) && (cf && (a1855872761.equals("h")))) && (input.equals("iG")))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 13;
            OperatorTracker.output("oX");
        }
        if (((a1855872761.equals("h")) && ((input.equals("iJ")) && (cf && (a547336540.equals("h")))))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 13;
            OperatorTracker.output("oX");
        }
        if (((a1855872761.equals("h")) && ((a547336540.equals("h")) && (cf && (input.equals("iA")))))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 13;
            OperatorTracker.output("oX");
        }
        if ((((input.equals("iH")) && ((a1855872761.equals("h")) && cf)) && (a547336540.equals("h")))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 10;
            OperatorTracker.output("oV");
        }
        if (((a1855872761.equals("h")) && (((input.equals("iB")) && cf) && (a547336540.equals("h"))))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 14;
            OperatorTracker.output("oV");
        }
        if ((((a1855872761.equals("h")) && ((a547336540.equals("h")) && cf)) && (input.equals("iE")))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 14;
            OperatorTracker.output("oV");
        }
        if (((a1855872761.equals("h")) && ((a547336540.equals("h")) && (cf && (input.equals("iI")))))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 14;
            OperatorTracker.output("oV");
        }
    }

    private void calculateOutputm29(String input) {
        if (((input.equals("iC")) && ((a1855872761.equals("i")) && (cf && (a547336540.equals("h")))))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 10;
            OperatorTracker.output("oU");
        }
        if ((((input.equals("iE")) && ((a1855872761.equals("i")) && cf)) && (a547336540.equals("h")))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 10;
            OperatorTracker.output("oU");
        }
        if ((((a547336540.equals("h")) && (cf && (input.equals("iF")))) && (a1855872761.equals("i")))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 10;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iI")) && ((a547336540.equals("h")) && (cf && (a1855872761.equals("i")))))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 10;
            OperatorTracker.output("oU");
        }
        if (((a1855872761.equals("i")) && ((input.equals("iJ")) && ((a547336540.equals("h")) && cf)))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 10;
            OperatorTracker.output("oU");
        }
        if (((a1855872761.equals("i")) && ((a547336540.equals("h")) && ((input.equals("iA")) && cf)))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 10;
            OperatorTracker.output("oU");
        }
        if (((a1855872761.equals("i")) && ((input.equals("iB")) && (cf && (a547336540.equals("h")))))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 13;
            OperatorTracker.output("oU");
        }
        if ((((a547336540.equals("h")) && (cf && (input.equals("iH")))) && (a1855872761.equals("i")))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 13;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iG")) && (((a1855872761.equals("i")) && cf) && (a547336540.equals("h"))))) {
            cf = false;
            a547336540 = "i";
            a1122863037 = 14;
            OperatorTracker.output("oU");
        }
    }

    private void calculateOutputm30(String input) {
        if (((((OperatorTracker.myOperator("==", a1122863037, 8, 231)) && cf) && (input.equals("iB"))) && (a547336540.equals("i")))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 6;
            OperatorTracker.output("oU");
        }
        if ((((OperatorTracker.myOperator("==", a1122863037, 8, 232)) && (cf && (a547336540.equals("i")))) && (input.equals("iC")))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 6;
            OperatorTracker.output("oU");
        }
        if (((((input.equals("iE")) && cf) && (a547336540.equals("i"))) && (OperatorTracker.myOperator("==", a1122863037, 8, 233)))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 6;
            OperatorTracker.output("oU");
        }
        if (((((input.equals("iF")) && cf) && (OperatorTracker.myOperator("==", a1122863037, 8, 234))) && (a547336540.equals("i")))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 6;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a1122863037, 8, 235)) && ((cf && (a547336540.equals("i"))) && (input.equals("iH"))))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 6;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iI")) && ((a547336540.equals("i")) && ((OperatorTracker.myOperator("==", a1122863037, 8, 236)) && cf)))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 6;
            OperatorTracker.output("oU");
        }
        if (((((OperatorTracker.myOperator("==", a1122863037, 8, 237)) && cf) && (input.equals("iA"))) && (a547336540.equals("i")))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 6;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a1122863037, 8, 238)) && ((a547336540.equals("i")) && (cf && (input.equals("iG")))))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 9;
            OperatorTracker.output("oU");
        }
        if ((((cf && (OperatorTracker.myOperator("==", a1122863037, 8, 239))) && (input.equals("iJ"))) && (a547336540.equals("i")))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 11;
            OperatorTracker.output("oV");
        }
        if (((OperatorTracker.myOperator("==", a1122863037, 8, 240)) && (((a547336540.equals("i")) && cf) && (input.equals("iD"))))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 14;
            OperatorTracker.output("oU");
        }
    }

    private void calculateOutputm31(String input) {
        if (((OperatorTracker.myOperator("==", a1122863037, 9, 241)) && ((input.equals("iH")) && (cf && (a547336540.equals("i")))))) {
            cf = false;
            a547336540 = "h";
            a1855872761 = "i";
            OperatorTracker.output("oV");
        }
        if (((((a547336540.equals("i")) && cf) && (input.equals("iB"))) && (OperatorTracker.myOperator("==", a1122863037, 9, 242)))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 16;
            OperatorTracker.output("oV");
        }
        if (((a547336540.equals("i")) && (((input.equals("iE")) && cf) && (OperatorTracker.myOperator("==", a1122863037, 9, 243))))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 16;
            OperatorTracker.output("oV");
        }
        if (((input.equals("iC")) && (((OperatorTracker.myOperator("==", a1122863037, 9, 244)) && cf) && (a547336540.equals("i"))))) {
            cf = false;
            a1122863037 = 10;
            OperatorTracker.output("oX");
        }
        if ((((a547336540.equals("i")) && ((input.equals("iF")) && cf)) && (OperatorTracker.myOperator("==", a1122863037, 9, 245)))) {
            cf = false;
            a1122863037 = 10;
            OperatorTracker.output("oX");
        }
        if (((input.equals("iG")) && ((OperatorTracker.myOperator("==", a1122863037, 9, 246)) && ((a547336540.equals("i")) && cf)))) {
            cf = false;
            a1122863037 = 10;
            OperatorTracker.output("oX");
        }
        if (((input.equals("iI")) && (((a547336540.equals("i")) && cf) && (OperatorTracker.myOperator("==", a1122863037, 9, 247))))) {
            cf = false;
            a1122863037 = 10;
            OperatorTracker.output("oX");
        }
        if ((((OperatorTracker.myOperator("==", a1122863037, 9, 248)) && (cf && (a547336540.equals("i")))) && (input.equals("iJ")))) {
            cf = false;
            a1122863037 = 10;
            OperatorTracker.output("oX");
        }
        if (((OperatorTracker.myOperator("==", a1122863037, 9, 249)) && (((a547336540.equals("i")) && cf) && (input.equals("iA"))))) {
            cf = false;
            a1122863037 = 10;
            OperatorTracker.output("oX");
        }
    }

    private void calculateOutputm32(String input) {
        if (((OperatorTracker.myOperator("==", a1122863037, 10, 250)) && (((a547336540.equals("i")) && cf) && (input.equals("iC"))))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 13;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iE")) && ((a547336540.equals("i")) && (cf && (OperatorTracker.myOperator("==", a1122863037, 10, 251)))))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 13;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a1122863037, 10, 252)) && ((a547336540.equals("i")) && (cf && (input.equals("iF")))))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 13;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("i")) && ((input.equals("iG")) && ((OperatorTracker.myOperator("==", a1122863037, 10, 253)) && cf)))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 13;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("i")) && (((input.equals("iI")) && cf) && (OperatorTracker.myOperator("==", a1122863037, 10, 254))))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 13;
            OperatorTracker.output("oU");
        }
        if ((((a547336540.equals("i")) && (cf && (OperatorTracker.myOperator("==", a1122863037, 10, 255)))) && (input.equals("iJ")))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 13;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a1122863037, 10, 256)) && ((cf && (a547336540.equals("i"))) && (input.equals("iA"))))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 13;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iH")) && (((a547336540.equals("i")) && cf) && (OperatorTracker.myOperator("==", a1122863037, 10, 257))))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 10;
            OperatorTracker.output("oZ");
        }
    }

    private void calculateOutputm33(String input) {
        if (((input.equals("iC")) && (((OperatorTracker.myOperator("==", a1122863037, 11, 258)) && cf) && (a547336540.equals("i"))))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 10;
            OperatorTracker.output("oU");
        }
        if (((((a547336540.equals("i")) && cf) && (input.equals("iF"))) && (OperatorTracker.myOperator("==", a1122863037, 11, 259)))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 10;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("i")) && ((input.equals("iI")) && ((OperatorTracker.myOperator("==", a1122863037, 11, 260)) && cf)))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 10;
            OperatorTracker.output("oU");
        }
        if ((((a547336540.equals("i")) && (cf && (OperatorTracker.myOperator("==", a1122863037, 11, 261)))) && (input.equals("iJ")))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 10;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iA")) && ((a547336540.equals("i")) && ((OperatorTracker.myOperator("==", a1122863037, 11, 262)) && cf)))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 10;
            OperatorTracker.output("oU");
        }
        if (((((a547336540.equals("i")) && cf) && (input.equals("iD"))) && (OperatorTracker.myOperator("==", a1122863037, 11, 263)))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 10;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a1122863037, 11, 264)) && ((cf && (a547336540.equals("i"))) && (input.equals("iB"))))) {
            cf = false;
            a1122863037 = 13;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iH")) && ((a547336540.equals("i")) && (cf && (OperatorTracker.myOperator("==", a1122863037, 11, 265)))))) {
            cf = false;
            a1122863037 = 13;
            OperatorTracker.output("oU");
        }
        if ((((a547336540.equals("i")) && ((OperatorTracker.myOperator("==", a1122863037, 11, 266)) && cf)) && (input.equals("iG")))) {
            cf = false;
            a1122863037 = 14;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("i")) && (((OperatorTracker.myOperator("==", a1122863037, 11, 267)) && cf) && (input.equals("iE"))))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 10;
            OperatorTracker.output("oX");
        }
    }

    private void calculateOutputm34(String input) {
        if (((a547336540.equals("i")) && (((input.equals("iB")) && cf) && (OperatorTracker.myOperator("==", a1122863037, 12, 268))))) {
            cf = false;
            a547336540 = "h";
            a1855872761 = "i";
            OperatorTracker.output("oV");
        }
        if (((input.equals("iC")) && ((cf && (a547336540.equals("i"))) && (OperatorTracker.myOperator("==", a1122863037, 12, 269))))) {
            cf = false;
            a547336540 = "h";
            a1855872761 = "i";
            OperatorTracker.output("oV");
        }
        if ((((OperatorTracker.myOperator("==", a1122863037, 12, 270)) && ((input.equals("iE")) && cf)) && (a547336540.equals("i")))) {
            cf = false;
            a547336540 = "h";
            a1855872761 = "i";
            OperatorTracker.output("oV");
        }
        if (((a547336540.equals("i")) && ((input.equals("iF")) && ((OperatorTracker.myOperator("==", a1122863037, 12, 271)) && cf)))) {
            cf = false;
            a547336540 = "h";
            a1855872761 = "i";
            OperatorTracker.output("oV");
        }
        if (((a547336540.equals("i")) && (((input.equals("iH")) && cf) && (OperatorTracker.myOperator("==", a1122863037, 12, 272))))) {
            cf = false;
            a547336540 = "h";
            a1855872761 = "i";
            OperatorTracker.output("oV");
        }
        if ((((a547336540.equals("i")) && ((OperatorTracker.myOperator("==", a1122863037, 12, 273)) && cf)) && (input.equals("iI")))) {
            cf = false;
            a547336540 = "h";
            a1855872761 = "i";
            OperatorTracker.output("oV");
        }
        if ((((OperatorTracker.myOperator("==", a1122863037, 12, 274)) && (cf && (input.equals("iJ")))) && (a547336540.equals("i")))) {
            cf = false;
            a547336540 = "h";
            a1855872761 = "i";
            OperatorTracker.output("oV");
        }
        if (((input.equals("iA")) && (((a547336540.equals("i")) && cf) && (OperatorTracker.myOperator("==", a1122863037, 12, 275))))) {
            cf = false;
            a547336540 = "h";
            a1855872761 = "i";
            OperatorTracker.output("oV");
        }
        if (((input.equals("iG")) && (((OperatorTracker.myOperator("==", a1122863037, 12, 276)) && cf) && (a547336540.equals("i"))))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 16;
            OperatorTracker.output("oV");
        }
    }

    private void calculateOutputm35(String input) {
        if (((input.equals("iD")) && ((a547336540.equals("i")) && ((OperatorTracker.myOperator("==", a1122863037, 13, 277)) && cf)))) {
            cf = false;
            a547336540 = "f";
            a1305805768 = 14;
            OperatorTracker.output("oU");
        }
        if ((((OperatorTracker.myOperator("==", a1122863037, 13, 278)) && (cf && (input.equals("iB")))) && (a547336540.equals("i")))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("i")) && ((cf && (input.equals("iC"))) && (OperatorTracker.myOperator("==", a1122863037, 13, 279))))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iE")) && (((OperatorTracker.myOperator("==", a1122863037, 13, 280)) && cf) && (a547336540.equals("i"))))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if ((((input.equals("iF")) && (cf && (OperatorTracker.myOperator("==", a1122863037, 13, 281)))) && (a547336540.equals("i")))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if ((((a547336540.equals("i")) && (cf && (input.equals("iH")))) && (OperatorTracker.myOperator("==", a1122863037, 13, 282)))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iI")) && ((OperatorTracker.myOperator("==", a1122863037, 13, 283)) && ((a547336540.equals("i")) && cf)))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if ((((input.equals("iJ")) && ((a547336540.equals("i")) && cf)) && (OperatorTracker.myOperator("==", a1122863037, 13, 284)))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a1122863037, 13, 285)) && (((a547336540.equals("i")) && cf) && (input.equals("iA"))))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if ((((cf && (OperatorTracker.myOperator("==", a1122863037, 13, 286))) && (a547336540.equals("i"))) && (input.equals("iG")))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 5;
            OperatorTracker.output("oY");
        }
    }

    private void calculateOutputm36(String input) {
        if ((((cf && (input.equals("iH"))) && (OperatorTracker.myOperator("==", a1122863037, 14, 287))) && (a547336540.equals("i")))) {
            cf = false;
            a1122863037 = 13;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iC")) && ((a547336540.equals("i")) && ((OperatorTracker.myOperator("==", a1122863037, 14, 288)) && cf)))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if ((((cf && (OperatorTracker.myOperator("==", a1122863037, 14, 289))) && (input.equals("iD"))) && (a547336540.equals("i")))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a1122863037, 14, 290)) && ((a547336540.equals("i")) && (cf && (input.equals("iE")))))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if ((((cf && (input.equals("iF"))) && (OperatorTracker.myOperator("==", a1122863037, 14, 291))) && (a547336540.equals("i")))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iG")) && ((cf && (OperatorTracker.myOperator("==", a1122863037, 14, 292))) && (a547336540.equals("i"))))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((((input.equals("iI")) && cf) && (OperatorTracker.myOperator("==", a1122863037, 14, 293))) && (a547336540.equals("i")))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if ((((a547336540.equals("i")) && (cf && (OperatorTracker.myOperator("==", a1122863037, 14, 294)))) && (input.equals("iJ")))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((((a547336540.equals("i")) && cf) && (OperatorTracker.myOperator("==", a1122863037, 14, 295))) && (input.equals("iA")))) {
            cf = false;
            OperatorTracker.output("oU");
        }
        if (((((OperatorTracker.myOperator("==", a1122863037, 14, 296)) && cf) && (a547336540.equals("i"))) && (input.equals("iB")))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 5;
            OperatorTracker.output("oY");
        }
    }

    private void calculateOutputm37(String input) {
        if (((((a547336540.equals("i")) && cf) && (input.equals("iH"))) && (OperatorTracker.myOperator("==", a1122863037, 15, 297)))) {
            cf = false;
            a547336540 = "e";
            a691849188 = 6;
            OperatorTracker.output("oU");
        }
        if (((a547336540.equals("i")) && ((OperatorTracker.myOperator("==", a1122863037, 15, 298)) && ((input.equals("iB")) && cf)))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 9;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iC")) && ((a547336540.equals("i")) && (cf && (OperatorTracker.myOperator("==", a1122863037, 15, 299)))))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 9;
            OperatorTracker.output("oU");
        }
        if ((((a547336540.equals("i")) && (cf && (OperatorTracker.myOperator("==", a1122863037, 15, 300)))) && (input.equals("iD")))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 9;
            OperatorTracker.output("oU");
        }
        if (((((OperatorTracker.myOperator("==", a1122863037, 15, 301)) && cf) && (input.equals("iE"))) && (a547336540.equals("i")))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 9;
            OperatorTracker.output("oU");
        }
        if (((input.equals("iF")) && ((a547336540.equals("i")) && (cf && (OperatorTracker.myOperator("==", a1122863037, 15, 302)))))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 9;
            OperatorTracker.output("oU");
        }
        if ((((a547336540.equals("i")) && ((input.equals("iG")) && cf)) && (OperatorTracker.myOperator("==", a1122863037, 15, 303)))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 9;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a1122863037, 15, 304)) && ((a547336540.equals("i")) && ((input.equals("iI")) && cf)))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 9;
            OperatorTracker.output("oU");
        }
        if ((((cf && (a547336540.equals("i"))) && (OperatorTracker.myOperator("==", a1122863037, 15, 305))) && (input.equals("iJ")))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 9;
            OperatorTracker.output("oU");
        }
        if (((OperatorTracker.myOperator("==", a1122863037, 15, 306)) && ((cf && (input.equals("iA"))) && (a547336540.equals("i"))))) {
            cf = false;
            a547336540 = "g";
            a612577343 = 9;
            OperatorTracker.output("oU");
        }
    }

    public void calculateOutput(String input) {
        cf = true;
        if (((a547336540.equals("e")) && cf)) {
            if (((OperatorTracker.myOperator("==", a691849188, 4, 307)) && cf)) {
                calculateOutputm1(input);
            }
            if ((cf && (OperatorTracker.myOperator("==", a691849188, 5, 308)))) {
                calculateOutputm2(input);
            }
            if ((cf && (OperatorTracker.myOperator("==", a691849188, 6, 309)))) {
                calculateOutputm3(input);
            }
            if (((OperatorTracker.myOperator("==", a691849188, 7, 310)) && cf)) {
                calculateOutputm4(input);
            }
            if (((OperatorTracker.myOperator("==", a691849188, 8, 311)) && cf)) {
                calculateOutputm5(input);
            }
            if (((OperatorTracker.myOperator("==", a691849188, 9, 312)) && cf)) {
                calculateOutputm6(input);
            }
            if (((OperatorTracker.myOperator("==", a691849188, 10, 313)) && cf)) {
                calculateOutputm7(input);
            }
            if ((cf && (OperatorTracker.myOperator("==", a691849188, 11, 314)))) {
                calculateOutputm8(input);
            }
        }
        if (((a547336540.equals("f")) && cf)) {
            if ((cf && (OperatorTracker.myOperator("==", a1305805768, 9, 315)))) {
                calculateOutputm9(input);
            }
            if ((cf && (OperatorTracker.myOperator("==", a1305805768, 10, 316)))) {
                calculateOutputm10(input);
            }
            if (((OperatorTracker.myOperator("==", a1305805768, 11, 317)) && cf)) {
                calculateOutputm11(input);
            }
            if ((cf && (OperatorTracker.myOperator("==", a1305805768, 12, 318)))) {
                calculateOutputm12(input);
            }
            if ((cf && (OperatorTracker.myOperator("==", a1305805768, 13, 319)))) {
                calculateOutputm13(input);
            }
            if ((cf && (OperatorTracker.myOperator("==", a1305805768, 14, 320)))) {
                calculateOutputm14(input);
            }
            if ((cf && (OperatorTracker.myOperator("==", a1305805768, 15, 321)))) {
                calculateOutputm15(input);
            }
            if ((cf && (OperatorTracker.myOperator("==", a1305805768, 16, 322)))) {
                calculateOutputm16(input);
            }
        }
        if (((a547336540.equals("g")) && cf)) {
            if (((OperatorTracker.myOperator("==", a612577343, 4, 323)) && cf)) {
                calculateOutputm17(input);
            }
            if ((cf && (OperatorTracker.myOperator("==", a612577343, 5, 324)))) {
                calculateOutputm18(input);
            }
            if (((OperatorTracker.myOperator("==", a612577343, 6, 325)) && cf)) {
                calculateOutputm19(input);
            }
            if (((OperatorTracker.myOperator("==", a612577343, 7, 326)) && cf)) {
                calculateOutputm20(input);
            }
            if (((OperatorTracker.myOperator("==", a612577343, 8, 327)) && cf)) {
                calculateOutputm21(input);
            }
            if (((OperatorTracker.myOperator("==", a612577343, 9, 328)) && cf)) {
                calculateOutputm22(input);
            }
            if ((cf && (OperatorTracker.myOperator("==", a612577343, 10, 329)))) {
                calculateOutputm23(input);
            }
            if ((cf && (OperatorTracker.myOperator("==", a612577343, 11, 330)))) {
                calculateOutputm24(input);
            }
        }
        if ((cf && (a547336540.equals("h")))) {
            if ((cf && (a1855872761.equals("e")))) {
                calculateOutputm25(input);
            }
            if ((cf && (a1855872761.equals("f")))) {
                calculateOutputm26(input);
            }
            if ((cf && (a1855872761.equals("g")))) {
                calculateOutputm27(input);
            }
            if ((cf && (a1855872761.equals("h")))) {
                calculateOutputm28(input);
            }
            if (((a1855872761.equals("i")) && cf)) {
                calculateOutputm29(input);
            }
        }
        if (((a547336540.equals("i")) && cf)) {
            if (((OperatorTracker.myOperator("==", a1122863037, 8, 331)) && cf)) {
                calculateOutputm30(input);
            }
            if ((cf && (OperatorTracker.myOperator("==", a1122863037, 9, 332)))) {
                calculateOutputm31(input);
            }
            if (((OperatorTracker.myOperator("==", a1122863037, 10, 333)) && cf)) {
                calculateOutputm32(input);
            }
            if (((OperatorTracker.myOperator("==", a1122863037, 11, 334)) && cf)) {
                calculateOutputm33(input);
            }
            if (((OperatorTracker.myOperator("==", a1122863037, 12, 335)) && cf)) {
                calculateOutputm34(input);
            }
            if ((cf && (OperatorTracker.myOperator("==", a1122863037, 13, 336)))) {
                calculateOutputm35(input);
            }
            if ((cf && (OperatorTracker.myOperator("==", a1122863037, 14, 337)))) {
                calculateOutputm36(input);
            }
            if ((cf && (OperatorTracker.myOperator("==", a1122863037, 15, 338)))) {
                calculateOutputm37(input);
            }
        }
        if (cf)
            throw new IllegalArgumentException("Current state has no transition for this input!");
    }

    public static void main(String[] args) throws Exception {
        // init system and input reader
        Problem1 eca = new Problem1();
        String[] operators = { "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==", "==" };
        OperatorTracker.run(operators, eca);
    }

    public String[] sequence;

    public Void call() {
        Problem1 cp = new Problem1();
        for (String s : sequence) {
            try {
                cp.calculateOutput(s);
            } catch (Exception e) {
                PatchingLab.output("Invalid input: " + e.getMessage());
            }
        }
        return null;
    }

    public void setSequence(String[] trace) {
        sequence = trace;
    }
}

