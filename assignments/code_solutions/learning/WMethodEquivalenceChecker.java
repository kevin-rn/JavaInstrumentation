package nl.tudelft.instrumentation.learning;

import java.util.List;
import java.util.Optional;

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
        // TODO implement the W-method equivalence checker
        List<Word<String>> A = this.accessSequenceGenerator.getAccessSequences();
        List<Word<String>> D = this.distinguishingSequenceGenerator.getDistinguishingSequences();
        int size = this.inputSymbols.length;
        int total = (int) Math.pow(size, w);
        
        for(Word<String> a : A) {
            for(int index = 0; index < total; index++) {
                Word<String> toCheck = a;
                int temp = index;
                for(int i = 0; i < w; i++){
                    toCheck = toCheck.append(this.inputSymbols[temp % size]);
                    temp = temp / size;
                }
                for(Word<String> d : D) {
                    Word<String> trace = toCheck.append(d);
                    if(!hypothesis.getLastOutput(trace).equals(sul.getLastOutput(trace))) {
                        return Optional.of(trace);
                    }
                }
            }
        }
        return Optional.empty();
    }

}
