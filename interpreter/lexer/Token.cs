using System;
using System.Collections.Generic;

namespace interpreter.lexer
{
    public class Token
    {
        public TokenType Type {get; set;}
        public string Literal {get; set;}

        public Token(TokenType tokenType, string literal)
        {
            Type = tokenType;
            Literal = literal;
        }

        protected bool Equals(Token other)
        {
            return Type == other.Type && Literal == other.Literal;
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
            return HashCode.Combine((int) Type, Literal);
        }

        public override string ToString()
        {
            return $"{Type}:{Literal}";
        }
    }
}
