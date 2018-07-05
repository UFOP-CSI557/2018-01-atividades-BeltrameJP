package br.ufop.algoritmo.genetico.hibrido;

public class appMain {
	public static void main(String[] args) {
		
		int numVariaveis = 50;
		int tamanhoPopulacao = 100;
		int geracoes = 300;
		double min = -500;
		double max = 500;
		int numDecendentes = 200;
		double taxaMutacao = 0.2;
		double taxaCrossOver = 0.1;
		double timeStart;
		
		for(int i=0;i<30;i++) {
			timeStart = System.currentTimeMillis();
			ESHibrido esHibrido = new ESHibrido(tamanhoPopulacao, numVariaveis, numDecendentes, taxaMutacao, taxaCrossOver, min, max, geracoes);
			esHibrido.executar();
			System.out.println((i+1) + ";" + esHibrido.getPopulacao().getListaIndividuos().get(0).getFuncaoObjetivo() + ";" + (System.currentTimeMillis()-timeStart));			
		}
	}

}
