package com.httpQL;

import java.util.LinkedList;
import java.util.List;

public class Query {
	QueryMethod method;
	String tag;
	List<QueryCondition> conditions = new LinkedList<>();
	String page;

	@Override
	public String toString() {
		// @formatter:off
		return "Method: " + method.toString() + "; " 
			 + "Tag: " + tag + "; "
			 + "Conditions: " + conditions.toString() + "; "  
			 + "Page: " + page.toString();
		// @formatter:on
	}
}
