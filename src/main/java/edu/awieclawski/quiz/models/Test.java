package edu.awieclawski.quiz.models;

import javax.persistence.*;

@Entity
@Table(name = "test_tb")
public class Test {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long testId;
	private String testName;
	@Enumerated(EnumType.STRING)
	private Statuses status;
	@ManyToOne
	private TestType testType;
	@ManyToOne
	private DifficultyLevel difficultyLevel;

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Statuses getStatus() {
		return status;
	}

	public void setStatus(Statuses status) {
		this.status = status;
	}

	public TestType getTestType() {
		return testType;
	}

	public void setTestType(TestType testType) {
		this.testType = testType;
	}

	public DifficultyLevel getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}
}
