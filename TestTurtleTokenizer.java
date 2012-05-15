/*
 * File: TestTurtleTokenizer.java
 * ------------------------------------------------------------------------
 * This program test the Turtle Tokenizer class in order to ensure that the
 * tokens are being processed correctly. The program continuously prompts 
 * the user to enter a Turtle Graphics command, and then it parses it into
 * the appropriate tokens, and displays the tokens on the screen.
 */


import acm.program.ConsoleProgram;


public class TestTurtleTokenizer extends ConsoleProgram{
	public void run() {
		while (true) {
			String program = readLine("Enter a turtle program: ");
			TurtleTokenizer tokenizer = new TurtleTokenizer(program);
			while (tokenizer.hasMoreTokens()) {
				String token = tokenizer.nextToken();
				println(token);
			}
		}
	}
}
