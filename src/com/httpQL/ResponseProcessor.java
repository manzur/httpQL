package com.httpQL;

public class ResponseProcessor {
	private final IQueryDB queryDB;

	public ResponseProcessor(IQueryDB queryDB) {
		this.queryDB = queryDB;
	}

	public Object process(Integer queryID, Object response) {
		throw new UnsupportedOperationException();
	}
}
