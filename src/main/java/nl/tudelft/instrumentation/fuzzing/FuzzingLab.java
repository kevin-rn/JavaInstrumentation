package nl.tudelft.instrumentation.fuzzing;

import java.util.*;

/**
 * You should write your own solution using this class.
 */
public class FuzzingLab {
        static Random r = new Random();
        static List<String> currentTrace;
        static int traceLength = 10;
        static boolean isFinished = false;

        // Keep track of visited branches and total distance
        static HashSet<Integer> visited = new HashSet<>();
        static int totalDistance = 0;

        static void initialize(String[] inputSymbols){
                // Initialise a random trace from the input symbols of the problem.
                currentTrace = generateRandomTrace(inputSymbols);
        }

        /**
         * Write your solution that specifies what should happen when a new branch has been found.
         */
        static void encounteredNewBranch(MyVar condition, boolean value, int line_nr) {
                // do something useful
                int distanceBranch = distanceCounter(condition, value, "");
                visited.add(line_nr);
                System.out.println(distanceBranch);
                totalDistance += distanceBranch;
        }

        /**
         * Helper method for calculating distance of the branch condition.
         * @param condition Branch condition as MyVar type.
         * @param value Boolean value of the branch condition.
         * @param str String value of the branch condition.
         * @return Branch condition distance.
         */
        static int distanceCounter(MyVar condition, boolean value, String str) {
                int dist = 0;
                switch(condition.type) {
                        case BOOL:
                                dist = value ? 0 : 1;
                                break;
                        case INT:
                                dist = condition.int_value;
                                break;
                        case STRING:
                                str = condition.str_value;
                                break;
                        case UNARY:
                                if (condition.operator.equals("!")) {
                                        dist = 1 - distanceCounter(condition.left, condition.left.value, "");
                                } else {
                                        dist = Integer.MIN_VALUE;
                                        System.out.println("Error: Encountered problem with Unary operator " + condition.operator);
                                }
                                break;
                        case BINARY:
                                String str1 = "", str2 = "";
                                int expr1 = distanceCounter(condition.left, condition.left.value, str1);
                                int expr2 = distanceCounter(condition.right, condition.right.value, str2);
                                dist = binaryDistance(condition, expr1, expr2, str1, str2);
                                break;
                        default:
                                dist =  Integer.MIN_VALUE;
                                System.out.println("Error: Encountered unexpected Branch condition type " + condition.type);
                                break;
                               
                }
                return normalizeDistance(dist);
        }

        /**
         * Method for calculating the binary type branch conditions.
         * @param condition Branch condition as MyVar type.
         * @param expr1 Left-hand expression from the operator of the conditon.
         * @param expr2 Right-hand expression from the operator of the conditon.
         * @param str1 Left-hand string expression.
         * @param str2 Right-hand string expression.
         * @return Branch condition distance.
         */
        static int binaryDistance(MyVar condition, int expr1, int expr2, String str1, String str2) {
                int dist = 0;
                switch(condition.operator){
                        case "|":
                        case "||": 
                                dist = Math.min(expr1, expr2);
                                break;
                        case "&":
                        case "&&":
                                dist = expr1 + expr2;
                                break;
                        case "==": 
                                if(condition.type == TypeEnum.STRING || !str1.isEmpty() || !str2.isEmpty()) {
                                        dist = editDistDP(str1, str2, str1.length(), str2.length());
                                } else {
                                        dist = Math.abs(expr1 - expr2);
                                }
                                break;
                        case "!=": 
                                if(condition.type == TypeEnum.STRING) {
                                        dist = !str1.equals(str2) ? 0 : 1;
                                } else {
                                        dist = (expr1 != expr2) ? 0 : 1;
                                }
                                break;
                        case "<": 
                                dist = (expr1 < expr2) ? 0 : expr1 - expr2 + 1;
                                break;
                        case "<=": 
                                dist = (expr1 <= expr2) ? 0 : expr1 - expr2;
                                break;
                        case ">":
                                dist = (expr1 > expr2) ? 0 : expr2 - expr1 + 1;
                                break;
                        case ">=":
                                dist = (expr1 > expr2) ? 0 : expr2 - expr1;
                                break;
                        default:
                                dist = Integer.MIN_VALUE;
                                System.err.println("Error: Encountered unexpected binary branch condition type " + condition.operator);
                                break;
                }
                return normalizeDistance(dist);
        }

        /**
         * Normalize the distances of individual predicates before combining them.
         * @param d the individual predicate.
         * @return Normalized predicate.
         */
        static int normalizeDistance(int d) {
                return d/(d+1);
        }

        /**
         * Compute the edit distance between two strings according to the Levenshtein Distance metric.
         * @param str1 First string to compute from.
         * @param str2 Second string to compute from.
         * @return Distance between two strings.
         */
        static int editDistDP(String str1, String str2, int m, int n){
                int[][] table = new int[m + 1][n + 1];

                for (int i = 0; i <= m; i++) {
                        for (int j = 0; j <= n; j++) {
                                // If first string is empty
                                if (i == 0) { 
                                        table[i][j] = j;
                                // If second string is empty
                                }else if (j == 0) {
                                        table[i][j] = i;
                                // If last characters are same
                                }else if ((int) str1.charAt(i - 1) == (int) str2.charAt(j - 1)){
                                        table[i][j] = table[i - 1][j - 1];
                                // If last character is different
                                } else {
                                        table[i][j] = 1 + Math.min(Math.min(table[i][j - 1],    // cost of adding a character
                                                                table[i - 1][j]),               // cost of removing a character
                                                                table[i - 1][j - 1]);           // cost of replacing a character
                                }
                        }
                }
                return table[m][n];
        }

        /**
         * Method for fuzzing new inputs for a program.
         * @param inputSymbols the inputSymbols to fuzz from.
         * @return a fuzzed sequence
         */
        static List<String> fuzz(String[] inputSymbols, List<String> optimalTrace){
                /*
                 * Add here your code for fuzzing a new sequence for the RERS problem.
                 * You can guide your fuzzer to fuzz "smart" input sequences to cover
                 * more branches. Right now we just generate a complete random sequence
                 * using the given input symbols. Please change it to your own code.
                 */
                return generateRandomTrace(inputSymbols);
        }

        /**
         * Generate a random trace from an array of symbols.
         * @param symbols the symbols from which a trace should be generated from.
         * @return a random trace that is generated from the given symbols.
         */
        static List<String> generateRandomTrace(String[] symbols) {
                ArrayList<String> trace = new ArrayList<>();
                for (int i = 0; i < traceLength; i++) {
                        trace.add(symbols[r.nextInt(symbols.length)]);
                }
                return trace;
        }

        static void run() {
                initialize(DistanceTracker.inputSymbols);
                DistanceTracker.runNextFuzzedSequence(currentTrace.toArray(new String[0]));

                // Place here your code to guide your fuzzer with its search.
                while(!isFinished) {
                        // Do things!
                        try {
                                System.out.println("Woohoo, looping!");
                                Thread.sleep(1000);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                }

        }

        /**
         * Method that is used for catching the output from standard out.
         * You should write your own logic here.
         * @param out the string that has been outputted in the standard out.
         */
        public static void output(String out){
                System.out.println(out);
        }
}
