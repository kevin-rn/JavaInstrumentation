package nl.tudelft.instrumentation.symbolic;

import java.util.*;
import com.microsoft.z3.*;

import nl.tudelft.instrumentation.branch.BranchCoverageTracker;
import nl.tudelft.instrumentation.fuzzing.DistanceTracker;

import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.text.BreakIterator;

/**
 * You should write your solution using this class.
 */
public class SymbolicExecutionLab {

    static Random r = new Random();
    static Boolean isFinished = false;
    static List<String> currentTrace;
    static int traceLength = 10;

    static void initialize(String[] inputSymbols){
        // Initialise a random trace from the input symbols of the problem.
        currentTrace = generateRandomTrace(inputSymbols);
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
        // Create an input var, these should be free variables!
        return new MyVar(PathTracker.ctx.mkString(""));
    }

    static MyVar createBoolExpr(BoolExpr var, String operator){
        // Any unary expression (!)
        Context c = PathTracker.ctx;
        if (operator.contains("!")) {
            return new MyVar(c.mkNot(var));
        } else {
            System.out.println("Error: expected (!) but got: " + operator);
            return new MyVar(c.mkFalse());
        }
    }

    static MyVar createBoolExpr(BoolExpr left_var, BoolExpr right_var, String operator){
        // Any binary expression (&, &&, |, ||)
        Context c = PathTracker.ctx;
        Expr expr = c.mkFalse();
        switch(operator) {
            case "|":
            case "||":
                expr = c.mkOr(left_var, right_var);
                break;
            case "&":
            case "&&":
                expr = c.mkAnd(left_var, right_var);
                break;
            default:
                System.out.println("Error: expected (&, &&, |, ||) but got: " + operator);
        }     
        return new MyVar(expr);
    }

    static MyVar createIntExpr(IntExpr var, String operator){
        // Any unary expression (+, -)
        // if(operator.equals("+") || operator.equals("-"))
        //     return new MyVar(PathTracker.ctx.mkInt(0));
        Context c = PathTracker.ctx;
        if (operator.equals("-")) {
            return new MyVar(c.mkUnaryMinus(var));
        } else {
            return new MyVar(var);
        }
    }

    static MyVar createIntExpr(IntExpr left_var, IntExpr right_var, String operator){
        // Any binary expression (+, -, /, etc)
        // if(operator.equals("+") || operator.equals("-") || operator.equals("/") || operator.equals("*") || operator.equals("%") || operator.equals("^"))
        //     return new MyVar(PathTracker.ctx.mkInt(0));
        Context c = PathTracker.ctx;
        Expr expr = c.mkFalse();
        switch (operator) {
            case "+":
                expr = c.mkAdd(left_var, right_var);
                break;
            case "-":
                expr = c.mkSub(left_var, right_var);
                break;
            case "/":
                expr = c.mkDiv(left_var, right_var);
                break;
            case "*":
                expr = c.mkMul(left_var, right_var);
                break;
            case "%":
                expr = c.mkMod(left_var, right_var);
                break;
            case "^":
                expr = c.mkPower(left_var, right_var);
                break;
            case "==":
                expr = c.mkEq(left_var, right_var);
                break;
            case "<":
                expr = c.mkLt(left_var, right_var);
                break;
            case "<=":
                expr = c.mkLe(left_var, right_var);
                break;
            case ">":
                expr = c.mkGt(left_var, right_var);
                break;
            case ">=":
                expr = c.mkGe(left_var, right_var);
                break;
            default:
                System.out.println("Error: expected binary expression (==, <, /, etc.) but got: " + operator);
        }
        return new MyVar(expr);
    }

    static MyVar createStringExpr(SeqExpr left_var, SeqExpr right_var, String operator){
        // We only support String.equals
        Context c = PathTracker.ctx;
        if (operator.equals("==")) {
            return new MyVar(c.mkEq(left_var, right_var));
        } else {
            return new MyVar(c.mkFalse());
        }
    }

    static void assign(MyVar var, String name, Expr value, Sort s){
        // All variable assignments, use single static assignment
        Context c = PathTracker.ctx;
        var.z3var = c.mkConst(c.mkSymbol(name + "_" + PathTracker.z3counter++), s);
        PathTracker.addToModel(c.mkAnd(c.mkEq(var.z3var, value)));
    }

    static void encounteredNewBranch(MyVar condition, boolean value, int line_nr){
        // Call the solver
    }

    static void newSatisfiableInput(LinkedList<String> new_inputs) {
        // Hurray! found a new branch using these new inputs!
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
        while(!isFinished) {
            // Do things!
            try {
                System.out.println("Woohoo, looping!");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void output(String out){
        System.out.println(out);
    }

}