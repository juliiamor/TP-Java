package Discográfica;

import javax.swing.*;

public class Pantalla extends JFrame{   //Hereda de JFrame

    private JPanel ventana;
    private JList list1;
    private JButton artistasButton;
    private JTextField textField1;
    private JButton facturarButton;
    private JTextField textField2;
    private JTextField textField3;
    private JButton consultarButton;
    private JTextField textField4;
    private JButton bajaButton;
    private JTextField textField5;
    private JButton top10Button;
    private JTextField textField6;
    private JButton discosButton;

    public Pantalla(){
        add(ventana);
        this.setSize(1000,700);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
