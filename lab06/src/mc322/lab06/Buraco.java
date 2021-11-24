package mc322.lab06;

public class Buraco extends Componente
{
	Buraco(int lin, int col, Caverna cav)
	{
		super(lin, col, cav);
		sim = "B";
		
		Componente componente = null;
		
		componente = new Brisa(lin + 1 , col, cav);
		cav.tentaAdicionarComponenteSecundario(componente);
		
		componente = new Brisa(lin - 1, col, cav);
		cav.tentaAdicionarComponenteSecundario(componente);

		componente = new Brisa(lin, col + 1, cav);
		cav.tentaAdicionarComponenteSecundario(componente);

		componente = new Brisa(lin, col - 1, cav);		
		cav.tentaAdicionarComponenteSecundario(componente);
	}	
	
}
