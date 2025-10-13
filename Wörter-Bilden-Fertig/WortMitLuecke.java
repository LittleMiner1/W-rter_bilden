public class WortMitLuecke extends Wort {

    public WortMitLuecke(String pWort) {
        super(pWort);
    }

    @Override
    public String gibBuchstaben() {
        // Beispiel: Wort "AUTO" => zeige "A _ U _" oder so
        // Hier simpel: jedes zweite Zeichen wird durch _ ersetzt
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < aWort.length(); i++) {
            if (i % 2 == 1) {
                sb.append("_ ");
            } else {
                sb.append(aWort.charAt(i) + " ");
            }
        }
        return sb.toString().trim();
    }
}
