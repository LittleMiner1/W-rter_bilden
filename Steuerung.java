public class Steuerung {
    private int aZustand; // 0 = kein Spiel, 1 = läuft
    private int aAktiverSpieler; // 0 oder 1
    private Spieler[] spieler;
    private WortListe dieWortListe;
    private GUI dieGUI;
    private int spielTyp; // 1 = Lücken füllen, 2 = Puzzle
    private boolean zweiterVersuch = false;

    public Steuerung() {
        // Steuerung erstellt die GUI und übergibt sich selbst
        dieGUI = new GUI(this);
        spieler = new Spieler[2];
        spieler[0] = new Spieler("Spieler1");
        spieler[1] = new Spieler("Spieler2");
        aZustand = 0;
        aAktiverSpieler = 0;
    }

    // --- Methoden für Button-Klicks ---
    public void clickStart() {
        dieGUI.clickStart();
    }

    public void clickFertig() {
        dieGUI.clickFertig();
    }

    public void clickOk() {
        dieGUI.clickOk();
    }

    // --- Spielstart ---
    public void gedruecktStart(int pSpielTyp, String name1, String name2) {
        spielTyp = pSpielTyp;
        spieler[0].setzeName(name1);
        spieler[0].addPunkte(-spieler[0].gibPunkte()); // Punkte auf 0 setzen
        spieler[1].setzeName(name2);
        spieler[1].addPunkte(-spieler[1].gibPunkte()); // Punkte auf 0 setzen

        dieWortListe = new WortListe(spielTyp);
        aZustand = 1;
        aAktiverSpieler = 0;

        stelleAufgabe();
        aktualisiereAnzeige();
        dieGUI.anzeigenMeldung("Spiel gestartet! " + spieler[aAktiverSpieler].gibName() + " ist am Zug.");
    }

    // --- Fertig-Button ---
    public void gedruecktFertig(String loesung) {
        if (aZustand == 0) {
            dieGUI.anzeigenMeldung("Starte erst ein Spiel!");
            return;
        }

        boolean korrekt = dieWortListe.testeLoesung(loesung);

        if (korrekt) {
            if (!zweiterVersuch) {
                spieler[aAktiverSpieler].addPunkte(5);
                dieGUI.anzeigenMeldung("Prima, das ist richtig! (5 Punkte)");
            } else {
                spieler[aAktiverSpieler].addPunkte(1);
                dieGUI.anzeigenMeldung("Gut, das ist jetzt richtig! (1 Punkt)");
            }
            zweiterVersuch = false;
            stelleAufgabe();
        } else {
            if (!zweiterVersuch) {
                dieGUI.anzeigenMeldung("Falsch, aber du hast noch eine Chance!");
                zweiterVersuch = true;
            } else {
                dieGUI.anzeigenMeldung("Leider auch falsch. ⇒ Spielerwechsel");
                zweiterVersuch = false;
                wechsleSpieler();
                stelleAufgabe();
            }
        }
        aktualisiereAnzeige();
    }

    // --- Spielerwechsel ---
    private void wechsleSpieler() {
        aAktiverSpieler = (aAktiverSpieler + 1) % 2;
    }

    // --- Aufgabe stellen ---
    public void stelleAufgabe() {
        dieWortListe.auswaehlenWort();
        dieGUI.anzeigenAufgabe(dieWortListe.gibAufgabe());
        dieGUI.loescheLoesung();
    }

    // --- Anzeige aktualisieren ---
    public void aktualisiereAnzeige() {
        dieGUI.anzeigenPunkte(spieler[0].gibPunkte(), spieler[1].gibPunkte());
        dieGUI.anzeigenAmZug(spieler[aAktiverSpieler].gibName());
    }

    // --- Main ---
    public static void main(String[] args) {
        new Steuerung();
    }
}
