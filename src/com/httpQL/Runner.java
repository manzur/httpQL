package com.httpQL;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;

public class Runner {
	QueryProcessor queryProcessor;
	ResponseProcessor responseProcessor;
	IQueryDB queryDB;
	Connector connector;

	void run() throws ClientProtocolException, IOException {
		queryDB = new QueryDB();
		queryProcessor = new QueryProcessor(queryDB);
		responseProcessor = new ResponseProcessor(queryDB);
		connector = new Connector(queryDB);

		// IDEA:
		// This can be done as a stream processing
		// response = filter mapQueryToResponse mapTextToQueryqueryText

		String query = getQuery();
		Integer queryID = queryProcessor.process(query);

		HttpResponse response = connector.send(DOMAIN1, queryID);
		responseProcessor.process(queryID, response);

		connector.release();
		// showResponse(filteredResponse);
	}

	static final String DOMAIN1 = "http://narod.ru";
	static final String queryText1 = "select * from index.html";

	private String getQuery() {
		return queryText1;
	}

	private void showResponse(Object filteredResponse) {
		throw new UnsupportedOperationException();
	}

	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		new Runner().run();
	}
}
