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

    static class OperatorCounter implements Comparable<OperatorCounter> {
        public int operator;
        public double score;

        OperatorCounter(int operator, double score) {
            this.operator = operator;
            this.score = score;
        }
        
        @Override
        public String toString() {
            return "OperatorCounter{" +
                "operator=" + operator +
                ", score=" + score +
                '}';
        }

        @Override
        public int compareTo(OperatorCounter o) {
            return Double.compare(o.score, this.score);
        }

    }

    final static int POPULATION_SIZE = 10;
    final static int MUTATION_SIZE = 5;


    static int totalPatches = 0;
    static Random r = new Random();
    static boolean isFinished = false;

    static HashSet<Integer> visitedOperators = new HashSet<>();
    static List<String[]> population = new ArrayList<>();
    static HashMap<Integer, TestCount> testCount = new HashMap<>();
    static PriorityQueue<OperatorCounter> tarantulaResults = new PriorityQueue<>();


    static void initialize() {
        OperatorTracker.runAllTests();
        // initialize the population based on OperatorTracker.operators
        for (int i = 0; i < POPULATION_SIZE; i++) {
            population.add(OperatorTracker.operators.clone());
        }
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

    private static double getFitness() {
        List<Boolean> res = OperatorTracker.runAllTests();
        long trueVals = res.stream().filter(x -> x).count();

        return (double) trueVals / res.size();
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

        // Calculate Tarantula scores and store them in hashmap.
        for (int operator = 0; operator < OperatorTracker.operators.length; operator++) {

            double epsilon = 1e-6;


            TestCount count = testCount.get(operator);
            double failedRatio = (count.failureAmount + epsilon) / (failed + epsilon);
            double successRatio = (count.successAmount + epsilon) / (success + epsilon);

            double score = (failedRatio + epsilon) / (failedRatio + successRatio + epsilon);
            tarantulaResults.add(new OperatorCounter(operator, score));
        }
    }

    static void mutateOperators(int index) {
        String[] operatorsList = OperatorTracker.operators;

        List<OperatorCounter> toMutate = new ArrayList<>();

        int k = MUTATION_SIZE;

        while (k-- >= 0) {
            toMutate.add(tarantulaResults.poll());
        }

        for (OperatorCounter op : toMutate) {
            population.get(index)[op.operator] = operatorsList[r.nextInt(operatorsList.length)];
        }

        //add back the removed operator counters from the pq
        tarantulaResults.addAll(toMutate);


        System.out.println("perform mutation");
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

            for (int idx = 0; idx < POPULATION_SIZE; idx++) {
                tarantulaScore(idx);
                mutateOperators(idx);
            }

            System.out.println("Fitness" + getFitness());

            // Clear for next iteration
            testCount.clear();
            tarantulaResults.clear();

            //Do things!
            try {
                System.out.println("Woohoo, looping!");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void output(String out) {
        // This will get called when the problem code tries to print things,
        // the prints in the original code have been removed for your convenience

        // System.out.println(out);
    }
}