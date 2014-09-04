package com.davidcalculli.entitystorage;

@Deprecated
public class StringEntity extends Entity {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -1532652885141699022L;

	private String contents = null;
	
	public StringEntity(String stringContents) {
		super();
		contents = stringContents;
	}

	public String getValue() {
		return contents;
	}
	
	
}
