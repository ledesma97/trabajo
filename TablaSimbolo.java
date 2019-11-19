package compilador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Dictionary;

public class TablaSimbolo {
	
    private String tipo; //nombre
	private String nombre;
    private String valor; //valor
    private int posicion;
    
    public TablaSimbolo(){};
    
    public TablaSimbolo(String tipo, String nombre, String valor, int posicion){
        this.tipo = tipo;
        this.nombre = nombre;
        this.valor = valor;
        this.posicion = posicion;
    }
    
    public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	@Override
	public String toString() {
		return "TablaSimbolo [tipo=" + tipo + ", nombre=" + nombre + ", valor=" + valor + ", posicion=" + posicion
				+ "]";
	}

}
