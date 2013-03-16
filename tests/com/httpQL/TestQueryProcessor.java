package com.httpQL;

import org.junit.Test;

public class TestQueryProcessor {

	static final String queryText1 = "select * from index.html";
	static final String queryText2 = "select body from index.html";
	static final String queryText3 = "select ul from index.html";
	static final String queryText4 = "select ul from index.html where ul.class = \"invisible\"";
	static final String queryText5 = "select p._value from index.html where p.id = \"1\"";
	static final String queryText6 = "select _head from index.html";
	static final String queryText7 = "update index.html set name=\"myname\" bankId=\"mybank\" cardId=\"0777\" where form=\"myform\"";
	static final String queryText8 = "update index.html set _contents = \"Contents of the file1 line1 line2 lineN\"";
	static final String queryText9 = "delete * from index.html";

	@Test
	public void test() {
		QueryProcessor queryProcessor = new QueryProcessor(queryDB);
	}

}
