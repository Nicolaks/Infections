package move;

public class Move {

    private final char type;
    private final int[] source;
    private final int[] dest;

    public Move(char t, int[] s, int[] d) {
        this.type = t;
        this.source = s;
        this.dest = d;
    }
    /**
     * Retourne la destination
     * @return tableau de int
     */
    public int[] getDest() {
        return this.dest;
    }

    /**
     * Retourne la source
     * @return un tableau de int
     */
    public int[] getSource() {
        return this.source;
    }

    /**
     * Retourne le type de joueur
     * @return un caractère qui est le type de joueur
     */
    public char getType() {
        return this.type;
    }

    @Override
    public String toString() {
        String res = "Type: ";
        if (this.getType() == 'd') {
            res += "duplicate ";
        } else {
            res += "move ";
        }
        return res + "source: " + this.getSource()[0] + "," + this.getSource()[1]
                + " destination: " + this.getDest()[0] + "," + this.getDest()[1];
    }
}
