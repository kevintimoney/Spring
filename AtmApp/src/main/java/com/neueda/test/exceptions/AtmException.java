package com.neueda.test.exceptions;

public class AtmException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3539947459402059481L;
	

	public AtmException(String message)

	{
		super(message);
	}
	
	public AtmException() {
		super();
	}
	
}
