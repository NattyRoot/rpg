/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import bean.Effet;
import bean.EffetEnCours;
import bean.Personnage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author b78877
 */
public class SystemCombat {
    
    private Personnage joueur;
    private Personnage enemy;
    
//    private Map<SystemTour, Effet> effetSurJoueur;
//    private Map<SystemTour, Effet> effetSurEnemy;
    
    
    private List<EffetEnCours> effetsJ;
    private List<EffetEnCours> effetsE;
    
//    private int pvJoueur;
//    private int pvEnemy;
    
    private List<SystemTour> lesTours;
        
    public SystemCombat(Personnage j, Personnage e){
        joueur = j;
        enemy = e;
        
        effetsJ = new ArrayList<EffetEnCours>();
        effetsE = new ArrayList<EffetEnCours>();
        
        SystemTour premierTour = new SystemTour(1);
        lesTours = new ArrayList<SystemTour>();
        lesTours.add(premierTour);
    }
    
    public boolean startFight(){
        SystemTour tour = getTour();
        
        if (SystemStats.isPlayerFirst(joueur, enemy)){
            lesTours.add(tour);
            return true;
        }
        else{
            lesTours.add(tour);
            return false;
        }
    }
    
    public List<Effet> getEffetJoueur(){
        List<Effet> lesEffets = new ArrayList<Effet>();
        List<EffetEnCours> effetToDel = new ArrayList<EffetEnCours>();
                
        for (EffetEnCours e : effetsJ){
            if (e.getEffet().getDuree() > e.getCptTours()){
                e.incrementCpt();
                lesEffets.add(e.getEffet());
            }
            else{
                effetToDel.add(e);
            }
        }
        
        effetsJ.removeAll(effetToDel);
        
        return lesEffets;
    }
    
    public List<Effet> getEffetEnemy(){
        List<Effet> lesEffets = new ArrayList<Effet>();
        List<EffetEnCours> effetToDel = new ArrayList<EffetEnCours>();
        
        for (EffetEnCours e : effetsE){
            if (e.getEffet().getDuree() > e.getCptTours()){
                e.incrementCpt();
                lesEffets.add(e.getEffet());
            }
            else{
                effetToDel.add(e);
            }
        }
        
        effetsE.removeAll(effetToDel);
        
        return lesEffets;
    }
    
    public void addEffetJoueur(Effet e){
        effetsJ.add(new EffetEnCours(e));
    }
    
    public void addEffetEnemy(Effet e){
        effetsE.add(new EffetEnCours(e));
    }
    
    public int tourSuivant(){
        SystemTour next = new SystemTour(getNextNumeroTour());
        lesTours.add(next);
        
        return next.getNumeroTour();
    }
    
    public int getNextNumeroTour(){
        return lesTours.get(lesTours.size() - 1).getNumeroTour() + 1;
    }
    
    public SystemTour getTour(){
        return lesTours.get(lesTours.size() - 1);
    }
}
