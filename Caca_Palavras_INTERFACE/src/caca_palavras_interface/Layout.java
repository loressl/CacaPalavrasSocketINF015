package caca_palavras_interface;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import interfaces.QuadroPalavras;
import javafx.geometry.Insets;
import logica.QuadroAleatorio;
import logica.QuadroMusica;
import logica.QuadroTecnologia;
import logica.Quadroesporte;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

public class Layout extends JFrame implements ActionListener {

    private JPanel contentPane;
    List<String> comboItens = new ArrayList<>();
    Quadroesporte teste = new Quadroesporte();
    QuadroPalavras quadroPalavras;
    int linha = teste.getQuadro().length;
    int coluna = teste.getQuadro()[0].length;
    Button[][] buttons = new Button[linha][coluna];
    JTextArea textArea = new JTextArea();
    String palavra = "";
    String itemSelectedCombox = null;
    int acertos = 0;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Layout frame = new Layout();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Layout() {
        getContentPane().setBackground(new Color(240, 255, 240));
        getContentPane().setEnabled(false);
        setTitle("Ca\u00E7a-Palavras");
        setBounds(100, 100, 500, 400);
        getContentPane().setLayout(null);

        JLabel lblAcertos = new JLabel("Acertos");
        lblAcertos.setForeground(new Color(46, 139, 87));
        lblAcertos.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        lblAcertos.setBounds(363, 96, 61, 22);
        getContentPane().add(lblAcertos);

        JLabel lblNewLabel = new JLabel("Escolha abaixo o tema do Ca\u00E7a Palavras ou \\\"SAIR\\\" para encerrar o jogo");
        lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
        lblNewLabel.setBounds(10, 31, 460, 29);
        getContentPane().add(lblNewLabel);

        JComboBox comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"Selecione opção", "Aleat\u00F3rio", "Esporte", "Tecnologia", "M\u00FAsica", "SAIR"}));
        comboBox.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
        comboBox.setBackground(new Color(245, 245, 220));
        comboBox.setBounds(10, 59, 141, 20);
        getContentPane().add(comboBox);
        comboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                itemSelectedCombox = (String) comboBox.getSelectedItem();
                if (itemSelectedCombox.equalsIgnoreCase("Esporte")) {
                    limparTela();
                    quadroPalavras = new Quadroesporte();
                    instaciarButtons();
                } else if (itemSelectedCombox.equalsIgnoreCase("Aleatório")) {
                    limparTela();
                    quadroPalavras = new QuadroAleatorio();
                    instaciarButtons();
                } else if (itemSelectedCombox.equalsIgnoreCase("Música")) {
                    limparTela();
                    quadroPalavras = new QuadroMusica();
                    instaciarButtons();
                } else if (itemSelectedCombox.equalsIgnoreCase("Tecnologia")) {
                    limparTela();
                    quadroPalavras = new QuadroTecnologia();
                    instaciarButtons();
                } else if (itemSelectedCombox.equalsIgnoreCase("SAIR")) {
                    System.out.println("SAIR");

                } else {
                    limparTela();
                    String tString = textArea.getText();
                    if (textArea.getText() != null) {
                        textArea.setText("");
                    }
                }
            }
        });

        textArea = new JTextArea();
        textArea.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
        textArea.setBackground(new Color(255, 239, 213));
        textArea.setEditable(false);
        textArea.setBounds(363, 129, 107, 124);
        getContentPane().add(textArea);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, 484, 21);
        getContentPane().add(menuBar);

    }

    public void setQuadroPalavras(QuadroPalavras quadroPalavras) {
        this.quadroPalavras = quadroPalavras;
    }

    public QuadroPalavras getQuadroPalavras() {
        return quadroPalavras;
    }

    public void instaciarButtons() {
        linha = quadroPalavras.getQuadro().length;
        coluna = quadroPalavras.getQuadro()[0].length;
        buttons = new Button[linha][coluna];
        preencherButtons();
        quadroPalavras.pegarPosicaoBotao(buttons);
    }

    public void preencherButtons() {
        int alt = 100;
        for (int i = 0; i < linha; i++) {
            int dist = 30;
            for (int j = 0; j < coluna; j++) {
                buttons[i][j] = new Button();
                buttons[i][j].setBounds(dist, alt, 50, 50);
                buttons[i][j].setFont(new Font("Ravie", 1, 12));
                buttons[i][j].setLabel(quadroPalavras.getQuadro()[i][j]);;
                buttons[i][j].addActionListener(this);
                getContentPane().add(buttons[i][j]);
                dist += 30;
            }
            alt += 40;
        }
    }

    public void limparTela() {
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                if (buttons[i][j] != null) {
                    buttons[i][j].removeNotify();
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        contadorCliques(e.getActionCommand());
    }

    public void contadorCliques(String letra) {
        palavra = palavra + letra;
        for (int i = 0; i < quadroPalavras.getPalavras().length; i++) {
            if (palavra.equalsIgnoreCase(quadroPalavras.getPalavras()[i])) {
                textArea.append(quadroPalavras.getPalavras()[i]);
                textArea.append("\n");
                pintarBotoes();
                acertos++;
                palavra = "";
                break;
            }
            if (palavra.length() == 2) {
                boolean verifica = false;
                for (int j = 0; j < quadroPalavras.getPalavras().length; j++) {
                    if (!String.valueOf(palavra.charAt(0))
                            .equalsIgnoreCase(String.valueOf(quadroPalavras.getPalavras()[j].charAt(0)))
                            || !String.valueOf(palavra.charAt(1))
                                    .equalsIgnoreCase(String.valueOf(quadroPalavras.getPalavras()[j].charAt(1)))) {
                        verifica = true;
                    } else {
                        verifica = false;
                        break;
                    }
                }
                if (verifica) {
                    palavra = "";
                }
            }
        }

        if (acertos == quadroPalavras.getPalavras().length) {
            JOptionPane.showMessageDialog(this, "PARAB�NS!!Voc� encontrou todas as palavras.");
        }
    }

    public void pintarBotoes() {
        List<Button> button_escolhidos = quadroPalavras.verificaBotoes(palavra);
        for (int i = 0; i < button_escolhidos.size(); i++) {
            button_escolhidos.get(i).setBackground(Color.orange);
//            button_escolhidos.get(i).setEnabled(false);
        }
    }
}
