package br.ufop.algoritmos.geneticos;

import java.util.ArrayList;

public class app {
	
	public static void main(String[] args) {
	  long tempoInicial;
	  
	  ArrayList<ResultadosExecucao> resultadosUmCorte = new ArrayList<>();
	  ArrayList<ResultadosExecucao> resultadosDoisCortes = new ArrayList<>();
	  ArrayList<ResultadosExecucao> resultadosBlender = new ArrayList<>();
	  
	  ResultadosExecucao results;
	  	
	  Problema problema = new Problema();
	
	  Integer tamanho = 50;
	  Double pCrossover = 0.015;
	  Double pMutacao = 0.025;
	  Integer geracoes = 300;
	  
	  Double minimo = -5.12;
	  Double maximo = 5.12;
	  Integer nVariaveis = 100;
	
	  AlgoritmoGenetico algoritmoGenetico = new AlgoritmoGenetico(tamanho, pCrossover, pMutacao, geracoes, problema, minimo, maximo, nVariaveis);
	  

//      results = algoritmoGenetico.executar(3);
	  
	  for (int j = 1; j<31; j++) {
	      tempoInicial = System.currentTimeMillis();
	      results = algoritmoGenetico.executar(1);
	      results.setTempoExecucao(System.currentTimeMillis() - tempoInicial);
	      resultadosUmCorte.add(results);
	      

	      tempoInicial = System.currentTimeMillis();
	      results = algoritmoGenetico.executar(2);
	      results.setTempoExecucao(System.currentTimeMillis() - tempoInicial);
	      resultadosDoisCortes.add(results);
	      
	      tempoInicial = System.currentTimeMillis();
	      results = algoritmoGenetico.executar(3);
	      results.setTempoExecucao(System.currentTimeMillis() - tempoInicial);
	      resultadosBlender.add(results);
	  }
	  
	  ResultadosExecucao.OrdenacaoPorMelhorIndividuo(resultadosUmCorte);
	  ResultadosExecucao.OrdenacaoPorMelhorIndividuo(resultadosDoisCortes);
	  ResultadosExecucao.OrdenacaoPorMelhorIndividuo(resultadosBlender);
	  
	  System.out.println("Resultados:");	  
	  System.out.println("\tMédia e Desvio Padrão do Custo: Um corte");
	  System.out.println("\t" + ResultadosExecucao.MediaCusto(resultadosUmCorte));
	  System.out.println("\t" + ResultadosExecucao.DesvioPadraoCusto(resultadosUmCorte));
	  System.out.println("\tMédia e Desvio Padrão do Tempo: Um corte");
	  System.out.println("\t" + ResultadosExecucao.MediaTempo(resultadosUmCorte) + "ms");
	  System.out.println("\t" + ResultadosExecucao.DesvioPadraoTempo(resultadosUmCorte));
	  System.out.println("\tMelhor População: " + resultadosUmCorte.get(0).getIndividuo().getVariaveis());
	  

	  System.out.println("Resultados:");	  
	  System.out.println("\tMédia e Desvio Padrão do Custo: Dois Cortes");
	  System.out.println("\t" + ResultadosExecucao.MediaCusto(resultadosDoisCortes));
	  System.out.println("\t" + ResultadosExecucao.DesvioPadraoCusto(resultadosDoisCortes));
	  System.out.println("\tMédia e Desvio Padrão do Tempo: Dois Cortes");
	  System.out.println("\t" + ResultadosExecucao.MediaTempo(resultadosDoisCortes) + "ms");
	  System.out.println("\t" + ResultadosExecucao.DesvioPadraoTempo(resultadosDoisCortes));
	  System.out.println("\tMelhor População: " + resultadosDoisCortes.get(0).getIndividuo().getVariaveis());
	  

	  System.out.println("Resultados:");	  
	  System.out.println("\tMédia e Desvio Padrão do Custo: Blender");
	  System.out.println("\t" + ResultadosExecucao.MediaCusto(resultadosBlender));
	  System.out.println("\t" + ResultadosExecucao.DesvioPadraoCusto(resultadosBlender));
	  System.out.println("\tMédia e Desvio Padrão do Tempo: Blender");
	  System.out.println("\t" + ResultadosExecucao.MediaTempo(resultadosBlender) + "ms");
	  System.out.println("\t" + ResultadosExecucao.DesvioPadraoTempo(resultadosBlender));
	  System.out.println("\tMelhor População: " + resultadosBlender.get(0).getIndividuo().getVariaveis());
	  
	}
	
}
