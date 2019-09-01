package cacaPalavras;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

//  ♠Implementações Pendentes♠
//    • Mostrar a contagem regressiva para o usuario
//    • Mostra quantidade de palavras o usuario já acertou - OPCIONAL

public class CacaPalavra implements Runnable {
    private Thread contador;
    private Quadros quadros;
    private Socket cliente;
    
    private String[] temas = {"aleatorio", "esporte", "musica"};
    private String[] palavras;// = quadros.getQuadroPalavras().get("esporte").getPalavras();
    private String[] palavrasUsuario;// = new String[palavras.length];
    private String quadroPalavras;// = quadros.getQuadroPalavras().get("esporte").getQuadro();
    private String palavraUsuario;
    private String tempoTotal;

    private short time;
    private short tempoLimite = 2;
    
    int qtdPalavras;
    int acertos;
    int tentativas;
    int pontuacao;

    public CacaPalavra(Socket cliente) {
        this.cliente = cliente;
        quadros = new Quadros();
        acertos = 0;
        tentativas = 0;
        pontuacao = 0;
        //int numAleatorio = new Random().nextInt(4);
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public void run() {
        try {
            Scanner in = new Scanner(this.cliente.getInputStream());
            PrintWriter out = new PrintWriter(this.cliente.getOutputStream(), true);
            
            inicio:
            while(true){
            
                mostrarRegras(in, out);
                escolheQuadro(in, out);
                
                //começa a contar o tempo
                contador = new Thread(conometro);
                contador.start();

                out.println("\nCAÇA PALAVRAS\n");
                out.flush();

                out.print(quadroPalavras);
                out.flush();

                while (acertos <= qtdPalavras) {
                    palavraUsuario = in.next();

                    if(palavraUsuario.equals("fim")){
                        try {
                            cliente.close();
                            contador.stop();
                            Thread.currentThread().stop();
                            break;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    int acertou = verificaPalavra(palavraUsuario);
                    
                    if(tempoLimite == 0){
                        out.print("acabou\n");
                        out.print("Tempo Esgotado -> [0"+tempoTotal+"0]\n");
                        contador.stop();
                        acertos = 0;
                        tempoLimite = 2;
                        tentativas = 0;
                        continue inicio;
                    }

                    if (acertos == 5) {
                        tentativas++;
                        out.print("fim\n");
                        out.println("Voce achou: \"" + palavraUsuario + "\" ☻");
                        out.println("Parabéns !! Você encontrou todas as palavras ☻");
                        out.println("Total de tentativas: " + tentativas);
                        out.println("Pontuação: " + pontuacao + "\n");
                        out.println("Tempo total: " + tempoTotal);
                        
                        contador.stop();
                        acertos = 0;
                        tempoLimite = 2;
                        tentativas = 0;
                        continue inicio;
                    } 
                    else{
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
                }// fim do primeiro while
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Runnable conometro = new Runnable() {
        @Override
        public void run() {
            try{
                    short minutos = 1 ;
                    short segundos = 59;
                    
                    while(true){
                        if(minutos == 0 && segundos == 0){
                            String format = minutos + ":"+ segundos;
                            tempoTotal = format;
                            tempoLimite = minutos;
                            break;
                        }
                            
                        if(segundos == 0){
                            segundos = 59;
                            minutos--;
                        }
                        Thread.sleep(1000);
                        segundos--;
                    }
                
            } catch (Exception e){}
 
        }
    };
    
    public int verificaPalavra(String palavra) {
        int i=0;
        for (String palavraDigitada : palavrasUsuario) {
            if(palavra.equalsIgnoreCase(palavraDigitada))
                return 0;
            
            for (String palavraRegistrada : palavras) {
                if (palavra.equalsIgnoreCase(palavraRegistrada)) {
                    calculaPontuacao(palavra.length());
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

    private void calculaPontuacao(int tamanhoPalavra) {

        switch (tamanhoPalavra) {
            case 4:
                pontuacao+=1;
                break;
            case 5:
                pontuacao+=3;
                break;
            default:
                pontuacao+=5;
                break;
        }
    }
    
    @SuppressWarnings("CallToPrintStackTrace")
    private void escolheQuadro(Scanner in, PrintWriter out) {
        String temaUsuario;
        boolean temaValido = false;
        
        out.println("Escolha o tema do Caça Palavras ou digite \"SAIR\" para encerrar o jogo");
        out.println("Aleatorio | Esporte | Musica\n");
        temaUsuario = in.next();
        temaUsuario = temaUsuario.toLowerCase();
        
        if(temaUsuario.equals("fim")){
            try {
                cliente.close();
                Thread.currentThread().stop();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } 
        else{
            for (String tema : temas) {
                if(temaUsuario.equals(tema)){
                    temaValido = true;
                }
            }
            if(temaValido == false)
                temaUsuario = "aleatorio";
               
            palavras = quadros.getQuadroPalavras().get(temaUsuario).getPalavras();
            palavrasUsuario = new String[palavras.length];
            qtdPalavras = palavras.length;
            quadroPalavras = quadros.getQuadroPalavras().get(temaUsuario).getQuadro();
            inicializarPalavrasUsuario();
                
                
//            if(!temaValido){
//                palavras = quadros.getQuadroPalavras().get("aleatorio").getPalavras();
//                palavrasUsuario = new String[palavras.length];
//                qtdPalavras = palavras.length;
//                quadroPalavras = quadros.getQuadroPalavras().get("aleatorio").getQuadro();
//                inicializarPalavrasUsuario();
//            }
        }
    }
    
    private void mostrarRegras(Scanner in, PrintWriter out) {
        out.println("#################################################################################################");
        out.println("##                                       REGRAS DO JOGO                                        ##");
        out.println("## -> As palavras têm que ter pelo menos 3 letras                                              ##");
        out.println("## -> Forme palavras utilizando letras que estejam juntas nas direções: Vertical ou horizontal ##");
        out.println("## -> Desconsidere acentos ao formar palavras. (Um “C” pode ser “Ç” ou um “A” pode ser “Ã”)    ##");
        out.println("## -> Forme qualquer palavra no plural ou singular                                             ##");
        out.println("## -> Não valem nomes próprios ou palavras estrangeiras                                        ##");
        out.println("## -> Tempo para encontrar as palavras: 2 Minutos                                                                                            ##");
        out.println("##                                                                                             ##");
        out.println("## -> PONTUAÇÃO:  4 letras = 1 ponto | 5 letras = 3 pontos | 6 ou mais letras = 5 pontos       ##");
        out.println("##                                                                                             ##");
        out.println("## -> Para encerrar o jogo digite \"SAIR\"                                                       ##");
        out.println("#################################################################################################\n");
        
    }

}
