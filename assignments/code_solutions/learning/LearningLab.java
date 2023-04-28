package nl.tudelft.instrumentation.learning;

import java.util.*;

/**
 * You should write your own solution using this class.
 */
public class LearningLab {
    static Random r = new Random();
    static int w = 3;
    static boolean isFinished = false;

    static ObservationTable observationTable;
    static EquivalenceChecker equivalenceChecker;

    static void run() {

        // LearnLibRunner llr = new LearnLibRunner();
        // llr.start(w);
        // System.exit(0);

        SystemUnderLearn sul = new RersSUL();
        long start = System.currentTimeMillis();
        observationTable = new ObservationTable(LearningTracker.inputSymbols, sul);
        // equivalenceChecker = new RandomWalkEquivalenceChecker(sul, LearningTracker.inputSymbols, w, 1000);
        equivalenceChecker = new WMethodEquivalenceChecker(sul,LearningTracker.inputSymbols, w, observationTable, observationTable);

        observationTable.print();
        MealyMachine hypothesis = observationTable.generateHypothesis();
        hypothesis.writeToDot("hypothesis.dot");
        // Place here your code to learn a model of the RERS problem.
        // Implement the checks for consistent and closed in the observation table.
        // Use the observation table and the equivalence checker to implement the L*
        // learning algorithm.
        while (true) {
            hypothesis = observationTable.generateHypothesis();
            // hypothesis.writeToDot("hypothesis.dot");
            
            Optional<Word<String>> inconsistent = observationTable.checkForConsistent();
            if (inconsistent.isPresent()) {
                System.out.println("not consistent: " + inconsistent.get());
                observationTable.addToE(inconsistent.get());
                continue;
            }
            
            Optional<Word<String>> notClosed = observationTable.checkForClosed();
            if (notClosed.isPresent()) { 
                System.out.println("not closed: " + notClosed.get());
                observationTable.addToS(notClosed.get());
                continue;
            }

            hypothesis.writeToDot("hypothesis.dot");
            // System.out.println("checking for equivalence");
            Optional<Word<String>> counter = equivalenceChecker.verify(hypothesis);
            if (counter.isPresent()) {
                List<String> c = counter.get().asList();
                System.out.printf("Counterexample: %s\n", String.join(",", c));
                for (int i = 0; i < c.size(); i++) {
                    observationTable.addToS(new Word<String>(c.subList(0, i + 1)));
                }
            } else {
                System.out.println("Done learning, no counterexample found");
                break;
            }
          
        }
        
        System.out.printf("With w: %d, found %d states in %dms with %d membership queries\n", w, hypothesis.getStates().length, (System.currentTimeMillis()-start), LearningTracker.membershipQueries);
        System.exit(0);
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
