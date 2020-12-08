import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * the class that defines the operations that a student can do
 *
 *
 */
public class Student extends User implements EnrolledStudent,Serializable{
	
	
	private ArrayList<Course> registeredCourses=new ArrayList<Course>();
	
	public Student(String u, String p, String f, String l) {
		
		super(u,p,f,l);
	}
	
	public void viewAllCourses(ArrayList<Course> courses) {
		for (int i=0;i<courses.size();i++) {
			Course course=courses.get(i);
			System.out.println("course name: "+ course.getCourseName()
			+" course ID: "+course.getCourseId()+" "+"maximum students: "+
			course.getMaximumStudents()+" currentStudents: "+course.getCurrentStudents()+
			" instructor: "+course.getInstructor()+"section number: "+
			course.getSectionNumber()+" location: "+course.getLocation());
		}
	}
	
	public void viewAvailableCourses(ArrayList<Course> courses) {
		for (int i=0;i<courses.size();i++) {
			if (courses.get(i).getCurrentStudents()<courses.get(i).getMaximumStudents()) {
				Course course=courses.get(i);
				System.out.println("course name: "+ course.getCourseName()
				+" course ID: "+course.getCourseId()+" "+"maximum students: "+
				course.getMaximumStudents()+" currentStudents: "+course.getCurrentStudents()+
				" instructor: "+course.getInstructor()+"section number: "+
				course.getSectionNumber()+" location: "+course.getLocation());
			}
		}
	}
	
	public void registerACourse(ArrayList<Course> courses) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the course name, sction number, and your full name "
				+ "(separated by comma):");
		String a=sc.nextLine();
		String[] answer=a.split(",");
		boolean foundCourse=false;
		for (int i=0;i<courses.size();i++) {
			if (answer[0].equals(courses.get(i).getCourseName())&&(
				answer[1].equals(courses.get(i).getSectionNumber()))){
					courses.get(i).addStudent(this);
					this.registeredCourses.add(courses.get(i));
					foundCourse=true;
					System.out.println("Susccessully registered!");
					break;
			}
		}
		if (!foundCourse) {
			System.out.println("Course not found!");
		}
	}
	
	public void withdrawACourse(ArrayList<Course> courses) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the course name and your full name "
				+ "(separated by comma):");
		String a =sc.nextLine();
		String[] answer=a.split(",");
		boolean foundCourse=false;
		for (int i=0;i<courses.size();i++) {
			if (answer[0].equals(courses.get(i).getCourseName())&&(
				courses.get(i).getStudents().contains(this))){
				courses.get(i).deleteStudent(this);
				this.registeredCourses.remove(courses.get(i));
				System.out.println("Successfully removed!");
				foundCourse=true;
				break;
			}else if(answer[0].equals(courses.get(i).getCourseName())){
				foundCourse=true;
				System.out.println("You are originally ont in the course!");
				break;
			}
		}
		if (!foundCourse) {
			System.out.println("Course not found!");
		}
	}
	
	public String toString() {
		return this.getName()+" "+this.getRegisteredCourses();
	}
	
	public void addCourse(Course c) {
		this.registeredCourses.add(c);
	}
	
	public void removeCourse(Course c) {
		this.registeredCourses.remove(c);
	}

	public ArrayList<Course> getRegisteredCourses() {
		return registeredCourses;
	}

	public void setRegisteredCourses(ArrayList<Course> registeredCourses) {
		this.registeredCourses = registeredCourses;
	}	

}
