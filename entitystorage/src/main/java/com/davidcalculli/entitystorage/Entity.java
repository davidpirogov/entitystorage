package com.davidcalculli.entitystorage;

import java.io.Serializable;
import java.util.HashMap;

public class Entity implements Serializable {

	private static final long serialVersionUID = 7734862533747155907L;
	private HashMap<String, Object> elements;
	
	public Entity() {
		 elements = new HashMap<String, Object>();
	}
	
	/**
	 * Puts the key and content into the current entity
	 * @param key A unique descriptor or ID of the content
	 * @param content The content object that can be serialised
	 */
	public void put(String key, Object content) {
		this.elements.put(key, content);		
	}
	
	/**
	 * Gets the object that is identified by the supplied key
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		return this.elements.get(key);
	}
}
