package com.shivraj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shivraj.model.Student;

@Repository("studDAO")
public class StudentDAO implements IStudentDAO 
{
	//Queries for DB Operation.
	private final String GET_ALLSTUD_QUERY = "SELECT ID,NAME,SUBJECT,MARKS FROM STUDENT";
	private final String GET_ALLSTUD_MARKS_QUERY = "SELECT MARKS FROM STUDENT";
	private final String INSERT_STUDENT_QUERY = "INSERT INTO STUDENT (ID,NAME,SUBJECT,MARKS) VALUES (STUD_SEQ.NEXTVAL,?,?,?)";
	private final String GET_STUD_BY_MARK_SUB = "SELECT NAME, SUBJECT, MARKS FROM STUDENT WHERE NAME LIKE  ?";
	private final String DELETE_BY_NAME_SUBJECT ="DELETE FROM STUDENT WHERE NAME LIKE ? AND SUBJECT LIKE ?";
	private final String UPDATE_MARKS_BY_NAME_SUBJECT ="UPDATE STUDENT SET MARKS = ? WHERE NAME LIKE ? AND SUBJECT LIKE ?";

	@Autowired
	private DataSource ds;
	

	//Getting All the data from DB.
	@Override
	public List<Student> getAllStudents() throws Exception 
	{
		List<Student> list = new ArrayList<Student>();
		
		try(Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(GET_ALLSTUD_QUERY);)
		{
			
			try(ResultSet rs = ps.executeQuery();)
			{
				while(rs.next())
				{
					Student student = new Student();
					student.setId(rs.getInt(1));
					student.setName(rs.getString(2));
					student.setSubject(rs.getString(3));
					student.setMarks(rs.getDouble(4));
					
					
					list.add(student);
				}
			}
			catch(Exception e)
			{
				throw e;
			}
			
		}
		catch(Exception e)
		{
			throw e;
		}
		
		return list;
	}

	// Getting Marks Column from DB.
	@Override
	public List<Double> getAverage() throws Exception 
	{
		List<Double> list = new ArrayList<Double>();
		
		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(GET_ALLSTUD_MARKS_QUERY);)
		{
			try(ResultSet rs = ps.executeQuery();)
			{
				double mark;
				while(rs.next())
				{
					mark = rs.getDouble(1);
					list.add(mark);
				}
				
			} 
			catch (Exception e) 
			{
				throw e;
			}
		} 
		catch (Exception e) 
		{
			throw e;
		}
		
		return list;
	}

	//Inserting Student Data into DB.
	@Override
	public int addStudent(Student student) throws Exception 
	{
		int result;
		try(Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(INSERT_STUDENT_QUERY);)
		{
			//setting values to string
			ps.setString(1, student.getName().toUpperCase());
			ps.setString(2, student.getSubject().toUpperCase());
			ps.setDouble(3, student.getMarks());
			
			//execute query 
			result = ps.executeUpdate();
			
			
		}
		catch(Exception e )
		{
			throw e;
		}
		
		return result;
	}

	@Override
	public List<Student> getStudentMarkandSub(String studName) throws Exception
	{
		List<Student> markAndSub = new ArrayList<Student>();
		
		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(GET_STUD_BY_MARK_SUB);)
		{
			ps.setString(1, "%" + studName.toUpperCase() + "%");
			
			
			try(ResultSet rs = ps.executeQuery();)
			{	
				
				while(rs.next())
				{
					Student student = new Student(rs.getString(1),rs.getString(2),rs.getDouble(3));
					markAndSub.add(student);
				}
			} 
			catch (Exception e) 
			{
				throw e;
			}
		} 
		catch (Exception e) 
		{
			throw e;
		}
		return markAndSub;
	}
		
	

@Override
public int deleteStudentByNameAndSubject(String name, String subject) throws Exception {
    try (Connection con = ds.getConnection();
         PreparedStatement ps = con.prepareStatement(DELETE_BY_NAME_SUBJECT)) {

        ps.setString(1, "%" + name.toUpperCase() + "%");
        ps.setString(2, "%" + subject.toUpperCase() + "%");

        return ps.executeUpdate(); // number of rows deleted
    }
}

@Override
public int updateMarksByNameAndSubject(double marks, String name, String subject) throws Exception
{
    try (Connection con = ds.getConnection();
         PreparedStatement ps = con.prepareStatement(UPDATE_MARKS_BY_NAME_SUBJECT)) {

        ps.setDouble(1, marks);
        ps.setString(2, "%" + name.toUpperCase() + "%");
        ps.setString(3, "%" + subject.toUpperCase() + "%");

        return ps.executeUpdate(); // number of rows updated
    }
}
	

}
