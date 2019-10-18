package analizadorLyS;

public class nodo<E>
{
	E elemento;
	E tipo;
	E renglon;
	E columna;
	nodo<E> siguiente;
	nodo<E> anterior;
	
//		public nodo(E ele, E tipo) 
//		{
//			this.elemento=ele;
//			this.tipo=tipo;
//		}
		public nodo(E ele, E tipo, E ren, E col) 
		{
			this.elemento=ele;
			this.tipo=tipo;
			this.renglon=ren;
			this.columna=col;
		}
}
