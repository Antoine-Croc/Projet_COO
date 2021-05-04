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
		List<PieceIHM> PieceIHM = new ArrayList<PieceIHM>(); //TODO v�rifier si on a besoin d'une arrayList ou si un autre type nous conviendrait
		Jeu Jeu = this.jeuBlanc;
		for (int i=0;i<Jeu.getPiecesIHM().size();i++) {
			PieceIHM.add(Jeu.getPiecesIHM().get(i)); //On r�cup�re le i-eme �l�ment
		}
		Jeu = this.jeuNoir;
		for (int i=0;i<Jeu.getPiecesIHM().size();i++) {
			PieceIHM.add(Jeu.getPiecesIHM().get(i)); //On r�cup�re le i-eme �l�ment
		}
		return PieceIHM;
	}
	
	private void setMessage(String newMessage) {
		message = newMessage;
	}
	
	@Override
	public boolean isEnd() {
		Jeu Jeu;
		boolean ret = false;
		if (this.getColorCurrentPlayer() == Couleur.BLANC) {
			Jeu = this.jeuBlanc;
		}
		else {
			Jeu = this.jeuNoir;
		}
		if (Jeu.isCheckMate) {
			ret = true;
		}
		return ret;
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
		if (!(Jeu.isPieceHere(xInit,yInit) && this.getPieceColor(xInit,yInit) != this.joueurCourant)) {
			ret = false;
		}
		// les coordonnees finales ne sont pas valides ou egales aux initiales
		//position finale ne correspond pas a algo de deplacement piece
		else if (!(Jeu.isMoveOk(xInit,yInit,xFinal,yFinal))) {
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
		else if (Jeu.isPieceHere(xFinal,yFinal)) {
			//si elle est de la meme couleur
			if (Jeu.getPieceColor(xInit,yInit) == (Jeu.getPieceColor(xFinal,yFinal))){
				if (Jeu.castlingOk) {
				ret = true;
				}
				ret = false;
			}
			//sinon prendre la piece intermediaire (vigilance pour le cas du pion) et deplacer la piece
			else {
				if (Jeu.isPawnPromotion(xFinal,yFinal)) {
					ret = false;
				}
				Jeu.setPossibleCapture();//ret = true si capture possible
				ret = true;
			}
		}
		//sinon deplacer la piece
		else {
			ret = true;
		}
		if (ret) {
			this.setMessage("D�placement Ok");
		}
		else {
			this.setMessage("D�placement interdit");
		}
		return ret;
	}

	@Override
	public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
		//TODO impl�mentation de checkmatePossibility ?
		boolean ret;
		Jeu Jeu;
		if (this.getColorCurrentPlayer() == Couleur.BLANC) {
			Jeu = this.jeuBlanc;
		}
		else {
			Jeu = this.jeuNoir;
		}
		Pieces Piece = Jeu.findPiece(xInit,yInit); //Recup la pi�ce a la pos (xinit,yinit) afin de lui faire effectuer le d�placement
		if (this.isMoveOk(xInit, yInit, xFinal, yFinal)) {
			Piece.move(xFinal,yFinal);
			if (Jeu.capturePossible) {
				Jeu.capture(xFinal,yFinal);
			}
			ret = true;//Jusque la et si pas de possibilit� de mise en �chec, le d�placement est effectu�
			Coord KingCoord = Jeu.getKingCoord();
			if (Jeu.checkmatePossibility()){ //Etude des possibilit�s de mise en �chec
				Jeu.undoMove();
				if (Jeu.capturePossible) {
					Jeu.undoCapture();
				}
				ret = false;
			}
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
