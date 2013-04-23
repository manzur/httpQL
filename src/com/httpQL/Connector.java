package com.httpQL;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class Connector {
	private Logger logger = Logger.getLogger(Connector.class.getName());

	private final IQueryDB queryDB;
	private HttpClient httpClient;

	private List<HttpResponse> responses = new LinkedList<>();

	public Connector(IQueryDB queryDB) {
		this.queryDB = queryDB;
		this.httpClient = new DefaultHttpClient();
	}

	public HttpResponse send(String domain, Integer queryID)
			throws ConnectorException {

		try {
			Query query = queryDB.getQuery(queryID);

			HttpUriRequest request = new QueryHttpAdapter(domain, query);
			HttpResponse response = httpClient.execute(request);
			responses.add(response);

			return response;

		} catch (IOException ex) {
			throw new ConnectorException();
		}

	}

	public void release() {
		for (HttpResponse r : responses) {
			try {
				EntityUtils.consume(r.getEntity());
			} catch (IOException e) {
				logger.severe("Exception occured on closing connection");
			}
		}

		httpClient.getConnectionManager().shutdown();
	}
}
