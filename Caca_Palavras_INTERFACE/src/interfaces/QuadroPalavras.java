/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.awt.Button;
import java.util.List;

/**
 *
 * @author jonatas.santos
 */
public abstract class QuadroPalavras {
    protected String[][] quadro;
    
    public QuadroPalavras() {}
    
    public abstract String[][] getQuadro();
    public abstract String[] getPalavras(); 
    public abstract void pegarPosicaoBotao(Button[][] buttons);
    
    public abstract List<Button> verificaBotoes(String palavra);
}
