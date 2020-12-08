import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * stores all the students, admins, and courses data and accomplishes serialization and deserialization
 *
 */
public class Data implements Serializable {
	
	
	private ArrayList<Course> courses;
	
	private ArrayList<Student> students;
	
	private ArrayList<Admin> admins;
	
	public Data(ArrayList<Course> c,ArrayList<Student> s,ArrayList<Admin> a) {
		this.courses=c;
		this.students=s;
		this.admins=a;
	}
	
	public void serialize() {
		try
        {
            FileOutputStream fos = new FileOutputStream(new File("myData"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            System.out.println("Information serialized! See you next time!");
            oos.close();
            fos.close();
            
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
        }
	}
	
	public static Data deserialize() throws IOException, ClassNotFoundException{
		Data data =null;
		 FileInputStream fis = new FileInputStream("myData");
         ObjectInputStream ois = new ObjectInputStream(fis);
         data= (Data) ois.readObject();
         return data;
	}
	public ArrayList<Course> getCourses() {
		return this.courses;
	}
	
	public ArrayList<Student> getStudents(){
		return this.students;
	}
	
	public ArrayList<Admin> getAdmins(){
		return this.admins;
	}
}
