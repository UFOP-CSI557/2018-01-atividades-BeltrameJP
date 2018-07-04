package ESRainhas;

import java.util.ArrayList;

public interface Individuo<T> extends Comparable<Individuo> {
    
    long getFuncaoObjetivo();
    void setFuncaoObjetivo(Double funcaoObjetivo);
    
    ArrayList<T> getCromossomos();
    void setCromossomos(ArrayList<T> cromossomos);
    
    void criar();
    Individuo<T> clone();    
}
