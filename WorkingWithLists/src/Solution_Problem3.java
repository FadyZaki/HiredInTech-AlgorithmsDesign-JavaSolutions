
public class Solution_Problem3 {

	public static boolean is_numeric_palindrome(long number) {

		number = Math.abs(number);
		int numberOfDigits = 0;
		
		long n = number;
		while (n > 0) {
			numberOfDigits++;
			n /= 10;
		}
		
		Long[] digits = new Long[numberOfDigits];
		for (int i=0; i<numberOfDigits; i++){
			digits[i] = number % 10;
			number /= 10;
		}
		
		int j = numberOfDigits-1;
		for (int i=0; i<numberOfDigits/2; i++){
			if (digits[i] != digits[j])
				return false;
			
			j--;
		}
		
		return true;

	}
	
	public static void main(String[] args) {
		
		System.out.println(is_numeric_palindrome(123));
		
	}
	
}
