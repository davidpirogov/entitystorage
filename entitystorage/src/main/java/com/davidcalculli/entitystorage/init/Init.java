package com.davidcalculli.entitystorage.init;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang.time.DateUtils;

import com.davidcalculli.entitystorage.Domain;
import com.davidcalculli.entitystorage.Entity;
import com.davidcalculli.entitystorage.ComplexEntity;

public class Init {
	
	public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
								
		InitHelper.runTest("data/d.1.alpha", 10);
		InitHelper.runTest("data/d.2.alpha", 100);
		InitHelper.runTest("data/d.3.alpha", 1000);
		InitHelper.runTest("data/d.4.alpha", 10000);
		
		
		/*
		Domain d2 = new Domain("data/d.alpha");
		System.out.println("D2 Domain Location: " + d2.getLocation());
		System.out.println("D2 Domain Records: " + d2.getRecordSize());
		System.out.println("D2 Domain Size: " + d2.getReadableSizeInBytes());
		
		Set<String> keys = d2.getRecordKeys();
		Iterator<String> iter = keys.iterator();
		
		while(iter.hasNext()) {
			String currentKey = iter.next();			
			Entity e = d2.getRecord(currentKey);
			InitHelper.printEntity(currentKey, e, 0, "    ");
			System.out.println("------------------------");
		}
		*/
		
		
	}
}
