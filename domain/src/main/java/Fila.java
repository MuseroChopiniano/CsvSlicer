import java.util.ArrayList;
import java.util.List;

/**
 * Created by GastónAlejandro on 16/12/2016.
 */
public class Fila {

    int id;

    List<Celda> listaDeCeldas = new ArrayList<Celda>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Fila(int id) {
        this.id = id;
    }

    public List<Celda> getListaDeCeldas() {

        return listaDeCeldas;
    }


    public void setListaDeCeldas(List<Celda> listaDeCeldas) {
        this.listaDeCeldas = listaDeCeldas;
    }

    public void agregarCelda(Celda celda){
        getListaDeCeldas().add(celda);
    }

    /** TODO: qué parametro asginar al quitarCelda...id identificador de la posición de la celda o un objeto celda que mediante equals sacamos de la lista? */
        public void quitarCelda(){}
}
