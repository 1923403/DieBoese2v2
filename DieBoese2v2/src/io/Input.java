package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input {

	public static String readInput() {
		final var in = new BufferedReader(new InputStreamReader(System.in));
		var input = "";

		try {
			input = in.readLine();
		} catch (final IOException e) {
			System.err.println("There seems to be a problem with your input device!");
			System.exit(0);
		}

		return input;
	}
}
