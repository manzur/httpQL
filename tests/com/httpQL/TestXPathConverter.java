package com.httpQL;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestXPathConverter {

	@Test
	public void test1() throws AbsentElementException {
		testHelper(TestQueryProcessor.query5, "//book");
		testHelper(TestQueryProcessor.query6, "/bookstore");
		testHelper(TestQueryProcessor.query7, "//bookstore/book");
		testHelper(TestQueryProcessor.query8, "//bookstore//book");
		testHelper(TestQueryProcessor.query9, "//bookstore/book[1]");
		testHelper(TestQueryProcessor.query10, "//bookstore/book[@position<3]");
		testHelper(TestQueryProcessor.query11,
				"//bookstore/book[@position=@last()]");
		testHelper(TestQueryProcessor.query12, "//title[@lang]");
		testHelper(TestQueryProcessor.query13, "//title[@lang='eng']");
		testHelper(TestQueryProcessor.query14, "//bookstore/book[@price>35]");
		testHelper(TestQueryProcessor.query15,
				"//bookstore/book[@price>35]/title");
		testHelper(TestQueryProcessor.query16, "//bookstore/book/title");
		testHelper(TestQueryProcessor.query17, "//bookstore/book/price/text()");
		testHelper(TestQueryProcessor.query18, "//host/service[text()='DNS']");
		testHelper(TestQueryProcessor.query19,
				"//network/host[2]/interface/arec/text()");
		testHelper(TestQueryProcessor.query20, "//*[@speciality]");
		testHelper(TestQueryProcessor.query21, "//degree[@from='Harvard']");
		testHelper(TestQueryProcessor.query22, "//author[text()='Bob Martin']");
		testHelper(TestQueryProcessor.query23,
				"//author[@firstname='Bob' and @lastname='Martin']");
		testHelper(TestQueryProcessor.query24,
				"//*[@id='w3c_home_upcoming_events']/ul/li[1]/div[2]/p[1]/a");
		testHelper(TestQueryProcessor.query25, "//ul[1]//ul[2]");

	}

	private void testHelper(Query query, String expected)
			throws AbsentElementException {

		QueryXPathConverter converter = new QueryXPathConverter(query);
		String xpath = converter.convertToXPath();
		assertEquals(expected, xpath);
	}
}
