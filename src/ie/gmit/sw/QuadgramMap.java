package ie.gmit.sw;

import java.util.*;
import java.io.*;

public class QuadgramMap {

	/*
	 * QuadgramMap parses a text file into quadgrams and records the quadgram and its frequency in a map.
	 * It also generates the score of each quadgra using n-gram statistics
	 * 
	 * @author Aaron Flanagan
	 */
	private Map<String, Integer> map = new HashMap<String, Integer>(); 
	
	/*
	 * When created the chosen file wil be parsed to quadgrams and stored in map along with their frequency
	 * 
	 * @param String filename
	 */
	public QuadgramMap(String filename) throws Exception {
		parse(filename);
	}
	
	/*
	 * Using n-gram statistics we calculate the score of each quadgram.
	 * Text is passed in and the score of the quadgrams within it is computed
	 * 
	 * @param String text
	 */
	public float getScore(String text){
		float score = 0.00f;

		for (int i = 0; i < text.length(); i+=4) {

			if (i + 4 > text.length()) break;
			
			String next = text.substring(i, i+4);
			
			if (map.get(next) != null){
				float frequency = (float)map.get(next);
				//float total = (float)map.size();

				score += Math.log10((frequency));
			}
		}
		return score;
	}
	
	/*
	 * Parses the chosen file into quadgrams and stores them and their frequencys in a map
	 * 
	 * @param String filename
	 */
	private void parse(String filename) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
		StringBuffer sb = new StringBuffer();
		
		int j;
		while((j = br.read()) != -1){
			char next = (char)j;
			
			if (next >= 'A' && next <= 'z'){
				sb.append(next);
			}
			
			if(sb.length() == 4){
				String qGram = sb.toString().toUpperCase();
				sb = new StringBuffer();
				
				int frequency = 0;
				
				if(map.containsKey(qGram)){
					frequency = map.get(qGram);
				}
				
				frequency++;
			
				map.put(qGram, frequency);//values defitinly put in
			}
		}
		br.close();
	}
}
