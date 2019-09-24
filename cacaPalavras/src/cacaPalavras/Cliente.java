package cacaPalavras;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

// CORRIGIR DIFERENCA DO TEMPO DE TERMINO DO JOGO
public class Cliente {

    private static String tempoInicial = null;
    private static String tempoFinal = null;

    @SuppressWarnings({"CallToPrintStackTrace", "UseSpecificCatch"})
    public static void main(String[] args) {
        try {
            Socket client = new Socket("", 1234);
            Scanner in = new Scanner(client.getInputStream());
            Scanner inUser = new Scanner(System.in);
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            String saida = "";
            String palavraDigitada;

            boolean continua;

            inicioGame:
            while (true) {
                //Exibe as regras do jogo
                exibirRegras(in);

                //Escolhe o tema do jogo
                continua = escolherTema(in, out, inUser, client);

                if (continua) {
                    Thread.sleep(700);
                    limparConsole();

//                    //Exibe o nome "CAÇA PALAVRAS e o Quadro de palavras"
//                    exibeQuadro(in);
                    while (true) {
                        //Exibe o nome "CAÇA PALAVRAS o tema escolhido e o Quadro de palavras"
                        exibeQuadro(in);

                        System.out.println(in.nextLine());
                        System.out.println(in.nextLine());

                        System.out.println("\nEscreva uma Palavra ou \"SAIR\" para encerrar o jogo");
                        palavraDigitada = inUser.nextLine();
                        if (palavraDigitada.equalsIgnoreCase("sair")) {
                            endGame(out, client);
                            break inicioGame;
                        }

                        out.println(palavraDigitada);
                        System.out.println();
                        saida = in.nextLine();

                        if (saida.equalsIgnoreCase("acabou")) {
                            System.out.println(in.nextLine());
                            saida = "";
                            newGame();
                            continue inicioGame;
                        }

                        if (saida.equalsIgnoreCase("fim")) {
                            System.out.println(in.nextLine());
                            System.out.println();
                            Thread.sleep(1000);
                            limparConsole();
                            System.out.println(in.nextLine());
                            System.out.println(in.nextLine());
                            System.out.println();
                            System.out.println(in.nextLine());
                            System.out.println(in.nextLine());
                            System.out.println(in.nextLine());
                            System.out.println(in.nextLine());
                            saida = "";
                            newGame();
                            continue inicioGame;
                        }
                        //Imprimi se acertou ou não ou se a palavra foi repetida
                        System.out.println(in.nextLine());
                        Thread.sleep(1000);
                        limparConsole();

                    }// Fim do Jogo
                } //Executa se continua for true
                else { // se o usuario escrever sair no lugar do tema
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String pegarTempoInicial(String tempo) {

        /*
            segundos = ( ms / 1000 ) % 60;  
            minutos  = ( ms / 60000 ) % 60;     // 60000    = 60 * 1000
            horas    = ( ms / 3600000 ) % 24;   // 3600000  = 60 * 60 * 1000
            dias     = ms / 86400000            // 86400000 = 24 * 60 * 60 * 1000
         */
        long horas;
        long minutos;
        long segundos;
        long ms = System.currentTimeMillis();

        horas = (ms / 3600000) % 24;
        minutos = (ms / 60000) % 60;
        segundos = (ms / 1000) % 60;

        if (tempo.equalsIgnoreCase("inicial")) {
            if (tempoInicial == null) {
                tempoInicial = String.format("%02d:%02d:%02d", horas - 3, minutos, segundos);
            }
            return tempoInicial;
        } else {
            if (tempoFinal == null) {
                tempoFinal = String.format("%02d:%02d:%02d", horas - 3, minutos + 2, segundos);
            }
            return tempoFinal;
        }

    }

    private static void exibirRegras(Scanner in) {
        for (int i = 0; i < 14; i++) {
            System.out.println(in.nextLine());
        }
    }

    private static boolean escolherTema(Scanner in, PrintWriter out, Scanner inUser, Socket client) {
        String tema;
        System.out.println(in.nextLine());
        System.out.println(in.nextLine());
        System.out.println(in.nextLine());
        tema = inUser.nextLine();

        try {
            if (tema.equalsIgnoreCase("sair")) {
                endGame(out, client);
                return false;
            } else {
                out.println(tema);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private static void exibeQuadro(Scanner in) {
        // Exibe o nome: CAÇA PALAVRAS
        System.out.println("\nCAÇA PALAVRAS" + " | Tempo Inicial: " + pegarTempoInicial("inicial")
                + " | Tempo Final: " + pegarTempoInicial("final") + "\n");

        //Exibe o tema escolhido pelo usuario
        System.out.println(in.nextLine());
        System.out.println();

        //Exibe o quadro de palavras
        System.out.println(in.nextLine());
        System.out.println(in.nextLine());
        System.out.println(in.nextLine());
        System.out.println(in.nextLine());
        System.out.println(in.nextLine());
    }

    private static void newGame() {
        try {
            tempoInicial = null;
            tempoFinal = null;
            Thread.sleep(5000);
            limparConsole();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void endGame(PrintWriter out, Socket client) {
        try {
            limparConsole();
            out.println("fim");
            System.out.println("FIM DE JOGO !!");
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void limparConsole() {
        try {
            Robot robot = new Robot();
            robot.setAutoDelay(10);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_L);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_L);
        } catch (AWTException ex) {
        }
    }

}
