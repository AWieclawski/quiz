package edu.awieclawski.quiz.exceptions;

public class UserNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4188804987518046697L;

	public UserNotFoundException(Long id) {
	    super("Could not find user " + id);
	  }

}
