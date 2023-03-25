package nl.tudelft.instrumentation.patching;

import java.util.*;


public class PatchingLab {

    /**
     * Helper class for storing test result counts.
     */
    static class TestCount {
        public int successAmount;
        public int failureAmount;

        TestCount(int successAmount, int failureAmount) {
            this.successAmount = successAmount;
            this.failureAmount = failureAmount;
        }
    }

    static class ScoreCounter implements Comparable<ScoreCounter> {
        public int id;
        public double score;

        public ScoreCounter(int id, double score) {
            this.id = id;
            this.score = score;
        }

        @Override
        public int compareTo(ScoreCounter o) {
            return Double.compare(this.score, o.score);
        }

        @Override
        public String toString() {
            return "ScoreCounter{" +
                "id=" + id +
                ", score=" + score +
                '}';
        }
    }


    final static int MAX_POPULATION_SIZE = 32;
    static int INIT_POPULATION_SIZE = 16;
    static int MUTATION_SIZE = 5;


    static int totalPatches = 0;
    static Random r = new Random();
    static boolean isFinished = false;

    static Set<Integer> visitedOperators = new HashSet<>();
    static List<String[]> population = new ArrayList<>();
    static Map<Integer, TestCount> testCount = new HashMap<>();
    static Map<Integer, List<ScoreCounter>> tarantulaResults = new HashMap<>();

    static List<ScoreCounter> populationFitness = new ArrayList<>();
    static Set<Double> fitness = new HashSet<>();


    static void initialize() {
        OperatorTracker.runAllTests();
        // initialize the population based on OperatorTracker.operators
        for (int i = 0; i < INIT_POPULATION_SIZE; i++) {
            population.add(OperatorTracker.operators.clone());
        }

        MUTATION_SIZE = OperatorTracker.operators.length / 10;
    }

    // encounteredOperator gets called for each operator encountered while running tests
    static boolean encounteredOperator(String operator, int left, int right, int operator_nr) {
        // Do something useful
        visitedOperators.add(operator_nr);

        String replacement = OperatorTracker.operators[operator_nr];
        if (replacement.equals("!=")) {
            return left != right;
        }
        if (replacement.equals("==")) {
            return left == right;
        }
        if (replacement.equals("<")) {
            return left < right;
        }
        if (replacement.equals(">")) {
            return left > right;
        }
        if (replacement.equals("<=")) {
            return left <= right;
        }
        if (replacement.equals(">=")) {
            return left >= right;
        }
        return false;
    }

    static boolean encounteredOperator(String operator, boolean left, boolean right, int operator_nr) {
        // Do something useful
        visitedOperators.add(operator_nr);

        String replacement = OperatorTracker.operators[operator_nr];
        if (replacement.equals("!=")) {
            return left != right;
        }
        if (replacement.equals("==")) {
            return left == right;
        }
        return false;
    }

    private static double getFitness(int id) {


        OperatorTracker.operators = population.get(id);

        List<Boolean> res = OperatorTracker.runAllTests();
        long trueVals = res.stream().filter(x -> x).count();

        return (double) trueVals / res.size();
    }

    private static void calculateFitness() {
        for (int i = 0; i < population.size(); i++) {
            populationFitness.add(new ScoreCounter(i, getFitness(i)));
        }
        populationFitness.sort(Comparator.reverseOrder());
    }

    /**
     * Calculates Tarantula score for given operators.
     *
     * @param index
     */
    static void tarantulaScore(int index) {

        OperatorTracker.operators = population.get(index);

        int success = 0;
        int failed = 0;

        // Initialize mapping with counts being 0 for each operator.
        for (int opIndex = 0; opIndex < OperatorTracker.operators.length; opIndex++) {
            testCount.put(opIndex, new TestCount(0, 0));
        }

        // Run each test and keep track of the count.
        for (int testIndex = 0; testIndex < OperatorTracker.tests.size(); testIndex++) {
            boolean check = OperatorTracker.runTest(testIndex);

            if (check) {
                success++;
                for (int visit : visitedOperators) {
                    testCount.get(visit).successAmount++;
                }
            } else {
                failed++;
                for (int visit : visitedOperators) {
                    testCount.get(visit).failureAmount++;
                }
            }
            visitedOperators.clear();
        }

        // Check if patches were found
        if (failed == 0) {
            totalPatches++;
        }

        if (!tarantulaResults.containsKey(index)) {
            tarantulaResults.put(index, new ArrayList<>());
        }

        // Calculate Tarantula scores and store them in hashmap.
        for (int operator = 0; operator < OperatorTracker.operators.length; operator++) {

            double epsilon = 1e-6;


            TestCount count = testCount.get(operator);
            double failedRatio = (count.failureAmount + epsilon) / (failed + epsilon);
            double successRatio = (count.successAmount + epsilon) / (success + epsilon);

            double score = (failedRatio + epsilon) / (failedRatio + successRatio + epsilon);
            tarantulaResults.get(index).add(new ScoreCounter(operator, score));
        }
    }

    static void mutateOperators(int index) {
        String[] operatorsList = OperatorTracker.operators;

        tarantulaResults.get(index).sort(Comparator.reverseOrder());

        for (int i = 0; i < MUTATION_SIZE; i++) {
            population.get(index)[tarantulaResults.get(index).get(i).id] = operatorsList[r.nextInt(operatorsList.length)];
        }

    }

    static String[] crossover(String[] a, String[] b) {


        String[] crossoverRes = new String[a.length];

        for (int i = 0; i < a.length; i++) {
            int rand = r.nextInt(10000);

            if (rand > 5000) {
                crossoverRes[i] = a[i];
            } else {
                crossoverRes[i] = b[i];
            }
        }

        return crossoverRes;
    }

    static void tournamentAndCrossover() {

        List<String[]> newIndividuals = new ArrayList<>();
        calculateFitness();

        //remove the 1/4 worst performers
        populationFitness.subList(0, populationFitness.size() - populationFitness.size() / 4);

        int i = 1;

        while (newIndividuals.size() < MAX_POPULATION_SIZE && i < populationFitness.size()) {
            newIndividuals.add(crossover(population.get(populationFitness.get(i).id), population.get(populationFitness.get(i - 1).id)));
            i++;
        }


        i = 0;
        //add back the ones with the best fitness if there is still space in the population
        while(newIndividuals.size() < MAX_POPULATION_SIZE && i < populationFitness.size()){
            newIndividuals.add(population.get(populationFitness.get(i).id));
            i++;
        }

        population = newIndividuals;

    }


    static void run() {
        initialize();

        // Place the code here you want to run once:
        // You want to change this of course, this is just an example
        // Tests are loaded from resources/rers2020_test_cases. If you are you are using
        // your own tests, make sure you put them in the same folder with the same
        // naming convention.
        // OperatorTracker.runAllTests();
        System.out.println("Entered run");

        // Loop here, running your genetic algorithm until you think it is done
        while (!isFinished) {
            System.out.println("POPULATION SIZE " + population.size());


            for (int idx = 0; idx < population.size(); idx++) {
                tarantulaScore(idx);
                mutateOperators(idx);
            }


            tournamentAndCrossover();

            System.out.println("Fitness " + populationFitness);
            System.out.println("Patches " + totalPatches);



            // Clear for next iteration
            testCount.clear();
            tarantulaResults.clear();
            populationFitness.clear();
        }
    }

    public static void output(String out) {
        // This will get called when the problem code tries to print things,
        // the prints in the original code have been removed for your convenience

        // System.out.println(out);
    }
}