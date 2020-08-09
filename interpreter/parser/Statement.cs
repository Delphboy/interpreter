using interpreter.lexer;

namespace interpreter.parser
{
    public class Statement : INode
    {
        public Token Token{get; set;}
        public string TokenLiteral => Token.Literal;
        public string TokenString => Token.Literal;

        public Statement()
        {
            Token = new Token();
        }
    }
}
