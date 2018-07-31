package Diretorios;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class Musicas extends Diretorio{
	//Variáveis, getters, setters e costrutores da classe
	private ArrayList <File> music = new ArrayList<File>();
	
	public Musicas(String dir){
		super(dir);
		AbrirDiretorio();
		CriarDiretorio();
	}
	
	public ArrayList<File> getMusic() {
		return music;
	}
	
	public void setMusic(ArrayList<File> music) {
		this.music = music;
	}
	
	@Override
	public void AbrirDiretorio(){
		 FilenameFilter filtro = new FilenameFilter() {
			   
	            @Override
	            public boolean accept(File dir, String name) {
	                  if(name.contains(".mp3") || name.contains(".wmv") || name.contains(".ogg")|| name.contains(".aac") || name.contains(".flac")){
	                	  return true;  
	                  }    
	               
	               return false;
	            }
	         };
	
		File aux[];
		int i;
		File dir = new File(getDir());
		try{
			if (dir.isDirectory()){
				// recebe todos os arquivos filtrados do diretorio
				aux = dir.listFiles(filtro);
				// adiciona no array de diretorios
				for (i=0; i<aux.length; i++){
						music.add(aux[i]);
				}
				
			}
		// se não for um diretorio aparecerá o erro	
		}catch(Exception err){
			System.err.println("Erro: "+err);
		}	
	
	}
	

	
	@Override
	public void CriarDiretorio(){
		pastaMusic();
		
	}
	
	//cria a pasta Musicas
	public void pastaMusic(){
		int i;
		File pastaMusic;
		try{
			if (!music.isEmpty()){
				pastaMusic = new File(getDir()+"\\Músicas");
				pastaMusic.mkdir();
				for(i=0; i<music.size(); i++){
					//Move os arquivos de musicas para o  diretorio criado de músicas 
					music.get(i).renameTo(new File(pastaMusic,music.get(i).getName()));
				}
				
			}
		}catch(Exception err){
			System.err.println("Erro: "+err);
		}
	}
	
	//Verifica a existencia de arquivos de musicas na arraylist
	@Override
	public boolean VerificaArquivos(){
		if (!music.isEmpty()){
			return true;
		}
		return false;
	}
	
	
	
}
