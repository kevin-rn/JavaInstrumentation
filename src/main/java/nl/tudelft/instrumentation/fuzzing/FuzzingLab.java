package nl.tudelft.instrumentation.fuzzing;

import java.util.*;


/**
 * You should write your own solution using this class.
 */
public class FuzzingLab {
    static Random r = new Random();
    static List<String> currentTrace;
    static int traceLength = 10;
    static boolean isFinished = false;
    static final double K = 1.0;

    static Map<Integer, Map<MyVar, Boolean>> visited = new HashMap<>();
    static double totalDistance = 0.0;
    static double bestDistance = Double.MAX_VALUE;

    static Set<String> output = new HashSet<>();
    static Set<String> traces = new HashSet<>();


    static void initialize(String[] inputSymbols) {
        // Initialise a random trace from the input symbols of the problem.
        currentTrace = generateRandomTrace(inputSymbols);
    }

    /**
     * Method for handling what to do when a new branch has been found.
     * @param condition Representation of a (near) binary tree of MyVar variables. 
     * @param value Boolean value of the condition.
     * @param line_nr Line number of the if-statement in the original Java file
     */
    static void encounteredNewBranch(MyVar condition, boolean value, int line_nr) {
        //System.out.println("CONDITION " + condition + "Value: " + value + " line nr : " + line_nr);

        if (visited.containsKey(line_nr)) {
            Map<MyVar, Boolean> branches = visited.get(line_nr);
            branches.put(condition, value);

            //System.out.println("encountered " + branches);
        } else {
            Map<MyVar, Boolean> branches = new HashMap<>();
            branches.put(condition, value);
            visited.put(line_nr, branches);
        }
    }

      /**
         * Method for calculating distance of the branch condition.
         * @param condition Branch condition as MyVar type.
         * @return Branch condition distance.
         */
        static double branchDistance(MyVar condition) {
            switch(condition.type) {
                    case BOOL:
                            return condition.value ? 0.0 : 1.0;
                    case INT:
                            return condition.int_value;
                    case STRING:
                            throw new RuntimeException("Error: Single string is an invalid branch condition");
                    case UNARY:
                            if (condition.operator.equals("!")) {
                                    return 1.0 - normalizeDistance(branchDistance(condition.left));
                            } else {
                                    System.out.println("Error: Encountered problem with Unary operator " + condition.operator);
                                    return -1.0;
                            }
                    case BINARY:
                            return binaryDistance(condition, condition.left, condition.right);
                    default:
                            System.out.println("Error: Encountered unexpected Branch condition type " + condition.type);
                            throw new RuntimeException("Error: Encountered unexpected Branch condition type " + condition.type);
            }
    }

    /**
     * Helper method for calculating the binary type branch conditions.
     * @param condition Branch condition as MyVar type.
     * @param left Left-hand condition of the conditon.
     * @param right Right-hand condition of the conditon.
     * @return Branch condition distance.
     */
    static double binaryDistance(MyVar condition, MyVar left, MyVar right) {
            switch(condition.operator){
                    case "|":
                    case "||": 
                            return normalizeDistance(branchDistance(left)) + normalizeDistance(branchDistance(right));
                    case "&":
                    case "&&":
                            return Math.min(normalizeDistance(branchDistance(left)), normalizeDistance(branchDistance(right)));
                    case "==": 
                        switch (left.type) {
                                case INT:
                                        return Math.abs(left.int_value - right.int_value);
                                case BOOL:
                                        return (left.value == right.value) ? 0.0 : 1.0;
                                case STRING:
                                        return editDistDP(left.str_value, right.str_value);
                                default:
                                        throw new RuntimeException("Error: binary condition == encountered incompatible type: " + condition);
                        }
                    case "!=": 
                        switch (left.type) {
                                case INT:
                                        return (left.int_value != right.int_value) ? 0.0 : 1.0;
                                case BOOL:
                                        return (left.value != right.value) ? 0.0 : 1.0;
                                case STRING:
                                        return (left.str_value != right.str_value) ? 0.0 : 1.0;
                                default:
                                        throw new RuntimeException("Error: binary condition != encountered incompatible type: " + condition);
                        }
                    case "<": 
                        switch (left.type) {
                                case INT:
                                        return (left.int_value < right.int_value) ? 0.0 : left.int_value - right.int_value + K;
                                default:
                                        throw new RuntimeException("Error: binary condition < encountered incompatible type: " + condition);
                        }
                    case "<=": 
                        switch (left.type) {
                                case INT:
                                        return (left.int_value <= right.int_value) ? 0.0 : left.int_value - right.int_value;
                                default:
                                        throw new RuntimeException("Error: binary condition <= encountered incompatible type: " + condition);
                        }
                    case ">":
                        switch (left.type) {
                                case INT:
                                        return (left.int_value > right.int_value) ? 0.0 : right.int_value - left.int_value + K;
                                default:
                                        throw new RuntimeException("Error: binary condition > encountered incompatible type: " + condition);
                        }
                    case ">=":
                        switch (left.type) {
                                case INT:
                                        return (left.int_value >= right.int_value) ? 0.0 : right.int_value - left.int_value;
                                default:
                                        throw new RuntimeException("Error: binary condition >= encountered incompatible type: " + condition);
                        }
                    case "^":
                    case "XOR":
                            double lefthand = normalizeDistance(branchDistance(left)) + normalizeDistance(branchDistance(new MyVar(right, "!")));
                            double righthand = normalizeDistance(branchDistance(right)) + normalizeDistance(branchDistance(new MyVar(left, "!")));
                            return Math.min(lefthand, righthand);
                    default:
                            System.err.println("Error: Encountered unexpected binary branch condition type " + condition.operator);
                            throw new RuntimeException("Error: Encountered unexpected binary branch operator: " + condition.operator);
            }
    }

    /**
     * Normalize the distances of individual predicates before combining them.
     * @param d the individual predicate.
     * @return Normalized predicate.
     */
    static double normalizeDistance(double d) {
            return d / (d + 1);
    }

    /**
     * Compute the edit distance between two strings according to the Levenshtein Distance metric.
     * @param str1 First string to compute from.
     * @param str2 Second string to compute from.
     * @return Distance between two strings.
     */
    static int editDistDP(String str1, String str2){
            int m = str1.length(), n = str2.length();
            int[][] table = new int[m + 1][n + 1];

            for (int i = 0; i <= m; i++) {
                    for (int j = 0; j <= n; j++) {
                            // If first string is empty
                            if (i == 0) { 
                                    table[i][j] = j;
                            // If second string is empty
                            }else if (j == 0) {
                                    table[i][j] = i;
                            // If last characters are same
                            }else if ((int) str1.charAt(i - 1) == (int) str2.charAt(j - 1)){
                                    table[i][j] = table[i - 1][j - 1];
                            // If last character is different
                            } else {
                                    table[i][j] = 1 + Math.min(Math.min(table[i][j - 1],    // cost of adding a character
                                                            table[i - 1][j]),               // cost of removing a character
                                                            table[i - 1][j - 1]);           // cost of replacing a character
                            }
                    }
            }
            return table[m][n];
    }

    /**
     * Method for fuzzing new inputs for a program.
     *
     * @param inputSymbols the inputSymbols to fuzz from.
     * @return a fuzzed sequence
     */
    static List<String> fuzz(String[] inputSymbols) throws InterruptedException {
        List<String> currentBestTrace = new ArrayList<>(currentTrace);
        double currentBestDistance = bestDistance;
        boolean betterTraceFound = true;
        int maxIter = 100;

        while (betterTraceFound) {

            if (maxIter-- <= 0) {
                break;
            }

            betterTraceFound = false;

            //mutate trace
            for (int i = 0; i < traceLength; i++) {
                List<String> newTrace = new ArrayList<>(currentBestTrace);
                newTrace.set(i, inputSymbols[r.nextInt(inputSymbols.length)]);
                String traceString = String.join("", newTrace);

                if (traces.contains(traceString)) {
                    continue;
                }

                traces.add(traceString);


                double newDistance = 0;

                for (Map<MyVar, Boolean> branches : visited.values()) {
                    // newDistance = branches.keySet().stream().map(MyVar::branchDistance).reduce(0., Double::sum);
                    newDistance = branches.keySet().stream().map(branch -> branchDistance(branch)).reduce(0., Double::sum);

                }

                if (newDistance < currentBestDistance) {
                    currentBestTrace = newTrace;
                    currentBestDistance = newDistance;
                    betterTraceFound = true;
                    break;
                }
            }
        }


        traces.add(currentBestTrace.toString());
        System.out.println("TRACE" + traces);
        // Update the current trace and total distance
        currentTrace = currentBestTrace;
        bestDistance = currentBestDistance;
        return currentTrace;
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
        initialize(DistanceTracker.inputSymbols);
        DistanceTracker.runNextFuzzedSequence(currentTrace.toArray(new String[0]));

        while (!isFinished) {
            try {
                visited.clear();
                DistanceTracker.runNextFuzzedSequence(fuzz(DistanceTracker.inputSymbols).toArray(new String[0]));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method that is used for catching the output from standard out.
     * You should write your own logic here.
     *
     * @param out the string that has been outputted in the standard out.
     */
    public static void output(String out) {
        if (out.contains("Current state has no transition for this input!")) {
            initialize(DistanceTracker.inputSymbols);
        }

        output.add(out);
        System.out.println("OUTPUT " + output);
    }
}
