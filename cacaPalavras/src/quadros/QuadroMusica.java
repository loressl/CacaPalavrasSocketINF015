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
public class QuadroMusica extends QuadroPalavras {
    String[] palavras = {"cordas", "violao", "baixo", "nota", "som"};
    
    public QuadroMusica() {
        quadro =   "C T Z C O R D A S B \n" 
                 + "S A N A U G N O T A \n"
                 + "O H B A X U I G I I \n"
                 + "M C U U N B Q A E X \n"
                 + "O A E X V I O L A O \n";
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
