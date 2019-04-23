package edu.kennesaw.seclass.gradescalc;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Iterator;
import java.text.NumberFormat;

public class GradesDB {

	private HashSet<Student> students = new HashSet<Student>();

	GradesDB(String path) throws Exception {
		//imports grade sheet based on a path
		String studentInfoSheet = "StudentsInfo";
		String attendanceSheet = "Attendance";
		File DBFile = new File(path);
		if (!DBFile.exists())
		{
			throw new Exception("File does not exist at this path!!");
		}
		XSSFWorkbook workBook = null;
		XSSFSheet infoWorkSheet = null;
		XSSFSheet attWorkSheet = null;

		try {
			FileInputStream fis = new FileInputStream(DBFile);
			//workBook = new XSSFWorkbook(path);
			workBook = new XSSFWorkbook(DBFile);
			//workBook = 	ExtractWorkbook(DBFile);
			infoWorkSheet = ExtractSheet(workBook, studentInfoSheet);
			attWorkSheet = ExtractSheet(workBook,attendanceSheet);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e)
		{
			throw new Exception("Exception is: " + e.toString());
		}
		if (infoWorkSheet != null && attWorkSheet != null)
		{
			ExtractStudents(infoWorkSheet,attWorkSheet);

		} else
		{
			throw new NullPointerException("WorkSheet is Empty, Check path");
		}



	}

	private XSSFWorkbook ExtractWorkbook(File file) {
		XSSFWorkbook workBook = null;
		try {
			workBook = new XSSFWorkbook(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
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

	private void ExtractStudents(XSSFSheet studentInfoSheet, XSSFSheet studentAttendanceSheet)
	{
		//okay, in the scope of this method, everything has worked and we have a worksheet to work with
		//we just need to extract the information and create and add students to the hash set
		/*	Name	ID		Email	C	C++	Java	CS_Job_Ex
		*	str		num		str		num	num	num		str
		* */
		String name = "", id = "", attendance= "";
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(0);
		nf.setGroupingUsed(false);
		nf.setRoundingMode(RoundingMode.FLOOR);		




		Iterator<Row> InfoRowIterator = studentInfoSheet.iterator();
		Iterator<Row> AttRowIterator = studentAttendanceSheet.iterator();
		Row Inforow = InfoRowIterator.next(); //used to set the row down one to correct placement.
		Row AttRow = AttRowIterator.next();

		while (InfoRowIterator.hasNext())
		{
			Inforow = InfoRowIterator.next();

			Iterator<Cell> InfocellIterator = Inforow.cellIterator();
			for (int cellPos = 0; InfocellIterator.hasNext(); cellPos++)//inner cell loop, only make student after done, iterates through info sheet
			{
				Cell cell = InfocellIterator.next();
				if (cellPos > 2)
				{
					break;
				} else if (cellPos == 0)
				{
					name = cell.getStringCellValue();
				} else if (cellPos == 1) {
					id = nf.format(cell.getNumericCellValue());
				}
			}

			AttRow = AttRowIterator.next();
			Iterator<Cell> AttCellIterator = AttRow.cellIterator();
			for (int cellPos = 0; AttCellIterator.hasNext(); cellPos++)
			{
				Cell cell = AttCellIterator.next();
				if (cellPos == 1)
				{
					attendance = Double.toString(cell.getNumericCellValue());
				}
			}

			students.add(new Student(name,id,Double.parseDouble(attendance)));
		}
	}










	
	public int getNumStudents() {
		// counts the number of students in the file and returns the resulting number
		return students.size();
		
	}
	
	public int getNumAssignments() {
		//counts the number of
		return 0;
		
	}
	
	public int getNumProjects() {
		return 0;
		
	}
	
	//Returns private Hashset 'students'
	public HashSet<Student> getStudents(){
		return students;
	}
	
	//Iterates through students Hashset, comparing names as it goes. Once it finds a match, breaks out then returns it
	public Student getStudentByName(String name) {		
		Student stu = new Student();
		
		for(Student s : students) {
			if(s.getName().compareTo(name) == 0) {
				stu = s;
				break;
				//System.out.println(s.getName() + s.getId());				
			}
		}
		return stu;
	}
	
	//Iterates through students Hashset, comparing id's as it goes. Once it finds a match, breaks out then returns it
	public Student getStudentByID(String id) {
		Student stu = new Student();
		
		for(Student s : students) {
			if(s.getId().compareTo(id) == 0) {
				stu = s;
				break;
				//System.out.println(s.getName() + s.getId());				
			}
		}
		return stu;
	}

}
