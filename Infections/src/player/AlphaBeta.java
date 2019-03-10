package player;

import move.Move;
import state.State;
import move.Pair;
import player.Player;

public class AlphaBeta implements Player {

    private String type;
    private int depth;
    private int count;
    private int nbCoutAvance;

    public AlphaBeta() {
        this.type = "alphabeta";
        this.depth = 3;
        this.nbCoutAvance = 0;

    }

    public AlphaBeta(int d) {
        this.type = "alphabeta";
        this.depth = d;
        this.nbCoutAvance = 0;
    }

    public AlphaBeta(int d, int nbCoutAvance) {
        this.type = "alphabeta";
        this.depth = d;
        this.nbCoutAvance = nbCoutAvance;
    }

    @Override
    public Boolean hasIa() {
        return true;
    }

    @Override
    public Move chooseMove(State s) {
        Pair alpha = new Pair(null, Integer.MIN_VALUE);
        Pair beta = new Pair(null, Integer.MAX_VALUE);
        return this.alphabeta(s.getCopy(), this.depth, alpha, beta, 1).getKey();
    }

    /**
     * algorithme alphabeta
     *
     * @param s State
     * @param d profondeur
     * @param alpha paire
     * @param beta paire
     * @param c couleur du joueur, 1 si c'est lui. -1 si c'est l'adversaire.
     * @return une paire
     */
    public Pair alphabeta(State s, int d, Pair alpha, Pair beta, int c) {
        if (d == 0 || s.is_terminal()) {
            return new Pair(null, s.getScore());
        } else {

            if (c > 0) {
                for (Move m : s.getMoves()) {
                    count++;
                    s.playMove(m);
                    s.switchCurrentPlayer();
                    Pair tmp = this.alphabeta(s.getCopy(), d - 1, alpha, beta, -c);
                    if (tmp.getValue() > alpha.getValue()) {
                        alpha = new Pair(m, tmp.getValue());
                    }
                    if (alpha.getValue() >= beta.getValue()) {
                        alpha = new Pair(m, alpha.getValue());
                        return alpha;
                    }
                }
                return alpha;
            } else {
                for (Move m : s.getMoves()) {
                    count++;
                    s.playMove(m);
                    s.switchCurrentPlayer();
                    Pair tmp = this.alphabeta(s.getCopy(), d - 1, alpha, beta, -c);
                    if (tmp.getValue() < beta.getValue()) {
                        beta = new Pair(m, tmp.getValue());
                    }
                    if (alpha.getValue() <= beta.getValue()) {
                        beta = new Pair(m, beta.getValue());
                        return beta;
                    }
                }
                return beta;
            }
        }

    }

    public void setDepth(int i) {
        this.depth = i;
    }

    @Override
    public void setNbCoutAvance() {
        this.nbCoutAvance -= 1;
    }

    @Override
    public int getNbCoutAvance() {
        return this.nbCoutAvance;
    }

    @Override
    public String getType() {
        return this.type;
    }

    public int getDepth() {
        return this.depth;
    }

    @Override
    public int getCount() {
        return count;
    }
}
