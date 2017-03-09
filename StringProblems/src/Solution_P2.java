import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution_P2 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		String text  = scanner.nextLine();
		String word = scanner.nextLine();
		int n = scanner.nextInt();
		scanner.close();
		
		System.out.println(retrieveRelatedWords(text, word, n));
	}

	public static String retrieveRelatedWords(String text, String word, int n) {

		String relatedWord = null;
		String[] words = text.split(" ");
		ArrayList<Integer> wordIndices = new ArrayList<Integer>();
		
		for (int i=0; i< words.length; i++) {
			words[i] = words[i].replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

			if (words[i].equals(word))
				wordIndices.add(i);

		}

		if (wordIndices.isEmpty())
			relatedWord = "N/A";
		else {
			HashMap<String, Integer> wordFrequencies = new HashMap<String, Integer>();
			int previousIndex = -(n + 1);
			int maxFrequency = 0;
			for (int index : wordIndices) {
				for (int j = index - n; j <= index + n; j++) {
					if (j >= 0 && j < words.length && j > previousIndex + n) {
						String currentWord = words[j];
						if (!currentWord.equals(word)) {
							int wordFrequency = wordFrequencies.containsKey(currentWord)
									? wordFrequencies.get(currentWord) : 0;
							if (++wordFrequency > maxFrequency)
								maxFrequency = wordFrequency;
							wordFrequencies.put(currentWord, wordFrequency);
						}
					}
				}
				previousIndex = index;
			}

			ArrayList<String> candidateWordsList = new ArrayList<String>();
			for (Map.Entry<String, Integer> entry : wordFrequencies.entrySet()) {
				if (entry.getValue().equals(maxFrequency))
					candidateWordsList.add(entry.getKey());
			}


			String[] candidateWordsArray = candidateWordsList.toArray(new String[candidateWordsList.size()]);
			Arrays.sort(candidateWordsArray);
			relatedWord = candidateWordsArray[0];

		}

		return relatedWord;

	}

}
