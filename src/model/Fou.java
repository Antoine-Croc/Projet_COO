package model;

public class Fou extends AbstractPiece {
	public Fou(Couleur couleur, Coord coord) {
		super(couleur, coord);
		super.name = "fou";
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		int sommeX = xFinal - super.getX();
		int sommeY = yFinal - super.getY();
		for (int i = 0; i < 8;) {
			if (((sommeX == i) && (sommeY == i)) || ((sommeX == -i) && (sommeY == -i))
					|| ((sommeX == i) && (sommeY == -i)) || ((sommeX == -i) && (sommeY == i))) {
				return true;
			}
			i++;
		}
		return false;
	}
}
