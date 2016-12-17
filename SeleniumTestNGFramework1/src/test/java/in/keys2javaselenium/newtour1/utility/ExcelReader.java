package in.keys2javaselenium.newtour1.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class ExcelReader {
	static FileInputStream fis;
	static Workbook wb;
	static Sheet s1;

	@DataProvider(name="newtours")//1st change
	public static String[][] storeCellDataForGivenTestCase(Method m)//3 change
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		fis = new FileInputStream(".\\TestData\\NewToursData.xlsx");
		wb = WorkbookFactory.create(fis);
		s1 = wb.getSheet("Sheet1");

		String testcaseName = m.getName();//4th change
		System.out.println(testcaseName);
		//this will fetch method name to be executed. here method name is verifyRegistrationProcess and testCasename is alos same.
		int rowCount = s1.getPhysicalNumberOfRows();

//		String testcaseName = tc;

		int arrayRowCount = getGivenTestCaseRowCount(testcaseName);
		int arrayCellCount = getGivenTestDataCellCount(testcaseName);
		String[][] testData = new String[arrayRowCount][arrayCellCount+1]; // 1

		int nrindex = 0; // 2

		for (int rowIndex = 1; rowIndex < rowCount; rowIndex++) {
			Row r = s1.getRow(rowIndex);

			Cell c1 = r.getCell(1);
			Cell c2 = r.getCell(2);

			int ncIndex = 0; // 3

			String secondCellData = c1.getStringCellValue();
			String thirdCellData = c2.getStringCellValue();

			if (thirdCellData.equalsIgnoreCase("Y") && secondCellData.equalsIgnoreCase(testcaseName)) {
				for (int cellDataIndex = 3; cellDataIndex < r.getPhysicalNumberOfCells(); cellDataIndex++) {
					Cell c3 = r.getCell(cellDataIndex);
					testData[nrindex][ncIndex++] = c3.getStringCellValue(); // 4
					
					
				}
				testData[nrindex][ncIndex++]=rowIndex+"";//cpverting 2 dimentional array to string
				nrindex++; // 5
			}
		}
		return testData; // 6
	}

	public static int getGivenTestDataCellCount(String testcaseName)
			throws EncryptedDocumentException, InvalidFormatException, IOException {

		fis = new FileInputStream(".\\TestData\\NewToursData.xlsx");
		wb = WorkbookFactory.create(fis);
		s1 = wb.getSheet("Sheet1");

		int rowCount = s1.getPhysicalNumberOfRows();

		for (int rowIndex = 1; rowIndex < rowCount; rowIndex++) {
			Row r = s1.getRow(rowIndex);

			Cell c1 = r.getCell(1);
			Cell c2 = r.getCell(2);

			String secondCellData = c1.getStringCellValue();
			String thirdCellData = c2.getStringCellValue();

			if (thirdCellData.equalsIgnoreCase("Y") && secondCellData.equalsIgnoreCase(testcaseName)) {
				return r.getPhysicalNumberOfCells() - 3;
			}
		}
		return 0;
	}

	public static int getGivenTestCaseRowCount(String testcaseName)
			throws EncryptedDocumentException, InvalidFormatException, IOException {

		fis = new FileInputStream(".\\TestData\\NewToursData.xlsx");
		wb = WorkbookFactory.create(fis);
		s1 = wb.getSheet("Sheet1");

		int rowCount = s1.getPhysicalNumberOfRows();

		int count = 0;
		for (int rowIndex = 1; rowIndex < rowCount; rowIndex++) {
			Row r = s1.getRow(rowIndex);

			Cell c1 = r.getCell(1);
			Cell c2 = r.getCell(2);

			String secondCellData = c1.getStringCellValue();
			String thirdCellData = c2.getStringCellValue();

			if (thirdCellData.equalsIgnoreCase("Y") && secondCellData.equalsIgnoreCase(testcaseName)) {
				count++;
			}
		}
		return count;
	}
	public static void writeXL(String text, int rowNum, int colNum) throws EncryptedDocumentException, InvalidFormatException, IOException {
		FileInputStream fs = new FileInputStream(".\\TestData\\NewToursData.xlsx");
		Workbook wb = new WorkbookFactory().create(fs);
		Sheet s = wb.getSheet("Sheet1");
		Row  r = s.getRow(rowNum);
		Cell c= r.createCell(colNum);
		c.setCellValue(text);
		
		FileOutputStream fos = new FileOutputStream(".\\TestData\\NewToursData.xlsx");
		wb.write(fos);
		fos.close();
		wb.close();
	}
}
