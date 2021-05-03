package model;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import tools.ChessPiecesFactory;

public class Jeu {
    private List<Pieces> list_pieces ;
    private Couleur couleur;
    
    //constructor
    public Jeu(Couleur couleur){
        this.list_pieces = ChessPiecesFactory.newPieces(couleur);
        this.couleur = couleur;
    }
    
    //getter & setter
    
    //couleur du jeu
    public Couleur getCouleur() {
    	return this.couleur;
    }
    
    //coordonnées du roi
    public Coord getKingCoord() {
    	Coord coord = null;
    	 Iterator<Pieces> ite = this.list_pieces.iterator();
         while(ite.hasNext())
         {
             Pieces piece = ite.next();
             if(piece.getClass().getSimpleName() =="Roi"){
            	 coord =  new Coord(piece.getX(),piece.getY());
            	 break;
                 }
             }
         return coord;
    }
    
    @Override 
    public java.lang.String toString(){
    	String result = "";
    	result = list_pieces.toString();
    	return result;
    }
 
//TODO getPiecesIHM
    public java.util.List<PieceIHM> getPiecesIHM(){
    	return null;
    }
    
    
    //methodes

    //true si une pièce se trouve aux coordonnÃ©es indiquÃ©es
    public boolean isPieceHere(int x, int y){
        boolean result = false;
        Iterator<Pieces> ite = this.list_pieces.iterator();
        while(ite.hasNext())
        {
            Pieces piece = ite.next();
            if(piece.getX() == x && piece.getY() == y){
                result = true;
                break;
                }
            }

        return result;
    }
    
    //true si piece du jeu peut être déplacée aux coordonnées finales, false sinon
    public boolean isMoveOk(int xInit, int yInit,int xFinal,int yFinal) {
	    boolean result = false;
	    Iterator<Pieces> ite = this.list_pieces.iterator();
        while(ite.hasNext())
        {
            Pieces piece = ite.next();
            //trouver la pièce située à la position(xInit,yInit)
            if(piece.getX() == xInit && piece.getY() == xInit){
            	//si la piece peut y deplacer
            	if( piece.isMoveOk( xFinal, yFinal)) {
	                result = true;
	                break;
            		}
                }
            }

	    
	    return result;
 }
    
//TODO  si déplacement pièce effectué
    //true si déplacement pièce effectué
    public boolean move(int xInit,int yInit,int xFinal,int yFinal) {
    	boolean result = false;
    	
    	return result;
    	
    }
//TODO  in 2nd iteration
    //Si une capture d'une pièce de l'autre jeu est possible met à jour 1 booléen 
    public void setPossibleCapture() {
    	
    }
//TODO in 2nd  iteration
    //true si la piece aux coordonnées finales a été capturée
    public boolean capture(int xCatch,int yCatch) {
    	boolean result = false;
    	
    	return result;
    }
    
//TODO in 2nd  iteration
    public void undoCapture() {
    	
    }
    
    //couleur de la pièce aux coordonnées x, y
    public Couleur getPieceColor(int x,int y){
    	Couleur couleur = null;
    	Iterator<Pieces> ite = this.list_pieces.iterator();
        while(ite.hasNext())
        {
            Pieces piece = ite.next();
            if(piece.getX() == x && piece.getY() == y){
            	couleur = piece.getCouleur();
                }
            }
        return couleur;
    }
    
   //type de la piece aux coordonnees x,y c'est a dire le nom de la classe 
    public java.lang.String getPieceType(int x,int y) {
    	String nomType = null;
    	Iterator<Pieces> ite = this.list_pieces.iterator();
        while(ite.hasNext())
        {
            Pieces piece = ite.next();
            if(piece.getX() == x && piece.getY() == y){
            	nomType = piece.getClass().getSimpleName();
                }
            }
        return nomType;
    	
    }

//TODO undoMove() 
    public void undoMove() {
    	
    	
    }
    
    
    //true si on est bien dresult le cas d'une promotion du pion
    public boolean isPawnPromotion(int xFinal,int yfinal) {
    	Boolean result = false;
    	//on vérifie juste la position ( pas de vérification de la classe)
    	if ( (getCouleur() == Couleur.BLANC && yfinal == 0 ) || (getCouleur() == Couleur.NOIR && yfinal == 7 ) ) {
    		result = true;
    	}
    	return result;
    }
    
    //true si promotion OK
    public boolean pawnPromotion(int xFinal,int yfinal,java.lang.String type) {
    	Boolean result = false;
    	Set<String> allTypes = new HashSet<>(Arrays.asList("Tour","Reine","Fou","Cavalier"));
    	
    	//si la position est bon et le type à remplacer est bon aussi
    	if(isPawnPromotion( xFinal, yfinal) && allTypes.contains(type)) {
    		result = true;
    	}
    	return result; 
    }

}
