package br.ufop.algoritmo.genetico.hibrido;

import java.util.ArrayList;

public class Populacao {
	
	private ArrayList<IndividuoReal> listaIndividuos;
	private Double min, max;
	private Integer numVariaveis;
	private Integer tamanhoPopulacao;
	private Problema<IndividuoReal> problema;
	
	public Populacao(Double min, Double max, Integer numVariaveis, Integer tamanhoPopulacao, Problema<IndividuoReal> problema) {
		super();
		this.min = min;
		this.max = max;
		this.numVariaveis = numVariaveis;
		this.tamanhoPopulacao = tamanhoPopulacao;
		this.problema = problema;
		listaIndividuos = new ArrayList<>();
	}
	
	
	
	public Populacao() {
		super();
		listaIndividuos = new ArrayList<>();
	}



	public void criar() {
		IndividuoReal individuoReal;
		for(int i=0;i<tamanhoPopulacao;i++) {
			individuoReal = new IndividuoReal(min, max, numVariaveis);
			individuoReal.criar();
			
			listaIndividuos.add(individuoReal);
		}
	}
	
	public void avaliar() {
		for(IndividuoReal val: listaIndividuos) {
			problema.calcularFuncaoObjetivo(val);
		}
	}

	public ArrayList<IndividuoReal> getListaIndividuos() {
		return listaIndividuos;
	}

	public void setListaIndividuos(ArrayList<IndividuoReal> listaIndividuos) {
		this.listaIndividuos = listaIndividuos;
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

	public Integer getNumVariaveis() {
		return numVariaveis;
	}

	public void setNumVariaveis(Integer numVariaveis) {
		this.numVariaveis = numVariaveis;
	}

	public Integer getTamanhoPopulacao() {
		return tamanhoPopulacao;
	}

	public void setTamanhoPopulacao(Integer tamanhoPopulacao) {
		this.tamanhoPopulacao = tamanhoPopulacao;
	}

	public Problema<IndividuoReal> getProblema() {
		return problema;
	}

	public void setProblema(Problema<IndividuoReal> problema) {
		this.problema = problema;
	}	

}
