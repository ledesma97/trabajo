package compilador;

public class valor {
	public String nombreVariable;
	public int posicion;
	public Alcance(String nombreVariable, int posicion) {
		super();
		this.nombreVariable = nombreVariable;
		this.posicion = columna;
	}
	public String getNombreVariable() {
		return nombreVariable;
	}
	public void setNombreVariable(String nombreVariable) {
		this.nombreVariable = nombreVariable;
	}
	public int getPosicion() {
		return posicion;
	}
	public void setPosicion(int posicion) {
		this.posicion = columna;
	}
	@Override
	public String toString() {
		return "valor [nombreVariable=" + nombreVariable + ", columnq=" + posicion + "]";
	}
	
}
