package move;

public class Pair {

    private Move key;
    private int value;

    public Pair(Move m, int i) {
        this.key = m;
        this.value = i;
    }

    /**
     * Permet de faire un flip pour les algorithme.
     */
    public void flip() {
        this.value = -this.value;
    }

    /**
     * Retourne la clé qui est un Move
     *
     * @return Move
     */
    public Move getKey() {
        return this.key;
    }

    /**
     * Retourne la valeur de la paire
     *
     * @return un entier
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Permet de mettre à jour le Move
     *
     * @param move Move
     */
    public void setMove(Move move) {
        this.key = move;
    }
}
