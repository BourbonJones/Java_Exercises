package mc322.lab06;

public class Sala {	
	Componente ouro = null;
	Componente brisa = null;
	Componente fedor = null;
	Componente heroi = null;
	Componente wumpus = null;
	Componente buraco = null;
	
	int lin = -1;
	int col = -1;
	
	boolean visitada = false;
	
	Sala(int lin, int col)
	{
		this.lin = lin;
		this.col = col;
	}
	
	void adicionaComponente(Componente componente) 
	{
		if(componente.getSim().equals("P"))
			heroi = componente;
		
		if(componente.getSim().equals("W"))
			wumpus = componente;
		
		if(componente.getSim().equals("B"))
			buraco = componente;
		
		if(componente.getSim().equals("O"))
			ouro = componente;
		
		if(componente.getSim().equals("b"))
			brisa = componente;
		
		if (componente.getSim().equals("f"))
			fedor = componente;		
	}
	
	String getComSim()
	{
		if(visitada)
		{
			if(ouro != null)
				return ouro.sim;
			
			else if(buraco != null)
				return buraco.sim;
			
			else if(wumpus != null)
				return wumpus.sim;
			
			else if(heroi != null)
				return heroi.sim;
			
			else if(fedor != null)
				return fedor.sim;
			
			else if(brisa != null)
				return brisa.sim;
			
			else
				return "#";				
		}
		else
		{
			return "-";
		}
	}
	
	void foiVisitada()
	{
		visitada = true;
	}
	
	boolean temBuraco()
	{
		if(buraco != null)
			return true;
		return false;
	}
	
	boolean temWumpus()
	{
		if(wumpus != null)
			return true;
		return false;
	}
	
	boolean temHeroi()
	{
		if(heroi != null)
			return true;
		return false;
	}
	
	boolean temOuro()
	{
		if(ouro != null)
			return true;
	return false;
	}
	
	boolean temBrisa()
	{
		if(brisa != null)
			return true;
	return false;
	}
	
	boolean temFedor()
	{
		if(fedor != null)
			return true;
	return false;
	}
	
}