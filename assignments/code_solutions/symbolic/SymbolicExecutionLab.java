package nl.tudelft.instrumentation.symbolic;

import java.util.*;
import com.microsoft.z3.*;
import nl.tudelft.instrumentation.fuzzing.DistanceTracker;

import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.Queue;


/**
 * You should write your solution using this class.
 */
public class SymbolicExecutionLab {

    static Queue<LinkedList<String> > q = new LinkedList<LinkedList<String> >();
    static Set<List<String>> already_done = new HashSet<List<String>>();
    static Random r = new Random();
    static Boolean isFinished = false;
    static int traceLength = 1;
    public static HashSet<String> branchVisited = new HashSet<>();
    private static int curLine;
    private static boolean curValue;
    static List<String> currentTrace;
    static HashSet<String> errorsFound = new HashSet<>();
    static long startTime;
    static long RUN_TIME = 900000;

    static void initialize(String[] inputSymbols){
        // Initialise a random trace from the input symbols of the problem.
        currentTrace = generateRandomTrace(inputSymbols);
        startTime = System.currentTimeMillis();
    }

    static MyVar createVar(String name, Expr value, Sort s){
        Context c = PathTracker.ctx;
        /**
         * Create var, assign value and add to path constraint.
         * We show how to do it for creating new symbols, please
         * add similar steps to the functions below in order to
         * obtain a path constraint.
         */
        Expr z3var = c.mkConst(c.mkSymbol(name + "_" + PathTracker.z3counter++), s);
        PathTracker.addToModel(c.mkEq(z3var, value));
        return new MyVar(z3var, name);
    }

    static MyVar createInput(String name, Expr value, Sort s){
        Context c = PathTracker.ctx;
        // create an input var, these should be free variables!
        String nameWithCounter = name + "_" + PathTracker.z3counter++;
        Expr z3var = c.mkConst(c.mkSymbol(nameWithCounter), s);

        BoolExpr constraint = c.mkFalse();
        for (String input: PathTracker.inputSymbols) {
            constraint = c.mkOr(c.mkEq(z3var, c.mkString(input)), constraint);
        }

        PathTracker.addToModel(constraint);

        MyVar result = new MyVar(z3var, nameWithCounter);
        PathTracker.inputs.add(result);
        return result;
    }

    static MyVar createBoolExpr(BoolExpr var, String operator){
        // any unary expression (!)
        if (operator.equals("!")) {
            return new MyVar(PathTracker.ctx.mkNot(var));
        } else {
            throw new RuntimeException("Unimplemented unary boolean expression: " + operator);
        }
    }

    static MyVar createBoolExpr(BoolExpr left_var, BoolExpr right_var, String operator){
        // any binary expression (&, &&, |, ||)
        Context ctx = PathTracker.ctx;
        switch (operator) {
            case "&":
            case "&&":
                return new MyVar(ctx.mkAnd(left_var, right_var));
            case "|":
            case "||":
                return new MyVar(ctx.mkOr(left_var, right_var));
            default:
                throw new RuntimeException("Unimplemented binary boolean expression: " + operator);
        }
    }

    static MyVar createIntExpr(IntExpr var, String operator){
        // any unary expression (+, -)
        switch(operator) {
            case "+":
                return new MyVar(var);
            case "-":
                return new MyVar(PathTracker.ctx.mkUnaryMinus(var));
            default:
                throw new RuntimeException("Unimplemented unary integer expression: " + operator);
        }
    }

    static MyVar createIntExpr(IntExpr left_var, IntExpr right_var, String operator){
        // any binary expression (+, -, /, etc)
        Context ctx = PathTracker.ctx;
        switch (operator) {
            case "+":
                return new MyVar(ctx.mkAdd(left_var, right_var));
            case "-":
                return new MyVar(ctx.mkSub(left_var, right_var));
            case "/":
                return new MyVar(ctx.mkDiv(left_var, right_var));
            case "*":
                return new MyVar(ctx.mkMul(left_var, right_var));
            case "%":
                return new MyVar(ctx.mkMod(left_var, right_var));
            case "^":
                return new MyVar(ctx.mkPower(left_var, right_var));
            case "==":
                return new MyVar(ctx.mkEq(left_var, right_var));
            case "<=":
                return new MyVar(ctx.mkLe(left_var, right_var));
            case "<":
                return new MyVar(ctx.mkLt(left_var, right_var));
            case ">=":
                return new MyVar(ctx.mkGe(left_var, right_var));
            case ">":
                return new MyVar(ctx.mkGt(left_var, right_var));
            default:
                throw new RuntimeException("Unimplemented binary integer expression: " + operator);
        }
    }

    static MyVar createStringExpr(SeqExpr left_var, SeqExpr right_var, String operator){
        // we only support String.equals
        if (operator.equals("==")) {
            return new MyVar(PathTracker.ctx.mkEq(left_var, right_var));
        }
        throw new RuntimeException("Unimplemented string expression: " + operator);
    }

    static void assign(MyVar var, String name, Expr value, Sort s){ //?????
        // all variable assignments, use single static assignment
        Context c = PathTracker.ctx;
        var.z3var = c.mkConst(c.mkSymbol(name + "_" + PathTracker.z3counter++), s);
        PathTracker.addToModel(c.mkEq(var.z3var, value));
    }

    static void encounteredNewBranch(MyVar condition, boolean value, int line_nr){
        curLine = line_nr;
        curValue = value;

        System.out.println("Visited:" + branchVisited.size());
        String branch = "" + line_nr + value;

        // Mark to which branch this trace belongs to
        List<String> trace_line = new ArrayList<>(currentTrace);
        trace_line.add(branch);

        // Don't try to solve for a trace that was already used for this branch.
        if(already_done.contains(trace_line)) {
            return;
        }

        already_done.add(trace_line);
        branchVisited.add(branch);

        Context c = PathTracker.ctx;
        // call the solver
        PathTracker.solve(c.mkEq(condition.z3var, value ? c.mkFalse() : c.mkTrue()) , false);
        // Add branch to the PathTracker
        PathTracker.addToBranches(value ? (BoolExpr) condition.z3var : c.mkNot((BoolExpr) condition.z3var));
    }

    static void newSatisfiableInput(LinkedList<String> new_inputs) {
        // hurray! found a new branch using these new inputs!
        System.out.println("Line nr: " + curLine + ", value: " + curValue + ", Inputs: " + new_inputs);

        // Remove the extra quotes from the inputs that were find by the solver.
        List<String> trimmed_new_inputs = new_inputs.stream()
                .map(s -> s.replaceAll("\"", ""))
                .collect(Collectors.toList());

        // Add the trace to the queue
        q.add(new LinkedList<>(trimmed_new_inputs));

    }

    /**
     * Method for fuzzing new inputs for a program.
     * @param inputSymbols the inputSymbols to fuzz from.
     * @return a fuzzed sequence
     */
    static List<String> fuzz(String[] inputSymbols){
        /*
         * Add here your code for fuzzing a new sequence for the RERS problem.
         * You can guide your fuzzer to fuzz "smart" input sequences to cover
         * more branches using symbolic execution. Right now we just generate
         * a complete random sequence using the given input symbols. Please
         * change it to your own code.
         */
        return generateRandomTrace(inputSymbols);
    }


    /**
     * Generate a random trace from an array of symbols.
     * @param symbols the symbols from which a trace should be generated from.
     * @return a random trace that is generated from the given symbols.
     */
    static List<String> generateRandomTrace(String[] symbols) {
        ArrayList<String> trace = new ArrayList<>();
        for (int i = 0; i < traceLength; i++) {
            trace.add(symbols[r.nextInt(symbols.length)]);
        }
        return trace;
    }

    static void run() {
        initialize(PathTracker.inputSymbols);
        PathTracker.runNextFuzzedSequence(currentTrace.toArray(new String[0]));
        // Place here your code to guide your fuzzer with its search using Symbolic Execution.
        while(System.currentTimeMillis() - startTime < RUN_TIME) {
            // Reset and do clean up before running a new trace.
            PathTracker.reset();
            System.gc();

            // Take a new trace from the queue
            LinkedList<String> new_input = q.poll();
            if(new_input == null) {
                break;
            }

            // Add a symbol to the trace to increase exploration.
            new_input.addLast(PathTracker.inputSymbols[0]);
            currentTrace = new ArrayList<>(new_input);
            PathTracker.runNextFuzzedSequence(new_input.toArray(new String[0]));

        }

        System.out.println("Number of unique branches after 15 minutes: " + branchVisited.size());
        System.out.println("Number of unique error codes after 15 minutes: " + errorsFound.size());
        System.out.println("Unique errors triggered: " + errorsFound.toString());
        System.exit(0);

    }

    /**
     * Method that is used for catching the output from standard out.
     * You should write your own logic here.
     * @param out the string that has been outputted in the standard out.
     */
    public static void output(String out){
        System.out.println(out);
        if (out.contains("error_")) {
            errorsFound.add(out);
        }
    }

}