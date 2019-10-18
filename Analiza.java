package analizadorLyS;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.*;
import javax.swing.JOptionPane;

public class Analiza
{		
    int renglon = 1,columna = 1, col2 = 0, retiene = 0, cont = 0,  contador = -1;
    boolean bandera = true;
    ArrayList<String> resultado = new ArrayList<String>(), every_token = new ArrayList<String>();
    ArrayList<Integer> every_tipo = new ArrayList<Integer>(); 
	ArrayList<Token> tk = new ArrayList<Token>();
	public Analiza(String ruta) 
	{
		analizaCodigo(ruta);
		if(bandera) /*{*/
			resultado.add("No hay errores lexicos"); 	/*for(int i=0; i<every_token.size();i++) { System.out.println(every_token.get(i));	}}*/
	}
	public void analizaCodigo(String ruta)
	{	
		String linea = "", token = "";
		StringTokenizer tokenizer;
		try{
			FileReader file = new FileReader(ruta);
			BufferedReader archivoEntrada = new BufferedReader(file);
			linea = archivoEntrada.readLine();
			
			while (linea != null){
				columna = 0;
				linea = espacios(linea);
				tokenizer = new StringTokenizer(linea);
				while(tokenizer.hasMoreTokens()) //DENTRO DE ESTE WHILE, EN EL METODO analizadorLexico SE MANDA CADA TOKEN, CADA PALABRA
				{				
					columna++;
					token = tokenizer.nextToken();
					analizadorLexico(token);
				}
				linea=archivoEntrada.readLine();
				renglon++;
			}
			archivoEntrada.close();
		}catch(IOException e) {
			JOptionPane.showMessageDialog(null,"No se encontro el archivo favor de checar la ruta","Alerta",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void analizadorLexico(String token) 
	{
		token = Junta(token);
		if(token == "==")
		{
			return;
		}
		String cadenas[] = {"class","do","until","int","boolean","{","}", "=", ";","<", ">", "=="//12
							,"true","false", "Print", "(",")", "\"", "+", "-", "if", "public"};		//8   total = 20, de 0 al 19
		int tipo = -1;
		for (int i = 0; i < cadenas.length; i++) 
		{
			if(token.equals(cadenas[i]))
				tipo = i;
		}
//		if(token.matches("^([a-z]*[A-Z]*[0-9]*[,]*[.]*[_]*)*?$")) {}
//		if(token.matches("^[0-9]+([.][0-9]+)?$")) {}
		if(Pattern.matches("^\\d+$",token) ) 
				tipo =50;
	
		if(tipo==-1) {
			Pattern pat = Pattern.compile("^[a-zA-Z\\d]+$");
			Matcher mat = pat.matcher(token);
			if(mat.find())
				tipo =52;
			else {
				resultado.add("Error Léxico en la linea "+renglon+" columna "+columna+" token "+token+"");
				every_token.add(token);
				every_tipo.add(tipo);
				tk.add(new Token(token, renglon, columna));
				bandera = false;
				return;
			}
		}
		tk.add(new Token(token, renglon, columna));
		every_token.add(token);
		every_tipo.add(tipo);
//		System.out.println(cont++ + " " +token);
	}
	public String espacios(String linea){
		for (String cadena : Arrays.asList("(", ")", "{", "}", "=", ";", "*", "-", "+", "<", "\""))
		{
			if(linea.contains(cadena)) 
				linea = linea.replace(cadena, " "+cadena+" ");
		}
		return linea;
	}
	public String Junta(String token) {
		
		contador++;
		if(token.equals("="))
			retiene++;
		else 
			retiene = 0;
		if(retiene == 2)
		{
			retiene = 0;
			every_token.remove(contador-1);
			every_tipo.remove(contador-1);
			tk.remove(contador-1);
			
			tk.add(new Token("==", renglon, columna));
			every_token.add("==");
			every_tipo.add(11);
			token = "==";
			contador--;
		}
		return token;
	}
}