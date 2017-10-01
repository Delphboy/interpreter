package com.hps.interpreter;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class LexerTest
{
	@Test
	public void testNextTokenSimple()
	{
		String input = "{}(),;=+";

		Lexer lexer = new Lexer(input);

		ArrayList<Token> expectedTokenList = new ArrayList<Token>();

		expectedTokenList.add(new Token(TokenType.LBRACE, "{"));
		expectedTokenList.add(new Token(TokenType.RBRACE, "}"));
		expectedTokenList.add(new Token(TokenType.LPAREN, "("));
		expectedTokenList.add(new Token(TokenType.RPAREN, ")"));
		expectedTokenList.add(new Token(TokenType.COMMA, ","));
		expectedTokenList.add(new Token(TokenType.SEMICOLON, ";"));
		expectedTokenList.add(new Token(TokenType.ASSIGN, "="));
		expectedTokenList.add(new Token(TokenType.PLUS, "+"));
		expectedTokenList.add(new Token(TokenType.EOF, ""));

		for (Token expectedToken : expectedTokenList)
		{
			assertEquals(expectedToken.tokenType, lexer.nextToken().tokenType);
		}
	}

	@Test
	public void testNextToken()
	{
		String input = "let five = 5;\n" +
				"let ten = 10;\n" +
				"\n" +
				"let add = fn(x, y) {\n" +
				"  x + y;\n" +
				"};\n" +
				"\n" +
				"let result = add(five, ten);\n" +
				"!-/*5;\n" +
				"5 < 10 > 5;\n" +
				"\n" +
				"if (5 < 10) {\n" +
				"\treturn true;\n" +
				"} else {\n" +
				"\treturn false;\n" +
				"}\n" +
				"\n" +
				"10 == 10;\n" +
				"10 != 9;";

		Lexer lexer = new Lexer(input);

		ArrayList<Token> expectedTokenList = new ArrayList<Token>();

		expectedTokenList.add(new Token(TokenType.LET, "let"));
		expectedTokenList.add(new Token(TokenType.IDENT, "five"));
		expectedTokenList.add(new Token(TokenType.ASSIGN, "="));
		expectedTokenList.add(new Token(TokenType.INT, "5"));
		expectedTokenList.add(new Token(TokenType.SEMICOLON, ";"));
		expectedTokenList.add(new Token(TokenType.LET, "let"));
		expectedTokenList.add(new Token(TokenType.IDENT, "ten"));
		expectedTokenList.add(new Token(TokenType.ASSIGN, "="));
		expectedTokenList.add(new Token(TokenType.INT, "10"));
		expectedTokenList.add(new Token(TokenType.SEMICOLON, ";"));
		expectedTokenList.add(new Token(TokenType.LET, "let"));
		expectedTokenList.add(new Token(TokenType.IDENT, "add"));
		expectedTokenList.add(new Token(TokenType.ASSIGN, "="));
		expectedTokenList.add(new Token(TokenType.FUNCTION, "fn"));
		expectedTokenList.add(new Token(TokenType.LPAREN, "("));
		expectedTokenList.add(new Token(TokenType.IDENT, "x"));
		expectedTokenList.add(new Token(TokenType.COMMA, ","));
		expectedTokenList.add(new Token(TokenType.IDENT, "y"));
		expectedTokenList.add(new Token(TokenType.RPAREN, ")"));
		expectedTokenList.add(new Token(TokenType.LBRACE, "{"));
		expectedTokenList.add(new Token(TokenType.IDENT, "x"));
		expectedTokenList.add(new Token(TokenType.PLUS, "+"));
		expectedTokenList.add(new Token(TokenType.IDENT, "y"));
		expectedTokenList.add(new Token(TokenType.SEMICOLON, ";"));
		expectedTokenList.add(new Token(TokenType.RBRACE, "}"));
		expectedTokenList.add(new Token(TokenType.SEMICOLON, ";"));
		expectedTokenList.add(new Token(TokenType.LET, "let"));
		expectedTokenList.add(new Token(TokenType.IDENT, "result"));
		expectedTokenList.add(new Token(TokenType.ASSIGN, "="));
		expectedTokenList.add(new Token(TokenType.IDENT, "add"));
		expectedTokenList.add(new Token(TokenType.LPAREN, "("));
		expectedTokenList.add(new Token(TokenType.IDENT, "five"));
		expectedTokenList.add(new Token(TokenType.COMMA, ","));
		expectedTokenList.add(new Token(TokenType.IDENT, "ten"));
		expectedTokenList.add(new Token(TokenType.RPAREN, ")"));
		expectedTokenList.add(new Token(TokenType.SEMICOLON, ";"));
		expectedTokenList.add(new Token(TokenType.BANG, "!"));
		expectedTokenList.add(new Token(TokenType.MINUS, "-"));
		expectedTokenList.add(new Token(TokenType.SLASH, "/"));
		expectedTokenList.add(new Token(TokenType.ASTERISK, "*"));
		expectedTokenList.add(new Token(TokenType.INT, "5"));
		expectedTokenList.add(new Token(TokenType.SEMICOLON, ";"));
		expectedTokenList.add(new Token(TokenType.INT, "5"));
		expectedTokenList.add(new Token(TokenType.LT, "<"));
		expectedTokenList.add(new Token(TokenType.INT, "10"));
		expectedTokenList.add(new Token(TokenType.GT, ">"));
		expectedTokenList.add(new Token(TokenType.INT, "5"));
		expectedTokenList.add(new Token(TokenType.SEMICOLON, ";"));
		expectedTokenList.add(new Token(TokenType.IF, "if"));
		expectedTokenList.add(new Token(TokenType.LPAREN, "("));
		expectedTokenList.add(new Token(TokenType.INT, "5"));
		expectedTokenList.add(new Token(TokenType.LT, "<"));
		expectedTokenList.add(new Token(TokenType.INT, "10"));
		expectedTokenList.add(new Token(TokenType.RPAREN, "}"));
		expectedTokenList.add(new Token(TokenType.LBRACE, "{"));
		expectedTokenList.add(new Token(TokenType.RETURN, "return"));
		expectedTokenList.add(new Token(TokenType.TRUE, "true"));
		expectedTokenList.add(new Token(TokenType.SEMICOLON, ";"));
		expectedTokenList.add(new Token(TokenType.RBRACE, "}"));
		expectedTokenList.add(new Token(TokenType.ELSE, "else"));
		expectedTokenList.add(new Token(TokenType.LBRACE, "{"));
		expectedTokenList.add(new Token(TokenType.RETURN, "return"));
		expectedTokenList.add(new Token(TokenType.FALSE, "false"));
		expectedTokenList.add(new Token(TokenType.SEMICOLON, ";"));
		expectedTokenList.add(new Token(TokenType.RBRACE, "}"));
		expectedTokenList.add(new Token(TokenType.INT, "10"));
		expectedTokenList.add(new Token(TokenType.EQ, "=="));
		expectedTokenList.add(new Token(TokenType.INT, "10"));
		expectedTokenList.add(new Token(TokenType.SEMICOLON, ";"));
		expectedTokenList.add(new Token(TokenType.INT, "10"));
		expectedTokenList.add(new Token(TokenType.NOT_EQ, "!="));
		expectedTokenList.add(new Token(TokenType.INT, "9"));
		expectedTokenList.add(new Token(TokenType.SEMICOLON, ";"));
		expectedTokenList.add(new Token(TokenType.EOF, ""));

		for (Token expectedToken : expectedTokenList)
		{
			assertEquals(expectedToken.tokenType, lexer.nextToken().tokenType);
		}
	}
}