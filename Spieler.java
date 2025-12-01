public class Spieler {
    private String aName;
    private int aPunkte;

    public Spieler(String pName) {
        aName = pName;
        aPunkte = 0;
    }

    public void addPunkte(int pPunkte) {
        aPunkte += pPunkte;
    }

    public int gibPunkte() {
        return aPunkte;
    }

    public String gibName() {
        return aName;
    }

    public void setzeName(String pName) {
        aName = pName;
    }
}
