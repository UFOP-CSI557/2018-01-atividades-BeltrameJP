/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufop.algoritmos.geneticos;

import br.ufop.algoritmos.geneticos.Individuo;

public class Problema implements Rastrigin{
    
    @Override
	public void calcularFuncaoObjetivo(Individuo individuo) {
		Double custo = 10.0*individuo.getVariaveis().size();
		
		for (Double val : individuo.getVariaveis()) {
			custo += Math.pow(val, 2)
					- 10.0*Math.cos(2*Math.PI*val);
		}
		
		individuo.setFuncaoObjetivo(custo);
	}
    
}
