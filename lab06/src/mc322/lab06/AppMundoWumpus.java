package mc322.lab06;

public class AppMundoWumpus {
	
	public static void main(String args[])
	{
		if(args.length == 1)
		{
			Montador mon = new Montador(args[0]);			
			
			//mon.cav.verEstadosCaverna();
			
			GameControl gc = new GameControl(mon.getHeroi());
			
			gc.comecar();
			
			
		}
		else
		{
			System.out.println("Informa apenas a caminho para o arquivo de entrada.");
		}

	}
}