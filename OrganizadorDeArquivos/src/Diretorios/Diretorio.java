package Diretorios;

public abstract class Diretorio implements InterfaceDiretorios {
	
	//Variáveis, getters, setters e costrutores da classe
	private String dir;
	public Diretorio(String dir){
		this.dir = dir;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	
	
}
