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
    static final int K = 1;

    static Map<Integer, Map<MyVar, Boolean>> visited = new HashMap<>();
    static double totalDistance = Double.MAX_VALUE;

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
         * Helper method for calculating distance of the branch condition.
         * @param condition Branch condition as MyVar type.
         * @param value Boolean value of the branch condition.
         * @param str String value of the branch condition.
         * @return Branch condition distance.
         */
        static int distanceCounter(MyVar condition, boolean value, String str) {
            int dist = 0;
            switch(condition.type) {
                    case BOOL:
                            dist = value ? 0 : 1;
                            break;
                    case INT:
                            dist = condition.int_value;
                            break;
                    case STRING:
                            str = condition.str_value;
                            break;
                    case UNARY:
                            if (condition.operator.equals("!")) {
                                    dist = 1 - distanceCounter(condition.left, condition.left.value, "");
                            } else {
                                    dist = Integer.MIN_VALUE;
                                    System.out.println("Error: Encountered problem with Unary operator " + condition.operator);
                            }
                            break;
                    case BINARY:
                            String str1 = "", str2 = "";
                            int expr1 = distanceCounter(condition.left, condition.left.value, str1);
                            int expr2 = distanceCounter(condition.right, condition.right.value, str2);
                            dist = binaryDistance(condition, expr1, expr2, str1, str2);
                            break;
                    default:
                            dist =  Integer.MIN_VALUE;
                            System.out.println("Error: Encountered unexpected Branch condition type " + condition.type);
                            break;

            }
            return normalizeDistance(dist);
    }

    /**
     * Method for calculating the binary type branch conditions.
     * @param condition Branch condition as MyVar type.
     * @param expr1 Left-hand expression from the operator of the conditon.
     * @param expr2 Right-hand expression from the operator of the conditon.
     * @param str1 Left-hand string expression.
     * @param str2 Right-hand string expression.
     * @return Branch condition distance.
     */
    static int binaryDistance(MyVar condition, int expr1, int expr2, String str1, String str2) {
            int dist = 0;
            switch(condition.operator){
                    case "|":
                    case "||": 
                            dist = Math.min(expr1, expr2);
                            break;
                    case "&":
                    case "&&":
                            dist = expr1 + expr2;
                            break;
                    case "==": 
                            if(condition.type == TypeEnum.STRING || !str1.isEmpty() || !str2.isEmpty()) {
                                    dist = editDistDP(str1, str2, str1.length(), str2.length());
                            } else {
                                    dist = Math.abs(expr1 - expr2);
                            }
                            break;
                    case "!=": 
                            if(condition.type == TypeEnum.STRING) {
                                    dist = !str1.equals(str2) ? 0 : 1;
                            } else {
                                    dist = (expr1 != expr2) ? 0 : 1;
                            }
                            break;
                    case "<": 
                            dist = (expr1 < expr2) ? 0 : expr1 - expr2 + K;
                            break;
                    case "<=": 
                            dist = (expr1 <= expr2) ? 0 : expr1 - expr2;
                            break;
                    case ">":
                            dist = (expr1 > expr2) ? 0 : expr2 - expr1 + K;
                            break;
                    case ">=":
                            dist = (expr1 > expr2) ? 0 : expr2 - expr1;
                            break;
                    default:
                            dist = Integer.MIN_VALUE;
                            System.err.println("Error: Encountered unexpected binary branch condition type " + condition.operator);
                            break;
            }
            return normalizeDistance(dist);
    }

    /**
     * Normalize the distances of individual predicates before combining them.
     * @param d the individual predicate.
     * @return Normalized predicate.
     */
    static int normalizeDistance(int d) {
            return d/(d+1);
    }

    /**
     * Compute the edit distance between two strings according to the Levenshtein Distance metric.
     * @param str1 First string to compute from.
     * @param str2 Second string to compute from.
     * @return Distance between two strings.
     */
    static int editDistDP(String str1, String str2, int m, int n){
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
        double currentBestDistance = totalDistance;
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
                    newDistance = branches.keySet().stream().map(MyVar::branchDistance).reduce(0., Double::sum);
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
        totalDistance = currentBestDistance;
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
