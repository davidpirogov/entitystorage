package com.davidcalculli.entitystorage.init;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

import com.davidcalculli.entitystorage.ComplexEntity;
import com.davidcalculli.entitystorage.Domain;
import com.davidcalculli.entitystorage.Entity;

public class InitHelper {


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
	
	public static void printEntity(String entityName, Entity e, int indentLevel, String indent) {
		
		if(e instanceof ComplexEntity<?>) {
			ComplexEntity<?> ce = (ComplexEntity<?>)e;
			
			String name = String.format("%sName: %s", StringUtils.repeat(indent, indentLevel), entityName);
			String value = String.format("%sValue: %s", StringUtils.repeat(indent, indentLevel),  ce.getValue().toString());
			String children = String.format("%sChildren: %s", StringUtils.repeat(indent, indentLevel), String.valueOf(ce.hasChildren()) + (ce.hasChildren() ?  String.format(" (%s children)", ce.getChildrenKeys().size()) : ""));
			
			System.out.println(name);
			System.out.println(value);
			System.out.println(children);
			
			if(ce.hasChildren()) {
				Iterator<String> iterChild = ce.getChildrenKeys().iterator();
				while(iterChild.hasNext()) {
					String current = iterChild.next();
					
					ComplexEntity<?> ceChild = (ComplexEntity<?>) ce.getChild(current);
					printEntity(current, ceChild, indentLevel + 1, indent);
				}
			}
		}
		else {
			
			String message = String.format("%sAn entity of type %s was found. Do not know what to do with it.", StringUtils.repeat(indent, indentLevel), e.getClass().getName());
			System.out.println(message);
		}		
	}
	
	public static Domain generateTestDomain(String domainName, int numberOfIterations) throws IOException {
		
		Domain domain = new Domain(domainName);		
		for(int i = 0; i < numberOfIterations; i++) {
			
			ComplexEntity<String> e = new ComplexEntity<String>("ENTITY_" + i + "_NAME");
			
			for(int j = 0; j < 2; j++) {
				
				ComplexEntity<String> stringEntity = new ComplexEntity<String>(generateRandomString(i + 1, " "));
				
				ComplexEntity<Integer> prem = new ComplexEntity<Integer>(i);
				stringEntity.addChild("entity.offset", prem);
				
				ComplexEntity<Boolean> prem2 = new ComplexEntity<Boolean>(true);
				stringEntity.addChild("entity.boolean", prem2);
				
				ComplexEntity<Double> prem3 = new ComplexEntity<Double>(12.5465789454654);
				stringEntity.addChild("entity.double", prem3);
				
				HashMap<String, String> values = new HashMap<String, String>();
				values.put("V1key", "V1value");
				values.put("V2key", "V2value");
				values.put("V3key", "V3value");
				ComplexEntity<HashMap<String, String>> prem4 = new ComplexEntity<HashMap<String, String>>(values);
				stringEntity.addChild("entity.list", prem4);
				
				
				e.addChild("ENTITY_" + i + "_" + j + "_KEY", stringEntity);
			}
			
			domain.register("ENTITY_" + i, e);
		}
		
		ComplexEntity<String> alpha = new ComplexEntity<String>("value of alpha");
		domain.register("ENTITY_ALPHA", alpha);
		domain.synchronise();
		
		return domain;
	}
	
	public static void runTest(String domainName, int iterations) throws IOException {

		long startOfTime = System.currentTimeMillis();
		Domain domain = InitHelper.generateTestDomain(domainName, iterations);
		long endOfTime = System.currentTimeMillis();
		
		System.out.println("Iterations: " + iterations + " Duration: " + (endOfTime - startOfTime) + "ms");
		System.out.println("\t Domain Location: " + domain.getLocation());
		System.out.println("\t Domain Records: " + domain.getRecordSize());
		System.out.println("\t Domain Size: " + domain.getReadableSizeInBytes());
		
	}

	
	/**
	 * Generates a string of random words with the supplied separator
	 * @param wordsLength
	 * @return
	 */
	public static String generateRandomString(int wordsLength, String separator) {
		
		Random rand = new Random();
		String sb = "";
		
		for(int i = 0; i < wordsLength; i++) {
			int randomWord = rand.nextInt(words.length);
			
			if(i != 0) {
				sb += separator;
			}
			
			sb += words[randomWord];
		}
				
		return sb;
	}


}
