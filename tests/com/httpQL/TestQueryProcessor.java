package com.httpQL;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TestQueryProcessor {

	/* Simple queries */
	static final String queryText1 = "select * from index.html";
	static final String queryText2 = "insert into index.html values(File contents)";
	static final String queryText3 = "update index.html set param1 = \"value1\" and param2 = \"value2\"";
	static final String queryText4 = "delete * from index.html";

	/* Sophisticated ones */
	static final String queryText5 = "select book from index.html";
	static final String queryText6 = "select .bookstore from index.html";
	static final String queryText7 = "select bookstore.book from index.html";
	static final String queryText8 = "select bookstore..book from index.html";
	static final String queryText9 = "select bookstore.book from index.html where limit book by 1";
	static final String queryText10 = "select bookstore.book from index.html where book@position < 3";
	static final String queryText11 = "select bookstore.book from index.html where book@position = @last()";
	static final String queryText12 = "select title from index.html where title@lang=*";
	static final String queryText13 = "select title from index.html where title@lang='eng'";
	static final String queryText14 = "select bookstore.book from index.html where book@price > 35";
	static final String queryText15 = "select bookstore.book.title from index.html where book@price > 35";
	static final String queryText16 = "select bookstore.book.title from index.html";
	static final String queryText17 = "select bookstore.book.price@text() from index.html";
	static final String queryText18 = "select host.service from index.html where service@text() = 'DNS'";
	static final String queryText19 = "select network.host.interface.arec@text() from index.html where limit host by 2";
	static final String queryText20 = "select * from index.html where @speciality=*";
	static final String queryText21 = "select degree from index.html where degree@from='Harvard'";
	static final String queryText22 = "select author from index.html where author@text() = 'Bob Martin'";
	static final String queryText23 = "select author from index.html where author@firstname='Bob' and author@lastname='Martin'";
	static final String queryText24 = "select *.ul.li.div.p.a from index.html where *@id='w3c_home_upcoming_events' and limit li by 1 and limit div by 2 and limit p by 1";
	static final String queryText25 = "select ul..ul from index.html where limit ul#1 by 1 and limit ul#2 by 2";

	private IQueryDB queryDB;
	private QueryProcessor queryProcessor;

	//@formatter:off
	static final Query query1 = Query.queryBuilder().setMethod(QueryMethod.SELECT)
													.setTag("*")
													.setPage("index.html")
													.build();

	static final Query query2 = Query.queryBuilder().setMethod(QueryMethod.INSERT)
													.setPage("index.html")
													.addCondition(new QueryCondition("values", "File contents"))
													.build();

	static final Query query3 = Query.queryBuilder().setMethod(QueryMethod.UPDATE)
													.setPage("index.html")
													.addCondition(new QueryCondition("param1", "value1"))
													.addCondition(new QueryCondition("param2", "value2"))
													.build();
	
	static final Query query4 = Query.queryBuilder().setMethod(QueryMethod.DELETE)
													.setTag("*")
													.setPage("index.html")
													.build();

	static final Query query5 = Query.queryBuilder().setMethod(QueryMethod.SELECT)
													.setTag("book")
													.setPage("index.html")
													.build();

	static final Query query6 = Query.queryBuilder().setMethod(QueryMethod.SELECT)
												    .setTag(".bookstore")
												    .setPage("index.html")
												    .build();

	static final Query query7 = Query.queryBuilder().setMethod(QueryMethod.SELECT)
													.setTag("bookstore.book")
													.setPage("index.html")
													.build();
	
	static final Query query8 = Query.queryBuilder().setMethod(QueryMethod.SELECT)
													.setTag("bookstore..book")
													.setPage("index.html")
													.build();
	
	static final Query query9 = Query.queryBuilder().setMethod(QueryMethod.SELECT)
													.setTag("bookstore.book")
													.setPage("index.html")
													.addCondition(new QueryCondition("book", "1", ConditionType.LIMIT))
													.build();

	static final Query query10 = Query.queryBuilder().setMethod(QueryMethod.SELECT)
													 .setTag("bookstore.book")
													 .setPage("index.html")
													 .addCondition(new QueryCondition("book@position", "3", ConditionType.LT))
													 .build();
	
	static final Query query11 = Query.queryBuilder().setMethod(QueryMethod.SELECT)
			 										 .setTag("bookstore.book")
			 										 .setPage("index.html")
			 										 .addCondition(new QueryCondition("book@position", "@last()"))
			 										 .build();

	static final Query query12 = Query.queryBuilder().setMethod(QueryMethod.SELECT)
			  										 .setTag("title")
			  										 .setPage("index.html")
			  										 .addCondition(new QueryCondition("title@lang", "*"))
			  										 .build();
	
	static final Query query13 = Query.queryBuilder().setMethod(QueryMethod.SELECT)
				 									 .setTag("title")
				 									 .setPage("index.html")
				 									 .addCondition(new QueryCondition("title@lang", "'eng'"))
				 									 .build();
	
	
	static final Query query14 = Query.queryBuilder().setMethod(QueryMethod.SELECT)
			 										 .setTag("bookstore.book")
			 										 .setPage("index.html")
			 										 .addCondition(new QueryCondition("book@price", "35", ConditionType.GT))
			 										 .build();

	static final Query query15 = Query.queryBuilder().setMethod(QueryMethod.SELECT)
													 .setTag("bookstore.book.title")
													 .setPage("index.html")
													 .addCondition(new QueryCondition("book@price", "35", ConditionType.GT))
													 .build();
	
	static final Query query16 = Query.queryBuilder().setMethod(QueryMethod.SELECT)
													 .setTag("bookstore.book.title")
													 .setPage("index.html")
													 .build();
	
	static final Query query17 = Query.queryBuilder().setMethod(QueryMethod.SELECT)
			 										 .setTag("bookstore.book.price@text()")
			 										 .setPage("index.html")
			 										 .build();
	
	static final Query query18 = Query.queryBuilder().setMethod(QueryMethod.SELECT)
			 										 .setTag("host.service")
													 .setPage("index.html")
													 .addCondition(new QueryCondition("service@text()", "'DNS'"))
													 .build();

	static final Query query19 = Query.queryBuilder().setMethod(QueryMethod.SELECT)
			 										 .setTag("network.host.interface.arec@text()")
													 .setPage("index.html")
													 .addCondition(new QueryCondition("host", "2", ConditionType.LIMIT))
													 .build();
	
	static final Query query20 = Query.queryBuilder().setMethod(QueryMethod.SELECT)
			 										 .setTag("*")
			 										 .setPage("index.html")
			 										 .addCondition(new QueryCondition("*@speciality", "*"))
			 										 .build();

	static final Query query21 = Query.queryBuilder().setMethod(QueryMethod.SELECT)
													 .setTag("degree")
													 .setPage("index.html")
													 .addCondition(new QueryCondition("degree@from", "'Harvard'"))
													 .build();
	
	static final Query query22 = Query.queryBuilder().setMethod(QueryMethod.SELECT)
													 .setTag("author")
										 			 .setPage("index.html")
										 			 .addCondition(new QueryCondition("author@text()", "'Bob Martin'"))
										 			 .build();


	static final Query query23 = Query.queryBuilder().setMethod(QueryMethod.SELECT)
													 .setTag("author")
													 .setPage("index.html")
													 .addCondition(new QueryCondition("author@firstname", "'Bob'"))
													 .addCondition(new QueryCondition("author@lastname", "'Martin'"))
													 .build();
	
	static final Query query24 = Query.queryBuilder().setMethod(QueryMethod.SELECT)
													 .setTag("*.ul.li.div.p.a")
													 .setPage("index.html")
													 .addCondition(new QueryCondition("*@id", "'w3c_home_upcoming_events'"))
													 .addCondition(new QueryCondition("li", "1", ConditionType.LIMIT))
													 .addCondition(new QueryCondition("div", "2", ConditionType.LIMIT))
													 .addCondition(new QueryCondition("p", "1", ConditionType.LIMIT))
													 .build();

	static final Query query25 = Query.queryBuilder().setMethod(QueryMethod.SELECT)
													 .setTag("ul..ul")
													 .setPage("index.html")
													 .addCondition(new QueryCondition("ul#1", "1", ConditionType.LIMIT))
													 .addCondition(new QueryCondition("ul#2", "2", ConditionType.LIMIT))
													 .build();
	
	//@formatter:on		

	@Before
	public void setUp() {
		queryDB = new QueryDB();
		queryProcessor = new QueryProcessor(queryDB);

		Logger.getLogger("").setLevel(Level.INFO);
	}

	@Test
	public void testSimpleQueries() {
		testQuery(queryText1, query1);
		testQuery(queryText2, query2);
		testQuery(queryText3, query3);
		testQuery(queryText4, query4);
	}

	@Test
	public void testXPathQueries() {
		testQuery(queryText5, query5);
		testQuery(queryText6, query6);
		testQuery(queryText7, query7);
		testQuery(queryText8, query8);
		testQuery(queryText9, query9);
		testQuery(queryText10, query10);
		testQuery(queryText11, query11);
		testQuery(queryText12, query12);
		testQuery(queryText13, query13);
		testQuery(queryText14, query14);
		testQuery(queryText15, query15);
		testQuery(queryText16, query16);
		testQuery(queryText17, query17);
		testQuery(queryText18, query18);
		testQuery(queryText19, query19);
		testQuery(queryText20, query20);
		testQuery(queryText21, query21);
		testQuery(queryText22, query22);
		testQuery(queryText23, query23);
		testQuery(queryText24, query24);
		testQuery(queryText25, query25);
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
