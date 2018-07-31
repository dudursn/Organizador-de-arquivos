package Interfacegrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.towel.swing.img.JImagePanel;

import Dados.Dados;
import Diretorios.Documentos;
import Diretorios.Imagens;
import Diretorios.Musicas;
import Diretorios.Outros;
import Diretorios.Videos;




public class Menu extends JFrame implements ActionListener {
	
	//Seta objetos e variáveis que fazem parte do menu principal
	private JMenuBar menuPrincipal = new JMenuBar();
	private JMenuItem item1 = new JMenuItem("Créditos");
	private JMenuItem item2 = new JMenuItem("Sair");
	private JMenuItem item3 = new JMenuItem("Selecionar Pasta");
	private JMenuItem item4 = new JMenuItem("Listar últimos Diretórios");
	private JMenu menu1 = new JMenu("Arquivo");
	private JMenu menu2 = new JMenu("Opções");
	private JFileChooser escolherPasta = new JFileChooser();
	private JImagePanel painel_imagem;
	private Dados dados = new Dados("dados\\relatorio.txt");
	
	public Menu() {
		Dados.lerRelatorio(); //Lê relatório já existente, adicionando dados ao programa
		criarJanela(); //Cria janela principal
		criarMenu(); //Cria menu superior

			
	}
	
	public void criarJanela(){
		setTitle("Organizador de Arquivos");
		setVisible(true);
		setSize(500,500);
		
		//Impedir a maximização da tela
		setResizable(false);
		
		//Abrir a aplicação no centro da tela
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocale(null);
		
		
	}
	

	public void criarMenu(){
		// coloca a cor no JMenuBar
		menuPrincipal.setBackground(Color.yellow);
		setJMenuBar(menuPrincipal);
		
		menuPrincipal.add(menu1);
		menuPrincipal.add(menu2);
		
		//Mensagem para dicas
		menu1.setToolTipText("Clique aqui para selecionar uma pasta");
		menu2.setToolTipText("Clique aqui para outras opções");
		
		menu1.add(item3);
		item3.addActionListener(this);
	
		menu2.add(item4);
		item4.addActionListener(this);
		
		menu2.add(item1);
		item2.addActionListener(this);
		
		menu2.add(item2);
		item1.addActionListener(this);
		
		//Coloca a imagem como fundo de tela
		try{
			painel_imagem = new JImagePanel(ImageIO.read(new File("imgLogo.jpg")));
			setPreferredSize(new Dimension(500, 500));
			add(painel_imagem);
			pack();
		} catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	public String abrirPasta(){
		escolherPasta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); //Mostra somente pastas na caixa de seleção
	
		//Verifica se a pasta existe e retorna o caminho dela
		int caixa = escolherPasta.showOpenDialog(null);
		if (caixa==JFileChooser.APPROVE_OPTION){
			String caminhoDaPasta = escolherPasta.getSelectedFile().getAbsolutePath();
			File f = new File(caminhoDaPasta);
			if (f.exists()){
				return caminhoDaPasta;
			}else{
				return null;
			}
		}
		return null;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean state = false;
		if (e.getSource()==item4){
			new JanelaDiretorios();
		}else if (e.getSource()==item3){
			
			String abriu = abrirPasta();
			if (abriu!=null){
				Outros other = new Outros (abriu);
				Videos videos = new Videos(abriu);
				Documentos documents = new Documentos(abriu);
				Musicas music = new Musicas (abriu); 
				Imagens image = new Imagens (abriu);
				
				//Verifica se os arquivos foram organizados
				state = (other.VerificaArquivos() || videos.VerificaArquivos() || documents.VerificaArquivos() || music.VerificaArquivos() || image.VerificaArquivos());
				if(state == true){
					JOptionPane.showMessageDialog( this, String.format("Diretório organizado com sucesso."));
					Dados.addDiretorio(abriu);
					Dados.gravarRelatorio();
				} else{
					JOptionPane.showMessageDialog( this, String.format("Erro: Não há arquivos nesse diretório"));
				}
			}
		}else if(e.getSource()==item1){
			//Abre uma nova janela com os créditos
			JanelaCreditos creditos = new JanelaCreditos();
			
		}else{
			System.exit(0);
			
			
		}
		
		
	}
	
	

	
	
}
