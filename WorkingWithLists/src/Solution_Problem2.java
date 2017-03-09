
public class Solution_Problem2 {

	public static void main(String[] args) {
		System.out.println(digit_sum(-3456));
	}

	public static int digit_sum(long number) {
		
		int sum = 0;
		long remainder = 0;
		number = Math.abs(number);
		
		while (number > 0) {
			remainder = number % 10;
			sum += remainder;
			number /= 10;
		}
		
		return sum;

	}
}
