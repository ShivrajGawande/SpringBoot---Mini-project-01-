package com.shivraj.service;

import java.util.List;

import com.shivraj.model.Student;

public interface IStudentService
{
	public List<Student> getAllStudents() throws Exception;
	public double getAverage() throws Exception;
	public String processStudent(Student student) throws Exception;
	public List<Student> getStudentSubMarks(String studName) throws Exception;
	public String removeStudent(String name, String subject) throws Exception;
	public String modifyMarks(double marks, String name, String subject) throws Exception;

}
