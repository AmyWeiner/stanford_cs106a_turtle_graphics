/*
 * File: TurtleGraphics.java
 * -------------------------
 * This file represents the starter file for the TurtleGraphics application.
 * Your job in Assignment #4 is to complete this application by implementing
 * the execute and replaceAction methods.  You also need to implement the
 * TurtleScanner class in the file TurtleScanner.java.
 */

import java.util.StringTokenizer;

import acm.program.*;

public class TurtleGraphics extends GraphicsProgram {

	/* Constants to set the application size */
	public static final int APPLICATION_WIDTH = 1000;
	public static final int APPLICATION_HEIGHT = 600;
	
	private static final int FORWARD_MOVE = 50;
	private static final int LEFT_TURN = 90;
	private static final int RIGHT_TURN = 90;

	/*
	 * Initializes the application.  Programs call the init() method before
	 * laying out the components in the window and the run() method after the
	 * layout is complete.  This application is driven entirely by events
	 * generated by the buttons and menus and does not need a run() method.
	 */
	public void init() {
		turtle = new GTurtle();
		add(turtle);
		ui = new TurtleGraphicsUI();
		ui.start();
	}

	/*
	 * Executes a turtle program, which consists of a string of commands.
	 * Each command consists of a single letter, optionally followed by an
	 * integer.  The commands you must implement for this assignment are
	 *
	 *    F#       Moves forward the specified number of pixels (default = 50)
	 *    L#       Turn left the specified number of degrees (default = 90)
	 *    R#       Turn right the specified number of degrees (default = 90)
	 *    U        Raise the pen so that moving no longer draws a line
	 *    D        Lower the pen to resume line drawing
	 *    X#{cmds} Execute the block of commands the specified number of times
	 */
	public void execute(String str) {
		tokenizer = new TurtleTokenizer(str);

		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			translateToCommand(token);
		}
	}

	/*
	 * Implements the Replace button action, which replaces all instances of a
	 * pattern string in the current file with a replacement string.  The
	 * pattern and replacement strings are taken from the replacement field in
	 * the user interface, where they appear as pattern->replacement.   
	 */
	public void replaceAction() {
		String replacement = ui.getReplacementField();
		replacement = removeWhiteSpace(replacement);
		String pattern = getPattern(replacement);
		String replace = getReplace(replacement);
		String text = ui.getProgramText();
		//performReplacements();
		//ui.setProgramText(str);
	}

	private void translateToCommand(String token) {
		//println("token is: " + token);
		char ch = token.charAt(0);
		switch (ch){
		case 'F': 
			if (token.length() == 1) {
				turtle.forward(FORWARD_MOVE);
			}
			if (isFollowedByInteger(token)) {
				String sub = token.substring(1);
				int pixels = Integer.parseInt(sub);
				turtle.forward(pixels);
			}
			break;
		case 'L': 
			if (token.length() == 1) {
				turtle.left(LEFT_TURN);
			}
			if (isFollowedByInteger(token)) {
				String sub = token.substring(1);
				int pixels = Integer.parseInt(sub);
				turtle.left(pixels);
			}
			break;
		case 'R':
			if (token.length() == 1) {
				turtle.right(RIGHT_TURN);
			}
			if (isFollowedByInteger(token)) {
				String sub = token.substring(1);
				int pixels = Integer.parseInt(sub);
				turtle.right(pixels);
			}
			break;
		case 'U': 
			turtle.penUp();
			break;
		case 'D':
			turtle.penDown();
			break;
		case 'X':
			// X4{X4{F120L90}L10}
			if (isFollowedByInteger(token)) {
				String sub = token.substring(1);
				//System.out.println("sub: " + sub);
				int nTimes = Integer.parseInt(sub);
				String tokenNext = tokenizer.nextToken();
				System.out.println("tokenNext: " + tokenNext);
				int length = tokenNext.length();
				String subNext = tokenNext.substring(1, (length -1));
				System.out.println("subnext: " + subNext);
				//System.out.println("WHAT WE ARE EXECUTING: " + subNext);
				
				for (int i = 0; i < nTimes; i ++) {
					//System.out.println("nTimes: " + nTimes + "iteration: " + i);
					//System.out.println("subnext: " + subNext);
					execute(subNext);
				}
			}
			default:
			break;
		}
	}

	private boolean isFollowedByInteger(String token) {
		if (token.length() == 1) {
			return false;
		}
		char next = token.charAt(1);
		return Character.isDigit(next);
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

	private String getPattern(String replacement) {
		String result = "";
		char ch = '-';
		for (int i = 0; i < replacement.length(); i ++) {
			result += replacement.charAt(i); 
			if (replacement.charAt(i) == ch) {
				result = result.substring(0, i);
			}
		}
		return result;
	}
	
	private String getReplace(String replacement) {
		char ch = '>';
		int index = replacement.indexOf(ch);
		String sub = replacement.substring(index + 1);
		return sub;
	}
	/* Private instance variables */

	private GTurtle turtle;         /* The GTurtle object        */
	private TurtleGraphicsUI ui;    /* The user-interface object */
	TurtleTokenizer tokenizer;
}
