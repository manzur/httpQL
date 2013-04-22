package com.httpQL;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

public class QueryXPathConverter {

	private Query query;
	private List<XPathElement> elements = new LinkedList<>();

	public QueryXPathConverter(Query query) {
		this.query = query;
		extractXPathElements(query.tag);
	}

	XPathElement getNthTagTypeOf(String tag, int nth)
			throws AbsentElementException {

		XPathElement result = null;

		int index = 0;
		Iterator<XPathElement> iterator = elements.iterator();
		while (iterator.hasNext()) {
			XPathElement element = iterator.next();
			if (element.tag.equals(tag) && ++index == nth) {
				result = element;
				break;
			}
		}

		if (result == null) {
			throw new AbsentElementException();
		}

		return result;
	}

	public String convertToXPath() throws AbsentElementException {

		for (QueryCondition condition : query.conditions) {
			TagInfo info = TagInfo.fromCondition(condition);

			XPathElement element = getNthTagTypeOf(info.tag, info.index);
			element.addPredicate(condition);
		}

		StringBuilder builder = new StringBuilder();
		for (XPathElement element : elements) {
			builder.append(element.build());
		}

		// "//" is the default path for the xpath
		if (builder.charAt(0) != '/') {
			builder.insert(0, "//");
		}

		return builder.toString();
	}

	private void extractXPathElements(String tag) {
		StringTokenizer tokenizer = new StringTokenizer(tag, ".@", true);

		String prefix = "";
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();

			if (token.equals(".") || token.equals("@")) {
				prefix += "/";

			} else {
				XPathElement element = new XPathElement(prefix, token);
				elements.add(element);
				prefix = "";
			}
		}
	}

	static class TagInfo {
		String tag;
		int index;

		private TagInfo(String tag, int index) {
			this.tag = tag;
			this.index = index;
		}

		static TagInfo fromCondition(QueryCondition condition)
				throws AbsentElementException {

			StringTokenizer tokenizer = new StringTokenizer(
					condition.attribute, "@");

			if (!tokenizer.hasMoreTokens()) {
				throw new AbsentElementException();
			}

			final String tag;
			final int index;

			String token = tokenizer.nextToken();
			int position = token.indexOf('#');

			if (position != -1) {
				tag = token.substring(0, position);
				index = Integer.valueOf(token.substring(position + 1));

			} else {
				tag = token;
				index = 1;
			}

			return new TagInfo(tag, index);
		}
	}
}

class XPathElement {
	List<XPathPredicate> predicates = new LinkedList<>();

	final String prefix;
	final String tag;

	XPathElement(String tag) {
		this("", tag);
	}

	XPathElement(String prefix, String tag) {
		this.prefix = prefix;
		this.tag = tag;
	}

	String build() {
		return prefix + tag + predicatesAsString();
	}

	void addPredicate(QueryCondition condition) {
		int leftPos = condition.attribute.indexOf('@');
		int rightPos = condition.value.indexOf('@');

		String left = leftPos != -1 ? condition.attribute.substring(leftPos)
				: condition.attribute;

		String right = rightPos != -1 ? condition.value.substring(rightPos)
				: condition.value;

		XPathPredicate predicate = new XPathPredicate(left, right,
				condition.conditionType);
		predicates.add(predicate);
	}

	private String predicatesAsString() {
		StringBuilder result = new StringBuilder();

		boolean first = true;
		for (XPathPredicate predicate : predicates) {
			final String prepend;
			if (first) {
				first = false;
				prepend = "";
			} else {
				prepend = " and ";
			}

			result.append(prepend);
			result.append(predicate.toString());
		}

		if (result.length() > 0) {
			result.insert(0, '[');
			result.append("]");
		}

		return result.toString();
	}

}

class XPathPredicate {

	//@formatter:off
	private static final Set<String> specFunctions = ImmutableSet.of( "last()"
  															        , "text()" 
															        , "position"
															        );
	
	private static final Map<ConditionType, String> mapConditionToOp = ImmutableMap.of(
																 						  ConditionType.LT, "<"
																						, ConditionType.LE, "<="
																						, ConditionType.EQ, "="
																						, ConditionType.GE, ">="
																						, ConditionType.GT, ">"
																		 			   );
	//@formatter:on

	private String attribute;
	private String value;
	private ConditionType conditionType;

	XPathPredicate(String attribute, String value, ConditionType conditionType) {
		this.attribute = checkAndConvert(attribute);
		this.value = checkAndConvert(value);
		this.conditionType = conditionType;
	}

	private String checkAndConvert(String s) {
		if (s.equalsIgnoreCase("@text()")) {
			return "text()";
		}

		return s;
	}

	@Override
	public String toString() {
		String result;
		if (conditionType == ConditionType.LIMIT) {
			result = value;

		} else if (value.equals("*") && conditionType == ConditionType.EQ) {
			result = attribute;

		} else {
			String binop = mapConditionToOp.get(conditionType);
			result = attribute + binop + value;
		}

		return result;
	}
}

class AbsentElementException extends Exception {
}
