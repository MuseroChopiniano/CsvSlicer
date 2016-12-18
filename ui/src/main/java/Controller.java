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
    /**TODO: Definir toda la clase Controller*/
    private DefaultTableModel modelo;
    private Archivo archivo;

    public DefaultTableModel leerArchivo() throws FileNotFoundException {
              modelo=new DefaultTableModel();
        Lector lector= new Lector();
        archivo = lector.leerArchivo(seleccionarArchivo());
        cargarTabla(archivo);
        return modelo;
    }

    private void cargarTabla(Archivo archivo){
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

    private String seleccionarArchivo(){
        File selectedFile=null;
        JFileChooser fileChooser=new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(new JPanel());
        if (result==JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
        }
        return selectedFile.getPath();
    }

}
