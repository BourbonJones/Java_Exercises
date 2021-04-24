package mc322.lab05;

public class Peca {
	String cor = "";
	int posLin = -1;
	int posCol = -1;
	
	Peca(String cor, int posLin, int posCol)
	{	
		this.cor = cor;
		this.posLin = posLin;
		this.posCol = posCol;
	}
	
	String verPeca()
	{
		return cor;
	}
	
	public boolean movimentaPeca(String trajeto, int linF, int colF)
	{
		boolean permissao = false;
		
		if(trajeto.equals("b-")) 				//lance normal
			permissao = true;
		else if(trajeto.equals("bp-"))			//come pe�a adversaria
			permissao = true;
		else if(trajeto.equals("p-"))			//lance normal
			permissao = true;
		else if(trajeto.equals("pb-"))			//come pe�a adversaria
			permissao = true;
		else
			
		if(permissao) 
		{
			posLin = linF;
			posCol = colF;	
		}
		
		return permissao;
	}
}