package nl.tudelft.instrumentation.patching;
import org.javatuples.Pair;

import java.util.*;


public class PatchingLab {

        static Random r = new Random();
        static boolean isLocalizingFaults = false;

        // Keeps track of what operators are used in the different tests,
        // Test ID -> set of Operator IDs
        static HashMap<Integer, LinkedList<Integer>> usedOperators = new HashMap<>();
        static List<Double> tarantulaScores = Arrays.asList(new Double[OperatorTracker.operators.length]);
        static double totalTarantulaScore = 0;

        final static int populationSize = 50;
        static List<List<String>> population = new ArrayList<>(populationSize);
        static List<Double> fitnesses = new ArrayList<>(populationSize);
        static double pMutate = 1;

        static List<String> possibleOperators = Arrays.asList("!=", "==", "<", ">", "<=", ">=");
        static List<String> ogOperators;

        static int nTests = 0;

        static long startTime = System.currentTimeMillis();
        static long MAX_RUN_TIME = 1800000; // 30 mins

        static void initialize(){
                // initialize the population based on OperatorTracker.operators
                ogOperators = new ArrayList<>(Arrays.asList(OperatorTracker.operators));

//                for (int i = 0; i < ogOperators.size(); i++) {
//                        ogOperators.set(i, "==");
//                }

                // Add original operators too
                population.add(ogOperators);
                fitnesses.add(0.0);

                for (int i = 0; i < populationSize - 1; i++) {
                        // Mutate all but one individuals a bunch of times I guess
                        List<String> newIndividual = new ArrayList<>(ogOperators);
//                        for (int p = 0; p < 3; p++) {
//                                newIndividual = mutate(newIndividual);
//                        }
                        population.add(newIndividual);
                        fitnesses.add(0.0);
                }

                nTests = OperatorTracker.tests.size();
        }

        static List<String> mutate(List<String> og) {
                List<String> modified = new ArrayList<>(og);

                // Choose a random position in the list
                // We use the tarantula score to see if we will actually use this position
                boolean mutatePosOk = false;
                int mutatePos = -1;
                while (!mutatePosOk) {
                        mutatePos = r.nextInt(modified.size());
//                         double acceptRand = r.nextDouble();
//                         if (acceptRand < tarantulaScores.get(mutatePos)) {
//                                 mutatePosOk = true;
//                         }
//                        System.out.println(tarantulaScores.toString());
//                        System.exit(-1);
                        if (tarantulaScores.get(mutatePos) > 0.7) {
                                mutatePosOk = true;
                        }
                }

                // Choose a random new operator
                String newOperator = possibleOperators.get(r.nextInt(possibleOperators.size()));
                //String newOperator = "==";

                // Mutate the list
                modified.set(mutatePos, newOperator);

                return modified;
        }

        static List<String> crossover(List<String> parent1, List<String> parent2) {
                assert parent1.size() == parent2.size();

                List<String> child = Arrays.asList(new String[parent1.size()]);

                // Choose a random position in the list
                int crossoverPos = r.nextInt(parent1.size());

                // Copy part of parent 1
                for (int i = 0; i < crossoverPos; i++) {
                        child.set(i, parent1.get(i));
                }
                // And part of parent 2
                for (int i = crossoverPos; i < child.size(); i++) {
                        child.set(i, parent2.get(i));
                }

                return child;
        }

        static void generateNewPopulation() {
                List<List<String>> newPopulation = new ArrayList<>(population.size());

                // Calculate selection probabilities
                List<Double> selectionProbabilities = Arrays.asList(new Double[populationSize]); //new ArrayList<>(populationSize);
                double totalFitness = fitnesses.stream().reduce(0.0, Double::sum);
                for (int idx = 0; idx < populationSize; idx++) {
                        double curFitness = fitnesses.get(idx);
                        double tmp = curFitness / totalFitness;
                        // Handle case were all fitnesses are 0
                        if (Double.isNaN(tmp)) {
                                tmp = 1.0 / populationSize;
                        }
                        selectionProbabilities.set(idx, tmp);
                }

                // Calculate cumulative selection probabilities for ease of use
                double cumProbability = 0;
                int cumIdx = 1;
                List<Double> cumSelectionProbabilities = Arrays.asList(new Double[populationSize + 1]); //new ArrayList<>(populationSize + 1);
                cumSelectionProbabilities.set(0, 0.0);
                for (double p: selectionProbabilities) {
                        cumProbability += p;
                        cumSelectionProbabilities.set(cumIdx, cumProbability);
                        cumIdx++;
                }

                // We always keep the best individual
                int bestIdx = 0;
                double bestFitness = 0;
                for (int i = 0; i < populationSize; i++) {
                        if (fitnesses.get(i) > bestFitness) {
                                bestFitness = fitnesses.get(i);
                                bestIdx = i;
                        }
                }
                newPopulation.add(population.get(bestIdx));

                // Generate new population
                while (newPopulation.size() < populationSize) {
                        // Randomly select two parents
                        double random_p1 = r.nextDouble() * cumProbability;
                        double random_p2 = r.nextDouble() * cumProbability;
                        int p1idx = -1;
                        int p2idx = -1;

                        for (int i = 0; i < cumSelectionProbabilities.size(); i++) {
                                double lowerBound = cumSelectionProbabilities.get(i);
                                double upperBound = cumSelectionProbabilities.get(i + 1);

                                if (random_p1 < upperBound && random_p1 >= lowerBound) {
                                        p1idx = i;
                                }
                                if (random_p2 < upperBound && random_p2 >= lowerBound) {
                                        p2idx = i;
                                }
                                if (p1idx != -1 && p2idx != -1) {
                                        break;
                                }
                        }

                        // If floating point precision errors caused us to not select anything, choose randomly
                        if (p1idx == -1) p1idx = r.nextInt(populationSize);
                        if (p2idx == -1) p2idx = r.nextInt(populationSize);

                        List<String> p1 = population.get(p1idx);
                        List<String> p2 = population.get(p2idx);

                        List<String> child = crossover(p1, p2);


                        if (r.nextDouble() < pMutate) {
                                child = mutate(child);
                        }



                        newPopulation.add(child);
                }

                population = newPopulation;
        }

        static boolean encounteredOperator(String operator, int left, int right, int operator_nr){
                // do something useful
                if(isLocalizingFaults){
                        // these operators are executed in test OperatorTracker.current_test:
                        //System.out.print(String.valueOf(OperatorTracker.current_test) + ":" + String.valueOf(operator_nr) + " ");

                        int currentTestID = OperatorTracker.current_test;
                        if (!usedOperators.containsKey(currentTestID)) {
                                usedOperators.put(currentTestID, new LinkedList<>());
                        }
                        usedOperators.get(currentTestID).add(operator_nr);
                }
                String replacememt = OperatorTracker.operators[operator_nr];
                if(replacememt.equals("!=")) return left != right;
                if(replacememt.equals("==")) return left == right;
                if(replacememt.equals("<")) return left < right;
                if(replacememt.equals(">")) return left > right;
                if(replacememt.equals("<=")) return left <= right;
                if(replacememt.equals(">=")) return left >= right;
                return false;
        }

        static boolean encounteredOperator(String operator, boolean left, boolean right, int operator_nr){
                // do something useful
                if(isLocalizingFaults){
                        // these operators are executed in test OperatorTracker.current_test:
                        //System.out.print(String.valueOf(OperatorTracker.current_test) + ":" + String.valueOf(operator_nr) + " ");

                        int currentTestID = OperatorTracker.current_test;
                        if (!usedOperators.containsKey(currentTestID)) {
                                usedOperators.put(currentTestID, new LinkedList<>());
                        }
                        usedOperators.get(currentTestID).add(operator_nr);
                }
                String replacememt = OperatorTracker.operators[operator_nr];
                if(replacememt.equals("!=")) return left != right;
                if(replacememt.equals("==")) return left == right;
                return false;
        }

        static void localizeFaults() {
                usedOperators = new HashMap<>();

                // Enable this flag to capture the operators used for each test
                isLocalizingFaults = true;

                // Run all tests once
                List<Boolean> testResults = OperatorTracker.runAllTests();

                isLocalizingFaults = false;

                int nTotalPassed = Collections.frequency(testResults, true);
                int nTotalFailed = Collections.frequency(testResults, false);
                // For all operators, keep track of how often they are used in passing and failing tests
                HashMap<Integer, Pair<Integer, Integer>> operatorTestResults = new HashMap<>();

                // Loop over all tests
                for (int testID = 0 ; testID < OperatorTracker.tests.size(); testID++) {
                        // The operator IDs used in the current test
                        LinkedList<Integer> operatorIDs = usedOperators.get(testID);
                        if (operatorIDs == null) {
                                continue;
                        }
                        // Use unique?
                        HashSet<Integer> uniqueOperatorIDs = new HashSet<>(operatorIDs);

                        // Current test result
                        boolean curTestPassed = testResults.get(testID);

                        // Loop over the operators used in this test and update their counts
                        for (int operatorID: uniqueOperatorIDs) { // operatorIDs) {
                                if (!operatorTestResults.containsKey(operatorID)) {
                                        operatorTestResults.put(operatorID, new Pair<>(0, 0));
                                }

                                if (curTestPassed) {
                                        int curPassedCount = operatorTestResults.get(operatorID).getValue0();
                                        operatorTestResults.put(operatorID,
                                                operatorTestResults.get(operatorID).setAt0(curPassedCount + 1)
                                        );
                                } else {
                                        int curFailedCount = operatorTestResults.get(operatorID).getValue1();
                                        operatorTestResults.put(operatorID,
                                                operatorTestResults.get(operatorID).setAt1(curFailedCount + 1)
                                        );
                                }
                        }
                }

                calculateTarantulaScores(nTotalPassed, nTotalFailed, operatorTestResults);
        }

        private static void calculateTarantulaScores(int nTotalPassed, int nTotalFailed,
                                                     HashMap<Integer, Pair<Integer, Integer>> operatorTestResults) {
                for (int opID = 0; opID < OperatorTracker.operators.length; opID++) {
                        if (!operatorTestResults.containsKey(opID)) {
                                tarantulaScores.set(opID, 0.0);
                        } else {
                                Pair<Integer, Integer> opResults = operatorTestResults.get(opID);
                                int opPassed = opResults.getValue0();
                                int opFailed = opResults.getValue1();
                                tarantulaScores.set(opID, calcTarantula(nTotalPassed, nTotalFailed, opPassed, opFailed));
                        }
                }
                totalTarantulaScore = tarantulaScores.stream().reduce(0.0, Double::sum);
                System.out.println("Tarantula calculated");
        }

        private static double calcTarantula(int nTotalPassed, int nTotalFailed, int opPassed, int opFailed) {
                double result;
                try {
                        result = ((double)opFailed / (double)nTotalFailed) /
                                (((double)opFailed / (double)nTotalFailed) + ((double)opPassed / (double)nTotalPassed));
                } catch (ArithmeticException e) {
                        result = 0;
                }
                return result;
        }

        static void run() {
                initialize();

                localizeFaults();

                while (System.currentTimeMillis() - startTime < MAX_RUN_TIME) {
                        calcFitnesses();
                        //localizeFaults();
                        generateNewPopulation();
                }
                System.exit(0);
        }

        private static void calcFitnesses() {
//                System.out.print("Fitnesses: [");
                for (int i = 0; i < population.size(); i++) {
                        List<String> curIndividual = population.get(i);
                        double curFitness = calcFitness(curIndividual);
                        fitnesses.set(i, curFitness);
//                        System.out.print(curFitness + " ");
                }
                //System.out.println("]");

                double bestFitness = Collections.max(fitnesses);
                System.out.println("Current best fitness: " + bestFitness + " Time: " + (System.currentTimeMillis() - startTime));

                if (bestFitness > 0.99999) {
                        System.exit(0);
                }
        }

        private static double calcFitness(List<String> operators) {
                OperatorTracker.operators = operators.toArray(new String[0]);;
                List<Boolean> testResults = OperatorTracker.runAllTests();
                int nPassedTests = Collections.frequency(testResults, true);
                int nTotalTests = OperatorTracker.tests.size();
                return (double) nPassedTests / (double) nTotalTests;
        }


        public static void output(String out){
                //System.out.println(out);
        }
}