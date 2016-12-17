import java.io.FileNotFoundException;

/**
 * Created by GastónAlejandro on 17/12/2016.
 */
public class Program {

/**TODO: Hay que hacer todo lo de UI*/

        static Archivo archivo;

    public static void main(String[] args) {

       /** Cambiar el path harcodeado para probar*/

        Lector lector=new Lector();
        try {
            archivo=lector.leerArchivo("C:\\Users\\GastónAlejandro\\Desktop\\archivo.csv");
            for (int i=0;i<archivo.getListaDeColumnas().size();i++)
            {
                System.out.print(archivo.getListaDeColumnas().get(i).getNombre() + "    ");
            }
            System.out.println("");
            for (int j=0;j<archivo.getListaDeFilas().size();j++)
            {
                for (int k=0;k<archivo.getListaDeFilas().get(j).getListaDeCeldas().size();k++)
                {
                    System.out.print(archivo.getListaDeFilas().get(j).getListaDeCeldas().get(k) + "    ");
                }
                System.out.println("");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }




    }

