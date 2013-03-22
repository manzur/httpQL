package com.httpQL;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestQueryDB {

	IQueryDB queryDB;
	static final int ABSENT_QUERY_ID = -1;

	@Before
	public void setUp() {
		queryDB = new QueryDB();
	}

	@Test(expected = AbsentQueryException.class)
	public void testAbsent() {
		Query query = queryDB.getQuery(ABSENT_QUERY_ID);
	}

	@Test
	public void testPresent() {
		Query inputQuery = new Query();
		Integer queryID = queryDB.putQuery(inputQuery);
		Query outputQuery = queryDB.getQuery(queryID);
		assertEquals(inputQuery, outputQuery);
	}

}
