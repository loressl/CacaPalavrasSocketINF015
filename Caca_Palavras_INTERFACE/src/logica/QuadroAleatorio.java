/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.Button;
import java.util.ArrayList;
import java.util.List;
import interfaces.QuadroPalavras;

/**
 *
 * @author jonatas.santos
 */
public class QuadroAleatorio extends QuadroPalavras{
	
	public static final String nome = "Aleatorio";
    String[] palavras = {"ceu", "cadeiras", "samba", "corda", "casa"};
    private List<Button> ceu = new ArrayList<>();
    private List<Button> cadeiras = new ArrayList<>();
    private List<Button> samba = new ArrayList<>();
    private List<Button> corda = new ArrayList<>();
    private List<Button> casa = new ArrayList<>();
            
    public QuadroAleatorio(){
        quadro =  new String[][]{{"J", "A", "D", "C", "O", "R", "D", "A", "X", "Y"},
        						 {"C", "A", "S", "A", "U", "G", "V", "L", "Á", "R"},
        						 {"E", "H", "S", "A", "M", "B", "A", "G", "I", "G"},
        						 {"U", "C", "U", "U", "N", "L", "Q", "K", "E", "I"},
        						 {"A", "P", "C", "A", "D", "E", "I", "R", "A", "S"}};
    }
    
    @Override
    public String[][] getQuadro() {
        return quadro;
    }

    @Override
    public String[] getPalavras() {
        return palavras;
    }
    
    public List<Button> getCeu() {
		return ceu;
	}
    
    public List<Button> getCadeiras() {
		return cadeiras;
	}
    
    public List<Button> getSamba() {
		return samba;
	}
    
    public List<Button> getCorda() {
		return corda;
	}
    
    public List<Button> getCasa() {
		return casa;
	}
    
    @Override
    public void pegarPosicaoBotao(Button[][] buttons){
    	//ceu
    	ceu.add(buttons[1][0]);
    	ceu.add(buttons[2][0]);
    	ceu.add(buttons[3][0]);
    	//cadeiras
    	cadeiras.add(buttons[4][2]);
    	cadeiras.add(buttons[4][3]);
    	cadeiras.add(buttons[4][4]);
    	cadeiras.add(buttons[4][5]);
    	cadeiras.add(buttons[4][6]);
    	cadeiras.add(buttons[4][7]);
    	cadeiras.add(buttons[4][8]);
    	cadeiras.add(buttons[4][9]);
    	//samba
    	samba.add(buttons[2][2]);
    	samba.add(buttons[2][3]);
    	samba.add(buttons[2][4]);
    	samba.add(buttons[2][5]);
    	samba.add(buttons[2][6]);
    	//corda
    	corda.add(buttons[0][3]);
    	corda.add(buttons[0][4]);
    	corda.add(buttons[0][5]);
    	corda.add(buttons[0][6]);
    	corda.add(buttons[0][7]);
    	//casa
    	casa.add(buttons[1][0]);
    	casa.add(buttons[1][1]);
    	casa.add(buttons[1][2]);
    	casa.add(buttons[1][3]);
    	
    }

	@Override
	public List<Button> verificaBotoes(String palavra) {
		if (palavra.equalsIgnoreCase("ceu"))
			return ceu;
		else if (palavra.equalsIgnoreCase("cadeiras"))
			return cadeiras;
		else if (palavra.equalsIgnoreCase("samba"))
			return samba;
		else if (palavra.equalsIgnoreCase("corda"))
			return corda;
		else
			return casa;
	}
        
}
