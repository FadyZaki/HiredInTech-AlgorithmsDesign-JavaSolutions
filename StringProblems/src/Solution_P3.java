import java.util.Arrays;
import java.util.Scanner;

public class Solution_P3 {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		String word = scanner.nextLine();
		String text = scanner.nextLine();
		scanner.close();

		System.out.println(findTypos(word, text));

	}

	public static int findTypos(String word, String text) {

		int numberOfTypos = 0;
		String[] words = text.split(" ");

		for (String w : words) {

			int dpTable[][] = new int[word.length() + 1][w.length() + 1];
			for (int[] row : dpTable)
				Arrays.fill(row, 0);

			for (int i = 1; i <= w.length(); i++)
				dpTable[0][i] = 2*i;

			for (int i = 1; i <= word.length(); i++)
				dpTable[i][0] = 2*i;

			for (int i = 1; i <= word.length(); i++) {
				for (int j = 1; j <= w.length(); j++) {
					if (word.charAt(i - 1) == w.charAt(j - 1))
						dpTable[i][j] = dpTable[i - 1][j - 1];
					else {
						dpTable[i][j] = Math.min(Math.min(dpTable[i - 1][j - 1] + 1, dpTable[i - 1][j] + 2),
								dpTable[i][j - 1] + 2);
					}
				}

			}

			if (dpTable[word.length()][w.length()] <= 2)
				numberOfTypos++;

		}

		return numberOfTypos;

	}

}
