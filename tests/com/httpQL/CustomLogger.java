package com.httpQL;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class CustomLogger {

	static class CustomFormatter extends Formatter {

		@Override
		public String format(LogRecord record) {
			return record.getMessage() + "\n";
		}

	}

	static Logger getLogger(String className) {
		Logger logger = Logger.getLogger(TestQueryProcessor.class.getName());

		logger.setUseParentHandlers(false);

		Handler handler = new ConsoleHandler();
		handler.setFormatter(new CustomFormatter());

		logger.addHandler(handler);

		return logger;
	}

}
