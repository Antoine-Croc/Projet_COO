package model;

/**
 * @author francoise.perrin Inspiration Jacques SARAYDARYAN, Adrien GUENARD *
 * 
 */

public class Fou extends AbstractPiece {

	public Fou(Couleur couleur_de_piece, Coord coord) {
		super(couleur_de_piece, coord);

	}

	public boolean isMoveOk(int xFinal, int yFinal) {
		boolean resultat = false;
		int X = xFinal - this.getX();
		int Y = xFinal - this.getY();
		int mov = 0;
		for (int i = 1; i < 8; i++) {
			if (X * X == i && Y * Y == i) {
				mov = 1;
				if (mov == 1) {
					resultat = true;
				} else {
					resultat = false;
				}

			}

		}
		return resultat;
	}
}