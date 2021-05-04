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
        System.out.println("-------------test"+tour+"-------------");
        Coord coordTest = new Coord(3,3); //False
        System.out.println("tour peut se d�placer �"+coordTest +"?->" +tour.isMoveOk(coordTest.x,coordTest.y));
        coordTest = new Coord(2,5);
        System.out.println("tour peut se d�placer �"+coordTest +"?->" +tour.isMoveOk(coordTest.x,coordTest.y));
        coordTest = new Coord(3,2);
        System.out.println("tour peut se d�placer �"+coordTest +"?->" +tour.isMoveOk(coordTest.x,coordTest.y));
        
        Roi roi = new Roi(Couleur.BLANC,coord);
        System.out.println("-------------test"+roi+"-------------");
        coordTest = new Coord(3,3);
        System.out.println("Roi peut se d�placer �"+coordTest +"?->" +roi.isMoveOk(coordTest.x,coordTest.y));
        coordTest = new Coord(2,3);
        System.out.println("Roi peut se d�placer �"+coordTest +"?->" +roi.isMoveOk(coordTest.x,coordTest.y));
        coordTest = new Coord(1,2);
        System.out.println("Roi peut se d�placer �"+coordTest +"?->" +roi.isMoveOk(coordTest.x,coordTest.y));
        coordTest = new Coord(1,4);//false
        System.out.println("Roi peut se d�placer �"+coordTest +"?->" +roi.isMoveOk(coordTest.x,coordTest.y));

        Reine reine = new Reine(Couleur.BLANC,coord);
        System.out.println("-------------test"+reine+"-------------");
        coordTest = new Coord(2,5);
        System.out.println("Reine peut se d�placer �"+coordTest +"?->" +reine.isMoveOk(coordTest.x,coordTest.y));
        coordTest = new Coord(3,2);
        System.out.println("Reine peut se d�placer �"+coordTest +"?->" +reine.isMoveOk(coordTest.x,coordTest.y));
        coordTest = new Coord(0,3);
        System.out.println("Reine peut se d�placer �"+coordTest +"?->" +reine.isMoveOk(coordTest.x,coordTest.y));
        coordTest = new Coord(0,4);//false
        System.out.println("Reine peut se d�placer �"+coordTest +"?->" +reine.isMoveOk(coordTest.x,coordTest.y));
        
        Cavalier cavalier = new Cavalier(Couleur.BLANC,coord);
        System.out.println("-------------test"+cavalier+"-------------");
        coordTest = new Coord(3,1);
        System.out.println("cavalier peut se d�placer �"+coordTest +"?->" +cavalier.isMoveOk(coordTest.x,coordTest.y));
        coordTest = new Coord(1,1);
        System.out.println("cavalier peut se d�placer �"+coordTest +"?->" +cavalier.isMoveOk(coordTest.x,coordTest.y));
        coordTest = new Coord(1,3); 
        System.out.println("cavalier peut se d�placer �"+coordTest +"?->" +cavalier.isMoveOk(coordTest.x,coordTest.y));
        coordTest = new Coord(0,3); //True
        System.out.println("cavalier peut se d�placer �"+coordTest +"?->" +cavalier.isMoveOk(coordTest.x,coordTest.y));
        
        Fou fou = new Fou(Couleur.BLANC,coord);
        System.out.println("-------------test"+fou+"-------------");
        coordTest = new Coord(4,0);
        System.out.println("Fou peut se d�placer �"+coordTest +"?->" +fou.isMoveOk(coordTest.x,coordTest.y));
        coordTest = new Coord(1,4);
        System.out.println("Fou peut se d�placer �"+coordTest +"?->" +fou.isMoveOk(coordTest.x,coordTest.y));
        coordTest = new Coord(0,0);
        System.out.println("Fou peut se d�placer �"+coordTest +"?->" +fou.isMoveOk(coordTest.x,coordTest.y));
        coordTest = new Coord(3,3); //FALSE
        System.out.println("Fou peut se d�placer �"+coordTest +"?->" +fou.isMoveOk(coordTest.x,coordTest.y));
        
        Pion pion = new Pion(Couleur.BLANC,coord);
        System.out.println("-------------test "+pion+"------------");
        coordTest = new Coord(2,1);
        System.out.println("Pion peut se d�placer �"+coordTest +"?->" +pion.isMoveOk(coordTest.x,coordTest.y));
        coordTest = new Coord(1,4);
        System.out.println("Pion peut se d�placer �"+coordTest +"?->" +pion.isMoveOk(coordTest.x,coordTest.y));
        coordTest = new Coord(0,0);
        System.out.println("Pion peut se d�placer �"+coordTest +"?->" +pion.isMoveOk(coordTest.x,coordTest.y));
        coordTest = new Coord(2,0); //FALSE
        System.out.println("Pion peut se d�placer �"+coordTest +"?->" +pion.isMoveOk(coordTest.x,coordTest.y));
        
        Pion pionN = new Pion(Couleur.NOIR,new Coord(2,1));
        System.out.println("-------------test "+pionN+"------------");
        coordTest = new Coord(2,4);
        System.out.println("Pion peut se d�placer �"+coordTest +"?->" +pionN.isMoveOk(coordTest.x,coordTest.y));
        coordTest = new Coord(1,4);
        System.out.println("Pion peut se d�placer �"+coordTest +"?->" +pionN.isMoveOk(coordTest.x,coordTest.y));
        coordTest = new Coord(0,0);
        System.out.println("Pion peut se d�placer �"+coordTest +"?->" +pionN.isMoveOk(coordTest.x,coordTest.y));
        coordTest = new Coord(2,3); //FALSE
        System.out.println("Pion peut se d�placer �"+coordTest +"?->" +pionN.isMoveOk(coordTest.x,coordTest.y));
        
        
        
        
    }
}
