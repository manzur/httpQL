package com.httpQL;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

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
import org.apache.http.util.EntityUtils;

public class Connector {
	private final IQueryDB queryDB;
	private HttpClient httpClient;

	private List<HttpResponse> responses = new LinkedList<>();

	private final static String GET_DOMAIN = "http://narod.ru";
	private final static String POST_DOMAIN = "http://posttestserver.com";

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

	public static void main(String[] args) throws ClientProtocolException,
			IOException {

		connectViaAdapter();
		connectViaAdapterPost();
		// fluentGet("output.html");
		fluentPost("output1.html");
		// fluentPostHandler("output2.html");
		//
		// simpleGet();

	}

	private static void connectViaAdapter() throws ClientProtocolException,
			IOException {

		//@formatter:off
		Query query = Query.queryBuilder().setTag("*")
										  .setMethod(QueryMethod.SELECT)
										  .setPage("index.html")
										  .build();
		//@formatter:on

		HttpClient client = new DefaultHttpClient();

		QueryHttpAdapter request = new QueryHttpAdapter(GET_DOMAIN, query);
		HttpResponse response = client.execute(request);
		response.getEntity().writeTo(System.out);

	}

	private static void connectViaAdapterPost() throws ClientProtocolException,
			IOException {

		//@formatter:off
		Query query = Query.queryBuilder().setTag("*")
										  .setMethod(QueryMethod.UPDATE)
										  .setPage("post.php")
										  .addCondition(new QueryCondition("somekey #", "somevalue"))
										  .addCondition(new QueryCondition("newkey puper", "newvalue"))
										  .build();
		//@formatter:on										  

		HttpClient client = new DefaultHttpClient();

		QueryHttpAdapter request = new QueryHttpAdapter(POST_DOMAIN, query);
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
