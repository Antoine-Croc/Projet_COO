package model;

public abstract class AbstractPiece implements Pieces{
    private Couleur couleur;
    private Coord coord;
    private String name;

    //constructeur
    public AbstractPiece(){
    }

    public AbstractPiece(Couleur couleur, Coord coord){
        this.couleur = couleur;
        this.coord = coord;
        this.name = this.getClass().getSimpleName();
    }

    //getter

    //indice de la colonne où est positionnée la piece
    public int getX(){
        return this.coord.x;
    }

    //indice de la ligne où est positionnée la piece
    public int getY(){
        return this.coord.y;
    }

    //couleur de la piece
    public Couleur getCouleur(){
        return this.couleur;
    }


    //méthodes
    @Override
    public String toString() {
        return "[name=" + this.name + ", x=" + getX() + ", y="+getY() +"]";
    }

    //true si déplacement légal en fonction des algo de déplacement spécifique de chaque pièce
    public abstract  boolean isMoveOk(int xFinal, int yFinal);

    //true si déplacement effectué
    public boolean move(int xFinal, int yFinal){
        boolean ans = false;
        coord.x = xFinal;
        coord.y = yFinal;
        if(getX()==xFinal && getY()==yFinal){
            ans = true;
        }
        return ans;
    }

    //true si piece effectivement capturée Positionne x et y à -1
    public boolean capture(){
        boolean ans = false;
        if(getX()==-1 && getY()==-1){
            ans = true;
        }
        return ans;
    }

    public static void main(String[] args) {
        Coord coord = new Coord(2,2);
        Tour tour = new Tour(Couleur.BLANC,coord);
        System.out.println(tour);
        Coord coordTest = new Coord(3,3);
        System.out.println("tour peut se d�placer �"+coordTest +"?->" +tour.isMoveOk(coordTest.x,coordTest.y));
        coordTest = new Coord(2,5);
        System.out.println("tour peut se d�placer �"+coordTest +"?->" +tour.isMoveOk(coordTest.x,coordTest.y));
        
    }
}
