package com.shivraj.service;

import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shivraj.dao.IStudentDAO;
import com.shivraj.model.Student;

@Service("studService")
public class StudentService implements IStudentService
{
	//getting DAO class.
	@Autowired
	private IStudentDAO studentDAO;
	
	// returning sorted list in descending order (Sorted by marks).
	@Override
	public List<Student> getAllStudents() throws Exception 
	{
		List<Student> sortedList = studentDAO.getAllStudents();
		sortedList.sort(Comparator.comparingDouble(Student::getMarks).reversed());
		return sortedList;
	}
	
	// returning Average marks of all student.
	@Override
	public double getAverage() throws Exception
	{
		List<Double> marks = studentDAO.getAverage();
		
		double total=0; int count=0;
		
		//calculating Average.
		for(double mark : marks)
		{
			total+=mark;
			count++;
		}
		double average = total/count;
		
		
		//returning Average.
		return average;
	
	}
	
	//Success or failure message on student data insert
	@Override
	public String processStudent(Student student) throws Exception
	{
		int result = studentDAO.addStudent(student);
		return result>0?"Student "+student.getName()+" is registed successfully":"Student registration field..! Try again..!";
	}

	@Override
	public List<Student> getStudentSubMarks(String studName) throws Exception 
	{
		List<Student> subMarks = studentDAO.getStudentMarkandSub(studName.toUpperCase());
		return subMarks;
		
	}
	
	@Override
	public String removeStudent(String name, String subject) throws Exception
	{
	    int result =  studentDAO.deleteStudentByNameAndSubject(name, subject);
	    return result > 0 ? "Student deleted successfully." : "No matching student found.";

	}
	
	@Override
	public String modifyMarks(double marks, String name, String subject) throws Exception
	{
	    int rows = studentDAO.updateMarksByNameAndSubject(marks, name, subject);
	    return rows > 0 ? rows + " record(s) updated successfully." : "No matching student found.";
	}


	
}
