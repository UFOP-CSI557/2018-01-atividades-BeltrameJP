/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecmodel;

import java.util.ArrayList;
import java.util.Collections;

import metodo.ESReal;
import problema.Problema;
import problema.ProblemaRastrigin;
import solucao.Individuo;
import solucao.ResultadosExecucao;

/**
 *
 * @author fernando
 */
public class ESRealMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
    	long tempoInicial;
    	
    	ArrayList<ResultadosExecucao> resultadosGaussiana = new ArrayList<ResultadosExecucao>();
    	ArrayList<ResultadosExecucao> resultadosAritmetico = new ArrayList<ResultadosExecucao>();

        Double minimo = -5.12;
        Double maximo = 5.12;
        
        Integer nVariaveis = 100;
        Problema problema = new ProblemaRastrigin(nVariaveis);

        // Parametros - ES
        Integer mu = 50; // Tamanho da populacao
        Integer lambda = 100; // numero de descendentes
        Integer geracoes = 300; // criterio de parada
        Double pMutacao = 0.05; // mutacao -	 aplicacao ao descendente - variacao/perturbacao

        ESReal esReal = new ESReal(minimo, maximo, nVariaveis, problema, mu, lambda, geracoes, pMutacao);
        
        ResultadosExecucao results;
        
      //Numero de Execucões = 30 para cada Caso
        ArrayList<Integer> execucoes = new ArrayList<Integer>();
        for(int i=0;i<30;i++) {
        	execucoes.add(0);
        	execucoes.add(1);
        }
        //Aleatorização de Execucoes
        Collections.shuffle(execucoes);
       
        for(Integer val: execucoes) {
        	if(val == 0) {
        		tempoInicial = System.currentTimeMillis();
        		results = new ResultadosExecucao(esReal.executar(val));
        		results.setTempoExecucao(System.currentTimeMillis() - tempoInicial);
        		resultadosGaussiana.add(results);
        	}else {
        		tempoInicial = System.currentTimeMillis();
        		results = new ResultadosExecucao(esReal.executar(val));
        		results.setTempoExecucao(System.currentTimeMillis() - tempoInicial);
        		resultadosAritmetico.add(results);
        	}
        }
        
        
        ResultadosExecucao.OrdenacaoPorMelhorIndividuo(resultadosGaussiana);
        ResultadosExecucao.OrdenacaoPorMelhorIndividuo(resultadosAritmetico);
        
        System.out.println("Resultados:");
        System.out.println("\tMédia e Desvio Padrão do Custo: Aritmetico");
        System.out.println("\t" + ResultadosExecucao.MediaCusto(resultadosAritmetico));
        System.out.println("\t" + ResultadosExecucao.DesvioPadraoCusto(resultadosAritmetico));
        System.out.println("\tMédia e Desvio Padrão do Tempo: Aritmetico");
        System.out.println("\t" + ResultadosExecucao.MediaTempo(resultadosAritmetico));
        System.out.println("\t" + ResultadosExecucao.DesvioPadraoTempo(resultadosAritmetico));
        System.out.println("\n");
        System.out.println("\tMédia e Desvio Padrão do Custo: Gaussiana");
        System.out.println("\t" + ResultadosExecucao.MediaCusto(resultadosGaussiana));
        System.out.println("\t" + ResultadosExecucao.DesvioPadraoCusto(resultadosGaussiana));
        System.out.println("\tMédia e Desvio Padrão do Tempo: Gaussiana");
        System.out.println("\t" + ResultadosExecucao.MediaTempo(resultadosGaussiana));
        System.out.println("\t" + ResultadosExecucao.DesvioPadraoTempo(resultadosGaussiana));
        
    }

}
