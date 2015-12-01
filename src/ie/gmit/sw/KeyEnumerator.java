package ie.gmit.sw;

public class KeyEnumerator {
	
	//private Vigenere cypher;
	private QuadgramMap map = null;
	private float bestScore;
	private String bestKey;
	
	public KeyEnumerator() throws Exception {
		map = new QuadgramMap("res/quadgrams.txt");
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
		for (int j = 3; j <= maxKeyLength; j++){
			key = new char[j];
			for (int k = 0; k < key.length; k++) key[k] = 'A';
			
			do{
				counter++;
				String result = new Vigenere(new String(key)).doCypher("TVHUGTUDHKOURUGTS", false);
				float score = map.getScore(result);

				if(score > bestScore){
					bestKey = new String(key);
				}
				
			}while ((key = getNextKey(key)) != null);
		}
		System.out.println("Enumerated " + counter + " keys.");
		String result = new Vigenere(bestKey).doCypher("TVHUGTUDHKOURUGTS", false);
		System.out.println(result);
		
		return result;
	}
}
