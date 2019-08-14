package cacaPalavras;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class CacaPalavra implements Runnable {

    Socket cliente;
    String[] palavras = {"casa", "barco", "samba", "corda"};
    String[] palavrasUsuario = new String[palavras.length];
    String quadroPalavras =   "J A S C O R D A X Y \n"
                            + "C A N A Ú G V L Á R \n"
                            + "A H B Á M U I G Í G \n"
                            + "S Ç Ú U N B Q A E I \n"
                            + "A A E L T B A R C O \n";

    String palavraUsuario;
    //int posicaoSorteada;
    int acertos;
    int qtdPalavras = palavras.length;
    int tentativas;

    public CacaPalavra(Socket cliente) {
        this.cliente = cliente;
        Random numAleatorio = new Random();
        acertos = 0;
        tentativas = 0;
    }

    @Override
    public void run() {
        try {
            Scanner in = new Scanner(this.cliente.getInputStream());
            PrintWriter out = new PrintWriter(this.cliente.getOutputStream(), true);
            out.println("CAÇA PALAVRAS\n");
            out.flush();

            out.print(quadroPalavras);
            out.flush();

            while (acertos <= qtdPalavras) {
                palavraUsuario = in.next();
                Boolean acertou = verificaPalavra(palavraUsuario);
                
                if (acertos == 4) {
                    tentativas++;
                    out.print("fim\n");
                    out.println("Voce achou: \"" + palavraUsuario + "\" ☻");
                    out.println("Total de tentativas: " + tentativas);
                    out.println("Parabéns !! Você encontrou todas as palavras ☻\n");
                    cliente.close();
                    break;
                }

                if (acertou) {
                    tentativas++;
                    out.print("nao\n");
                    out.println("Voce achou: \"" + palavraUsuario + "\" ☻");
                } else if (!acertou) {
                    tentativas++;
                    out.print("nao\n");
                    out.println("Palavra não encontrada !");
                }

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean verificaPalavra(String palavraUsuario) {
        boolean acertou = false;

        for (String palavra : palavras) {
            if (palavraUsuario.equalsIgnoreCase(palavra)) {
                acertou = true;
                acertos++;
            }
        }

        return acertou;
    }

}
