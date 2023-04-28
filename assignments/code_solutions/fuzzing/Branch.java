package nl.tudelft.instrumentation.fuzzing;

/**
 * Class is used to store a branch as an object.
 */
public class Branch {

    private MyVar condition;
    private String id;

    public Branch(MyVar condition, String id) {
        this.condition = condition;
        this.id = id;
    }

    public double computeOppositeBranchDistance() {
        return 1 - BranchDistanceComputer.computeBranchDistance(this.condition);
    }

    /**
     * Getters
     */
    public String getId() {return this.id;}
}
