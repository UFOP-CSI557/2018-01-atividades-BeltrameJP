package br.ufop.algoritmo.genetico.hibrido;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Algoritmo hibrido, com Evolução Estratégica aplicado 
 * @author jp22_
 *
 */
public class ESHibrido {
	private Integer tamanhoPopulacao;
	private Integer numVariaveis;
	private Integer numDecendentes;
	private Double min, max;
	private Double taxaMutacao, taxaCrossOver;
	private ProblemaSchwefel problemaSchwefel;
	private Integer geracoes;
	private Populacao populacao;
	
	public ESHibrido(Integer tamanhoPopulacao, Integer numVariaveis, Integer numDecendentes, Double taxaMutacao, Double taxaCrossOver, Double min, Double max, Integer geracoes) {
		super();
		this.tamanhoPopulacao = tamanhoPopulacao;
		this.numVariaveis = numVariaveis;
		this.numDecendentes = numDecendentes;
		this.taxaMutacao = taxaMutacao;
		this.taxaCrossOver = taxaCrossOver;
		this.max = max;
		this.min = min;
		this.geracoes = geracoes;
		problemaSchwefel = new ProblemaSchwefel();
	}
	
	public void executar() {
		populacao = new Populacao(min, max, numVariaveis, tamanhoPopulacao, problemaSchwefel);
		Populacao descendentes = new Populacao();
		
		populacao.criar();
		populacao.avaliar();
		
		Random rnd = new Random();
		
		for(int i=0;i<geracoes;i++) {
			for(int j=0;j<tamanhoPopulacao;j++) {
				for (int d=0;d<numDecendentes/tamanhoPopulacao;d++) {
					IndividuoReal individuoReal = populacao.getListaIndividuos().get(j).clone();
					
					problemaSchwefel.calcularFuncaoObjetivo(individuoReal);
					
					
					mutacao(individuoReal);
					descendentes.getListaIndividuos().add(individuoReal);
					
				}
				
			}
			
			
			for(IndividuoReal val : populacao.getListaIndividuos()) {
				crossOver(val, descendentes.getListaIndividuos().get(rnd.nextInt(numDecendentes)));
			}
			
			
			populacao.getListaIndividuos().addAll(descendentes.getListaIndividuos());
			populacao.avaliar();
			
			Collections.sort(populacao.getListaIndividuos());
			
			populacao.getListaIndividuos().subList(tamanhoPopulacao, populacao.getListaIndividuos().size()).clear();
			
			descendentes.getListaIndividuos().clear();
		}
	}
	
	
	public void crossOver(IndividuoReal i1, IndividuoReal i2) {
		Random rnd = new Random();
		
		if(rnd.nextDouble() < taxaCrossOver) {
			int index = rnd.nextInt(numVariaveis);
			int toIndex = rnd.nextInt(numVariaveis);
			
			if(index > toIndex) {
				int flag = toIndex;
				toIndex = index;
				index = flag;
			}
			
			for(int i=index;i<toIndex;i++) {
				Double atenuacao = rnd.nextDouble();
				Double flag = atenuacao*i1.getCromossomos().get(0);
				i1.getCromossomos().set(index, atenuacao*i2.getCromossomos().get(index));
				i2.getCromossomos().set(index, flag);
			}
			
		}
	}
	
		
	
	public void mutacao(IndividuoReal individuoReal) {
		Random rnd = new Random();
		for(Double val : individuoReal.getCromossomos()) {
			if(rnd.nextDouble() <= taxaMutacao) {
				val *= rnd.nextDouble();
				
				if(rnd.nextBoolean()) {
					val *= -1;
				}
				
			}
		}
	}
	
	

	public Integer getTamanhoPopulacao() {
		return tamanhoPopulacao;
	}

	public void setTamanhoPopulacao(Integer tamanhoPopulacao) {
		this.tamanhoPopulacao = tamanhoPopulacao;
	}

	public Integer getNumVariaveis() {
		return numVariaveis;
	}

	public void setNumVariaveis(Integer numVariaveis) {
		this.numVariaveis = numVariaveis;
	}

	public Integer getNumDecendentes() {
		return numDecendentes;
	}

	public void setNumDecendentes(Integer numDecendentes) {
		this.numDecendentes = numDecendentes;
	}

	public Double getMin() {
		return min;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	public Double getMax() {
		return max;
	}

	public void setMax(Double max) {
		this.max = max;
	}

	public Double getTaxaMutacao() {
		return taxaMutacao;
	}

	public void setTaxaMutacao(Double taxaMutacao) {
		this.taxaMutacao = taxaMutacao;
	}

	public ProblemaSchwefel getProblemaSchwefel() {
		return problemaSchwefel;
	}

	public void setProblemaSchwefel(ProblemaSchwefel problemaSchwefel) {
		this.problemaSchwefel = problemaSchwefel;
	}

	public Integer getGeracoes() {
		return geracoes;
	}

	public void setGeracoes(Integer geracoes) {
		this.geracoes = geracoes;
	}

	public Double getTaxaCrossOver() {
		return taxaCrossOver;
	}

	public void setTaxaCrossOver(Double taxaCrossOver) {
		this.taxaCrossOver = taxaCrossOver;
	}

	public Populacao getPopulacao() {
		return populacao;
	}

	public void setPopulacao(Populacao populacao) {
		this.populacao = populacao;
	}
	
}
