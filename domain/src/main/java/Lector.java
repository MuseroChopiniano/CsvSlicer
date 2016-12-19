import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;


/**
 * Created by GastónAlejandro on 16/12/2016.
 */
public class Lector {

    private String caracterSeparador;

        public Archivo leerArchivo(String path) throws FileNotFoundException
    {
        Archivo archivo = new Archivo();

    /** Elementos para el manejo de archivos*/
        File archivoPorLeer = new File(path);
        FileReader lector = new FileReader(archivoPorLeer);
        BufferedReader br = new BufferedReader(lector);

        /**Carga de datos al archivo*/

        String linea;
                try {
                    int idFila= 0;
                    while ((linea = br.readLine()) != null)
                    {
                        String[] datosFila = linea.split(caracterSeparador);
                        if (idFila>0)
                        {
                            Fila fila=new Fila(idFila);
                            for (int i=0;i<datosFila.length;i++)
                            {
                                Celda celda= new Celda(i,datosFila[i]);
                                fila.agregarCelda(celda);
                            }
                            archivo.agregarFila(fila);
                        }
                        else
                        {
                            for (int i=0;i<datosFila.length;i++)
                            {
                                Columna columna= new Columna(i,datosFila[i]);
                                archivo.agregarColumna(columna);
                            }
                        }
                        idFila+=1;
                    }
                    archivo.setPath(path);
                    String nombre = archivoPorLeer.getName().substring(0,archivoPorLeer.getName().length()-4);
                    archivo.setNombre(nombre);
                }
                catch (Exception ex)
                {

                }
                return  archivo;
    }

    public void setCaracterSeparador(String caracterSeparador) {
        this.caracterSeparador = caracterSeparador;
    }

    public String getCaracterSeparador() {
        return caracterSeparador;
    }
}
