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
		for (int i=0;i<Jeu.getPieceIHM().size();i++) {
			PieceIHM.add(Jeu.getPieceIHM().get(i)); //On récupère le i-eme élément
		}
		Jeu Jeu = this.jeuNoir;
		for (int i=0;i<Jeu.getPieceIHM().size();i++) {
			PieceIHM.add(Jeu.getPieceIHM().get(i)); //On récupère le i-eme élément
		}
		//TODO vérifier comment donner des accès en écriture
		return PieceIHM;
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
		if (!(Jeu.isPieceHere(xInit,yInit) && this.getPieceColor(xInit,yInit) != this.joueurCourant)) {
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
		else if (Jeu.intermediatePiece()) {
			if 	(Jeu.getPieceType(xInit,yInit).equals("Cavalier")) {
				ret = true;
			}
			ret = false;
		}
		//il existe une piece positionnees aux coordonnees finales :
		else if (Jeu.isPieceHere(xFinal,yFinal)) {
			//si elle est de la meme couleur
			if (Jeu.getPieceColor(xInit,yInit) == (Jeu.getPieceColor(xFinal,yFinal))){
				if (Jeu.setCastling()) {
				ret = true;
				}
				ret = false;
			}
			//sinon prendre la piece intermediaire (vigilance pour le cas du pion) et deplacer la piece
			else {
				if (Jeu.isPawnPromotion(xFinal,yFinal)) {
					//TODO vérifier comment mettre en place le type
					Jeu.pawnPromotion(xFinal,yFinal,type);
				}
				ret = Jeu.setPossibleCapture();//ret = true si capture possible
			}
		}
		//sinon deplacer la piece
		else {
			ret = true;
		}

		return ret;
	}

	@Override
	public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
		//TODO implémentation de checkmatePossibility ?
		boolean ret;
		Jeu Jeu;
		if (this.getColorCurrentPlayer() == Couleur.BLANC) {
			Jeu = this.jeuBlanc;
		}
		else {
			Jeu = this.jeuNoir;
		}
		Pieces Piece = Jeu.findPiece(xInit,yInit); //Recup la pièce a la pos (xinit,yinit) afin de lui faire effectuer le déplacement
		if (this.isMoveOk(xInit, yInit, xFinal, yFinal)) {
			Piece.move(xFinal,yFinal);
			if (Jeu.capturePossible) {
				Jeu.capture(xFinal,yFinal);
			}
			ret = true;//Jusque la et si pas de possibilité de mise en échec, le déplacement est effectué
			Coord KingCoord = Jeu.getKingCoord();
			if (Jeu.checkmatePossibility()){ //Etude des possibilités de mise en échec
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
