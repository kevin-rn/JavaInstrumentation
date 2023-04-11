package nl.tudelft.instrumentation.learning;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class WMethodEquivalenceChecker extends EquivalenceChecker {

    private int w;
    private AccessSequenceGenerator accessSequenceGenerator;
    private DistinguishingSequenceGenerator distinguishingSequenceGenerator;

    public WMethodEquivalenceChecker(SystemUnderLearn sul, String[] inputSymbols, int w, DistinguishingSequenceGenerator dg, AccessSequenceGenerator ag) {
        super(sul, inputSymbols);
        this.w = w;
        this.distinguishingSequenceGenerator = dg;
        this.accessSequenceGenerator = ag;
    }

    private static void generateInputSequences(String[] inputs, int W, Word<String> current, List<Word<String>> inputSequences) {
        if (W == 0) {
            return;
        }

        for (int i = 0; i < inputs.length; i++) {
            Word<String> newWord = current.append(inputs[i]);
            inputSequences.add(newWord);
            generateInputSequences(inputs, W - 1, newWord, inputSequences);
        }
    }


    @Override
    public Optional<Word<String>> verify(MealyMachine hypothesis) {
        List<Word<String>> accessSequences = accessSequenceGenerator.getAccessSequences();
        List<Word<String>> distinguishingSequences = distinguishingSequenceGenerator.getDistinguishingSequences();

        List<Word<String>> inputSequences = new ArrayList<>();
        generateInputSequences(inputSymbols, w, new Word<>(), inputSequences);

        // Iterate over all access sequences
        for (Word<String> accessSequence : accessSequences) {
            // Iterate over all words of max length w over the input symbols
            for (Word<String> word : inputSequences) {
                // Build the test input
                Word<String> testInput = accessSequence.append(word);

                // Iterate over all distinguishing sequences
                for (Word<String> distinguishingSequence : distinguishingSequences) {
                    // Extend the test input with the distinguishing sequence
                    Word<String> extendedTestInput = testInput.append(distinguishingSequence);

                    // Evaluate the hypothesis and the system under learning on the test input
                    String[] hypothesisOutput = hypothesis.getOutput(testInput);
                    String[] systemOutput = sul.getOutput(testInput);

                    // If the hypothesis and the system under learning disagree on the output, a counterexample has been found
                    if (!Arrays.equals(hypothesisOutput, systemOutput)) {
                        return Optional.of(extendedTestInput);
                    }
                }
            }
        }

        // No counterexample found
        return Optional.empty();
    }


}
