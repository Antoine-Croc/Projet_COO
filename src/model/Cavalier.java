package model;

/**
 * @author francoise.perrin Inspiration Jacques SARAYDARYAN, Adrien GUENARD *
 * 
 */


public class Cavalier implements Pieces{
	
	public Cavalier(Couleur couleur_de_piece, Coord coord) {
		super(couleur_de_piece);
		super(coord);
		/*
		 * Constructeur
		 */
	}
	
	public boolean isMoveOk(int xFinal, int yFinal) {
		int X = xFinal-this.getX();
		int Y = xFinal-this.getY();
		int mov = 0;
		if ((X²,Y²) == (4,1)) {
			mov = 1;
		}else if((X²,Y²) == (1,4) {
			mov = 1;
		if (mov == 1) {
			return true
		}else {
			return false
		}
		/*		 
		 * returns true si déplacement légal en fonction des algo de déplacement 
		 * spécifique de chaque pièce
		 */
	}
}
