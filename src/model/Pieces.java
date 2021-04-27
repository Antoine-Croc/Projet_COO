package model;

/**
 * @author francoise.perrin Inspiration Jacques SARAYDARYAN, Adrien GUENARD *
 * 
 * Cette interface défini le comportement attendu 
 * des pièces du jeu
 *
 */


public interface Pieces {

	public boolean capture(); 

	/**
	 * @return true si piece effectivement captur�e Positionne x et y à -1
	 */
	
	public int getX();
	
	/**
	 * @return indice de la colonne où est positionnée la piece
	 */
	
	public int getY();
	
	/**
	 * @return indice de la ligne où est positionnée la piece
	 */
	public boolean isMoveOk(int xFinal, int yFinal); 

	/**
	 * @return true si déplacement légal en fonction des algo de déplacement spécifique de chaque pièce
	 */
	public boolean move(int xFinal, int yFinal); 

	/**
	 * @return true si déplacement effectué
	 */
	
}
