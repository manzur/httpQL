package com.httpQL;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

public class QueryHttpAdapter extends HttpRequestBase implements
		HttpEntityEnclosingRequest {

	static final String GET_METHOD = "GET";
	static final String PUT_METHOD = "PUT";
	static final String POST_METHOD = "POST";
	static final String HEAD_METHOD = "HEAD";
	static final String DELETE_METHOD = "DELETE";

	private Query query;
	private final String domain;

	public QueryHttpAdapter(String domain, Query query) {
		this.domain = domain;
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
			return new URI(domain + "/" + query.page);

		} catch (URISyntaxException e) {
			throw new IllegalArgumentException("wrong formatted link");
		}
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

			builder.append((condition.attribute) + "="
					+ encodeParamValue(condition));
		}

		final String content;
		final StringEntity result;
		if (POST_METHOD.equals(getMethod())) {
			result = new StringEntity(builder.toString(),
					ContentType.APPLICATION_FORM_URLENCODED);

		} else {
			try {
				result = new StringEntity(builder.toString(), "UTF-8");

			} catch (UnsupportedEncodingException e) {
				// Should occur as only supported Encoding is UTF-8
				e.printStackTrace();
				throw new RuntimeException(e);

			}
		}

		return result;
	}

	private String encodeParamValue(QueryCondition condition) {
		try {
			return URLEncoder.encode(condition.value, "UTF-8");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setURI(URI uri) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setEntity(HttpEntity arg0) {
		throw new UnsupportedOperationException();
	}

}
