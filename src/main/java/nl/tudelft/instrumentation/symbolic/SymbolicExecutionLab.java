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
    static String[] currentTrace;
    static int traceLength = 10;
    static Set<Expr> unsat = new HashSet<>();
    static Set<Integer> visited = new HashSet<>();
    static Set<String> output = new HashSet<>();

    // static Queue<String[]> queue = new LinkedList<>();
    static PriorityQueue<String[]> queue = new PriorityQueue<>(new Comparator<String[]>() {
        @Override
        public int compare(String[] s1, String[] s2) {
            return s1.length - s2.length;
        }
    });

    static Set<String[]> usedTraces = new HashSet<>();

    static int visitedBranches = 0;

    static void initialize(String[] inputSymbols) {
        // Initialise a random trace from the input symbols of the problem.
        List<String> randomTrace = generateRandomTrace(inputSymbols);
        currentTrace = randomTrace.toArray(new String[0]);
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
        Expr z3var = c.mkConst(c.mkSymbol(name + "_" + PathTracker.z3counter++), s);

        BoolExpr constraint = c.mkFalse();
        for (String input : PathTracker.inputSymbols) {
            constraint = c.mkOr(c.mkEq(z3var, c.mkString(input)), constraint);
        }
        PathTracker.addToModel(constraint);
        MyVar input = new MyVar(z3var, name);
        PathTracker.inputs.add(input);

        return input;
    }


    static MyVar createBoolExpr(BoolExpr var, String operator) {
        // Any unary expression (!)
        Context c = PathTracker.ctx;
        if (operator.contains("!")) {
            return new MyVar(c.mkNot(var));
        } else {
            System.out.println("Error: expected (!) but got: " + operator);
            // return new MyVar(c.mkFalse());
            return null;
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
            case "==":
                z3var = c.mkEq(left_var, right_var);
            default:
                System.out.println("Error: expected (&, &&, |, ||) but got: " + operator);
                return null;
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
            System.out.println("Error: expected (-, +) but got: " + operator);
            return null;
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
                System.out.println("Error: expected binary expression (==, <, /, etc.) but got: " + operator);
                return null;
        }
        return new MyVar(z3var);
    }

    static MyVar createStringExpr(SeqExpr left_var, SeqExpr right_var, String operator) {
        // We only support String.equals
        Context c = PathTracker.ctx;
        if (operator.equals("==")) {
            return new MyVar(c.mkEq(left_var, right_var));
        } else {
            // return new MyVar(c.mkFalse());
            return null;
        }
    }

    static void assign(MyVar var, String name, Expr value, Sort s) {
        // All variable assignments, use single static assignment
        Context c = PathTracker.ctx;
        Expr z3var = c.mkConst(c.mkSymbol(name + "_" + PathTracker.z3counter++), s);
        var.z3var = value;
        PathTracker.addToModel(c.mkEq(z3var, value));
    }

    // TODO: Check for Unsat
    static void encounteredNewBranch(MyVar condition, boolean value, int line_nr) {
        // Call the solver
        Context c = PathTracker.ctx;
        BoolExpr z3var = c.mkEq(condition.z3var, c.mkBool(value));
        BoolExpr Oppositez3var = c.mkEq(condition.z3var, c.mkBool(!value));
        visited.add(line_nr);

        if (!visited.contains(line_nr)) {
            // Find input that flips the condition
            if (value) {
                PathTracker.solve(Oppositez3var, false);
            } else {
                PathTracker.solve(z3var, false);
            }
        }

        // Add constraint to the branches regardless if its satisfiable
        if (value) {
            PathTracker.addToBranches(z3var);
        } else {
            PathTracker.addToBranches(Oppositez3var);
        }
    }

    static void newSatisfiableInput(LinkedList<String> new_inputs) {
        //System.out.println("NEW INPUTS " + new_inputs);

        // Convert char to string array
        List<String[]> inputs = new_inputs.stream().map(x -> {
            // Remove symbols (e.g. quotations, commas, etc.)
            char[] charArray = x.replace(",", "").replace(" ", "").replace("\"", "").replace("[", "").replace("]", "").toCharArray(); 
            String[] stringArray = new String[charArray.length];

            for (int i = 0; i < charArray.length; i++) {
                stringArray[i] = Character.toString(charArray[i]);
            }

            return stringArray;
        }).collect(Collectors.toList());

        // Add new input trace to the queue if its not already there or its already run.
        for (String[] input : inputs) {
            if (!queue.contains(input) && !usedTraces.contains(input)) {
                queue.add(input);
            }
        }

        // Hurray! found a new branch using these new inputs!
        //System.out.println("FOUND NEW SAT");
    }

    /**
     * Method for fuzzing new inputs for a program.
     *
     * @param inputSymbols the inputSymbols to fuzz from.
     * @return a fuzzed sequence
     */
    static String[] fuzz(String[] inputSymbols) {
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
            List<String> randomTrace = generateRandomTrace(inputSymbols);
            return randomTrace.toArray(new String[0]);
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
        int count = 0;

        // Run for 5 minutes.
        long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(5L, TimeUnit.MINUTES);

        // Place here your code to guide your fuzzer with its search using Symbolic Execution.
        while (!isFinished && System.nanoTime() < endTime) {
            currentTrace = fuzz(PathTracker.inputSymbols);
            usedTraces.add(currentTrace);
            printTrace(currentTrace);
            PathTracker.runNextFuzzedSequence(currentTrace);

            // TODO: Fix printing out the correct trace and only mutate on satisfiable strings
            // String[] toRun = Arrays.copyOf(currentTrace, currentTrace.length + 1);
            // toRun[toRun.length - 1] = PathTracker.inputSymbols[r.nextInt(PathTracker.inputSymbols.length)];
            // printTrace(toRun);
            // PathTracker.runNextFuzzedSequence(toRun);
            // usedTraces.add(toRun);

            visitedBranches = Math.max(visitedBranches, visited.size());
            visited.clear();
            PathTracker.reset();

            //DEBUG CODE
            // if (count++ == 5000) {
            //     isFinished = true;
            // }


            System.out.println("Visited branches: " + visitedBranches + "\nOutput: " + output + "\nOutput size: " + output.size());
            // if (count % 10 == 0) {
            //     System.out.println("Visited size: " + visitedBranches + ", Queue size: " + queue.size() + ", First element: " + Arrays.stream(queue.peek()).map(x -> {
            //         String res = "";
            //         for (char i : x.toCharArray()) {
            //             res += i;
            //         }
            //         return res;
            //     }).collect(Collectors.toList()));
            // }

            //System.out.println(output);
            // // Do things!
            // try {
            //     System.out.println("Woohoo, looping!");
            //     Thread.sleep(1000);
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }
        }
        System.exit(-1);
    }

    public static void output(String out) {
        if (out.contains("error")) {
            output.add(out);
        }
        // System.out.println(out);
    }

    private static void printTrace(String[] trace) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String t : trace) {
            stringBuilder.append(t);
        }
        System.out.println("Trace: " + stringBuilder.toString());
    }

}