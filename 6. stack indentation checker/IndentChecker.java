package stack_indentation_checker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

class BadIndentationException extends RuntimeException{
	BadIndentationException(String error)
	{
		super(error);
	}
}

public class IndentChecker {
	Stack <Integer> indentStack = new Stack <Integer>();
	
	// return index of first non-blank character
	// return -1 if the line doesn't contain a non-blank character
	private int findFirstNonBlank(String line){		
		if (line.isBlank()) {
			return -1;
		}else {
		return line.indexOf(line.trim());
		}
	}
	
	private void processLine(String line, int lineNumber){
		int index = findFirstNonBlank(line);
		// Skip blank lines ... i.e. return immediately
		if (index == -1) {
			return;
		}
		// If the stack is empty, then push this index onto the stack and return
		if (indentStack.isEmpty()){
			indentStack.push(index);
			return;	
		}
		// If this index > than the top of the stack, then push this index onto the stack and return
		if (index > indentStack.peek()) {
			indentStack.push(index);
			return;
		}
		// Pop off all Indentation indexes > index
		while (index < indentStack.peek()) {
			indentStack.pop();
		}
		// At his point the top of the stack should match the current index. If it
		// doesn't throw a BadIndentationException. In the error message, include the source Line Number
		if (index != indentStack.peek()){
			throw new BadIndentationException("bad indentation at line #:" + lineNumber);
		}		
	}
	
	public void checkIndentation(String fileName){
		
		indentStack.clear();
		Scanner input = null;
		
		try {
			input = new Scanner (new File(fileName));
			// read through the file line by line
			// for each line, call processLine to check indentation
			int lineNumber = 1;
			while(input.hasNextLine()) {
				processLine(input.nextLine(), lineNumber);
				lineNumber++;
			}
			System.out.println("******" + fileName + " must be properly indented.");			
		}
		catch (BadIndentationException error){
			System.out.println(error);
		}
		catch (FileNotFoundException e){
			System.out.println("Can't open file: "+fileName);
		}finally{
			if (input != null)
				input.close();
		}
	}
	
	public static void main(String[] args) {
		IndentChecker indentChecker = new IndentChecker();
		for (int i=0; i < args.length; i++){
			System.out.println("\nProcessing file: " + args[i]);
			indentChecker.checkIndentation(args[i]);
		}
	}
}