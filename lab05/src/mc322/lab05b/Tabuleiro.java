package mc322.lab05b;

//import mc322.lab05b.Dama;
//import mc322.lab05b.Peca;
//import mc322.lab05b.Peao;

public class Tabuleiro {
	int maxLin = 8;
	int maxCol = 8;
	Peca matrix[][] = new Peca[maxLin][maxCol];
	
	String source = "Tabuleiro inicial";
	String target = "";
	
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
						matrix[i][j] = new Peao("p", i, j);
					}					
				}
				if(i >= 5)
				{
					if((i+j)%2 != 0)
					{
						matrix[i][j] = new Peao("b", i, j);
					}
				}
			}			
		}
	}
	
	
	String estadoDaPosicao(int lin, int col)
	{
		String estado = "";
		
		Peca peca = matrix[lin][col];
		if(peca != null)
		{
			estado += peca.getSimb();
		}
		else
		{
			estado += "-";
		}		
		return estado;
	}	

	void solicitaMovimento(String movimento)
	{
		int colI =     ((int)movimento.charAt(0) - 97); 
		int linI = 8 - ((int)movimento.charAt(1) - 48);
		int colF =     ((int)movimento.charAt(3) - 97); 
		int linF = 8 - ((int)movimento.charAt(4) - 48);	
		
		if(verificaConsistenciaMovimento(linI, colI, linF, colF, movimento))
		{
			String trajeto = levantaTrajeto(linI, colI, linF, colF);
					
			Peca peca = matrix[linI][colI];				
		
			if(   (peca.getTipo() == "peao" && peca.tentaMovimento(trajeto, linF, colF)) 
			   || (peca.getTipo() == "dama" && peca.tentaMovimento(minimizarTrajeto(trajeto), linF, colF)) )
		   {
				source = "Source: " + movimento.charAt(0) + movimento.charAt(1);
				target = "Target: " + movimento.charAt(3) + movimento.charAt(4);
				
				int colIn = (colF - colI)/Math.abs(colF - colI);
				int linIn = (linF - linI)/Math.abs(linF - linI);
				
				int lin = linI;
				int col = colI;				
				for(int i=1; i < trajeto.length(); i++) 
				{					
					lin += linIn;
					col += colIn;
					
					if(!trajeto.substring(i, i+1).equals("-")) 
					{
						//System.out.println(trajeto);
						//System.out.println(trajeto.substring(i, i+1));
					    matrix[lin][col] = null;
					    break;
					}
				}				

				matrix[linI][colI] = null;
				matrix[linF][colF] = peca;
				
				if(peca.getSimb().equals("b") && linF == 0) 
				{
					matrix[linF][colF] = new Dama("B", linF, colF);
				}
				else if(peca.getSimb().equals("p") && linF == 7)
				{
					matrix[linF][colF] = new Dama("P", linF, colF);
				}								
			}			
		}
	}
	
	boolean verificaConsistenciaMovimento(int linI, int colI, int linF, int colF, String movimento)
	{		
		// Verifica se há peça na posição
		if(matrix[linI][colI] == null)
		{
			System.out.println("Não há peça na posição: " + movimento);
			System.out.println("Estado do tabuleiro não atualizado");
			return false;
		}
		
		Peca peca = matrix[linI][colI];
	
		// Vetifica se o movimento vai pela diagonal
		if(Math.abs(colF - colI) != Math.abs(linF - linI))
		{
			System.out.println("Apenas jogadas na diagonal são válidas: " + movimento);			
			System.out.println("Estado do tabuleiro não atualizado");
			return false;
		}
		
		// Verifica se o movimeto não está fora do tabuleiro
		if(linI < 0 || colI < 0 || linI >= 8 || colI >= 8 ||
				linF < 0 || colF < 0 || linF >= 8 || colF >= 8)
		{			
			System.out.println("Apenas jogadas dentro do tabuleiro são válidas: " + movimento);			
			System.out.println("Estado do tabuleiro não atualizado");
			return false;			
		}				

		// Verifica se há lance obrigatório
		
		
		String cor = peca.getCor();
		String trajeto = levantaTrajeto(linI, colI, linF, colF);
		String trajeto_min = minimizarTrajeto(trajeto); // Minimização do trajeto
		String trajetos_min;
		String tipo_pecaobservada;
		
		for(int i=0; i<maxLin; i++)
		{
			for(int j=0; j<maxCol; j++)
			{	
				String[] trajetos = levantaTrajetosAteBordaDoTabuleiro(i, j);
		
				if(trajetos[0].substring(0,1).equals(cor.substring(0,1)))
				{
					for(int z=0; z<4; z++)
					{
						trajetos_min = minimizarTrajeto(trajetos[z]);
						
						if(trajetos_min.length() >= 3)
						{
							trajetos_min = minimizarTrajeto(trajetos[z]);
							//há trajeto_min e trajetos_min
							tipo_pecaobservada = matrix[i][j].getTipo();
							
							//Se algum lance for do tipo ab-
							if(trajetos_min.substring(0,3).equals(cor + outraCor(cor) + "-"))
							{
								//podera ser peao ou dama
								if(!trajeto_min.equals(trajetos_min.substring(0,3)))
								{
									System.out.println("ERRO 01");
									System.out.println("Jogada não permita. Há uma jogada obrigatária pendente :" + movimento);
									System.out.println("Estado do tabuleiro não atualizado");
									return false;	
								}									
							}
							//se algum lance for tipo a-b-
							//necessariamente será dama
							else if(tipo_pecaobservada.equals("dama"))
							{
								if(trajetos_min.length() >= 4) //isso é pra garantir q n e de erro na proxima comparacao
								{
									if(trajetos_min.substring(0,4).equals(cor + "-" + outraCor(cor) + "-"))
									{
										if(!trajeto_min.equals(trajetos_min.substring(0,4)))
										{
											System.out.println("ERRO 02");
											System.out.println("Jogada não permita. Há uma jogada obrigatária pendente :" + movimento);
											System.out.println("Estado do tabuleiro não atualizado");
											return false;	
										}
								//Somente estamos verificando opções de tomar pecas, tanto faz quem esteja tomando
								//Repare que estamos comparando os trajetos minimizados					
										
									}
								}
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	
	String outraCor(String cor)
	{
		if(cor.toLowerCase().equals("b"))
		{
			return "p";
		}
		return "b";
	}
	
	
	String levantaTrajeto(int linI, int colI, int linF, int colF)
	{
		int colD = colF - colI;
		int linD = linF - linI;
		
		int colIn = colD/Math.abs(colD);
		int linIn = linD/Math.abs(linD);		
		
		String trajeto = "";
		
		int col = colI;
		int lin = linI;
		while(true)
		{
			trajeto += estadoDaPosicao(lin, col);
			
			col += colIn;
			lin += linIn;			
		
			if(col == colF)
			{
				trajeto += estadoDaPosicao(lin, col);
				break;
			}
		}
		return trajeto;
	}
	
	
	String[] levantaTrajetosAteBordaDoTabuleiro(int lin, int col)
	{
		String[] trajetos = new String[4];
		trajetos[0] = "";
		trajetos[1] = "";
		trajetos[2] = "";
		trajetos[3] = "";		
		
		int l = lin;
		int c = col;		
		while(true)
		{
			trajetos[0] += estadoDaPosicao(l, c);
			l += 1;
			c += 1;
			if(l == maxLin || c == maxCol)
			{
				break;
			}
		}
		
		l = lin;
		c = col;
		while(true)
		{
			trajetos[1] += estadoDaPosicao(l, c);
			l -= 1;
			c += 1;
			if(l == -1 || c == maxCol)
			{
				break;
			}
		}
		
		l = lin;
		c = col;
		while(true)
		{
			trajetos[2] += estadoDaPosicao(l, c);
			l += 1;
			c -= 1;
			if(l == maxLin || c == -1)
			{
				break;
			}
		}
		
		l = lin;
		c = col;
		while(true)
		{
			trajetos[3] += estadoDaPosicao(l, c);
			l -= 1;
			c -= 1;
			if(l == -1 || c == -1)
			{
				break;
			}
		}		
		return trajetos;		
	}
	
	
	public String minimizarTrajeto(String trajeto) {
		String auxT = trajeto; //string auxiliar de trajeto
	    String auxTI = "";     //substring inicial de auxT
	    String auxTF = "";     //substring inicial de auxT
	    int i = 0;
		int a = 0;
			
	    //System.out.println("comeco: " + auxT);
		while(i < auxT.length())
		{
			if(auxT.charAt(i) == '-') 
			{
		        auxTI = auxT.substring(0, i+1);
		        if(i != auxT.length()-1)
		        {
				  a = i+1;
				  
		          while(auxT.charAt(a) == '-')
		          {
		            if(a == auxT.length()-1) 
		            {
		            	break;
		            }
		            a++;
		          }
		          
		          auxTF = auxT.substring(a);
		          
		          if(auxTF.equals("-"))
		          {
		            auxTF = "";
		          }
		          
		          auxT = auxTI + auxTF;
		          i = a-1;
		        }
		        i++;
			}
			else
			{
				i++;
			}
		}	
		return auxT;
	}
	
	String imprimirCabecalho(String estados)
	{
		estados += source + "\n";
		
		if(target.length() != 0)		
		{
			estados += target + "\n";
		}		
		return estados;
	}
	
	void imprimirTabuleiro()
	{
		String estados = "";
		
		estados = imprimirCabecalho(estados);
		
		for(int i=0; i<maxLin+1; i++)
		{
			for(int j=-1; j<maxCol; j++)
			{
				if(i >= 0 && j >= 0 && i < maxLin && j < maxCol)
				{
					estados += estadoDaPosicao(i,j);
					estados += " ";
				}
				if(i == maxLin && j != -1)
				{
					estados += String.valueOf((char)(j + 97));
					estados += " ";
				}
				if(i != maxLin && j == -1)
				{
					estados += (maxLin - i);
					estados += " ";
				}
				if(i == maxLin && j == -1)
				{
					estados += " ";
					estados += " ";
				}
			}
			estados += "\n";
		}
		//return estados.strip();
		System.out.println(estados);
	}

	
	void exportarArquivo()
	{
		String[] state = new String[64];
		int contador = 0;
		String col;
		String lin;
		
		for(int j=0; j<maxCol; j++)
		{
			for(int i=maxLin-1; i>=0; i--)
			{
				col = (char)(j+97) + "";
				lin = 8-i + "";
				
				if(matrix[i][j] != null)
					state[contador] = col + lin + matrix[i][j].simb;
				else
					state[contador] = col + lin + "_";
				contador++;
			}
		}
		for(int a=0; a<64; a++)
			System.out.println(a + " " + state[a]);
 	}	
	
}