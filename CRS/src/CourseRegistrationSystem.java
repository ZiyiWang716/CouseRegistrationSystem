import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Program to perform the Course Registration System
 * @author Ziyi Wang
 * @version 0.1
 */

public class CourseRegistrationSystem implements Serializable {
	
	public static void main(String[] args) throws ClassNotFoundException, IOException{
		
		ArrayList<Course> courses=new ArrayList<Course>(); //all the courses
		ArrayList<Student> students= new ArrayList<Student>(); //all the students that have been registered into the system
		ArrayList<Admin> admins=new ArrayList<Admin>();//all the administrators in the system
		Data data=null;
		Data dataOut=null;
		
		try
        {
          data=Data.deserialize();
          System.out.println(":::::::");
          courses=data.getCourses();
          students=data.getStudents();
          admins=data.getAdmins();
            
         }catch(IOException ioe){
     		Scanner file = new Scanner(new File("src/MyUniversityCourses.csv"));
    		String textString= "";
    		while(file.hasNextLine()) {
    			String line=file.nextLine();
    			textString=textString+line+",";
    		}
    		
    		String[] rawData= textString.split(",");
    		//System.out.println(rawData.length);
    		int row=( rawData.length/8)-1;
    		String [][] courseData= new String[row][8] ;
    		int index=8;
    		for (int i=0; i<row; i++) {
    			for (int j=0;j<8;j++) {
    				courseData[i][j]=rawData[index+j];
    			}
    			index+=8;
    		}
    		
    		for (String[] eachCourse:courseData ) {
    			
    				String courseName=eachCourse[0];
    				String courseId=eachCourse[1];
    				int maximumStudents=Integer.parseInt(eachCourse[2]);
    				int currentStudents=Integer.parseInt(eachCourse[3]);
    				String studentNames=eachCourse[4];
    				String instructors=eachCourse[5];
    				String sectionNumber=eachCourse[6];
    				String location=eachCourse[7];
    				courses.add(new Course(courseName, courseId, maximumStudents, currentStudents,
    						studentNames, instructors, sectionNumber, location));
    				

    		}
    		admins.add(new Admin("Admin","Admin001","Hilda","Wang"));
    		//System.out.println(courses.get(0).getCourseId());

    		
    	}catch(ClassNotFoundException c){
            System.out.println("Class not found");
            c.printStackTrace();
            return;
         }//finish set-up (de-serialization or file-open)
		
		boolean successfulStart=false;
		
		while (!successfulStart) {
		
			System.out.println("Are you a 1.administrator or 2.student?");
			Scanner sc= new Scanner (System.in);
			int ans1= sc.nextInt();
			if (ans1==1) {
				successfulStart=true;
				Admin admin=null;
				boolean loggedIn=false;
				System.out.println("username: ");
				String ans2= sc.next();
				System.out.println("password: ");
				String ans3=sc.next();
				System.out.println(admins.get(0).getPassword());
				while (!loggedIn) {
					for (int i =0;i<admins.size();i++) {
						if (((admins.get(i).getUsername()).equals(ans2)) &&
								((admins.get(i).getPassword()).equals(ans3))) { 
							loggedIn=true;
							admin=admins.get(i);
							System.out.println("Successfully logged in!");
							break;
						}
					}
					if (!loggedIn) {
						System.out.println("Username or Password wrong!");
						System.out.println("Please reenter username: ");
						ans2=sc.next();
						System.out.println("Please reenter password: ");
						ans3=sc.next();
					}
				}
				while (loggedIn) {
					System.out.println("What would you like to do? (1.Course Ma"
								+ "nagement 2.Reports)");
					int ansA1=sc.nextInt();
					if (ansA1==1) {
						System.out.println("What specific task do you want to compelet?\n"
								+ "1.Create a new Course\n"
								+ "2.Delete a course\n"
								+ "3.Edit a Course\n"
								+ "4.Display information for a given course\n"
								+ "5.Register a student\n"
								+ "6.Exit");
						int ansA2=sc.nextInt();
						switch (ansA2) {
							case 1:
								admin.createCourse(courses);
								break;
							case 2:
								admin.deleteCourse(courses,students);
								break;
							case 3:
								admin.editCourse(courses,students);
								break;
							case 4:
								admin.displayInformation(courses);
								break;
							case 5:
								admin.registerStudent(students);
								break;
							case 6:
								loggedIn=false;
								break;
						}
					}
					else if(ansA1==2) {
						System.out.println("What specific task do you want to complete?\n"
								+ "1.view all courses\n"
								+ "2.view all courses that are full\n"
								+ "3.write to a file the list of courses that are full\n"
								+ "4.view the names of the students that are registered in a specific course\n"
								+ "5.view the list of courses that a given student is registere in\n"
								+ "6.srot the courses (based on the current number of students registered)\n"
								+ "6.exit");
						int ansA3=sc.nextInt();
						switch(ansA3){
							case 1:
								admin.viewAllCourses(courses);
								break;
							case 2:
								admin.viewFullCourses(courses);
								break;
							case 3:
							try {
								admin.writeFullCoursesFile(courses);
							} catch (Exception e) {
								e.printStackTrace();
							}
							break;
							case 4:
								admin.viewStudentsOfCourse(courses);
								break;
							case 5:
								admin.viewCoursesOfStudent(students);
								break;
							case 6:
								admin.sortCourses(courses);
								break;
							case 7:
								loggedIn=false;
								break;
							
						}
					}//ansA1
				}//while
				
			}//if administrator or student
			else if (ans1==2) {
				successfulStart=true;
				Student student=null;
				boolean loggedIn=false;
				System.out.println("username: ");
				String ans2= sc.next();
				System.out.println("password: ");
				String ans3=sc.next();
				while (!loggedIn) {
					for (int i =0;i<students.size();i++) {
						if (((students.get(i).getUsername()).equals(ans2)) &&
								((students.get(i).getPassword()).equals(ans3))) { 
							loggedIn=true;
							student=students.get(i);
							System.out.println("Successfully logged in!");
							break;
						}
					}
					if (!loggedIn) {
						System.out.println("Username or Password wrong!");
						System.out.println("Please reenter username: ");
						ans2=sc.nextLine();
						System.out.println("Please reenter password: ");
						ans3=sc.nextLine();
					}
				}
				while (loggedIn) {
					System.out.println("What would you like to do?\n"
							+ "1.view all courses\n"
							+ "2.view courses that are not full\n"
							+ "3.register a course\n"
							+ "4.withdraw a course\n"
							+ "5.view all courses you are currently registered in\n"
							+ "6.exit");
					int ansS=sc.nextInt();
					switch(ansS) {
						case 1:
							student.viewAllCourses(courses);
							break;
						case 2:
							student.viewAvailableCourses(courses);
							break;
						case 3:
							student.registerACourse(courses);
							break;
						case 4:
							student.withdrawACourse(courses);
							break;
						case 5:
							student.viewRegisteredCourses(student);
							break;
						case 6:
							loggedIn=false;
							break;
					}
				}
			
			}
			else { 
				System.out.println("Invalid input! please try again!");
			}
		}//big while loop
		
//		System.out.println(courses.toString());
//		System.out.println(admins.toString());
//		System.out.println(students.toString());
		
		dataOut=new Data(courses,students,admins);
		dataOut.serialize();
		
		
	}
}

