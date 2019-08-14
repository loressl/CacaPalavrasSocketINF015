package cacaPalavras;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        try {
            Socket client = new Socket("", 1234);
            Scanner in = new Scanner(client.getInputStream());
            Scanner inUser = new Scanner(System.in);
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            String saida = "";

            // Imprime o nome: CAÇA PALAVRAS
            System.out.println(in.nextLine());

            //Imprime o quadro de palavras
            System.out.println(in.nextLine());
            System.out.println(in.nextLine());
            System.out.println(in.nextLine());
            System.out.println(in.nextLine());
            System.out.println(in.nextLine());
            System.out.println(in.nextLine());

            while (true) {
                if( saida.equalsIgnoreCase("fim")){
                    System.out.println(in.nextLine());
                    System.out.println();
                    System.out.println(in.nextLine());
                    System.out.println();
                    System.out.println(in.nextLine());
                    break;
                }
                System.out.println("\nEscreva uma Palavra: ");
                out.println(inUser.nextLine());
                System.out.println();
                saida = in.nextLine();
                System.out.println(in.nextLine());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
