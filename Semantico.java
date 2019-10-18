package analizadorLyS;

import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.text.StyledEditorKit.ForegroundAction;

public class Semantico {
	
	 
	final int clase = 0, hacer = 1, until = 2, entero = 3, booleano = 4, llaveizq = 5, llaveder = 6, EQ = 7, semi = 8,
			menor = 9, mayor = 10, d2EQ = 11,truex = 12,  falsex = 13,print = 14, brackizq = 15, brackder = 16, comillas = 17,
			mas = 18, menos = 19, si = 20,
			num = 50,   ID = 52; //bool = 21,
	public ArrayList<String> metodos = new ArrayList<String>();
	int metod = 0;
	int conDec = 0;
	int conMet = 0;
	public Semantico(ArrayList<String> tokens,ArrayList<Integer> tipos, ArrayList<Token> info, Lista l1, Lista l2) {
		
		//lista1 contiene tipo y var, metodos - enteros
		//lista2 contiene metodos y asignaciones de variables, nombre de var - valor
		ArrayList<String> valoresRepetidos = new ArrayList<String>();
		
		int contador = 0, conta = 0;

		String nombretok = "";
		//Esto me servirá más adelante para no imprimir valores repetidos
		for (int i = 0; i < l1.size(); i++) 
		{
			
			
			for (int j = 0; j < l1.size(); j++) 
			{
					
				if(i != j) {
					if((l1.BuscarxIndexTip(i).equals(l1.BuscarxIndexTip(j)) && l1.BuscarxIndexNom(i).equals(l1.BuscarxIndexNom(j))) 
							|| (l1.BuscarxIndexNom(i).equals("boolean") && (l1.BuscarxIndexTip(i).equals(l1.BuscarxIndexTip(j)))))
					{

						nombretok = l1.BuscarxIndexTip(i);
						for (int k = 0; k < valoresRepetidos.size(); k++) { //for para detectar valores repetidos en la lista
							if( nombretok.equals(valoresRepetidos.get(k)) )//detecta si hay elementos repetidos en la lista
								conta++;
						}
						if(conta>0) //si encuentra valores repetidos anteriormente entra aquí y hace un salto  cada vez que entre
									//hasta la siguiente "i"
							continue;
						valoresRepetidos.add(nombretok);
//						if(l1.BuscarxIndexTip(i).equal)
						for (int x = 0; x < tokens.size(); x++) {
							
							if(nombretok.equals(tokens.get(x))) {
								
								if((tokens.get(x-1).equals("int") || tokens.get(x-1).equals("boolean")  )   ) {
									conDec++;
										
								}
								else if(tokens.get(x+1).equals("(")) {
									conMet++;
									
									}
								}			
							}
					}
				}

			}
		}

		nombretok = "";

		valoresRepetidos = new ArrayList<String>();
		 contador = 0; conta = 0;
		 int c = 0;
		for (int i = 0; i < l1.size()-1; i++) 
		{
			
			conta = 0;
			for (int j = 0; j < l1.size(); j++) 
			{
				if(i != j) {
					if((l1.BuscarxIndexTip(i).equals(l1.BuscarxIndexTip(j)) && l1.BuscarxIndexNom(i).equals(l1.BuscarxIndexNom(j))) 
							|| (l1.BuscarxIndexNom(i).equals("boolean") && (l1.BuscarxIndexTip(i).equals(l1.BuscarxIndexTip(j)))))
//					&& l1.BuscarxIndexNom(i).equals(""))
					{
//						if(tokens.get(j-1).equals("int"))
//							continue;
						nombretok = l1.BuscarxIndexTip(i);
						for (int k = 0; k < valoresRepetidos.size(); k++) { //for para detectar valores repetidos en la lista
							if( nombretok.equals(valoresRepetidos.get(k)) )//detecta si hay elementos repetidos en la lista
								conta++;
								
						}
						if(conta>0) //si encuentra valores repetidos anteriormente entra aquí y hace un salto  cada vez que entre
									//hasta la siguiente "i"
							continue;
						
						valoresRepetidos.add(nombretok);
//						System.out.println("El dato ** " + l1.BuscarxIndexTip(i) + " ** se repite");
//						System.out.println(l1.BuscarxIndexTip(i) + " = " + (l1.BuscarxIndexTip(j)) 
//								+ "  " +l1.BuscarxIndexNom(i) + " = " + (l1.BuscarxIndexNom(j)));
						contador = ValoresRep(nombretok, valoresRepetidos,  tokens,tipos,  info, contador , l1 );
						contadorTodo = contador;
					}
					
				}
			}
		}
		String vardec = "/", vardecdesp = "/";
		
		
		ArrayList<Token_Tipo> al = new ArrayList<Token_Tipo>();
		int cont = 0;
		for (int i = 0; i < tokens.size(); i++)
		{
			cont = 0;
			if(tipos.get(i) == ID && (tipos.get(i-1) == entero ||  tipos.get(i-1 ) == booleano )) //almacena cada token que va siendo declarado sobre la marcha
			{
				al.add(new Token_Tipo(tokens.get(i), tipos.get(i-1)));
			}
			
			if(tipos.get(i) == ID && tipos.get(i+1) == EQ && tipos.get(i+3) == semi)
			{
			
				for (int j = 0; j < al.size(); j++)
				{
					if(tokens.get(i).equals(al.get(j).getToken())) 
					{
						cont = 1;
						break;
					}

				}
				if(cont == 0) {
					contador++;
	/* AQUI*/	System.out.println("token ** " +info.get(i).getValor() + " NO declarada anteriormente **, En renglón \"" + info.get(i).getRenglon() + "\", número de token \"" + info.get(i).getColumna() + "\"");
					contadorTodo = contador;
//					System.out.println("Variable ** " + tokens.get(i) + " ** NO declarada anteriormente" );
				}
					
			}
			if(tipos.get(i) == ID && tipos.get(i+1) == EQ && (tipos.get(i+1) == mas || tipos.get(i+1) == menos))
			{
//				b = 1 + 2;
				for (int j = 0; j < al.size(); j++)
				{
					if(tokens.get(i).equals(al.get(j).getToken())) 
					{
						cont = 1;
						break;
					}
					
				}
				if(cont == 0) {
					contador++;
					/* AQUI*/	System.out.println("token ** " +info.get(i).getValor() + " NO declarada anteriormente **, En renglón \"" + info.get(i).getRenglon() + "\", número de token \"" + info.get(i).getColumna() + "\"");
					contadorTodo = contador;
//					System.out.println("Variable ** " + tokens.get(i) + " ** NO declarada anteriormente" );
				}
				
			}
			cont = 0;
			//valida las comparaciones
			if((tipos.get(i) == ID &&( tipos.get(i+1) == d2EQ ||tipos.get(i+1) == menor ||tipos.get(i+1) == mayor ))
				|| (tipos.get(i) == ID &&( tipos.get(i-1) == d2EQ ||tipos.get(i-1) == menor ||tipos.get(i-1) == mayor )))
			{
				for (int j = 0; j < al.size(); j++)
				{
					if(tokens.get(i).equals(al.get(j).getToken())) //si pasa esto es que si existe declarado
					{
						cont = 1;
					
						if(tipos.get(i+2) == ID)
							for (int j2 = 0; j2 < al.size(); j2++) {
								if(tokens.get(i+2).equals(al.get(j2).getToken()) && al.get(j2).getTipo() != al.get(j).getTipo())
								{
									
									String valor1 = "", valor2 = "";
									if(al.get(j).getTipo() == booleano)
										valor1 = "boolean";
									if(al.get(j).getTipo() == entero)
										valor1 = "int";
									if(valor1 == "boolean")
										valor2 = "int";
									if(valor1 == "int")
										valor2 = "boolean";
									System.out.println("en Renglón " + info.get(i).getRenglon() + " ** " + al.get(j).getToken() + " & " + al.get(j2).getToken() + " ** son tipos imcompatibles \"" + valor1 + " con " + valor2 + "\"");
									contador++;
									contadorTodo = contador;
									break;
									
								}
							}
					}
				}
				
				if(cont == 0) {
					contador++;
					System.out.println("token ** " +info.get(i).getValor() + " NO declarada anteriormente **, En renglón \"" + info.get(i).getRenglon() + "\", número de token \"" + info.get(i).getColumna() + "\"");
					contadorTodo = contador;
//					System.out.println("Variable ** " + tokens.get(i) + " ** NO declarada anteriormente" );
				}
				
			}
			cont = 0;

			if(tipos.get(i) == ID && tipos.get(i+1) == brackder&& tipos.get(i-1) == brackizq)
			{
			
				for (int j = 0; j < al.size(); j++)
				{
					if(tokens.get(i).equals(al.get(j).getToken())) 
					{
						cont = 1;
						break;
					}

				}
				if(cont == 0) {
					contador++;
					System.out.println("token ** " +info.get(i).getValor() + " NO declarada anteriormente **, En renglón \"" + info.get(i).getRenglon() + "\", número de token \"" + info.get(i).getColumna() + "\"");
					contadorTodo = contador;
//					System.out.println("Variable ** " + tokens.get(i) + " ** NO declarada anteriormente" );
				}
					
			}
			
			/////////////////////////////////////////////////////////////////////////////////////////////
			//-----------------COMPARA SI LOS ID'S ASIGNADOS CORRESPONDEN EN SU TIPO ----------------------------------------------------
			///////////////////////////////////////////////////////////////////////////////////////////
			int contando = 0;
			
			cont = 0;
			
			if((tipos.get(i) == ID || tipos.get(i) == num || tipos.get(i) == falsex || tipos.get(i) == truex)
					&& tipos.get(i-1) == EQ && tipos.get(i-2) == ID) /* && tipos.get(i+1)==semi*/
			{
//				System.out.println("entra a semi");
//				System.out.println(tokens.get(i) + " = " + tokens.get(i-2));
				for (int j = 0; j < al.size(); j++)
				{
					if(tokens.get(i).equals(al.get(j).getToken())) //comprueba si el token fue declarado con anterioridad
					{
						cont = 1;
						if(tipos.get(i) == ID)
							for (int j2 = 0; j2 < al.size(); j2++) {
								if(tokens.get(i-2).equals(al.get(j2).getToken()) && al.get(j2).getTipo() != al.get(j).getTipo())
								{
									String valor1 = "", valor2 = "";
									if(al.get(j).getTipo() == booleano)		valor1 = "boolean";	
									if(al.get(j).getTipo() == entero)		valor1 = "int";
									if(valor1 == "boolean")					valor2 = "int";
									if(valor1 == "int")						valor2 = "boolean";
									System.out.println("en Renglón " + info.get(i).getRenglon() + " ** " + al.get(j2).getToken() + " & " + al.get(j).getToken() + " ** son tipos imcompatibles \"" + valor1 + " con " + valor2 + "\"");
									contador++;			contadorTodo = contador;
									contando = 3;
									break;	
								}
//								System.out.println(tokens.get(i) + " " + al.get(j2).getToken() + "\n"
//										+ " " + al.get(j2).getTipo() + " " + al.get(j).getTipo());
								if(tipos.get(i+2) != llaveder)

								if(tipos.get(i+3) == semi &&(tipos.get(i+1) == mas  || tipos.get(i+1) == menos ))
								{
//									System.out.println(tokens.get(i) + " ");
									
									
									
									
									
									
									
									
									for (int j3 = 0; j3 < al.size(); j3++)
									{
										if(tokens.get(i+2).equals(al.get(j3).getToken())) //comprueba si el token fue declarado con anterioridad
										{
											cont = 1;
											if(tipos.get(i+2) == ID)
												for (int j4 = 0; j4 < al.size(); j4++) 
												{
													if((tokens.get(i-2).equals(al.get(j3).getToken()) && al.get(j3).getTipo() != al.get(j3).getTipo()) )
													{
//														System.out.println(tokens.get(i-2) + " " + al.get(j3).getToken() +"\n"
//																			+ "" + al.get(j3).getTipo() + " " + al.get(j3).getTipo());
														System.out.println("en Renglón " + info.get(i).getRenglon() + " ** " + tokens.get(i-2) + " & " + al.get(j3).getToken() + " ** Error con los \"tipos\"");
														contador++;			contadorTodo = contador;
															j2 = al.size();
//															j3 = al.size();
															j = al.size();
														break;	
													}

												}
										}
										if( tipos.get(i+2) == falsex ||tipos.get(i+2) == truex){
											System.out.println("entrax");
											System.out.println("en Renglón " + info.get(i).getRenglon() + " ** " + tokens.get(i-2) + " & " + tokens.get(i+2) + " ** Error con los \"tipos\"");
											contador++;			contadorTodo = contador;
													j2 = al.size();
//													j3 = al.size();
											j = al.size();
											break;	
										}
									}
							}		
							}
						
					}
					if(tipos.get(i) == num)
					{
						cont = 1;
						for (int j2 = 0; j2 < al.size(); j2++) {
								if(tokens.get(i).equals(al.get(j).getToken())) //comprueba si el token fue declarado con anterioridad
System.out.println(tokens.get(i));
							if(tokens.get(i-2).equals(al.get(j2).getToken()) && al.get(j2).getTipo() != entero)
							{
								System.out.println("en Renlón " + info.get(i).getRenglon() + " ** " + tokens.get(i) + " ** es tipo imcompatible con " + tokens.get(i-2) + "  Error con los \"tipos\"");
								contador++;			contadorTodo = contador;
								
						
									j = al.size();
							
								break;	
							}
						}
//						System.out.println(tipos.get(i+3) + " " + tipos.get(i+1));
						if(tipos.get(i+2) != llaveder)
							if(tipos.get(i+3) == semi &&(tipos.get(i+1) == mas  || tipos.get(i+1) == menos ))
							{
//								System.out.println(tokens.get(i) + " ");
								for (int j3 = 0; j3 < al.size(); j3++)
								{
									if(tokens.get(i+2).equals(al.get(j3).getToken())) //comprueba si el token fue declarado con anterioridad
									{
										cont = 1;
										if(tipos.get(i+2) == ID)
											for (int j2 = 0; j2 < al.size(); j2++) 
											{
												if((tokens.get(i-2).equals(al.get(j3).getToken()) && al.get(j3).getTipo() != al.get(j3).getTipo()) )
												{
//													System.out.println(tokens.get(i-2) + " " + al.get(j3).getToken() +"\n"
//																		+ "" + al.get(j3).getTipo() + " " + al.get(j3).getTipo());
													System.out.println("en Renglón " + info.get(i).getRenglon() + " ** " + tokens.get(i-2) + " & " + al.get(j3).getToken() + " **  Error con los \"tipos\"");
													contador++;			contadorTodo = contador;
//														j2 = al.size();
//														j3 = al.size();
														j = al.size();
													break;	
												}

											}
									}
									if( tipos.get(i+2) == falsex ||tipos.get(i+2) == truex){
										System.out.println("en Renglón " + info.get(i).getRenglon() + " ** " + tokens.get(i-2) + " & " + tokens.get(i+2) + " ** Error con los \"tipos\"");
										contador++;			contadorTodo = contador;
//												j2 = al.size();
//												j3 = al.size();
										j = al.size();
										break;	
									}
								}
						}
					}
					if(tipos.get(i) == falsex) 				
					{
						cont = 1;
						for (int j2 = 0; j2 < al.size(); j2++) {
							if(tokens.get(i-2).equals(al.get(j2).getToken()) && al.get(j2).getTipo() != booleano)
							{
								System.out.println("en Renglón " + info.get(i).getRenglon() + " ** " + tokens.get(i) + " ** es tipo imcompatible con " + tokens.get(i-2) + "\"");
								contador++;			contadorTodo = contador;
								j = al.size();
								}
						}
						if(tipos.get(i+1) == mas || tipos.get(i+1) == menos	) 
							{
							System.out.println("en Renglón " + info.get(i).getRenglon() + " ** " + tokens.get(2) 
							+ " ** imposible sumar en un booleano, variable " + tokens.get(i-2) + "\"");
							contador++;			contadorTodo = contador;
							j = al.size();
							}
					}
					if(tipos.get(i) == truex)
					{
						cont = 1;
						for (int j2 = 0; j2 < al.size(); j2++) {
							if(tokens.get(i-2).equals(al.get(j2).getToken()) && al.get(j2).getTipo() != booleano)
							{
								System.out.println("en Renglón " + info.get(i).getRenglon() + " ** " + tokens.get(i) + " ** es tipo imcompatible con " + tokens.get(i-2) + "\"");
								contador++;			contadorTodo = contador;
								j = al.size();
							}
							
						}
						
						if(tipos.get(i+1) == mas || tipos.get(i+1) == menos	) 
						{
						System.out.println("en Renglón " + info.get(i).getRenglon() + " ** " + tokens.get(2) 
						+ " ** imposible sumar en un booleano, variable " + tokens.get(i-2) + "\"");
						contador++;			contadorTodo = contador;
						j = al.size();
						}
					}
								
				}
				if(cont == 0) {
					contador++;
						if(!Pattern.matches("^\\d+$",info.get(i).getValor() ) )

							System.out.println("token ** " +info.get(i).getValor() + " NO declarada anteriormente **,"
							+ " En renglón \"" + info.get(i).getRenglon() + "\", número de token \"" + info.get(i).getColumna() + "\"");
							contadorTodo = contador;
//					System.out.println("Variable ** " + tokens.get(i) + " ** NO declarada anteriormente" );
				}
			}
				
				
				//_________________________________________________________________________________________
				
	
			}
			
		
//	for (int j = 0; j < al.size(); j++) {
//		System.out.println("al " + al.get(j).getTipo());
//	}
	}
	
	int contadorTodo = 0;
	public int contador () {
		return contadorTodo;
		
	}
	public int ValoresRep(String nombretok, ArrayList valoresRepetidos, ArrayList tokens,ArrayList<Integer> tipos, ArrayList<Token> info,int contador, Lista l1 ) {
		int conDec = 0;
		int conMet = 0;
		ArrayList<Token> aD = new ArrayList<Token>(), aM = new ArrayList<Token>();
		for (int x = 0; x < tokens.size(); x++) {
			
			if(nombretok.equals(tokens.get(x))) {
				
				if((tokens.get(x-1).equals("int") || tokens.get(x-1).equals("boolean")  )   ) {
					conDec++;
					aD.add(new Token(info.get(x).getValor(),info.get(x).getRenglon(),info.get(x).getColumna() ));
					if(conDec == this.conDec && conDec > 1)
						for (int i = 0; i < aD.size(); i++) 
							
							System.out.println("token Declaración** " +aD.get(i).getValor() + " se REPITE **, En renglón \"" + aD.get(i).getRenglon() + "\", número de token \"" + aD.get(i).getColumna() + "\"");
						
					contador++;
				}
				else if(tokens.get(x+1).equals("(")) {
					conMet++;
					
					aM.add(new Token(info.get(x).getValor(),info.get(x).getRenglon(),info.get(x).getColumna() ));
					if(conMet == this.conMet && conMet > 1) {
						for (int i = 0; i < aM.size(); i++) 
							System.out.println("token Método ** " +aM.get(i).getValor() + " se REPITE **, En renglón \"" + aM.get(i).getRenglon() + "\", número de token \"" + aM.get(i).getColumna() + "\"");
						
					}
					contador++;
				}			
			}
		}
		return contador;
	}
	
	
	
	
	
	
	public boolean Existencia(String token, ArrayList<String> tokens, ArrayList<Integer> tipos, ArrayList<Token> info, Lista l1)
	{
		int contador = 0;
		ArrayList<String> al = new ArrayList<String>();
		int cont = 0;
		for (int i = 0; i < tokens.size(); i++)
		{
			cont = 0;
			
			if(token.equals(l1.BuscarxIndexTip(i)))
				
			if(tipos.get(i) == ID && (tipos.get(i-1) == entero ||  tipos.get(i-1 ) == booleano))
			{
				al.add(tokens.get(i));
			}
			if(tipos.get(i) == ID && tipos.get(i+1) == EQ)
			{
			
				for (int j = 0; j < al.size(); j++)
				{
					if(tokens.get(i).equals(al.get(j))) 
					{
						cont = 1;
						break;
					}

				}
				if(cont == 0) {
					contador++;
					System.out.println("token ** " +info.get(i).getValor() + " NO declarada anteriormente **, En renglón \"" + info.get(i).getRenglon() + "\", número de token \"" + info.get(i).getColumna() + "\"");
					contadorTodo = contador;
//					System.out.println("Variable ** " + tokens.get(i) + " ** NO declarada anteriormente" );
				}
					
			}
		
		}
	
		return true;
	}
}


