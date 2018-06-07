/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.awt.Frame;
import javax.swing.JOptionPane;

/**
 *
 * @author b78877
 */
public class Personnage {
    private int Personnage_ID;
    private String Nom;
    private int Race_ID;
    private int Classe_ID;
    private int Niveau_ID;
    private int Experience;
    private String url;
    
    public Personnage(){
        setNiveau_ID(1);
        setExperience(0);
    }
    
    public Personnage(String Nom, int Race_ID, int Classe_ID, int Niveau_ID, int Experience, boolean isJoueur) {
        this.Nom = Nom;
        this.Race_ID = Race_ID;
        this.Classe_ID = Classe_ID;
        this.Niveau_ID = Niveau_ID;
        this.Experience = Experience;
        setUrl(Classe_ID, Race_ID, isJoueur);
    }

    public Personnage(String Nom, int Race_ID, int Classe_ID, int Niveau_ID, int Experience) {
        this.Nom = Nom;
        this.Race_ID = Race_ID;
        this.Classe_ID = Classe_ID;
        this.Niveau_ID = Niveau_ID;
        this.Experience = Experience;
        setUrl(Classe_ID, Race_ID, true);
    }
    
    public Personnage(int id, String nom, int raceId, int classeId, int niveau, int exp){
        setPersonnage_ID(id);
        setNom(nom);
        setRace_ID(raceId);
        setClasse_ID(classeId);
        setNiveau_ID(niveau);
        setExperience(exp);
        setUrl(Classe_ID, Race_ID, true);
    }

    public int getPersonnage_ID() {
        return Personnage_ID;
    }

    public void setPersonnage_ID(int Personnage_ID) {
        this.Personnage_ID = Personnage_ID;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public int getRace_ID() {
        return Race_ID;
    }

    public void setRace_ID(int Race_ID) {
        this.Race_ID = Race_ID;
        if (this.Classe_ID != 0){
            this.setUrl(Classe_ID, Race_ID, true);
        }
    }

    public int getClasse_ID() {
        return Classe_ID;
    }

    public void setClasse_ID(int Classe_ID) {
        this.Classe_ID = Classe_ID;
        if (this.Race_ID != 0){
            this.setUrl(Classe_ID, Race_ID, true);
        }
    }

    public int getNiveau_ID() {
        return Niveau_ID;
    }

    public void setNiveau_ID(int niveau) {
        this.Niveau_ID = niveau;
    }

    public int getExperience() {
        return Experience;
    }

    public void setExperience(int Experience) {
        this.Experience = Experience;
    }
    
    public void addExperience(int exp){
        this.Experience += exp;
        
        if (this.Experience >= this.getNiveau_ID() * 1000){
            JOptionPane.showMessageDialog(Frame.getFrames()[0], "Vous montez d'un niveau !");
            this.Experience -= this.getNiveau_ID() * 1000;
            this.setNiveau_ID(this.getNiveau_ID() + 1);
        }
    }

    public String getUrl(boolean isJoueur) {
        if (url == null || url.length() == 0){
            setUrl(this.getClasse_ID(), this.getRace_ID(), isJoueur);
        }
        return url;
    }

    public void setUrl(int classe, int race, boolean isJoueur) {
        String path = "/img/personnage";
        
        boolean hasRace = true;
        
        switch(race){
            case 1: //Humain
                path += "/Humain";
                break;
            case 2 : //Nain
                path += "/Nain";
                break;
            case 3: //Elfe
                path += "/Elfe";
                break;
            case 4: //Orque
                path += "/Orque";
                break;
            case 5: //Gnome
                path += "/Gnome";
                break;
            default :
                path = "/img/personnage";
                hasRace = false;
                break;
        }
        if (hasRace){ //Si il y a une race de définit
            switch(classe){
                case 1: //Guerrier
                    path += "/Guerrier";
                    break;
                case 2 : //Pretre
                    path += "/Pretre";
                    break;
                case 3: //Voleur
                    path += "/Voleur";
                    break;
                case 4: //Mage
                    path += "/Mage";
                    break;
                case 5: //Chaman
                    path += "/Chaman";
                    break;
                default :
                    path = "/img/personnage";
                    break;
            }
        }
        
        if (isJoueur){
            path += "/joueur.jpg";
        }
        else{
            path += "/enemy.jpg";
        }
        
        url = path;
    }
    
    @Override
    public String toString() {
        return "Personnage{" + "Personnage_ID=" + Personnage_ID + ", Nom=" + Nom + ", Race_ID=" + Race_ID + ", Classe_ID=" + Classe_ID + ", Niveau=" + Niveau_ID + ", Experience=" + Experience +", url=" + url +'}';
    }
}
