package br.ufop.algoritmo.genetico.hibrido;

public class ProblemaSchwefel implements Problema<IndividuoReal>{
	
	public void calcularFuncaoObjetivo(IndividuoReal individuo) {
		Double acum = 418.9829*individuo.getCromossomos().size();
		
		for(Double val: individuo.getCromossomos()) {
			acum -= val*Math.sin(Math.sqrt(Math.abs(val)));
		}
		individuo.setFuncaoObjetivo(acum);
	}

}
