package mc322.lab05;

public class Tabuleiro {
	int maxLin = 8;
	int maxCol = 8;
	public Peca matrixPeca[][] = new Peca[maxLin][maxCol];
	public Dama matrixDama[][] = new Dama[maxLin][maxCol];
	
	Tabuleiro()	
	{
		for(int i=0; i<maxLin; i++)
		{
			for(int j=0; j<maxCol; j++)
			{
				if(i < 3)
				{
					if((i+j)%2 != 0)
					{
						matrixPeca[i][j] = new Peca("p", i, j);	
					}					
				}
				if(i >= 5)
				{
					if((i+j)%2 != 0)
					{
						matrixPeca[i][j] = new Peca("b", i, j);	
					}
				}
			}			
		}		
	}
	
	
	//Lembrar de 2 coisas:
	//quando for obrigatorio comer
	//Teremos q avaliar se os lances sao alternados?
	void fazJogada(String jogada)
	{
		int colI = ((int)jogada.charAt(0) - 97); 
		int linI = 8-((int)jogada.charAt(1)- 48);
		int colF = ((int)jogada.charAt(3) - 97); 
		int linF = 8-((int)jogada.charAt(4)- 48);
		
		System.out.println("source: " + jogada.charAt(0) + jogada.charAt(1));
		System.out.println("target: " + jogada.charAt(3) + jogada.charAt(4));
		//Coloquei os prints aqui, se quiser muda-los de lugar, c q sabe
		
		
		String trajeto = fazTrajeto(linI, colI, linF, colF); 
		
		
		Peca peca = matrixPeca[linI][colI];
		Dama dama = matrixDama[linI][colI];
	
		boolean respostaPeca = false;
		boolean respostaDama = false;
		
		if(peca != null)
		{
			respostaPeca = peca.movimentaPeca(trajeto, linF, colF);			
		}
		if(dama != null)
		{
			respostaDama = dama.movimentaDama(trajeto, linF, colF);
		}
		if(peca == null && dama == null)
		{
			System.out.println("Não há peça na posição: " + jogada);
		}	
		
		if(respostaPeca)
		{
			if(trajeto.length() > 2)			//Se for movimento de tomada de peca
			{
			matrixPeca[(linI+linF)/2][(colI+colF)/2] = null; //a media retorna a casa entre a inicial e a final
			}
			matrixPeca[linI][colI] = null;
			matrixPeca[linF][colF] = peca;
			
		}
		if(respostaDama)
		{
			//movimentação da dama
		}		
	}
	
	String fazTrajeto(int linI, int colI, int linF, int colF)
	{
		int colDelta = colF - colI;
		int linDelta = linF - linI;
		
		int colIncremento = colDelta/Math.abs(colDelta);
		int linIncremento = linDelta/Math.abs(linDelta);		
		
		String trajeto ="";
		
		int col = colI;
		int lin = linI;
		while(true)
		{
			trajeto += estadoPosicao(lin, col);
			
			col += colIncremento;
			lin += linIncremento;
			
			//System.out.println("l: " + lin + " c: " + col);
			
			if(col == colF)
			{
				trajeto += estadoPosicao(lin, col);
				break;
			}
		}
		return trajeto;
	}
	
	String estadoPosicao(int lin, int col)
	{
		String estado = "";
		
		Peca peca = matrixPeca[lin][col];
		Dama dama = matrixDama[lin][col];
		if(peca != null)
		{
			estado += peca.verPeca();
		}
		if(dama != null)
		{
			estado += dama.verDama();
		}
		if(peca == null && dama == null)
		{
			estado += "-";
		}
		
		return estado;
	}
	
	String estadoTabuleiro()
	{
		String estado = "";
		for(int i=0; i<maxLin; i++)
		{
			for(int j=0; j<maxCol; j++)
			{
				estado += estadoPosicao(i,j);
			}
			estado += "\n";
		}
		return estado;
	}
	
	String estadoComBordasTabuleiro()
	{
		String estado = "";
		for(int i=0; i<maxLin+1; i++)
		{
			for(int j=-1; j<maxCol; j++)
			{
				if(i >= 0 && j >= 0 && i < maxLin && j < maxCol)
				{
					estado += estadoPosicao(i,j);
				}
				if(i == maxLin && j != -1)
				{
					estado += String.valueOf((char)(j + 97));
				}
				if(i != maxLin && j == -1)
				{
					estado += (maxLin - i);
				}
				if(i == maxLin && j == -1)
				{
					estado += " "; 
				}
			}
			estado += "\n";
		}
		return estado;
	}
	
	public static void main(String[] args){		
		CSVReader csv = new CSVReader();	
		csv.setDataSource("C:\\Users\\Pablo\\Desktop\\comandos.csv");		
		
		Tabuleiro tabuleiro = new Tabuleiro();
		
		System.out.println(tabuleiro.estadoComBordasTabuleiro());
		
		String jogadas[] = csv.requestCommands();
		
		for(int i=0; i<jogadas.length; i++)
		{
			tabuleiro.fazJogada(jogadas[i]);
			System.out.println(tabuleiro.estadoComBordasTabuleiro());
		}
	}
}