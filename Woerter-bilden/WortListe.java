public class WortListe {
    private Wort[] woerter;
    private int aAnzahlWoerter;
    private int ldxGewaehltesWort;
    private int spielTyp; // 1 = Lücken füllen, 2 = Puzzle

    public WortListe(int spielTyp) {
        this.spielTyp = spielTyp;
        // Beispielwörter
        String[] beispielWoerter = {"AUTO", "BANANE", "SCHIFF", "HUND", "KAFFEE", "MASCHINE", "KANONE", "MAUS", "KATZE", 
                                    "HAUS", "TOR", "FARBE", "TASSE", "JACKE", "HAARE", "SCHULE", "APFEL", "SPIEL", 
                                    "MITTAG", "LERNEN", "SPRACHEN", "QUELLE", "KLASSE", "FILM", "SERIE", "MALEN", 
                                    "ZEICHNEN", "AUSMALEN", "SCHREIBEN", "SPORT", "ESSEN", "TASTATUR", "FREUND", 
                                    "FREUNDIN", "KLAMMER", "RING", "ARMBAND", "PUZZLE", "RAUM", "RECHTECK", "ZAHLEN", 
                                    "MODEL", "KAMERA", "STUHL", "NAME", "WORT", "NACHNAME", "LUECKE", "WEIHNACHTEN", 
                                    "SCHULHOF", "PAUSENBROT", "HEIZUNG", "RAUMSCHIFF", "INFORMATIK", "DEUTSCHLAND", 
                                    "TISCH", "BUCHSCHTABEN", "TEXT", "SCHNEEFLOCKE", "REGENSCHAUER", "KOERPER", 
                                    "ZIRKEL", "FLAGGE", "QUALLE", "GESETZE", "GEOGRAFIE", "CHINA", "AMERIKA", 
                                    "AUTOGRAMM", "BRILLE", "FIEBER", "RESTAURANT", "KELLNER", "MUSIK", "ARBEITEN", "KUNST", 
                                    "ADVENT", "FRUEHLING", "FAMILIE", "SUEDKOREA", "GEREDE", "COMPUTER", "LIEDER", "KASTEN", 
                                    "REISEN", "TAFEL", "PROGRAMMIEREN", "SEOUL", "ORT", "ROT", "STADT", "INFRAROT", };
        aAnzahlWoerter = beispielWoerter.length;
        woerter = new Wort[aAnzahlWoerter];
        for (int i = 0; i < aAnzahlWoerter; i++) {
            if (spielTyp == 1) {
                woerter[i] = new WortMitLuecke(beispielWoerter[i]);
            } else {
                woerter[i] = new WortPuzzle(beispielWoerter[i]);
            }
        }
        ldxGewaehltesWort = -1;
    }

    public void auswaehlenWort() {
        ldxGewaehltesWort = (int) (Math.random() * aAnzahlWoerter);
        woerter[ldxGewaehltesWort].setzeStatusAufNie();
    }

    public String gibAufgabe() {
        if (ldxGewaehltesWort >= 0 && ldxGewaehltesWort < aAnzahlWoerter) {
            return woerter[ldxGewaehltesWort].gibBuchstaben();
        }
        return "";
    }

    public boolean testeLoesung(String pWort) {
        if (ldxGewaehltesWort < 0) return false;
        return woerter[ldxGewaehltesWort].pruefeLoesung(pWort);
    }

    public String gibLoesungsWort() {
        if (ldxGewaehltesWort < 0) return "";
        return woerter[ldxGewaehltesWort].gibWort();
    }
}
