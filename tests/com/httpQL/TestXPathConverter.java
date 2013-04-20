package com.httpQL;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestXPathConverter {

	@Test
	public void test1() throws AbsentElementException {
		Query query = makeQuery("book");
		QueryXPathConverter converter = new QueryXPathConverter(query);
		String xpath = converter.convertToXPath();

		final String expected = "//book";
		assertEquals(expected, xpath);
	}

	@Test
	public void test2() throws AbsentElementException {
		Query query = makeQuery(".bookstore");

		QueryXPathConverter converter = new QueryXPathConverter(query);
		String xpath = converter.convertToXPath();

		final String expected = "/bookstore";
		assertEquals(expected, xpath);
	}

	@Test
	public void test3() throws AbsentElementException {
		Query query = makeQuery("bookstore.book");

		QueryXPathConverter converter = new QueryXPathConverter(query);
		String xpath = converter.convertToXPath();

		final String expected = "//bookstore/book";
		assertEquals(expected, xpath);
	}

	@Test
	public void test4() throws AbsentElementException {
		Query query = makeQuery("title");
		QueryCondition condition = new QueryCondition("title@lang", "eng");
		query.conditions.add(condition);

		QueryXPathConverter converter = new QueryXPathConverter(query);
		String xpath = converter.convertToXPath();

		final String expected = "//title";
		assertEquals(expected, xpath);
	}

	private static Query makeQuery(String tag) {
		//@formatter:off
		Query query = Query.queryBuilder().setMethod(QueryMethod.SELECT)
										  .setTag(tag)
										  .build();
		//@formatter:on
		return query;
	}

}
