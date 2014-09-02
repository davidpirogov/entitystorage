package com.davidcalculli.entitystorage.init;

import java.io.IOException;
import java.util.Random;

import com.davidcalculli.entitystorage.Domain;
import com.davidcalculli.entitystorage.Entity;
import com.davidcalculli.entitystorage.StringEntity;

public class Init {

	private static String[] words = new String[] {
			"cogitation",
			"unwilful",
			"intendment",
			"flexibleness",
			"intervertebrally",
			"magi",
			"phalanx",
			"pipsqueak",
			"bosnian",
			"martinmas",
			"vimana",
			"agitational",
			"hyperorthognathous",
			"hierarchize",
			"cheshunt",
			"trachea",
			"consternate",
			"deep",
			"calcimined",
			"sublunated",
			"sorer",
			"interlaminar",
			"polemicist",
			"overassertive",
			"endolymph",
			"dichloride",
			"simultaneously",
			"prerejoicing",
			"degradedness",
			"hexameron",
			"consecratedness",
			"nondisciplining",
			"bimorph",
			"planck",
			"somewise",
			"milfoil",
			"postprandially",
			"isolate",
			"pseudoerotic",
			"fishiness",
			"benefiter",
			"kola",
			"socialist",
			"frenchify",
			"unglorifying",
			"innovating",
			"sotol",
			"downcastly",
			"wkly",
			"unabolished",
			"cogitation",
			"unwilful",
			"intendment",
			"flexibleness",
			"intervertebrally",
			"magi",
			"phalanx",
			"pipsqueak",
			"bosnian",
			"martinmas",
			"vimana",
			"agitational",
			"hyperorthognathous",
			"hierarchize",
			"cheshunt",
			"trachea",
			"consternate",
			"deep",
			"calcimined",
			"sublunated",
			"sorer",
			"interlaminar",
			"polemicist",
			"overassertive",
			"endolymph",
			"dichloride",
			"simultaneously",
			"prerejoicing",
			"degradedness",
			"hexameron",
			"consecratedness",
			"nondisciplining",
			"bimorph",
			"planck",
			"somewise",
			"milfoil",
			"postprandially",
			"isolate",
			"pseudoerotic",
			"fishiness",
			"benefiter",
			"kola",
			"socialist",
			"frenchify",
			"unglorifying",
			"innovating",
			"sotol",
			"downcastly",
			"wkly",
			"unabolished"
	};
	
	public static void main(String[] args) throws IOException {
		
		Domain domain = new Domain("data/content.world");
		
		for(int i = 0; i < 100; i++) {
			
			Entity e = new Entity();
			
			for(int j = 0; j < 1000; j++) {
				
				StringEntity stringEntity = new StringEntity(generateRandomString(i + 1, " "));
				
				e.put("ENTITY_" + i + "_" + j + "_KEY", stringEntity);
			}
			
			
			domain.register("ENTITY_" + i, e);
		}
		
		domain.synchronise();
		
		System.out.println("Domain Location: " + domain.getLocation());
		System.out.println("Domain Records: " + domain.getRecordSize());
		System.out.println("Domain Size: " + domain.getReadableSizeInBytes());
		
	}
	
	/**
	 * Generates a string of random words with the supplied separator
	 * @param wordsLength
	 * @return
	 */
	public static String generateRandomString(int wordsLength, String separator) {
		
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();
		
		for(int i = 0; i < wordsLength; i++) {
			int randomWord = rand.nextInt(words.length);
			
			if(i != 0) {
				sb.append(separator);
			}
			
			sb.append(words[randomWord]);
		}
		
		
		return sb.toString();
	}

}
