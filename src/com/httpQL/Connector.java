package com.httpQL;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class Connector {
	private final IQueryDB queryDB;
	private HttpClient httpClient;

	private List<HttpResponse> responses = new LinkedList<>();

	public Connector(IQueryDB queryDB) {
		this.queryDB = queryDB;
		this.httpClient = new DefaultHttpClient();
	}

	public HttpResponse send(String domain, Integer queryID)
			throws ClientProtocolException, IOException {

		Query query = queryDB.getQuery(queryID);

		HttpUriRequest request = new QueryHttpAdapter(domain, query);
		HttpResponse response = httpClient.execute(request);
		responses.add(response);

		return response;
	}

	public void release() throws IOException {
		for (HttpResponse r : responses) {
			EntityUtils.consume(r.getEntity());
		}

		httpClient.getConnectionManager().shutdown();
	}
}
