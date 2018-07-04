package ESRainhas;

import java.util.ArrayList;

public class PopulacaoTabuleiros {
	public int tamanho;
	public ArrayList<IndividuoTabuleiro> individuoTabuleiros;
	public ProblemaRainhas problemaRainhas;
	
	
	public PopulacaoTabuleiros() {
		super();
		individuoTabuleiros = new ArrayList<>();
	}

	public PopulacaoTabuleiros(int tamanho, ProblemaRainhas problemaRainhas) {
		super();
		this.tamanho = tamanho;
		this.problemaRainhas = problemaRainhas;
		individuoTabuleiros = new ArrayList<>();
	}
	
	public void criar() {
		IndividuoTabuleiro individuoTabuleiro;
		for(int i=0;i<tamanho;i++) {
			individuoTabuleiro = new IndividuoTabuleiro(this.problemaRainhas.getDimensao());
			individuoTabuleiro.criar();
			
			individuoTabuleiros.add(individuoTabuleiro);
		}
	}
	
	public void avaliar() {
		for(IndividuoTabuleiro val : individuoTabuleiros) {
			ProblemaRainhas.calcularFuncaoObjetivo(val);
		}
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public ProblemaRainhas getProblemaRainhas() {
		return problemaRainhas;
	}

	public void setProblemaRainhas(ProblemaRainhas problemaRainhas) {
		this.problemaRainhas = problemaRainhas;
	}

	public ArrayList<IndividuoTabuleiro> getIndividuoTabuleiros() {
		return individuoTabuleiros;
	}

	public void setIndividuoTabuleiros(ArrayList<IndividuoTabuleiro> individuoTabuleiros) {
		this.individuoTabuleiros = individuoTabuleiros;
	}
	
}
