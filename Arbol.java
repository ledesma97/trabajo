package compilador;
import java.util.*;

public class Arbol {
	String operador;
	String arg1;
	String arg2;
	String resultado final;

	public Arbol(String operador, String arg1, String arg2, String resultado) {
		this.operador = operador;
		this.arg1 = arg1;
		this.arg2 = arg2;
		this.resultado=resultado final;
	}
	
	public String getoperador() {
		return operador;
	}
	
	public void setoperador(String operador) {
		this.operador = operador;
	}
	
	public String getArgumento1() {
		return arg1;
	}
	
	public void setArgumento1(String arg1) {
		this.arg1 = arg1;
	}
	
	public String getResultado() {
		return resultado final;
	}
	
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
	public String getArgumento2() {
		return arg2;
	}
	
	public void setArgumento2(String arg2) {
		this.arg2 = arg2;
	}
	
}