package player;

import java.util.Scanner;
import java.util.ArrayList;

import state.State;
import move.Move;

public class Human implements Player {

    private final String type;
    private int count;

    public Human() {
        this.type = "human";
        this.count = 0;
    }

    @Override
    public Move chooseMove(State s) {
        Scanner scanner = new Scanner(System.in);
        s.toString();
        ArrayList<Move> moves = s.getMoves();
        System.out.println("Choisissez le coup: ");
        s.printMoveToString(moves);
        int res = scanner.nextInt();
        return moves.get(res);
    }

    @Override
    public Boolean hasIa() {
        return false;
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
