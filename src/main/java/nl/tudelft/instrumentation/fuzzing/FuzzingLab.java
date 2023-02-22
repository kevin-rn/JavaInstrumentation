package nl.tudelft.instrumentation.fuzzing;

import java.util.*;
import java.util.Random;

/**
 * You should write your own solution using this class.
 */
public class FuzzingLab {
        static Random r = new Random();
        static List<String> currentTrace;
        static int traceLength = 10;
        static boolean isFinished = false;

        // Keep track of visited branches and total distance
        static HashSet<Integer> visited = new HashSet<>();
        static int totalDistance = 0;

        static void initialize(String[] inputSymbols){
                // Initialise a random trace from the input symbols of the problem.
                currentTrace = generateRandomTrace(inputSymbols);
        }

        /**
         * Write your solution that specifies what should happen when a new branch has been found.
         */
        static void encounteredNewBranch(MyVar condition, boolean value, int line_nr) {
                // do something useful

                int distanceBranch = distanceCounter(condition, value);
                visited.add(line_nr);
                System.out.println(distanceBranch);
                totalDistance += distanceBranch;

        }

        static int distanceCounter(MyVar condition, boolean value) {
                int count = 0;

                switch(condition.type) {
                        case BOOL:
                                count = value ? 0 : 1;
                                break;
                        case INT:
                                count = condition.int_value;
                                break;
                        // case STRING:
                        //         String strCondition = condition.str_value;
                                

                        //         break;
                        case UNARY:
                                int tempDist = distanceCounter(condition.left, value);
                                count = unaryDistance(condition, tempDist);
                                break;
                        // case BINARY:
                                
                        default:
                                System.out.printf("Error: Encountered unexpected Branch condition type %s%n", condition.type);
                               
                }

                return count;
        }

        // Handle ! operator
        static int unaryDistance(MyVar condition, int dist) {
                if (condition.operator.equals('!')) {
                        return normalizeDistance(1 - dist)
                } else {
                        return Integer.MIN_VALUE;
                }
        }

        /**
         * Normalize the distances of individual predicates before combining them.
         * @param d the individual predicate.
         * @return Normalized predicate.
         */
        static int normalizeDistance(int d) {
                return d/(d+1);
        }

        // Levenshtein distance (slide 57 lecture 2)
        static float editDistDP(String str1, String str2){
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
         * @param inputSymbols the inputSymbols to fuzz from.
         * @return a fuzzed sequence
         */
        static List<String> fuzz(String[] inputSymbols){
                /*
                 * Add here your code for fuzzing a new sequence for the RERS problem.
                 * You can guide your fuzzer to fuzz "smart" input sequences to cover
                 * more branches. Right now we just generate a complete random sequence
                 * using the given input symbols. Please change it to your own code.
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
                initialize(DistanceTracker.inputSymbols);
                DistanceTracker.runNextFuzzedSequence(currentTrace.toArray(new String[0]));

                // Place here your code to guide your fuzzer with its search.
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

        /**
         * Method that is used for catching the output from standard out.
         * You should write your own logic here.
         * @param out the string that has been outputted in the standard out.
         */
        public static void output(String out){
                System.out.println(out);
        }
}
