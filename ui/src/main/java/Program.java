import java.io.FileNotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by GastónAlejandro on 17/12/2016.
 */
public class Program {

/**TODO: Hay que hacer todo lo de UI*/



    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        Archivo archivo ;
       /** Cambiar el path harcodeado para probar*/

        Lector lector= (Lector) context.getBean("lector");
        try {
            archivo=lector.leerArchivo("C:\\Users\\Facu\\Desktop\\Java\\PruebaConPath.csv");
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

