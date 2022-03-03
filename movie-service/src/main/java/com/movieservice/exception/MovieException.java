package com.movieservice.exception;

public class MovieException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -433397681028174838L;
	public MovieException(String errorMessage) {
	        super(errorMessage);
	    }
	public MovieException(String errorMessage, Throwable throwale) {
        super(errorMessage,throwale);
    }
	
}
