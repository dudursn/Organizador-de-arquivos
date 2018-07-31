package Interfacegrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import Dados.Dados;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.towel.swing.img.JImagePanel;


public class JanelaDiretorios extends JFrame implements ActionListener {
	//Declara objetos que fazem parte da janela
	private JFrame Diretorios;
	
	private Container caixa;
	private BorderLayout layout;
	private JButton item4;
	private JLabel titulo, resultado;
	private JImagePanel background_imagem;
	
	public JanelaDiretorios(){
		//Cria um Jframe
		Diretorios = new JFrame();
		Diretorios.setTitle("últimos diretórios"); 
		Diretorios.setSize(500,500);
		Diretorios.setResizable(false);
		Diretorios.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//Cria um container com o tamanho da janela da JFrame
		caixa = Diretorios.getContentPane();
		//Define o layout do container
		layout = new BorderLayout(10,10);
		caixa.setLayout(layout);
		
		//Inicializa os componentes
		inicializarComponetesDiretorios();
		
		
		
		Diretorios.setVisible(true); 
	}
	public void inicializarComponetesDiretorios(){
	
		try{
			background_imagem = new JImagePanel(ImageIO.read(new File("bg.jpg")));
			setPreferredSize(new Dimension(500, 500));
			pack();
		} catch(IOException ex){
			ex.printStackTrace();
		}
		
		item4 = new JButton("Voltar");
		item4.addActionListener(this);
		
		caixa.add(item4, BorderLayout.SOUTH);
		//caixa.add(background_imagem);
		lastDiretorios();
		
		
	}
	
	public void lastDiretorios(){
		
	int i = Dados.diretorios.size(), j, k = 1;
	titulo = new JLabel("últimos diretórios organizados:");
	
	String resultadoTotal = "";
	StringBuilder strBuilder = new StringBuilder(resultadoTotal);
	//Cria um buffer pra salvar a concatenação de todas as strings do ArrayList
	
	if (!Dados.diretorios.isEmpty()){
		if (i>5){
			j = 5;
		}else{
			j = i;
		}
		strBuilder.append("<html>"); //Inicializa o html para colocar quebra de linha entre cada string
		while (j >0){
			strBuilder.append("<br> <i>" + k + " - " + Dados.diretorios.get(i-1) + "</i></br>"); //Concatena a string, colocando uma quebra de linha no fim
			k++;
			i--;
			j--;
		}
		strBuilder.append("</html>"); //Encerra o uso do html
		resultado = new JLabel(strBuilder.toString()); //Guarda todas strings concatenadas e divididas no rótulo para guardar String
	}else{
		resultado = new JLabel("Nenhum diretório foi organizado");
	}

	titulo.setFont(new Font("Times New Roman", 0, 30));
	resultado.setFont(new Font("Times New Roman", 0, 18));
	
	caixa.add(resultado, BorderLayout.CENTER);
	caixa.add(titulo, BorderLayout.NORTH);
	
	
}
	@Override
	public void actionPerformed(ActionEvent e){
		if (e.getSource()==item4){
			//Fecha a janela e volta para a parte principal
			Diretorios.dispose();
		}
	}
    
}
