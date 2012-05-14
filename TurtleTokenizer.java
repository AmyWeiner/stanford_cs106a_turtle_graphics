import java.util.StringTokenizer;

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
		str = str.toUpperCase();
		command = removeWhiteSpace(str);
	}

	/**
	 * Returns true if there are more tokens to read and false if the tokenizer
	 * has reached the end of the input.
	 * @return A boolean value indicating whether there are any unread tokens
	 */
	public boolean hasMoreTokens() {
		return command.length() > 0; 
	}

	/**
	 * Returns the next complete token.  If this method is called at the end
	 * of the input, the tokenizer returns the empty string.
	 * @return The next token in the input
	 */
	public String nextToken() {
		String result = "";
		char ch = command.charAt(0);
		if (command.length() == 1) {
			result += ch;
			command = updateCommand(command, result);
			return result;
		}
		char ch2 = command.charAt(1);
		switch (ch){
		case 'F': case 'L': case 'R':
			if (Character.isLetter(ch2)) {
				result += ch;
			} else if (Character.isDigit(ch2)) {
				result += ch + findTokenLetterLength(command,ch2);
			} 
			break;
		case 'U': case 'D':
			result += ch;
			break;
		case 'X':
			result += ch + findTokenLetterLength(command,ch2);
			break;
		case '{':
			result += findTokenBracketLength(command, ch); break;
		}
		command = updateCommand(command, result);
		return result;
	}


	private String removeWhiteSpace(String str) {
		String result = "";
		StringTokenizer tokenizer = new StringTokenizer(str);
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			result += token;
		}
		return result;
	}

	private String updateCommand(String command, String str) {
		int length = str.length();
		String sub = command.substring(length);
		return sub;
	}

	private String findTokenLetterLength(String str, char ch) {
		int start = str.indexOf(ch);
		String sub = command.substring(start);
		String result = "";
		for(int i = 0; i < sub.length(); i ++) {
			char ch2 = sub.charAt(i);
			if (Character.isDigit(ch2)) {
				result += ch2;
			} else if (Character.isLetter(ch2)) {
				return result;
			}
		}
		return result;
	} 

	private String findTokenBracketLength(String str, char ch) {
		bracketCounter = 0;
		String result = "";
		for (int i = 0; i < str.length(); i ++) {
			result += str.charAt(i);
			if (str.charAt(i) == '{') {
				bracketCounter ++;
			} else if (str.charAt(i) == '}') {
				bracketCounter --;
				if (bracketCounter == 0) return result;
			}
			
		}
		return result;
	}

	private String command;

	private int bracketCounter;

}
