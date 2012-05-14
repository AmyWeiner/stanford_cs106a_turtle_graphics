import acm.program.ConsoleProgram;


public class TestTurtleTokenizer extends ConsoleProgram{
	public void run() {
		int counter = 0;
		String program = readLine("Enter a turtle program: ");
		TurtleTokenizer tokenizer = new TurtleTokenizer(program);
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			counter ++;
			if (counter == 5) break;
			println(token);
		}
	}

}
