package cacaPalavras;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

//  ♠Implementações Pendentes♠
//    • Mostrar a contagem regressiva para o usuario

public class CacaPalavra implements Runnable {
    private Thread contador;
    private Quadros quadros;
    private Socket cliente;
    
    private String[] temas = {"aleatorio", "esporte", "musica", "tecnologia"};
    private String[] palavras;
    private String[] palavrasAcertadas;
    private String quadroPalavras;
    private String palavraUsuario;
    private String tempoTotal = "";
    private String tempoDecorrido = "";

    private short time;
    private short tempoLimite = 2;
    
    private short qtdPalavras;
    private short acertos;
    private short tentativas;
    private short pontuacao;

    public CacaPalavra(Socket cliente) {
        this.cliente = cliente;
        quadros = new Quadros();
        acertos = 0;
        tentativas = 0;
        pontuacao = 0;
    }
    
    private void inicializarPalavrasAcertadas() {
        palavrasAcertadas = new String[5];
        for (int i = 0; i < 5; i++) {
            palavrasAcertadas[i] = "";
        }
    }
    
    private String exibirPalavrasAcertadas() {
        String words = "";
        String word;
        
        for (int i = 0; i < 5; i++) {
            word = palavrasAcertadas[i];
            
            if(i==4)
                words += "[" + word + "] ";
            else
                words += "[" + word + "], ";
        }
        
        return words;
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
                    out.println("\nPalavras Encontradas: " + exibirPalavrasAcertadas());
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
                        acertos = 0;
                        tempoLimite = 2;
                        tentativas = 0;
                        continue inicio;
                    }

                    if (acertos == 5) {
                        contador.stop();
                        tentativas++;
                        out.print("fim\n");
                        out.println("Voce achou: \"" + palavraUsuario + "\" ☻");
                        out.println("Parabéns !! Você encontrou todas as palavras ☻");
                        out.println("Palavras Encontradas: " + exibirPalavrasAcertadas());
                        out.println("Total de tentativas: " + tentativas);
                        out.println("Pontuação: " + pontuacao + "\n");
                        
                        //Criar metodo para formatar tempo
                        int minutosDecorridos = Integer.parseInt(tempoDecorrido.split(":")[0]);
                        int segundosDecorridos = Integer.parseInt(tempoDecorrido.split(":")[1]);
                        
                        if(segundosDecorridos > 9)
                            out.println("Tempo Decorrido: [0"+ minutosDecorridos + ":" + segundosDecorridos +"]");
                        else if(segundosDecorridos < 10)
                            out.println("Tempo Decorrido: [0"+ minutosDecorridos + ":0" + segundosDecorridos +"]");
                        
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
                    short minutosTotal = 1 ;
                    short segundosTotal = 59;
                    short minutosDecorrido = 0 ;
                    short segundosDecorrido = 0;
                    
                    while(true){
                        if(minutosTotal == 0 && segundosTotal == 0){
                            tempoTotal = minutosTotal + ":"+ segundosTotal;
                            tempoLimite = minutosTotal;
                            Thread.currentThread().stop();
                            break;
                        }
                            
                        if(segundosTotal == 0){
                            segundosTotal = 59;
                            minutosTotal--;
                        }
                        
                        if(segundosDecorrido == 59){
                            segundosDecorrido = 0;
                            minutosDecorrido++;
                        }
                        
                        Thread.sleep(1000);
                        segundosTotal--;
                        segundosDecorrido++;
                        
                        tempoDecorrido = minutosDecorrido + ":"+ segundosDecorrido;
                    }
                
            } catch (Exception e){}
 
        }
    };
    
    private int verificaPalavra(String palavra) {
        for (String palavraDigitada : palavrasAcertadas) {
            if(palavra.equalsIgnoreCase(palavraDigitada))
                return 0;
            
            for (String palavraRegistrada : palavras) {
                if (palavra.equalsIgnoreCase(palavraRegistrada)) {
                    calculaPontuacao(palavra.length());
                    palavrasAcertadas[acertos] = palavra;
                    acertos++;
                    return 1;
                }
            }
        }
        return -1;
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
        
        out.println("Escolha abaixo o tema do Caça Palavras ou digite \"SAIR\" para encerrar o jogo");
        out.println("Aleatorio | Esporte | Musica | Tecnologia\n");
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
            
            inicializarPalavrasAcertadas();
            palavras = quadros.getQuadroPalavras().get(temaUsuario).getPalavras();
            qtdPalavras = (short) palavras.length;
            quadroPalavras = quadros.getQuadroPalavras().get(temaUsuario).getQuadro();
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
        out.println("## -> Tempo para encontrar as palavras: 2 Minutos                                              ##");
        out.println("##                                                                                             ##");
        out.println("## -> PONTUAÇÃO:  4 letras = 1 ponto | 5 letras = 3 pontos | 6 ou mais letras = 5 pontos       ##");
        out.println("##                                                                                             ##");
        out.println("## -> Para encerrar o jogo digite \"SAIR\"                                                       ##");
        out.println("#################################################################################################\n");
        
    }

}
