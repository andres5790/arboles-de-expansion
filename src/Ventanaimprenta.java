import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventanaimprenta {
    private JPanel impren;
    private JButton btnprim;
    private JButton btnkruskal;
    private JTextArea txtresultados;
    private ConexionImprenta red;


    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventanaimprenta");
        frame.setContentPane(new Ventanaimprenta().impren);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Ventanaimprenta() {
        ConexionImprenta red = new ConexionImprenta();
        red.predefinir();
        btnprim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                txtresultados.setText(red.prim());
            }
        });
        btnkruskal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                txtresultados.setText(red.kruskal());
            }
        });


    }
}
