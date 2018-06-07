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
public class Effet {
    
    private int Effet_ID;
    private String Libelle;
    private int Degat;
    private int Soin;
    private int Duree;
    private String HtmlDesc;
    
    public Effet(){
        
    }
    
    public Effet(int effetId, String libelle, int degat, int soin, int duree, String htmlDesc){
        setEffet_ID(effetId);
        setLibelle(libelle);
        setDegat(degat);
        setSoin(soin);
        setDuree(duree);
        setHtmlDesc(htmlDesc);
    }

    public int getEffet_ID() {
        return Effet_ID;
    }

    public void setEffet_ID(int Effet_ID) {
        this.Effet_ID = Effet_ID;
    }

    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String Libelle) {
        this.Libelle = Libelle;
    }

    public int getDegat() {
        return Degat;
    }

    public void setDegat(int Degat) {
        this.Degat = Degat;
    }

    public int getSoin() {
        return Soin;
    }

    public void setSoin(int Soin) {
        this.Soin = Soin;
    }

    public int getDuree() {
        return Duree;
    }

    public void setDuree(int Duree) {
        this.Duree = Duree;
    }

    public String getHtmlDesc() {
        return HtmlDesc;
    }

    public void setHtmlDesc(String HtmlDesc) {
        this.HtmlDesc = HtmlDesc;
    }

    @Override
    public String toString() {
        return "Effet{" + "Effet_ID=" + Effet_ID + ", Libelle=" + Libelle + ", Degat=" + Degat + ", Soin=" + Soin + ", Duree=" + Duree + '}';
    }
}
