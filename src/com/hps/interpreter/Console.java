package com.hps.interpreter;

import java.util.Scanner;

public class Console
{
	private final String PROMPT = "$ ";

	public Console()
	{
		Scanner scanner = new Scanner(System.in);

		System.out.println("Please enter some code: \n");
		while(true)
		{
			System.out.print(PROMPT);
			String inp = scanner.nextLine();

			System.out.println("inp:\t" + inp);

			Lexer l = new Lexer(inp);
			for(Token t = l.nextToken(); ! t.tokenType.equals(TokenType.EOF); t = l.nextToken())
			{
				System.out.println(t.tokenType);
			}
		}
	}
}
