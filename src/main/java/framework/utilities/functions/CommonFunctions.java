package framework.utilities.functions;

public class CommonFunctions {

    public static String getMethodName() {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return "[" + methodName + "] ";
    }
}
