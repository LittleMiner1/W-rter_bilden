import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class SpielGUI {
    private JFrame frame;

    public SpielGUI() {
        frame = new JFrame("Wörter bilden");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLayout(null); // absolute Positionierung

        // --- Großer Rahmen für obere Sektion ---
        JPanel spielbereich = new JPanel();
        spielbereich.setLayout(null);
        spielbereich.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        spielbereich.setBounds(10, 10, 660, 240);  // umfasst Spieler1, Spieler2, Auswahl, Button
        frame.add(spielbereich);

        // --- Spieler 1 Panel ---
        JPanel spieler1 = new JPanel();
        spieler1.setLayout(null);
        spieler1.setBorder(new TitledBorder("Spieler 1"));
        spieler1.setBounds(20, 20, 300, 80);
        
        JLabel name1Label = new JLabel("Name");
        name1Label.setBounds(20, 20, 50, 20);
        spieler1.add(name1Label);
        
        JTextField name1Feld = new JTextField("Paul");
        name1Feld.setBounds(70, 20, 100, 20);
        spieler1.add(name1Feld);

        JLabel punkte1Label = new JLabel("Punkte");
        punkte1Label.setBounds(20, 50, 50, 20);
        spieler1.add(punkte1Label);
        
        JTextField punkte1Feld = new JTextField("0");
        punkte1Feld.setBounds(70, 50, 50, 20);
        punkte1Feld.setEditable(false);
        spieler1.add(punkte1Feld);

        spielbereich.add(spieler1);

        // --- Spieler 2 Panel ---
        JPanel spieler2 = new JPanel();
        spieler2.setLayout(null);
        spieler2.setBorder(new TitledBorder("Spieler 2"));
        spieler2.setBounds(340, 20, 300, 80);

        JLabel name2Label = new JLabel("Name");
        name2Label.setBounds(20, 20, 50, 20);
        spieler2.add(name2Label);
        
        JTextField name2Feld = new JTextField("Anna");
        name2Feld.setBounds(70, 20, 100, 20);
        spieler2.add(name2Feld);

        JLabel punkte2Label = new JLabel("Punkte");
        punkte2Label.setBounds(20, 50, 50, 20);
        spieler2.add(punkte2Label);
        
        JTextField punkte2Feld = new JTextField("0");
        punkte2Feld.setBounds(70, 50, 50, 20);
        punkte2Feld.setEditable(false);
        spieler2.add(punkte2Feld);

        spielbereich.add(spieler2);

        // --- Auswahl Panel ---
        JPanel auswahl = new JPanel();
        auswahl.setLayout(null);
        auswahl.setBorder(new TitledBorder("Auswahl Typ"));
        auswahl.setBounds(20, 120, 620, 70);
        
        JRadioButton typ1 = new JRadioButton("1: Lücken füllen");
        typ1.setBounds(20, 25, 150, 20);
        auswahl.add(typ1);

        JRadioButton typ2 = new JRadioButton("2: Buchstabenpuzzle");
        typ2.setBounds(325, 25, 200, 20);
        auswahl.add(typ2);

        ButtonGroup gruppe = new ButtonGroup();
        gruppe.add(typ1);
        gruppe.add(typ2);

        spielbereich.add(auswahl);
        
        //--- Neues Spiel Button ---
        JButton startButton = new JButton("Starte neues Spiel");
        startButton.setBackground(Color.LIGHT_GRAY);
        startButton.setForeground(Color.BLACK);
        startButton.setBounds(20, 200, 620, 30);
        spielbereich.add(startButton);
        
        // --- Spielfeld unten ---
        JTextField aktuellerSpieler = new JTextField("Paul");
        aktuellerSpieler.setBounds(20, 270, 100, 25);
        aktuellerSpieler.setEditable(false);
        frame.add(aktuellerSpieler);

        JLabel zugLabel = new JLabel("ist am Zug");
        zugLabel.setBounds(130, 270, 100, 25);
        frame.add(zugLabel);

        JLabel aufgabeLabel = new JLabel("Aufgabe:");
        aufgabeLabel.setBounds(20, 310, 100, 25);
        frame.add(aufgabeLabel);
        
        JLabel wortLabel = new JLabel("Bilde ein Wort aus:");
        wortLabel.setBounds(20, 340, 150, 25);
        frame.add(wortLabel);

        JTextField buchstabenFeld = new JTextField("U B S");
        buchstabenFeld.setBounds(140, 340, 200, 25);
        buchstabenFeld.setEditable(false);
        frame.add(buchstabenFeld);

        JLabel loesungLabel = new JLabel("deine Lösung");
        loesungLabel.setBounds(20, 380, 100, 25);
        frame.add(loesungLabel);

        JTextField loesungFeld = new JTextField();
        loesungFeld.setBounds(130, 380, 200, 25);
        frame.add(loesungFeld);

        JButton fertigButton = new JButton("Fertig");
        fertigButton.setBounds(340, 380, 100, 25);
        frame.add(fertigButton);

        // Fenster anzeigen
        frame.setVisible(true);
    }

    // Main-Methode zum Starten
    public static void main(String[] args) {
        new SpielGUI();
    }
}
