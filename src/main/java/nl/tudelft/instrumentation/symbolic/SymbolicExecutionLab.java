package nl.tudelft.instrumentation.symbolic;
import java.util.concurrent.TimeUnit;
import java.nio.file.Path;
import java.util.*;
import com.microsoft.z3.*;

import java.util.stream.Collectors;

import java.util.Random;

/**
 * You should write your solution using this class.
 */
public class SymbolicExecutionLab {

    static Random r = new Random();
    static Boolean isFinished = false;
    static List<String> currentTrace;
    static int traceLength = 20;

    static int visitedBranches = 0;
    static boolean isSatisfiable = false;

    // Store unsatisfiable traces
    static Set<BoolExpr> unsatisfied = new HashSet<>();
    // Store visited branches
    static Set<List<String>> visited = new HashSet<>();
    static Set<List<String>> totalVisited = new HashSet<>();
    // Store error codes
    static Set<String> output = new HashSet<>();
    static Set<List<String>> usedTraces = new HashSet<>();

    static PriorityQueue<List<String>> queue = new PriorityQueue<>(new Comparator<List<String>>() {
        @Override
        public int compare(List<String> s1, List<String> s2) {
            return s1.size() - s2.size();
        }
    });


    static void initialize(String[] inputSymbols) {
        // Initialise a random trace from the input symbols of the problem.
        currentTrace = generateRandomTrace(inputSymbols);
        queue.add(currentTrace);
    }

    static MyVar createVar(String name, Expr value, Sort s) {
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

    static MyVar createInput(String name, Expr value, Sort s) {
        // Create a free variable
        Context c = PathTracker.ctx;
        String newName = name + "_" + PathTracker.z3counter++;
        Expr z3var = c.mkConst(c.mkSymbol(newName), s);

        BoolExpr constraint = c.mkFalse();
        for (String input : PathTracker.inputSymbols) {
            constraint = c.mkOr(c.mkEq(z3var, c.mkString(input)), constraint);
        }
        PathTracker.addToModel(constraint);
        MyVar input = new MyVar(z3var, newName);
        PathTracker.inputs.add(input);

        return input;
    }


    static MyVar createBoolExpr(BoolExpr var, String operator) {
        // Any unary expression (!)
        Context c = PathTracker.ctx;
        if (operator.contains("!")) {
            return new MyVar(c.mkNot(var));
        } else {
            throw new RuntimeException("Error: expected (!) but got: " + operator);
        }
    }

    static MyVar createBoolExpr(BoolExpr left_var, BoolExpr right_var, String operator) {
        // Any binary expression (&, &&, |, ||)
        Context c = PathTracker.ctx;
        Expr z3var = c.mkFalse();
        switch (operator) {
            case "|":
            case "||":
                z3var = c.mkOr(left_var, right_var);
                break;
            case "&":
            case "&&":
                z3var = c.mkAnd(left_var, right_var);
                break;
            default:
                throw new RuntimeException("Error: expected (&, &&, |, ||) but got: " + operator);
        }
        return new MyVar(z3var);
    }

    static MyVar createIntExpr(IntExpr var, String operator) {
        // Any unary expression (+, -)
        // if(operator.equals("+") || operator.equals("-"))
        //     return new MyVar(PathTracker.ctx.mkInt(0));
        Context c = PathTracker.ctx;
        if (operator.equals("-")) {
            return new MyVar(c.mkUnaryMinus(var));
        } else if (operator.equals("+") || operator.equals("")) {
            return new MyVar(var);
        } else {
            throw new RuntimeException("Error: expected (-, +) but got: " + operator);
        }
    }

    static MyVar createIntExpr(IntExpr left_var, IntExpr right_var, String operator) {
        // Any binary expression (+, -, /, etc)
        // if(operator.equals("+") || operator.equals("-") || operator.equals("/") || operator.equals("*") || operator.equals("%") || operator.equals("^"))
        //     return new MyVar(PathTracker.ctx.mkInt(0));
        Context c = PathTracker.ctx;
        Expr z3var = c.mkFalse();
        switch (operator) {
            case "+":
                z3var = c.mkAdd(left_var, right_var);
                break;
            case "-":
                z3var = c.mkSub(left_var, right_var);
                break;
            case "/":
                z3var = c.mkDiv(left_var, right_var);
                break;
            case "*":
                z3var = c.mkMul(left_var, right_var);
                break;
            case "%":
                z3var = c.mkMod(left_var, right_var);
                break;
            case "^":
                z3var = c.mkPower(left_var, right_var);
                break;
            case "==":
                z3var = c.mkEq(left_var, right_var);
                break;
            case "<":
                z3var = c.mkLt(left_var, right_var);
                break;
            case "<=":
                z3var = c.mkLe(left_var, right_var);
                break;
            case ">":
                z3var = c.mkGt(left_var, right_var);
                break;
            case ">=":
                z3var = c.mkGe(left_var, right_var);
                break;
            default:
                throw new RuntimeException("Error: expected binary expression (==, <, /, etc.) but got: " + operator);
        }
        return new MyVar(z3var);
    }

    static MyVar createStringExpr(SeqExpr left_var, SeqExpr right_var, String operator) {
        // We only support String.equals
        Context c = PathTracker.ctx;
        if (operator.equals("==")) {
            return new MyVar(c.mkEq(left_var, right_var));
        } else {
            throw new RuntimeException("Error: expected string expression but got: " + operator);
        }
    }

    static void assign(MyVar var, String name, Expr value, Sort s) {
        // All variable assignments, use single static assignment
        Context c = PathTracker.ctx;
        Expr z3var = c.mkConst(c.mkSymbol(name + "_" + PathTracker.z3counter++), s);
        var.z3var = z3var;
        PathTracker.addToModel(c.mkEq(z3var, value));
    }


    static void encounteredNewBranch(MyVar condition, boolean value, int line_nr) {
        // Skip branch if already visited
        String branch_nr = line_nr + "-" + value;

        List<String> traceBranch = new ArrayList<>(currentTrace);
        traceBranch.add(branch_nr);

        if (visited.contains(traceBranch)) {
            return;
        }
        visited.add(traceBranch);
        totalVisited.add(traceBranch);

        // Call the solver
        Context c = PathTracker.ctx;
        // Reset satisfiablity check before calling solve.
        isSatisfiable = false;
        // For branches to visit use mkFalse and mkTrue instead of the more generic mkBool(value)
        BoolExpr z3var = c.mkEq(condition.z3var, c.mkTrue());
        BoolExpr Oppositez3var = c.mkEq(condition.z3var, c.mkFalse());

        // Find input that flips the condition; so solve for opposite branch only if it is not already solved before as unsatisfiable.
        if (value && !unsatisfied.contains(Oppositez3var)) {
            PathTracker.solve(Oppositez3var, false);
            // Add visited branch regardless of it being satisfiable or not.
            PathTracker.addToBranches(z3var);

            // Check if it was unsatisfiable
            if (!isSatisfiable) {
                unsatisfied.add(Oppositez3var);
            }
        } else if (!value && !unsatisfied.contains(z3var)) {
            PathTracker.solve(z3var, false);
            PathTracker.addToBranches(Oppositez3var);
            if (!isSatisfiable) {
                unsatisfied.add(z3var);
            }
        }
    }

    static void newSatisfiableInput(LinkedList<String> new_inputs) {
        // Hurray! found a new branch using these new inputs!
        //System.out.println("FOUND NEW SAT");
        isSatisfiable = true;

        // Remove extra quotes.
        List<String> inputs = new_inputs.stream().map(x -> x.replace("\"", "")).collect(Collectors.toList());
        queue.add(inputs);

        // Mutate trace by adding a random symbol for deeper exploration.
        String[] symbols = PathTracker.inputSymbols;
        inputs.add(symbols[r.nextInt(symbols.length)]);

        // Add new input trace to the queue if its not already there
        if (!queue.contains(inputs)) {
            queue.add(inputs);
        }
    }

    /**
     * Method for fuzzing new inputs for a program.
     *
     * @param inputSymbols the inputSymbols to fuzz from.
     * @return a fuzzed sequence
     */
    static List<String> fuzz(String[] inputSymbols) {
        /*
         * TODO:
         * Add here your code for fuzzing a new sequence for the RERS problem.
         * You can guide your fuzzer to fuzz "smart" input sequences to cover
         * more branches using symbolic execution. Right now we just generate
         * a complete random sequence using the given input symbols. Please
         * change it to your own code.
         */

        if (queue.isEmpty()) {
            // Generate random trace when the queue is empty
            return generateRandomTrace(inputSymbols);
        } else {
            return queue.poll();
        }
    }

    /**
     * Generate a random trace from an array of symbols.
     *
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

        // Run for 5 minutes.
        long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(5L, TimeUnit.MINUTES);

        // Place here your code to guide your fuzzer with its search using Symbolic Execution.
        while (!isFinished && System.nanoTime() < endTime) {
            currentTrace = fuzz(PathTracker.inputSymbols);

            System.out.print("Trace: ");
            currentTrace.forEach(System.out::print);
            System.out.println("\n");
            
            PathTracker.runNextFuzzedSequence(currentTrace.toArray(new String[0]));

            // visited.clear();
            System.out.println("Total visited branches: " + totalVisited.size() + "\nOutput: " + output + "\nOutput size: " + output.size());
            usedTraces.add(currentTrace);

            PathTracker.reset();
        }
        System.exit(-1);
    }

    public static void output(String out) {
        if (out.contains("error")) {
            output.add(out);
        }
        // System.out.println(out);
    }
}