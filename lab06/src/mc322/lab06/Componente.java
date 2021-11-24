package mc322.lab06;

public class Componente {
	String sim = "C";
	Caverna cav = null;
	
	int lin = -1;
	int col = -1;
	
	
	Componente(int lin, int col, Caverna cav)
	{
		this.lin = lin;
		this.col = col;
		this.cav = cav;
	}
	
	String getSim()
	{
		return sim;
	}

	public void changeArrow (int x) {} 
	
	public void changeGold (int x) {}
	
	public int getArrow() {return 0;}
	
	public int getGold() {return 0;}
}