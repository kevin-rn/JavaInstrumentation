package nl.tudelft.instrumentation.learning;

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
        equivalenceChecker = new RandomWalkEquivalenceChecker(sul, LearningTracker.inputSymbols, 100, 1000);
        // equivalenceChecker = new WMethodEquivalenceChecker(sul, LearningTracker.inputSymbols, 1, observationTable, observationTable);
        observationTable.print();
        MealyMachine hypothesis = observationTable.generateHypothesis();;

        // Place here your code to learn a model of the RERS problem.
        // Implement the checks for consistent and closed in the observation table.
        // Use the observation table and the equivalence checker to implement the L* learning algorithm.
        while (!isFinished) {
            Optional<Word<String>> isClosed = observationTable.checkForClosed();
            Optional<Word<String>> isConsistent = observationTable.checkForConsistent();

            if(!isClosed.isPresent()) {
                // Add as row
                observationTable.addToS(isClosed.get());
            } else if (!isConsistent.isPresent()) {
                // Add as column
                observationTable.addToE(isConsistent.get());
            } else {
                observationTable.print();
                hypothesis = observationTable.generateHypothesis();
                Optional<Word<String>> counterexample = equivalenceChecker.verify(hypothesis);
                if(counterexample.isPresent()) {
                    // Process counterexample to get both the input and output
                    Word<String> input = counterexample.get();
                    String output = sul.getLastOutput(input);

                    // TODO: Not sure if I should add this like this
                    Word<String> combine = input.append(output);
                    observationTable.addToS(combine);
                } else {
                    // If no counterexample can be found, then we are done learning.
                    isFinished = true;
                }
            }
        }
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
