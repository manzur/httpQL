package com.httpQL;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

public class Connector {
	private final IQueryDB queryDB;
	private HttpClient httpClient;

	public Connector(IQueryDB queryDB) {
		this.queryDB = queryDB;
	}

	public Object send(Integer queryID) throws ClientProtocolException,
			IOException {

		Query query = queryDB.getQuery(queryID);

		HttpUriRequest request = new QueryHttpAdapter(query);
		httpClient.execute(request);

		return null;
	}

	public static void main(String[] args) throws ClientProtocolException,
			IOException {

		// connectViaAdapter();
		connectViaAdapterPost();
		// fluentGet("output.html");
		fluentPost("output1.html");
		// fluentPostHandler("output2.html");
		//
		// simpleGet();

	}

	private static void connectViaAdapter() throws ClientProtocolException,
			IOException {

		Query query = new Query();
		query.method = QueryMethod.SELECT;
		query.page = "http://narod.ru";
		query.tag = "*";

		HttpClient client = new DefaultHttpClient();

		QueryHttpAdapter request = new QueryHttpAdapter(query);
		HttpResponse response = client.execute(request);
		response.getEntity().writeTo(System.out);

	}

	private static void connectViaAdapterPost() throws ClientProtocolException,
			IOException {

		Query query = new Query();
		query.method = QueryMethod.UPDATE;
		query.page = "http://posttestserver.com/post.php";
		query.tag = "*";
		query.conditions.add(new QueryCondition("somekey", "somevalue"));
		query.conditions.add(new QueryCondition("newkey", "newvalue"));

		HttpClient client = new DefaultHttpClient();

		QueryHttpAdapter request = new QueryHttpAdapter(query);
		HttpParams params = new BasicHttpParams();
		request.setParams(params);
		HttpResponse response = client.execute(request);
		response.getEntity().writeTo(System.out);

	}

	private static void simpleGet() throws IOException, ClientProtocolException {

		HttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(new HttpGet("http://narod.ru"));

		System.err.println("Response code " + response.getStatusLine());
		System.err.println("Content length "
				+ response.getEntity().getContentLength());

		InputStream is = response.getEntity().getContent();

		client.getConnectionManager().shutdown();

		int c = -1;
		while ((c = is.read()) != -1) {
			System.err.write(c);
		}
	}

	private static void fluentGet(String fname) throws ClientProtocolException,
			IOException, FileNotFoundException {
		final String URL = "http://narod.ru";

		String s = Request.Get(URL).execute().returnContent().asString();
		BufferedWriter fout = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(fname)));
		fout.append(s);
		fout.flush();
		fout.close();
	}

	private static void fluentPost(String fname)
			throws ClientProtocolException, IOException, FileNotFoundException {

		final String URL = "http://posttestserver.com/post.php";

		Form q = Form.form();
		q.add("dd", "zz");
		String s = Request.Post(URL).bodyForm(q.build()).execute()
				.returnContent().asString();
		BufferedWriter fout = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(fname)));
		fout.append(s);
		fout.flush();
		fout.close();
	}

	private static void fluentPostHandler(final String fname)
			throws ClientProtocolException, IOException, FileNotFoundException {

		final String URL = "http://posttestserver.com/post.php";

		Form q = Form.form();
		q.add("eee", "kkkk");
		String s = Request.Post(URL).bodyForm(q.build()).execute()
				.handleResponse(new ResponseHandler<String>() {

					@Override
					public String handleResponse(HttpResponse arg0)
							throws ClientProtocolException, IOException {

						System.out.println("Status line: "
								+ arg0.getStatusLine());
						System.out.println("Content-length: "
								+ arg0.getEntity().getContentLength());

						BufferedReader reader = new BufferedReader(
								new InputStreamReader(arg0.getEntity()
										.getContent()));
						String s;
						while ((s = reader.readLine()) != null) {
							System.out.println(s);
						}

						return "";
					}
				});

		BufferedWriter fout = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(fname)));
		fout.append(s);
		fout.flush();
		fout.close();
	}

}
