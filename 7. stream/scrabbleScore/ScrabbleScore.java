package scrabbleScore;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScrabbleScore {

	public static int scoreConversion(String word) {

		Map<Character, Integer> letterValues = new HashMap();
		letterValues.put('a', 1);
		letterValues.put('b', 3);
		letterValues.put('c', 3);
		letterValues.put('d', 2);
		letterValues.put('e', 1);
		letterValues.put('f', 4);
		letterValues.put('g', 2);
		letterValues.put('h', 4);
		letterValues.put('i', 1);
		letterValues.put('j', 8);
		letterValues.put('k', 5);
		letterValues.put('l', 1);
		letterValues.put('m', 3);
		letterValues.put('n', 1);
		letterValues.put('o', 1);
		letterValues.put('p', 3);
		letterValues.put('q', 10);
		letterValues.put('r', 1);
		letterValues.put('s', 1);
		letterValues.put('t', 1);
		letterValues.put('u', 1);
		letterValues.put('v', 8);
		letterValues.put('w', 4);
		letterValues.put('x', 8);
		letterValues.put('y', 4);
		letterValues.put('z', 10);

		int score = 0;
		word = word.toLowerCase();
		
		for (int i = 0; i < word.length(); i++) {
			score += letterValues.get(word.charAt(i));
		}
		
		return score;
	}

	public static void main(String[] args) {

		String[] wordArr = {"Java", "program", "list", "string", "unix", "hours", "syntax", "error"};

		System.out.println("Top three words are:");
		
		Stream.of(wordArr).sorted((a,b)-> scoreConversion(b)-scoreConversion(a)).limit(3).
		forEach(e->System.out.println(e + ":" + scoreConversion(e)));

		double average = Stream.of(wordArr).mapToInt(e-> scoreConversion(e)).average().getAsDouble();
		System.out.println("Average scrabble value is:" + average);

		Stream.of(wordArr).collect(Collectors.groupingBy(e-> scoreConversion(e) > average))
		.forEach((k,v) -> System.out.println( (k ? "words above average:" : "words below average:") + v));
	}
}
