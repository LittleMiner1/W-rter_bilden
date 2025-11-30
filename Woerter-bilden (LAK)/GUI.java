import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class GUI {
    private JFrame frame;
    private JTextField name1Feld, punkte1Feld, name2Feld, punkte2Feld;
    private JRadioButton typ1, typ2;
    private JTextField aktuellerSpieler, buchstabenFeld, loesungFeld;
    private JPanel bannerPanel;
    private JLabel bannerText;
    private JButton bannerOkButton, startButton, fertigButton;

    private Steuerung steuerung;

    public GUI(Steuerung pSteuerung) {
        steuerung = pSteuerung;

        frame = new JFrame("Wörter bilden");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 700, 500);
        frame.add(layeredPane);

        // --- Spielerbereich ---
        JPanel spielbereich = new JPanel();
        spielbereich.setLayout(null);
        spielbereich.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        spielbereich.setBounds(10, 10, 660, 240);
        layeredPane.add(spielbereich, JLayeredPane.DEFAULT_LAYER);

        // Spieler 1
        JPanel spieler1 = new JPanel(); spieler1.setLayout(null); spieler1.setBorder(new TitledBorder("Spieler 1")); spieler1.setBounds(20, 20, 300, 80); spielbereich.add(spieler1);
        name1Feld = new JTextField(""); name1Feld.setBounds(70, 20, 100, 20); spieler1.add(name1Feld);
        punkte1Feld = new JTextField("0"); punkte1Feld.setBounds(70, 50, 50, 20); punkte1Feld.setEditable(false); spieler1.add(punkte1Feld);

        // Spieler 2
        JPanel spieler2 = new JPanel(); spieler2.setLayout(null); spieler2.setBorder(new TitledBorder("Spieler 2")); spieler2.setBounds(340, 20, 300, 80); spielbereich.add(spieler2);
        name2Feld = new JTextField(""); name2Feld.setBounds(70, 20, 100, 20); spieler2.add(name2Feld);
        punkte2Feld = new JTextField("0"); punkte2Feld.setBounds(70, 50, 50, 20); punkte2Feld.setEditable(false); spieler2.add(punkte2Feld);

        // Spieltyp Auswahl
        JPanel auswahl = new JPanel(); auswahl.setLayout(null); auswahl.setBorder(new TitledBorder("Auswahl Typ")); auswahl.setBounds(20, 120, 620, 70); spielbereich.add(auswahl);
        typ1 = new JRadioButton("1: Lücken füllen"); typ1.setBounds(20, 25, 150, 20); auswahl.add(typ1);
        typ2 = new JRadioButton("2: Buchstabenpuzzle"); typ2.setBounds(325, 25, 200, 20); auswahl.add(typ2);
        ButtonGroup gruppe = new ButtonGroup(); gruppe.add(typ1); gruppe.add(typ2);

        // Start Button
        startButton = new JButton("Starte neues Spiel"); startButton.setBounds(20, 200, 620, 30); spielbereich.add(startButton);

        // Banner (oben im Layer)
        bannerPanel = new JPanel(); bannerPanel.setLayout(null); bannerPanel.setBackground(Color.RED); bannerPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); bannerPanel.setBounds(20, 110, 620, 120); bannerPanel.setVisible(false);
        layeredPane.add(bannerPanel, JLayeredPane.POPUP_LAYER);

        bannerText = new JLabel("", SwingConstants.CENTER); bannerText.setBounds(10, 20, 600, 40); bannerText.setFont(new Font("SansSerif", Font.BOLD, 14)); bannerPanel.add(bannerText);
        bannerOkButton = new JButton("OK"); bannerOkButton.setBounds(270, 70, 80, 25); bannerPanel.add(bannerOkButton);

        // Spielfeld unten
        aktuellerSpieler = new JTextField(""); aktuellerSpieler.setBounds(20, 270, 100, 25); aktuellerSpieler.setEditable(false); layeredPane.add(aktuellerSpieler, JLayeredPane.DEFAULT_LAYER);
        JLabel zugLabel = new JLabel("ist am Zug"); zugLabel.setBounds(130, 270, 100, 25); layeredPane.add(zugLabel, JLayeredPane.DEFAULT_LAYER);
        JLabel aufgabeLabel = new JLabel("Aufgabe:"); aufgabeLabel.setBounds(20, 310, 100, 25); layeredPane.add(aufgabeLabel, JLayeredPane.DEFAULT_LAYER);

        buchstabenFeld = new JTextField(); buchstabenFeld.setBounds(130, 315, 200, 25); buchstabenFeld.setEditable(false); layeredPane.add(buchstabenFeld, JLayeredPane.DEFAULT_LAYER);
        loesungFeld = new JTextField(); loesungFeld.setBounds(130, 380, 200, 25); layeredPane.add(loesungFeld, JLayeredPane.DEFAULT_LAYER);

        fertigButton = new JButton("Fertig"); fertigButton.setBounds(340, 380, 100, 25); layeredPane.add(fertigButton, JLayeredPane.DEFAULT_LAYER);
        frame.getRootPane().setDefaultButton(fertigButton);

        // Listener
        startButton.addActionListener(e -> clickStart());
        fertigButton.addActionListener(e -> clickFertig());
        bannerOkButton.addActionListener(e -> clickOk());

        frame.setVisible(true);
    }

    // --- Neue Methoden für Steuerung ---

    public void clickStart() {
        if (bannerPanel.isVisible()) return;
        int typ = typ1.isSelected() ? 1 : typ2.isSelected() ? 2 : 0;
        if (typ == 0) { anzeigenMeldung("Bitte einen Spieltyp auswählen."); return; }
        steuerung.gedruecktStart(typ, name1Feld.getText(), name2Feld.getText());
    }

    public void clickFertig() {
        if (bannerPanel.isVisible()) return;
        String loesung = loesungFeld.getText();
        if (loesung == null || loesung.trim().isEmpty()) { anzeigenMeldung("Bitte eine Lösung eingeben."); return; }
        steuerung.gedruecktFertig(loesung);
    }

    public void clickOk() {
        ausblendenBanner();
    }

    public String gibSpielTyp() {
        if (typ1.isSelected()) return "Lücken füllen";
        if (typ2.isSelected()) return "Buchstabenpuzzle";
        return "Kein Spiel";
    }

    public String leseLoesung() {
        return loesungFeld.getText();
    }

    public void anzeigenPunkte(int punkte1, int punkte2) {
        punkte1Feld.setText("" + punkte1);
        punkte2Feld.setText("" + punkte2);
    }

    public void anzeigenAmZug(String name) {
        aktuellerSpieler.setText(name);
    }

    public void anzeigenMeldung(String text) {
        bannerText.setText(text);
        bannerPanel.setVisible(true);
        startButton.setEnabled(false);
        typ1.setEnabled(false);
        typ2.setEnabled(false);
        loesungFeld.setEnabled(false);
        fertigButton.setEnabled(false);
    }

    public void anzeigenAufgabe(String buchstaben) {
        buchstabenFeld.setText(buchstaben);
    }

    public void loescheLoesung() {
        loesungFeld.setText("");
    }

    private void ausblendenBanner() {
        bannerPanel.setVisible(false);
        startButton.setEnabled(true);
        typ1.setEnabled(true);
        typ2.setEnabled(true);
        loesungFeld.setEnabled(true);
        fertigButton.setEnabled(true);
    }
}
