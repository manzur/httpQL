package com.httpQL;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

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

		Iterator<XPathElement> iter = elements.iterator();

		int index = 0;
		while (iter.hasNext()) {
			XPathElement element = iter.next();
			if (element.tag.equals(tag) && index++ == nth) {
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
		StringTokenizer tokenizer = new StringTokenizer(tag, ".", true);

		String prefix = "";
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();

			if (token.equals(".")) {
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
				index = Integer.valueOf(token.substring(position));

			} else {
				tag = token;
				index = 0;
			}

			return new TagInfo(tag, index);
		}
	}
}

class XPathElement {
	List<XPathPredicate> predicates = new LinkedList<>();

	final String prefix;
	final String tag;

	public XPathElement(String tag) {
		this("", tag);
	}

	public XPathElement(String prefix, String tag) {
		this.prefix = prefix;
		this.tag = tag;
	}

	String build() {
		return prefix + tag;
	}

	public void addPredicate(QueryCondition condition) {
		int leftPos = condition.attribute.indexOf('@');
		int rightPos = condition.value.indexOf('@');

		String left = condition.attribute.substring(leftPos + 1);
		String right = condition.value.substring(rightPos + 1);

		XPathPredicate predicate = new XPathPredicate(left, right);
		predicates.add(predicate);
	}

}

class XPathPredicate {

	//@formatter:off
	private static final Set<String> specFunctions = ImmutableSet.of( "last"
  															        , "text" 
															        , "position"
															        );
	//@formatter:on

	private String attribute;
	private String value;

	XPathPredicate(String left, String right) {
		attribute = checkAndConvert(left);
		value = checkAndConvert(right);
	}

	private static String checkAndConvert(String s) {
		return specFunctions.contains(s) ? s + "()" : s;
	}
}

class AbsentElementException extends Exception {
}
