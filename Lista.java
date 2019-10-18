package analizadorLyS;


//import lista.Nodo;

public class Lista <T>{

	@SuppressWarnings("rawtypes")
	nodo<T> inicio = null, fin = null;
	public boolean vacia(){
		
		if(inicio != null)
			return false;
		else
			return true;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean insertar(T ele, T tipo, T ren, T col) {
		
		nodo n = new nodo(ele, tipo, ren, col);
//		nodo aux = inicio, aux2 = null;
		
//		if(!vacia())
//			System.out.println("\n"+n.numero+"\n");
		if(vacia())
		{
			inicio = n;
			fin = n;		
//			System.out.println("Vacía\n"+n.numero+"\n");
			return true;
			
		}
	
		fin.siguiente = n;		//f		= 6,7
		n.anterior=fin;			//nuevo = 5,6
		fin=n;					//f 	= 6,7
		return true;

	}	
	//Guardo tipos y valores
	public void mostrar() {
		nodo aux = inicio;
//		System.out.println("Tipo \t\tValor");
		while(aux != null){
			System.out.println( aux.elemento +"\t\t" + aux.tipo  +"\t\t" + aux.renglon  +"\t\t" + aux.columna );
			aux = aux.siguiente;
		}
	}
	public nodo buscar(T dato){
		nodo Aux=inicio;
		while(Aux !=null){
			if(Aux.elemento.equals(dato))
				return Aux;
			Aux=Aux.siguiente;
		}
		return null;
	}
	//Guardo tokens
	public String BuscarxIndexNom(int dato){
		
		nodo aux = inicio;
		int indice = 0;
		while(dato != indice) {
			indice++;
			aux = aux.siguiente;
		}
		if(indice == dato) 
			return (String) aux.elemento;							

		return null;
	}
	public String BuscarxIndexTip(int dato){
		
		nodo aux = inicio;
		int indice = 0;
		while(dato != indice) {
			indice++;
			aux = aux.siguiente;
		}
		if(indice == dato) 
			return (String) aux.tipo;							
		
		return null;
	}
	public int BuscarxIndexRen(int dato){
		Token tok;
		nodo aux = inicio;
		int indice = 0;
		while(dato != indice) {
			indice++;
			aux = aux.siguiente;
		}
		if(indice == dato) 
			return (int) aux.renglon;							
		
		return -1;
	}
	public int BuscarxIndexCol(int dato){
		
		nodo aux = inicio;
		int indice = 0;
		while(dato != indice) {
			indice++;
			aux = aux.siguiente;
		}
		if(indice == dato) 
			return (int) aux.columna;							
		
		return -1;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int retirar() {
		nodo aux = inicio, auxant = null;
		
//		if(vaz
		
		System.out.println(inicio.elemento);
		inicio = inicio.siguiente;
		

		System.out.println("\nRegistro retirado.");
		return 0;
		
	}
	public int size() {
		nodo aux = inicio;
		int indice = 0;
		
		while(aux != null){
			indice++;
			aux = aux.siguiente;
		}		
		return indice;
	}
}
