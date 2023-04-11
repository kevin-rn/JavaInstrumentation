package nl.tudelft.instrumentation.learning;

import java.security.Principal;
import java.util.*;

/**
 * You should write your own solution using this class.
 */
public class LearningLab {
    static Random r = new Random();
    static int traceLength = 10;
    static boolean isFinished = false;

    static ObservationTable observationTable;
    static EquivalenceChecker equivalenceChecker;

    static void run() {
        SystemUnderLearn sul = new RersSUL();
        observationTable = new ObservationTable(LearningTracker.inputSymbols, sul);
        //equivalenceChecker = new RandomWalkEquivalenceChecker(sul, LearningTracker.inputSymbols, 100, 1000);
        equivalenceChecker = new WMethodEquivalenceChecker(sul, LearningTracker.inputSymbols, 4, observationTable, observationTable);
        observationTable.print();
        MealyMachine hypothesis = observationTable.generateHypothesis();


        long startTime = System.currentTimeMillis();
        long elapsedTime = 0;
        // Place here your code to learn a model of the RERS problem.
        // Implement the checks for consistent and closed in the observation table.
        // Use the observation table and the equivalence checker to implement the L* learning algorithm.
        while (!isFinished && elapsedTime < 30000) {
            elapsedTime = System.currentTimeMillis() - startTime;

            Optional<Word<String>> isNonClosed = observationTable.checkForClosed();
            Optional<Word<String>> isInconsistent = observationTable.checkForConsistent();


            // Add as row
            isNonClosed.ifPresent(stringWord -> observationTable.addToS(stringWord));
            // Add as column
            isInconsistent.ifPresent(stringWord -> observationTable.addToE(stringWord));

            if (!isNonClosed.isPresent() && !isInconsistent.isPresent()) {
                hypothesis = observationTable.generateHypothesis();
                Optional<Word<String>> counterexample = equivalenceChecker.verify(hypothesis);
                if (counterexample.isPresent()) {
                    // Process counterexample to get both the input and output
                    Word<String> input = counterexample.get();
                    String output = sul.getLastOutput(input);

                    if (output.equals("invalid")) {
                        continue;
                    }

                    observationTable.addToS(input);
                    observationTable.addToE(new Word<>(output));
                } else {
                    // If no counterexample can be found, then we are done learning.
                    isFinished = true;
                    break;
                }
            }

        }

        System.out.println("Total amount of states: " + hypothesis.getStates().length + ", membership queries: " + LearningTracker.query_count);
        // observationTable.print();
        hypothesis.writeToDot("hypothesis.dot");
        System.exit(-1);
    }


    /**
     * Method that is used for catching the output from standard out.
     *
     * @param out the string that has been outputted in the standard out.
     */
    public static void output(String out) {
        // System.out.println(out);
    }
}
