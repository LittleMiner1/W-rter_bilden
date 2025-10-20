public abstract class Wort {
    protected String aWort;
    protected boolean aStatus; // true = korrekt gelöst

    public Wort(String pWort) {
        this.aWort = pWort;
        this.aStatus = false; // noch nicht gelöst
    }

    public void setzeStatusAufNie() {
        aStatus = false;
    }

    public String gibWort() {
        return aWort;
    }

    public abstract String gibBuchstaben();

    public boolean gibStatus() {
        return aStatus;
    }

    public void loescheLoesung() {
        aStatus = false;
    }

    public boolean pruefeLoesung(String pWort) {
        if (pWort == null) return false;
        if (aWort.equalsIgnoreCase(pWort.trim())) {
            aStatus = true;
            return true;
        } else {
            aStatus = false;
            return false;
        }
    }
}
