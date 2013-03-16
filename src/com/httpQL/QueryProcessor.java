package com.httpQL;

public class QueryProcessor {
	private final IQueryDB queryDB;

	public QueryProcessor(IQueryDB queryDB) {
		this.queryDB = queryDB;
	}

	public Integer process(String query) {
		throw new UnsupportedOperationException();
	}
}
