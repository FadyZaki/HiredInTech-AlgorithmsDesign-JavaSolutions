
public class Solution_P3 {

	public static void main(String[] args) {

		System.out.println(sum_the_divisors(0));

	}

	public static long sum_the_divisors(int number) {
		if (number == 0 || number == 1)
			return 1;

		long sumOfDivisors = 1 + number; // 1 and the number itself
		int i = 0;
		for (i = 2; i * i < number; i++) {
			if (number % i == 0)
				sumOfDivisors += i + number / i;
		}

		if (i * i == number)
			sumOfDivisors += i;

		return sumOfDivisors;
	}

}
