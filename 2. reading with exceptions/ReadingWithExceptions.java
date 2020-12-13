package reading_with_exceptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ReadingWithExceptions {

	static void process(String inputFilename) {
		
		String outputFilename = null;
		Scanner scannerIO = null;
		PrintWriter printOut = null;

		try {
			
			scannerIO = new Scanner(new FileInputStream(inputFilename));//construct scanner object
			outputFilename = scannerIO.next();//assigns 1st string to outputFilename
			printOut = new PrintWriter(new FileOutputStream(outputFilename));//construct print object			
			int number_to_read = -1; 
			
			//Assigns integer value to number_to_read if integer exists, otherwise skips that bad number.
			if (scannerIO.hasNextInt()){
				number_to_read = scannerIO.nextInt();
			}else {
				scannerIO.next();
			}
			//Bad number message printed out if number_to_read is anything other than a positive integer.
			if(number_to_read <0) {
				System.out.println("\n" + inputFilename + " number_to_read is bad. Print all numbers.\n");
			}
			
			int count = 0; //count number of integers printed
			
			//prints integers until count reaches input number or print all integers if input number is bad
			while (scannerIO.hasNext()&&((count < number_to_read)||number_to_read<0)) {
				
				if(scannerIO.hasNextInt()) {
					printOut.print(scannerIO.nextInt() + " ");
					count ++;
				//skips non-integer and prints out bad number message
				}else {
					scannerIO.next();
					System.out.println("\n" + inputFilename + " encounter bad number and skip over");
					
					//prints 10 integers each line
				}	if (count%10 == 0) {
						printOut.println(); 
					}
			}
			//not enough input numbers message
			if (number_to_read >0 && count < number_to_read) {
				System.out.println("\n" + inputFilename + " number_to_read not enough. Print all numbers.\n");	
			
			}

		}	catch (FileNotFoundException e) {
				System.out.println("\n\n" + inputFilename + " file not found");
			
		}	finally {
			//close scanner and printWriter objects if they have opened
			if(scannerIO != null) {
				scannerIO.close();
			}
			if(printOut != null) {		
				printOut.close();
				System.out.println(outputFilename + " created with the following output:");
			}			
		}
		//print output files to console
		Scanner in = null;
		try {
			in = new Scanner(new FileInputStream(outputFilename));//construct scanner object
			int countPrint = 0;
			while (in.hasNext()) {
				System.out.print(in.nextInt() + " ");
				countPrint ++;	
				if (countPrint % 10 == 0) {
					System.out.println();
				}
			}
		}catch (FileNotFoundException | NullPointerException e) {
			
		}
		finally {
			if(in != null) {		
				in.close();
			}
		}
	}
	//process inputFilename
	//file1.txt non-existent-file file2.txt file3.txt
	public static void main(String[] args) {		
		for (String inputFileName : args) {
			process(inputFileName);			
		}
	}
}
