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
		command = removeWhiteSpace(str);


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
		String result = "";
		char ch = command.charAt(0);
		char ch2 = command.charAt(1);
		switch (ch){
		case 'F':
			if (Character.isLetter(ch2)) {
				result += ch;
			} else if (Character.isDigit(ch2)) {
				result += ch + findTokenLength(command,ch2);
			}
		case 'L':
			if (Character.isLetter(ch2)) {
				result += ch;
			} else if (Character.isDigit(ch2)) {
				result += ch + findTokenLength(command,ch2);
			}
		case 'R':
			if (Character.isLetter(ch2)) {
				result += ch;
			}else if (Character.isDigit(ch2)) {
				result += ch + findTokenLength(command,ch2);
			}
		case 'U':
			if (Character.isLetter(ch2)) {
				result += ch;
			}
			else if (Character.isDigit(ch2)) {
				result += ch + findTokenLength(command,ch2);
			}
		case 'D':
			if (Character.isLetter(ch2)) {
				result += ch;
			}
			else if (Character.isDigit(ch2)) {
				result += ch + findTokenLength(command,ch2);
			}
		case 'X':
			if (Character.isLetter(ch2)) {
				result += ch;
			}
			else if (Character.isDigit(ch2)) {
				result += ch + findTokenLength(command,ch2);
			}
		}
		command = updateCommand(command);
		return result;
		// Replace this line with the correct implementation
	}

	private String removeWhiteSpace(String str) {
		String result = "";
		for(int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (Character.isLetter(ch)) {
				result += ch;
			}
		}
		return result;
	}

	private String updateCommand(String command) {
		String result = "";
		return result;
	}

	private String findTokenLength(String str, char ch) {
		int start = str.indexOf(ch);
		String sub = command.substring(start);
		String result = "";
		for(int i = 0; i < sub.length(); i ++) {
			char ch2 = str.charAt(i);
			if (Character.isDigit(ch2)) {
				result += ch2;
			}
		}
		return result;
	} 




	// Add private methods and instance variables here
	private String command;

}
