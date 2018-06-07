/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import bean.Effet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author b78877
 */
public class SystemTour {
    
    private int numero;
    
    private List<Effet> lesEffetsJoueur;
    private List<Effet> lesEffetsEnemy;
    
    public SystemTour(int num){
        numero = num;
        lesEffetsEnemy = new ArrayList<Effet>();
        lesEffetsJoueur = new ArrayList<Effet>();
    }
    
    public int getNumeroTour(){
        return this.numero;
    }
   
    public List<Effet> getEffetsJoueur(){
        return lesEffetsJoueur;
    }
    
    public List<Effet> getEffetsEnemy(){
        return lesEffetsEnemy;
    }
    
    public void addEffetJoueur(Effet e){
        lesEffetsJoueur.add(e);
    }
    
    public void addEffetEnemy(Effet e){
        lesEffetsEnemy.add(e);
    }
}
