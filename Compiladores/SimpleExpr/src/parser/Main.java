/*  
    Integrantes:

    Enrico Giannobile                RA: 19.00610-0
    Guilherme de Campos              RA: 20.00089-8
    Luis Guilherme de Souza Munhoz   RA: 20.01937-8

    
    Para executar o interpretador na linha de comando (fora do IDE):
        java -jar simple_expr.jar
 */
package parser;

public class Main {

    public static void main(String[] args) {
        java.io.Reader reader = null;
        if (args.length == 0) {
            reader = new java.io.InputStreamReader(System.in);
        } else {
            if (args.length != 1) {
                System.out.println("Uso : java -jar drp <nome do arquivo>");
            } else {
                try {
                    java.io.FileInputStream stream = new java.io.FileInputStream(args[0]);
                    reader = new java.io.InputStreamReader(stream);
                } catch (java.io.FileNotFoundException e) {
                    System.out.println("Arquivo não  encontrado: \"" + args[0] + "\"");
                } catch (Exception e) {
                    System.out.println("Exceção inesperada ao abrir o arquivo: " + e);
                }
            }
        }
        try {
            // Cria um scanner (gerado pelo JFlex)
            Scanner scanner = new Scanner(reader);
            // Cria um parser (gerado pelo CUP)
            Parser parser = new Parser(scanner);
            // Executa a análise
            parser.parse();
        } catch (Exception e) {
            System.out.println("Exceção inesperada durante a análise: " + e);
        }
    }
}
