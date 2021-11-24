package mc322.lab06;

public class Montador {
	Caverna cav = null;
	Componente heroi = null;
	
	Montador(String caminho_arquivo_csv)
	{
		CSVHandling csv = new CSVHandling(); 	
		
		csv.setDataSource(caminho_arquivo_csv);		
		
		String[][] comandos = csv.requestCommands();		
		
		cav = new Caverna();
		
		int n_ouro = 0;
		int n_heroi = 0;		
		int n_wumpus = 0;
		int n_buraco = 0;
		boolean simbolo_desconhecido = false;
		
		for(int i=0; i<comandos.length; i++)
		{
			String sim = comandos[i][1];
			if(sim.equals("P"))
				n_heroi += 1;
			
			else if(sim.equals("W"))
				n_wumpus += 1;

			else if(sim.equals("B"))
				n_buraco += 1;
			
			else if(sim.equals("O"))
				n_ouro += 1;
			
			else if(sim.equals("_"))
			{} // Nao faz nada
			
			else
			{
				simbolo_desconhecido = true;
				break;
			}
		}
		
		if((n_heroi == 1) && (n_wumpus == 1) && (n_ouro == 1) && (n_buraco >= 2) && (n_buraco <= 3) && (!simbolo_desconhecido))
		{
			for(int i=0; i<comandos.length; i++) 
			{
				int lin = Integer.parseInt(comandos[i][0].substring(0, 1)) - 1;
				int col = Integer.parseInt(comandos[i][0].substring(2, 3)) - 1;
				String sim = comandos[i][1];
			
				Componente componente = null;
			
				if(sim.equals("P"))
				{
					componente = new Heroi(lin, col, cav);	
					heroi = componente;
				}
				
				else if(sim.equals("W"))
					componente = new Wumpus(lin, col, cav);
				
				else if(sim.equals("B"))
					componente = new Buraco(lin, col, cav);
				
				else if(sim.equals("O"))
					componente = new Ouro(lin, col, cav);
				
				else
					componente = new Componente(lin, col, cav); // Nao faz nada		
				
				cav.adicionaComponente(componente);			
			}
		}
		
		else if(n_heroi != 1)
			System.out.println("Maximo de 1 heroi permitido");
		
		else if(n_wumpus != 1)
			System.out.println("Maximo de 1 Wumpus permitido");
		
		else if(n_ouro != 1)
			System.out.println("Maximo de 1 ouro permitido");
		
		else if( (n_buraco < 2) && (n_buraco > 3) )
			System.out.println("Minimo de 2 buracos e maximo de 3 buracos permitidos");
		
		else if(simbolo_desconhecido)
			System.out.println("Simbolo desconhecido: " + simbolo_desconhecido);
		
		else
			System.out.println("Verificao com BUG");
	}	
	
	
	Componente getHeroi()
	{
		return heroi;
	}
}