package edu.awieclawski.quiz.exceptions;

public class DifficultyLevelNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4188804981218046643L;

	public DifficultyLevelNotFoundException(Long id) {
	    super("Could not find difficulty level " + id);
	  }

}
