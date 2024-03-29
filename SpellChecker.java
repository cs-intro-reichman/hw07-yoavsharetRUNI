
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word.toLowerCase(), threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		return levenshteinHelper(word1.toLowerCase(), word2.toLowerCase());
		
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);

		for(int i = 0; i < dictionary.length; i++){
			dictionary[i] = in.readLine();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int minIdx = 0;
		int minVal = levenshtein(word, dictionary[0]);
		for(int i = 0; i < dictionary.length; i++){
			int currentDistance = levenshtein(word, dictionary[i]);
			if(currentDistance < minVal){
				minIdx = i;
				minVal = currentDistance;
			}
		}

		if(minVal > threshold){
			return word;
		}
		return dictionary[minIdx];
		
	}

	/*** Helping functions ***/
	private static int min(int a, int b){
		if(a <= b){
			return a;
		}
		return b;
	}

	private static int min(int a, int b, int c){
		return min(min(a, b), c);
	}

	private static int levenshteinHelper(String word1, String word2){
		if(word2.length() == 0){
			return word1.length();
		}

		if(word1.length() == 0){
			return word2.length();
		}

		if(word1.charAt(0) == word2.charAt(0)){
			return levenshteinHelper(tail(word1), tail(word2));
		}

		return 1 + min(levenshteinHelper(tail(word1), word2), 
		               levenshteinHelper(word1, tail(word2)), 
					   levenshteinHelper(tail(word1), tail(word2)));
	}
}
