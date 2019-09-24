/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.Button;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import interfaces.QuadroPalavras;

public class Quadroesporte extends QuadroPalavras{
	
    String[] palavras = {"futebol", "bola", "natacao", "remo", "gols"};
    public static final String nome = "Esporte";
    private List<Button> futebol = new ArrayList<>();
    private List<Button> bola = new ArrayList<>();
    private List<Button> natacao = new ArrayList<>();
    private List<Button> remo = new ArrayList<>();
    private List<Button> gols = new ArrayList<>();
    
    
    public Quadroesporte() {
        quadro = new String[][]{{"J","F","U","T", "E", "B", "O", "L","X", "A"},
                                {"B","A","N","A", "U", "G", "V", "L","A", "R"},
                                {"O","M","Y","G", "O", "L", "S", "W","I", "E"},
                                {"L","C","U","U", "N", "B", "Q", "A","E", "M"},
                                {"A","A","C","N", "A", "T", "A", "C","A", "O"}};
    }

    @Override
    public String[][] getQuadro() {
        return quadro;
    }

    @Override
    public String[] getPalavras() {
        return palavras;
    }

    public List<Button> getFutebol() {
        return futebol;
    }

    public List<Button> getBola() {
        return bola;
    }

    public List<Button> getRemo() {
        return remo;
    }

    public List<Button> getNatacao() {
        return natacao;
    }

    public List<Button> getGols() {
        return gols;
    }
    
    @Override
    public void pegarPosicaoBotao(Button[][] buttons){
        //futebol
        futebol.add(buttons[0][1]);
        futebol.add(buttons[0][2]);
        futebol.add(buttons[0][3]);
        futebol.add(buttons[0][4]);
        futebol.add(buttons[0][5]);
        futebol.add(buttons[0][6]);
        futebol.add(buttons[0][7]);
        //bola
        bola.add(buttons[1][0]);
        bola.add(buttons[2][0]);
        bola.add(buttons[3][0]);
        bola.add(buttons[4][0]);
        //natacao
        natacao.add(buttons[4][3]);
        natacao.add(buttons[4][4]);
        natacao.add(buttons[4][5]);
        natacao.add(buttons[4][6]);
        natacao.add(buttons[4][7]);
        natacao.add(buttons[4][8]);
        natacao.add(buttons[4][9]);
        //remo
        remo.add(buttons[1][9]);
        remo.add(buttons[2][9]);
        remo.add(buttons[3][9]);
        remo.add(buttons[4][9]);
        //gols
        gols.add(buttons[2][3]);
        gols.add(buttons[2][4]);
        gols.add(buttons[2][5]);
        gols.add(buttons[2][6]);
    }
    
	public List<Button> verificaBotoes(String palavra) {
		if (palavra.equalsIgnoreCase("Futebol"))
			return futebol;
		else if (palavra.equalsIgnoreCase("Bola"))
			return bola;
		else if (palavra.equalsIgnoreCase("Natacao"))
			return natacao;
		else if (palavra.equalsIgnoreCase("Remo"))
			return remo;
		else
			return gols;
	}
    
}
