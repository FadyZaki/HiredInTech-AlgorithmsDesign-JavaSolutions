import java.util.Arrays;
import java.util.Scanner;

public class Solution_P1 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		String text  = scanner.nextLine();
		String pattern = scanner.nextLine();
		scanner.close();
		
		System.out.println(count_occurrences(text, pattern));
	}

	public static int count_occurrences(String t, String p) {
		
		if (t.length() == 0 || p.length() == 0 || p.length() > t.length())
			return 0;

		int numberOfOccurences = 0;
		int f[] = buildFailureArray(p);
		
		int j = 0;
		for (int i = 0; i < t.length(); i++) {
			if (t.charAt(i) == p.charAt(j))
				j++;
			else {
				while (t.charAt(i) != p.charAt(j) && j != 0)
					j = f[--j];
			}

			if (j == p.length()) {
				numberOfOccurences++;
				j = f[--j];
			}
		}

		return numberOfOccurences;
	}

	public static int[] buildFailureArray(String p) {
		int f[] = new int[p.length()];
		Arrays.fill(f, 0);

		int j = 0;
		for (int i = 1; i < f.length; i++) {
			if (p.charAt(i) == p.charAt(j)) {
				j++;
			} else {
				while (p.charAt(i) != p.charAt(j) && j != 0)
					j = f[--j];
			}

			f[i] = j;

		}

		return f;
	}

}
