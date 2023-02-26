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

    static Map<Integer, Map<MyVar, Boolean>> visited = new HashMap<>();
    static double totalDistance = Double.MAX_VALUE;

    static Set<String> output = new HashSet<>();
    static Set<String> traces = new HashSet<>();


    static void initialize(String[] inputSymbols) {
        // Initialise a random trace from the input symbols of the problem.
        currentTrace = generateRandomTrace(inputSymbols);
    }

    /**
     * TODO: WRITE your solution that specifies what should happen when a new branch has been found.
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

        // TODO Place here your code to guide your fuzzer with its search.
        while (!isFinished) {
            try {
                visited.clear();
                DistanceTracker.runNextFuzzedSequence(fuzz(DistanceTracker.inputSymbols).toArray(new String[0]));
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
