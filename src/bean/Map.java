/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author b78877
 */
public class Map {
    
    private Object[][] position;
    
    public Map(){
        
    }
    
    public Map(Object[][] position){
        setPosition(position);
    }
    
    public Object[][] getPosition(){
        return position;
    }

    public String getPositionValue(int x, int y) {
        return position[y][x].toString();
    }

    public void setPosition(Object[][] position) {
        this.position = position;
    }
    @Override
    public String toString() {
        return "Map{" + ", position=" + position + '}';
    }
}
