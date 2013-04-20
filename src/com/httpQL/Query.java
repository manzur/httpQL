package com.httpQL;

import java.util.LinkedList;
import java.util.List;

public class Query {
	QueryMethod method;
	String tag;
	String page;
	List<QueryCondition> conditions = new LinkedList<>();

	public static class QueryBuilder {
		private final Query query;

		public QueryBuilder(Query query) {
			this.query = query;
		}

		public QueryBuilder setMethod(QueryMethod method) {
			query.method = method;
			return this;
		}

		public QueryBuilder setTag(String tag) {
			query.tag = tag;
			return this;
		}

		public QueryBuilder setPage(String page) {
			query.page = page;
			return this;
		}

		public QueryBuilder addCondition(QueryCondition condition) {
			query.conditions.add(condition);
			return this;
		}

		public Query build() {
			return query;
		}
	}

	public static QueryBuilder queryBuilder() {
		return new QueryBuilder(new Query());
	}

	// use builder
	private Query() {
	}

	@Override
	public String toString() {
		// @formatter:off
		return "Method: " + method.toString() + "; " 
			 + "Tag: " + tag + "; "
			 + "Conditions: " + conditions.toString() + "; "  
			 + "Page: " + page.toString();
		// @formatter:on
	}
}
