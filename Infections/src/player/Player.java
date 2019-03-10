package player;

import move.Move;
import state.State;

public interface Player {

    public int getNbCoutAvance();

    public void setNbCoutAvance();

    public Move chooseMove(State s);

    public Boolean hasIa();

    public String getType();

    public int getCount();
}
