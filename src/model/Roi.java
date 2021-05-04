package model;

import java.lang.Math;

public class Roi extends AbstractPiece {
    public Roi(Couleur couleur_de_piece, Coord coord) {
        super(couleur_de_piece, coord);
    }

    //Le roi se déplace d’une case dans n’importe quelle direction (horizontale, verticale, ou en diagonale),
    // sauf sur une case occupée par une pièce de sa couleur ou contrôlée par une pièce ennemie
    // (il ne peut se mettre lui-même en position d’être pris, c’est-à-dire en échec).
    // Aucun déplacement ne peut donc conduire à ce que les deux rois se trouvent sur des cases adjacentes
    // ou un coin commun.
    // il peut donc prendre toute pièce adverse non protégée (par une autre pièce adverse)
    // en se déplaçant sur la case qu’elle occupe, et la pièce prise est retirée de l’échiquier.
    public boolean isMoveOk(int xFinal, int yFinal) {
        boolean ans = false;
        if(!(this.getX() == xFinal && this.getY() == yFinal)) {
            //true si déplacement légal en fonction des algo de déplacement spécifique de chaque pièce
            if (Math.abs(this.getX() - xFinal) <= 1 && Math.abs(this.getY() - yFinal) <= 1) {
                ans = true;
            }
        }
        return ans;
    }
}
