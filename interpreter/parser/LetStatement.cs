using System;
using System.Linq.Expressions;
using interpreter.lexer;

namespace interpreter.parser
{
    public class LetStatement : Statement
    {
        public Identifier Name {get;}
        public Expression Value {get;}

        public LetStatement(Token token, Identifier identifier, Expression expression)
        {
            this.Token = token;
            this.Name = identifier;
            this.Value = expression;
        }
    }
}
