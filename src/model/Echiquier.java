package model;
import model.Jeu;
import model.PieceIHM;

import java.util.ArrayList;
import java.util.List;

public class Echiquier extends java.lang.Object implements BoardGames {
	// Noms a verifier
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
		return this.joueurCourant;
	}
	
	@Override
	public String getMessage() { 
		return message;
	}
	
	@Override
	public Couleur getPieceColor(int x, int y) {
		return this.jeuBlanc.getPieceColor(x,y);
	}
	
	public List<PieceIHM> getPiecesIHM(){
		//TODO faire valider le fonctionnement
		List<PieceIHM> PieceIHM = new ArrayList<PieceIHM>(); //TODO vérifier si on a besoin d'une arrayList ou si un autre type nous conviendrait
		Jeu Jeu = this.jeuBlanc;
		for (int i=0;i<Jeu.getPiecesIHM().size();i++) {
			PieceIHM.add(Jeu.getPiecesIHM().get(i)); //On récupère le i-eme élément
		}
		Jeu = this.jeuNoir;
		for (int i=0;i<Jeu.getPiecesIHM().size();i++) {
			PieceIHM.add(Jeu.getPiecesIHM().get(i)); //On récupère le i-eme élément
		}
		return PieceIHM;
	}
	
	private void setMessage(String newMessage) {
		message = newMessage;
	}
	
	@Override
	public boolean isEnd() {
		Jeu jeu;
		boolean ret = false;
		if (this.getColorCurrentPlayer() == Couleur.BLANC) {
			jeu = this.jeuBlanc;
		}
		else {
			jeu = this.jeuNoir;
		}
		//TODO implémenter possibilité echec et mat
		/*
		if (jeu.isCheckMate) {
			ret = true;
		}
		*/
		return ret;
	}
	
	public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal) {
		boolean ret;
		Jeu jeu;
		if (this.getColorCurrentPlayer() == Couleur.BLANC) {
			jeu = this.jeuBlanc;
		}
		else {
			jeu = this.jeuNoir;
		}
		//il n'existe pas de piece du jeu courant aux coordonnees initiales
		if (!(jeu.isPieceHere(xInit,yInit) && this.getPieceColor(xInit,yInit) != this.joueurCourant)) {
			ret = false;
		}
		// les coordonnees finales ne sont pas valides ou egales aux initiales
		//position finale ne correspond pas a algo de deplacement piece
		else if (!(jeu.isMoveOk(xInit,yInit,xFinal,yFinal))) {
			ret	= false;	
		}
	
		//il existe une piece intermediaire sur la trajectoire (sauf cavalier)
		//TODO trouver la bonne fonction
		/*
		else if (Jeu.intermediatePiece()) {
			if 	(Jeu.getPieceType(xInit,yInit).equals("Cavalier")) {
				ret = true;
			}
			ret = false;
		}
		*/
		//il existe une piece positionnees aux coordonnees finales :
		else if (jeu.isPieceHere(xFinal,yFinal)) {
			//si elle est de la meme couleur
			if (jeu.getPieceColor(xInit,yInit) == (jeu.getPieceColor(xFinal,yFinal))){
				if (jeu.getRoque()) {
				ret = true;
				}
				ret = false;
			}
			//sinon prendre la piece intermediaire (vigilance pour le cas du pion) et deplacer la piece
			else {
				if (jeu.isPawnPromotion(xFinal,yFinal)) {
					ret = false;
				}
				jeu.setPossibleCapture();//ret = true si capture possible
				ret = true;
			}
		}
		//sinon deplacer la piece
		else {
			ret = true;
		}
		if (ret) {
			this.setMessage("Déplacement Ok");
		}
		else {
			this.setMessage("Déplacement interdit");
		}
		return ret;
	}

	@Override
	public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
		//TODO implémentation de checkmatePossibility ?
		boolean ret;
		Jeu jeu;
		if (this.getColorCurrentPlayer() == Couleur.BLANC) {
			jeu = this.jeuBlanc;
		}
		else {
			jeu = this.jeuNoir;
		}
		if (this.isMoveOk(xInit, yInit, xFinal, yFinal)) {
			jeu.move(xInit, yInit, xFinal, yFinal);
			if (jeu.getCapturePossible()) {
				jeu.capture(xFinal,yFinal);
			}
			ret = true;//Jusque la et si pas de possibilité de mise en échec, le déplacement est effectué
			Coord KingCoord = jeu.getKingCoord();
			/*
			 //TODO implémenter possibilité échec et mat
			if (jeu.checkmatePossibility()){ //Etude des possibilités de mise en échec
				if (jeu.getCapturePossible()) {
					jeu.undoCapture();
				}
				ret = false;
			}
			*/
		}
		else {
			ret = false;
		}
		return ret;
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
