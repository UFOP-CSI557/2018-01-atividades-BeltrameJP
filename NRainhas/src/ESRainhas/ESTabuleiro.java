package ESRainhas;

import java.util.Collections;
import java.util.Random;

public class ESTabuleiro {
	private int numeroTabuleiros;
	private int tamanhoTabuleiro;
	private int tamDecendentes;
	private double taxaMutacao;
	private ProblemaRainhas problemaRainhas;
	
	public ESTabuleiro(int tamanhoTabuleiro, int numeroTabuleiros, double taxaMutacao, int tamDecenentes) {
		super();
		this.numeroTabuleiros = numeroTabuleiros;
		this.taxaMutacao = taxaMutacao;
		this.tamanhoTabuleiro = tamanhoTabuleiro;
		this.tamDecendentes = tamDecenentes;
		problemaRainhas = new ProblemaRainhas(tamanhoTabuleiro);
	}
	
	public IndividuoTabuleiro executar() {
		PopulacaoTabuleiros populacaoTabuleiros = new PopulacaoTabuleiros(numeroTabuleiros, problemaRainhas);
		
		populacaoTabuleiros.criar();
		populacaoTabuleiros.avaliar();
		
		PopulacaoTabuleiros descendentes = new PopulacaoTabuleiros();
		descendentes.setTamanho(problemaRainhas.getDimensao());
		
		
		while (true) {
			
			for(int i=0;i<populacaoTabuleiros.getTamanho();i++) {
				for(int d=0;d<tamDecendentes/numeroTabuleiros;d++) {
					
					
					IndividuoTabuleiro filho = (IndividuoTabuleiro) populacaoTabuleiros.getIndividuoTabuleiros().get(i).clone();
					
					mutacaoRainha(filho, taxaMutacao);					
					ProblemaRainhas.calcularFuncaoObjetivo(filho);
					
					descendentes.getIndividuoTabuleiros().add(filho);
				}
			}
			
			populacaoTabuleiros.getIndividuoTabuleiros().addAll(descendentes.getIndividuoTabuleiros());
			
			Collections.sort(populacaoTabuleiros.getIndividuoTabuleiros());
			
			populacaoTabuleiros.getIndividuoTabuleiros()
								.subList(numeroTabuleiros, populacaoTabuleiros.getIndividuoTabuleiros()
								.size()).clear();
			
			descendentes.getIndividuoTabuleiros().clear();
			
			if(populacaoTabuleiros.getIndividuoTabuleiros().get(0).getFuncaoObjetivo() == 0) {
				return populacaoTabuleiros.getIndividuoTabuleiros().get(0);
			}			
		}
		
	}
	
	
	public static void mutacaoRainha(IndividuoTabuleiro individuoTabuleiro, double taxaMutacao) {
		Random rnd = new Random();
			
		for(int[] val: individuoTabuleiro.getTabuleiro()) {
			if(rnd.nextDouble() < taxaMutacao) {
				val[1] = rnd.nextInt(individuoTabuleiro.getDimensao());
			}
		}
	}
	
	
	public int getNumeroTabuleiros() {
		return numeroTabuleiros;
	}

	public void setNumeroTabuleiros(int numeroTabuleiros) {
		this.numeroTabuleiros = numeroTabuleiros;
	}

	public double getTaxaMutacao() {
		return taxaMutacao;
	}

	public void setTaxaMutacao(double taxaMutacao) {
		this.taxaMutacao = taxaMutacao;
	}
	
}
