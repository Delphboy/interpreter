using System.Collections.Generic;
using System.Linq;

namespace interpreter.parser
{
    public class Program : INode
    {
        public List<IStatement> Statements {get; }
        public string TokenLiteral => Statements.Count() > 0 ? Statements[0].TokenLiteral : "";
        public string TokenString => Statements.FirstOrDefault().TokenString;

        public Program()
        {
            Statements = new List<IStatement>();
        }
    }
}
