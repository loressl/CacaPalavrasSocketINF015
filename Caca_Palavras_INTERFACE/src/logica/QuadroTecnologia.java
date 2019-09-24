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
public class QuadroTecnologia extends QuadroPalavras {

	public static final String nome = "Tecnologia";
    String[] palavras = {"intel", "memoria", "linux", "socket", "pixel"};
    private List<Button> intel = new ArrayList<>();
    private List<Button> memoria = new ArrayList<>();
    private List<Button> linux = new ArrayList<>();
    private List<Button> socket = new ArrayList<>();
    private List<Button> pixel = new ArrayList<>();
    
    public QuadroTecnologia() {
        quadro =  new String[][]{{"P", "T", "Z", "U", "I", "N", "T", "E", "L", "L"}, 
        						 {"I", "A", "N", "A", "U", "G", "N", "O", "T", "I"},
        						 {"X", "S", "O", "C", "K", "E", "T", "L", "W", "N"},
        						 {"E", "C", "D", "I", "Y", "U", "P", "T", "A", "U"}, 
        						 {"L", "A", "M", "E", "M", "O", "R", "I", "A", "X"}};
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
		//intel
		intel.add(buttons[0][4]);
		intel.add(buttons[0][5]);
		intel.add(buttons[0][6]);
		intel.add(buttons[0][7]);
		intel.add(buttons[0][8]);
		//memoria
		memoria.add(buttons[4][2]);
		memoria.add(buttons[4][3]);
		memoria.add(buttons[4][4]);
		memoria.add(buttons[4][5]);
		memoria.add(buttons[4][6]);
		memoria.add(buttons[4][7]);
		memoria.add(buttons[4][8]);
		//linux
		linux.add(buttons[0][9]);
		linux.add(buttons[1][9]);
		linux.add(buttons[2][9]);
		linux.add(buttons[3][9]);
		linux.add(buttons[4][9]);
		//socket
		socket.add(buttons[2][1]);
		socket.add(buttons[2][2]);
		socket.add(buttons[2][3]);
		socket.add(buttons[2][4]);
		socket.add(buttons[2][5]);
		socket.add(buttons[2][6]);
		//pixel
		pixel.add(buttons[0][0]);
		pixel.add(buttons[1][0]);
		pixel.add(buttons[2][0]);
		pixel.add(buttons[3][0]);
		pixel.add(buttons[4][0]);
		
	}

	@Override
	public List<Button> verificaBotoes(String palavra) {
		if (palavra.equalsIgnoreCase("intel"))
			return intel;
		else if (palavra.equalsIgnoreCase("memoria"))
			return memoria;
		else if (palavra.equalsIgnoreCase("linux"))
			return linux;
		else if (palavra.equalsIgnoreCase("socket"))
			return socket;
		else
			return pixel;
	}
    
}
