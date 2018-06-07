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
public class Specialite {

    private int Specialite_ID;
    private int Race_ID;
    private String Libelle;
    
    public Specialite(){
        
    }

    public Specialite(int specialiteId, int raceId, String libelle){
        setSpecialite_ID(specialiteId);
        setRace_ID(raceId);
        setLibelle(libelle);
    }

    public int getSpecialite_ID() {
        return Specialite_ID;
    }

    public void setSpecialite_ID(int Specialite_ID) {
        this.Specialite_ID = Specialite_ID;
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

    @Override
    public String toString() {
        return "Specialite{" + "Specialite_ID=" + Specialite_ID + ", Race_ID=" + Race_ID + ", Libelle=" + Libelle + '}';
    }

}
