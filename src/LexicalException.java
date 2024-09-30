public class LexicalException extends Exception {
    private final int position; // Position de l'erreur dans le texte d'entrée

    public LexicalException(String message, int position) {
        super(message);
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
