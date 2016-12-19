import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Facu on 17/12/2016.
 */
public class Vista extends JFrame implements ActionListener{
    private JButton abrirArchivoButton;
    private JTable table1;
    private JPanel panel1;
    private JButton removerColumnaButton;
    private JList list1;
    private JButton cambiarCaracterSeparadorButton;
    private JLabel cantidadDeFilasLabel;
    private JButton crearNArchivosButton;
    private JLabel caracterSeparadorLabel;
    private JTextField textField1;
    private JButton filtrarFilasButton;
    private JButton verArchivoOriginalButton;
    private JButton guardarArchivoButton;
    private Controller controlador = new Controller();


    public Vista() {
        abrirArchivoButton.addActionListener(this);
        removerColumnaButton.addActionListener(this);
        cambiarCaracterSeparadorButton.addActionListener(this);
        crearNArchivosButton.addActionListener(this);
        filtrarFilasButton.addActionListener(this);
        verArchivoOriginalButton.addActionListener(this);
        guardarArchivoButton.addActionListener(this);
        getContentPane().add(panel1, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){
            e.printStackTrace();
        }
        Vista vista= new Vista();
        vista.setTitle("CsvSlicer");
        Image icon=new ImageIcon("CsvSlicerIcon.png").getImage();
        vista.setIconImage(icon);
        vista.pack();
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== abrirArchivoButton){
            try {
                table1.setModel(controlador.leerArchivo(seleccionarArchivo()));
                cargarLista();
                cantidadDeFilasLabel.setText("Cantidad de filas: " + table1.getRowCount());
                caracterSeparadorLabel.setText("Caracter separador: " + controlador.getCaracter());
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        } else if(e.getSource()==removerColumnaButton){
          try {  table1.setModel(controlador.removerColumna(list1.getSelectedValue().toString()));}
          catch (NullPointerException ex)
          {
            JOptionPane.showMessageDialog(null,"Por favor seleccione una Columna de la lista");
          }
            cargarLista();
        } else if(e.getSource()==cambiarCaracterSeparadorButton){
            try {
                controlador.cambiarCaracter(JOptionPane.showInputDialog("Ingrese el nuevo caracter separador: "));
                caracterSeparadorLabel.setText("Caracter separador: " + controlador.getCaracter());
                table1.setModel(controlador.leerArchivo(controlador.getArchivo().getPath()));
                cargarLista();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        } else if(e.getSource()==crearNArchivosButton){
            String cantFilas=JOptionPane.showInputDialog("Ingrese la cantidad de filas por archivo: ");
            try{
            controlador.crearNArchivos(Integer.parseInt(cantFilas), seleccionarDirectorio());}
            catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null,"Por favor introduzca un valor válido");
            }
        } else if(e.getSource()==filtrarFilasButton){
         if (textField1.getText().length()>0) {
             String[] valores = textField1.getText().split(controlador.getCaracter());

           try{
            table1.setModel(controlador.filtrarFilas(valores));}
            catch (NullPointerException ex){JOptionPane.showMessageDialog(null,"Asegúrese de tener un archivo cargado");}
         }
         else JOptionPane.showMessageDialog(null,"Por favor introduzca un valor válido");
        } else if(e.getSource()==verArchivoOriginalButton){
            try {
                table1.setModel(controlador.verOriginal());
                cargarLista();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }

        } else if(e.getSource()==guardarArchivoButton){
        try {            controlador.crearArchivo(seleccionarDirectorio());}
            catch (Exception ex){}
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

    private String seleccionarDirectorio(){
        String selectedDirectory="";
        JFileChooser fileChooser=new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result=fileChooser.showSaveDialog(new JPanel());
        if(result==JFileChooser.APPROVE_OPTION){
            selectedDirectory=fileChooser.getSelectedFile().getPath();
        }
        return selectedDirectory;
    }

    private void cargarLista(){
        DefaultListModel listModel=new DefaultListModel();
        for (int i=0;i<table1.getColumnCount();i++){
            listModel.addElement(table1.getColumnName(i));
        }
        list1.setModel(listModel);
    }
}
