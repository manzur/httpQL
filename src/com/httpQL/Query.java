package com.httpQL;

import java.net.URL;
import java.util.List;

public class Query {
	QueryMethod method;
	String tag;
	List<QueryCondition> conditions;
	URL page;
}
