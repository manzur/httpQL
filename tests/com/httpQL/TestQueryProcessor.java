package com.httpQL;

import java.net.MalformedURLException;
import java.net.URL;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TestQueryProcessor {

	static final String queryText1 = "select * from index.html";
	static final String queryText2 = "select body from index.html";
	static final String queryText3 = "select ul from index.html";
	static final String queryText4 = "select ul from index.html where ul.class = \"invisible\"";
	static final String queryText5 = "select p._value from index.html where p.id = \"1\"";
	static final String queryText6 = "select _head from index.html";
	static final String queryText7 = "update index.html set name=\"myname\" and bankId=\"mybank\" and cardId=\"0777\" where form=\"myform\"";
	static final String queryText8 = "update index.html set _contents = \"Contents of the file1 line1 line2 lineN\"";
	static final String queryText9 = "delete * from index.html";

	private IQueryDB queryDB;
	private QueryProcessor queryProcessor;

	//@formatter:off
	static final Query query1 = Query.queryBuilder().setMethod(QueryMethod.SELECT)
													.setTag("*")
													.setPage("index.html")
													.build();

	static final Query query2 = Query.queryBuilder().setMethod(QueryMethod.SELECT)
													.setTag("body")
													.setPage("index.html")
													.build();

	static final Query query3 = Query.queryBuilder().setMethod(QueryMethod.SELECT)
													.setTag("ul")
													.setPage("index.html")
													.build();

	static final Query query4 = Query.queryBuilder().setMethod(QueryMethod.SELECT)
													.setTag("ul")
													.setPage("index.html")
													.addCondition(new QueryCondition("ul.class", "invisible"))
													.build();

	static final Query query5 = Query.queryBuilder().setMethod(QueryMethod.SELECT)
												    .setTag("p._value")
												    .setPage("index.html")
												    .addCondition(new QueryCondition("p.id", "1"))
												    .build();

	static final Query query6 = Query.queryBuilder().setMethod(QueryMethod.SELECT)
													.setTag("_head")
													.setPage("index.html")
													.build();

	static final Query query7 = Query.queryBuilder().setMethod(QueryMethod.UPDATE)
													.setPage("index.html")
													.addCondition(new QueryCondition("name", "myname"))
													.addCondition(new QueryCondition("bankId", "mybank"))
													.addCondition(new QueryCondition("cardId", "0777"))
													.addCondition(new QueryCondition("form", "myform"))
													.build();

	static final Query query8 = Query.queryBuilder().setMethod(QueryMethod.UPDATE)
													.setTag("_head")
													.setPage("index.html")
													.addCondition(new QueryCondition("_contents", "Contents of the file1 line1 line2 lineN"))
													.build();

	static final Query query9 = Query.queryBuilder().setMethod(QueryMethod.DELETE)
													.setTag("*")
													.setPage("index.html")
													.build();
	//@formatter:on		

	@Before
	public void setUp() {
		queryDB = new QueryDB();
		queryProcessor = new QueryProcessor(queryDB);
		// Utils.turnOnVerboseMode();
	}

	@Test
	public void testSelect() {
		testQuery(queryText1, query1);
		testQuery(queryText2, query2);
		testQuery(queryText3, query3);
		testQuery(queryText4, query4);
		testQuery(queryText5, query5);
		testQuery(queryText6, query6);
	}

	@Test
	public void testUpdate() {
		testQuery(queryText7, query7);
	}

	@Test
	public void testDelete() {
		testQuery(queryText9, query9);
	}

	private void testQuery(String queryText, Query query) {
		Integer queryID = queryProcessor.process(queryText);
		Query result = queryDB.getQuery(queryID);
		Assert.assertEquals(query.toString(), result.toString());
	}

	private static URL stringToUrlUnchecked(String text) {
		try {
			return new URL(text);
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(
					"Text coulnd't be converted to URL");
		}
	}

}
