package test;

import state.State;
import player.AlphaBeta;
import player.Human;
import player.Player;
import player.RandomPlayer;
import player.NegaMax;
import move.Move;

public class Test {

    private static final String usage = "nombre de ligne, nombre de colonne, coup d'avance pour le 1er joueur,  profondeur du joueur 1, profondeur du 2e, 0 ou 1 pour l'usage d'un alphabeta";

    public static void main(String[] arg) {
        if (checkRequierment(arg)) {
            Player one = new NegaMax(Integer.parseInt(arg[3]), Integer.parseInt(arg[2]));
            Player two;
            if (Integer.parseInt(arg[5]) == 1) {
                two = new AlphaBeta(Integer.parseInt(arg[4]));
            } else {
                two = new NegaMax(Integer.parseInt(arg[4]));
            }
            State s = new State(Integer.parseInt(arg[0]), Integer.parseInt(arg[1]), one, two);
            play(s);
        }
    }
    /**
     *
     * @param s qui est un State et qui permet de lancer la partie.
     */
    public static void play(State s) {
        while (!s.is_terminal()) {
            System.out.flush();
            System.out.println(s.toString());
            System.out.println(s.whoPlays());
            Move m = s.getCurrentPlayer().chooseMove(s);
            s.playMove(m);
            s.getCurrentPlayer().setNbCoutAvance();
            if (s.getCurrentPlayer().getNbCoutAvance() <= 0) {
                s.switchCurrentPlayer();
            }

        }
        Player one = s.getPlayer(0);
        Player two = s.getPlayer(1);
        int score1 = s.getScore(s.getPlayer(0));
        int score2 = s.getScore(s.getPlayer(1));
        System.out.println("Score:" + System.lineSeparator() + "Joueur 1: " + score1 + System.lineSeparator() + "Joueur 2: " + score2);
        String winner = (score1 > score2) ? "Joueur 1" : "Joueur 2";
        System.out.println(winner + " a gagné!");
        System.out.println(one.getCount());
        System.out.println(two.getCount());
    }

    /**
     * @param  arg permet de prendre les arguments et de les mettres en variable.
     * @return     false si les arguments ne sont pas bons.
     */
    public static boolean checkRequierment(String[] arg) {
        System.out.println("..checking..");
        if (arg.length != 6) {
            System.err.println("Le nombre d'argument ne correspond pas:" + usage);
            System.exit(1);
            return false;
        }
        if (arg.length == 6) {
            int x;
            for (int i = 0; i < 6; i++) {
                try {
                    x = Integer.parseInt(arg[i]);
                } catch (NumberFormatException e) {
                    System.err.println("L'argument " + (i + 1) + "n'est pas un entier" + System.lineSeparator()
                            + "Rappel: " + usage);
                    System.exit(1);
                    return false;
                }
            }
        }

        if (Integer.parseInt(arg[0]) < 2 && Integer.parseInt(arg[1]) < 2) {
            System.err.println("Le plateau de jeu doit être plus grand que (1,1)");
            System.exit(1);
            return false;
        }
        if (Integer.parseInt(arg[5]) != 0 && Integer.parseInt(arg[5]) != 1) {
            System.err.println("Le dernier argument doit etre 0 ou 1." + System.lineSeparator()
                    + "0: pas d'elaguage alphabeta" + System.lineSeparator()
                    + "1: presence d'elaguage alphabeta" + System.lineSeparator()
                    + "Rappel: " + usage);
            System.exit(1);
            return false;
        }
        return true;
    }

}
