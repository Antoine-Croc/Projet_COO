package model;

import java.util.List;
import tools.ChessPiecesFactory;

public class Jeu {
    private List<Pieces> list_pieces ;
    //constructor
    public Jeu(Couleur couleur){
        list_pieces = ChessPiecesFactory.newPieces(couleur);
    }





    //methodes
    public boolean isPieceHere(int x, int y){
    return false;
    }

}
