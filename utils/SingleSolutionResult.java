package utils;

import java.util.function.Function;

/**
 * Encapsulates the result of a solution run on a single input
 */
public class SingleSolutionResult<T> {

    private String runTitle;
    private int timeExeMs;
    private T expected;
    private T actual;
    private boolean areEqual;
    private Function<T, String> resultToString;

    public SingleSolutionResult(
            String runTitle,
            int timeExeMs,
            T expected,
            T actual,
            boolean areEqual,
            Function<T, String> resultToString) {
        this.runTitle = runTitle;
        this.timeExeMs = timeExeMs;
        this.expected = expected;
        this.actual = actual;
        this.areEqual = areEqual;
        this.resultToString = resultToString;
    }

    public String getRunTitle() {
        return runTitle;
    }

    public int getTimeExeMs() {
        return timeExeMs;
    }

    public T getExpected() {
        return expected;
    }

    public T getActual() {
        return actual;
    }

    public boolean getIsCorrect() {
        return areEqual;
    }

    @Override
    public String toString() {
        String output = (getIsCorrect() ? "Passed" : "FAILED") + ":: " + getRunTitle() + ": in " + getTimeExeMs()
                + "ms";
        if (!getIsCorrect()) {
            output += " -- expected " + resultToString.apply(expected) + " but got " + resultToString.apply(actual);
        }
        return output;
    }

}