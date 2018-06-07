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
public class EffetEnCours {
    
    private Effet effet;
    private int cptTours;

    public EffetEnCours(Effet effet) {
        this.effet = effet;
        this.cptTours = 0;
    }
    
    public Effet getEffet() {
        return effet;
    }

    public void setEffet(Effet effet) {
        this.effet = effet;
    }

    public int getCptTours() {
        return cptTours;
    }

    public void setCptTours(int cptTours) {
        this.cptTours = cptTours;
    }
    
    public void incrementCpt(){
        this.cptTours++;
    }

    @Override
    public String toString() {
        return "EffetEnCours{" + "effet=" + effet + ", cptTours=" + cptTours + '}';
    }
    
    
}
