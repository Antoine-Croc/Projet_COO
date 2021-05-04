package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cavalier extends AbstractPiece {
	public Cavalier(Couleur couleur, Coord coord) {
		super(couleur, coord);
		super.name = "cavalier";
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		int compareX = xFinal - super.getX();
		int compareY = yFinal - super.getY();
		Integer[] val = { -1, -2, 1, 2 };
		List<Integer> intList = new ArrayList<>(Arrays.asList(val));
		if ((intList.contains(compareX)) && (intList.contains(compareX)) && (compareX != compareY) &&(compareX != -compareY)) {
			return true;
		} else {
			return false;
		}

	}
}
