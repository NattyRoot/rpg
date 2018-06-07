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
public class Competence {
    
    private int Competence_ID;
    private String Libelle;
    private int Classe_ID;
    private int Degat;
    private int Effet_ID;
    
    public Competence(){
        
    }
    
    public Competence(int competenceId, String libelle, int classeId, int degat, int effetId){
        setCompetence_ID(competenceId);
        setLibelle(libelle);
        setClasse_ID(classeId);
        setDegat(degat);
        setEffet_ID(effetId);
    }

    public int getCompetence_ID() {
        return Competence_ID;
    }

    public void setCompetence_ID(int Competence_ID) {
        this.Competence_ID = Competence_ID;
    }

    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String Libelle) {
        this.Libelle = Libelle;
    }

    public int getClasse_ID() {
        return Classe_ID;
    }

    public void setClasse_ID(int Classe_ID) {
        this.Classe_ID = Classe_ID;
    }

    public int getDegat() {
        return Degat;
    }

    public void setDegat(int Degat) {
        this.Degat = Degat;
    }

    public int getEffet_ID() {
        return Effet_ID;
    }

    public void setEffet_ID(int Effet_ID) {
        this.Effet_ID = Effet_ID;
    }

    @Override
    public String toString() {
        return "Competence{" + "Competence_ID=" + Competence_ID + ", Libelle=" + Libelle + ", Classe_ID=" + Classe_ID + ", Degat=" + Degat + ", Effet_ID=" + Effet_ID + '}';
    }
    
}
