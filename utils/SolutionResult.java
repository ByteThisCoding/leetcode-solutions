package utils;

public class SolutionResult<T> {

    private SingleSolutionResult<T>[] results;

    public SolutionResult(SingleSolutionResult<T>[] results) {
        this.results = results;
    }

    public int getTotalTimeExeMs() {
        int time = 0;
        for (SingleSolutionResult<T> result : results) {
            time += result.getTimeExeMs();
        }
        return time;
    }

    public int getNumCasesCorrect() {
        int correct = 0;
        for (SingleSolutionResult<T> result : results) {
            correct += result.getIsCorrect() ? 1 : 0;
        }
        return correct;
    }

    public int getTotalNumCases() {
        return results.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (SingleSolutionResult<T> result : results) {
            sb.append(result.toString());
            sb.append("\n");
        }

        sb.append("\nOverall Results:\n");
        sb.append(": " + getNumCasesCorrect() + " test cases correct.\n");
        sb.append(": " + (getTotalNumCases() - getNumCasesCorrect()) + " test cases incorrect.\n");
        sb.append(": " + getTotalNumCases() + " total number of tests.\n");
        sb.append(" -- All tests executed in " + getTotalTimeExeMs() + "ms.\n");

        return sb.toString();
    }

}
