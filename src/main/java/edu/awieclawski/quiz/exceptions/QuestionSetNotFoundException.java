package edu.awieclawski.quiz.exceptions;

public class QuestionSetNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4188804991218046483L;

	public QuestionSetNotFoundException(Long id) {
	    super("Could not find question " + id);
	  }

}
