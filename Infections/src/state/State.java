package state;

import java.util.ArrayList;
import player.Player;
import move.Move;
import player.AlphaBeta;
import player.Human;
import player.RandomPlayer;
import player.NegaMax;

public class State {

    private final Player[][] board;
    private Player currentPlayer;
    private final Player[] listPLayers;

    public State(int x, int y, Player player1, Player player2) {
        this.board = new Player[x][y];
        this.listPLayers = new Player[2];
        this.listPLayers[0] = player1;
        this.listPLayers[1] = player2;
        this.currentPlayer = player1;
        this.board[0][0] = this.currentPlayer;
        this.board[x - 1][y - 1] = this.listPLayers[1];
    }

    /**
     * Permet de changer le Joueur courant
     */
    public void switchCurrentPlayer() {
        if (this.getCurrentPlayer() == this.listPLayers[0]) {
            this.setCurrentPlayer(this.listPLayers[1]);
        } else {
            this.setCurrentPlayer(this.listPLayers[0]);
        }
    }

    /**
     * Permet de savoir qui joue
     *
     * @return une String avec le nom du Joueur
     */
    public String whoPlays() {
        String res = "Vous etes : ";
        if (this.getCurrentPlayer() == this.listPLayers[0]) {
            res += "joueur 1 -> " + this.listPLayers[0].getType() + " X" + System.lineSeparator();
        } else {
            res += "joueur 2 -> " + this.listPLayers[1].getType() + " O" + System.lineSeparator();
        }
        return res;
    }

    /**
     * Permet de savoir si la grille est pleine
     *
     * @return un booléen.
     */
    public boolean isFull() {
        for (Player[] board1 : this.board) {
            for (int y = 0; y < this.board[0].length; y++) {
                if (board1[y] == null) {
                    return false;
                } else {
                    ;
                }
            }
        }
        return true;
    }

    /**
     * Vérifie si il ne reste que un seul joueur dans la grille.
     *
     * @param "empty-statement" [description]
     */
    @SuppressWarnings("empty-statement")
    public boolean onlyOne(Player p) {
        for (Player[] board1 : this.board) {
            for (int y = 0; y < this.board[0].length; y++) {
                if (board1[y] != p && board1[y] != null) {
                    return false;
                } else {
                    ;
                }
            }
        }
        return true;
    }

    /**
     * Verifie si la partie est terminée
     *
     * @return true ou false
     */
    public boolean is_terminal() {
        if (this.onlyOne(this.listPLayers[0]) || this.onlyOne(this.listPLayers[1]) || this.isFull()) {
            return true;
        }
        if (this.getMoves(this.listPLayers[0]).isEmpty()) {
            System.out.println("Le joueur 2 va jouer");
            return playAllRemainingMoves(this.listPLayers[1]);
        } else if (this.getMoves(this.listPLayers[1]).isEmpty()) {
            System.out.println("Le joueur 1 va jouer");
            return playAllRemainingMoves(this.listPLayers[0]);
        } else {
            return this.isFull();
        }
    }

    /**
     * Permet de jouer les coups restant.
     *
     * @param p joueur
     * @return booléen
     */
    public boolean playAllRemainingMoves(Player p) {
        System.out.println("..PLaying..." + System.lineSeparator() + this.toString());
        this.setCurrentPlayer(p);
        while (this.getMoves(p).size() > 0) {
            for (Move move : this.getMoves(p)) {
                if (move.getType() == 'd') {
                    this.playMove(move);
                    break;
                }
            }
        }
        return true;
    }

    /**
     * Permet de jouer le coup mis en paramètre.
     *
     * @param m Move
     */
    public void playMove(Move m) {
        if (m != null) {
            int x = m.getDest()[0];
            int y = m.getDest()[1];
            char type = m.getType();
            Player p = this.getCurrentPlayer();
            this.board[x][y] = p;
            if (type == 'd') {
                if (x + 1 < this.board.length) {
                    if (this.board[x + 1][y] != null && this.board[x + 1][y] != p) {
                        this.board[x + 1][y] = p;
                    } else
						;
                }
                if (y + 1 < this.board[0].length) {
                    if (this.board[x][y + 1] != null && this.board[x][y + 1] != p) {
                        this.board[x][y + 1] = p;
                    } else
						;
                }
                if (x - 1 >= 0) {
                    if (this.board[x - 1][y] != null && this.board[x - 1][y] != p) {
                        this.board[x - 1][y] = p;
                    } else
						;
                }
                if (y - 1 >= 0) {
                    if (this.board[x][y - 1] != null && this.board[x][y - 1] != p) {
                        this.board[x][y - 1] = p;
                    } else
						;
                } else
					;
            } else if (type == 'm') {
                int a = m.getSource()[0];
                int b = m.getSource()[1];
                this.board[a][b] = null;
            }
        } else
			;

    }

    /**
     * Permet de metttre le joueur courant
     *
     * @param p qui est un joueur.
     */
    public void setCurrentPlayer(Player p) {
        this.currentPlayer = p;
    }

    /**
     * Permet de mettre en place la grille pour le joueur
     *
     * @param x un entier
     * @param y un entier
     * @param p un joueur
     */
    public void setBoard(int x, int y, Player p) {
        this.board[x][y] = p;
    }

    /**
     * Permet de connaitre le scrore d'un joueur
     *
     * @param "empty-statement" [description]
     */
    @SuppressWarnings("empty-statement")
    public int getScore(Player p) {
        int p1_score = 0;
        int p2_score = 0;
        for (Player[] board1 : this.board) {
            for (int y = 0; y < this.board[0].length; y++) {
                if (board1[y] == this.listPLayers[0]) {
                    p1_score++;
                } else if (board1[y] != null) {
                    p2_score++;
                }
            }
        }
        if (p == this.listPLayers[0]) {
            if (this.onlyOne(p)) {
                p1_score += 100;
            }
            return p1_score - p2_score;
        } else {
            if (this.onlyOne(p)) {
                p2_score += 100;
            }
            return p2_score - p1_score;
        }
    }

    /**
     * Permet de retourner le score.
     *
     * @return le scrore du joueur courant
     */
    public int getScore() {
        return getScore(this.getCurrentPlayer());
    }

    /**
     * Retourne le joueur courant
     *
     * @return le joueur actuel
     */
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * Donne la liste des joueurs
     *
     * @param a un entier
     * @return la liste des joueurs
     */
    public Player getPlayer(int a) {
        return this.listPLayers[a];
    }

    /**
     * Retourne le joueur suivant
     *
     * @return [description]
     */
    public Player getRelativeToCurrentPlayer() {
        if (this.listPLayers[0] == this.getCurrentPlayer()) {
            return this.listPLayers[1];
        } else {
            return this.listPLayers[0];
        }
    }

    /**
     * Retourne une copy du board
     *
     * @return un State qui est une copy du jeu
     */
    public State getCopy() {
        int x = this.board.length;
        int y = this.board[0].length;
        Player one = this.listPLayers[0];
        Player two = this.listPLayers[1];

        State copy = new State(x, y, one, two);
        copy.setCurrentPlayer(this.getCurrentPlayer());

        copy.setBoard(0, 0, null);
        copy.setBoard(x - 1, y - 1, null);

        for (int a = 0; a < x; a++) {
            for (int b = 0; b < y; b++) {
                if (this.board[a][b] == null) {
                    copy.board[a][b] = null;
                } else if (this.board[a][b] == this.listPLayers[0]) {
                    copy.board[a][b] = one;
                } else {
                    copy.board[a][b] = two;
                }
            }
        }
        return copy;
    }

    /**
     * Retourne les coups du joueur courant.
     *
     * @return ArrayList de Move
     */
    public ArrayList<Move> getMoves() {
        return this.getMoves(this.getCurrentPlayer());
    }

    /**
     * Retourne les coups du joueurs mis en argument
     *
     * @param p joueur
     * @return ArrayList de Move
     */
    public ArrayList<Move> getMoves(Player p) {
        int x = this.board.length;
        int y = this.board[0].length;
        ArrayList<Move> lm = new ArrayList();
        for (int a = 0; a < x; a++) {
            for (int b = 0; b < y; b++) {
                if (this.board[a][b] == p) {
                    if (b + 1 < y) {
                        if (this.board[a][b + 1] == null) {
                            lm.add(new Move('d', new int[]{a, b}, new int[]{a, b + 1}));
                        }
                        if (b + 2 < y) {
                            if (this.board[a][b + 2] == null) {
                                lm.add(new Move('m', new int[]{a, b}, new int[]{a, b + 2}));
                            }
                        }
                    }
                    if (b - 1 >= 0) {
                        if (this.board[a][b - 1] == null) {
                            lm.add(new Move('d', new int[]{a, b}, new int[]{a, b - 1}));
                        }
                        if (b - 2 >= 0) {
                            if (this.board[a][b - 2] == null) {
                                lm.add(new Move('m', new int[]{a, b}, new int[]{a, b - 2}));
                            }
                        }
                    }
                    if (a + 1 < x) {
                        if (this.board[a + 1][b] == null) {
                            lm.add(new Move('d', new int[]{a, b}, new int[]{a + 1, b}));
                        }
                        if (a + 2 < x) {
                            if (this.board[a + 2][b] == null) {
                                lm.add(new Move('m', new int[]{a, b}, new int[]{a + 2, b}));
                            }
                        }
                    }
                    if (a - 1 >= 0) {
                        if (this.board[a - 1][b] == null) {
                            lm.add(new Move('d', new int[]{a, b}, new int[]{a - 1, b}));
                        }
                        if (a - 2 >= 0) {
                            if (this.board[a - 2][b] == null) {
                                lm.add(new Move('m', new int[]{a, b}, new int[]{a - 2, b}));
                            }
                        }
                    }
                }
            }
        }
        return lm;
    }

    /**
     * Permet d'afficher la liste des coups possible
     *
     * @param moves ArrayList de Move
     */
    public void printMoveToString(ArrayList<Move> moves) {
        String res = new String("");
        for (int i = 0; i < moves.size(); i++) {
            res += i + ") " + moves.get(i).toString() + System.lineSeparator();
        }
        System.out.println(res);
    }

    @Override
    public String toString() {
        String res = "";
        for (Player[] board1 : this.board) {
            for (int y = 0; y < this.board[0].length; y++) {
                if (board1[y] == this.listPLayers[0]) {
                    res += "X ";
                } else if (board1[y] == null) {
                    res += "  ";
                } else {
                    res += "O ";
                }
                if (y + 1 < this.board[0].length) {
                    res += "|";
                } else
					;
            }
            res += System.lineSeparator();
        }
        return res;
    }
}
