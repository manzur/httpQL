package com.httpQL;

import java.io.IOException;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.htmlcleaner.ContentNode;
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
					System.out.print(node.getText());
					// traverseAndPrint(node, 1);
				}

			} else {
				response.getEntity().writeTo(System.out);
			}

		} catch (IOException | AbsentElementException | XPatherException e) {
			throw new ResponseProcessorException();
		}
	}

	private void printTab(int tabCount) {
		while (tabCount-- > 0) {
			System.out.print("\t");
		}
	}

	private void traverseAndPrint(TagNode node, int level) {

		printTab(level);
		System.out.print(node.getText());

		Iterator iterator = node.getAllChildren().iterator();

		while (iterator.hasNext()) {
			Object next = iterator.next();

			if (next instanceof ContentNode) {
				printTab(level + 1);
				System.out.println(((ContentNode) next).getContent());

			} else if (next instanceof TagNode) {
				traverseAndPrint((TagNode) next, level + 1);
			}
		}
	}
}
