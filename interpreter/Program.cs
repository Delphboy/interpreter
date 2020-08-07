using System;
using System.Threading.Tasks;

using interpreter.lexer;

namespace interpreter
{
    class Program
    {
        static async Task Main(string[] args)
        {
            await ProcessMonkeyFile(args[0]);
            await Repl();
        }

        private static async Task ProcessMonkeyFile(string fileName)
        {
            Console.WriteLine($"Given the file: {fileName}");
        }

        private static async Task Repl()
        {
            Console.WriteLine("Monkey REPL: Type 'EXIT()' to leave the REPL environment");
            while(true)
            {
                var input = Console.ReadLine();

                if(input == "EXIT()")
                {
                    break;
                }

                var lexer = new Lexer(input);
                var token = lexer.NextToken();

                while(token.Type != TokenType.EOF)
                {
                    Console.WriteLine(token.ToString());
                    token = lexer.NextToken();
                }
            }
        }
    }
}
