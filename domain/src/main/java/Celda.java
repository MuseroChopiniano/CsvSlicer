/**
 * Created by GastónAlejandro on 16/12/2016.
 */
public class Celda {
    private String valor;
    private int indice;

    public Celda(int indice,String valor) {
        this.indice=indice;
        this.valor = valor;

    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return this.valor;
    }
}
