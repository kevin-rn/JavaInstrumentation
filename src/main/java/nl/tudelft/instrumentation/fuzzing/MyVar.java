package nl.tudelft.instrumentation.fuzzing;

import java.util.*;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;


/**
 * This class is used to wrap the primitive types and expressions
 * in an object. This way we can keep track of values and operators
 * that were used. It also makes it easier for us to compute the branch
 * distance.
 *
 * @author Clinton Cao, Sicco Verwer
 */
public class MyVar {

    public static final int K = 1;

    TypeEnum type = TypeEnum.UNKNOWN; // the type of the variable

    public boolean value = false; // the boolean value of the variable (if it's a boolean).
    public int int_value = 0; // the integer value of the variable (if it's an integer).
    public String str_value = ""; // the string value of the variable (if it's a string).

    public String operator = ""; // the operator of the expression variable (if it's an expression).
    public MyVar left = null; // the left-hand side value of the expression.
    public MyVar right = null; // the right-hand side value of the expression.

    /**
     * Constructor to wrap a boolean in a MyVar object.
     *
     * @param b the boolean value.
     */
    MyVar(boolean b) {
        this.type = TypeEnum.BOOL;
        this.value = b;
    }

    /**
     * Constructor to wrap an integer in a MyVar object.
     *
     * @param i the integer value.
     */
    MyVar(int i) {
        this.type = TypeEnum.INT;
        this.int_value = i;
    }

    /**
     * Constructor to wrap a string in a MyVar object.
     *
     * @param s the string value.
     */
    MyVar(String s) {
        this.type = TypeEnum.STRING;
        this.str_value = s;
    }

    /**
     * Constructor to wrap an unary expression in a MyVar object.
     *
     * @param l the value/variable of the unary expression
     * @param o the operator of the unary expression.
     */
    MyVar(MyVar l, String o) {
        this.type = TypeEnum.UNARY;
        this.left = l;
        this.operator = o;
    }

    /**
     * Constructor to wrap a binary expression in a MyVar object.
     *
     * @param l the left-hand side value/variable of the binary expression.
     * @param r the right-hand side value/variable of the binary expression.
     * @param o the operator of the binary expression.
     */
    MyVar(MyVar l, MyVar r, String o) {
        this.type = TypeEnum.BINARY;
        this.left = l;
        this.right = r;
        this.operator = o;
    }


    public double branchDistance(boolean trigger) {
        double dist = 0;

        switch (this.type) {
            case BOOL:
                dist = value ? 0 : 1;
                break;
            case INT:
                dist = int_value;
                break;
            case STRING:
                //strings should be compared with equals or comapreTo
                dist = str_value.length();
                break;
            case UNARY:
                if (operator.equals("!")) {
                    dist = 1 - left.branchDistance(trigger);
                }
                break;
            case BINARY:
                switch (operator) {
                    case "==":
                        dist = Math.abs(left.branchDistance(trigger) - right.branchDistance(trigger));
                        break;
                    case "!=":
                        dist = left.branchDistance(trigger) == right.branchDistance(trigger) ? 0 : 1;
                        break;
                    case "<": {
                        double diff = left.branchDistance(trigger) - right.branchDistance(trigger);
                        dist = diff < 0 ? K - diff : 0;
                        break;
                    }
                    case "<=": {
                        double diff = left.branchDistance(trigger) - right.branchDistance(trigger);
                        dist = diff <= 0 ? -diff : 0;
                        break;
                    }
                    case ">": {
                        double diff = left.branchDistance(trigger) - right.branchDistance(trigger);
                        dist = diff > 0 ? K - diff : 0;
                        break;
                    }
                    case ">=": {
                        double diff = left.branchDistance(trigger) - right.branchDistance(trigger);
                        dist = Math.max(diff, 0);
                        break;
                    }
                    case "&":
                    case "&&":
                        dist = left.branchDistance(trigger) + right.branchDistance(trigger);
                        break;
                    case "|":
                    case "||":
                        dist = Math.min(left.branchDistance(trigger), right.branchDistance(trigger));
                        break;
                    case "^":
                        double d1 = left.branchDistance(trigger) + (1 - right.branchDistance(trigger));
                        double d2 = right.branchDistance(trigger) + (1 - left.branchDistance(trigger));
                        dist = Math.min(d1, d2);
                        break;
                }
                break;
            default:
                break;
        }
        
        return trigger ? normalizeDistance(dist) : 1 - normalizeDistance(dist);
    }

    static double normalizeDistance(double d) {
        return d / (d + 1);
    }


    static int editDistDP(String str1, String str2, int m, int n) {
        int table[][] = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // If first string is empty
                if (i == 0) {
                    table[i][j] = j;
                }
                // If second string is empty
                else if (j == 0) {
                    table[i][j] = i;
                }
                // If last characters are same
                else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    table[i][j] = table[i - 1][j - 1];
                }
                // If the last character is different
                else {
                    table[i][j] = 1 + Math.min(table[i][j - 1],
                        Math.min(table[i - 1][j],
                            table[i - 1][j - 1]));
                }
            }
        }
        return table[m][n];
    }


    /**
     * Generate the string representation of a MyVar object.
     *
     * @return the MyVar object represented as MyVar object.
     */
    public String toString() {
        switch (this.type) {
            case BOOL:
                return "(" + value + ")";
            case INT:
                return "(" + int_value + ")";
            case STRING:
                return "(" + str_value + ")";
            case UNARY:
                return "(" + operator + left.toString() + ")";
            case BINARY:
                return "(" + left.toString() + " " + operator + " " + right.toString() + ")";
            default:
                return "";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MyVar)) {
            return false;
        }
        MyVar myVar = (MyVar) o;
        return value == myVar.value && int_value == myVar.int_value && type == myVar.type && Objects.equals(str_value, myVar.str_value) && Objects.equals(operator, myVar.operator) && Objects.equals(left, myVar.left) && Objects.equals(right, myVar.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value, int_value, str_value, operator, left, right);
    }
}