package com.davidcalculli.entitystorage;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

public class Entity implements Serializable {

	private static final long serialVersionUID = 7734862533747155907L;
	protected HashMap<String, Entity> elements;
	
	public Entity() {
		 elements = new HashMap<String, Entity>();
		 
		 // TODO Explore potential thread livelocks / deadlocks arising from
		 //		 synchronized methods. Especially around accessing an Entity's
		 //		 children by another thread. Shouldn't be much of an issue,
		 //		 however it is in this class that investigation should begin.
		 
		 
		 // TODO Re-write these methods to be MessagePack friendly
		 //		 from doco in https://github.com/msgpack/msgpack-java/wiki/QuickStart 
	}
	
	/**
	 * Gets whether or not this Entity has any children
	 * @return A {@link true} if there are registered children Entities
	 */
	public synchronized boolean hasChildren() {
		return (this.elements.size() > 0);
	}
	
	/**
	 * Gets the set of keys that represent the first level children
	 * @return A {@link Set<String>} of names of children keys or {@link Null} if there are no children
	 */
	public synchronized Set<String> getChildrenKeys() {
		if(this.hasChildren()) {
			return this.elements.keySet();
		}		
		return null;
	}
	
	/**
	 * Gets a child entity element based on a supplied key
	 * @param childKey The name of the child entity
	 * @return The child {@link Entity} or {@link Null} if an invalid key was supplied
	 */
	public synchronized Entity getChild(String childKey) {
		if(this.elements.containsKey(childKey)) {
			return this.elements.get(childKey);
		}
		return null;
	}
	
	/**
	 * Adds a child entity to this entity based on a supplied key. If the key already exists, the value is overwritten
	 * @param childKey The unique name of the child entity
	 * @param child The unique value of the child of type {@link Entity}
	 * @return The child {@link Entity} is passed back to the caller 
	 */
	public synchronized Entity addChild(String childKey, Entity child) {
		if(this.elements.containsKey(childKey)) {
			this.removeChild(childKey);
		}
		this.elements.put(childKey, child);
		return this.getChild(childKey);
	}
	
	/**
	 * Removes a child (and its children) from this entity and dereferences them. 
	 * @param childKey The child key of the child object
	 */
	public synchronized void removeChild(String childKey) {
		if(this.elements.containsKey(childKey)) {
			this.elements.remove(childKey);
		}
	}
}
