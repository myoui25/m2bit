package ss.week6;

import java.util.Scanner;

public class Words {
	
	private static final String END_WORD = "end";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String input;
		
		while(true) {
			input = scan(scanner);
			if (input.equals(END_WORD)) {
				break;
			} else {
				printWord(input);
			}
		}
		System.out.println("End of programme.");
		
	}
	
	private static void printWord(String input) {
		String[] word = input.split(" ");
		
		for(int i=0; i<word.length; i++) {
			System.out.printf("Word %d: %s\n", i+1, word[i]);
		}	
	}
	
	private static String scan(Scanner scanner) {
		String next = scanner.nextLine();
		System.out.printf("Line (or \"end\"): %s\n", next);
		
		return next;
	}
}
