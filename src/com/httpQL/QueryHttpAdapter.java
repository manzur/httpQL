package com.httpQL;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

public class QueryHttpAdapter extends HttpRequestBase implements
		HttpEntityEnclosingRequest {

	private Query query;

	public QueryHttpAdapter(Query query) {
		this.query = query;
	}

	@Override
	public String getMethod() {

		String result;

		switch (query.method) {

		case SELECT:
			result = "GET";
			break;

		case UPDATE:
			result = "POST";

			for (QueryCondition condition : query.conditions) {
				if (condition.attribute.equals("_contents")) {
					result = "PUT";
					break;
				}
			}

			break;

		case DELETE:
			result = "DELETE";
			break;

		default:
			result = "HEAD";

		}

		return result;
	}

	@Override
	public URI getURI() {
		try {
			return new URI(query.page);
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException("wrong formatted link");
		}
	}

	@Override
	public void setURI(URI uri) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean expectContinue() {
		return false;
	}

	@Override
	public HttpEntity getEntity() {
		StringBuilder builder = new StringBuilder();

		boolean first = false;
		for (QueryCondition condition : query.conditions) {
			if (first) {
				builder.append("&");
			} else {
				first = !first;
			}

			builder.append(condition.toString());
		}

		return new StringEntity(builder.toString(),
				ContentType.APPLICATION_FORM_URLENCODED);

	}

	@Override
	public void setEntity(HttpEntity arg0) {
		throw new UnsupportedOperationException();
	}

}
