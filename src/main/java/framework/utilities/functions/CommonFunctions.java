package framework.utilities.functions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonFunctions {

    public static String getMethodName() {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return "[" + methodName + "] ";
    }
    
    public static String getFolderNameAsTime() {
        String folderTime = System.getProperty("fileNameDateFormat");
        if (folderTime == null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH.mm.ss");
            folderTime = dateFormat.format(new Date());
            System.setProperty("fileNameDateFormat", folderTime);
        }
        return folderTime + "/";
    }

    public static String getTypePath() {
        String typePath;
        String suiteName = System.getProperty("suiteName");
        if (suiteName == null) {
            typePath = System.getProperty("currentClassName");
        } else {
            typePath = suiteName.substring(0, suiteName.lastIndexOf("."));
        }
        return typePath + "/";
    }

    public static String  getReportFilePath() {
        String reportFilePath = System.getProperty("reportFilePath");
        if (reportFilePath == null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            reportFilePath = "src/main/resources/reports/" + dateFormat.format(new Date()) + "/" + getTypePath() + getFolderNameAsTime();
            System.setProperty("reportFilePath", reportFilePath);
        }
        return reportFilePath;
    }
}
