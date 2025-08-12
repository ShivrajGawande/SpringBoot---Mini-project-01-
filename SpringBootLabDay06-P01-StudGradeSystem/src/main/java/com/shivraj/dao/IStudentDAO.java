package com.shivraj.dao;

import java.util.List;

import com.shivraj.model.Student;

public interface IStudentDAO 
{
	public List<Student> getAllStudents() throws Exception;
	public List<Double> getAverage() throws Exception;
	public int addStudent(Student student) throws Exception;
	public List<Student> getStudentMarkandSub(String studName) throws Exception;
	public int deleteStudentByNameAndSubject(String name, String subject) throws Exception;
	public int updateMarksByNameAndSubject(double marks, String name, String subject) throws Exception;


}
