import java.io.IOException;
import java.util.ArrayList;

/**
 * the interface that defines what the administrators can do and implemented by Admin class
 *
 *
 */
public interface SchoolAdministrator {
	
	public abstract void createCourse(ArrayList<Course> c);
	
	public abstract void deleteCourse(ArrayList<Course> c,ArrayList<Student> s);
	
	public abstract void editCourse(ArrayList<Course> courses, ArrayList<Student> students);
	
	public abstract void displayInformation(ArrayList<Course> courses);
	
	public abstract void registerStudent(ArrayList<Student> students);
	
	public abstract void viewAllCourses(ArrayList<Course> courses);
	
	public abstract void viewFullCourses(ArrayList<Course> courses);
	
	public abstract void writeFullCoursesFile(ArrayList<Course> courses)throws IOException;
	
	public abstract void viewStudentsOfCourse(ArrayList<Course> courses);
	
	public abstract void viewCoursesOfStudent(ArrayList<Student> students);
	
	public abstract void sortCourses(ArrayList<Course> courses);

}
