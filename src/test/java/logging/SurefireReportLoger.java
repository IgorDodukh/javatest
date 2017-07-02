package logging;

import org.testng.Reporter;

/**
 * Created by Ihor on 7/2/2017.
 */
public class SurefireReportLoger {
    public static void log(String logMessage) {
        Reporter.log(logMessage + "<br>");
    }
}
