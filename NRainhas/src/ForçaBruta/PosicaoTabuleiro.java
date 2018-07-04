package ForçaBruta;

public class PosicaoTabuleiro {
	int i,j;

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	@Override
	public String toString() {
		return "[i=" + i + ", j=" + j + "]";
	}
	
}
