import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;


public class GUI
{   
    private JLabel label;
    private JButton button;
    private JFrame frame;
    
    public GUI(){
        JFrame frame = new JFrame("Wörter Bilden");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLayout(null);
        
        label = new JLabel("Start", JLabel.CENTER);
        
        button = new JButton("klick mich");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText("Spiel wird gestartet");
            }
        });
        
        JTextField textField = new JTextField(20);
        
        
        frame.setLayout(new BorderLayout());
        frame.add(label, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);
        frame.add(textField, BorderLayout.NORTH);
        
        frame.setVisible(true);
    }
}

