# Ensure jenkins is installed
# Ensure jenkins.war is running before navigating to localhost:8080
# Current path C:\Program Files\Jenkins
# Command line: java -jar jenkins.war 

Personal Selenium Java Framework - Test Data Driven / POM
Maven Project

Functions
 * Selenium Grid/WebDriver
 * Parallel execution
 * Configuration property file
 * Excel
 * Extent Report
 * Logger

openReporAfterTest - boolean - false
Automatically opens the report after execution

browser - choice

-Dbrowser=chrome
-Dbrowser=firefox
-Dbrowser=edge
-Dbrowser=safari
Select which browser the test will run

suiteXML - choice
RegressionSuite
SmokeSuite
TestSuite
SuitesRunner
Select which suite to run

selenium - choice
WebDriver
Grid
Select which Selenium the test will run.
Note: Selenium Grid requires Hub and Nodes. Ensure docker-compose is running

https://github.com/jaz0ver/AutomationSelenium_VSC.git
jaz0ver
Jaz102707!

*/main
mvn test -DskipTests=false -DsuiteXML=%suiteXML% -Dopen_extent_report=%openReporAfterTest% -Dselenium=%selenium% %browser%

SessionNotCreatedException