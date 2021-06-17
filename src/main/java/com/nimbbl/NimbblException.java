package com.nimbbl;

public class NimbblException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NimbblException(String message, Throwable cause) {
	    super(message, cause);
	  }	
	
	public NimbblException(String message) {
		super(message);
	}
	
	public NimbblException(Throwable cause) {
	    super(cause);
	  }

	  public NimbblException(String message, Throwable cause, boolean enableSuppression,
	      boolean writableStackTrace) {
	    super(message, cause, enableSuppression, writableStackTrace);
	  }

}
