package com.hatumruna.taskmanager.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class Words {


	@Test
	public void test() {
		String sentence = convertSentence("wHy Does A biRd Fly?");
		assertEquals("Why Does a Bird Fly?", sentence);
	}

	private String convertSentence(String sentence){
		List<String> lowerlist = Arrays.asList(new String[] {"at", "when", "is", "a"});
		// Split
		String[] words = sentence.split(" ");
		
		words[0] = capitalize(words[0]);
		
		int size = words.length;
		words[size - 1] = capitalize(words[size-1]);
		
		for (int i = 1; i < words.length - 1; i++) {
			if (lowerlist.contains(words[i].toLowerCase()))
				words[i] = words[i].toLowerCase();
			else
				words[i] = capitalize(words[i]);
		}
		
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			if (i == words.length - 1)
				s.append(words[i]);
			else
				s.append(words[i] + " ");
		}
		
		return s.toString();
	}
	
	private String capitalize(String word){
		word = word.toLowerCase();
		word = word.substring(0, 1).toUpperCase() + word.substring(1);
		return word;
	}
	
	public int solution(int[] A) {
        int size = A.length;
        int min = Integer.MAX_VALUE;
        for (int p = 1; p < size -1 ; p++){
            int first = 0;
            int second = 0;
            for ( int i = 0; i < p ; i++)
                first += A[i];
            for ( int i = p; i < size ; i++)
                second += A[i];
            int total = Math.abs(first - second);
            if (total < min)
                min = total;
        }
        return min;
    
	}
}
