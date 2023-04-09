package nl.tudelft.instrumentation.learning;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

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

    private static void generateInputSequences(String[] inputs, int W, int index, String current, List<Word<String>> inputSequences) {
        // If sequence length reached, add current permutation to the list.
        if (current.length() == W) {
            Word<String> newInput = new Word<>(current.split(""));
            inputSequences.add(newInput);
            return;
        }
        // If index longer than input symbols return.
        if (index == inputs.length) {
            return;
        }

        // Exclude current input to be added to current
        generateInputSequences(inputs, W, index + 1, current, inputSequences);

        // Include current input to be added to current
        generateInputSequences(inputs, W, index + 1, current + inputs[index], inputSequences);

    }

    @Override
    public Optional<Word<String>> verify(MealyMachine hypothesis) {
        List<Word<String>> accessSequences = accessSequenceGenerator.getAccessSequences();
        List<Word<String>> distinguishingSequences = distinguishingSequenceGenerator.getDistinguishingSequences();

        // Generate all possible combinations of length w from the inputsymbols.
        List<Word<String>> inputSequences = new ArrayList<>();
        generateInputSequences(inputSymbols, w, 0, "", inputSequences);

        // Iterate over all access sequences
        for (Word<String> accessSequence : accessSequences) {

            // Iterate over all input sequences of length w
            for(Word<String> input : inputSequences ) {
                Word<String> temp = accessSequence.append(input);

                // Iterate over all distinguishing sequences
                for (Word<String> distinguishingSequence : distinguishingSequences) {
                    // Build the test input
                    Word<String> testInput = temp.append(distinguishingSequence);

                    // For the test input, generate model and real output
                    String[] modelOutput = hypothesis.getOutput(testInput);
                    String[] realOutput = sul.getOutput(testInput);

                    // If these are not equal a counterexample has been found and returned.
                    if(!modelOutput.equals(realOutput)) {
                        return Optional.of(testInput);
                    }
                }
            }
        }

        // no counterexample found
        return Optional.empty();
    }

}
