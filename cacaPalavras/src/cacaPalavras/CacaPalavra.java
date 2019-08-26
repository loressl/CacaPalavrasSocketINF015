package cacaPalavras;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

//  ♠Implementações Pendentes♠
//    • Conometrar 2 min para acertar todas as palavras
//    • Pontuar os acertos com base na quantidade de letras
//      -> 4 letras = 1 ponto;
//      -> 5 letras = 3 pontos;
//      -> 6 ou mais letras = 5 pontos!
//    • Exibir regras ao iniciar jogo
//    • Perguntar e exbir para o usuario o tema do caça palavra
//    • receber feedback dos usuarios sobre o jogo ou possivel melhoria (OPCIONAL)

public class CacaPalavra implements Runnable {
    Quadros quadros = new Quadros();
    Socket cliente;
    String[] palavras = quadros.getQuadroPalavras().get("esporte").getPalavras();
    String[] palavrasUsuario = new String[palavras.length];
    String quadroPalavras = quadros.getQuadroPalavras().get("esporte").getQuadro();
    String palavraUsuario;
    
    //int posicaoSorteada;
    int acertos;
    int qtdPalavras = palavras.length;
    int tentativas;

    public CacaPalavra(Socket cliente) {
        this.cliente = cliente;
        int numAleatorio = new Random().nextInt(4);
        acertos = 0;
        tentativas = 0;
        inicializarPalavrasUsuario();
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
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
                int acertou = verificaPalavra(palavraUsuario);
                
                if (acertos == 4) {
                    tentativas++;
                    out.print("fim\n");
                    out.println("Voce achou: \"" + palavraUsuario + "\" ☻");
                    out.println("Total de tentativas: " + tentativas);
                    out.println("Parabéns !! Você encontrou todas as palavras ☻\n");
                    cliente.close();
                    break;
                }

                switch (acertou) {
                    case 1:
                        tentativas++;
                        out.print("nao\n");
                        out.println("Voce achou: \"" + palavraUsuario + "\" ☻");
                        break;
                    case -1:
                        tentativas++;
                        out.print("nao\n");
                        out.println("Palavra não encontrada !");
                        break;
                    default:
                        tentativas++;
                        out.print("nao\n");
                        out.println("Palavra já digitada !");
                        break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int verificaPalavra(String palavra) {
        int i=0;
        for (String palavraDigitada : palavrasUsuario) {
            if(palavra.equalsIgnoreCase(palavraDigitada))
                return 0;
            
            for (String palavraRegistrada : palavras) {
                if (palavra.equalsIgnoreCase(palavraRegistrada)) {
                    palavrasUsuario[i] = palavra;
                    acertos++;
                    i++;
                    return 1;
                }
            }
        }
        return -1;
    }

    private void inicializarPalavrasUsuario() {
        for (int i = 0; i < palavrasUsuario.length; i++) {
            palavrasUsuario[i] = "";
        }
    }

}
