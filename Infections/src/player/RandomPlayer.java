package player;

import java.util.Random;
import java.util.ArrayList;

import state.State;
import move.Move;

public class RandomPlayer implements Player {

    private final String type;
    private int count;

    public RandomPlayer() {
        this.type = "random";
        this.count = 0;
    }

    @Override
    public Boolean hasIa() {
        return false;
    }

    @Override
    public Move chooseMove(State s) {
        Random r = new Random();

        ArrayList<Move> moves = s.getMoves();
        this.count++;
        return moves.get(r.nextInt(moves.size()));
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public int getNbCoutAvance() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setNbCoutAvance() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getCount() {
        return this.count;
    }
}
