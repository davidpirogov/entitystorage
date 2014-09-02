package com.davidcalculli.entitystorage.init;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;

import com.davidcalculli.entitystorage.Domain;
import com.davidcalculli.entitystorage.Entity;

public class Init {

	public static void main(String[] args) throws IOException {
		
		String content = "Our extensive experience";
		
		Domain domain = new Domain("data/content.world");
		
		for(int i = 0; i < 100; i++) {
			
			Entity e = new Entity();
			
			for(int j = 0; j < 1000; j++) {
				e.put("ENTITY_" + i + "_" + j + "_KEY", StringUtils.rightPad(content, content.length() +  j, " "));
			}
			
			
			domain.register("ENTITY_" + i, e);
		}
		
		domain.synchronise();
		
		System.out.println("Domain Location: " + domain.getLocation());
		System.out.println("Domain Records: " + domain.getRecordSize());
		System.out.println("Domain Size: " + domain.getReadableSizeInBytes());
		
	}

}
