package mc322.lab06;
import java.util.Random;

import java.util.Scanner;

public class GameControl {
	Componente heroi = null;
	String nome = null;
	int score = 0;
	
	GameControl(Componente heroi)
	{
		this.heroi = heroi;
	}
	
	void comecar()
	{	
		System.out.println("INICIO DE JOGO!!!\n");
		
		System.out.println("Qual o seu nome?");
		Scanner keyboard = new Scanner(System.in);
		nome = keyboard.nextLine();	
		Random rand = new Random();
		
		String comando = "";
		
		while(!comando.equals("q"))
		{
			
			heroi.cav.verEstadosCaverna();
			System.out.println("Player: " + nome);
			System.out.println("Score: " + score);
			
			System.out.println("\nINFORME SEU MOVIMENTO:");
			
			keyboard = new Scanner(System.in);
			comando = keyboard.nextLine();
			System.out.println();
			
			
			int lin_ant = heroi.lin;
			int col_ant = heroi.col;
			
			//System.out.println("Hero: " + heroi.lin +" "+ heroi.col);
			//System.out.println("Hero: " + lin_ant +" "+ col_ant);

			//TECLAS
			if(comando.equals("q")) {
				break;
			}
			else if(comando.equals("w")) 
			{
				int lin_nov = lin_ant - 1;
				int col_nov = col_ant;
				if(lin_nov >= 0 && lin_nov < 4 && col_nov >= 0 && col_nov < 4) {					
					heroi.cav.atualizaSalaDoHeroiNaCaverna(lin_ant, col_ant, lin_nov, col_nov);
					score -= 15;
				}
			}
			
			else if(comando.equals("s"))
			{
				int lin_nov = lin_ant + 1;
				int col_nov = col_ant;
				if(lin_nov >= 0 && lin_nov < 4 && col_nov >= 0 && col_nov < 4) {				
					heroi.cav.atualizaSalaDoHeroiNaCaverna(lin_ant, col_ant, lin_nov, col_nov);
					score -= 15;
				}
			}
			
			else if(comando.equals("d"))
			{
				int lin_nov = lin_ant;
				int col_nov = col_ant + 1;
				if(lin_nov >= 0 && lin_nov < 4 && col_nov >= 0 && col_nov < 4) {				
					heroi.cav.atualizaSalaDoHeroiNaCaverna(lin_ant, col_ant, lin_nov, col_nov);
					score -= 15;
				}
			}
			
			else if(comando.equals("a")) 
			{
				int lin_nov = lin_ant;
				int col_nov = col_ant - 1;
				if(lin_nov >= 0 && lin_nov < 4 && col_nov >= 0 && col_nov < 4) {			
					heroi.cav.atualizaSalaDoHeroiNaCaverna(lin_ant, col_ant, lin_nov, col_nov);
					score -= 15;
				}
			}
			
			else if(comando.equals("k")) {
				if(heroi.getArrow() != 0) {
					heroi.changeArrow(2);
					System.out.println("Flecha equipada");
				}
				else
					System.out.println("Você já gastou a sua flecha");
			}
			
			else if(comando.equals("c")) {
				Sala sala = heroi.cav.getSala(heroi.lin, heroi.col);	
				if(sala.temOuro()) {
					heroi.changeGold(1);
					heroi.cav.retiraOuroDaSala(heroi.lin, heroi.col);
					System.out.println("Ouro capturado");
				}
				else
					System.out.println("Não há ouro nesta sala");
			}
			
			else 
				System.out.println("Comando não mapeado: " + comando);
			
			//PÓS TECLAS
			Sala sala = heroi.cav.getSala(heroi.lin, heroi.col);
			
			
			//condicoes de vitoria
			if(sala.lin == 0 && sala.col == 0) {
				if(heroi.getGold() == 1) {
					System.out.println("PARABÉNS!!! Voce venceu\n");
					score += 1000;
					break;
				}		
			}
			
			//condicoes de derrota
			if(sala.temBuraco()) {
				System.out.println("Buraco!!! Voce perdeu");
				score -= 1000;
				break;	
			}
			else if(sala.temWumpus()) {
				if(heroi.getArrow() == 2) {
					System.out.println("Luta!!!");
					heroi.changeArrow(0);
					int numero = rand.nextInt(2);
					if(numero == 1) {
						System.out.println("Boa, voce matou o wumpus!");
						heroi.cav.atualizaSalaQuandoWumpuesEMorto(heroi.lin, heroi.col);
						score += 500;
					}
				}
				else {
					System.out.println("Morreu para o Wumpus! Voce perdeu");
					score -= 1000;
					break;
				}
				
			}
			
			//ADICIONAIS
			
			//Avisos: brisa, fedor, ouro
			if(!(comando.equals("k") || comando.equals("c"))) {
				//Atualizacao da flecha
				if(heroi.getArrow() == 2)
					heroi.changeArrow(0);
				
				if(sala.temBrisa()) 
					System.out.println("Aqui tem uma brisa, há um buraco perto");
				if(sala.temFedor()) 
					System.out.println("Aqui ta fedendo, Wumpus está por perto");
				if(sala.temOuro()) 
					System.out.println("Voce encontrou o ouro, aperte c para captura-lo");
			}
		}
		if(!comando.equals("q")) {
			heroi.cav.verEstadosCaverna();
			System.out.println("Player: " + nome);
			System.out.println("Score: " + score + "\n");
		}
		keyboard.close();
		System.out.println("FIM DE JOGO!!!\n");
	}
}