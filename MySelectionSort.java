package student;

//MySelectionSort class
public class MySelectionSort {

 //Method to sort studentArray in ascending order
 //Sort by student ID or student name depends on compareTo method chosen in Class Student
	public static void sort(Student[] studentArr){
		int minIndex = 0;
		for(int i = 0 ;i < studentArr.length ;i++){
			minIndex = i;      
			for(int j = i+1; j <studentArr.length ; j++){
				if(studentArr[j].compareTo(studentArr[minIndex]) < 0){
					minIndex = j;
             }
         }      
         //swap Student at index i with Student at minIndex for this iteration
         Student temp = studentArr[i];
         studentArr[i]= studentArr[minIndex];
         studentArr[minIndex] = temp;
     }
 }

	public static void main(String[] args){
		Student[] studentArr = {
             new Student("Cat",4.0),
             new Student("Alice",2.5),
             new Student("Catherine",3.5),
             new Student("Brian",1.9),
             new Student("Jackson",0.5),
             new Student("Zoe",3.88),
             new Student("Kathy",2.4),
             new Student("Katherine",1.7),
             new Student("Lily",1.0),
             new Student("Samuel",3.66),
		};
    
		System.out.println("The students are: ");
		for(Student s: studentArr)
			System.out.println(s);
    
		//sort the students
		sort(studentArr);
    
		System.out.println("\nThe students after sorting are: ");
		for(Student s: studentArr)
			System.out.println(s);
 }
}