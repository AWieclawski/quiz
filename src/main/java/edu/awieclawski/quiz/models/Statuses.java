package edu.awieclawski.quiz.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Statuses {
	ACTIVE(1, "active"), EXCLUDED(0, "inactive");

	private final int statusKey;
	@JsonValue
	private final String statusName;

	private Statuses(int key, String name) {
		this.statusKey = key;
		this.statusName = name;
	}

	public int getStatusKey() {
		return statusKey;
	}

	@Override
	public String toString() {
		return this.statusName;
	}

	public static Statuses valueOf(int digit) {
		return digit == 0 ? EXCLUDED : ACTIVE;
	}

}
