package com.davidcalculli.entitystorage;

public class StringEntity extends Entity {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -1532652885141699022L;

	private String contents = null;
	
	public StringEntity(String stringContents) {
		super();
		
		// TODO - Work out how to handle children
		//		  Perhaps an if(x typeof String) check
		//		  to ensure all children are children.
		//
		//		  Alternatively, explore the potential for
		//		  children entity types to have different
		//		  values and the consequences of that.
		contents = stringContents;
	}

	public String getValue() {
		return contents;
	}
	
	
}
