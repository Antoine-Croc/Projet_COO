package model;

public class Fou extends AbstractPiece {
	public Fou(Couleur couleur, Coord coord) {
		super(couleur, coord);
		super.name = "fou";
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		int sommeXY = super.getX() + super.getY();
		int sommexy_final = xFinal + yFinal;
		int sousXY = super.getX() - super.getY();
		int sousxy_final = xFinal - yFinal;
		if ((sommeXY == sommexy_final) || (sousXY == sousxy_final)) {
			return true;
		} else {
			return false;
		}

	}
}
