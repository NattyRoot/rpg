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
public class Race {
    
    private int Race_ID;
    private String Libelle;
    private int Pv;
    private int Esquive;
    private int Precision;

    public Race(){
        
    }

    public Race(int Race_ID, String Libelle, int Pv, int esquive, int precision) {
        this.Race_ID = Race_ID;
        this.Libelle = Libelle;
        this.Pv = Pv;
        this.Esquive = esquive;
        this.Precision = precision;
    }

    public int getEsquive() {
        return Esquive;
    }

    public void setEsquive(int esquive) {
        this.Esquive = esquive;
    }

    public int getPrecision() {
        return Precision;
    }

    public void setPrecision(int precision) {
        this.Precision = precision;
    }

    public int getRace_ID() {
        return Race_ID;
    }

    public void setRace_ID(int Race_ID) {
        this.Race_ID = Race_ID;
    }

    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String Libelle) {
        this.Libelle = Libelle;
    }

    public int getPv() {
        return Pv;
    }

    public void setPv(int Pv) {
        this.Pv = Pv;
    }    

    @Override
    public String toString() {
        return "Race{" + "Race_ID=" + Race_ID + ", Libelle=" + Libelle + ", Pv=" + Pv + ", esquive=" + Esquive + ", precision=" + Precision + '}';
    }

    
}
