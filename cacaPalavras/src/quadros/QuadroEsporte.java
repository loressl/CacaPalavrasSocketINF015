/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadros;

/**
 *
 * @author jonatas.santos
 */
public class QuadroEsporte extends QuadroPalavras {
    String[] palavras = {"futebol", "bola", "natacao", "remo", "gols"};

    public QuadroEsporte() {
        quadro =   "J F U T E B O L X A \n"
                 + "B A N A U G V L A R \n"
                 + "O M Y G O L S W I E \n"
                 + "L C U U N B Q A E M \n"
                 + "A A C N A T A C A O \n";
    }

    @Override
    public String getQuadro() {
        return quadro;
    }

    @Override
    public String[] getPalavras() {
        return palavras;
    }
}
