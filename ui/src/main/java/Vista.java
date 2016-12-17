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
    private JTable table1;
    private JPanel panel1;
    private JButton removerColumnaButton;
    private JList list1;
    private JTextField cantidadDeFilasTextField;
private Controller controlador = new Controller();

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
               table1.setModel(controlador.leerArchivo());
                cargarLista();
                cantidadDeFilasTextField.setText(String.valueOf(table1.getRowCount()));
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        } else if(e.getSource()==removerColumnaButton){
            removerColumna();
        }
    }

    private void removerColumna(){
        for(int i=0;i<table1.getColumnCount();i++){
            if(table1.getColumnName(i)==list1.getSelectedValue().toString()){
                TableColumn column=table1.getColumnModel().getColumn(i);
                table1.removeColumn(column);
                cargarLista();
            }
        }
    }

    private void cargarLista(){
        DefaultListModel listModel=new DefaultListModel();
        for (int i=0;i<table1.getColumnCount();i++){
            listModel.addElement(table1.getColumnName(i));
        }
        list1.setModel(listModel);
    }
}
