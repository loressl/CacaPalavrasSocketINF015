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
public class QuadroMusica extends QuadroPalavras {

	public static final String nome = "Musica";
    String[] palavras = {"cordas", "violao", "baixo", "nota", "som"};
    private List<Button> cordas = new ArrayList<>();
    private List<Button> violao = new ArrayList<>();
    private List<Button> baixo = new ArrayList<>();
    private List<Button> nota = new ArrayList<>();
    private List<Button> som = new ArrayList<>();
    
    public QuadroMusica() {
        quadro =  new String[][]{{"C", "T", "Z", "C", "O", "R", "D", "A", "S", "B"}, 
        						 {"S", "A", "N", "A", "U", "G", "N", "O", "T", "A"},
        						 {"O", "H", "B", "A", "X", "U", "I", "G", "I", "I"},
        						 {"M", "C", "U", "U", "N", "B", "Q", "A", "E", "X"},
        						 {"O", "A", "E", "X", "V", "I", "O", "L", "A", "O"}};
    }

    @Override
    public String[][] getQuadro() {
        return quadro;
    }

    @Override
    public String[] getPalavras() {
        return palavras;
    }

	@Override
	public void pegarPosicaoBotao(Button[][] buttons) {
		//cordas
		cordas.add(buttons[0][3]);
		cordas.add(buttons[0][4]);
		cordas.add(buttons[0][5]);
		cordas.add(buttons[0][6]);
		cordas.add(buttons[0][7]);
		cordas.add(buttons[0][8]);
		//violao
		violao.add(buttons[4][4]);
		violao.add(buttons[4][5]);
		violao.add(buttons[4][6]);
		violao.add(buttons[4][7]);
		violao.add(buttons[4][8]);
		violao.add(buttons[4][9]);
		//baixo
		baixo.add(buttons[0][9]);
		baixo.add(buttons[1][9]);
		baixo.add(buttons[2][9]);
		baixo.add(buttons[3][9]);
		baixo.add(buttons[4][9]);
		//nota
		nota.add(buttons[1][6]);
		nota.add(buttons[1][7]);
		nota.add(buttons[1][8]);
		nota.add(buttons[1][9]);
		//som
		som.add(buttons[1][0]);
		som.add(buttons[2][0]);
		som.add(buttons[3][0]);
		
	}

	@Override
	public List<Button> verificaBotoes(String palavra) {
		if (palavra.equalsIgnoreCase("cordas"))
			return cordas;
		else if (palavra.equalsIgnoreCase("violao"))
			return violao;
		else if (palavra.equalsIgnoreCase("baixo"))
			return baixo;
		else if (palavra.equalsIgnoreCase("nota"))
			return nota;
		else
			return som;
	}
    
    
}
