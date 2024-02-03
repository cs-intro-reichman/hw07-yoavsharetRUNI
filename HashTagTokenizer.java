

public class HashTagTokenizer {

	public static void main(String[] args) {
		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag.toLowerCase(), dictionary);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);

		for(int i = 0; i < dictionary.length; i++){
			dictionary[i] = in.readLine();
		}

		return dictionary;
	}

	public static boolean existInDictionary(String word, String []dictionary) {
		int dictIdx = 0;
		while(dictIdx < dictionary.length && !alphabeticalOrder(word, dictionary[dictIdx])){
			if(word.equalsIgnoreCase(dictionary[dictIdx])){
				return true;
			}
			dictIdx++;
		}
		return false;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
 
        int N = hashtag.length();

        for (int i = 1; i <= N; i++) {
			if(existInDictionary(hashtag.substring(0, i), dictionary)){
				System.out.println(hashtag.substring(0, i));
				breakHashTag(hashtag.substring(i), dictionary);
				break;
			}
        }
    }

	/*** Helping functions ***/
	/***
	 * Perform alphabeticalOrder test on two input strings
	 * @param a String 1
	 * @param b String 2
	 * @return True if "String 1" (a) comes before "String 2" (b), false otherwise
	 * (Edge case - if equals, return False)
	 */
	private static boolean alphabeticalOrder(String a, String b){
		int index = 0;
		while(index < a.length()){
			if(b.charAt(0) < a.charAt(0)){
				return false;
			}
			if(b.charAt(0) > a.charAt(0)){
				return true;
			}
			index++;
		}
		return false;
	}

}
