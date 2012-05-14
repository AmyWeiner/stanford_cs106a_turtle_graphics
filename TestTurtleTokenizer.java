import acm.program.ConsoleProgram;


public class TestTurtleTokenizer extends ConsoleProgram{
	public void run() {
		String program = readLine("Enter a turtle program: ");
		TurtleTokenizer tokenizer = new TurtleTokenizer(program);
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			println(token);
		}
	}

}
