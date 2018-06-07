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
public class Classe {
    
    private int Classe_ID;
    private String Libelle;
    private int Attaque;
    private int Defense;
    private int Vitesse;
    
    public Classe(){
        
    }
    
    public Classe(String Libelle, int attaque, int defense, int vitesse) {
        this.Libelle = Libelle;
        this.Attaque = attaque;
        this.Defense = defense;
        this.Vitesse = vitesse;
    }

    public Classe(int Classe_ID, String Libelle, int attaque, int defense, int vitesse) {
        this.Classe_ID = Classe_ID;
        this.Libelle = Libelle;
        this.Attaque = attaque;
        this.Defense = defense;
        this.Vitesse = vitesse;
    }
    
   

    public int getClasse_ID() {
        return Classe_ID;
    }

    public void setClasse_ID(int Classe_ID) {
        this.Classe_ID = Classe_ID;
    }

    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String Libelle) {
        this.Libelle = Libelle;
    }

    public int getAttaque() {
        return Attaque;
    }

    public void setAttaque(int attaque) {
        this.Attaque = attaque;
    }

    public int getDefense() {
        return Defense;
    }

    public void setDefense(int defense) {
        this.Defense = defense;
    }

    public int getVitesse() {
        return Vitesse;
    }

    public void setVitesse(int vitesse) {
        this.Vitesse = vitesse;
    }

    @Override
    public String toString() {
        return "Classe{" + "Classe_ID=" + Classe_ID + ", Libelle=" + Libelle + ", attaque=" + Attaque + ", defense=" + Defense + ", vitesse=" + Vitesse + '}';
    }
    
}
