package com.httpQL;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

public class ResponseProcessor {
	private final IQueryDB queryDB;

	public ResponseProcessor(IQueryDB queryDB) {
		this.queryDB = queryDB;
	}

	public void process(Integer queryID, HttpResponse response)
			throws ResponseProcessorException {
		try {
			Query query = queryDB.getQuery(queryID);

			// filter output only when select is used
			if (query.method == QueryMethod.SELECT) {

				QueryXPathConverter converter = new QueryXPathConverter(query);
				String xpath = converter.convertToXPath();

				HtmlCleaner cleaner = new HtmlCleaner();
				TagNode root = cleaner.clean(response.getEntity().getContent());

				Object[] result = root.evaluateXPath(xpath);

				for (Object o : result) {
					TagNode node = (TagNode) o;
					System.out.println(node.getText());
				}

			} else {
				response.getEntity().writeTo(System.out);
			}

		} catch (IOException | AbsentElementException | XPatherException e) {
			throw new ResponseProcessorException();

			// strangely, htmlcleaner throws NPE instead of XPatherException
		} catch (NullPointerException e) {
			throw new ResponseProcessorException();
		}
	}
}
