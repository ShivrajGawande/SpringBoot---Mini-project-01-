package com.shivraj.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.shivraj.model.Student;
import com.shivraj.service.IStudentService;

@Controller("studControl")
public class StudentController 
{
	@Autowired
	IStudentService service;
	
	public List<Student> showAllStudent() throws Exception
	{
		return service.getAllStudents();
	}
		
	public double showAllStudentAvg() throws Exception
	{
		return service.getAverage();
	}
	public String registerStudent(Student student) throws Exception
	{
		return service.processStudent(student);
	}
	public List<Student> showStudSubMark(String studName) throws Exception
	{
		return service.getStudentSubMarks(studName);
	}
	public String updateStudent(double mark ,String studName,String subName) throws Exception
	{
		return service.modifyMarks(mark, studName, subName); 
	}
	public String deleteStudent(String name, String subject) throws Exception 
	{
	    return  service.removeStudent(name, subject);
	}
	
}
