import java.util.ArrayList;
import java.util.List;

/**
 * Created by GastónAlejandro on 16/12/2016.
 * Created by Facu on 16/12/2016.
 */
public class Archivo {

    private Integer id;


    private String nombre;


    private String path;

    private List<Fila> listaDeFilas = new ArrayList<Fila>();
    private List<Columna> listaDeColumnas = new ArrayList<Columna>();

    public List<Columna> getListaDeColumnas() {
        return listaDeColumnas;
    }

    public void setListaDeColumnas(List<Columna> listaDeColumnas) {
        this.listaDeColumnas = listaDeColumnas;
    }

    public List<Fila> getListaDeFilas() {
        return listaDeFilas;
    }

    public void setListaDeFilas(List<Fila> listaDeFilas) {
        this.listaDeFilas = listaDeFilas;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre){this.nombre=nombre;}

    public String getPath() {
        return path;
    }
    public void setPath(String path){this.path=path;}
    public int getCantidadFilas(){
        return this.getListaDeFilas().size();
    }
    public Archivo() {
    }

    public Archivo(Integer id, String nombre, String path) {
        this.id=id;
        this.nombre = nombre;
        this.path = path;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [Id=" + id + ", Nombre=" + nombre + ", Path=" + path + "]";
    }

    public void agregarFila(Fila fila){
        getListaDeFilas().add(fila);
    }
    public void agregarColumna(Columna columna){ getListaDeColumnas().add(columna);}

}
