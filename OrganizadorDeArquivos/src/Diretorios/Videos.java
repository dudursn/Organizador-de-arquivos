package Diretorios;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class Videos extends Diretorio{
	private ArrayList<File> videos = new ArrayList <File>();

	public Videos(String dir) { 
		super(dir);
		AbrirDiretorio();
		CriarDiretorio();
	}
	
	public ArrayList<File> getVideos() {
		return videos;
	}
	
	public void setVideos(ArrayList<File> videos) {
		this.videos = videos;
	}
	@Override
	public void AbrirDiretorio(){
		File dir = new File(getDir());
		FilenameFilter filtro = new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				if (name.contains(".mp4") || name.contains(".mkv") || name.contains(".avi") || name.contains(".3gp") || name.contains(".flv")  || name.contains(".rmvb") || name.contains(".mov")){
					return true;
				}
				return false;
			}
		};
		File aux[];
		int i;
		try{
			if (dir.isDirectory()){
				aux = dir.listFiles(filtro);
				for(i=0; i<aux.length; i++){
					videos.add(aux[i]);
				}
			}
			
		}catch(Exception err){
			System.err.println("Erro: "+err);
		}
	}
	@Override
	public void CriarDiretorio(){
		pastaVideos();
		
	}
	
	//Cria a pasta Videos
	public void pastaVideos(){
		int i;
		File pastaVideo;
		try{
			if (!videos.isEmpty()){
				pastaVideo = new File(getDir() + "\\Videos");
				pastaVideo.mkdir();
				for(i=0; i< videos.size(); i++){
					//Move os arquivos de videos para o  diretorio criado de Videos
					videos.get(i).renameTo(new File(pastaVideo, videos.get(i).getName()));
				}
				
			}
		}catch(Exception err){
			System.err.println("Erro: "+err);
		}
	
	}
	

	
	//Verifica a existencia de arquivos de videos na arraylist
	@Override
	public boolean VerificaArquivos(){
		if (!videos.isEmpty()){
			return true;
		}
		return false;
	}
	
}
//