import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * a class that defines the operations that an administrator can do
 */
public class Admin extends User implements SchoolAdministrator,Serializable {
	
	public Admin(String u, String p, String f, String l) {
		super(u,p,f,l);
	} 
	
	public void createCourse(ArrayList<Course> c) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Please enter coursename, courseID, maximumStudents, instructor"
				+ ", section number, and location in order (separated by comma: ");
		String a=sc.nextLine();
		String[] answer=a.split(",");
		Course newCourse=new Course(answer[0],answer[1],Integer.parseInt(answer[2]),0,null,answer[3],answer[4],
				answer[5]);
		c.add(newCourse);
		System.out.println("Successfully created!");
	}
	
	public void deleteCourse(ArrayList<Course> c,ArrayList<Student> s) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter the courseID of the course:");
		String answer=sc.nextLine();
		for(int i=0;i<c.size();i++) {
			if ((c.get(i).getCourseId()).equals(answer)) {
				Course courseToRemove=c.get(i);
				c.remove(courseToRemove);
				for (int k=0;k<s.size();k++) {
					if (s.get(k).getRegisteredCourses().contains(courseToRemove)) {
						s.get(k).removeCourse(courseToRemove);
						System.out.println("removed!");
					}
				}
				System.out.println("successfully deleted!");
				break;
			}
		}
	}
	
	
	public void editCourse(ArrayList<Course> courses, ArrayList<Student> students) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the courseID of the course you want to edit:");
		String ans1=sc.nextLine();
		Course courseToEdit = null;
		boolean foundCourse=false;
		for(int i=0;i<courses.size();i++) {
			if (courses.get(i).getCourseId().equals(ans1)) {
				courseToEdit=courses.get(i);
				foundCourse=true;
				break;
			}
		}
		if (foundCourse) {
			System.out.println("What do you want to edit?\n"
					+ "1. change maximum students allowed\n"
					+ "2. add a student\n"
					+ "3. drop a student\n"
					+ "4. change instructor name\n"
					+ "5. change section number\n"
					+ "6. change location");
			int ans2=sc.nextInt();
			switch (ans2) {
				case 1:
					System.out.println("Enter the new maximum number:");
					int ans21=sc.nextInt();
					courseToEdit.setMaximumStudents(ans21);
					break;
				case 2:
					if (!(students.size()==0)) {

						System.out.println("Enter the student's username:");
						String ans22=sc.next();
						boolean foundStudent=false;
						for (int i= 0;i<students.size();i++) {
							if (courseToEdit.getStudents()!=null) {
								if(((students.get(i).getUsername()).equals(ans22))&&!((courseToEdit.getStudents()).contains(students.get(i)))) {
									courseToEdit.addStudent(students.get(i));
									students.get(i).addCourse(courseToEdit);
									System.out.println("Susccessfully added!");
									foundStudent=true;
									break;
								}
								else if(students.get(i).getUsername().equals(ans22)&&(courseToEdit.getStudents()).contains(students.get(i))){
									System.out.println("This student is already in this course!");
									foundStudent=true;
									break;
								}
							}else{
								if((students.get(i).getUsername()).equals(ans22)) {
									students.get(i).addCourse(courseToEdit);
									courseToEdit.addStudent(students.get(i));
									System.out.println("Successfully added!");
									foundStudent=true;
									break;
								}
							}
						}
						
						if (foundStudent=false){
							System.out.println("The student has not been registered to the system yet.");
							break;
						}
					
				}
				else {
					System.out.println("There are currently no student registered in the system!");
					break;
				}
				break;
				
				case 3:
					if (!(courseToEdit.getStudents().size()==0)) {

						System.out.println("Enter the student's username: ");
						String ans23=sc.next();
						boolean foundStudent=false;
						for (int i =0;i<courseToEdit.getStudents().size();i++) {
							if (courseToEdit.getStudents().get(i).getUsername().equals(ans23)) {
								foundStudent=true;
								Student student=courseToEdit.getStudents().get(i);
								courseToEdit.deleteStudent(student);
								student.removeCourse(courseToEdit);
								System.out.println("Successfully removed!");
								break;
							}
						}
						if (foundStudent=false) {
							System.out.println("This student is not found in this course!");
						}
					break;
					}else System.out.println("No students in the class right now!");
					break;
				case 4:
					System.out.println("Enter the new instructor's name:");
					String ans24=sc.next();
					courseToEdit.setInstructor(ans24);
					break;
				case 5:
					System.out.println("Enter the new section number: ");
					String ans25=sc.next();
					courseToEdit.setSectionNumber(ans25);
					break;
				case 6:
					System.out.println("Enter the new location: ");
					String ans26=sc.next();
					courseToEdit.setLocation(ans26);
					break;
				}
		}else
			System.out.println("Course not found.");
					
			
	}
	
	public void displayInformation(ArrayList<Course> courses) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the courseID for that course: ");
		String answer=sc.next();
		boolean foundCourse=false;
		for(int i=0; i<courses.size();i++) {
			if (courses.get(i).getCourseId().equals(answer)){
				System.out.println(courses.get(i).toString());
				foundCourse=true;
				break;
				
			}
		}
		if (!foundCourse) {
			System.out.println("Course not found!");
		}
		
	}
	
	public void registerStudent(ArrayList<Student> students) {
		Scanner sc=new Scanner(System.in);
		String[] answer=null;
		while(answer==null||answer.length!=4) {
			System.out.println("Please enter the student's "
					+ "initial username, initial password, First name, and last name in order"
					+ "(separated by comma): ");
			String a=sc.nextLine();
			answer= a.split(",");
		}
		boolean legal=false;
		if (students.size()==0) {
			students.add(new Student(answer[0],answer[1],answer[2],answer[3]));
			System.out.println("Successfully registered!");
		}
		else {
			while (!legal) {
				for (int i =0;i<students.size();i++) {
					if (students.get(i).getUsername().equals(answer[0])) {
						System.out.println("The username has already been taken, please enter another "
								+ "username: ");
						answer[0]=sc.nextLine();
						legal=false;
						break;
					}else
						legal=true;
				}
			}
			students.add(new Student(answer[0],answer[1],answer[2],answer[3]));
			System.out.println("Successfully registered!");
			}
		}
			
					
	
	
	public void viewAllCourses(ArrayList<Course> courses) {
		for(int i=0;i<courses.size();i++) {
			System.out.println(courses.get(i).toString());
		}
	}
	
	public void viewFullCourses(ArrayList<Course> courses) {
		for (int i=0; i<courses.size();i++) {
			if (courses.get(i).getCurrentStudents()==courses.get(i).getMaximumStudents()) {
				courses.get(i).toString();
			}
		}
	}
	
	public void writeFullCoursesFile(ArrayList<Course> courses) throws IOException {
		File file=new File("src/FullCourses.txt");
		FileWriter writer = new FileWriter("src/FullCourses.txt");
		for (int i=0;i<courses.size();i++) {
			Course course=courses.get(i);
			String text=course.getCourseId()+","+course.getCourseName()+","+course.getMaximumStudents()+","
			+course.getCurrentStudents()+","+course.getStudentNames()+","+
					course.getInstructor()+","+course.getSectionNumber()+
					","+course.getLocation()+"\n";
			writer.write(text);
					
		}
		writer.close();
	}
	
	public void viewStudentsOfCourse(ArrayList<Course> courses) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the course ID: ");
		String answer=sc.nextLine();
		boolean foundCourse=false;
		for (int i=0;i<courses.size();i++) {
			if (courses.get(i).getCourseId().contentEquals(answer)) {
				foundCourse=true;
				System.out.println(courses.get(i).getStudentNames());
				break;
			}
		}
		if (foundCourse==false) {
			System.out.println("Course not found!");
		}
		
	}
	
	public void viewCoursesOfStudent(ArrayList<Student> students) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the student's full name: ");
		String answer=sc.nextLine();
		boolean foundStudent=false;
		for (int i=0;i<students.size();i++) {
			if (students.get(i).getName().equals(answer)) {
				foundStudent=true;
				students.get(i).viewRegisteredCourses(students.get(i));
				break;
			}
			
		}
		if (foundStudent=false) {
			System.out.println("Student not found!");
		}
	}
	
	
	public void sortCourses(ArrayList<Course> courses) {
	    for (int i = 0; i < courses.size(); i++) {
	        for (int j = 0; j <i; j++) {
	            if(courses.get(j).compareTo(courses.get(j-1)) > 0) {
	            	Course temp=courses.get(i);
	            	courses.set(i, courses.get(j));
	            	courses.set(j, temp);
	            	
	            }
	        }
	    }
	    System.out.println("The courses have been sorted!");
	}
	
	public String toString() {
		return this.getUsername()+" "+this.getPassword()+" " +this.getName();
	}
	
	
		
		
}


