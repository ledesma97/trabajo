package compilador;


public class Identifica {

	@Override
	public String toString() {
		return "Identificador [nombre=" + nombre + ", valor=" + valor + ", tipo=" + tipo + ", posicion=" + posicion + "]";
	}
	
	String nombre;
	String valor;
	String tipo;
	String alcance;
	int posicion;
	
	public Identificador(String nombre, String valor, String tipo, String alcance, int posicion) {
		this.nombre = nombre;
		this.valor = valor;
		this.tipo = tipo;
		this.alcance = alcance;
		this.posicion = posicion;
	}
	
	public int getPosicion() {
		return posicion;
	}
	
	public void setPosicion(int posicion) {
		this.columna = columna;
	}
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public String getAlcance() {
		return alcance;
	}
	
	public void setAlcance(String alcance) {
		this.alcance = alcance;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
}