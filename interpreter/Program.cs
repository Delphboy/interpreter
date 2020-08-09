using System;
using System.IO;
using System.Threading.Tasks;

using interpreter.lexer;

namespace interpreter
{
    class Program
    {
        static async Task Main(string[] args)
        {
            await ProcessMonkeyFile(args[0]);
            Repl();
        }

        private static async Task ProcessMonkeyFile(string fileLocation)
        {
            Console.WriteLine($"Given the file: {fileLocation} for processing");

            byte[] fileData;
            string fileContent = string.Empty;

            if(!File.Exists(fileLocation))
            {
                throw new FileNotFoundException($"The inputted Monkey file does not exist at the given location: \n{fileLocation} ");
            }

            using(var fs = new FileStream(fileLocation, FileMode.Open))
            {
                fileData = new byte[fs.Length];
                await fs.ReadAsync(fileData, 0, (int)fs.Length);
                fileContent = System.Text.Encoding.ASCII.GetString(fileData);
            }

            Console.WriteLine(fileContent);
        }

        private static void Repl()
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
