package cacaPalavras;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

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
            while(true) {
                //Exibe as regras do jogo
                exibirRegras(in);

                //Escolhe o tema do jogo
                continua = escolherTema(in, out, inUser, client);

                if(continua){

                    //Exibe o nome "CAÇA PALAVRAS e o Quadro de palavras"
                    exibeQuadro(in);
                    
                    
                    while (true) {
                        //MUDAR A ORDEM DAS CONDICIONAIS
             
                        if(saida.equalsIgnoreCase("fim")){
                            System.out.println(in.nextLine());
                            System.out.println();
                            System.out.println(in.nextLine());
                            System.out.println();
                            System.out.println(in.nextLine());
                            System.out.println(in.nextLine());
                            System.out.println(in.nextLine());
                            saida = "";
                            newGame();
                            continue inicioGame;
                        }
                        
                        if(saida.equalsIgnoreCase("acabou")){
                            System.out.println(in.nextLine());
                            saida = "";
                            newGame();
                            continue inicioGame;
                        }

                        System.out.println("\nEscreva uma Palavra ou \"SAIR\" para encerrar o jogo");
                        palavraDigitada = inUser.nextLine();
                        if(palavraDigitada.equalsIgnoreCase("sair")){
                            endGame(out,client);
                            break inicioGame;
                        }

                        out.println(palavraDigitada);
                        System.out.println();
                        saida = in.nextLine();
                        
                        if(saida.equalsIgnoreCase("acabou")){
                            System.out.println(in.nextLine());
                            saida = "";
                            newGame();
                            continue inicioGame;
                        }
                        System.out.println(in.nextLine());
                    }// Fim do Jogo
                } //Executa se continua for true
                else{ // se continuar for false
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void exibirRegras(Scanner in) {
        for(int i=0; i<14; i++)
            System.out.println(in.nextLine());
    }
    
    private static boolean escolherTema(Scanner in, PrintWriter out, Scanner inUser, Socket client) {
        String tema;
        System.out.println(in.nextLine());
        System.out.println(in.nextLine());
        System.out.println(in.nextLine());
        tema = inUser.nextLine();
        
        try{
            if(tema.equalsIgnoreCase("sair")){
                endGame(out,client);
                return false;
            } else
                out.println(tema); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    
    private static void exibeQuadro(Scanner in) {
        // Exibe o nome: CAÇA PALAVRAS
        System.out.println(in.nextLine());
        System.out.println(in.nextLine());

        //Exibe o quadro de palavras
        System.out.println(in.nextLine());
        System.out.println(in.nextLine());
        System.out.println(in.nextLine());
        System.out.println(in.nextLine());
        System.out.println(in.nextLine());
        System.out.println(in.nextLine());
    }
    
    private static void newGame(){
        try{
            Thread.sleep(5000);
            limparConsole();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void endGame(PrintWriter out, Socket client){
        try{
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
