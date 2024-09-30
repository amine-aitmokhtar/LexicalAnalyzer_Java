package TOKEN;

public class Token {
    private final TokenType type; // Type du token
    private final String value; // Valeur du token (pour les nombres, les identificateurs, les opérateurs)

    // Constructeur pour les tokens qui ont une valeur (nombre, identificateur, opérateur)
    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    // Constructeur pour les tokens qui n'ont pas de valeur (EOF)
    public Token(TokenType type) {
        this.type = type;
        this.value = null;
    }

    // Accesseurs pour le type et la valeur du token
    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    // Retourne une représentation sous forme de chaîne de caractères du token
    @Override
    public String toString() {
        return String.format("[%s %s]", type, value != null ? value : "");
    }
    
}
