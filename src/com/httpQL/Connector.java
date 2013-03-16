package com.httpQL;

public class Connector {
	private final IQueryDB queryDB;
	private HttpClient httpClient;

	public Connector(IQueryDB queryDB) {
		this.queryDB = queryDB;
	}

	public Object send(Integer queryID) {
		Query query = queryDB.getQuery(queryID);

		Object changeMe = new QueryHttpAdapter(query);
		Object response = httpClient.execute(changeMe);

		return response;
	}
}
