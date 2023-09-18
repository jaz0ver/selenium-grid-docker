package framework.utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class MyDataProvider {

    ExcelReader testdata = new ExcelReader();
	String noData = "n/a";

	@DataProvider(name = "invalid_credentials")
	public String[][] testData() {
		return new String[][] {
			// { "user", "pass" },
			{ "infor_taas", "infor_taas" }
		};
	}

	@DataProvider(name="Excel_Data")
	public String[][] excelData(Method m) {
		String [][] data;
		switch (m.getName()) {
			case "TC1_Infor_Login":
				data = testdata.getAllData("testdata", "login");
				break;
			case "TC2_Infor_Login":
				testdata.setExcelXlsx("testdata", "testcases");
				data = new String[][] {
					{ testdata.getData("TC3_Login", "UserName"), testdata.getData("TC3_Login", "Password") }
				};
				break;
			default:
				data = new String[][] {
					{noData, noData}
				};
				break;
		}
		return data;
	}

	@DataProvider(name = "getDataByTcName")
	public String[][] testData3() {
		testdata.setExcelXlsx("testdata", "testcases");
		return new String[][] {
			{ testdata.getData("TC3_Login", "UserName"), testdata.getData("TC3_Login", "Password") }
		};
	}
}
