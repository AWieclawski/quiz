package edu.awieclawski.quiz.exceptions;

public class TestNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5788437858054379304L;
	
	public TestNotFoundException(Long id) {
		super("Could not find test " + id);
	}

}
