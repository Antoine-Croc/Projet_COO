package model;

/**
 * @author francoise.perrin Inspiration Jacques SARAYDARYAN, Adrien GUENARD *
 * 
 */

public class Pion extends AbstractPiece {

	public Pion(Couleur couleur_de_piece, Coord coord) {
		super(couleur_de_piece, coord);

	}

	public boolean isMoveOk(int xFinal, int yFinal) {
		boolean resultat = false;
		int X = xFinal - this.getX();
		int Y = yFinal - this.getY();
		int mov = 0;
		if (this.getCouleur() == Couleur.BLANC) {
			if (this.getY() == 6) {
				if (Y == -1 | Y == -2) {
					if (X == 0) {
						mov = 1;
					}
				}
			} else {
				if (Y == -1) {
					if (X == 0) {
						mov = 1;
						}
				}
			}
		}
		if (this.getCouleur() == Couleur.NOIR) {
			if (this.getY() == 1) {
				if (Y == 1 | Y == 2) {
					if (X == 0) {
						mov = 1;
						}
				}
			} else {
				if (Y == 1) {
					if (X == 0) {
						mov = 1;
						}
				}
			}
		}
		if (mov == 1) {
			resultat= true;
		} else {
			resultat= false;
		}
		return resultat;
	}
}

/*
 * returns true si déplacement légal en fonction des algo de déplacement
 * spécifique de chaque pièce
 */