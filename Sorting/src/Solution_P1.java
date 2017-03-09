import java.util.ArrayList;
import java.util.List;

class Solution_P1 {

	public static void sort_the_files(int n, List<String> result) {
		ArrayList<Integer> orderedNumbers = new ArrayList<Integer>();
		recursiveNumbering(0, n, orderedNumbers);

		for (Integer i : orderedNumbers) {
			result.add("IMG" + i + ".jpg");
		}

	}

	public static void recursiveNumbering(int currentNumber, int n, ArrayList<Integer> orderedNumbers) {
		if (currentNumber > n || orderedNumbers.size() == 1000)
			return;

		if (currentNumber != 0)
			orderedNumbers.add(currentNumber);

		currentNumber *= 10;
		for (int i = 0; i < 10; i++) {
			if (currentNumber != 0 || i != 0)
				recursiveNumbering(currentNumber + i, n, orderedNumbers);
		}
	}
}
