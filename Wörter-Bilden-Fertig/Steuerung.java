public class Steuerung {
    private int aZustand; // 0 = kein Spiel, 1 = läuft
    private int aAktiverSpieler; // 0 oder 1
    private Spieler[] spieler;
    private WortListe wortListe;
    private GUI gui;
    private int spielTyp; // 1 = Lücken füllen, 2 = Puzzle

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
            gui.zeigeMeldung("Richtig! " + spieler[aAktiverSpieler].gibName() + " bekommt 1 Punkt.");
            spieler[aAktiverSpieler].addPunkte(1);
        } else {
            gui.zeigeMeldung("Falsch! Das richtige Wort war: " + wortListe.gibLoesungsWort());
        }
        wechsleSpieler();
        stelleAufgabe();
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
