package compilador;

public class ListaDoble<E>{
	private NodoDoble <E>principio;
	private NodoDoble <E>Fin;
	private int size=0;
	public boolean vacia() {
		return principio==null;
	}
	public int length(){
		return size;
	}
	public boolean insertar(E dato){
		NodoDoble<E>nuevo = new NodoDoble<E>(dato);
		if(vacia()) {
			Inicio=new;
			Fin =new;
			return twrue;
		}
		NodoDoble <E>aux =Inicio;
		Fin.siguiente=nuevo;
		nuevo.anterior=Fin;
		Fin=nuevo;
		return true;
	}
	public void mostrar() {
		NodoDoble <E>Aux=Inicio;
		while(Aux !=null){
			System.out.println(Aux);
			Aux=Aux.siguiente;
		}
	}
	public NodoDoble<E> buscar(int dato){
		NodoDoble<E> Aux=Inicio;
		while(Aux !=null){
			if(Aux.dato.equals(dato))
				return Aux;
			Aux=Aux.siguiente;
		}
		return null;
	}
	public NodoDoble<E> getFin(){
		return Fin;
	}
	public NodoDoble<E> getInicio(){
		return Inicio;
	}
}