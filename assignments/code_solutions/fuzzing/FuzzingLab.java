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
        static List<List<String>> tracesQ;
        static List<String> bestTrace;
        static double smallestDistance;
        static TreeMap<Double, List<String>> traceDistances;
        static Set<String> uniqueBranches;
        static Set<String> uniqueErrors;
        static long startTime;
        static long RUN_TIME = 900000;
        static ArrayList<Branch> visitedBranches;
        static Set<String> uniqueBranchesVisitedInRun;
        static Random random;

        static void initialize(String[] inputSymbols){
                // Initialise a random trace from the input symbols of the problem.
                currentTrace = generateRandomTrace(inputSymbols);
                tracesQ = new ArrayList<>();
                bestTrace = currentTrace;
                smallestDistance = Double.MAX_VALUE;
                traceDistances = new TreeMap<>();
                uniqueBranches = new HashSet<>();
                uniqueErrors = new HashSet<>();
                startTime = System.currentTimeMillis();
                visitedBranches = new ArrayList<>();
                uniqueBranchesVisitedInRun = new HashSet<>();
                random = new Random();
        }

        /**
         * Write your solution that specifies what should happen when a new branch has been found.
         */
        static void encounteredNewBranch(MyVar condition, boolean value, int line_nr) {
                System.out.println("Got to branch " + line_nr + "_" + value);
                String branchID = line_nr + "_" + value;
                uniqueBranches.add(branchID);
                uniqueBranchesVisitedInRun.add(branchID);
                visitedBranches.add(new Branch(condition,line_nr + "_" + value));
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
                smallestDistance = computeSumBranchDistances(visitedBranches) / (double) uniqueBranchesVisitedInRun.size();
                traceDistances.put(smallestDistance, currentTrace);

                // Place here your code to guide your fuzzer with its search.
                while(System.currentTimeMillis() - startTime < RUN_TIME) {
                        visitedBranches.clear();
                        uniqueBranchesVisitedInRun.clear();

                        currentTrace = getNextTrace();
                        DistanceTracker.runNextFuzzedSequence(currentTrace.toArray(new String[0]));
                        double distance = computeSumBranchDistances(visitedBranches) / (double) uniqueBranchesVisitedInRun.size();
                        traceDistances.put(distance, currentTrace);

                        if (distance < smallestDistance) {
                                smallestDistance = distance;
                                bestTrace = currentTrace;
                        }

                        for (int i =0; i < 10; i++) {
                                tracesQ.add(generateNewTrace());
                        }

                }

                System.out.println("Number of unique branches after 15 minutes: " + uniqueBranches.size());
                System.out.println("Number of unique error codes after 15 minutes: " + uniqueErrors.size());
                System.out.println("Unique errors triggered: " + uniqueErrors.toString());
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
                        uniqueErrors.add(out);
                }
        }

        public static double computeSumBranchDistances(ArrayList<Branch> branches){
                double sum = 0.0;
                for (Branch b : branches) {
                        sum += b.computeOppositeBranchDistance();
                }

                return sum;
        }

        public static List<String> getNextTrace() {
                if (!tracesQ.isEmpty()) {
                        return tracesQ.remove(0);
                }
                else {
                        return generateRandomTrace(DistanceTracker.inputSymbols);
                }
        }

        public static List<String> generateNewTrace() {
                List<String> newTrace = new ArrayList<>(currentTrace);

                if (random.nextDouble() >= 0.75) {
                        return generateRandomTrace(DistanceTracker.inputSymbols);
                }

                if (random.nextDouble() >= 0.4) {
                        newTrace.remove(random.nextInt(newTrace.size()));
                }

                if (random.nextDouble() >= 0.5) {
                        newTrace.add(newTrace.get(random.nextInt(newTrace.size())));
                }

                if (random.nextDouble() >= 0.2) {
                        for (int i = 0; i < 5; i++) {
                                int index = random.nextInt(newTrace.size());
                                int symbol = newTrace.get(index).charAt(0);
                                String newSymbol =  "" + (symbol + random.nextInt(15) + 1);
                                newTrace.set(index, newSymbol);
                        }
                }

                return newTrace;
        }

}
