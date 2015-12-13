package ie.gmit.sw;

public class KeyEnumerator {

	/*
	 * KeyEnumerator creates a Quadgram map and calculates the best key it can.
	 * It then invokes Vigeneres doCypher method and passes the best key.
	 *
	 * @see QuadgramMap
	 * @see Vigenere
	 *
	 * @author Aaron Flanagan
	 */

	private QuadgramMap map = null;
	private float bestScore=0.0f;
	private String bestKey;

	/*
	 * KeyEnumerator creates a QuadgramMap object when instantiated
	 * @see QuadgramMap
	 */
	public KeyEnumerator() throws Exception {
		//map = new QuadgramMap("src/WarAndPeace-Tolstoy.txt");//eclipse
		map = new QuadgramMap("./WarAndPeace-Tolstoy.txt");//command line
	}

	/*
	 * Loops through a set of keys. If not finished reset to A, else increment the key
	 */
	private char[] getNextKey(char[] key){
		for (int i = key.length - 1; i >=0; i--){
			if (key[i] =='Z'){
				if (i == 0) return null;
				key[i] = 'A';
			}else{
				key[i]++;
				break;
			}
		}
		return key;
	}

	/*
	 * crackCypher takes in the clients request parameters, generates the bestkey it can and then invokes Vigenere doCypher method
	 * @see Vigenere.doCypher
	 */
	public String crackCypher(String cypherText, int maxKeyLength){
		char[] key = null;

		int counter = 0;
		for (int j = 3; j <= maxKeyLength; j++){
			key = new char[j];

			for (int k = 0; k < key.length; k++) key[k] = 'A';

			do{
				counter++;//incremented keys
				String result = new Vigenere(new String(key)).doCypher(cypherText, false);//pass in encrypted text and decrpyt it with key
				float score = map.getScore(result);//get score of key

				if(score > bestScore){
					bestScore = score;//new bestscore means
					bestKey = new String(key);//we have a new better key
					//System.out.println("Got best key " + bestKey);
				}

			}while ((key = getNextKey(key)) != null);
		}
		System.out.println("Enumerated " + counter + " keys.");

		String result = new Vigenere(bestKey).doCypher(cypherText, false);//do the actual decryption with bestkey
		return result;//return the decrypted text
	}
}
