package nl.tudelft.instrumentation.learning;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

public class WMethodEquivalenceChecker extends EquivalenceChecker{

    private int w;
    private AccessSequenceGenerator accessSequenceGenerator;
    private DistinguishingSequenceGenerator distinguishingSequenceGenerator;

    public WMethodEquivalenceChecker(SystemUnderLearn sul, String[] inputSymbols, int w, DistinguishingSequenceGenerator dg, AccessSequenceGenerator ag) {
        super(sul, inputSymbols);
        this.w = w;
        this.distinguishingSequenceGenerator= dg;
        this.accessSequenceGenerator= ag;
    }

    @Override
    public Optional<Word<String>> verify(MealyMachine hypothesis) {
        List<Word<String>> accessSequences = accessSequenceGenerator.getAccessSequences();
        List<Word<String>> distinguishingSequences = distinguishingSequenceGenerator.getDistinguishingSequences();

        // Iterate over all access sequences
        for (Word<String> accessSequence : accessSequences) {
            // Iterate over all distinguishing sequences
            for (Word<String> distinguishingSequence : distinguishingSequences) {
                // Build the test input
                Word<String> testInput = accessSequence.append(distinguishingSequence);

                // For the test input, generate model and real output
                String[] modelOutput = hypothesis.getOutput(testInput);
                String[] realOutput = sul.getOutput(testInput);

                for (int i = 0; i < w; i++) {
                    // If the model output does not match the real output
                    if (!modelOutput[i].equals(realOutput[i])) {
                        // Construct counterexample
                        return Optional.of(testInput);
                    }
                }
            }
        }

        // no counterexample found
        return Optional.empty();
    }

}
