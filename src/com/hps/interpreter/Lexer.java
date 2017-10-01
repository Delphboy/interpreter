package com.hps.interpreter;

import java.util.HashMap;

public class Lexer
{
	private String input;
	private int position;
	private int readPosition;
	private char ch;

	private HashMap<String, Token> map;

	public Lexer(String input)
	{
		map = new HashMap<String, Token>();
		initiliseMap();

		this.input = input;
		readChar();
	}

	public Token nextToken()
	{
		Token token = new Token();
		skipWhiteSpace();

		switch (ch)
		{
			case '=':
				if(peekChar() == '=')
				{
					readChar();
					token = new Token(TokenType.EQ, "==");
				}
				else
				{
					token = new Token(TokenType.ASSIGN, String.valueOf(ch));
				}
				break;
			case '!':
				if(peekChar() == '=')
				{
					readChar();
					token = new Token(TokenType.NOT_EQ, "!=");
				}
				else
				{
					token = new Token(TokenType.BANG, String.valueOf(ch));
				}
				break;
			case ';':
				token = new Token(TokenType.SEMICOLON, String.valueOf(ch));
				break;
			case '(':
				token = new Token(TokenType.LPAREN, String.valueOf(ch));
				break;
			case ')':
				token = new Token(TokenType.RPAREN, String.valueOf(ch));
				break;
			case ',':
				token = new Token(TokenType.COMMA, String.valueOf(ch));
				break;
			case '+':
				token = new Token(TokenType.PLUS, String.valueOf(ch));
				break;
			case '-':
				token = new Token(TokenType.MINUS, String.valueOf(ch));
				break;
			case '<':
				token = new Token(TokenType.LT, String.valueOf(ch));
				break;
			case '>':
				token = new Token(TokenType.GT, String.valueOf(ch));
				break;
			case '*':
				token = new Token(TokenType.ASTERISK, String.valueOf(ch));
				break;
			case '/':
				token = new Token(TokenType.SLASH, String.valueOf(ch));
				break;
			case '{':
				token = new Token(TokenType.LBRACE, String.valueOf(ch));
				break;
			case '}':
				token = new Token(TokenType.RBRACE, String.valueOf(ch));
				break;
			case 0:
				token = new Token(TokenType.EOF, "");
				break;
			default:
				if(isLetter(ch))
				{
					token.literal = readIdentifier();
					token = lookupToken(token.literal);
					return token;
				}
				else if(isDigit(ch))
				{
					return new Token(TokenType.INT, String.valueOf(readNumber()));
				}
				else
				{
					token = new Token(TokenType.ILLEGAL, String.valueOf(ch));
				}

				break;
		}

		readChar();
		return token;
	}

	private void skipWhiteSpace()
	{
		while(ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r')
		{
			readChar();
		}
	}

	private String readIdentifier()
	{
		int pos = position;
		while(isLetter(ch))
		{
			readChar();
		}
		return String.valueOf(input.substring(pos, position));
	}

	public void readChar()
	{
		//mark end of file
		if(readPosition >= input.length())
		{
			ch = 0;
		}
		else // ASCII of current character being read
		{
			ch = input.charAt(readPosition);
		}
		position = readPosition;
		readPosition += 1;
	}

	/**
	 * Returns whether or not a character is a letter or legal character
	 * Allowing '_' means variable names such as 'foo_bar' are allowed in the language
	 * @param c
	 * @return true/false
	 */
	private boolean isLetter(char c)
	{
		return 'a' <= c && ch <= 'z' || 'A' <= c && ch <= 'Z' || ch == '_';
	}

	/**
	 * Initilise the token keyword map
	 */
	private void initiliseMap()
	{
		map.put("fn", new Token(TokenType.FUNCTION));
		map.put("let", new Token(TokenType.LET));
		map.put("true", new Token(TokenType.TRUE));
		map.put("false", new Token(TokenType.FALSE));
		map.put("if", new Token(TokenType.IF));
		map.put("else", new Token(TokenType.ELSE));
		map.put("return", new Token(TokenType.RETURN));
	}

	/**
	 * Search the keyword map for a keyword. If the identifier is found, return correct token
	 * Else return the "identifier" token
	 * @param identifier
	 * @return Token
	 */
	private Token lookupToken(String identifier)
	{
		if(map.containsKey(identifier))
			return map.get(identifier);
		else
			return new Token(TokenType.IDENT, identifier);
	}

	/**
	 * Reads numbers
	 * @return char
	 */
	private char readNumber()
	{
		int pos = position;
		while (isDigit(ch))
		{
			readChar();
		}
		return input.charAt(pos);
	}

	/**
	 * Returns true if the character is a latin number (0-9)
	 * @param c
	 * @return true/false
	 */
	private boolean isDigit(char c)
	{
		return '0' <= c && c <= '9';
	}

	/**
	 * Peek the next character
	 * @return next char along
	 */
	private char peekChar()
	{
		if(readPosition > input.length())
		{
			return 0;
		}
		else
		{
			return input.charAt(readPosition);
		}
	}

}