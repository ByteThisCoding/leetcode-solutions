package utils;

/**
 * Encapsulate a single test case with a title,
 * params for the method, and expected value
 */
public class TestCase<T> {

    private String runTitle;
    private Object[] params;
    private T expected;

    public TestCase(String runTitle, T expected, Object[] params) {
        this.runTitle = runTitle;
        this.params = params;
        this.expected = expected;
    }

    public String getRunTitle() {
        return runTitle;
    }

    public Object[] getParams() {
        return params;
    }

    public T getExpectedResult() {
        return expected;
    }

}
