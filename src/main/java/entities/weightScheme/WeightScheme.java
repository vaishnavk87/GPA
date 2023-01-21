package entities.weightScheme;

public interface WeightScheme {
    /**
     * Compute the average mark earned with (marks) according to this weightScheme.
     * <p>
     * Preconditions:
     * marks.length <= getNumberOfInstances()
     * for each mark in marks, 0 <= mark <= 100
     *
     * @param marks a list of marks to weigh
     * @return The average mark earned.
     */
    double computeWeighted(double[] marks);

    double getTotalWeight();

    int getNumberOfInstances();
}
