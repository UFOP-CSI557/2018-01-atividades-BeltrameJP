package ESRainhas;

import java.util.ArrayList;

public class appMain {
	public static void main(String[] args) {
		
		int quantTabuleiros = 50;
		int tamanhoTabuleiro = 35;
		double taxaMutacao = 0.4;
		double timeStart;
		int quantDecendentes = 100;
		int quantidadeVaria��o = 20;
		int numInteracoes = 10;
		int[][] tabuleiro = new int[tamanhoTabuleiro][tamanhoTabuleiro];
		
		
		ESTabuleiro esTabuleiro = new ESTabuleiro(tamanhoTabuleiro, quantTabuleiros, taxaMutacao, quantDecendentes);
		
		timeStart = System.currentTimeMillis();
		IndividuoTabuleiro individuoTabuleiro = esTabuleiro.executar();
		
		System.out.println("Tempo de Execu��o:" + (System.currentTimeMillis() - timeStart));
		
		
		for(int[] val:individuoTabuleiro.getCromossomos()) {
			tabuleiro[val[0]][val[1]] = 7;
		}
		
		for(int[] val : tabuleiro) {
			System.out.println("");
			for(int i=0;i<tamanhoTabuleiro;i++) {
				System.out.printf("%d ", val[i]);
			}
		}
		
		
		/*
		//Exibi��o das Intera��es
		ArrayList<CalculoResultados> listaDeResultados = new ArrayList<>();
		
		ArrayList<Double> mutacaoVariada = new ArrayList<>();
		
		for(int i=0;i<=quantidadeVaria��o;i++) {
			mutacaoVariada.add(0.2 + 0.8*((double)i/quantidadeVaria��o));
			listaDeResultados.add(new CalculoResultados(0.2 + 0.8*((double)i/quantidadeVaria��o)));
		}
		
		for(int j=0;j<numInteracoes;j++) {
			System.out.println("Intera��o " + j);
			for(int i=0;i<mutacaoVariada.size();i++) {
				timeStart = System.currentTimeMillis();
				
				ESTabuleiro esTabuleiro = new ESTabuleiro(tamanhoTabuleiro, quantTabuleiros, mutacaoVariada.get(i), quantDecendentes);
				IndividuoTabuleiro individuoTabuleiro = esTabuleiro.executar();
				
				listaDeResultados.get(i).addInteracao(System.currentTimeMillis()-timeStart);
			}
		}
		
		for(CalculoResultados val : listaDeResultados) {
			val.calcularDesvioPadraoTempo();
			val.calcularMediaTempo();
		}
		
		CalculoResultados.OrdenarPorMedia(listaDeResultados);
		
		System.out.println("Resultados M�dia:");
		for(CalculoResultados val : listaDeResultados) {
			System.out.printf("\n Valor Mutacao: %.2f - M�dia de Tempo: %.2f", val.getValorMutacao(), val.getMediaTempo());
		}
		
		CalculoResultados.OrdenarPorDesvioPadrao(listaDeResultados);
		
		System.out.println("\nResultados Desvio Padr�o:");
		for(CalculoResultados val : listaDeResultados) {
			System.out.printf("\n Valor Mutacao: %.2f - Desvio Padr�o: %.2f", val.getValorMutacao(), val.getDesvioPadraoTempo());
		}
		
		*/
		
		
	}
}
