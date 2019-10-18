package analizadorLyS;

public class Token_Tipo {
	
	private String token;
	private int tipo;
	
	public Token_Tipo(String tok, int tip) {
		token = tok;
		tipo = tip;
	}

	public String getToken() {
		
		return token;
	}
	public int getTipo() {
		return tipo;
	}
}
