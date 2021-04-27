package model;
import java.model.Jeu;

public class Echiquier extends java.lang.Object implements BoardGames {
	// Noms à vérifier
	private Jeu jeuNoir;
	private Jeu jeuBlanc;
	//Pas besoin de getter et setter car on a switchJoueur : on sait quel est le jeu courant et le jeu non courant
	private String message;
	private Couleur joueurCourant;
	
	public Echiquier() {
		this.jeuNoir = new Jeu(Couleur.NOIR);
		this.jeuBlanc = new Jeu(Couleur.BLANC);
		this.joueurCourant = Couleur.BLANC;
	}
	
	@Override
	public Couleur getColorCurrentPlayer() {
		// TODO 
		return null;
	}
	
	@Override
	public String getMessage() { 
		return message;
	}
	
	@Override
	public Couleur getPieceColor(int x, int y) {
		// TODO 
		return null;
	}
	
	private void setMessage(String newMessage) {
		message = newMessage;
	}
	
	
	
	
	@Override
	public boolean isEnd() {
		// TODO 
		return false;
	}
	
	public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal) {
		boolean ret;
		//il n'existe pas de piece du jeu courant aux coordonnées initiales
		if (Jeu.isPieceHere()) {
			
		}
		// les coordonnées finales ne sont pas valides ou égales aux initiales
		else if {
					
		}
		//position finale ne correspond pas à algo de déplacement piece
		else if {
					
		}
		//il existe une piéce intermédiaire sur la trajectoire (sauf cavalier)
		else if {
					
		}
		//il existe une piéce positionnées aux coordonnées finales :
		else if {
			//si elle est de la méme couleur
			if {
				
			}
			//sinon prendre la piéce intermédiaire (vigilance pour le cas du pion) et déplacer la piéce
			else if {
				
			}
			//sinon déplacer la piéce
			else {
				
			}
		}
			
			
		
		return ret;
	}

	@Override
	public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
		// TODO
		return false;
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
	








}
