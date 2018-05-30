/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecmodel;

import java.util.ArrayList;
import java.util.Collections;

import metodo.DEReal;
import problema.Problema;
import problema.ProblemaRastrigin;
import solucao.Individuo;
import solucao.ResultadosExecucao;

/**
 *
 * @author fernando
 */
public class DERealPrincipal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    	long tempoInicial;
    	
    	ArrayList<ResultadosExecucao> resultadosRand = new ArrayList<ResultadosExecucao>();
    	ArrayList<ResultadosExecucao> resultadosBest = new ArrayList<ResultadosExecucao>();
    	
        Double minimo = -5.12;
        Double maximo = 5.12;
        
        int D = 100;
        Problema problema = new ProblemaRastrigin(D);
        
        int gmax = 300;
        int Np = 100;
        
        double F = 0.001;
        double Cr = 0.8;
        
        DEReal deReal = new DEReal(minimo, maximo, problema, gmax, D, Np, F, Cr);
        
        //Numero de Execucões = 30 para cada Caso
        ArrayList<Integer> execucoes = new ArrayList<Integer>();
        for(int i=0;i<30;i++) {
        	execucoes.add(0);
        	execucoes.add(1);
        }
        //Aleatorização de Execucoes
        Collections.shuffle(execucoes);
        
        ResultadosExecucao results;
        
        for(Integer val : execucoes) {
        	if(val == 0) {
        		tempoInicial = System.currentTimeMillis();
        		results = new ResultadosExecucao(deReal.executar(val));
        		results.setTempoExecucao(System.currentTimeMillis() - tempoInicial);
        		resultadosRand.add(results);
        	}else {
        		tempoInicial = System.currentTimeMillis();
        		results = new ResultadosExecucao(deReal.executar(val));
        		results.setTempoExecucao(System.currentTimeMillis() - tempoInicial);
        		resultadosBest.add(results);
        	}
        }
        
        ResultadosExecucao.OrdenacaoPorMelhorIndividuo(resultadosBest);
        ResultadosExecucao.OrdenacaoPorMelhorIndividuo(resultadosRand);
        
        System.out.println("Resultados:");
        System.out.println("\tMédia e Desvio Padrão do Custo: Rand");
        System.out.println("\t" + ResultadosExecucao.MediaCusto(resultadosRand));
        System.out.println("\t" + ResultadosExecucao.DesvioPadraoCusto(resultadosRand));
        System.out.println("\tMédia e Desvio Padrão do Tempo: Rand");
        System.out.println("\t" + ResultadosExecucao.MediaTempo(resultadosRand));
        System.out.println("\t" + ResultadosExecucao.DesvioPadraoTempo(resultadosRand));
        System.out.println("\n");
        System.out.println("\tMédia e Desvio Padrão do Custo: Best");
        System.out.println("\t" + ResultadosExecucao.MediaCusto(resultadosBest));
        System.out.println("\t" + ResultadosExecucao.DesvioPadraoCusto(resultadosBest));
        System.out.println("\tMédia e Desvio Padrão do Tempo: Best");
        System.out.println("\t" + ResultadosExecucao.MediaTempo(resultadosBest));
        System.out.println("\t" + ResultadosExecucao.DesvioPadraoTempo(resultadosBest));
        
        
    }
    
}
