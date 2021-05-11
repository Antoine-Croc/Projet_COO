package model;
import model.Jeu;
import model.PieceIHM;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		List<PieceIHM> PieceIHM = new LinkedList<PieceIHM>(); //TODO vérifier si on a besoin d'une arrayList ou si un autre type nous conviendrait
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
		Jeu jeuCourant;
		Jeu jeuAdverse;
		if (this.getColorCurrentPlayer() == Couleur.BLANC) {
			jeuCourant = this.jeuBlanc;
			jeuAdverse = this.jeuNoir;
		}
		else {
			jeuCourant = this.jeuNoir;
			jeuAdverse = this.jeuBlanc;
		}
		//il n'existe pas de piece du jeu courant aux coordonnees initiales
		if (!(jeuCourant.isPieceHere(xInit,yInit))) {
			ret = false;
		}
		// les coordonnees finales ne sont pas valides ou egales aux initiales
		//position finale ne correspond pas a algo de deplacement piece
		else if (!(jeuCourant.isMoveOk(xInit,yInit,xFinal,yFinal))) {
			this.setMessage("KO : la position finale ne correspond pas a l'aglo de deplacement legal de la piece");
			ret	= false;	
		}
	
		//il existe une piece intermediaire sur la trajectoire (sauf cavalier)
		//TODO trouver la bonne fonction
		/*
		else if (JeuCourant.intermediatePiece(xInit, yInit, xFinal, yFinal) || jeuAdverse.intermediatePiece(xInit, yInit, xFinal, yFinal)) {
			if 	(Jeu.getPieceType(xInit,yInit).equals("Cavalier")) {
				ret = true;
			}
			ret = false;
		}
		*/
		//il existe une piece positionnees aux coordonnees finales :
		else if (jeuCourant.isPieceHere(xFinal,yFinal)) {
			//si elle est de la meme couleur
			if (jeuCourant.getRoque()) {
				this.setMessage("Possibilité de roque");
				ret = true;
			}
			else if (jeuCourant.isPawnPromotion(xFinal,yFinal)) {
				this.setMessage("Possibilité de promotion du pion");
				ret = false; //A confirmer
			}
			else {
				ret = false;
				this.setMessage("Piece présente aux coordonnees finales");
			}
		}
			//sinon prendre la piece intermediaire (vigilance pour le cas du pion) et deplacer la piece		
		else if (jeuAdverse.isPieceHere(xFinal, yFinal)) {
			jeuAdverse.setPossibleCapture();
			ret = true;
			this.setMessage("OK : Deplacement + capture");
		}
		//sinon deplacer la piece
		else {
			this.setMessage("OK : Deplacement simple");
			ret = true;
		}
		
		return ret;
	}

	@Override
	public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
		//TODO implémentation de checkmatePossibility ?
		boolean ret;
		Jeu jeuCourant;
		Jeu jeuAdverse;
		if (this.getColorCurrentPlayer() == Couleur.BLANC) {
			jeuCourant = this.jeuBlanc;
			jeuAdverse = this.jeuNoir;
		}
		else {
			jeuCourant = this.jeuNoir;
			jeuAdverse = this.jeuBlanc;
		}
		if (this.isMoveOk(xInit, yInit, xFinal, yFinal)) {
			jeuCourant.move(xInit, yInit, xFinal, yFinal);
			if (jeuAdverse.getCapturePossible()) {
				jeuAdverse.capture(xFinal,yFinal);
			}
			ret = true;//Jusque la et si pas de possibilité de mise en échec, le déplacement est effectué
			Coord KingCoord = jeuCourant.getKingCoord();
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
	


	// il ne calcule pas la position  initiale et finale
	//valable que pour les deplacements droits
	public  List<Coord>  inter_coord(int xinit,int  yinit,int xfinal,int yfinal) {
		 int ychemin =yfinal-yinit;
		 int xchemin =xfinal-xinit;
		 int x_inter = intervalle(xchemin);
		 int y_inter = intervalle(ychemin);
		 List<Coord> list_coord = new ArrayList<Coord>();
		 
		 int number_sequence = Math.max(Math.abs(xchemin), Math.abs(ychemin)) ;
		 
		 List<Integer> x_list = Stream.iterate(0, n -> n + x_inter)
                 .limit(number_sequence)
                 .collect(Collectors.toList());
		 List<Integer> y_list = Stream.iterate(0, n -> n + y_inter)
                 .limit(number_sequence)
                 .collect(Collectors.toList());
		 
		 for(int i =1 ;i<number_sequence;i++) {
			 list_coord.add(new Coord(xinit+x_list.get(i),yinit+y_list.get(i) ));
		 }
		 
		 System.out.println(list_coord);
		 
		return list_coord;
		 
	 }
	 
	 private int intervalle(int x) {
		 if (x==0) {return 0;}
		 return x >0? 1:-1;
	 }


	 public static void main(String[] args) {
		 
		 Echiquier echiquier = new Echiquier();
		 System.out.println(echiquier.getPiecesIHM());
		 System.out.println(echiquier.move(0, 7, 0, 5));
		 System.out.println(echiquier.getPiecesIHM());
		 echiquier.inter_coord(3,4,0,1);
		 echiquier.inter_coord(3,4,0,7);
	 }




}
