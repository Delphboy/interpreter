using System;
using System.Collections.Generic;

namespace interpreter.lexer
{
    public class Token
    {
        private readonly TokenType _tokenType;
        private readonly string _literal;

        public Token(TokenType tokenType, string literal)
        {
            _tokenType = tokenType;
            _literal = literal;
        }

        protected bool Equals(Token other)
        {
            return _tokenType == other._tokenType && _literal == other._literal;
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            if (obj.GetType() != this.GetType()) return false;
            return Equals((Token) obj);
        }
        
        public override int GetHashCode()
        {
            return HashCode.Combine((int) _tokenType, _literal);
        }
    }
}
