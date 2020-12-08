import java.io.Serializable;
import java.util.ArrayList;
/**
 * defines the attributes of a course and performs basic adding and dropping students from course
 * 
 *
 */
public class Course implements Comparable<Course> ,Serializable{
	

	private String courseName;
	private String courseId;
	private int maximumStudents;
	private int currentStudents;
	private String studentNames;
	private ArrayList<Student> students=new ArrayList<Student>();
	private String studentIds="";
	private String instructor;
	private String sectionNumber;
	private String location;
	
	public Course(String cn, String ci, int max, int current, String n, String i, String section, String l) {
		this.courseName=cn;
		this.courseId=ci;
		this.maximumStudents=max;
		this.currentStudents=current;
		if (n.equals("NULL")){
			this.studentNames="";
		}else {
		this.studentNames=n;
		}
		this.instructor=i;
		this.sectionNumber=section;
		this.location=l;
		
	}
	
	public void addStudent(Student s) {
		if (currentStudents>=maximumStudents) {
			System.out.println("Sorry, the course is already FULL.");
		}
		else {
		students.add(s);
		this.currentStudents++;
		studentNames=studentNames+s.getName()+",";
		studentIds=studentIds+s.getUsername()+",";
		}
	}
	
	public void deleteStudent(Student s) {
		students.remove(s);
		this.currentStudents--;
		studentNames="";
		studentIds="";
		for (int i=0;i<students.size();i++) {
			studentNames=studentNames+students.get(i).getName()+",";
			studentIds=studentIds+students.get(i).getUsername()+",";
		}
	}
	
	public String toString() {
		return "course name: "+ courseName+" course ID: "+courseId+" "+"maximum students: "+
	maximumStudents+" currentStudents: "+currentStudents+" studentNames: " +studentNames+
	" studentIDs: "+studentIds+
	" instructor: "+instructor+" section number: "+sectionNumber+" location: "+location;
		
	}
	
	
	public int getCurrentStudents() {
		return this.currentStudents;
	}
	
	public int getMaximumStudents() {
		return this.maximumStudents;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getSectionNumber() {
		return sectionNumber;
	}

	public void setSectionNumber(String sectionNumber) {
		this.sectionNumber = sectionNumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setMaximumStudents(int maximumStudents) {
		this.maximumStudents = maximumStudents;
	}

	public void setCurrentStudents(int currentStudents) {
		this.currentStudents = currentStudents;
	}

	public String getStudentNames() {
		return studentNames;
	}

	public void setStudentNames(String studentNames) {
		this.studentNames = studentNames;
	}

	@Override
	public int compareTo(Course o) {
		if (this.currentStudents>o.getCurrentStudents()) return 1;
		if (this.currentStudents<o.getCurrentStudents()) return -1;
		else return 0;
	}
	
	
	
	
	
	
	

}
