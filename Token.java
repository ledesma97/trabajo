package analizadorLyS;

public class Token {	
	private String valor;
	private int columna;
	private int renglon;
	
	public Token(String valor, int renglon, int columna) {
		this.valor=valor;
		this.renglon=renglon;
		this.columna=columna;
	}
	public int getColumna() {
		return columna;
	}
	public String getValor() {
		return valor;
	}
	public int getRenglon() {
		return renglon;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
