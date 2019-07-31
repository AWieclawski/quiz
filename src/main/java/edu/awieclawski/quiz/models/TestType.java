package edu.awieclawski.quiz.models;

import javax.persistence.*;

@Entity
@Table(name = "testtypes_tb")
public class TestType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long testTypeId;
	private String testTypeName;

	public TestType(Long testTypeId, String testTypeName) {
		this.testTypeId = testTypeId;
		this.testTypeName = testTypeName;
	}

	public TestType() {
		super();
	}

	public Long getTestTypeId() {
		return testTypeId;
	}

	public void setTestTypeId(Long testTypeId) {
		this.testTypeId = testTypeId;
	}

	public String getTestTypeName() {
		return testTypeName;
	}

	public void setTestTypeName(String testTypeName) {
		this.testTypeName = testTypeName;
	}

	@Override
	public String toString() {
		return "TestType [testTypeId=" + testTypeId + ", testTypeName=" + testTypeName + "]";
	}

}
