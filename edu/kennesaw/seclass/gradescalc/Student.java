package edu.kennesaw.seclass.gradescalc;

public class Student { 
	
	private String name;
	private String id;
	private double attendance;
	
	public Student(){
		name = "";
		id = "";
		attendance = 0.0;
	}
	
	public Student(String name, String id, double attendance) {
		this.name = name;
		this.id = id;
		this.attendance = attendance;
	}
	
	public String getName(){
		return name;
		
	}
	
	public String getId() {
		return id;
	}
	
	public int getAttendance() { return (int)attendance;	}
	
	public void setName(String n)
	{
		if(n.length() > 0)
		{
			this.name = n;
		}
	}
	
	public void setId(String i)
	{
		if(i.length() > 0)
		{
			this.id = i;
		}
	}
	
	public void setAttendance(int a)
	{
		if(a >= 0)
		{
			this.attendance = a;
		}
	}
}