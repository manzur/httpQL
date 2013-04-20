package com.httpQL;

public class QueryCondition {
	final String attribute;
	final String value;

	final ConditionType conditionType;

	public QueryCondition(String attribute, String value) {
		this(attribute, value, ConditionType.EQ);
	}

	public QueryCondition(String attribute, String value,
			ConditionType conditionType) {

		this.attribute = attribute;
		this.value = value;
		this.conditionType = conditionType;
	}

	public String toString() {
		return attribute + "=" + value;
	}
}
