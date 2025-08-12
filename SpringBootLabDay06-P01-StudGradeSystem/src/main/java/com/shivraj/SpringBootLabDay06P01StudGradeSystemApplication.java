package com.shivraj;

import java.util.List;
import java.util.Scanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.shivraj.controller.StudentController;
import com.shivraj.model.Student;

@SpringBootApplication
public class SpringBootLabDay06P01StudGradeSystemApplication 
{

	public static void main(String[] args) 
	{

		try(ConfigurableApplicationContext ctx = 
		SpringApplication.run(SpringBootLabDay06P01StudGradeSystemApplication.class, args);
				Scanner sc = new Scanner(System.in);)
		{
			StudentController controller = ctx.getBean("studControl",StudentController.class);
			
			while(true)
			{
				System.out.println("1. Add Student Details.");
				System.out.println("2. View All Student.");
				System.out.println("3. Get student marks by name and subject");
				System.out.println("4. Get all student Average.");
				System.out.println("5. Update student marks by name and subject");
				System.out.println("6. Delete student by name and subject");
				System.out.println("7. Exit");
				
				int choice = sc.nextInt();
				
				switch(choice)
				{
				case 1 : 
					sc.nextLine(); 
					System.out.print("Enter student name : ");
					String name = sc.nextLine();
					System.out.print("Enter subject name : ");
					String sub = sc.nextLine();
					System.out.print("Enter subject marks : ");
					double mark = sc.nextDouble();
					Student student = new Student(name,sub,mark);
					
					try 
					{
						String msg = controller.registerStudent(student);
						System.out.println(msg);
					} 
					catch (Exception e)
					{
						System.out.println("Internal Server Problem - Try Again..!");
					}
					break;
				case 2 : 
					try 
					{
						List <Student> list = controller.showAllStudent();
						for(Student stud : list)
							System.out.println(stud);
						
					} 
					catch (Exception e)
					{
						System.out.println("Internal Server Problem - Try Again..!");
					}
					break;
				case 3 :

					sc.nextLine();
				    System.out.print("Enter Student name : ");
				    String studName = sc.nextLine();
				    try 
				    {
				        List<Student> StudList = controller.showStudSubMark(studName);
				        for (Student s : StudList)
				            System.out.println(s);
				    }
				    catch (Exception e)
				    {
				        e.printStackTrace();
				        System.out.println("Internal Server Problem - Try Again..!");
				    }
				    break;
				case 4 : 
					try 
					{
						System.out.println("Average marks :: ");
						System.out.println(controller.showAllStudentAvg()); 
						
					} 
					catch (Exception e)
					{
						System.out.println("Internal Server Problem - Try Again..!");
					}
					break;
					
				case 5:
				    sc.nextLine();
				    System.out.print("Enter student name : ");
				    String updName = sc.nextLine();
				    
				    System.out.print("Enter subject name : ");
				    String updSubject = sc.nextLine();
				    
				    System.out.print("Enter new marks: ");
				    double newMarks = sc.nextDouble();

				    try
				    {
				    		String msg = controller.updateStudent(newMarks, updName, updSubject);
				        System.out.println(msg);
				    }
				    catch (Exception e) 
				    {
				        System.out.println("Internal Server Problem - Try Again..!");
				    }
				    break;

					
				case 6:
				    sc.nextLine(); 
				    System.out.print("Enter student name : ");
				    String delName = sc.nextLine();
				    System.out.print("Enter subject name : ");
				    String delSubject = sc.nextLine();
				    try 
				    {
				        String msg = controller.deleteStudent(delName, delSubject);
				        System.out.println(msg);
				    } 
				    catch (Exception e) 
				    {
				        System.out.println("Internal Server Problem - Try Again..!");
				    }
				    break;
				case 7 :
					System.out.println("Exiting Application...Thank You..!");
					System.exit(0);
					
				default:
						System.out.println("Invalid input number. Enter between 1 to 4 only..");
						
				}
			}
		}
	}

}
