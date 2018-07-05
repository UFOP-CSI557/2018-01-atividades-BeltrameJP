package br.ufop.algoritmo.genetico.hibrido;

import java.util.ArrayList;
import java.util.Random;

public class IndividuoReal implements Comparable<IndividuoReal>{

    private ArrayList<Double> cromossomos;
    private Double funcaoObjetivo;
    private Double min,max;
    private Integer numVariaveis;
    
	public IndividuoReal(Double min, Double max, Integer numVariaveis) {
		super();
		cromossomos = new ArrayList<>();
		this.min = min;
		this.max = max;
		this.numVariaveis = numVariaveis;
	}
	

	public IndividuoReal(Double funcaoObjetivo, Double min, Double max, Integer numVariaveis) {
		super();
		this.funcaoObjetivo = funcaoObjetivo;
		this.min = min;
		this.max = max;
		this.numVariaveis = numVariaveis;
	}



	public void criar() {
		Random rnd = new Random();
		
		for(int i=0;i<numVariaveis;i++) {
			cromossomos.add(this.min + rnd.nextDouble()*(this.max-this.min));
		}
		
	}
	
	public IndividuoReal clone() {
		IndividuoReal individuoReal = new IndividuoReal(funcaoObjetivo, min, max, numVariaveis);
		
		ArrayList<Double> cromossomosClone = new ArrayList<>();
		for(Double val : this.cromossomos) {
			cromossomosClone.add(val);
		}
		
		individuoReal.setCromossomos(cromossomosClone);
		
		return individuoReal;		
	}
	
	@Override
	public int compareTo(IndividuoReal arg0) {
		// TODO Auto-generated method stub
		return funcaoObjetivo.compareTo(arg0.getFuncaoObjetivo());
	}
    
	
	public ArrayList<Double> getCromossomos() {
		return cromossomos;
	}

	public void setCromossomos(ArrayList<Double> cromossomos) {
		this.cromossomos = cromossomos;
	}

	public Double getFuncaoObjetivo() {
		return funcaoObjetivo;
	}

	public void setFuncaoObjetivo(Double funcaoObjetivo) {
		this.funcaoObjetivo = funcaoObjetivo;
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

}
