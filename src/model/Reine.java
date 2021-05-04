package model;

import java.lang.Math;

public class Reine extends AbstractPiece {
    public Reine(Couleur couleur_de_piece, Coord coord) {
        super(couleur_de_piece, coord);
    }

    //La dame est une pièce à longue portée,
    //capable de se mouvoir en ligne droite, verticalement, horizontalement, et diagonalement
    //sur un nombre quelconque de cases inoccupées
    //Comme pour les autres pièces du jeu d'échecs (excepté pour le pion lors de la prise en passant),
    // la dame capture en occupant la case occupée par une pièce adverse.
    public boolean isMoveOk(int xFinal, int yFinal) {
        boolean ans = false;
        if(this.getX() != xFinal && this.getY() != yFinal) {
            //true si déplacement légal en fonction des algo de déplacement spécifique de chaque pièce
            //si horizontalement ou verticalement
            if (this.getX() == xFinal | this.getY() == yFinal){
                ans = true;
            }
            //si diagonalement
            else if (Math.abs(this.getX() - xFinal) == 1 && Math.abs(this.getY() - yFinal) == 1){
                ans = true;
            }
        }
        return ans;
    }
    
    
}

