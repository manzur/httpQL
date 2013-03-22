package com.httpQL;

import java.util.HashMap;
import java.util.Map;

public class QueryDB implements IQueryDB {

	Map<Integer, Query> queries = new HashMap<>();

	@Override
	public Integer putQuery(Query query) {
		Integer result = query.hashCode();
		queries.put(result, query);

		return result;
	}

	@Override
	public Query getQuery(Integer id) {
		if (queries.containsKey(id)) {
			return queries.get(id);
		}

		throw new AbsentQueryException("Such query doesn't exist");
	}

}
