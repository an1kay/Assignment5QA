package edu.kennesaw.seclass.gradescalc;

import java.util.HashSet;

public class GradesDB {
	
	GradesDB(String gradeSheet){
		
	}
	
	public int getNumStudents() {
		return 0;		
		
	}
	
	public int getNumAssignments() {
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
