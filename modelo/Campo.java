package modelo;

import java.util.ArrayList;
import java.util.List;

public class Campo {
	
	private  final int LINHA;
	private final int COLUNA;
	
	private boolean aberto = false;
	private boolean minado = false;
	private boolean marcado = false;
	
	private List<Campo> vizinhos = new ArrayList<>();
	
	Campo(int linha, int coluna){
		this.LINHA = linha;
		this.COLUNA = coluna;
	}
	
	public int getLinha() {
		return this.LINHA;
	}
	
	public int getColuna() {
		return this.COLUNA;
	}
	
	void setAberto(boolean aberto){
		this.aberto = aberto;
	}
	
	public boolean isAberto() {
		return this.aberto;
	}
	
	public boolean isFechado() {
		return !this.aberto;
	}
	
	public boolean isMarcado() {
		return this.marcado;
	}
	
	public boolean isMinado() {
		return this.minado;
	}
	
	void minar(){
		minado = true;
	}
	boolean adicionarVizinho(Campo vizinho) {
		
		boolean linhaDiferente = this.LINHA != vizinho.LINHA;
		boolean colunaDiferente = this.COLUNA != vizinho.COLUNA;
		boolean diagonal = linhaDiferente && colunaDiferente;
		
		int deltaLinha = Math.abs(LINHA - vizinho.LINHA);
		int deltaColuna = Math.abs(COLUNA - vizinho.COLUNA);
		int deltaGeral = deltaLinha + deltaColuna;
		
		if(deltaGeral == 1 && !diagonal) {
			vizinhos.add(vizinho);
			return true;
		}
		
		else if(deltaGeral == 2 && diagonal) {
			vizinhos.add(vizinho);
			return true;
		}
		
		else {
			return false;
		}
		
	}
	void alternarMarcacao() {
		if(!aberto) {
			marcado = !marcado;
		}
	}
	
	boolean abrir() {
		if(!aberto && !marcado) {
			aberto = true;
			
			if(minado) {
				throw new RuntimeException();
			}
			
			if(vizinhancaSegura()) {
				vizinhos.forEach(v -> v.abrir());
			}
			
			return true;
		}
		else {
			return false;
		}
	}
	
	boolean vizinhancaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}
	
	boolean objetivoAlcancado() {
		
		boolean desvendado = aberto && !minado;
		boolean protegido = minado && marcado;
		return desvendado || protegido;
	}
	
	long minasNaVizinhanca() {
		return vizinhos.stream().filter(v -> v.isMinado()).count();
	}
	
	void reiniciar() {	
		minado = false;
		aberto = false;
		marcado = false;
	}
	
	public String toString() {
		
		if(marcado) {
			return "x";
		}
		
		else if(aberto && minado) {
			return "*";
		}
		
		else if(aberto && minasNaVizinhanca() > 0) {
			return Long.toString(minasNaVizinhanca());
		}
		
		else if(aberto) {
			return " ";
		}
		
		else {
			return "?";
		}
	}
}
