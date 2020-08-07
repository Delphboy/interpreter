using interpreter.lexer;

namespace interpreter.parser
{
    public interface IExpression : INode
    {
        public Token Token {get; set;}
        public new string TokenLiteral => Token.Literal;
        public new string TokenString => Token.Literal;
    }
}
