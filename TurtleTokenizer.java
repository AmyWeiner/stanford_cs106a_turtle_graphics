/*
 * File: TurtleTokenizer.java
 * --------------------------
 * This file implements a simple tokenizer for the TurtleGraphics system.
 */

/**
 * This class divides up a command string into individual tokens.
 * A token consists of one of two forms:
 *
 * (1) A letter, optionally followed by any number of decimal digits,
 *     as in "F20", "R120", or "D", or
 * (2) A string beginning with "{" and continuing up to the matching "}".
 *
 * The tokenizer ignores all whitespace characters separating tokens.
 */

public class TurtleTokenizer {

/**
 * Creates a new TurtleTokenizer that takes its input from the string str.
 * @param str The string to be scanned
 */
	public TurtleTokenizer(String str) {
		// Fill this in
	}

/**
 * Returns true if there are more tokens to read and false if the tokenizer
 * has reached the end of the input.
 * @return A boolean value indicating whether there are any unread tokens
 */
	public boolean hasMoreTokens() {
		return false; // Replace this line with the correct implementation
	}

/**
 * Returns the next complete token.  If this method is called at the end
 * of the input, the tokenizer returns the empty string.
 * @return The next token in the input
 */
	public String nextToken() {
		return ""; // Replace this line with the correct implementation
	}

// Add private methods and instance variables here

}
