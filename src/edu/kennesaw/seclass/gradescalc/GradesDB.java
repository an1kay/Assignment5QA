package edu.kennesaw.seclass.gradescalc;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

public class GradesDB {

	private HashSet<Student> students = new HashSet();

	GradesDB(String path) throws Exception {
		//imports grade sheet based on a path
		String studentInfoSheet = "StudentsInfo";
		File DBFile = new File(path);
		XSSFWorkbook workBook;
		XSSFSheet workSheet = null;
		try {
			FileInputStream fis = new FileInputStream(DBFile);
			workBook = 	ExtractWorkbook(fis);
			workSheet = ExtractSheet(workBook, studentInfoSheet);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (workSheet != null	)
		{
			ExtractStudents(workSheet);

		} else
		{
			throw new NullPointerException("WorkSheet is Empty, Check path");
		}



	}

	private XSSFWorkbook ExtractWorkbook(FileInputStream fis) {
		XSSFWorkbook workBook = null;
		try {
			workBook = new XSSFWorkbook(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return workBook;
	}

	//workbook helper methods
	private XSSFSheet ExtractSheet(XSSFWorkbook wb, String sheetName)
	{//purpose of method is to successfully extract sheets needed
		XSSFSheet workSheet = null;

		workSheet = wb.getSheet(sheetName);

		return workSheet;
	}

	private void ExtractStudents(XSSFSheet workSheet)
	{
		Iterator<Row> RowIterator = workSheet.iterator();
	}










	
	public int getNumStudents() {
		// counts the number of students in the file and returns the resulting number
		return 0;		
		
	}
	
	public int getNumAssignments() {
		//counts the number of
		return 0;
		
	}
	
	public int getNumProjects() {
		return 0;
		
	}
	
	public HashSet<Student> getStudents(){
		return new HashSet<Student>();
	}
	
	public Student getStudentByName(String name) {
		return new Student();
	}
	
	public Student getStudentByID(String id) {
		return new Student();
	}

}
