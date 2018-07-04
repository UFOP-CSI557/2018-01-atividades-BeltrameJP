package ESRainhas;

public class ProblemaRainhas {
	
	private int dimensao;
	
	public ProblemaRainhas(int dimensao) {
		super();
		this.dimensao = dimensao;
	}

	public static void calcularFuncaoObjetivo(IndividuoTabuleiro individuoTabuleiro) {
		int conflitos = 0;
		
		for(int i=0; i<individuoTabuleiro.getDimensao();i++) {
			for(int j=i+1;j<individuoTabuleiro.getDimensao();j++){
				if(testeAtaque(individuoTabuleiro.getTabuleiro().get(i), individuoTabuleiro.getTabuleiro().get(j), individuoTabuleiro.getDimensao())) {
					conflitos++;
				}
			}
		}
		individuoTabuleiro.setFuncaoObjetivo(conflitos);
	}
	
	public static boolean testeAtaque(int[] rainha1, int[] rainha2, int tam) {
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

	public int getDimensao() {
		return dimensao;
	}

	public void setDimensao(int dimensao) {
		this.dimensao = dimensao;
	}
	
}
