package mc322.lab06;

public class Wumpus extends Componente 
{
	
	Wumpus(int lin, int col, Caverna cav)
	{
		super(lin, col, cav);
		sim = "W";
		
		Componente componente = null;
		
		componente = new Fedor(lin + 1 , col, cav);
		cav.tentaAdicionarComponenteSecundario(componente);
		
		componente = new Fedor(lin - 1, col, cav);
		cav.tentaAdicionarComponenteSecundario(componente);

		componente = new Fedor(lin, col + 1, cav);
		cav.tentaAdicionarComponenteSecundario(componente);

		componente = new Fedor(lin, col - 1, cav);		
		cav.tentaAdicionarComponenteSecundario(componente);
	}

}