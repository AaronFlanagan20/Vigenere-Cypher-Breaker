package ie.gmit.sw;

public class KeyEnumerator {
	
	/*
	 * KeyEnumerators job is to create a QuadgramMap when invoked
	 * then calculate the best key it can
	 * and finally decrypt text with the key
	 */
	
	private QuadgramMap map = null;
	private float bestScore=0.0f;
	private String bestKey;
	
	public KeyEnumerator() throws Exception {
		map = new QuadgramMap("res/WarAndPeace-Tolstoy.txt");//parse in the quadgrams from text and add to map
	}
	
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
	
	public String crackCypher(String cypherText, int maxKeyLength){
		char[] key = null;
		
		int counter = 0;
		for (int j = 4; j <= maxKeyLength; j++){
			key = new char[j];
			
			for (int k = 0; k < key.length; k++) key[k] = 'A';

			do{//this culculates the best key
				counter++;//incremented keys
				String result = new Vigenere(new String(key)).doCypher(cypherText, false);//pass in encrypted text and decrpyt it with key
				float score = map.getScore(result);//get score of key

				if(score > bestScore){
					bestScore = score;//new bestscore means
					bestKey = new String(key);//we have a new better key
					System.out.println("Got best key " + bestKey);
				}
				
			}while ((key = getNextKey(key)) != null);
		}
		System.out.println("Enumerated " + counter + " keys.");
		
		String result = new Vigenere(bestKey).doCypher(cypherText, false);//do the actual decryption with bestkey
		return result;//return the decrypted text
	}
}
