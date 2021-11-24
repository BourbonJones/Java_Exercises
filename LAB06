# Lab06 - O Mundo de Wumpus

# Arquivos Java do Jogo
[arquivo](./src/mc322/lab06)

# Destaques da Arquitetura
## Classe Componente
Classe Componente foi criada como classe mãe, a qual é herdada por todos os componentes específicos (heroi, brisa, e etc). Assim, qualquer componente novo pode ser criado com mínimas modificações no código porque o objeto desse novo componente pode ser passado para o ponterio do objeto componente já implementado e distribuido por todo o código da aplicação.
```
public class Heroi extends Componente
{
	int arrow;
	int gold;
	
	Heroi(int lin, int col, Caverna cav)
  ...	
}
```

## Classe Sala
Classe Sala resolve o problema automaticamente das prioridade de impressão dos simbolos. Então, a classe Caverna não precisa saber de nada sobre os detalhes da impressão. No cenário de um novo componente, seria só ajustar o metodo String getComSim() e todo o processo de impressão funcionaŕio corretamente.
```
public class Sala {	
...
	String getComSim()
	{
		if(visitada)
		{
			if(ouro != null)
				return ouro.sim;
			
			else if(buraco != null)
				return buraco.sim;
			
			else if(wumpus != null)
				return wumpus.sim;
			
			else if(heroi != null)
				return heroi.sim;
			
			else if(fedor != null)
				return fedor.sim;
			
			else if(brisa != null)
				return brisa.sim;
			
			else
				return "#";				
		}
		else
		{
			return "-";
		}
	}	
...
}
```
