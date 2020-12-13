package spell_checker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class SpellCheck {
	
	private HashSet<String> dictionary = new HashSet<String>();
	private TreeSet<String> miss_spelled_words = new TreeSet<String>();
	
	public SpellCheck() throws FileNotFoundException {
		// Add all of the words from "dictionary.txt" to the dictionary HashSet	
		Scanner dictionaryInput = new Scanner(new File("dictionary.txt"));
		while (dictionaryInput.hasNextLine()) {
			dictionary.add(dictionaryInput.nextLine());
		}
		dictionaryInput.close();
	}
	
	public void checkSpelling(String fileName) throws FileNotFoundException{
			System.out.println("======== Spell checking " + fileName + " =========");
			
			// Clear miss_spelled_words
			miss_spelled_words.clear();
			
			//construct scanner object to read file input
			Scanner fileInput = new Scanner(new File(fileName));
			//construct scanner object to read user input
			Scanner kbInput = new Scanner(System.in);
				
			int lineNumber = 1;
			
			while (fileInput.hasNextLine()) {
				//Read one line in each while loop
				String line = fileInput.nextLine();
				//Split line into words with space or punctuations
				String[] words = line.split(" +|\\p{Punct}");
				
				// Print line containing miss-spelled word. By default is true.
				boolean printLine = true;
				
				for (int i=0; i < words.length; i++) {
											
					if (words[i].length()== 0) {
						continue;
					}
					
					// skip word if the first character is not a letter
					char c = words[i].charAt(0);
					if (!Character.isLetter(c)){
						continue;
					}					
					
					// Skip over word if it can be found in either dictionary, or miss_spelled_words	
					if (dictionary.contains(words[i])||miss_spelled_words.contains(words[i])) {
						continue;
					}
					
					// If word ends with 's', then try the singular version of the word in the
					// dictionary and miss_spelled_words ... skip if found
					if (words[i].endsWith("s")) {
						String omitS = words[i].substring(0, words[i].length()-1);
						if (dictionary.contains(omitS)||miss_spelled_words.contains(omitS)) {
							continue;
						}
					}
					
					words[i] = words[i].toLowerCase();
					if (dictionary.contains(words[i])||miss_spelled_words.contains(words[i])) {
						continue;
					}
					
					//Print line containing miss-spelled word
					if (printLine) {
						System.out.println(lineNumber + " : " + line);
						printLine = false; //print line once if multiple miss-spelled words are found on this line
					}
					
					// Ask the user if he wants the word added to the dictionary or the miss-spelled
					// words and add word as specified by the user
					System.out.println(words[i]+" not in dictionary. Add to dictionary? (y/n)");
					if (kbInput.next().charAt(0)=='y') {
						dictionary.add(words[i]);
					}else {
						miss_spelled_words.add(words[i]);
					}
					
				}lineNumber+=1;
			}
			fileInput.close();
		}
		
		
	public void dump_miss_spelled_words(){		
		// Print out the miss-spelled words
		System.out.println("****** Miss spelled words *******");
		Iterator<String> word = miss_spelled_words.iterator();
		while (word.hasNext()) {
			System.out.println(word.next());
		}
	}
	
	public static void main(String[] args) {
		try {
			SpellCheck spellCheck = new SpellCheck();
			for (int i=0; i < args.length; i++){
					spellCheck.checkSpelling(args[i]);
					spellCheck.dump_miss_spelled_words();
			}
		}
		catch (FileNotFoundException e){
				System.out.println(e);
		}
	}
}