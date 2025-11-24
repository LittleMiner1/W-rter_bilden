public class WortPuzzle extends Wort {
    public WortPuzzle(String pWort) {
        super(pWort);
    }
    
    @Override
    public String gibBuchstaben() {
        // Buchstaben zufällig anordnen
        char[] chars = aWort.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int j = (int) (Math.random() * chars.length);
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            sb.append(c + " ");
        }
        return sb.toString().trim();
    }
}
