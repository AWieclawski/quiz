package edu.awieclawski.quiz.exceptions;

public class TestTypeNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4133804981218077643L;

	public TestTypeNotFoundException(Long id) {
	    super("Could not find test type " + id);
	  }

}
