package mc322.lab06;

public class Caverna {
	Sala matrix[][] = new Sala[4][4];
	
	Caverna()
	{
		for(int l=0; l<4; l++)
		{
			for(int c=0; c<4; c++)
			{
				matrix[l][c] = new Sala(l, c);
			}
		}
	}
	
	void adicionaComponente(Componente componente)
	{
		if(componente != null)
		{
			int lin = componente.lin;
			int col = componente.col;
			
			Sala sala = matrix[lin][col];
			sala.adicionaComponente(componente);
			
			if(componente.getSim().equals("P"))	
				sala.foiVisitada();			
			//sala.foiVisitada();
		}
	}
	
	
	void tentaAdicionarComponenteSecundario(Componente componente)
	{
		if(componente != null)
		{
			int lin = componente.lin;
			int col = componente.col;			
			
			if(lin >= 0 && lin < 4 && col >= 0 & col < 4)
			{
				Sala sala = matrix[lin][col];
				sala.adicionaComponente(componente);
			}
		}
	}
	
	
	void atualizaSalaDoHeroiNaCaverna(int lin_ant, int col_ant, int lin_nov, int col_nov) {
		
		//System.out.println("Sala: " + lin_ant + " " + col_ant);

		Sala sala = matrix[lin_ant][col_ant];
		Componente heroi = sala.heroi;
		sala.heroi = null;
		
		heroi.lin = lin_nov;
		heroi.col = col_nov;
		
		sala = matrix[lin_nov][col_nov];
		sala.heroi = heroi;
		sala.foiVisitada();
	}
	
	void retiraOuroDaSala(int lin_atual, int col_atual) {
		Sala sala = matrix[lin_atual][col_atual];
		sala.ouro = null;
	}
	
	void atualizaSalaQuandoWumpuesEMorto(int lin_atual, int col_atual) {
		Sala sala = matrix[lin_atual][col_atual];
		sala.wumpus = null;
	}
		
	void verEstadosCaverna()
	{		
		for(int l=0; l<5; l++)
		{
			for(int c=-1; c<4; c++)
			{
				if(c == -1 && l < 4)
					System.out.print((l+1) + " ");
				if(l == 4 && c > -1)
					System.out.print((c+1) + " ");
				if(l == 4 && c == -1)
					System.out.print(" " + " ");
				if(l >= 0 && l < 4 && c >= 0 & c < 4)
					System.out.print(matrix[l][c].getComSim() + " ");
			}
			System.out.print("\n");
		}
	}
	
	Sala getSala(int lin, int col) {
		return matrix[lin][col];
	}
}