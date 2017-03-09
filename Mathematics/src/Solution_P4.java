import java.util.ArrayList;
import java.util.List;

public class Solution_P4 {

	public static void main(String[] args) {

		List<Long> result = all_prime_factors(80);

		for (long i : result)
			System.out.println(i);
	}

	public static List<Long> all_prime_factors(long number) {

		List<Long> result = new ArrayList<Long>();

		int sqrtN = (int) Math.sqrt(number);
		boolean[] primes = new boolean[sqrtN + 1];
		primes[0] = primes[1] = false;
		for (int i = 2; i <= sqrtN; i++)
			primes[i] = true;

		for (int i = 2; i <= sqrtN; i++) {
			if (primes[i]) {
				while (number % i == 0) {
					result.add((long) i);
					number /= i;
				}

				for (long j = (long)i*(long)i; j <= sqrtN; j += (long)i) {
					primes[(int)j] = false;
				}
			}
		}

		if (number > 1)
			result.add(number);

		return result;
	}
}
