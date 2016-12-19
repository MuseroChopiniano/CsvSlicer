import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GastónAlejandro on 17/12/2016.
 */
public class Controller {

    private DefaultTableModel modelo;
    private Archivo archivo;
    private  Lector lector;
    private  Escritor escritor;
    private Archivo archivoOriginal;

    public Archivo getArchivo() {
        return archivo;
    }

    public Controller(){
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        lector= (Lector) context.getBean("lector");
        escritor=(Escritor) context.getBean("escritor");
    }

    public DefaultTableModel leerArchivo(String path) throws FileNotFoundException {
        archivo = lector.leerArchivo(path);
        archivoOriginal=lector.leerArchivo(path);
        cargarTabla(archivo);
        return modelo;
    }

    public void cambiarCaracter(String caracter) throws FileNotFoundException {
        this.lector.setCaracterSeparador(caracter);
        this.escritor.setCaracterSeparador(caracter);
        if (this.archivo!=null){
        this.archivo=lector.leerArchivo(this.archivo.getPath());}
    }

    private void cargarTabla(Archivo archivo){
        modelo=new DefaultTableModel();
        for (int i = 0; i < archivo.getListaDeColumnas().size(); i++) {
            modelo.addColumn(archivo.getListaDeColumnas().get(i).getNombre());
        }
        for (int j = 0; j < archivo.getListaDeFilas().size(); j++) {
            List<Object> celdas = new ArrayList<Object>();
            for (int k = 0; k < archivo.getListaDeFilas().get(j).getListaDeCeldas().size(); k++) {
                celdas.add(archivo.getListaDeFilas().get(j).getListaDeCeldas().get(k).getValor());
            }
            Object[] fila = new Object[celdas.size()];
            for (int i = 0; i < celdas.size(); i++) {
                fila[i] = celdas.get(i);
            }
            modelo.addRow(fila);
        }
    }

    public DefaultTableModel removerColumna(String nombreColumna)
    {
        for (int i=0; i<archivo.getListaDeColumnas().size();i++)
        {
            if (archivo.getListaDeColumnas().get(i).getNombre()==nombreColumna)
            {
                archivo.getListaDeColumnas().remove(i);
                removerCeldas(i);
            }
        }
        cargarTabla(archivo);
        return modelo;
    }
    private void removerCeldas(int indice)
    {
        for (int i=0;i<archivo.getListaDeFilas().size();i++)
        {
            archivo.getListaDeFilas().get(i).getListaDeCeldas().remove(indice);
        }
    }
    public void crearNArchivos(int numeroFilas, String path){
        escritor.crearNArchivos(archivo,numeroFilas,path);
    }

    public void crearArchivo(String path){
        archivo.setPath(path + "\\" +archivo.getNombre() + "Slice.csv");
        escritor.crearArchivo(archivo, archivo.getPath());
    }

    public String getCaracter(){
        return lector.getCaracterSeparador();
    }

    public DefaultTableModel filtrarFilas(String[] valores){
        boolean coincidencia=false;
        for(int i=0;i<archivo.getListaDeFilas().size();){
            Fila fila=archivo.getListaDeFilas().get(i);
            coincidencia=false;

            for(int j=0;j<valores.length;j++){
               String valor= valores[j];
                if(fila.getListaDeCeldas().get(0).getValor().equals(valor)){
                   coincidencia=true;
                }
            }

            if(!coincidencia){
                archivo.getListaDeFilas().remove(i);
            } else{i++;}
        }
        cargarTabla(archivo);
        return modelo;
    }

    public DefaultTableModel verOriginal() throws FileNotFoundException {
        cargarTabla(archivoOriginal);
        archivo=lector.leerArchivo(archivoOriginal.getPath());
        return modelo;
    }
}
