package Diretorios;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class Imagens extends Diretorio{
	//Variáveis, getters, setters e costrutores da classe
	private ArrayList <File> image = new ArrayList<File>();
	
	public Imagens(String dir){
		super(dir);
		AbrirDiretorio();
		pastaImage();
	}
	
	public ArrayList<File> getImage() {
		return image;
	}
	
	public void setImage(ArrayList<File> image) {
		this.image= image;
	}
	

	@Override
	public void AbrirDiretorio(){
		 FilenameFilter filtro = new FilenameFilter() {
			   
	            @Override
	            public boolean accept(File dir, String name) {
	                  if(name.contains(".png") || name.contains(".jpg") || name.contains(".jpeg")|| name.contains(".jpe") || name.contains(".gif")
	                		 || name.contains(".bpm") || name.contains(".psd") || name.contains(".raw") || name.contains(".JPG")){
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
						image.add(aux[i]);
				}
				
			}
		// se não for um diretorio aparecerá o erro	
		}catch(Exception err){
			System.err.println("Erro: "+err);
		}	
	
	}
	

	
	@Override
	public void CriarDiretorio(){
		pastaImage();
	}
	
	public void pastaImage(){
		int i;
		File pastaImage;
		try{
			if (!image.isEmpty()){
				pastaImage = new File(getDir()+"\\Imagens");
				pastaImage.mkdir();
				for(i=0; i<image.size(); i++){
					//Move os arquivos de imagens para o  diretorio criado de imagens 
					image.get(i).renameTo(new File(pastaImage,image.get(i).getName()));
				}
				
			}
		}catch(Exception err){
			System.err.println("Erro: "+err);
		}
	}
	
	//Verifica a existencia de arquivos de imagens na arraylist
	@Override
	public boolean VerificaArquivos(){
		if (!image.isEmpty()){
			return true;
		}
		return false;
	}
}
