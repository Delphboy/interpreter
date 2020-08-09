using System;

using interpreter.lexer;

namespace interpreter.parser
{
    public class Identifier : Expression
    {
        public string Value {get;}

        public Identifier(Token token, string value)
        {
            this.Token = token;
            this.Value = value;
        }
    }
}
