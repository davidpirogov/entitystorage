package com.davidcalculli.entitystorage.init;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;

import com.davidcalculli.entitystorage.Domain;
import com.davidcalculli.entitystorage.Entity;

public class Init {

	public static void main(String[] args) throws IOException {
		
		String content = "Our extensive 25 years information management expertise in the public sector and regulated industries is seriously lacking in the SharePoint domain. This same extensive experience has recently produced ECM for Small Agencies which is one step towards providing a pre-configured or packaged information management solution. The long term vision of SharePoint Governance includes creating a pre-configured solution to map of out of the box SharePoint content to a pre-configured version of Objective (using a model similar to ECM for small agencies) to reduce the overhead involved in implementing a governed SharePoint solution. While competitors such as RecordPoint, Automated Intelligence and Gimmal provide tools to allow customers or consultants to build a tailored solution to manage their information, Objective will leverage its industry experience to provide pre-packaged solutions which solve specific business problems and use information management best practices gained from our industry experience.";
		
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
