package Dados;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Dados {

		//Variáveis, getters e setters da classe
		public static ArrayList<String> diretorios = new ArrayList();
		static String caminhoArquivo;
		
		public ArrayList<String> getDiretorios() {
			return diretorios;
		}
	
		public void setDiretorios(ArrayList<String> diretorios) {
			this.diretorios = diretorios;
		}
		
	
		public Dados(String caminhoArquivo){
			this.caminhoArquivo = caminhoArquivo;
		}
		
		public static void addDiretorio(String diretorio){
			diretorios.add(diretorio);
		}

		
		//Lê do arquivo que contém os diretórios organizados e jogano Array somente os diretórios
		public static void lerRelatorio(){
		
			  try {
			      FileReader arq = new FileReader(caminhoArquivo);
			      BufferedReader lerArq = new BufferedReader(arq);
			 
			      String linha = lerArq.readLine(); 

			      while (linha != null) {
			    	  int index = linha.indexOf("C");
			    	  if (index!= -1){
				    	  linha = linha.substring(index);
				    	  diretorios.add(linha);
			    	  }
			        
			        linha = lerArq.readLine(); 
			      }
			 
			      arq.close();
			    } catch (IOException e) {
			        System.err.printf("Erro na abertura do arquivo: " + e.getMessage());
			    }
		}
		
		//Joga no arquivo a relação de todas as pastas organizas que estão dentro do Array de diretórios
		public static void gravarRelatorio(){
			
			int i;
			
			try {
				FileWriter arq = new FileWriter(caminhoArquivo);
				PrintWriter gravarArq = new PrintWriter(arq);
				
				if (!diretorios.isEmpty()){
					for (i=0; i<diretorios.size(); i++){
						gravarArq.println("Diretório organizado: " + diretorios.get(i));
					}
				    arq.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
}
