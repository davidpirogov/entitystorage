package com.davidcalculli.entitystorage;

public class ComplexEntity<T> extends Entity {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 2071604129073403032L;
	protected T value;
	
	public ComplexEntity(T entityValue) {
		super();
		this.value = entityValue;
	}
	
	/**
	 * Gets the value that is stored within this entity
	 * @return
	 */
	public T getValue() {
		return value;
	}

}
