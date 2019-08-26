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
public class QuadroAleatorio extends QuadroPalavras{
    String[] palavras = {"ceu", "cadeiras", "samba", "corda", "cana"};
            
    public QuadroAleatorio(){
        quadro =   "J A D C O R D A X Y \n"
                 + "C A N A U G V L Á R \n"
                 + "E H S A M B A G I G \n"
                 + "U C U U N L Q K E I \n"
                 + "A P C A D E I R A S \n";
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
