/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import bean.Personnage;
import bll.BLL_Classe;
import bll.BLL_Race;
import java.util.Random;
import javax.swing.JOptionPane;
import ui.MainUI;

/**
 *
 * @author b78877
 */
public class SystemStats {
    
    public static boolean isHit(Personnage attaquant, Personnage defenseur){
        BLL_Race bllRace = new BLL_Race();
        
        int aPrecision = bllRace.getRaceById(attaquant.getRace_ID()).getPrecision() + (attaquant.getNiveau_ID() * 5);
        int dEsquive = bllRace.getRaceById(defenseur.getRace_ID()).getEsquive() + (defenseur.getNiveau_ID() * 5);
        
        Random randomizer = new Random();
        
        if (aPrecision + randomizer.nextInt(50) >= dEsquive){
            return true;
        }
        else{
            return false;
        }
    }
    
    public static boolean isPlayerFirst(Personnage joueur, Personnage enemy){
//        BLL_Classe bllClasse = new BLL_Classe();
//        
//        int jVitesse = bllClasse.getClasseById(joueur.getClasse_ID()).getVitesse() + (joueur.getNiveau_ID() * 5);
//        int eVitesse = bllClasse.getClasseById(enemy.getClasse_ID()).getVitesse() + (joueur.getNiveau_ID() * 5);
//        
//        Random randomizer = new Random();
//        
//        if (jVitesse + randomizer.nextInt(30) >= eVitesse + randomizer.nextInt(30)){
//            return true;
//        }
//        else{
//            return false;
//        }

        return true; //Pose des problèmes d'affichage lorsque l'ennemi joue en premier (JOptionPane s'ouvre avant l'affichage du combat)
    }
    
    public static int bonusAttaque(Personnage attaquant, Personnage defenseur){
        
        BLL_Classe bllClasse = new BLL_Classe();
        
        int aAttaque = bllClasse.getClasseById(attaquant.getClasse_ID()).getAttaque() + attaquant.getNiveau_ID() * 5;
        int dDefense = bllClasse.getClasseById(defenseur.getClasse_ID()).getDefense() + defenseur.getNiveau_ID() * 5;
        
        int bonus = 0;
        
        if (aAttaque > dDefense){ //+1 
            bonus++;
            if (aAttaque > dDefense + 10){//+2
                bonus++;
                if (aAttaque > dDefense + 20){//+3
                    bonus++;
                    if (aAttaque > dDefense + 30){//+5
                        bonus+=2;
                        if (aAttaque > dDefense + 40){//+7
                            bonus+=2;
                            if (aAttaque > dDefense + 50){//+10
                                bonus += 3;
                            }
                        }
                    }
                }
            }
        }
        
        bonus += addCriticalHit(aAttaque); //1 chance sur 5 de faire du bonus de dégat (attaque / 10)
        
        return bonus;
    }
    
    private static int addCriticalHit(int attaque){
        int randomizer = new Random().nextInt(50);
        
        if (randomizer >= 45){
            JOptionPane.showMessageDialog(MainUI.getFrames()[2], "Coup critique ! + " + attaque / 10 + " dgt");
            return attaque / 10;
        }
        else{
            return 0;
        }
    } 
}
