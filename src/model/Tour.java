package model;

import tools.ChessPiecesFactory;

public class Tour extends AbstractPiece {

    public Tour(Couleur couleur_de_piece, Coord coord){
        super(couleur_de_piece,coord);
    }

    @Override
    //Tour peut se déplacer horizontalement ou verticalement.
    // Cette pièce est à longue portée,sans pouvoir sauter par-dessus une autre pièce
    //ou capturer le pion blanc.
    public boolean isMoveOk(int xFinal, int yFinal){
        boolean ans = false;
        if(   !(this.getX() == xFinal && this.getY() == yFinal)) {
            //true si déplacement légal en fonction des algo de déplacement spécifique de chaque pièce
            if (this.getX() ==xFinal || this.getY() == yFinal) {
                ans = true;
            }
        }
        return ans;
    }


}

