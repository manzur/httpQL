package com.httpQL;

import java.io.IOException;

import org.apache.http.HttpResponse;

public class ResponseProcessor {
	private final IQueryDB queryDB;

	public ResponseProcessor(IQueryDB queryDB) {
		this.queryDB = queryDB;
	}

	public Object process(Integer queryID, HttpResponse response) {
		try {
			response.getEntity().writeTo(System.out);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
