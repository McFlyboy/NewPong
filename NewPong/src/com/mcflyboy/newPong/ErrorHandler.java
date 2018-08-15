package com.mcflyboy.newPong;

import java.io.PrintStream;

public class ErrorHandler {
	public static PrintStream getPrintStream() {
		return System.err;
	}
	public static void println(String string) {
		System.err.println(string);
	}
}
