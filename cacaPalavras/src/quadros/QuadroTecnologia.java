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
public class QuadroTecnologia extends QuadroPalavras {
    String[] palavras = {"intel", "memoria", "linux", "socket", "pixel"};
    
    public QuadroTecnologia() {
        quadro =   "P T Z U I N T E L L \n" 
                 + "I A N A U G N O T I \n"
                 + "X S O C K E T L W N \n"
                 + "E C D I Y U P T A U \n" 
                 + "L A M E M O R I A X \n";
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
