package sortArray;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SortArray {
	
	public static void main(String[] args) {
		
		// displays distinct numbers in increasing order
		int[][] arr = {{34,89},{56,3},{27,61},{45,8},{45,89}};
		Stream.of(arr).flatMapToInt(e->IntStream.of(e)).sorted().distinct().
		forEach(e->System.out.print(e+" "));
	}
}
