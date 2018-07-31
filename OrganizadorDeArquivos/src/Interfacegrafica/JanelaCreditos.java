package Interfacegrafica;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.towel.swing.img.JImagePanel;


public class JanelaCreditos extends JFrame implements ActionListener {
	//Declara objetos que fazem parte da janela
	private JFrame Credit;
	private Container caixa;
	private BorderLayout layout;
	private JButton item4;
	private JLabel titulo, nome1, nome2, nome3;
	private JImagePanel background_imagem;
	
	public JanelaCreditos(){
		//Cria um Jframe
		Credit = new JFrame();
		Credit.setTitle("Créditos"); 
		Credit.setSize(500,500);
		Credit.setResizable(false);
		Credit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//Cria um container com o tamanho da janela da JFrame
		caixa = Credit.getContentPane();
		//Define o layout do container
		layout = new BorderLayout(10,10);
		caixa.setLayout(layout);
		
		//Inicializa os componentes
		inicializarComponetes();
		
		
		
		Credit.setVisible(true); 
	}
	public void inicializarComponetes(){
	
			
		try{
			background_imagem = new JImagePanel(ImageIO.read(new File("tela_creditos.jpg")));
			setPreferredSize(new Dimension(500, 500));
			pack();
		} catch(IOException ex){
			ex.printStackTrace();
		}
		
		item4 = new JButton("Voltar");
		item4.addActionListener(this);
		
		caixa.add(item4, BorderLayout.SOUTH);
		caixa.add(background_imagem);
		
	}
	
		
		

	@Override
	public void actionPerformed(ActionEvent e){
		if (e.getSource()==item4){
			//Fecha a janela e volta para a parte principal
			Credit.dispose();
		}
	}
    
}
