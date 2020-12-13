package countDigit;

import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CountSingleDigits {
		
	public static void main(String[] args) {

		Supplier<Integer> supp = () -> {
			Integer result = (int)(Math.random() * 10);
			return result;
		};
		Stream<Integer> random = Stream.generate(supp);
		random.limit(100)
		.collect(Collectors.groupingBy(e->e, Collectors.counting()))
		.forEach((k,v) -> System.out.println(k + ":" + v));
	}
}

