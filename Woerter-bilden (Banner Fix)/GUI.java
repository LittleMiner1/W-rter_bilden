import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JFrame frame;

    private JTextField name1Feld;
    private JTextField punkte1Feld;
    private JTextField name2Feld;
    private JTextField punkte2Feld;

    private JRadioButton typ1;
    private JRadioButton typ2;

    private JTextField aktuellerSpieler;
    private JLabel meldungLabel;

    private JLabel aufgabeLabel;
    private JTextField buchstabenFeld;
    private JTextField loesungFeld;

    // --- Neuer Banner-Bereich ---
    private JPanel bannerPanel;
    private JLabel bannerText;
    private JButton bannerOkButton;

    private Steuerung steuerung;

    // --- Neues Spiel Button ---
    private JButton startButton;

    public GUI() {
        frame = new JFrame("Wörter bilden");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLayout(null); // absolute Positionierung

        // --- Großer Rahmen für obere Sektion ---
        JPanel spielbereich = new JPanel();
        spielbereich.setLayout(null);
        spielbereich.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        spielbereich.setBounds(10, 10, 660, 240);
        frame.add(spielbereich);

        // --- Spieler 1 Panel ---
        JPanel spieler1 = new JPanel();
        spieler1.setLayout(null);
        spieler1.setBorder(new TitledBorder("Spieler 1"));
        spieler1.setBounds(20, 20, 300, 80);

        JLabel name1Label = new JLabel("Name");
        name1Label.setBounds(20, 20, 50, 20);
        spieler1.add(name1Label);

        name1Feld = new JTextField("");
        name1Feld.setBounds(70, 20, 100, 20);
        spieler1.add(name1Feld);

        JLabel punkte1Label = new JLabel("Punkte");
        punkte1Label.setBounds(20, 50, 50, 20);
        spieler1.add(punkte1Label);

        punkte1Feld = new JTextField("0");
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

        name2Feld = new JTextField("");
        name2Feld.setBounds(70, 20, 100, 20);
        spieler2.add(name2Feld);

        JLabel punkte2Label = new JLabel("Punkte");
        punkte2Label.setBounds(20, 50, 50, 20);
        spieler2.add(punkte2Label);

        punkte2Feld = new JTextField("0");
        punkte2Feld.setBounds(70, 50, 50, 20);
        punkte2Feld.setEditable(false);
        spieler2.add(punkte2Feld);

        spielbereich.add(spieler2);

        // --- Auswahl Panel ---
        JPanel auswahl = new JPanel();
        auswahl.setLayout(null);
        auswahl.setBorder(new TitledBorder("Auswahl Typ"));
        auswahl.setBounds(20, 120, 620, 70);

        typ1 = new JRadioButton("1: Lücken füllen");
        typ1.setBounds(20, 25, 150, 20);
        auswahl.add(typ1);

        typ2 = new JRadioButton("2: Buchstabenpuzzle");
        typ2.setBounds(325, 25, 200, 20);
        auswahl.add(typ2);

        ButtonGroup gruppe = new ButtonGroup();
        gruppe.add(typ1);
        gruppe.add(typ2);

        spielbereich.add(auswahl);

        // --- Neues Spiel Button ---
        startButton = new JButton("Starte neues Spiel");
        startButton.setBackground(Color.LIGHT_GRAY);
        startButton.setForeground(Color.BLACK);
        startButton.setBounds(20, 200, 620, 30);
        spielbereich.add(startButton);

        // --- Banner über Auswahl und Start ---
        bannerPanel = new JPanel();
        bannerPanel.setLayout(null);
        bannerPanel.setBackground(new Color(255, 0, 0));
        bannerPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        bannerPanel.setBounds(20, 110, 620, 120);
        bannerPanel.setVisible(false);

        bannerText = new JLabel("", SwingConstants.CENTER);
        bannerText.setBounds(10, 20, 600, 40);
        bannerText.setFont(new Font("SansSerif", Font.BOLD, 14));
        bannerPanel.add(bannerText);

        bannerOkButton = new JButton("OK");
        bannerOkButton.setBounds(270, 70, 80, 25);
        bannerPanel.add(bannerOkButton);

        spielbereich.add(bannerPanel);

        spielbereich.setComponentZOrder(bannerPanel, 0);
        spielbereich.repaint();

        // --- Spielfeld unten ---
        aktuellerSpieler = new JTextField("");
        aktuellerSpieler.setBounds(20, 270, 100, 25);
        aktuellerSpieler.setEditable(false);
        frame.add(aktuellerSpieler);

        JLabel zugLabel = new JLabel("ist am Zug");
        zugLabel.setBounds(130, 270, 100, 25);
        frame.add(zugLabel);

        JLabel aufgabeTextLabel = new JLabel("Aufgabe:");
        aufgabeTextLabel.setBounds(20, 310, 100, 25);
        frame.add(aufgabeTextLabel);

        buchstabenFeld = new JTextField();
        buchstabenFeld.setBounds(130, 315, 200, 25);
        buchstabenFeld.setEditable(false);
        frame.add(buchstabenFeld);

        JLabel loesungLabel = new JLabel("deine Lösung");
        loesungLabel.setBounds(20, 380, 100, 25);
        frame.add(loesungLabel);

        loesungFeld = new JTextField();
        loesungFeld.setBounds(130, 380, 200, 25);
        frame.add(loesungFeld);

        JButton fertigButton = new JButton("Fertig");
        fertigButton.setBounds(340, 380, 100, 25);
        frame.add(fertigButton);

        frame.getRootPane().setDefaultButton(fertigButton);

        // Steuerung initialisieren
        steuerung = new Steuerung(this);

        // Listener für Buttons
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int typ = 0;
                if (typ1.isSelected()) typ = 1;
                else if (typ2.isSelected()) typ = 2;
                else {
                    zeigeMeldung("Bitte einen Spieltyp auswählen.");
                    return;
                }
                steuerung.gedruecktStart(typ, name1Feld.getText(), name2Feld.getText());
            }
        });

        fertigButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String loesung = loesungFeld.getText();
                if (loesung == null || loesung.trim().isEmpty()) {
                    zeigeMeldung("Bitte eine Lösung eingeben.");
                    return;
                }
                steuerung.gedruecktFertig(loesung);
            }
        });

        bannerOkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ausblendenBanner();
            }
        });

        frame.setVisible(true);
    }

    // Methoden für Steuerung

    public String getLoesung() {
        return loesungFeld.getText();
    }

    public void setzePunkteSpieler1(int punkte) {
        punkte1Feld.setText("" + punkte);
    }

    public void setzePunkteSpieler2(int punkte) {
        punkte2Feld.setText("" + punkte);
    }

    public void setzeAktuellenSpieler(String name) {
        aktuellerSpieler.setText(name);
    }

    public void zeigeMeldung(String text) {
        bannerText.setText(text);
        bannerPanel.setVisible(true);
        bannerPanel.repaint();

        // **Ausblenden des Start-Buttons, wenn das Banner sichtbar ist**
        startButton.setVisible(false);
        
        // **Deaktiviert die Auswahlbuttons**
        typ1.setEnabled(false);
        typ2.setEnabled(false);
    }

    private void ausblendenBanner() {
        bannerPanel.setVisible(false);

        // **Einblenden des Start-Buttons, wenn das Banner unsichtbar ist**
        startButton.setVisible(true);

        // **Reaktivieren der Auswahlbuttons**
        typ1.setEnabled(true);
        typ2.setEnabled(true);
    }

    public void setzeAufgabe(String buchstaben) {
        buchstabenFeld.setText(buchstaben);
    }

    public void loescheLoesung() {
        loesungFeld.setText("");
    }

    // Main-Methode zum Starten
    public static void main(String[] args) {
        new GUI();
    }
}
