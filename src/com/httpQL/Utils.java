package com.httpQL;

public class Utils {
	private static boolean verbose;

	public static void turnOnVerboseMode() {
		verbose = true;
	}

	public static void turnOffVerboseMode() {
		verbose = false;
	}

	public static void debugMsg(String msg) {
		if (verbose) {
			System.out.println(msg);
		}
	}
}
