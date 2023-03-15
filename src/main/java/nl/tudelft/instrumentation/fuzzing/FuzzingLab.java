package nl.tudelft.instrumentation.fuzzing;
import java.util.concurrent.TimeUnit;
import java.util.*;


/**
 * You should write your own solution using this class.
 */
public class FuzzingLab {
    static Random r = new Random();
    static List<String> currentTrace;
    static List<String> bestTrace;
    static int traceLength = 10;
    static boolean isFinished = false;
    static boolean hasBetterTrace = false;
    static final double K = 1.0;

    static double newDistance = 0.0;
    static int permutationTotal= 10;

    static List<List<String>> fuzz_traces = new ArrayList<>();
    static Set<String> visited = new HashSet<>();
    static Set<String> totalVisited = new HashSet<>();

    static double smallestNormDistance = Double.MAX_VALUE;
    static double totalDistance = Double.MAX_VALUE;
    static Set<String> output = new HashSet<>();

    static void initialize(String[] inputSymbols) {
        // Initialise a random trace from the input symbols of the problem.
        currentTrace = generateRandomTrace(inputSymbols);
        fuzz_traces.add(currentTrace);
    }

    /**
     * Method for handling what to do when a new branch has been found.
     *
     * @param condition Representation of a (near) binary tree of MyVar variables.
     * @param value     Boolean value of the condition.
     * @param line_nr   Line number of the if-statement in the original Java file
     */
    static void encounteredNewBranch(MyVar condition, boolean value, int line_nr) {

        // Check if line_nr already visited
        if (!visited.contains(line_nr + "-" + value)) {
            String branch = line_nr + "-" + value;
            visited.add(branch);
            totalVisited.add(branch);
            newDistance += value ? branchDistance(condition) : 1 - branchDistance(condition);
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
                dist = condition.str_value.length();
                break;
            case UNARY:
                if (condition.operator.equals("!") || condition.operator.equals("-")) {
                    dist = 1.0 - branchDistance(condition.left);
                }
                break;
            case BINARY:
                dist = binaryDistance(condition, condition.left, condition.right);
                break;
            default:
                System.out.println("Error: Encountered unexpected Branch condition type " + condition.type);
                throw new RuntimeException("Error: Encountered unexpected Branch condition type " + condition.type);
        }
        return normalizeDistance(dist);
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
                    case STRING:
                        dist = editDistDP(left.str_value, right.str_value);
                        break;
                    default:
                        dist = Math.abs(branchDistance(left) - branchDistance(right));
                        break;
                }
            case "!=":
                switch (left.type) {
                    case STRING:
                        dist = (!left.str_value.equals(right.str_value)) ? 0.0 : 1.0;
                        break;
                    default:
                        dist = (branchDistance(left) != branchDistance(right)) ? 0.0 : 1.0;
                        break;
                }
            case "<": {
                double l = branchDistance(left), r = branchDistance(right);
                dist = (l < r) ? 0.0 : l - r + K;
                break;
            }
            case "<=": {
                double l = branchDistance(left), r = branchDistance(right);
                dist = (l <= r) ? 0.0 : l - r;
                break;
            }
            case ">": {
                double l = branchDistance(left), r = branchDistance(right);
                dist = (l > r) ? 0.0 : r - l + K;
                break;
            }
            case ">=": {
                double l = branchDistance(left), r = branchDistance(right);
                dist = (l >= r) ? 0.0 : r - l;
                break;
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
        return dist;
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
     * Simple Hill-Climbing that mutates the new trace if branch distance is smaller (optimal) else generates random trace.
     *
     * @param inputSymbols the inputSymbols to fuzz from.
     * @return a fuzzed sequence
     */
    static List<String> fuzz(String[] inputSymbols, boolean hasBetterTrace) throws InterruptedException {
        if (hasBetterTrace) {
            // Generate multiple permutations of the current best trace.
            HashSet<List<String>> generated = new HashSet<>();
            while (generated.size() < permutationTotal) {

                // Copy best trace
                List<String> newTrace = new ArrayList<>(bestTrace);

                // Generate random symbol and random position
                String randomSymbol = inputSymbols[r.nextInt(inputSymbols.length)];
                int randomPosition = r.nextInt(newTrace.size());
                int randomNum = r.nextInt(3);

                // Perform random mutation: Replace, Addition or Deletion
                if(randomNum == 2) {
                    // Replace symbol at random position
                    newTrace.set(randomPosition, randomSymbol);
                } else if (randomNum == 1) {
                    // Add symbol at random position
                    newTrace.add(randomPosition, randomSymbol);
                } else {
                    // Delete symbol at random position
                    newTrace.remove(randomPosition);
                }

                // Add mutated trace to the list
                generated.add(newTrace);
            }
            // Update fuzzed traces
            fuzz_traces = new ArrayList<>(generated);
            return bestTrace;
        } else {
            return generateRandomTrace(inputSymbols);
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
        
        initialize(DistanceTracker.inputSymbols);
        List<List<String>> fuzz_traces = new ArrayList<>();
        
        // Run for 5 minutes.
        long timer = System.nanoTime();
        long elapse = TimeUnit.NANOSECONDS.convert(10L, TimeUnit.SECONDS);
        long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(5L, TimeUnit.MINUTES);

        while (!isFinished && System.nanoTime() < endTime) {
            try {
                if (!fuzz_traces.isEmpty()) {
                    currentTrace = fuzz_traces.remove(0);
                } else {
                    // currentTrace = fuzz(DistanceTracker.inputSymbols, false);           // Random Fuzzing
                    currentTrace = fuzz(DistanceTracker.inputSymbols, hasBetterTrace);     // Hill-Climbing
                    hasBetterTrace = false;
                }

                System.out.print("Trace: ");
                currentTrace.forEach(System.out::print);
                System.out.println("\n");

                DistanceTracker.runNextFuzzedSequence(currentTrace.toArray(new String[0]));

                System.out.println("Total visited branches: " + totalVisited.size() + "\nOutput: " + output + "\nOutput size: " + output.size());

                double newNormdistance = newDistance / (newDistance + visited.size());
                // Select the most optimal Trace that has a lower computed normalised sum of branches compared to previous best found Trace
                if (newNormdistance < smallestNormDistance) {
                    // Set new lower sum of distances to be the total
                    totalDistance = newDistance;
                    smallestNormDistance = newNormdistance;
                    // Keep track of best trace found so far
                    bestTrace = currentTrace;
                    hasBetterTrace = true;
                }

                // Print every x amount of time
                // if (timer + elapse <= System.nanoTime()) {
                //     timer += elapse;
                    // System.out.println("Total visited branches: " + totalVisited.size() + "\nOutput: " + output + "\nOutput size: " + output.size());
                // }

                // Reset values for next iteration
                visited.clear();
                newDistance = 0;

            //     System.out.println("Woohoo, looping!");
            //     Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Total visited branches: " + totalVisited.size() + "\nOutput: " + output + "\nOutput size: " + output.size());
        System.exit(-1);
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
