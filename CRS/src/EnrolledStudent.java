import java.util.ArrayList;

/**
 * the interface that defines what the students can do and implemented by Student class
 *
 *
 */

public interface EnrolledStudent {
	
	public abstract void viewAllCourses(ArrayList<Course> courses);
	
	public abstract void viewAvailableCourses(ArrayList<Course> courses);
	
	public abstract void registerACourse(ArrayList<Course> courses);
	
	public abstract void withdrawACourse(ArrayList<Course> courses);
	
	public abstract void addCourse(Course c);
	
	public abstract void removeCourse(Course c);
}

