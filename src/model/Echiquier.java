package model;
import model.Jeu;
import model.PieceIHM;

import java.util.ArrayList;
import java.util.LinkedList;
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
		this.message = "Bienvenue dans le jeu d'echecs du module de COO";
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
		Couleur ret;
		if (this.joueurCourant == Couleur.BLANC) {
			ret = this.jeuBlanc.getPieceColor(x,y);
		}
		else {
			ret = this.jeuNoir.getPieceColor(x,y);
		}
		return ret;
	}
	
	public List<PieceIHM> getPiecesIHM(){
		//TODO faire valider le fonctionnement
		List<PieceIHM> PieceIHM = new LinkedList<PieceIHM>(); //TODO v�rifier si on a besoin d'une arrayList ou si un autre type nous conviendrait
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
		Jeu jeu;
		boolean ret = false;
		if (this.getColorCurrentPlayer() == Couleur.BLANC) {
			jeu = this.jeuBlanc;
		}
		else {
			jeu = this.jeuNoir;
		}
		//TODO impl�menter possibilit� echec et mat
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
		if (!(jeu.isPieceHere(xInit,yInit))) {
			ret = false;
		}
		
		if (this.getPieceColor(xInit,yInit) != this.joueurCourant) {
			this.setMessage("KO : c'est au tour de l'autre joueur");
			ret = false;
		}
		// les coordonnees finales ne sont pas valides ou egales aux initiales
		//position finale ne correspond pas a algo de deplacement piece
		else if (!(jeu.isMoveOk(xInit,yInit,xFinal,yFinal))) {
			this.setMessage("KO : la position finale ne correspond pas a l'aglo de deplacement legal de la piece");
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
				this.setMessage("Piece pr�sente aux coordonnees finales");
				ret = false;
			}
			//sinon prendre la piece intermediaire (vigilance pour le cas du pion) et deplacer la piece
			else {
				if (jeu.isPawnPromotion(xFinal,yFinal)) {
					this.setMessage("Possibilit� de promotion du pion");
					ret = false;
				}
				if (this.getColorCurrentPlayer() == Couleur.BLANC) {
					jeuNoir.setPossibleCapture();//ret = true si capture possible
					this.setMessage("Capture set Noir");
				}
				else {
					jeuBlanc.setPossibleCapture();
					this.setMessage("Capture set Blanc");
				}
				ret = true;
			}
		}
		//sinon deplacer la piece
		else {
			this.setMessage("OK : Deplacement");
			ret = true;
		}
		return ret;
	}

	@Override
	public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
		//TODO impl�mentation de checkmatePossibility ?
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
				String messageConcat = this.getMessage();
				messageConcat += "+ capture";
				this.setMessage(messageConcat);
			}
			else {
				String messageConcat = this.getMessage();
				messageConcat += " simple";
				this.setMessage(messageConcat);
			}
			ret = true;//Jusque la et si pas de possibilit� de mise en �chec, le d�placement est effectu�
			Coord KingCoord = jeu.getKingCoord();
			/*
			 //TODO impl�menter possibilit� �chec et mat
			if (jeu.checkmatePossibility()){ //Etude des possibilit�s de mise en �chec
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
	



	 public static void main(String[] args) {
		 
		 Echiquier echiquier = new Echiquier();
		 System.out.println(echiquier.getPiecesIHM());
		 System.out.println(echiquier.move(0, 7, 0, 5));
		 System.out.println(echiquier.getPiecesIHM());
		 
	 }




}
