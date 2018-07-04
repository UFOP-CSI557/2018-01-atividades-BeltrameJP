package ForçaBruta;
import java.util.Stack;

public class Tabuleiro {
	
	private Stack<int[]> stackMovimentos;
	private Stack<int[]> stackRetirada;
	
	private int numRainhas;
	private int[] rainhaTeste;
	
	private int tabuleiro[][];
	private int tam;
	
	private boolean isAtackNotPossible;
	
	public Tabuleiro(int tam) {
		super();
		this.tam = tam;
		stackMovimentos = new Stack<>();
		stackRetirada = new Stack<>();
		numRainhas = -1;
		isAtackNotPossible = false;
	}
	
	public void preencherTabuleiro() {
		tabuleiro = new int[tam][tam];
		for(int i=0;i<tam;i++) {
			for(int j=0;j<tam;j++) {
				tabuleiro[i][j] = 0;
			}
		}
	}
	
	
	public void resolverProblemaNovo() {
		while(true) {
			//Supoe que Nenhuma Rainha se Ataca por Enquanto
			isAtackNotPossible = false;
			rainhaTeste = new int[2];
			
			//Se está tudo vazio, é a primeira vez que o programa executa
			if(stackRetirada.isEmpty() && stackMovimentos.isEmpty()) {
				rainhaTeste[0] = 0; rainhaTeste[1] = 0;
				stackMovimentos.push(rainhaTeste);
			
			//É o caso que trata o Straight-Foward do programa
			}else if(stackRetirada.isEmpty()) {
				rainhaTeste[0] = stackMovimentos.peek()[0]+1;
				for(int i=0;i<tam;i++) {
					rainhaTeste[1] = i;
					
					for(int[] val : stackMovimentos) {
						isAtackNotPossible = testeAtaque(val,rainhaTeste);
						if(isAtackNotPossible) {
							break;
						}
					}
					
					if(!isAtackNotPossible) {
						stackMovimentos.push(rainhaTeste);
						stackRetirada.clear();
						break;
					}
					
				}
				
				if(isAtackNotPossible) {
					stackRetirada.push(stackMovimentos.pop());
				}
			
			//O caso que trata o Back-Track do Programa	
			}else if(!stackRetirada.empty()){
				rainhaTeste[0] = stackRetirada.peek()[0];
				isAtackNotPossible = true;
				
				for(int i=stackRetirada.peek()[1]+1;i<tam;i++) {
					rainhaTeste[1] = i;
					
					if(stackMovimentos.isEmpty()) {
						isAtackNotPossible = false;
					}else {
						for(int[] val : stackMovimentos) {
							isAtackNotPossible = testeAtaque(val,rainhaTeste);
							if(isAtackNotPossible) {
								break;
							}
						}
					}
					
					if(!isAtackNotPossible) {
						stackMovimentos.push(rainhaTeste);
						stackRetirada.clear();
						break;	
					}
				}
				
				if(isAtackNotPossible) {
					stackRetirada.push(stackMovimentos.pop());
				}
				
			}
			
			if(stackMovimentos.size() == tam) {
				for(int[] val : stackMovimentos) {
					tabuleiro[val[0]][val[1]] = 1;
				}
				break;
			}
		}
		
	}
	
	public boolean testeAtaque(int[] rainha1, int[] rainha2) {
		//Teste em Linha		
		if(rainha1[0] == rainha2[0]) {
			return true;
		}
		
		if(rainha1[1] == rainha2[1]) {
			return true;
		}
		//Teste na Diagonal
		
		for(int i=1;i<=tam;i++) {
			if(rainha1[0] + i < tam && rainha1[0]+i < tam) {
				if(rainha1[0] + i == rainha2[0] && rainha1[1] + i == rainha2[1]) {
					return true;
				}
			}
			
			if(rainha1[0] - i < tam && rainha1[0]+i < tam) {
				if(rainha1[0]- i == rainha2[0] && rainha1[1] + i == rainha2[1]) {
					return true;
				}
			}
			
			if(rainha1[0] + i < tam && rainha1[0]-i < tam) {
				if(rainha1[0]+i == rainha2[0] && rainha1[1]-i == rainha2[1]) {
					return true;
				}
			}
			
			if(rainha1[0] - i < tam && rainha1[0]-i < tam) {
				if(rainha1[0]-i == rainha2[0] && rainha1[1]-i == rainha2[1]) {
					return true;
				}
			}
		}
		
		
		return false;
	}
	
	
	
	public void printMatriz() {
		for(int i=0;i<tam;i++) {
			System.out.printf("\n");
			for(int j=0;j<tam;j++) {
				System.out.printf("%d ", tabuleiro[i][j]);
			}
		}
	}
	
	public String printVector(int[] vec) {
		String acum = "";
		for(int val: vec) {
			acum += val;
			acum += " ";
		}
		
		return acum;
	}
	
	public void printStack() {
		System.out.println("MovimentStack");
		for(int[] val:stackMovimentos) {
			System.out.println(printVector(val));
		}
	}
	
}
