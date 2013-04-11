package com.httpQL;

public class QueryCondition {
	final String attribute;
	final String value;

	public QueryCondition(String attribute, String value) {
		this.attribute = attribute;
		this.value = value;
	}

	public String toString() {
		return attribute + "=" + value;
	}
}
