package personIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

class Person implements Serializable {
	
	String name;
	int age;
	
	//constructor for Person class
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	//override method for output format
	public String toString() {
		return String.format ("Person [name=%s, age=%d]",name, age); 
	}
}

//class for read and write to binary file
public class PersonIO {
	
	String nameP; //name for each person input
	int ageP; //age for each person input
	ObjectInputStream ois = null;
	ObjectOutputStream oos = null;
	static Scanner kbInput = new Scanner(System.in);
	
	//initiate oos
	public PersonIO() {
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream("objOut.dat"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	//add person to binary file
	public void add() {
		
		System.out.println("Please enter the person's name:");
		nameP = kbInput.nextLine();
		System.out.println("Please enter the person's age:");
		ageP = kbInput.nextInt();
		
		Person p = new Person(nameP, ageP); //p is each person input
		
		try {
			oos.writeObject(p);
			} catch (IOException e) {
				e.printStackTrace();
			}			
	}
	//read person from binary file and display on console
	public void display() {
		
		Person displayP = null; //person display for each iteration
		boolean eof = false; // default NOT end of file
		
		try {
			ois = new ObjectInputStream(new FileInputStream("objOut.dat"));
			System.out.println("***********************");
			while (!eof) {
				try {
					displayP = (Person)ois.readObject();
					System.out.println(displayP);
				} catch (ClassNotFoundException | IOException e) {
					eof = true;
				}
			}
			System.out.println("***********************");
		} catch (IOException e) {
		}			
	}

	public static void main(String[] args) throws IOException  {
		PersonIO mp1 = new PersonIO(); //instantiate PersonIO object
		
		try {
			int option = -1;
			while (option != 0) {
				System.out.println("Please choose an option:");
				System.out.println("0: quit");
				System.out.println("1: add");
				System.out.println("2: display");
				option = kbInput.nextInt();
				kbInput.nextLine();
				switch (option) {
				case 0:
					System.out.println("Bye");
					break;					
				case 1:
					mp1.add();
					break;
				case 2:
					mp1.display();
					break;
				}

			}
		} finally {
			if (mp1.oos != null) {
				mp1.oos.close();
			}
			if (mp1.ois != null) {
				mp1.ois.close();
			}
		}
	}
}
