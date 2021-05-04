package model;

import java.util.List;
import java.util.LinkedList;

import tools.ChessPiecePos;
import tools.ChessPiecesFactory;
import tools.Introspection;

public class Jeu {
	private List<Pieces> pieces;
	private Couleur couleur;

	public Jeu(Couleur couleur) {
		this.pieces = ChessPiecesFactory.newPieces(couleur);
	}

	@Override
	public String toString() {

		return this.pieces.toString();
	}

	public static void main(String[] args) {
		System.out.println(new Jeu(Couleur.BLANC));
	}

	private Pieces findPieces(int x, int y) {
		for (Pieces piece : this.pieces) {
			if (piece.getX() == x && piece.getY() == y) {
				return piece;
			}

		}
		return null;
	}

	public boolean capture(int xCatch, int yCatch) {
		Pieces pieceC = findPieces(xCatch, yCatch);
		if (pieceC != null) {
			return pieceC.capture();
		}
		return false;
	}

	public Couleur getCouleur() {
		return this.couleur;

	}

	public Couleur getPieceColor(int x, int y) {
		Pieces pieceC = findPieces(x, y);
		if (pieceC != null) {
			return pieceC.getCouleur();
		}
		return null;
	}

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

	public String getPieceType(int x, int y) {
		Pieces pieceC = findPieces(x, y);
		if (pieceC != null) {
			return pieceC.getClass().getSimpleName();
		}
		return null;

	}

	public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal) {
		Pieces pieceC = findPieces(xInit, yInit);
		if (pieceC != null) {
			return pieceC.isMoveOk(xFinal, yFinal);

		}

		return false;
	}

	public boolean isPawnPromotion(int xFinal, int yfinal) {

		if (yfinal == 7) {
			Pieces pieceC = findPieces(xFinal, yfinal);
			if (pieceC != null && pieceC instanceof Tour) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean isPieceHere(int x, int y) {
		return findPieces(x, y) != null;
		/*
		 * Pieces pieceC = findPieces(x,y); if (pieceC != null) { return true; }return
		 * false;
		 */
	}

	public boolean move(int xInit, int yInit, int xFinal, int yFinal) {

		if (xInit == xFinal && yInit == yFinal) {
			return false;
		} else {
			Pieces pieceC = findPieces(xInit, yInit);
			if (pieceC != null) {
				return pieceC.move(xFinal, yFinal);
			} else {
				return false;
			}
		}
	}

	public boolean pawnPromotion(int xFinal, int yfinal, String type) {
		if (this.isPawnPromotion(xFinal, yfinal)) {
			Pieces pieceC = findPieces(xFinal, yfinal);
			this.pieces.remove(pieceC);
			String className = "model." + type;
			Coord pieceCoord = new Coord(xFinal,yfinal);
			pieces.add((Pieces) Introspection.newInstance (className,
					new Object[] {this.couleur, pieceCoord}));
			return true;
		} else {
			return false;
		}

	}
}
