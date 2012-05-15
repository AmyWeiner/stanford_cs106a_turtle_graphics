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
	
	/* Constants to set the defaults for the commands */
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
		TurtleTokenizer tokenizer = new TurtleTokenizer(str);

		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			translateToCommand(tokenizer, token);
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
		replacement = removeWhiteSpace(replacement);		//text from replacement field without white space
		String pattern = getPattern(replacement);			//command to use as replacement
		String replace = getReplace(replacement);			//command to be replaced
		String text = ui.getProgramText();					//original command prior to replacement
		String replaced = performReplacements(text, pattern, replace);
		ui.setProgramText(replaced);
	}

	/* This method determines which Turtle command to execute based on the token it receives. */
	private void translateToCommand(TurtleTokenizer tokenizer, String token) {
		char ch = token.charAt(0);
		switch (ch){
		case 'F': 
			if (token.length() == 1) {
				turtle.forward(FORWARD_MOVE);						//default for moving forward
			}
			if (isFollowedByInteger(token)) {					
				String sub = token.substring(1);
				int pixels = Integer.parseInt(sub);
				turtle.forward(pixels);
			}
			break;
		case 'L': 
			if (token.length() == 1) {								//default for turning left
				turtle.left(LEFT_TURN);
			}
			if (isFollowedByInteger(token)) {
				String sub = token.substring(1);
				int pixels = Integer.parseInt(sub);
				turtle.left(pixels);
			}
			break;
		case 'R':
			if (token.length() == 1) {								//default for turning right
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
			if (isFollowedByInteger(token)) {
				String sub = token.substring(1);					
				int nTimes = Integer.parseInt(sub);					//determines the number of times command should be executed
				String tokenNext = tokenizer.nextToken();
				int length = tokenNext.length();
				String subNext = tokenNext.substring(1, (length -1));
				
				for (int i = 0; i < nTimes; i ++) {
					execute(subNext);
				}
			}
			default:
			break;
		}
	}

	/* This method determines whether or not the command letter is followed by an integer. */
	private boolean isFollowedByInteger(String token) {
		if (token.length() == 1) {
			return false;
		}
		char next = token.charAt(1);
		return Character.isDigit(next);
	}
	
	/* This method removes the white space from the command string. */
	private String removeWhiteSpace(String str) {
		String result = "";
		StringTokenizer tokenizer = new StringTokenizer(str);
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			result += token;
		}
		return result;
	}

	/* This method gets the command that will be used as the replacement. */
	private String getPattern(String replacement) {
		int position = replacement.indexOf("->");
		String result = replacement.substring(0, position);
		/*
		String result = "";
		char ch = '-';
		for (int i = 0; i < replacement.length(); i ++) {
			result += replacement.charAt(i); 
			if (replacement.charAt(i) == ch) {
				result = result.substring(0, i);
				
			}
		}
		*/
		return result;
	}
	
	/* This method gets the command that will be replaced. */
	private String getReplace(String replacement) {
		char ch = '>';
		int index = replacement.indexOf(ch);
		String sub = replacement.substring(index + 1);
		return sub;
	}
	
	private String performReplacements(String text, String pattern, String replace) {
		System.out.println("text:" + text);
		System.out.println("pattern:" + pattern);
		System.out.println("replace:" + replace);
		int position = text.indexOf(pattern);
		System.out.println(text.substring(0, 4));
		System.out.println(text.substring(0, 4).equals(pattern));
		 System.out.println("position:" + position);
		String result = "";
		while (position != -1) {
			String head = text.substring(0, position);
			String tail = text.substring(position + pattern.length());
			int length = head.length() + replace.length();
			 result = head + replace + tail;
			 System.out.println("result:" + result);
			 position = result.indexOf(pattern, length);
			
			return result;
		} 
		return result;
	}
	
	/* Private instance variables */

	private GTurtle turtle;         /* The GTurtle object        */
	private TurtleGraphicsUI ui;    /* The user-interface object */
}
