package Diretorios;

import java.io.File;
import java.util.ArrayList;
import java.io.FilenameFilter;


public class Documentos extends Diretorio{
	//Variáveis, getters, setters e costrutores da classe
	private ArrayList<File> documents = new ArrayList<File>();
	
	public Documentos(String dir) { 
		super(dir);
		AbrirDiretorio();
		CriarDiretorio();
		
	}

	
	@Override
	public void AbrirDiretorio() {
		FilenameFilter filtro = new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				if (name.contains(".txt") || name.contains(".doc") || name.contains(".pdf") || name.contains(".docx")|| name.contains(".xls") || name.contains(".xlsx") || name.contains(".ppt") || name.contains(".pps")){
					return true;
				}
				return false;
			}
		};
		File aux[];
		int i;
		File dir = new File (getDir());
		try{
			if (dir.isDirectory()){
				aux = dir.listFiles(filtro);
				for (i=0; i<aux.length; i++){
					documents.add(aux[i]);
				}
			}
		}catch(Exception err){
			System.err.println("Erro: "+err);
		}
	}
	
	@Override
	public void CriarDiretorio(){
		pastaDocuments();
		
	}
	//Cria a pasta documentos
	public void pastaDocuments(){
		int i;
		File pastaDocuments;
		try{
			if (!documents.isEmpty()){
				pastaDocuments = new File(getDir()+ "\\Documentos");
				pastaDocuments.mkdir();
				//Percorre a ArrayList que contém os documentos e coloca na pasta
				for (i=0; i<documents.size(); i++){
					//Move os arquivos de documentos para o  diretorio criado de documentos 
					documents.get(i).renameTo(new File(pastaDocuments,documents.get(i).getName()));
				}
				
			}
		}catch(Exception err){
			System.err.println("Erro: "+err);
		}
		
	}
	
	//Verifica a existencia de arquivos de documentos na arraylist
	@Override
	public boolean VerificaArquivos(){
		if (!documents.isEmpty()){
			return true;
		}
		return false;
	}

	public ArrayList<File> getDocuments() {
		return documents;
	}

	public void setDocuments(ArrayList<File> documents) {
		this.documents = documents;
	}

	
	
	
}
