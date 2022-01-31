package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public abstract class SolutionRunner<T, S> {

    private S solutionInstance;

    public SolutionRunner() {
        try {
            solutionInstance = createSolutionInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }
    }

    public SolutionResult<T> run() throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {

        ArrayList<TestCase<T>> cases = getTestCases();

        SingleSolutionResult<T>[] tcResults = new SingleSolutionResult[cases.size()];

        for (int i = 0; i < cases.size(); i++) {
            SingleSolutionResult<T> result = executeCase(cases.get(i));
            tcResults[i] = result;
        }

        SolutionResult<T> response = new SolutionResult<T>(tcResults);

        String art = "";
        try {
            art = readAsciiArt();
        } catch (FileNotFoundException e) {
            // Nothing, this is not required
        }
        System.out.println("\n" + art + "\n\n" + response.toString());

        return response;
    }

    /**
     * Get a reference of the solution class we'll be working on
     */
    protected abstract Class<S> getSolutionClass();

    /**
     * Get a reference of the solution instance to call methods on
     * 
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    protected S createSolutionInstance() throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        return getSolutionClass().getConstructor().newInstance();
    }

    /**
     * Get a name of the method the solution class exposes
     */
    protected String getSolutionMethod() {
        return getSolutionClass().getMethods()[0].getName();
    }

    /**
     * Used to check if the expected is equal to the actual
     */
    protected abstract boolean areValuesEqual(T valueA, T valueB);

    /**
     * Define test cases which will be used for this run
     */
    protected abstract ArrayList<TestCase<T>> getTestCases();

    /**
     * Execute a single test case
     * 
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    private SingleSolutionResult<T> executeCase(TestCase<T> test) throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // use reflection to get a reference to the method to call
        Method testMethod = null;
        // iterate to find method without knowing param types
        for (Method method : getSolutionClass().getMethods()) {
            if (method.getName().equals(getSolutionMethod())) {
                testMethod = method;
                break;
            }
        }

        // run and time test
        long start = Calendar.getInstance().getTimeInMillis();
        T result = (T) testMethod.invoke(solutionInstance, test.getParams());
        long end = Calendar.getInstance().getTimeInMillis();

        // create the single solution object and return
        return new SingleSolutionResult<T>(
                test.getRunTitle(),
                (int) (end - start),
                test.getExpectedResult(),
                result,
                areValuesEqual(test.getExpectedResult(), result));
    }

    /**
     * Read the ASCII art for pretty output
     * 
     * @throws FileNotFoundException
     */
    private String readAsciiArt() throws FileNotFoundException {
        return new Scanner(new File("./resources/ascii-art.txt")).useDelimiter("\\Z").next();
    }

}
