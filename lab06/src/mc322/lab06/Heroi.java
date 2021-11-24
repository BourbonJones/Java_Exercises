package mc322.lab06;

public class Heroi extends Componente
{
	int arrow;
	int gold;
	
	Heroi(int lin, int col, Caverna cav)
	{
		super(lin, col, cav);
		sim = "P";
		this.arrow = 1;
		this.gold = 0;
	}
	
	public void changeArrow (int x) {
		arrow = x;
	}
	
	public void changeGold (int x) {
		gold = x;
	}
	
	public int getArrow() {
		return arrow;
	}
	
	public int getGold() {
		return gold;
	}
	//estado arrow: 0 -> n tem, 1-> tem -> 2 -> equipado
	
}

