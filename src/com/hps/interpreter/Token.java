package com.hps.interpreter;


import java.util.HashMap;

public class Token
{
	public String tokenType;
	public String literal;

	//Create an empty token

	public Token()
	{

	}

	public Token(String tokenType)
	{
		this.tokenType = tokenType;
		this.literal = tokenType;
	}

	public Token(String tokenType, String literal)
	{
		this.tokenType = tokenType;
		this.literal = literal;
	}
}
