package com.httpQL;

public class Runner {
	QueryProcessor queryProcessor;
	ResponseProcessor responseProcessor;
	IQueryDB queryDB;
	Connector connector;

	void run() {
		queryDB = newQueryDB();
		queryProcessor = new QueryProcessor(queryDB);
		responseProcessor = new ResponseProcessor(queryDB);
		connector = new Connector(queryDB);

		String query = getQuery();
		Integer queryID = queryProcessor.process(query);

		Object response = connector.send(queryID);
		Object filteredResponse = responseProcessor.process(queryID, response);

		showResponse(filteredResponse);
	}

	private IQueryDB newQueryDB() {
		throw new UnsupportedOperationException();
	}

	private String getQuery() {
		throw new UnsupportedOperationException();
	}

	private void showResponse(Object filteredResponse) {
		throw new UnsupportedOperationException();
	}

	public static void main(String[] args) {
		new Runner().run();
	}
}
