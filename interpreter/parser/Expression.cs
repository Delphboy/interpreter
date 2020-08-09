using interpreter.lexer;

namespace interpreter.parser
{
    public class Expression : INode
    {
        public Token Token {get; set;}
        public string TokenLiteral => Token.Literal;
        public string TokenString => Token.Literal;

        public Expression()
        {
            Token = new Token();
        }
    }
}
