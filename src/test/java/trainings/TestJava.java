package trainings;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.base.BaseTest;

public class TestJava extends BaseTest{

    static Logger Log = LoggerFactory.getLogger(TestJava.class);

    public static void main(String[] args) {
        
        System.out.println(browserSelector());
        
    }

	private static String browserSelector() {
        Object[] options = {"chrome_debug", "chrome", "firefox", "edge", "safari" };
        String browser = (String) JOptionPane.showInputDialog(
						    null,
						    "Browser:",
						    "Select browser",
						    JOptionPane.PLAIN_MESSAGE,
                            null,
                            options,
                            options[0]);
        return browser == null ? "chrome" : browser;
	}

}
