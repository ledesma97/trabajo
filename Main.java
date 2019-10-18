package analizadorLyS;

import java.util.ArrayList;

public class Main 
{
	public static void main(String[] args) 
	{
		
	        Analiza analizador = new Analiza("g.txt");
	        ArrayList<String> a1 = analizador.resultado;
	        Sintactico s;
	        
//	        ArrayList<Token> tk = analizador.tk;
//	        
//			for(int i = 0; i < tk.size(); i++)
//			{ 
//				System.out.println( tk.get(i).getValor() + "      " + tk.get(i).getColumna() );
//			}
			
//			System.out.println("analizador.tk\n\n\n");
			
			for(int i = 0; i < a1.size(); i++) { 
				System.out.println( a1.get(i) );
			}
			if( a1.get(0).equals("No hay errores lexicos"))
			{
				s = new Sintactico(analizador.every_token, analizador.every_tipo, analizador.tk );
			
			}
	}
}
