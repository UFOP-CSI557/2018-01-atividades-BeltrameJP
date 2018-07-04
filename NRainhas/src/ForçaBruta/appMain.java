package ForçaBruta;

public class appMain {
	public static void main(String[] args){
		Tabuleiro tabuleiro = new Tabuleiro(8);
		double timeStart;
		
		timeStart = System.currentTimeMillis();
		tabuleiro.preencherTabuleiro();
		tabuleiro.resolverProblemaNovo();
		
		System.out.println("\n\nProblema Resolvido: " + (System.currentTimeMillis() - timeStart));
		tabuleiro.printMatriz();
		
	}
}
