import java.io.BufferedReader;
import java.io.InputStreamReader;
import nl.tudelft.instrumentation.fuzzing.*;
import nl.tudelft.instrumentation.runner.CallableTraceRunner;

public class ProblemPin implements CallableTraceRunner<Void> {

    static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

    private String[] inputs = { "T", "Z", "O", "N", "W", "E", "D", "R", "ENTER", "C" };

    public boolean cf = true;

    public int digit = 0;

    public boolean open = false;

    public void errorCheck() {
        DistanceTracker.myIf(DistanceTracker.MyVar(open), open, 14);
        if (open) {
            cf = false;
            Errors.__VERIFIER_error(0);
        }
    }

    public void calculateOutput(String input) {
        cf = true;
        DistanceTracker.myIf(DistanceTracker.binaryExpr(DistanceTracker.binaryExpr(DistanceTracker.MyVar(cf), DistanceTracker.binaryExpr(DistanceTracker.MyVar(digit), DistanceTracker.MyVar(0), "=="), "&&"), DistanceTracker.equals(DistanceTracker.MyVar("Z"), DistanceTracker.MyVar(input)), "&&"), cf && digit == 0 && input.equals("Z"), 22);
        if (cf && digit == 0 && input.equals("Z")) {
            digit += 1;
            cf = false;
            DistanceTracker.output("N");
        }
        DistanceTracker.myIf(DistanceTracker.binaryExpr(DistanceTracker.binaryExpr(DistanceTracker.MyVar(cf), DistanceTracker.binaryExpr(DistanceTracker.MyVar(digit), DistanceTracker.MyVar(1), "=="), "&&"), DistanceTracker.equals(DistanceTracker.MyVar("E"), DistanceTracker.MyVar(input)), "&&"), cf && digit == 1 && input.equals("E"), 27);
        if (cf && digit == 1 && input.equals("E")) {
            digit += 1;
            cf = false;
            DistanceTracker.output("N");
        }
        DistanceTracker.myIf(DistanceTracker.binaryExpr(DistanceTracker.binaryExpr(DistanceTracker.MyVar(cf), DistanceTracker.binaryExpr(DistanceTracker.MyVar(digit), DistanceTracker.MyVar(2), "=="), "&&"), DistanceTracker.equals(DistanceTracker.MyVar("R"), DistanceTracker.MyVar(input)), "&&"), cf && digit == 2 && input.equals("R"), 32);
        if (cf && digit == 2 && input.equals("R")) {
            digit += 1;
            cf = false;
            DistanceTracker.output("N");
        }
        DistanceTracker.myIf(DistanceTracker.binaryExpr(DistanceTracker.binaryExpr(DistanceTracker.MyVar(cf), DistanceTracker.binaryExpr(DistanceTracker.MyVar(digit), DistanceTracker.MyVar(3), "=="), "&&"), DistanceTracker.equals(DistanceTracker.MyVar("O"), DistanceTracker.MyVar(input)), "&&"), cf && digit == 3 && input.equals("O"), 37);
        if (cf && digit == 3 && input.equals("O")) {
            digit += 1;
            cf = false;
            DistanceTracker.output("N");
        }
        DistanceTracker.myIf(DistanceTracker.binaryExpr(DistanceTracker.binaryExpr(DistanceTracker.MyVar(cf), DistanceTracker.binaryExpr(DistanceTracker.MyVar(digit), DistanceTracker.MyVar(4), "=="), "&&"), DistanceTracker.equals(DistanceTracker.MyVar("ENTER"), DistanceTracker.MyVar(input)), "&&"), cf && digit == 4 && input.equals("ENTER"), 43);
        if (cf && digit == 4 && input.equals("ENTER")) {
            cf = false;
            open = true;
            DistanceTracker.output("Y");
        }
        DistanceTracker.myIf(DistanceTracker.binaryExpr(DistanceTracker.MyVar(cf), DistanceTracker.equals(DistanceTracker.MyVar("ENTER"), DistanceTracker.MyVar(input)), "&&"), cf && input.equals("ENTER"), 49);
        if (cf && input.equals("ENTER")) {
            cf = false;
            digit = 0;
            DistanceTracker.output("X");
        }
        DistanceTracker.myIf(DistanceTracker.binaryExpr(DistanceTracker.MyVar(cf), DistanceTracker.binaryExpr(DistanceTracker.binaryExpr(DistanceTracker.binaryExpr(DistanceTracker.binaryExpr(DistanceTracker.binaryExpr(DistanceTracker.binaryExpr(DistanceTracker.binaryExpr(DistanceTracker.equals(DistanceTracker.MyVar("T"), DistanceTracker.MyVar(input)), DistanceTracker.equals(DistanceTracker.MyVar("Z"), DistanceTracker.MyVar(input)), "||"), DistanceTracker.equals(DistanceTracker.MyVar("O"), DistanceTracker.MyVar(input)), "||"), DistanceTracker.equals(DistanceTracker.MyVar("N"), DistanceTracker.MyVar(input)), "||"), DistanceTracker.equals(DistanceTracker.MyVar("W"), DistanceTracker.MyVar(input)), "||"), DistanceTracker.equals(DistanceTracker.MyVar("E"), DistanceTracker.MyVar(input)), "||"), DistanceTracker.equals(DistanceTracker.MyVar("D"), DistanceTracker.MyVar(input)), "||"), DistanceTracker.equals(DistanceTracker.MyVar("R"), DistanceTracker.MyVar(input)), "||"), "&&"), cf && (input.equals("T") || input.equals("Z") || input.equals("O") || input.equals("N") || input.equals("W") || input.equals("E") || input.equals("D") || input.equals("R")), 55);
        if (cf && (input.equals("T") || input.equals("Z") || input.equals("O") || input.equals("N") || input.equals("W") || input.equals("E") || input.equals("D") || input.equals("R"))) {
            cf = false;
            DistanceTracker.output("N");
        }
        errorCheck();
        DistanceTracker.myIf(DistanceTracker.MyVar(cf), cf, 61);
        if (cf) {
            throw new IllegalArgumentException("Current state has no transition for this input!");
        }
    }

    public static void main(String[] args) throws Exception {
        // init system and input reader
        ProblemPin eca = new ProblemPin();
        DistanceTracker.run(eca.inputs, eca);
    }

    public String[] sequence;

    public Void call() {
        ProblemPin cp = new ProblemPin();
        for (String s : sequence) {
            try {
                cp.calculateOutput(s);
            } catch (Exception e) {
                FuzzingLab.output("Invalid input: " + e.getMessage());
            }
        }
        return null;
    }

    public void setSequence(String[] trace) {
        sequence = trace;
    }
}

