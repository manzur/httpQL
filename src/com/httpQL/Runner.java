package com.httpQL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;

public class Runner {
	private final static String PROMPT = ">";

	QueryProcessor queryProcessor;
	ResponseProcessor responseProcessor;
	IQueryDB queryDB;
	Connector connector;

	Runner() {
		queryDB = new QueryDB();
		queryProcessor = new QueryProcessor(queryDB);
		responseProcessor = new ResponseProcessor(queryDB);
		connector = new Connector(queryDB);
	}

	void run(String domain, String query) {
		System.out.println("Processing: " + query);

		// IDEA:
		// This can be done as a stream processing
		// response = filter mapQueryToResponse mapTextToQueryqueryText
		try {
			Integer queryID = queryProcessor.process(query);
			HttpResponse response = connector.send(domain, queryID);
			responseProcessor.process(queryID, response);

		} catch (ConnectorException e) {
			System.out.println("Network error while connecting to domain "
					+ domain);
		} catch (ResponseProcessorException | ParseException e) {
			System.out.println("Some error occured while processing query");

		}
	}

	void stop() {
		connector.release();
	}

	static void usage() {
		System.out.println("Usage: program domainname");
	}

	public static void main(String[] args) throws ClientProtocolException,
			IOException {

		if (args.length != 1) {
			usage();
			return;
		}

		String domain = args[0];

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		while (true) {
			System.out.print("\n" + PROMPT + " ");
			System.out.flush();

			String s = reader.readLine();
			// CTRL-D
			if (s == null) {
				break;

			} else {
				s = s.trim();

				if (s.length() > 0) {
					new Runner().run(domain, s);
				}
			}
		}
	}
}
