package edu.awieclawski.quiz.models;

import javax.persistence.*;

@Entity
@Table(name="difflevels_tb")

public class DifficultyLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long difficultyLevelId;
    private String difficultyLevelName;
    
	public Long getDifficultyLevelId() {
		return difficultyLevelId;
	}
	public void setDifficultyLevelId(Long difficultyLevelId) {
		this.difficultyLevelId = difficultyLevelId;
	}
	public String getDifficultyLevelName() {
		return difficultyLevelName;
	}
	public void setDifficultyLevelName(String difficultyLevelName) {
		this.difficultyLevelName = difficultyLevelName;
	}
}
