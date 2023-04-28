package nl.tudelft.instrumentation.fuzzing;
import java.lang.Math;

/**
 * This class is a helper class for computing the branch distances.
 */
public class BranchDistanceComputer {


    /**
     * Compute the branch distance to the opposite branch for a given branch.
     */
    public static double computeBranchDistance(MyVar branch) {
        double distance = 0.0;

        switch(branch.type) {
            case BOOL:
                distance = branch.value ? 0.0 : 1.0;
                break;
            case INT:
                distance = branch.int_value;
                break;
            case STRING:
                distance = branch.str_value.charAt(0);
                break;
            case UNARY:
                if (branch.operator.equals("!")) {
                    distance = computeBranchDistance(branch.left);
                }
                else {
                    distance = -1 * branch.int_value;
                }
                break;
            case BINARY:
                double left_distance = computeBranchDistance(branch.left);
                double right_distance = computeBranchDistance(branch.right);

                switch(branch.operator) {
                    case "==":
                        distance = Math.abs(left_distance - right_distance);
                        break;
                    case "<":
                        distance = left_distance < right_distance ? 0.0 : left_distance - right_distance + 1.0;
                        break;
                    case ">":
                        distance = left_distance > right_distance ? 0.0 : right_distance - left_distance + 1.0;
                        break;
                    case "<=":
                        distance = left_distance <= right_distance ? 0.0 : left_distance - right_distance;
                        break;
                    case ">=":
                        distance = left_distance >= right_distance ? 0.0 : right_distance - left_distance;
                        break;
                    case "!=":
                        distance = left_distance != right_distance ? 0.0 : 1.0;
                        break;
                    case "||":
                        distance = Math.min(left_distance, right_distance);
                        break;
                    case "&&":
                        distance = left_distance + right_distance;
                        break;
                    default:
                        System.out.println("Unknown operator" + branch.operator);
                        System.exit(-1);
                        break;
                }

                break;
            default:
                System.out.println("Not possible to compute distance for this type of expression" + branch.type);
                System.exit(-1);
        }

        return normalise_distance(distance);
    }

    /**
     * Normalise the distance using the formula shown in the slides.
     */
    public static double normalise_distance(double distance) {
        return distance / (distance + 1);
    }
}
