package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import tools.ChessPiecesFactory;
import tools.Introspection;

public class Jeu {
	private List<Pieces> pieces;
	private Couleur couleur;
	private boolean roque_roi  = false;
	private boolean capture_possible = false;
	
	private LinkedList<Coord> history_coord;//ex:{positionInit_1,positionFinal_1,  positionInit_2,positionFinal_2}
	private LinkedList<Object>  history_capture ;//ex:{Tour,(1,2),  Roi,(2,2) , Rein,(3,2)}
	// constructor
	public Jeu(Couleur couleur) {
		this.pieces = ChessPiecesFactory.newPieces(couleur);
		this.couleur = couleur;
		this.history_coord = new LinkedList<Coord>();
		this.history_capture = new LinkedList<Object>();

	}

	// getter & setter

	// couleur du jeu
	public Couleur getCouleur() {
		return this.couleur;
	}

	// coordonnées du roi
	public Coord getKingCoord() {
		Coord coord = null;
		Iterator<Pieces> ite = this.pieces.iterator();
		while (ite.hasNext()) {
			Pieces piece = ite.next();
			if (piece.getClass().getSimpleName() == "Roi") {
				coord = new Coord(piece.getX(), piece.getY());
				break;
			}
		}
		return coord;
	}

	public boolean getRoque() {
		return this.roque_roi;
	}
	public boolean getCapturePossible() {
		return this.capture_possible;
	}

	@Override
	public java.lang.String toString() {
		String result = "";
		result = pieces.toString();
		return result;
	}

	/**
	 * @return une vue de la liste des pièces en cours ne donnant que des accès en
	 *         lecture sur des PieceIHM (type piece + couleur + liste de
	 *         coordonnées)
	 */
	public List<PieceIHM> getPiecesIHM() {
		PieceIHM newPieceIHM = null;
		List<PieceIHM> list = new LinkedList<PieceIHM>();

		for (Pieces piece : pieces) {
			boolean existe = false;
			// si le type de piece existe déjà dans la liste de PieceIHM
			// ajout des coordonnées de la pièce dans la liste de Coord de ce type
			// si elle est toujours en jeu (x et y != -1)
			for (PieceIHM pieceIHM : list) {
				if ((pieceIHM.getTypePiece()).equals(piece.getClass().getSimpleName())) {
					existe = true;
					if (piece.getX() != -1) {
						pieceIHM.add(new Coord(piece.getX(), piece.getY()));
					}
				}
			}
			// sinon, création d'une nouvelle PieceIHM si la pièce est toujours en jeu
			if (!existe) {
				if (piece.getX() != -1) {
					newPieceIHM = new PieceIHM(piece.getClass().getSimpleName(), piece.getCouleur());
					newPieceIHM.add(new Coord(piece.getX(), piece.getY()));
					list.add(newPieceIHM);
				}
			}
		}
		return list;
	}

	// methodes

	// true si une pièce se trouve aux coordonnÃ©es indiquÃ©es
	public boolean isPieceHere(int x, int y) {
		return findPieces(x, y) != null;
	}


	// true si piece du jeu peut être déplacée aux coordonnées finales, false sinon
	public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal) {
		boolean result = false;
		Pieces piece = findPieces( xInit, yInit) ; 
		if(piece != null) {
			if (piece.isMoveOk(xFinal, yFinal)) {
					result = true;
				}
			}
		return result;
	}

//TODO  si déplacement pièce effectué
	// true si déplacement pièce effectué
	public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
		boolean result = false;
		Pieces piece = findPieces( xInit, yInit) ; 
		if (piece !=null && piece.isMoveOk(xFinal, yFinal)) {
			piece.move(xFinal, yFinal);
			result = true;
			//MAJ l'histoire de deplacements
			this.history_coord.addLast(new Coord(xInit,yInit));
			this.history_coord.addLast(new Coord(xFinal,yFinal));
			
		}

		return result;

	}


	public void undoMove() {
		Coord finalCoord= this.history_coord.pollLast();
		Coord initCoord= this.history_coord.pollLast();
		move(finalCoord.x,finalCoord.y,initCoord.x,initCoord.y) ;
	}

//TODO  in 2nd iteration
	// Si une capture d'une pièce de l'autre jeu est possible met à jour 1 booléen
	public void setPossibleCapture() {
		this.capture_possible = true;
	}

//TODO in 2nd  iteration
	// true si la piece aux coordonnées finales a été capturée
	public boolean capture(int xCatch, int yCatch) {
		boolean result = false;
		if (this.capture_possible) {
			Pieces piece = findPieces( xCatch, yCatch) ; 
			
			history_capture.addLast(piece);//ajouter la piece dans l'historique
			history_capture.addLast(new Coord(xCatch,yCatch));//ajouter la position  dans l'historique
			System.out.println(piece);
			piece.move(-1, -1); //deplacer la piece à (-1 -1)
			this.capture_possible = false;
			result = true;
		}
		return result;
	}

//TODO in 2nd  iteration
	public void undoCapture() {
		Coord coord =(Coord) history_capture.pollLast();
		Pieces piece = (Pieces)history_capture.pollLast();
		piece.move(coord.x,coord.y); //restorer la position
	}

	// couleur de la pièce aux coordonnées x, y
	public Couleur getPieceColor(int x, int y) {
		Couleur couleur = null;
		Pieces piece = findPieces( x, y) ; 
			if (piece !=null) {
				couleur = piece.getCouleur();
			}
		return couleur;
	}

	// type de la piece aux coordonnees x,y c'est a dire le nom de la classe
	public java.lang.String getPieceType(int x, int y) {
		String nomType = null;
		Pieces piece = findPieces( x, y) ; 
		if (piece !=null) {
				nomType = piece.getClass().getSimpleName();
		}
		return nomType;

	}
//TODO à voir
	// true si on est bien dans le cas d'une promotion du pion
	public boolean isPawnPromotion(int xFinal, int yFinal) {
		Boolean resultat = false;
		if ( (getCouleur() == Couleur.BLANC && yFinal == 0 )
				|| (getCouleur() == Couleur.NOIR && yFinal == 7 ) ) {
			
			Pieces pieceC = findPieces(xFinal, yFinal);
			if (pieceC != null && pieceC instanceof Tour) {
				resultat = true;
			}
			} 
		return resultat;
	}

	// true si promotion OK
	public boolean pawnPromotion(int xFinal, int yfinal, String type) {
		Boolean resultat = false;
		Set<String> allTypes = new HashSet<>(Arrays.asList("Tour","Reine","Fou","Cavalier"));
		if (this.isPawnPromotion(xFinal, yfinal) && allTypes.contains(type)) {
			Pieces pieceC = findPieces(xFinal, yfinal);
			this.pieces.remove(pieceC);
			String className = "model." + type;
			Coord pieceCoord = new Coord(xFinal,yfinal);
			pieces.add((Pieces) Introspection.newInstance (className,
					new Object[] {this.couleur, pieceCoord}));
			resultat = true;
		} 
		return resultat;
	}
	
	

	
//TODO setCastling
	public void setCastling() {
/*
		Pieces piece_roi = null;
		int position_y_roi = -1;
		Set<Integer> position_y_tour = new HashSet<>();
		
		Iterator<Pieces> ite = this.pieces.iterator();
		while (ite.hasNext()) {
			Pieces piece = ite.next();
			if (piece.getClass().getSimpleName() == "Roi") {
				position_y_roi = piece.getY();
			}
			if (piece.getClass().getSimpleName() == "Tour") {
				position_y_tour.add(piece.getY());
			}
			
		if(position_y_tour.contains(position_y_roi)) {
			this.roque_roi=true;
		}
			
		}
*/
		this.roque_roi=true;
	}
	private Pieces findPieces(int xInit,int yInit) {
		Pieces piece_resultat = null;
		Iterator<Pieces> ite = this.pieces.iterator();
		while (ite.hasNext()) {
			Pieces piece = ite.next();
			if (piece.getX() == xInit && piece.getY() == yInit) {
				piece_resultat = piece;
				break;
			}
		}
		return piece_resultat;
	}
	
	
	 public static void main(String[] args) {
		 
		 Jeu jeu = new Jeu(Couleur.BLANC);
		 System.out.println(jeu);
		 System.out.println(jeu.move(0, 7, 0, 8));
		 System.out.println(jeu);
		 System.out.println(jeu.getPiecesIHM());
	 }

}
