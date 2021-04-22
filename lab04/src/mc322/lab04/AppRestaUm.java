package mc322.lab04;

public class AppRestaUm {
	
	public static void executaJogo(String link) {
		
		//instanciando
		CSVReader comandos = new CSVReader();
		Tabuleiro board = new Tabuleiro();
		
		comandos.setDataSource(link);
		
		//aplicando as instruções
		System.out.println("Tabuleiro Inicial:");
		board.print_board();
		
		for(int i=0;i<comandos.requestCommands().length; i++) {
			board.comando = comandos.requestCommands()[i].split(":");
			board.atualizar();
			board.print_commands();
			board.print_board();
		}
	}
	
	
	
	public static void main(String args[]) {
		executaJogo("C:\\Users\\Pablo\\Desktop\\comandos.csv");		
	}
}
