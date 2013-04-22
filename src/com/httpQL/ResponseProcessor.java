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

	public void process(Integer queryID, HttpResponse response) {
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
					System.out.println(o);
				}

			} else {
				response.getEntity().writeTo(System.out);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (AbsentElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPatherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
