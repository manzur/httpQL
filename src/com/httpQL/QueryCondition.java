package com.httpQL;

public class QueryCondition {
	String attribute;
	String value;

	public QueryCondition(String attribute, String value) {
		this.attribute = attribute;
		this.value = value;
	}

	public String toString() {
		// @formatter:off
		return attribute + "=" + value;
		// @formatter:on
	}
}
