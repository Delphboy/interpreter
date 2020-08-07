using System.Collections.Generic;

namespace interpreter.lexer
{
    public class Lexer
    {
        private string _input;
        private int _position;
        private int _readPosition;
        private char _currentCharacter;

        private Dictionary<string, TokenType> _keywords;

        public Lexer(string input)
        {
            _input = input;

            _position = 0;
            _readPosition = 0;

            _keywords = new Dictionary<string, TokenType>();

            initialiseKeywordMap();
            readCharacter();
        }


        public Token NextToken()
        {
            Token token;
            skipWhiteSpace();

            switch(_currentCharacter)
            {
                case '=':
                    if(peekChar() == '=')
                    {
                        readCharacter();
                        token = new Token(TokenType.EQ, "==");
                    }
                    else
                    {
                        token = new Token(TokenType.ASSIGN, "=");
                    }

                    break;
                case ';':
                    token = new Token(TokenType.SEMICOLON, _currentCharacter.ToString());
                    break;
                case '(':
                    token = new Token(TokenType.LPAREN, _currentCharacter.ToString());
                    break;
                case ')':
                    token = new Token(TokenType.RPAREN, _currentCharacter.ToString());
                    break;
                case ',':
                    token   = new Token(TokenType.COMMA, _currentCharacter.ToString());
                    break;
                case '+':
                    token = new Token(TokenType.PLUS, _currentCharacter.ToString());
                    break;
                case '-':
                    token = new Token(TokenType.MINUS, _currentCharacter.ToString());
                    break;
                case '!':
                    if(peekChar() == '=')
                    {
                        readCharacter();
                        token = new Token(TokenType.NOT_EQ, "!=");
                    }
                    else
                    {
                        token = new Token(TokenType.BANG, _currentCharacter.ToString());
                    }
                    break;
                case '/':
                    token = new Token(TokenType.SLASH, _currentCharacter.ToString());
                    break;
                case '*':
                    token = new Token(TokenType.ASTERISK, _currentCharacter.ToString());
                    break;
                case '<':
                    token = new Token(TokenType.LT, _currentCharacter.ToString());
                    break;
                case '>':
                    token = new Token(TokenType.RT, _currentCharacter.ToString());
                    break;
                case '{':
                    token = new Token(TokenType.LBRACE, _currentCharacter.ToString());
                    break;
                case '}':
                    token = new Token(TokenType.RBRACE, _currentCharacter.ToString());
                    break;
                case '\0':
                    token = new Token(TokenType.EOF, "");
                    break;
                default:
                    if(isLetter(_currentCharacter))
                    {
                        var identifier = readIdentifier();
                        var tokenType = lookupIdentifier(identifier);

                        token = new Token(tokenType, identifier);
                        return token;
                    }
                    else if(isDigit(_currentCharacter))
                    {
                        return new Token(TokenType.INT, readNumber());
                    }
                    else
                    {
                        token = new Token(TokenType.ILLEGAL, _currentCharacter.ToString());
                    }
                    break;
            }

            readCharacter();
            return token;
        }

        private void readCharacter()
        {
            if(_readPosition >= _input.Length)
            {
                _currentCharacter = '\0'; //ASCII NULL character
            }
            else
            {
                _currentCharacter = _input[_readPosition];
            }

            _position = _readPosition;
            _readPosition++;
        }

        private string readIdentifier()
        {
            var pos = _position;

            while(isLetter(_currentCharacter))
            {
                readCharacter();
            }

            return _input.Substring(pos, _position - pos);
        }

        private string readNumber()
        {
            var pos = _position;

            while(isDigit(_currentCharacter))
            {
                readCharacter();
            }
            return _input.Substring(pos, _position - pos);
        }

        private char peekChar()
        {
            if(_readPosition > _input.Length)
            {
                return '\0';
            }
            return _input[_readPosition];
        }

        private bool isLetter(char character)
        {
            return 'a' <= character && character >= 'z'
                || 'A' <= character && character >= 'Z'
                || character == '_';
        }

        private bool isDigit(char character)
        {
            return '0' <= character
                && character <= '9';
        }

        private void initialiseKeywordMap()
        {
            _keywords.Add("fn", TokenType.FUNCTION);
            _keywords.Add("let", TokenType.LET);
            _keywords.Add("true", TokenType.TRUE);
            _keywords.Add("false", TokenType.FALSE);
            _keywords.Add("if", TokenType.IF);
            _keywords.Add("else", TokenType.ELSE);
            _keywords.Add("return", TokenType.RETURN);
        }

        private TokenType lookupIdentifier(string identifier)
        {
            if(_keywords.ContainsKey(identifier))
            {
                return _keywords[identifier];
            }

            return TokenType.IDENT;
        }

        private void skipWhiteSpace()
        {
            while(_currentCharacter == ' '
                  || _currentCharacter == '\t'
                  || _currentCharacter == '\n'
                  || _currentCharacter == '\r')
            {
                readCharacter();
            }
        }

    }
}
