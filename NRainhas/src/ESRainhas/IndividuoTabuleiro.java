package ESRainhas;

import java.util.ArrayList;
import java.util.Random;

public class IndividuoTabuleiro implements Comparable<IndividuoTabuleiro>{
		
	private ArrayList<int[]> tabuleiro;
	private int dimensao;
	private long funcaoObjetivo;

	public IndividuoTabuleiro(int dimensao) {
		super();
		this.dimensao = dimensao;
		tabuleiro = new ArrayList<>();
	}

	public int compareTo(IndividuoTabuleiro arg0) {
		// TODO Auto-generated method stub
		if(funcaoObjetivo > arg0.getFuncaoObjetivo()) {
			return 1;
		}else if(funcaoObjetivo == arg0.getFuncaoObjetivo()) {
			return 0;
		}else {
			return -1;
		}
	}

	public void criar() {
		// TODO Auto-generated method stub
		Random rng = new Random();
		
		for(int i=0;i<dimensao;i++) {
			int[] rainhaRandom = new int[2];
			rainhaRandom[0] = i;
			rainhaRandom[1] = rng.nextInt(dimensao);
			tabuleiro.add(rainhaRandom);
		}
	}

	public IndividuoTabuleiro clone() {
		IndividuoTabuleiro individuoTabuleiro = new IndividuoTabuleiro(dimensao);
		ArrayList<int[]> cloneTabuleiro = new ArrayList<int[]>();
		
		for(int[] val : tabuleiro) {
			int[] rainhaRandom = new int[2];
			rainhaRandom[0] = val[0];
			rainhaRandom[1] = val[1];
			cloneTabuleiro.add(rainhaRandom);
		}
		
		individuoTabuleiro.setTabuleiro(cloneTabuleiro);
		individuoTabuleiro.setFuncaoObjetivo(this.funcaoObjetivo);
		
		return individuoTabuleiro;
	}
	
	public String toString() {
		// TODO Auto-generated method stub
		String text = "";
		for(int i=0;i<tabuleiro.size();i++) {
			for(int j=0;i<tabuleiro.size();j++) {
				if(j == tabuleiro.get(i)[1]) {
					text += j + " ";
				}else {
					text += 0 + " ";
				}
			}
			text += "\n";
		}
		return text;
	}
	
	public ArrayList<int[]> getCromossomos() {
		// TODO Auto-generated method stub
		return tabuleiro;
	}

	public void setCromossomos(ArrayList<int[]> cromossomos) {
		// TODO Auto-generated method stub
		tabuleiro = cromossomos;
	}

	public ArrayList<int[]> getTabuleiro() {
		return tabuleiro;
	}
	

	public void setTabuleiro(ArrayList<int[]> tabuleiro) {
		this.tabuleiro = tabuleiro;
	}
	

	public int getDimensao() {
		return dimensao;
	}

	public void setDimensao(int dimensao) {
		this.dimensao = dimensao;
	}

	public long getFuncaoObjetivo() {
		// TODO Auto-generated method stub
		return funcaoObjetivo;
	}
	
	public void setFuncaoObjetivo(long funcaoObjetivo) {
		this.funcaoObjetivo = funcaoObjetivo;
	}
	
	public int[][] montarTabuleiro(){
		int[][] tabuleiroMatrix = new int[getDimensao()][getDimensao()];
		for(int[] val : tabuleiroMatrix) {
			for(int valor : val) {
				valor = 0;
			}
		}
		
		for(int[] val : tabuleiro) {
			tabuleiroMatrix[val[0]][val[1]] = 7;
		}
		
		return tabuleiroMatrix;
	}

}
