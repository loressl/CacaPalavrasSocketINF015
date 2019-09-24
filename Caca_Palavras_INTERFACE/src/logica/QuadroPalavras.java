/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.Button;

/**
 *
 * @author jonatas.santos
 */
public abstract class QuadroPalavras {
    protected String[][] quadro;

    public abstract String[][] getQuadro();
    public abstract String[] getPalavras();
    public abstract void pegarPosicaoBotao(Button[][] buttons);
    
}
