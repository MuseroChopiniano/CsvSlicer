import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

/**
 * Created by Facu on 17/12/2016.
 */
public class Vista extends JFrame implements ActionListener{
    private JButton button1;
    private JTextField textField1;
    private JTextField textField2;
    private JTable table1;
    private JPanel panel1;
    private JComboBox comboBox1;
    private JButton removerColumnaButton;
    private DefaultTableModel modelo;

    public Vista() {
        button1.addActionListener(this);
        removerColumnaButton.addActionListener(this);
        getContentPane().add(panel1, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Vista vista= new Vista();
        vista.pack();
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==button1){
            try {
                leerArchivo();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        } else if(e.getSource()==removerColumnaButton){
            removerColumna();
        }
    }

    public void leerArchivo() throws FileNotFoundException {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        modelo=new DefaultTableModel();
        Archivo archivo ;
        Lector lector= (Lector) context.getBean("lector");
        archivo = lector.leerArchivo(seleccionarArchivo());
        for (int i=0;i<archivo.getListaDeColumnas().size();i++){
            comboBox1.addItem(archivo.getListaDeColumnas().get(i).getNombre());
        }
        cargarTabla(archivo);
        table1.setModel(modelo);
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

    private void removerColumna(){
        for(int i=0;i<table1.getColumnCount();i++){
            if(table1.getColumnName(i)==comboBox1.getSelectedItem().toString()){
                TableColumn column=table1.getColumnModel().getColumn(i);
                table1.removeColumn(column);
            }
        }
    }
}
