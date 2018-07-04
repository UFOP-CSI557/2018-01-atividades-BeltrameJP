package ESRainhas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CalculoResultados {
	
	private Double valorMutacao;
	private ArrayList<Double> interacoes;
	private Double mediaTempo, desvioPadraoTempo;
	
	public CalculoResultados(double valorMutacao) {
		super();
		this.valorMutacao = valorMutacao;
		interacoes = new ArrayList<>();
	}

	public void addInteracao(Double valor) {
		interacoes.add(valor);
	}
	
	public void calcularMediaTempo() {
		mediaTempo = MediaTempo(interacoes);
	}
	
	public void calcularDesvioPadraoTempo() {
		desvioPadraoTempo = DesvioPadraoTempo(interacoes);
	}
	
	
	public static Double MediaTempo(ArrayList<Double> lista) {
		double result = 0;
		for(Double val : lista) {
			result += val;
		}
		return result/lista.size();
	}
	
	public static Double DesvioPadraoTempo(ArrayList<Double> lista) {
		double result = 0;
		double media = MediaTempo(lista);
		
		for(Double val : lista) {
			result += Math.pow(val - media, 2);
		}
		
		return result/lista.size();
	}
	
	public static void OrdenarPorMedia(ArrayList<CalculoResultados> lista) {
		Collections.sort(lista, new Comparator<CalculoResultados>() {
			@Override
			public int compare(CalculoResultados arg0, CalculoResultados arg1) {
				return arg0.getMediaTempo().compareTo(arg1.getMediaTempo());
			}
			
		});
	}
	
	public static void OrdenarPorDesvioPadrao(ArrayList<CalculoResultados> lista) {
		Collections.sort(lista, new Comparator<CalculoResultados>() {
			@Override
			public int compare(CalculoResultados arg0, CalculoResultados arg1) {
				return arg0.getDesvioPadraoTempo().compareTo(arg1.getDesvioPadraoTempo());
			}
			
		});
	}

	public double getValorMutacao() {
		return valorMutacao;
	}

	public void setValorMutacao(double valorMutacao) {
		this.valorMutacao = valorMutacao;
	}

	public ArrayList<Double> getInteracoes() {
		return interacoes;
	}

	public void setInteracoes(ArrayList<Double> interacoes) {
		this.interacoes = interacoes;
	}

	public Double getMediaTempo() {
		return mediaTempo;
	}

	public void setMediaTempo(double mediaTempo) {
		this.mediaTempo = mediaTempo;
	}

	public Double getDesvioPadraoTempo() {
		return desvioPadraoTempo;
	}

	public void setDesvioPadraoTempo(double desvioPadraoTempo) {
		this.desvioPadraoTempo = desvioPadraoTempo;
	}
	
}
