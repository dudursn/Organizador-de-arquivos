package Diretorios;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class Outros extends Diretorio{
	//Variáveis, getters, setters e costrutores da classe
	private ArrayList <File> other = new ArrayList<File>();
	
	public Outros(String dir){
		super(dir);
		AbrirDiretorio();
		CriarDiretorio();
	}
	
	public ArrayList<File> getOther() {
		return other;
	}
	
	public void setOther(ArrayList<File> other) {
		this.other= other;
	}
	
	@Override
	public void AbrirDiretorio(){
		 FilenameFilter filtro = new FilenameFilter() {
			   
	            @Override
	            public boolean accept(File dir, String name) {
	            	
	            	File supostoDiretorio = new File(dir.getAbsolutePath() + "\\" + name);
	            	
	            	if (!supostoDiretorio.isDirectory()){
	                  if(!(name.contains(".png") || name.contains(".jpg") || name.contains(".jpeg")|| name.contains(".jpe") || name.contains(".gif") || name.contains(".bpm") || name.contains(".psd") || name.contains(".raw")
	                		  || name.contains(".mp3") || name.contains(".wmv") || name.contains(".ogg")|| name.contains(".aac") || name.contains(".flac")
	                		  || name.contains(".txt") || name.contains(".doc") || name.contains(".pdf") || name.contains(".docx")|| name.contains(".xls") || name.contains(".xlsx") || name.contains(".ppt") || name.contains(".pps")
	                		  || name.contains(".mp4") || name.contains(".mkv") || name.contains(".avi") || name.contains(".3gp") || name.contains(".flv")  || name.contains(".rmvb") || name.contains(".mov"))){
	                	  return true;  
	                  }
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
						other.add(aux[i]);
				}
				
			}
		// se não for um diretorio aparecerá o erro	
		}catch(Exception err){
			System.err.println("Erro: "+err);
		}	
	
	}
	
	
	
	@Override
	public void CriarDiretorio(){
		pastaOther();
		
	}
	
	//Cria a pasta Outros
	public void pastaOther(){
		int i;
		File pastaOther;
		try{
			if (!other.isEmpty()){
				pastaOther = new File(getDir()+"\\Outros");
				pastaOther.mkdir();
				//Percorre a ArrayList
				for(i=0; i<other.size(); i++){
					//Move os outros arquivos para o  diretorio criado Outros
					other.get(i).renameTo(new File(pastaOther,other.get(i).getName()));
				}
				
			}
		}catch(Exception err){
			System.err.println("Erro: "+err);
		}
	}
	
	//Verifica a existencia de outros arquivos na arraylist
	@Override
	public boolean VerificaArquivos(){
		if (!other.isEmpty()){
			return true;
		}
			return false;
	}
}
