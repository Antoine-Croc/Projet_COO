package model;

import java.util.Iterator;
import java.util.List;
import tools.ChessPiecesFactory;

public class Jeu {
    private List<Pieces> list_pieces ;
    //constructor
    public Jeu(Couleur couleur){
        this.list_pieces = ChessPiecesFactory.newPieces(couleur);
    }

    //methodes

    //true si une pièce se trouve aux coordonnées indiquées
    public boolean isPieceHere(int x, int y){
        boolean ans = false;
        Iterator<Pieces> ite = this.list_pieces.iterator();
        while(ite.hasNext())
        {
            Pieces piece = ite.next();
            if(piece.getX() == x && piece.getY() == y){
                ans = true;
                }
            }

        return ans;
    }

}
