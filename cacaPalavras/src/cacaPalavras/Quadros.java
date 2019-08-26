/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cacaPalavras;

import quadros.*;
import java.util.HashMap;

/**
 *
 * @author jonatas.santos
 */
public class Quadros {
    private HashMap<String,QuadroPalavras> quadroPalavras;

    public Quadros() {
        initialize();
    }
    
    private void initialize(){
        quadroPalavras = new HashMap();
        QuadroPalavras aleatorio = new QuadroAleatorio();
        QuadroPalavras esporte = new QuadroEsporte();
        QuadroPalavras musica = new QuadroMusica();
        
        quadroPalavras.put("aleatorio",aleatorio);
        quadroPalavras.put("esporte",esporte);
        quadroPalavras.put("musica",musica);
    }

    public HashMap<String, QuadroPalavras> getQuadroPalavras() {
        return quadroPalavras;
    }
    
}
