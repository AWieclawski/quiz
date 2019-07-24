package edu.awieclawski.quiz.models.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Privileges {
	RULER(0, "admin"), EXPLOITER(1, "user");

	private final int privilegeKey;
	@JsonValue // just in case someone turns on this enum for JSON
	private final String privilegeName;

	private Privileges(int key, String name) {
		this.privilegeKey = key;
		this.privilegeName = name;
	}

	public int privilegeKey() {
		return privilegeKey;
	}

	@Override
	public String toString() {
		return this.privilegeName;
	}

	public static Privileges valueOf(int digit) {
		return digit == 0 ? RULER : EXPLOITER;
	}

}
