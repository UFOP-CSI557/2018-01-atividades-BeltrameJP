/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufop.algoritmos.geneticos;
import java.util.Collections;
import java.util.Random;

public class AlgoritmoGenetico {

    // Tamanho da popula��o
    Integer tamanho;
    // Taxa de crossover - operador principal
    Double pCrossover;
    // Taxa de muta��o - operador secund�rio
    Double pMutacao;
    // Crit�rio de parada - n�mero de gera��es
    Integer geracoes;

    // Dados do problema
    // Problema - DeJong
    Problema problema;
    // M�nimo
    Double minimo;
    // M�ximo
    Double maximo;
    // Vari�veis
    Integer nVariaveis;

    public AlgoritmoGenetico(Integer tamanho, Double pCrossover, Double pMutacao, Integer geracoes, Problema problema, Double minimo, Double maximo, Integer nVariaveis) {
        this.tamanho = tamanho;
        this.pCrossover = pCrossover;
        this.pMutacao = pMutacao;
        this.geracoes = geracoes;
        this.problema = problema;
        this.minimo = minimo;
        this.maximo = maximo;
        this.nVariaveis = nVariaveis;
    }

    Populacao populacao;
    Populacao novaPopulacao;
    Individuo melhorSolucao;

    public Individuo getMelhorSolucao() {
        return melhorSolucao;
    }

    public ResultadosExecucao executar(int parametroCrossOver) {

        populacao = new Populacao(minimo, maximo, nVariaveis, tamanho, problema);
        novaPopulacao = new Populacao(minimo, maximo, nVariaveis, tamanho, problema);
        
        int g;

        // Criar a popula��o
        populacao.criar();

        // Avaliar
        populacao.avaliar();

        // Recuperar o melhor indiv�duo
        Random rnd = new Random();
        int ind1, ind2;

        // Enquanto o crit�rio de parada n�o for satisfeito - Gera��es
        for (g = 1; g <= geracoes; g++) {

            for (int i = 0; i < this.tamanho; i++) {
                // Crossover
                if (rnd.nextDouble() <= this.pCrossover) {
                    // Realizar a opera��o

                    ind1 = rnd.nextInt(this.tamanho);

                    do {
                        ind2 = rnd.nextInt(this.tamanho);
                    } while (ind1 == ind2);

                    Individuo desc1 = new Individuo(minimo, maximo, nVariaveis);
                    Individuo desc2 = new Individuo(minimo, maximo, nVariaveis);

                    // Progenitores
                    Individuo p1 = populacao.getIndividuos().get(ind1);
                    Individuo p2 = populacao.getIndividuos().get(ind2);
                    
                    int corte, corte2;
                    
                    switch(parametroCrossOver){
                    	case 1:
                            corte = rnd.nextInt(p1.getVariaveis().size());
                    		// Descendente 1 -> Ind1_1 + Ind2_2;
                            crossoverUmPonto(p1, p2, desc1, corte);

                            // Descendente 2 -> Ind2_1 + Ind1_2;
                            crossoverUmPonto(p2, p1, desc2, corte);
                            break;
                    	case 2:
                            corte = rnd.nextInt(p1.getVariaveis().size()-1) + 1;
                            corte2 = rnd.nextInt(p1.getVariaveis().size()-1) +1;

                            crossoverDoisPontos(p1, p2, desc1, corte, corte2);
                            crossoverDoisPontos(p2, p1, desc2, corte, corte2);
                            break;
                    	case 3:
                    		crossoverBlender(p1, p2, desc1);
                    		crossoverBlender(p2, p2, desc2);
                    		break;
                    	default:
                    		System.out.println("what");
                            
                    		
                    }
                    
                    mutacaoPorVariavel(desc1);
                    // Descendente 2
                    mutacaoPorVariavel(desc2);

                    // Avaliar as novas solu��es
                    problema.calcularFuncaoObjetivo(desc1);
                    problema.calcularFuncaoObjetivo(desc2);

                    // Inserir na nova popula��o
                    novaPopulacao.getIndividuos().add(desc1);
                    novaPopulacao.getIndividuos().add(desc2);
                }
            }

            // Definir sobreviventes -> populacao + descendentes
            // Merge: combinar pop+desc
            populacao.getIndividuos().addAll(novaPopulacao.getIndividuos());
            
            // Ordenar populacao;
            Collections.sort(populacao.getIndividuos());

            // Eliminar os demais individuos - criterio: tamanho da popula��o
            populacao.getIndividuos()
                    .subList(this.tamanho,
                            populacao.getIndividuos().size())
                    .clear();

            // Limpa a nova popula��o para a gera��o seguinte
            novaPopulacao.getIndividuos().clear();       
            
            //System.out.println("Geracao: " + g + " Custo: " + populacao.getIndividuos().get(0).getFuncaoObjetivo());
            
        }
    	
        return new ResultadosExecucao(populacao.getIndividuos().get(0), populacao.getIndividuos().get(populacao.getIndividuos().size()-1));
    }

    private void crossoverUmPonto(Individuo ind1, Individuo ind2, Individuo descendente, int corte) {
        // Crossover aritmetico - 1 ponto de corte
        Random rnd = new Random();
        Double alpha = rnd.nextDouble();

        // Ind1_1
        // alpha * P1
        for (int i = 0; i < corte; i++) {
            Double valor = alpha * ind1.getVariaveis().get(i);
            descendente.getVariaveis().add(valor);
        }

        // Ind2_2
        // (1 - alpha) * P2
        for (int i = corte; i < this.nVariaveis; i++) {
            Double valor = (1.0 - alpha) * ind2.getVariaveis().get(i);
            descendente.getVariaveis().add(valor);
        }
    }
    
    
    private void crossoverDoisPontos(Individuo ind1, Individuo ind2, Individuo descendente, int corte1, int corte2) {
    	Random rnd = new Random();
        Double alpha = rnd.nextDouble();
        
        if(corte1>corte2) {
        	int flag;
        	flag = corte1;
        	corte1 = corte2;
        	corte2 = flag;
        }
        
        for(int i=0; i<corte1;i++) {
            Double valor = (1-alpha) * ind1.getVariaveis().get(i);
            descendente.getVariaveis().add(valor);
        }
        
        for(int i=corte1; i<corte2;i++) {
            Double valor = alpha * ind2.getVariaveis().get(i);
            descendente.getVariaveis().add(valor);
        }
        
        for(int i=corte2; i< this.nVariaveis; i++) {
            Double valor = (1-alpha) * ind1.getVariaveis().get(i);
            descendente.getVariaveis().add(valor);
        }
        
    }
    
    private void crossoverBlender(Individuo ind1, Individuo ind2, Individuo descendente) {
    	Random rnd = new Random();
    	double alpha = 0.5;
    	double beta = rnd.nextDouble();
    	beta *= 1 + 2*alpha;
    	beta -= alpha;
    	double crossResult;
    	
    	for(int i=0;i<this.nVariaveis;i++) {
    		crossResult = ind1.getVariaveis().get(i) 
    				+ beta*(ind2.getVariaveis().get(i) - ind1.getVariaveis().get(i));
    		descendente.getVariaveis().add(crossResult);
    	}
    }
    
    
    
    
    

    private void mutacaoPorVariavel(Individuo individuo) {

        Random rnd = new Random();

        for (int i = 0; i < individuo.getVariaveis().size(); i++) {
            if (rnd.nextDouble() <= this.pMutacao) {
          
                // Mutacao aritmetica
                // Multiplicar rnd e inverter ou nao o sinal
                Double valor = individuo.getVariaveis().get(i);
                // Multiplica por rnd
                valor *= rnd.nextDouble();

                // Inverter o sinal
                if (!rnd.nextBoolean()) {
                    valor = -valor;
                }

                if (valor >= this.minimo
                        && valor <= this.maximo) {
                    individuo.getVariaveis().set(i, valor);

                }

            }
        }

    }
    
    
    private String nomeMetodoCrossOver(int flag) {
    	switch(flag) {
			case 1:
				return "Um ponto de Corte";
			case 2:
				return "Dois Pontos de Corte";
			case 3:
				return "Blender";
			default:
				return "Tem algo de Errado ai fera";
    	
    	}
    }

}
