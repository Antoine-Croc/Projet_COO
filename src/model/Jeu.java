package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import tools.ChessPiecesFactory;

public class Jeu {
	private List<Pieces> list_pieces;
	private Couleur couleur;
	private boolean roque_roi  = false;
	private boolean capture_possible = false;
	// constructor
	public Jeu(Couleur couleur) {
		this.list_pieces = ChessPiecesFactory.newPieces(couleur);
		this.couleur = couleur;
	}

	// getter & setter

	// couleur du jeu
	public Couleur getCouleur() {
		return this.couleur;
	}

	// coordonn�es du roi
	public Coord getKingCoord() {
		Coord coord = null;
		Iterator<Pieces> ite = this.list_pieces.iterator();
		while (ite.hasNext()) {
			Pieces piece = ite.next();
			if (piece.getClass().getSimpleName() == "Roi") {
				coord = new Coord(piece.getX(), piece.getY());
				break;
			}
		}
		return coord;
	}

	@Override
	public java.lang.String toString() {
		String result = "";
		result = list_pieces.toString();
		return result;
	}

	/**
	 * @return une vue de la liste des pi�ces en cours ne donnant que des acc�s en
	 *         lecture sur des PieceIHM (type piece + couleur + liste de
	 *         coordonn�es)
	 */
	public List<PieceIHM> getPiecesIHM() {
		PieceIHM newPieceIHM = null;
		List<PieceIHM> list = new LinkedList<PieceIHM>();

		for (Pieces piece : list_pieces) {
			boolean existe = false;
			// si le type de piece existe d�j� dans la liste de PieceIHM
			// ajout des coordonn�es de la pi�ce dans la liste de Coord de ce type
			// si elle est toujours en jeu (x et y != -1)
			for (PieceIHM pieceIHM : list) {
				if ((pieceIHM.getTypePiece()).equals(piece.getClass().getSimpleName())) {
					existe = true;
					if (piece.getX() != -1) {
						pieceIHM.add(new Coord(piece.getX(), piece.getY()));
					}
				}
			}
			// sinon, cr�ation d'une nouvelle PieceIHM si la pi�ce est toujours en jeu
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

	// true si une pi�ce se trouve aux coordonnées indiquées
	public boolean isPieceHere(int x, int y) {
		boolean result = false;
		Iterator<Pieces> ite = this.list_pieces.iterator();
		while (ite.hasNext()) {
			Pieces piece = ite.next();
			if (piece.getX() == x && piece.getY() == y) {
				result = true;
				break;
			}
		}

		return result;
	}


	// true si piece du jeu peut �tre d�plac�e aux coordonn�es finales, false sinon
	public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal) {
		boolean result = false;
		Pieces piece = findPiece( xInit, yInit) ; 
		if(piece != null) {
			if (piece.isMoveOk(xFinal, yFinal)) {
					result = true;
				}
			}
		return result;
	}

//TODO  si d�placement pi�ce effectu�
	// true si d�placement pi�ce effectu�
	public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
		boolean result = false;
		Pieces piece = findPiece( xInit, yInit) ; 
		if (piece !=null && piece.isMoveOk(xFinal, yFinal)) {
			piece.move(xFinal, yFinal);
			result = true;
		}

		return result;

	}

// TODO in 2nd iteration
	public void undoMove() {

	}

//TODO  in 2nd iteration
	// Si une capture d'une pi�ce de l'autre jeu est possible met � jour 1 bool�en
	public void setPossibleCapture() {
		this.capture_possible = false;
	}

//TODO in 2nd  iteration
	// true si la piece aux coordonn�es finales a �t� captur�e
	public boolean capture(int xCatch, int yCatch) {
		boolean result = false;

		return result;
	}

//TODO in 2nd  iteration
	public void undoCapture() {

	}

	// couleur de la pi�ce aux coordonn�es x, y
	public Couleur getPieceColor(int x, int y) {
		Couleur couleur = null;
		Pieces piece = findPiece( x, y) ; 
			if (piece !=null) {
				couleur = piece.getCouleur();
			}
		return couleur;
	}

	// type de la piece aux coordonnees x,y c'est a dire le nom de la classe
	public java.lang.String getPieceType(int x, int y) {
		String nomType = null;
		Pieces piece = findPiece( x, y) ; 
		if (piece !=null) {
				nomType = piece.getClass().getSimpleName();
		}
		return nomType;

	}

	// true si on est bien dans le cas d'une promotion du pion
	public boolean isPawnPromotion(int xFinal, int yfinal) {
		Boolean result = false;
		// on v�rifie juste la position ( pas de v�rification de la classe)
		if ((getCouleur() == Couleur.BLANC && yfinal == 0) || (getCouleur() == Couleur.NOIR && yfinal == 7)) {
			result = true;
		}
		return result;
	}

	// true si promotion OK
	public boolean pawnPromotion(int xFinal, int yfinal, java.lang.String type) {
		Boolean result = false;
		Set<String> allTypes = new HashSet<>(Arrays.asList("Tour", "Reine", "Fou", "Cavalier"));

		// si la position est bon et le type � remplacer est bon aussi
		if (isPawnPromotion(xFinal, yfinal) && allTypes.contains(type)) {
			result = true;
		}
		return result;
	}
	
	public void setCastling() {
/*
		Pieces piece_roi = null;
		int position_y_roi = -1;
		Set<Integer> position_y_tour = new HashSet<>();
		
		Iterator<Pieces> ite = this.list_pieces.iterator();
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
	private Pieces findPiece(int xInit,int yInit) {
		Pieces piece_resultat = null;
		Iterator<Pieces> ite = this.list_pieces.iterator();
		while (ite.hasNext()) {
			Pieces piece = ite.next();
			if (piece.getX() == xInit && piece.getY() == yInit) {
				piece_resultat = piece;
				break;
			}
		}
		return piece_resultat;
	}
	

}
