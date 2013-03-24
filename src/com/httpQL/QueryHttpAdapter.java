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

	static final String HEAD_METHOD = "HEAD";
	static final String GET_METHOD = "GET";
	static final String POST_METHOD = "POST";
	static final String PUT_METHOD = "PUT";
	static final String DELETE_METHOD = "DELETE";

	private Query query;

	public QueryHttpAdapter(Query query) {
		this.query = query;
	}

	@Override
	public String getMethod() {

		String result;

		switch (query.method) {

		case SELECT:
			result = GET_METHOD;
			break;

		case UPDATE:
			result = POST_METHOD;

			for (QueryCondition condition : query.conditions) {
				if (condition.attribute.equals("_contents")) {
					result = PUT_METHOD;
					break;
				}
			}

			break;

		case DELETE:
			result = DELETE_METHOD;
			break;

		default:
			result = HEAD_METHOD;

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

		boolean first = true;
		for (QueryCondition condition : query.conditions) {
			if (!first) {
				builder.append("&");
			} else {
				first = !first;
			}

			builder.append(condition.toString());
		}

		final ContentType contentType;
		if (POST_METHOD.equals(getMethod())) {
			contentType = ContentType.APPLICATION_FORM_URLENCODED;

		} else {
			contentType = ContentType.TEXT_PLAIN;
		}

		return new StringEntity(builder.toString(), contentType);

	}

	@Override
	public void setEntity(HttpEntity arg0) {
		throw new UnsupportedOperationException();
	}

}
