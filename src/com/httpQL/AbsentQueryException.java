package com.httpQL;

public class AbsentQueryException extends IllegalArgumentException {

	public AbsentQueryException() {
	};

	public AbsentQueryException(String msg) {
		super(msg);
	};
}
