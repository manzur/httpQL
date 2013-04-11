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
			Query query = queryDB.getQuery(queryID);

			// filter output only when select is used
			if (query.method == QueryMethod.SELECT) {

			} else {
				response.getEntity().writeTo(System.out);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
