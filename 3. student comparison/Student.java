package student;

//Student Class with implementation of compareTo using studentID or name
public class Student implements Comparable<Student>{
	private int studentID;
	private String name;
	private double gpa;

	private static int currStudentID;

 //default constructor for Student
	public Student(){
     currStudentID++;//increments currStudentID by 1
     this.studentID = currStudentID;//set studentID to currStudentID
	}
	
 //parameterized constructor
	public Student(String name, double gpa){
		this();
		this.name = name;
		this.gpa = gpa;
	}
	
 //getter for studentID
	public int getStudentID() {
		return studentID;
	}
//setter for name
	public void setName(String name) {
		this.name = name;
	}
 //getter for name
	public String getName() {
		return name;
	}
//setter for gpa
	 public void setGpa(double gpa) {
		 this.gpa = gpa;
	 }
 //getter for gpa
	 public double getGpa() {
		 return gpa;
 }
@Override //return string representation of Student object
 	public String toString() {
     	return "Student [studentID=" + studentID + ", name=" + name + ", gpa=" + gpa + "]";
 	}

 @Override
 //Implementation of compareTo
 //Part 1: Sorting using studentID
 //Return -1 if current student's id is smaller than otherStudent's id
 //return 1 if current student's id is greater than othertudent's id
 //return 0 if both are the same

/*	public int compareTo(Student otherStudent) { 
	 if(this.studentID < otherStudent.studentID) 
	 		return -1; 
	 	if(this.studentID > otherStudent.studentID) 
	 		return 1; 
	 	else {
	 		return 0; }
	 }
	 */
 //Part 2: Sorting using student name
 	public int compareTo(Student otherStudent) {  
	 	return this.name.compareTo(otherStudent.name); 
 	}
}