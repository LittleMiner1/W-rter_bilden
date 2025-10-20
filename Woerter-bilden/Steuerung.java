public class Steuerung {
    private int aZustand; // 0 = kein Spiel, 1 = läuft
    private int aAktiverSpieler; // 0 oder 1
    private Spieler[] spieler;
    private WortListe wortListe;
    private GUI gui;
    private int spielTyp; // 1 = Lücken füllen, 2 = Puzzle
    private boolean zweiterVersuch = false;

    public Steuerung(GUI gui) {
        this.gui = gui;
        spieler = new Spieler[2];
        spieler[0] = new Spieler("Paul");
        spieler[1] = new Spieler("Anna");
        aZustand = 0;
        aAktiverSpieler = 0;
    }

    public void gedruecktStart(int spielTyp, String name1, String name2) {
        this.spielTyp = spielTyp;
        spieler[0].setzeName(name1);
        spieler[0].addPunkte(-spieler[0].gibPunkte()); // Punkte auf 0 setzen
        spieler[1].setzeName(name2);
        spieler[1].addPunkte(-spieler[1].gibPunkte()); // Punkte auf 0 setzen

        wortListe = new WortListe(spielTyp);
        aZustand = 1;
        aAktiverSpieler = 0;
        stelleAufgabe();
        aktualisiereAnzeige();
        gui.zeigeMeldung("Spiel gestartet! " + spieler[aAktiverSpieler].gibName() + " ist am Zug.");
    }

    public void gedruecktFertig(String loesung) {
        if (aZustand == 0) {
            gui.zeigeMeldung("Starte erst ein Spiel!");
            return;
        }

        boolean korrekt = wortListe.testeLoesung(loesung);

        if (korrekt) {
            if (!zweiterVersuch) {
                // Erster Versuch richtig
                spieler[aAktiverSpieler].addPunkte(5); // Add_5Pkt
                gui.zeigeMeldung("Prima, das ist richtig! (5 Punkte)"); // Mld_1
            } else {
                // Zweiter Versuch richtig
                spieler[aAktiverSpieler].addPunkte(1); // Add_1Pkt
                gui.zeigeMeldung("Gut, das ist jetzt richtig! (1 Punkt)"); // Mld_3
            }
            zweiterVersuch = false; // zurücksetzen
            wechsleSpieler(); // WechsleSp
            stelleAufgabe();  // StelleAufg
        } else {
            if (!zweiterVersuch) {
                // Erster Versuch falsch → zweite Chance
                gui.zeigeMeldung("Falsch, aber du hast noch eine Chance!"); // Mld_2
                zweiterVersuch = true;
            } else {
                // Zweiter Versuch auch falsch → Spielerwechsel
                gui.zeigeMeldung("Leider auch falsch. ⇒ Spielerwechsel"); // Mld_4
                zweiterVersuch = false;
                wechsleSpieler(); // WechsleSp
                stelleAufgabe();  // StelleAufg
            }
        }
        aktualisiereAnzeige();
    }

    public void gedruecktOK() {
        // Kann z.B. zum Weitermachen genutzt werden, hier nicht zwingend gebraucht
    }

    public String gibSpielTyp() {
        if (spielTyp == 1) return "Lücken füllen";
        else if (spielTyp == 2) return "Buchstabenpuzzle";
        else return "Kein Spiel";
    }

    public String leseLoesung() {
        return gui.getLoesung();
    }

    public void anzeigenPunkte(int pPktSpieler1, int pPktSpieler2) {
        gui.setzePunkteSpieler1(pPktSpieler1);
        gui.setzePunkteSpieler2(pPktSpieler2);
    }

    public void anzeigenAmZug(String pName) {
        gui.setzeAktuellenSpieler(pName);
    }

    public void anzeigenMeldung(String pName) {
        gui.zeigeMeldung(pName);
    }

    public void anzeigenAufgabe(String pBuchstaben) {
        gui.setzeAufgabe(pBuchstaben);
    }

    public void loescheLoesung() {
        gui.loescheLoesung();
    }

    private void wechsleSpieler() {
        aAktiverSpieler = (aAktiverSpieler + 1) % 2;
    }

    public void stelleAufgabe() {
        wortListe.auswaehlenWort();
        anzeigenAufgabe(wortListe.gibAufgabe());
        loescheLoesung();
    }

    public void aktualisiereAnzeige() {
        anzeigenPunkte(spieler[0].gibPunkte(), spieler[1].gibPunkte());
        anzeigenAmZug(spieler[aAktiverSpieler].gibName());
    }
}
