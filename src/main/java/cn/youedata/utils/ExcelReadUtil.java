package cn.youedata.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.youedata.config.TestTep;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * excel读取类，用于读取excel数据
 * @author Administrator
 *
 */
public class ExcelReadUtil {

	private static TestTep testTep;

	/**
	 * 读取一条用例
	 * @param filePath
	 * @param sheetName
	 * @param num
	 * @return
	 */
	public static Iterator<Object[]> readExcel(String filePath, String sheetName,int num){

		File file = new File(filePath);
		List<Object[]> result = new ArrayList<Object[]>();
		try {
			FileInputStream fs = new FileInputStream(file);
			Workbook wb = null;
			String fileTypeName = filePath.substring(filePath.indexOf("."));
			if(fileTypeName.equals(".xlsx")){
				wb = new XSSFWorkbook(fs);
			}else{
				wb = WorkbookFactory.create(fs);    ;
			}
			Sheet st = wb.getSheet(sheetName);
			List<Object[]> records = new ArrayList<Object[]>();
			Row rows = st.getRow(num);
			String[] fields = new String[rows.getLastCellNum()];
			for (int j = 0; j < rows.getLastCellNum(); j++) {
				rows.getCell(j).setCellType(CellType.STRING);
				fields[j] = rows.getCell(j).getStringCellValue();
				records.add(fields);
			}
			for(int i=0;i<records.size();i++){
				testTep = new TestTep();
				testTep.setProjectPath((String)records.get(i)[0]);
				testTep.setActionName((String)records.get(i)[1]);
				testTep.setTestName((String)records.get(i)[2]);
				testTep.setTestDesc((String)records.get(i)[3]);
				testTep.setOpeartor((String)records.get(i)[4]);
				testTep.setTestData((String)records.get(i)[5]);
				testTep.setExpectResult((String)records.get(i)[6]);
				testTep.setTestTeam((String)records.get(i)[7]);
				testTep.setExecuteUser((String)records.get(i)[8]);
				testTep.setPlanTime((String)records.get(i)[9]);
			}
			if(testTep != null){
				result.add(new Object[]{testTep});
			}


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		return result.iterator();
	}

	/**
	 * 读取整个excel表
	 * @param filePath
	 * @param sheetName
	 * @return
	 */
	public static Iterator<Object[]> readExcel(String filePath, String sheetName){

		List<TestTep> item = new ArrayList<TestTep>();
		File file = new File(filePath);
		List<Object[]> result = new ArrayList<Object[]>();
		try {
			FileInputStream fs = new FileInputStream(file);
			Workbook wb = null;
			String fileTypeName = filePath.substring(filePath.indexOf("."));
			if(fileTypeName.equals(".xlsx")){
				wb = new XSSFWorkbook(fs);
			}else{
				wb = WorkbookFactory.create(fs);    ;
			}
			Sheet st = wb.getSheet(sheetName);
			int firstRowNum = st.getFirstRowNum();
			int lastRowNum = st.getLastRowNum();
			int rowCount = lastRowNum - firstRowNum;
			List<Object[]> records = new ArrayList<Object[]>();
			for(int i=2;i<=rowCount;i++) {
				Row rows = st.getRow(i);
				String[] fields = new String[rows.getLastCellNum()];
				for (int j = 0; j < rows.getLastCellNum(); j++) {
					rows.getCell(j).setCellType(CellType.STRING);
					fields[j] = rows.getCell(j).getStringCellValue();
				}
				records.add(fields);
			}
			for(int i=0;i<records.size();i++){
				testTep = new TestTep();
				testTep.setProjectPath((String)records.get(i)[0]);
				testTep.setActionName((String)records.get(i)[1]);
				testTep.setTestName((String)records.get(i)[2]);
				testTep.setTestDesc((String)records.get(i)[3]);
				testTep.setOpeartor((String)records.get(i)[4]);
				testTep.setTestData((String)records.get(i)[5]);
				testTep.setExpectResult((String)records.get(i)[6]);
				testTep.setTestTeam((String)records.get(i)[7]);
				testTep.setExecuteUser((String)records.get(i)[8]);
				testTep.setPlanTime((String)records.get(i)[9]);

				item.add(testTep);
			}
			for (TestTep t:item ) {

				result.add(new Object[]{t});
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}

		return result.iterator();
	}

}
