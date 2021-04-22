package mc322.lab04;

public class Tabuleiro {
	String[] tabuleiro;
	String[] comando;

	public Tabuleiro() {		//Creator
		this.tabuleiro = new String[]{
				"  PPP  \n", 
				"  PPP  \n",
				"PPPPPPP\n", 
				"PPP-PPP\n", 
				"PPPPPPP\n", 
				"  PPP  \n", 
				"  PPP  \n"};
		this.comando = null;
	}
	
	public String mudar_c(String x, int pos, char c) {
		String b= x;
	       char a[]= b.toCharArray();
	       a[pos]= c;
	       b= new String(a);	
		return b;
	}
	
	public String[] atualizar() {
		int s2 = comando[0].charAt(0)-97; 			//source 1
		int s1 = 7-(comando[0].charAt(1)-48); 		//source 2
		int t2 = comando[1].charAt(0)-97; 			//target 1
		int t1 = 7-(comando[1].charAt(1)-48); 		//target 2
		
	    System.out.print(s1 + "," + s2 + "," + t1 + "," + t2 + "\n");
		
	    //source -> tabuleiro[s1][s2] e target -> tabuleiro[t1][t2]
	    
	    tabuleiro[s1] = mudar_c(tabuleiro[s1], s2, '-');
	    tabuleiro[t1] = mudar_c(tabuleiro[t1], t2, 'P');
	 
	    if(s2 == t2)
	    	tabuleiro[(s1+t1)/2] = mudar_c(tabuleiro[(s1+t1)/2], t2, '-');
	    else
	    	tabuleiro[s1] = mudar_c(tabuleiro[t1], (s2+t2)/2, '-');
	    
	    
		return tabuleiro;
	}
	
	public void print_commands() {
		System.out.println("Source: " + comando[0]);
		System.out.println("Target: " + comando[1]);
	}
	
	public void print_board() {
		
		for(int i=0;i<tabuleiro.length; i++) { //linhas
			System.out.print(7-i);
			for(int j=0;j<8;j++)
				System.out.print(" " + tabuleiro[i].charAt(j));
		}
		
		System.out.println("  a b c d e f g \n"); //colunas
		
	}
	
	
	
	
	
	
	
	
	
}
