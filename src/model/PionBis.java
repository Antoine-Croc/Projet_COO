package model;

/**
 * @author francoise.perrin Inspiration Jacques SARAYDARYAN, Adrien GUENARD *
 * 
 */

public class PionBis extends AbstractPiece {

	public PionBis(Couleur couleur_de_piece, Coord coord) {
		super(couleur_de_piece, coord);

	}

	public boolean isMoveOk(int xFinal, int yFinal) {
		boolean resultat = false;
		int X = xFinal - this.getX();
		int Y = xFinal - this.getY();
		int mov = 0;
		if (this.getCouleur() == Couleur.BLANC) {
			if (this.getY() == 6) {
				if (Y == -1 || Y == -2) {
					mov = 1;
				}
			} else {
				if (Y == -1) {
					mov = 1;
				}
			}
		}
		if (this.getCouleur() == Couleur.NOIR) {
			if (this.getY() == 1) {
				if (Y == 1 || Y == 2) {
					mov = 1;
				}
			} else {
				if (Y == 1) {
					mov = 1;
				}
			}
		}
		if (mov == 1) {
			resultat= true;
		} else {
			resultat= false;
		}
		System.out.println("X="+X+" Y="+Y);
		return resultat;
	}
}

/*
 * returns true si déplacement légal en fonction des algo de déplacement
 * spécifique de chaque pièce
 */
