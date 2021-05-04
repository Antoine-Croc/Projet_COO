package model;

/**
 * @author francoise.perrin Inspiration Jacques SARAYDARYAN, Adrien GUENARD *
 * 
 */

public class Cavalier extends AbstractPiece {

	public Cavalier(Couleur couleur_de_piece, Coord coord) {
		super(couleur_de_piece, coord);

	}

	public boolean isMoveOk(int xFinal, int yFinal) {
		boolean resultat = false;
		int X = xFinal - this.getX();
		int Y = xFinal - this.getY();
		int mov = 0;
		if ((X * X == 4 && Y * Y == 1)) {
			mov = 1;
		} else if ((X * X == 1 && Y * Y == 4)) {
			mov = 1;
			if (mov == 1) {
				resultat = true;
			} else {
				resultat = false;
			}

		}

		return resultat;
	}
}
