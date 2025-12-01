public class WortMitLuecke extends Wort {

    public WortMitLuecke(String pWort) {
        super(pWort);
    }

    @Override
    public String gibBuchstaben() {
        // Jedes zweite Zeichen wird durch "_" ersetzt
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < aWort.length(); i++) {
            if (i % 2 == 1) sb.append("_ ");
            else sb.append(aWort.charAt(i)).append(" ");
        }
        return sb.toString().trim();
    }
}
