package com.davidcalculli.entitystorage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.util.HashMap;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

public class Domain {
	
	protected String domainName;
	
	protected File domainFile;
	
	protected HashMap<String, Entity> entities;
	

	public Domain(String domainName) {
		this.domainName = domainName;
		this.entities = new HashMap<String, Entity>();
	}	
	
	
	/**
	 * Returns the filename that this domain covers
	 * @return
	 */
	private String getFileName() {
		return String.format("%s.data", this.domainName);		
	}
	
	/**
	 * Returns the file object that this domain covers
	 * @return A {@link File} object that describes the location of the domain file
	 */
	private File getDomainFile() {
		if(this.domainFile == null) {
			this.domainFile = new File(this.getFileName());
		}
		
		return this.domainFile;
	}
	
	/**
	 * Gets the location of this domain
	 * @return 
	 */
	public String getLocation() {
		return this.getDomainFile().getAbsolutePath();
	}
	
	/**
	 * Gets the number of records that are managed by this domain
	 * @return A {@link long} value with the number of records
	 */
	public long getRecordSize() {
		
		return this.entities.size();
	}


	public void register(String entityName, Entity entity) {
		this.entities.put(entityName, entity);
	}


	/**
	 * Syncronises our changes with the non-volatile storage
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	public void synchronise() throws IOException {
		
		// Serialise our hash map into bytes
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(entities);
		baos.flush();
		
		String entitiesContents = baos.toString();
		baos.close();
		
		// Finally, write the contents of our entity list to the file system
		// TODO: Add config items for type of domain
		if(false) {
			FileUtils.writeByteArrayToFile(this.getDomainFile(), Base64.encodeBase64(entitiesContents.getBytes("UTF-8")));
		}
		else {
			FileUtils.writeStringToFile(this.getDomainFile(), entitiesContents, "UTF-8");
		}

	}


	/**
	 * Returns the readable size of this domain file
	 * @return
	 */
	public String getReadableSizeInBytes() {
		return FileUtils.byteCountToDisplaySize(this.getDomainFile().length());
	}
}
