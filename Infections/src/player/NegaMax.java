package player;

import move.Move;
import move.Pair;
import state.State;

public class NegaMax implements Player {

    private String type;
    private int depth;
    private int count;
    private int nbCoutAvance;

    public NegaMax() {
        this.type = "negamax";
        this.depth = 2;
        this.nbCoutAvance = 0;
    }

    public NegaMax(int d) {
        this.type = "negamax";
        this.depth = d;
        this.nbCoutAvance = 0;
    }

    public NegaMax(int d, int nbCoutAvance) {
        this.type = "negamax";
        this.depth = d;
        this.nbCoutAvance = nbCoutAvance;
    }

    /**
     * Permet de connaitre le type ici negamax
     *
     * @return NegaMax
     */
    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public Boolean hasIa() {
        return true;
    }

    /**
     * Permet de choisir un Move
     *
     * @param s State
     * @return Move
     */
    @Override
    public Move chooseMove(State s) {
        return this.negamax(s.getCopy(), this.getDepth(), 1).getKey();
    }

    /**
     * algorithme NegaMax
     *
     * @param s State
     * @param depth profondeur
     * @param c [description]
     * @return une paire
     */
    public Pair negamax(State s, int depth, int c) {

        if (depth == 0 || s.is_terminal()) {

            int i = s.getScore() * c;
            return new Pair(null, i);
        } else {
            Pair best_value = new Pair(null, (int) Integer.MIN_VALUE);
            for (Move move : s.getMoves()) {
                count++;
                s.playMove(move);
                s.switchCurrentPlayer();
                Pair value = negamax(s.getCopy(), depth - 1, -c);
                if (best_value.getValue() < value.getValue()) {
                    best_value = value;
                    best_value.setMove(move);
                }
            }
            return best_value;
        }
    }

    /**
     * ¨Permet de connaitre le nombre de coups d'avance restant
     *
     * @return entier
     */
    @Override
    public int getNbCoutAvance() {
        return nbCoutAvance;
    }

    /**
     * Permet d'enlever un à chaque fois que la méthode est appelée.
     */
    @Override
    public void setNbCoutAvance() {
        this.nbCoutAvance -= 1;
    }

    /**
     * Retourne les noeuds parcourus
     *
     * @return entier
     */
    @Override
    public int getCount() {
        return count;
    }

    /**
     * Retourne la profondeur
     *
     * @return entier
     */
    public int getDepth() {
        return this.depth;
    }

    public void setDepth(int i) {
        this.depth = i;
    }
}
