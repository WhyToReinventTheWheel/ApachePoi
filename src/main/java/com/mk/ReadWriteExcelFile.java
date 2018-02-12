package com.mk;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


class Course{
	private String courseId;
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
}

class Student{
	private String id;
	private Course course;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
}

class Class{
	private Map<String,List<Student>> classDetail = new HashMap<>();
	
	void addClass(String classId,List<Student> studentList) {
		if(classDetail.get(classId) != null) {
			System.out.println("This Classs Id is already exits" + classId );
		}else {
			classDetail.put(classId,studentList);
		}
	}
}

public class ReadWriteExcelFile {

	public static void readXLSXFile() throws IOException
	{
		InputStream ExcelFileToRead = new FileInputStream("F://ApachePoi/Test.xlsx");
		XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);
		
		XSSFWorkbook test = new XSSFWorkbook(); 
		
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row; 
		XSSFCell cell;

		Iterator<Row> rows = sheet.rowIterator();
		rows.next();
		String orderId = "";
		
		while (rows.hasNext())
		{
			row=(XSSFRow) rows.next();
			double sNo = row.getCell(0).getNumericCellValue();
			String id = row.getCell(1).getStringCellValue();
			String key = row.getCell(2).getStringCellValue();
			String value = row.getCell(3).getStringCellValue();
			if(!id.trim().isEmpty()) {
				orderId = id;
			}
			System.out.println("SNo="+sNo +" ,classId="+orderId+" ,key="+key+" ,value="+value);
		}
	}
	

	public static void main(String[] args) throws IOException {
	//	writeXLSXFile();
		readXLSXFile();

	}

	

	public static void writeXLSXFile() throws IOException {
		
		String excelFileName = "F:/Test.xlsx";//name of excel file

		String sheetName = "Sheet1";//name of sheet

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName) ;

		//iterating r number of rows
		for (int r=0;r < 5; r++ )
		{
			XSSFRow row = sheet.createRow(r);
			//iterating c number of columns
			for (int c=0;c < 5; c++ )
			{
				XSSFCell cell = row.createCell(c);
				cell.setCellValue("Cell "+r+" "+c);
			}
		}

		FileOutputStream fileOut = new FileOutputStream(excelFileName);

		//write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}
	
}