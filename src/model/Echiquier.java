package model;
import java.model.Jeu;

public class Echiquier extends java.lang.Object implements BoardGames {
	// Noms ï¿½ vï¿½rifier
	private Jeu jeuNoir;
	private Jeu jeuBlanc;
	//Pas besoin de getter et setter car on a switchJoueur : on sait quel est le jeu courant et le jeu non courant
	private String message;
	private Couleur joueurCourant;
	
	public Echiquier() {
		this.jeuNoir = new Jeu(Couleur.NOIR);
		this.jeuBlanc = new Jeu(Couleur.BLANC);
		this.joueurCourant = Couleur.BLANC;
	}
	
	@Override
	public Couleur getColorCurrentPlayer() {
		// TODO 
		return null;
	}
	
	@Override
	public String getMessage() { 
		return message;
	}
	
	@Override
	public Couleur getPieceColor(int x, int y) {
		// TODO 
		return null;
	}
	
	private void setMessage(String newMessage) {
		message = newMessage;
	}
	
	
	
	
	@Override
	public boolean isEnd() {
		// TODO 
		return false;
	}
	
	public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal) {
		boolean ret;
		Jeu Jeu;
		if (this.getColorCurrentPlayer() == Couleur.BLANC) {
			Jeu = this.jeuBlanc;
		}
		else {
			Jeu = this.jeuNoir;
		}
		//il n'existe pas de piece du jeu courant aux coordonnees initiales
		// TODO 
		// vérifier si condition sur jeu courant
		if (!(Jeu.isPieceHere(xInit,yInit) && Jeu.getPieceColor() != this.joueurCourant)) {
			ret = false;
		}
		// les coordonnees finales ne sont pas valides ou egales aux initiales
		//TODO vérifier si possible condition sur couple de valeurs
		else if (!(Jeu.isMoveOk()) || (xInit == xFinal && yInit == yFinal)) {
			ret	= false;	
		}
		//position finale ne correspond pas a algo de deplacement piece
		else if (!(Jeu.findPiece(xInit,yInit).isMoveOk(xFinal,yFinal))) {
			ret = false;		
		}
		//il existe une piece intermediaire sur la trajectoire (sauf cavalier)
		//TODO trouver la bonne fonction
		else if (Jeu.move()) {
			if 	(Jeu.getPieceType(xInit,yInit).equals("Cavalier")) {
				ret = true;
			}
			ret = false;
		}
		//il existe une piece positionnees aux coordonnees finales :
		else if (Jeu.isPieceHere(xFinal,yFinal)) {
			//si elle est de la meme couleur
			if (Jeu.getPieceColor(xInit,yInit) == (Jeu.getPieceColor(xFinal,yFinal))){
				if (Jeu.setCastling()) { s
				ret = true;
				}
				ret = false;
			}
			//sinon prendre la piece intermediaire (vigilance pour le cas du pion) et deplacer la piece
			else if (Jeu.setPossibleCapture()) {
				if (Jeu.isPawnPromotion(xFinal,yFinal)) {
					//TODO vérifier comment mettre en place le type
					Jeu.pawnPromotion(xFinal,yFinal,type);
				}
				ret = Jeu.capture(xFinal,yFinal);
			}
		}
		//sinon deplacer la piece
		else {
			ret = this.move(xInit,yInit,xFinal,yFinal);//ret = true si déplacement effectué	
		}

		return ret;
	}

	@Override
	public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
		// TODO
		return false;
	}
	
	public void switchJoueur() {
		//Si jeu blanc courant, on switch sur jeu noir
		if (this.joueurCourant == Couleur.BLANC) {
			this.joueurCourant = Couleur.NOIR;
		}
		//Si jeu noir courant, on switch sur jeu blanc
		else {
			this.joueurCourant = Couleur.BLANC;
		}
		
	}
	
	@Override
	public String toString() {
		String ret;
		if (this.getColorCurrentPlayer() == Couleur.BLANC) {
			ret = this.jeuBlanc.toString();
		}
		else {
			ret = this.jeuNoir.toString();
		}
		return ret;
	}
	








}
