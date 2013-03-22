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

	static final Query query1 = new Query();
	static {
		query1.method = QueryMethod.SELECT;
		query1.tag = "*";
		query1.page = "index.html";
	}

	static final Query query2 = new Query();
	static {
		query2.method = QueryMethod.SELECT;
		query2.tag = "body";
		query2.page = "index.html";
	}

	static final Query query3 = new Query();
	static {
		query3.method = QueryMethod.SELECT;
		query3.tag = "ul";
		query3.page = "index.html";
	}

	static final Query query4 = new Query();
	static {
		query4.method = QueryMethod.SELECT;
		query4.tag = "ul";
		query4.page = "index.html";
		query4.conditions.add(new QueryCondition("ul.class", "invisible"));
	}

	static final Query query5 = new Query();
	static {
		query5.method = QueryMethod.SELECT;
		query5.tag = "p._value";
		query5.page = "index.html";
		query5.conditions.add(new QueryCondition("p.id", "1"));
	}

	static final Query query6 = new Query();
	static {
		query6.method = QueryMethod.SELECT;
		query6.tag = "_head";
		query6.page = "index.html";
	}

	static final Query query7 = new Query();
	static {
		query7.method = QueryMethod.UPDATE;
		query7.page = "index.html";
		query7.conditions.add(new QueryCondition("name", "myname"));
		query7.conditions.add(new QueryCondition("bankId", "mybank"));
		query7.conditions.add(new QueryCondition("cardId", "0777"));
		query7.conditions.add(new QueryCondition("form", "myform"));
	}

	static final Query query8 = new Query();
	static {
		query8.method = QueryMethod.UPDATE;
		query8.tag = "_head";
		query8.page = "index.html";
		query8.conditions.add(new QueryCondition("_contents",
				"Contents of the file1 line1 line2 lineN"));
	}

	static final Query query9 = new Query();
	static {
		query9.method = QueryMethod.DELETE;
		query9.tag = "*";
		query9.page = "index.html";
	}

	@Before
	public void setUp() {
		queryDB = new QueryDB();
		queryProcessor = new QueryProcessor(queryDB);
		Utils.turnOnVerboseMode();
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
