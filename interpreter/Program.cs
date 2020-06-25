using System;
using System.Threading.Tasks;

namespace interpreter
{
    class Program
    {
        static async Task Main(string[] args)
        {
            await Run("test.jpg");
        }

        private static async Task Run(string fileName)
        {
            Console.WriteLine($"Given the file: {fileName}");
        }
    }
}
