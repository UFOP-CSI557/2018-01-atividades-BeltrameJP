package br.ufop.algoritmos.geneticos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ResultadosExecucao implements Comparable{
	 
	private Individuo individuo;
	private Individuo piorIndividuo;
	private long tempoExecucao;
	
	public ResultadosExecucao(Individuo individuo, Individuo piorIndividuo) {
		super();
		this.individuo = individuo;
		this.piorIndividuo = piorIndividuo;
	}
	
	public Double getMelhorResultado() {
		return individuo.getFuncaoObjetivo();
	}
	
	public Double getPiorResultado() {
		return piorIndividuo.getFuncaoObjetivo();
	}	
	public Individuo getPiorIndividuo() {
		return piorIndividuo;
	}
	public void setPiorIndividuo(Individuo piorIndividuo) {
		this.piorIndividuo = piorIndividuo;
	}
	public Individuo getIndividuo() {
		return individuo;
	}
	public void setIndividuo(Individuo individuos) {
		this.individuo = individuos;
	}
	public long getTempoExecucao() {
		return tempoExecucao;
	}
	public void setTempoExecucao(long tempoExecucao) {
		this.tempoExecucao = tempoExecucao;
	}
	
	
	public static double MediaCusto(ArrayList<ResultadosExecucao> lista) {
		double result = 0;
		for(ResultadosExecucao val : lista) {
			result += val.getIndividuo().getFuncaoObjetivo();
		}
		return result/lista.size();
	}
	
	
	public static double DesvioPadraoCusto(ArrayList<ResultadosExecucao> lista) {
		double result = 0;
		double media = MediaCusto(lista);
		
		for(ResultadosExecucao val : lista) {
			result += Math.pow(val.getIndividuo().getFuncaoObjetivo() - media, 2);
		}
		
		return result/lista.size();
	}
	
	public static double MediaTempo(ArrayList<ResultadosExecucao> lista) {
		double result = 0;
		for(ResultadosExecucao val : lista) {
			result += val.getTempoExecucao();
		}
		return result/lista.size();
	}
	
	public static double DesvioPadraoTempo(ArrayList<ResultadosExecucao> lista) {
		double result = 0;
		double media = MediaCusto(lista);
		
		for(ResultadosExecucao val : lista) {
			result += Math.pow(val.getTempoExecucao() - media, 2);
		}
		
		return result/lista.size();
	}

	public static void OrdenacaoPorMelhorIndividuo(ArrayList<ResultadosExecucao> lista) {
		Collections.sort(lista, new Comparator<ResultadosExecucao>(){
			@Override
			public int compare(ResultadosExecucao arg0, ResultadosExecucao arg1) {
				if(arg0.getIndividuo().getFuncaoObjetivo() > arg1.getIndividuo().getFuncaoObjetivo()) {
					return 1;
				}else if(arg0.getIndividuo().getFuncaoObjetivo() < arg1.getIndividuo().getFuncaoObjetivo()){
					return -1;
				}
				return 0;
			}
			
		});
	}

	@Override
	public int compareTo(Object arg0) {

		return 0;
	}
	
	
	
}
