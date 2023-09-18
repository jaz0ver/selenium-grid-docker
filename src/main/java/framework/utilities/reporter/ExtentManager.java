package framework.utilities.reporter;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import framework.base.DriverManager;
import framework.utilities.ConfigFileReader;
import framework.utilities.functions.CommonFunctions;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();
    public static String extentReportFilePath;
    
    public synchronized static ExtentReports createExtentReports() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
        String fileName = "ExtentReport_" + dateFormat.format(new Date()) + ".html";
        extentReportFilePath = CommonFunctions.getReportFilePath() + fileName;
        
        ExtentSparkReporter reporter = new ExtentSparkReporter(extentReportFilePath);
        reporter.config().setDocumentTitle(ConfigFileReader.getProperty("project_name"));
        reporter.config().setReportName(ConfigFileReader.getProperty("environment").toUpperCase() + " environment - Report".toUpperCase());
        
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Project", ConfigFileReader.getProperty("project_name"));
        extentReports.setSystemInfo("Author", "Zenric Navea");
        extentReports.setSystemInfo("Platform", DriverManager.getCapabilities().getPlatformName().toString().toUpperCase());
        return extentReports;
    }
}
