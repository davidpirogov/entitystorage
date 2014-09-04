package com.davidcalculli.entitystorage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.SerializationUtils;


/**
 *  TODO
 *  Re-architect Domain into an interface that has a synchronise function
 *  and ensure that we can create domains (with associated Domain Options) 
 *  such as:
 * 	 	General Domain (Key-Value Structure)
 * 			- HashMap Structure
 * 			- Flat-file Storage
 * 			- Raw or Base64 encoded byte stream
 * 		Minimal Domain (Value Structure)
 * 			- Array Structure
 * 			- Flat-file Storage
 * 			- Base64 or Protobuf(?) byte stream 		
 */
public class Domain {
	
	protected String domainName;
	
	protected File domainFile;
	
	protected HashMap<String, Entity> entities;
	

	public Domain(String domainName) {
		this.domainName = domainName;
		this.entities = new HashMap<String, Entity>();
		loadFromFile();
	}	
	
	/**
	 * Loads the domain contents and replace the domain contents 
	 */
	protected void loadFromFile() {
		if(this.getDomainFile().exists()) {
			
			try {
				
				HashMap<String, Entity> attemptedEntityList = (HashMap<String, Entity>)SerializationUtils.deserialize(FileUtils.readFileToByteArray(this.getDomainFile()));
				
				if(attemptedEntityList != null) {
					this.entities = attemptedEntityList;
				}
			}
			catch(Exception ex) {
				// TODO - use logging
				ex.printStackTrace();
			}
		}		
	}
	
	
	/**
	 * Returns the filename that this domain covers
	 * @return
	 */
	private String getFileName() {
		return String.format("%s.domain", this.domainName);		
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
	 * Synchronises our changes with the non-volatile storage
	 * @throws IOException
	 */
	public void synchronise() throws IOException {
		// Write the contents of our entity list to the file system
		FileUtils.writeByteArrayToFile(this.getDomainFile(), SerializationUtils.serialize(this.entities));
	}


	/**
	 * Returns the readable size of this domain file
	 * TODO - move this to a utils class
	 * @return
	 */
	public String getReadableSizeInBytes() {
		return FileUtils.byteCountToDisplaySize(this.getDomainFile().length());
	}

	/**
	 * Returns all the keys of the first level records within this domain
	 * @return
	 */
	public Set<String> getRecordKeys() {
		return this.entities.keySet();
	}

	/**
	 * Returns the record that is described by the specified key
	 * @param currentKey
	 * @return The record described by the key or {@link Null}
	 */
	public Entity getRecord(String currentKey) {
		if(this.entities.containsKey(currentKey)) {
			return this.entities.get(currentKey);
		}
		
		return null;
	}
}
