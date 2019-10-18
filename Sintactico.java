package analizadorLyS;

import java.util.ArrayList;

//import analizadorlexico.copy.Lista3;

public class Sintactico <T>{

    ArrayList<String> token;
    ArrayList<String> elementosClase;
    ArrayList<Integer> tipo; 
    ArrayList<Token> infor; 
    String tok = "", esperado = "";
    Token objToken;
    Lista lista1, lista2, listaElementosClase, listaElementosVar;
    int type, cont = 0;
    
	final int clase = 0, hacer = 1, until = 2, entero = 3, booleano = 4, llaveizq = 5, llaveder = 6, EQ = 7, semi = 8,
			menor = 9, mayor = 10, d2EQ = 11,truex = 12,  falsex = 13,print = 14, brackizq = 15, brackder = 16, comillas = 17,
			mas = 18, menos = 19, si = 20, publico = 21,
			num = 50,   ID = 52; //bool = 21,

	public Sintactico(ArrayList<String> token, ArrayList<Integer> tipo,  ArrayList<Token> info)
	{
		infor = info;
		this.token = token;
		
		this.tipo = tipo; 
		try {			
			this.tok = this.token.get(0); //devuelve token
			this.type = this.tipo.get(0); //devuelve tipo para el metodo eat
			this.objToken = this.infor.get(0); //devuelve tipo para el metodo eat
		} catch (Exception e) {
			System.out.println("El archivo está vacío");	
			System.exit(0);
			}
		lista1 = new Lista();
		lista2 = new Lista();
		P();
		
		SimbolsTable();
		Semantico s;

		s = new Semantico(token,tipo, info, lista1, lista2);
		Lista2();
		if(s.contadorTodo > 0 ) {
			System.out.println("Se detectaron errores Semánticos");
			
		}
		else 
			System.out.println("Semántica correcta");
		System.out.println("\nTabla de simbolos\n\nElemento\tNombre\t\tRenglón\t\tColumna");
		lista1.mostrar();
//		if(lista2.size() != 0) {
			System.out.println("\nlista de valores\n\nNombre\t\tValor\t\tRenglón\t\tColumna");
			lista2.mostrar();
//		}
		listaElementosClase = new Lista();
		listaElementosVar = new Lista();
		ArrayList<Integer> subJ = new ArrayList<Integer>(); // 	para comparar más adelante los subindices de J en el for
															//	y no duplicar valores repetidos en la impresión
		String metoNom = "";
		for (int i = 0; i <  lista1.size(); i++) {
			if(lista1.BuscarxIndexNom(i).equals("Método")) {
				
				for (int j = 0; j < token.size(); j++) {
					if(lista1.BuscarxIndexTip(i).equals(token.get(j)) 
						&& lista1.BuscarxIndexRen(i) == info.get(j).getRenglon()
						&& lista1.BuscarxIndexCol(i) == info.get(j).getColumna()) {
							listaElementosClase.insertar(token.get(1), lista1.BuscarxIndexTip(i),"\t\t"+ info.get(j).getRenglon(),info.get(j).getColumna());
							subJ.add(j);
						
					}
				}
				metoNom = "\t\t"+lista1.BuscarxIndexTip(i);
			}
			else {
				for (int j = 0; j < token.size(); j++)
				{

					
					if(lista1.BuscarxIndexTip(i).equals(token.get(j)) 
							&& (lista1.BuscarxIndexRen(i)) == info.get(j).getRenglon()
							&& (lista1.BuscarxIndexCol(i)+1) == info.get(j).getColumna()) {
						listaElementosVar.insertar(metoNom, lista1.BuscarxIndexTip(i), info.get(j).getRenglon(),info.get(j).getColumna());

					}}}
//			System.out.println("iprimer  " + i);
//			}
//				System.out.println("min");
		}
//		System.out.println("\nClase\t\tMétodo\n");
		if(metodos == null) {
		System.out.println("\nPertenencias\n");
		System.out.println("Clase\t\tMétodo\t\t\t\tRenglón\t\tColumna");
		listaElementosClase.mostrar();
		System.out.println("\t\tMétodo\t\tVariable");
		listaElementosVar.mostrar();
		}
	}
	
	public void SimbolsTable() {		
		String envio = "";
		for (int i = 0; i < token.size(); i++) 
		{
//			infor.get(i).getRenglon()
//			infor.get(i).getColumna()
			if(tipo.get(i) == entero || tipo.get(i) == booleano)
			{
				if(tipo.get(i) == entero)
					envio = "int";
				if(tipo.get(i) == booleano)
					envio = "boolean";
				if(token.get(i).equals(envio)) 
					lista1.insertar(envio, token.get(i+1), infor.get(i).getRenglon(),infor.get(i).getColumna());
			}
			if(tipo.get(i) == ID && tipo.get(i+1) == brackizq)
					lista1.insertar("Método", this.token.get(i), infor.get(i).getRenglon(),infor.get(i).getColumna());
				
			}
		
	}
	public void Lista2() {
		for (int i = 0; i < token.size(); i++) {
			if(tipo.get(i) == ID && tipo.get(i+1) != semi && tipo.get(i+1) != llaveder) 
			{
				
				if(((tipo.get(i+2) == falsex || tipo.get(i+2) == truex || tipo.get(i+2) == ID) ) && tipo.get(i+1) == EQ) 
					for (int j = 0; j < lista1.size(); j++) 
					{
						//comprueba sí ya existe esta variable como boolean
							 if(token.get(i).equals(lista1.BuscarxIndexTip(j)) && (lista1.BuscarxIndexNom(j).equals("boolean")))
							
							lista2.insertar( this.token.get(i),  this.token.get(i+2), infor.get(i).getRenglon(),infor.get(i).getColumna());//agrega variables y su valor al array
//						else if(token.get(i).equals(lista1.BuscarxIndexTip(j)) && !lista1.BuscarxIndexNom(j).equals("Método"))
//							System.out.println("Debes declarar la variable ** " + token.get(i) + " **, Renglón " + infor.get(i).getRenglon()
//									+ ", Columna " + infor.get(i).getColumna() + " debe como \"Booleana\" está declarado como " + lista1.BuscarxIndexNom(j)

//									+ ", en Renglón " + lista1.BuscarxIndexRen(j) + ", Columa " + lista1.BuscarxIndexCol(j));

					}
			
				if((((tipo.get(i+2) == num  ||tipo.get(i+2) == ID) && (tipo.get(i+3) == semi||tipo.get(i+3) == mas) ) && tipo.get(i+1) == EQ)) //toma c
					for (int j = 0; j < lista1.size(); j++) 
					{
						//comprueba sí ya existe esta variable como int
						if(token.get(i).equals(lista1.BuscarxIndexTip(j))&& lista1.BuscarxIndexNom(j).equals("int"))  //agrega los "int" con una suma
						if(tipo.get(i+3) == mas)
							lista2.insertar( this.token.get(i),  this.token.get(i+2)+""+this.token.get(i+3)+""+this.token.get(i+4), infor.get(i).getRenglon(),infor.get(i).getColumna());//agrega variables y su valor al array
						
						else if(token.get(i).equals(lista1.BuscarxIndexTip(j))&& lista1.BuscarxIndexNom(j).equals("int") &&  tipo.get(i+3) == semi)  { //agrega los "int"
							
							lista2.insertar( this.token.get(i),  this.token.get(i+2), infor.get(i).getRenglon(),infor.get(i).getColumna());//agrega variables y su valor al array
						}
//						else if(token.get(i).equals(lista1.BuscarxIndexTip(j)) && !lista1.BuscarxIndexNom(j).equals("Método"))//verifica que los int sean int
// {
//								System.out.println("Debes declarar la variable ** " + token.get(i) + " **, Renglón " + infor.get(i).getRenglon()
//										+ ", Columna " + infor.get(i).getColumna() + " debe como \"Entera\" está declarado como " + lista1.BuscarxIndexNom(j)
//										+ ", en Renglón " + lista1.BuscarxIndexRen(j) + ", Columa " + lista1.BuscarxIndexCol(j));						}
							
					}
		}
}}
		
	
	public void Advance() 
	{		
		for (int i = cont; i < cont+1 && i <token.size(); i++) 
		{
			type = tipo.get(i);
			tok = token.get(i);
			objToken = infor.get(i);
		}
	}		
	public void eat ( int esp)
	{ 
//		System.out.println("valor de tok = "+type+", valor esperado = "+esp);
		if ( type == esp)// if( ')' == ';' )
		{
			cont++;
			Advance ();  
		}
		else 
			error (esp); 
	} 
	ArrayList<String> metodos;
	Semantico semantico;
	public void P ( ) {
		
		metodos = new ArrayList<String>();
		
		if(type == publico )
			eat(publico);
		eat(clase);
		eat(ID);
			eat(llaveizq);
			
				
				while(type == ID||type == entero || type == booleano ||type == publico || type == si)	{
					while(type != llaveder ) {
						while(type == entero || type == booleano || type ==publico)
							D();

						while(type == hacer || type == print || type == ID || type ==si )
							S();
						while(type == entero || type == booleano || type ==publico)
							D();
						if(cont == tipo.size() && type != llaveizq) {
							System.out.println("\nse esperaba un ** } ** al final del todo");
							break;
						}
														

					}

					metodos.add(tok);//se añaden métodos
//					semantico = new Semantico(metodos, tok);
					if(type == ID) {
						eat (ID);
					
						eat(brackizq);
						
						while(type == entero || type == booleano || type ==publico)
								D();
							
						eat(brackder);
					
						eat(llaveizq);
						
							while(type != llaveder ) {
								while(type == entero || type == booleano || type ==publico)
									D();

								while(type == hacer || type == print || type == ID || type ==si )
									S();
								while(type == entero || type == booleano || type ==publico)
									D();
							}
						eat(llaveder);}
				}
			eat(llaveder);
//		System.out.println((tipo.size()) + " contador = " + cont);
		if(cont < tipo.size())
//		if(tok != null)
			error(1);
		
		System.out.println("Sintaxis correcta");
	}
	
	public void D () {

		switch (type) 
		{
		case publico:
			eat(publico);
			if(type == booleano)
				eat(booleano);
			if(type == entero)
				eat(entero);
			S();
			break;
			case entero: 
				eat(entero);
				S();
				break;

			case booleano:
				eat (booleano);S();
				break;
			default: error(); break;
		}
	}
	
	public void S ( ) {
		switch ( type ) {
		case si:
			eat(si);
			
			eat(brackizq);
				E ( );	eat (brackder);	
				while(type == hacer || type == print || type == ID || type ==si )
					S();
				break;		
		case hacer:
				eat(hacer);
				
				while(type == hacer || type == print || type == ID || type ==si )
				S();	eat(until);eat(brackizq);
				E ( );	eat (brackder);	eat(semi);
				while(type == hacer || type == print || type == ID || type ==si )
					S();
				break;		
	
		case print: 
			eat ( print );eat(brackizq);
			if(type != comillas)
				eat(ID);
			else
			{
				eat(comillas);
				if(type == ID)
					eat(ID);
				eat(comillas);
			}
			eat(brackder);
				eat(semi);
				while(type == hacer || type == print || type == ID || type ==si )
				S();
			break;		
		case ID:
			eat(ID);
			
			if(type == EQ)
				eat(EQ);
			if(type == num)
				eat(num);
			else if(truex == type)
				eat(truex);
			else if(falsex == type)
				eat(falsex);
			else if(ID == type)
				eat(ID);
			if(type != semi) {
				if( type == mas) 
					eat(mas);
				if( type == menos)
					eat(menos);
				if( type == ID)
					eat(ID);
			
				if( type == num)
					eat(num);
				if( type == falsex)
					eat(falsex);
				if( type == truex)
					eat(truex);
			}
			eat(semi);
			while(type == hacer || type == print || type == ID || type ==si )
				S();
			break;
		default: error();
		break;		
		}
	}
	public void E ( ) 
	{	
		int simbol = 0;
		String tok, simbolo;

		switch (type) {
	
			case ID: 
				if(type == semi || type == brackder)
					break;		
				eat (ID);
				Simbols();
				eat (ID) ;
				break;		
			case truex:
				eat(truex);
				break;		
			case falsex:
				eat(falsex);
				break;				
			default: error(); break;
		}
	}
	public void error(int type)
	{

		String tipo = ValoresInversos(type);
		if(type == 0) 
			tipo = "\nSe esperaba una expresión **class**";
		else if(type == 1)
			tipo = "\nError en los límites, se encontró al menos un token después de la llave cerrada ** " + tok + " **";
		else 
			if(tipo == "false")
				tipo = "\nSe esperaba ** un token entero o booleano ** en lugar o al lado de ** " +tok+" **";
			else
			tipo = "\nSe esperaba este token ** "+tipo+" ** en lugar o al lado de ** " +tok+" **";
		
		System.out.println(tipo);

		System.exit(0);
	}
	
	public void error() 
	{	
		System.out.println("Error en la sintaxis, con el siguiente token ** "+tok+" **");System.exit(0);	
	}
	
	public boolean Simbols() {
		if (type == menor || type == mayor  || type == d2EQ /*type == mayor || type == dobleEQ ||*/ )
		{
			eat(type);
			return true;
		}
		else 
			error();
			return false;
	}
	
	public String ValoresInversos(int type)
	{
		String devuelve, cadenas[] = {"class","do","until","int","boolean","{","}", "=", ";","<", ">", "=="//12
				,"true","false", "Print", "(",")", "\"", "+", "-", "if", "public"};		//8   total = 20, de 0 al 19
		if(type == 50) 
			return devuelve = "numérico";
		if(type == 52) 
			return devuelve = "identificador";
		devuelve = cadenas[type];
		
		return  devuelve;
	}
	public String IdSemi() {
		String tok = this.tok;
		eat(ID);
		eat(semi);
		return tok;
	}
}