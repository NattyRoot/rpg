/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author b78877
 */
public class PositionPerso {
    
    private int Personnage_ID;
    private int indexMap;
    private int x;
    private int y;

    public PositionPerso() {
    }

    public PositionPerso(int Personnage_ID, int indexMap, int x, int y) {
        this.Personnage_ID = Personnage_ID;
        this.indexMap = indexMap;
        this.x = x;
        this.y = y;
    }

    public int getPersonnage_ID() {
        return Personnage_ID;
    }

    public void setPersonnage_ID(int Personnage_ID) {
        this.Personnage_ID = Personnage_ID;
    }

    public int getIndexMap() {
        return indexMap;
    }

    public void setIndexMap(int indexMap) {
        this.indexMap = indexMap;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "PositionPerso{" + "Personnage_ID=" + Personnage_ID + ", indexMap=" + indexMap + ", x=" + x + ", y=" + y + '}';
    }    
    
}
