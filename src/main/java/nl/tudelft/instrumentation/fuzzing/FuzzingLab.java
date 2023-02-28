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

    static double newDistance = Double.MAX_VALUE;

    static Map<Integer, Map<MyVar, Boolean>> visited = new HashMap<>();
    static double totalDistance = Double.MAX_VALUE;

    static Set<String> output = new HashSet<>();
    static Set<String> traces = new HashSet<>();

    static Set<Integer> conditionsNo = new HashSet<>();

    static Map<String, Integer> results = new HashMap<>();

    static int count = 300;


    static void initialize(String[] inputSymbols) {
        // Initialise a random trace from the input symbols of the problem.
        currentTrace = generateRandomTrace(inputSymbols);
    }

    /**
     * Method for handling what to do when a new branch has been found.
     *
     * @param condition Representation of a (near) binary tree of MyVar variables.
     * @param value     Boolean value of the condition.
     * @param line_nr   Line number of the if-statement in the original Java file
     */
    static void encounteredNewBranch(MyVar condition, boolean value, int line_nr) {
        //System.out.println("CONDITION " + condition + "Value: " + value + " line nr : " + line_nr);
        conditionsNo.add(line_nr);

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
     *
     * @param condition Branch condition as MyVar type.
     * @return Branch condition distance.
     */
    static double branchDistance(MyVar condition) {
        double dist = 0.0;
        switch (condition.type) {
            case BOOL:
                dist = condition.value ? 0.0 : 1.0;
                break;
            case INT:
                dist = condition.int_value;
                break;
            case STRING:
                throw new RuntimeException("Error: Single string is an invalid branch condition");
            case UNARY:
                if (condition.operator.equals("!")) {
                    dist = 1.0 - normalizeDistance(branchDistance(condition.left));
                } else {
                    System.out.println("Error: Encountered problem with Unary operator " + condition.operator);
                    dist = -1.0;
                }
                break;
            case BINARY:
                dist = binaryDistance(condition, condition.left, condition.right);
                break;
            default:
                System.out.println("Error: Encountered unexpected Branch condition type " + condition.type);
                throw new RuntimeException("Error: Encountered unexpected Branch condition type " + condition.type);
        }
        return condition.value ? normalizeDistance(dist) : 1 - normalizeDistance(dist);
    }

    /**
     * Helper method for calculating the binary type branch conditions.
     *
     * @param condition Branch condition as MyVar type.
     * @param left      Left-hand condition of the conditon.
     * @param right     Right-hand condition of the conditon.
     * @return Branch condition distance.
     */
    static double binaryDistance(MyVar condition, MyVar left, MyVar right) {
        double dist = 0.0;
        switch (condition.operator) {
            case "==":
                switch (left.type) {
                    case INT:
                        dist = Math.abs(branchDistance(left) - branchDistance(right));
                        break;
                    case BOOL:
                        dist = (branchDistance(left) == branchDistance(right)) ? 0.0 : 1.0;
                        break;
                    case STRING:
                        dist = editDistDP(left.str_value, right.str_value);
                        break;
                    default:
                        throw new RuntimeException("Error: binary condition == encountered incompatible type: " + condition);
                }
            case "!=":
                switch (left.type) {
                    case INT:
                    case BOOL:
                        dist = (branchDistance(left) != branchDistance(right)) ? 0.0 : 1.0;
                        break;
                    case STRING:
                        dist = (!left.str_value.equals(right.str_value)) ? 0.0 : 1.0;
                        break;
                    default:
                        throw new RuntimeException("Error: binary condition != encountered incompatible type: " + condition);
                }
            case "<":
                switch (left.type) {
                    case INT:
                        double l = branchDistance(left), r = branchDistance(right);
                        dist = (l < r) ? 0.0 : l - r + K;
                        break;
                    default:
                        throw new RuntimeException("Error: binary condition < encountered incompatible type: " + condition);
                }
            case "<=":
                switch (left.type) {
                    case INT:
                        double l = branchDistance(left), r = branchDistance(right);
                        dist = (l <= r) ? 0.0 : l - r;
                        break;
                    default:
                        throw new RuntimeException("Error: binary condition <= encountered incompatible type: " + condition);
                }
            case ">":
                switch (left.type) {
                    case INT:
                        double l = branchDistance(left), r = branchDistance(right);
                        dist = (l > r) ? 0.0 : r - l + K;
                        break;
                    default:
                        throw new RuntimeException("Error: binary condition > encountered incompatible type: " + condition);
                }
            case ">=":
                switch (left.type) {
                    case INT:
                        double l = branchDistance(left), r = branchDistance(right);
                        dist = (l >= r) ? 0.0 : r - l;
                        break;
                    default:
                        throw new RuntimeException("Error: binary condition >= encountered incompatible type: " + condition);
                }
            case "&":
            case "&&":
                dist = branchDistance(left) + branchDistance(right);
                break;
            case "|":
            case "||":
                dist = Math.min(branchDistance(left), branchDistance(right));
                break;
            case "^":
            case "XOR":
                double lefthand = branchDistance(left), righthand = branchDistance(right);
                dist = Math.min(lefthand + (1 - righthand), righthand + (1 - lefthand));
                break;
            default:
                System.err.println("Error: Encountered unexpected binary branch condition type " + condition.operator);
                throw new RuntimeException("Error: Encountered unexpected binary branch operator: " + condition.operator);
        }
        return normalizeDistance(dist);
    }

    /**
     * Normalize the distances of individual predicates before combining them.
     *
     * @param d the individual predicate.
     * @return Normalized predicate.
     */
    static double normalizeDistance(double d) {
        return d / (d + 1);
    }

    /**
     * Compute the edit distance between two strings according to the Levenshtein Distance metric.
     *
     * @param str1 First string to compute from.
     * @param str2 Second string to compute from.
     * @return Distance between two strings.
     */
    static int editDistDP(String str1, String str2) {
        int m = str1.length(), n = str2.length();
        int[][] table = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // If first string is empty
                if (i == 0) {
                    table[i][j] = j;
                    // If second string is empty
                } else if (j == 0) {
                    table[i][j] = i;
                    // If last characters are same
                } else if ((int) str1.charAt(i - 1) == (int) str2.charAt(j - 1)) {
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
        List<String> newTrace = null;
        newDistance = 0;

        for (Map<MyVar, Boolean> branches : visited.values()) {
            // newDistance += branches.keySet().stream().map(MyVar::branchDistance).reduce(0., Double::sum);
            newDistance += branches.keySet().stream().map(x-> x.branchDistance(x.value)).reduce(0., Double::sum);

        }

        if (newDistance < totalDistance && visited.size() > 0) {

            newTrace = new ArrayList<>(currentTrace);
            newTrace.set(r.nextInt(newTrace.size()), inputSymbols[r.nextInt(inputSymbols.length)]);

            totalDistance = newDistance;
        }

        return newTrace;
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

        while (!isFinished && count-- > 0) {
            try {
                List<String> currFuzz = fuzz(DistanceTracker.inputSymbols);

                if (currFuzz == null) {
                    initialize(DistanceTracker.inputSymbols);
                    DistanceTracker.runNextFuzzedSequence(currentTrace.toArray(new String[0]));
                } else {
                    DistanceTracker.runNextFuzzedSequence(currFuzz.toArray(new String[0]));
                }

                results.put(String.join("", currentTrace), visited.size());

                visited.clear();

                if (count % 1000 == 0) {
                    System.out.println(count);
                }

                //System.out.println("Woohoo, looping!");
                //Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        Optional<Map.Entry<String, Integer>> maxEntry = results.entrySet()
            .stream()
            .max(Map.Entry.comparingByValue());

        System.out.println(" result final ");
        System.out.println(maxEntry);

        System.out.println("---------");
        System.out.println(output + " SIZE " + output.size());

        System.out.println("-------------- Branch");
        System.out.println(conditionsNo.size());
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

        if (out.contains("error")) {
            output.add(out);
        }
        //System.out.println("OUTPUT " + output);
    }
}
