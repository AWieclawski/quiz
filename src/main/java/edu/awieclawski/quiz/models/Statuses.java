package edu.awieclawski.quiz.models;

public enum Statuses {
	ACTIVE(1, "active"), EXCLUDED(0, "inactive");

	private final int statusKey;
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
