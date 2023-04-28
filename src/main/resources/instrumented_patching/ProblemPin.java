import java.io.BufferedReader;
import java.io.InputStreamReader;
import nl.tudelft.instrumentation.patching.*;
import nl.tudelft.instrumentation.runner.CallableTraceRunner;

public class ProblemPin implements CallableTraceRunner<Void> {

    static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

    private String[] inputs = { "T", "Z", "O", "N", "W", "E", "D", "R", "ENTER", "C" };

    public boolean cf = true;

    public int digit = 0;

    public boolean open = false;

    public void errorCheck() {
        if (open) {
            cf = false;
            Errors.__VERIFIER_error(0);
        }
    }

    public void calculateOutput(String input) {
        cf = true;
        if (cf && OperatorTracker.myOperator("==", digit, 0, 0) && input.equals("Z")) {
            digit += 1;
            cf = false;
            OperatorTracker.output("N");
        }
        if (cf && OperatorTracker.myOperator("==", digit, 1, 1) && input.equals("E")) {
            digit += 1;
            cf = false;
            OperatorTracker.output("N");
        }
        if (cf && OperatorTracker.myOperator("==", digit, 2, 2) && input.equals("R")) {
            digit += 1;
            cf = false;
            OperatorTracker.output("N");
        }
        if (cf && OperatorTracker.myOperator("==", digit, 3, 3) && input.equals("O")) {
            digit += 1;
            cf = false;
            OperatorTracker.output("N");
        }
        if (cf && OperatorTracker.myOperator("==", digit, 4, 4) && input.equals("ENTER")) {
            cf = false;
            open = true;
            OperatorTracker.output("Y");
        }
        if (cf && input.equals("ENTER")) {
            cf = false;
            digit = 0;
            OperatorTracker.output("X");
        }
        if (cf && (input.equals("T") || input.equals("Z") || input.equals("O") || input.equals("N") || input.equals("W") || input.equals("E") || input.equals("D") || input.equals("R"))) {
            cf = false;
            OperatorTracker.output("N");
        }
        errorCheck();
        if (cf) {
            throw new IllegalArgumentException("Current state has no transition for this input!");
        }
    }

    public static void main(String[] args) throws Exception {
        // init system and input reader
        ProblemPin eca = new ProblemPin();
        String[] operators = { "==", "==", "==", "==", "==" };
        OperatorTracker.run(operators, eca);
    }

    public String[] sequence;

    public Void call() {
        ProblemPin cp = new ProblemPin();
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

