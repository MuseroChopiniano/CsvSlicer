import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GastónAlejandro on 16/12/2016.
 */
public class Escritor {

    private  String caracterSeparador;

    public void setCaracterSeparador(String caracter)
    {
        this.caracterSeparador=caracter;
    }
    public  String getCaracterSeparador()
    {
        return this.caracterSeparador;
    }

public void crearArchivo(Archivo archivo,String path)
{
    File fileToCreate = new File(path);
    try {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToCreate));

        String linea="";

        for (int k=0;k<archivo.getListaDeColumnas().size();k++)
        {
            linea+=archivo.getListaDeColumnas().get(k).getNombre();
            if (k!=archivo.getListaDeColumnas().size()-1)
            {linea+= " " + caracterSeparador + " ";}
            else
                linea += "\r\n";
        }
        bufferedWriter.write(linea);
        for (int i=0; i<archivo.getListaDeFilas().size();i++)
        {
            linea="";
            for (int j=0; j<archivo.getListaDeFilas().get(i).getListaDeCeldas().size();j++)
            {
                linea+=archivo.getListaDeFilas().get(i).getListaDeCeldas().get(j).getValor();
             if (j!=archivo.getListaDeFilas().get(i).getListaDeCeldas().size()-1)
             {
                    linea += caracterSeparador;}
                    else linea += "\r\n";
            }

            bufferedWriter.write(linea);
        }
        bufferedWriter.close();
    } catch (IOException e) {
        e.printStackTrace();     /**TODO: MANEJO DE EXCEPCIONES!! ESTO LE DEBE SALTAR AL CLIENTE COMO UN MENSAJE DE ERROR NO COMO STACKTRACE*/
    }

}
public void crearNArchivos(Archivo archivo,int numeroFilas,String path)
{
    List<Archivo> lista = new ArrayList<Archivo>();
    int cantidadFilas=archivo.getCantidadFilas();
    int desde=0;
    int hasta=0;
        while (cantidadFilas>0 & numeroFilas!=0)
    {
        if (cantidadFilas>=numeroFilas)
        {
            hasta+=numeroFilas;
                    }
        else
        {    hasta+=cantidadFilas;}

        Archivo archivoNuevo = new Archivo();
        archivoNuevo.setListaDeColumnas(archivo.getListaDeColumnas());
        archivoNuevo.setListaDeFilas(archivo.getListaDeFilas().subList(desde,hasta));
        lista.add(archivoNuevo);
        archivoNuevo.setPath(path + "\\" +archivo.getNombre() + "Slice" + lista.size() +".csv");
        desde+=numeroFilas;
        cantidadFilas=cantidadFilas-numeroFilas;
    }
    for (int i=0;i<lista.size();i++)
    {
        crearArchivo(lista.get(i),lista.get(i).getPath());
    }

}
}
