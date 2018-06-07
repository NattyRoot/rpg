/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import bean.Competence;
import bean.Effet;
import bean.Personnage;
import bll.BLL_Competence;
import bll.BLL_Effet;
import bll.BLL_Personnage;
import bll.BLL_Race;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import system.SystemCombat;
import system.SystemStats;

/**
 *
 * @author b78877
 */
public class CombatUI extends javax.swing.JFrame {

    private SystemCombat leCombat;
    
    private Personnage perso;
    private Personnage enemy;
    
    private HashMap componentMap;
    
    /**
     * Creates new form CombatUI
     */
    public CombatUI() {
        initComponents();
    }
    
    public CombatUI(Personnage perso) {
        initComponents();
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        this.perso = perso;
        
        loadGame();
    }
    
    public void loadGame(){
        this.setTitle("COMBAT");
        
        showInfosJoueur();
        
        //Si il y a bien 4 compétences comme prévu, il faut séparer compétence/jeu à x ~= 315 avec un separator ou autre
        enemy = new Personnage("Bobby", new Random().nextInt(4) + 1, new Random().nextInt(4) + 1, perso.getNiveau_ID()<6?new Random().nextInt(4)+1:new Random().nextInt(4) + 6, 0, false);
        
        leCombat = new SystemCombat(perso, enemy);
        
        showInfosEnemy();
        
        showInfosCombat();
        
        loadComponentMap();        
        
        if (leCombat.startFight()){
            this.setControlComp(true);
        }
        else{
            enemyTurn();
            this.setControlComp(true);
        }
    }
    
    public void showInfosJoueur(){
        BLL_Competence bllComp = new BLL_Competence();
        BLL_Effet bllEffet = new BLL_Effet();
        
        int x = 10;
        int y = 380;
        
        int i = 1;
        
        //Creation boutons des competences
        for (Competence comp : bllComp.getCompetenceByClasse(perso.getClasse_ID())){
            JButton btn = new JButton(comp.getLibelle());
            btn.setBounds(x, y, 150, 50); 
            
            btn.setName("btnCompJoueur" + i);
            i++;
            
            x += 155;
            
            //On passe a la ligne a la 3e competence (1ere : x=10&y=0, 2e : x=265&y=0, 3e : x=10&y=55, 4e : x=265&y=55)
            if (x == 320){
                y += 55;
                x = 10;
            }
            
            Effet effet = bllEffet.getEffetById(comp.getEffet_ID());
            
            btn.setToolTipText(
                "<html><div>"
                    + "<span style='color:red'><b>" + comp.getDegat() + " dgt</b></span><br>"
                    + "<span>" + effet.getLibelle() + " :<br>" + effet.getHtmlDesc() + "</span>"
                + "</div></html>"
            );
            
            //Définit l'id de la competence sur l'action du bouton
            btn.setActionCommand(comp.getCompetence_ID() + "");
            
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (SystemStats.isHit(perso, enemy)){
                        BLL_Competence bllComp = new BLL_Competence();

                        Competence comp = bllComp.getCompetenceById(Integer.parseInt(e.getActionCommand()));
                        
                        if (comp.getEffet_ID() != 0 && comp.getEffet_ID() != 3){ //0 = Pas d'effet et 3 = Soin
                            leCombat.addEffetEnemy((new BLL_Effet()).getEffetById(comp.getEffet_ID()));
                        }
                        else if(comp.getEffet_ID() == 3){
                            leCombat.addEffetJoueur((new BLL_Effet()).getEffetById(comp.getEffet_ID()));
                        }
                        
                        int degat = comp.getDegat() + SystemStats.bonusAttaque(perso, enemy);

                        JOptionPane.showMessageDialog(CombatUI.this, "Tu utilise la competence " + comp.getLibelle() + " et inflige " + degat + " points de dégats");
                        
                        JProgressBar life = ((JProgressBar)CombatUI.this.getComponentByName("lifebarEnemy"));
                        
                        life.setValue(life.getValue() - degat);
                        life.setString(life.getValue() + "/" + life.getMaximum());
                    }
                    else{
                        JOptionPane.showMessageDialog(CombatUI.this, "Vous ratez votre attaque !");
                    }
                    
                    //Application des effets
                    if(processEffet(enemy, leCombat.getEffetEnemy()) && ((JProgressBar)getComponentByName("lifebarEnemy")).getValue() > 0){
                        CombatUI.this.enemyTurn();
                    }
                    
                    ((JLabel)CombatUI.this.getComponentByName("lblNumeroTour")).setText("<html><h2>Tour " + leCombat.tourSuivant() + "</h2></html>");
                }
            });
            
            pnlGame.add(btn);
        }
        
        //Création et affichage de la barre de vie du joueur
        JProgressBar lifebarJoueur = new JProgressBar(0, ((new BLL_Race()).getRaceById(perso.getRace_ID()).getPv() + perso.getNiveau_ID() * 5));
        lifebarJoueur.setBounds(10, 320, 305, 30);
        lifebarJoueur.setForeground(Color.green);
        lifebarJoueur.setBackground(Color.red);
        lifebarJoueur.setStringPainted(true);
        lifebarJoueur.setString(lifebarJoueur.getMaximum() + "/" + lifebarJoueur.getMaximum());
        lifebarJoueur.setValue(lifebarJoueur.getMaximum());
        lifebarJoueur.setName("lifebarJoueur");
        
        lifebarJoueur.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (((JProgressBar)e.getSource()).getValue() <= 0){
                    JOptionPane.showMessageDialog(CombatUI.this, "LOOSE");
                    
                    CombatUI.this.dispose();
                    for (int i = 1; i < Frame.getFrames().length; i++){
                        Frame.getFrames()[i].dispose();
                    }
                }
            }
        });
        
        pnlGame.add(lifebarJoueur);
        
        JLabel lblImage = new JLabel(""); 
        lblImage.setIcon(new ImageIcon(getClass().getResource(perso.getUrl(true))));
        lblImage.setIconTextGap(0);
        lblImage.setBorder(null);
        lblImage.setText(perso.getNom() + " : lvl " + perso.getNiveau_ID());
        lblImage.setSize(300, 300);
        lblImage.setLocation(50, 10);
        lblImage.setName("lblImageJoueur");        
        
        pnlGame.add(lblImage);
    }
    
    public void showInfosEnemy(){
        BLL_Competence bllComp = new BLL_Competence();
        BLL_Effet bllEffet = new BLL_Effet();
        
        int x = 710;
        int y = 380;
        
        int i = 1;
        for (Competence comp : bllComp.getCompetenceByClasse(enemy.getClasse_ID())){
            JButton btn = new JButton(comp.getLibelle());
            
            //Nom pour componentMap
            btn.setName("btnCompEnemy" + i);
            i++;
            
            btn.setBounds(x, y, 150, 50); 
            
            x += 155;
            
            if (x == 1020){
                y += 55;
                x = 710;
            }
            
            Effet effet = bllEffet.getEffetById(comp.getEffet_ID());
            
            btn.setToolTipText(
                "<html><div>"
                    + "<span style='color:red'><b>" + comp.getDegat() + " dgt</b></span><br>"
                    + "<span>" + effet.getLibelle() + " :<br>" + effet.getHtmlDesc() + "</span>"
                + "</div></html>"
            );
            
            btn.setEnabled(false);
            
            pnlGame.add(btn);
        }
        
        JProgressBar lifebarEnemy = new JProgressBar(0, ((new BLL_Race()).getRaceById(enemy.getRace_ID()).getPv() + enemy.getNiveau_ID() * 5));
        lifebarEnemy.setBounds(710, 320, 305, 30);
        lifebarEnemy.setForeground(Color.green);
        lifebarEnemy.setBackground(Color.red);
        lifebarEnemy.setStringPainted(true);
        lifebarEnemy.setString(lifebarEnemy.getMaximum() + "/" + lifebarEnemy.getMaximum());
        lifebarEnemy.setValue(lifebarEnemy.getMaximum());
        lifebarEnemy.setName("lifebarEnemy");
        
        lifebarEnemy.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (((JProgressBar)e.getSource()).getValue() <= 0){
                    JOptionPane.showMessageDialog(CombatUI.this, "WIN : Vous gagnez " + (enemy.getNiveau_ID() * 100 + 500) + " points d'experience");
                    
                    perso.addExperience((enemy.getNiveau_ID() * 100) + 500);
                    
                    (new BLL_Personnage()).updatePersonnage(perso);
                                        
                    CombatUI.this.dispose();
                }
            }
        });
        
        pnlGame.add(lifebarEnemy);
        
        JLabel lblImage = new JLabel("");
        lblImage.setIcon(new ImageIcon(getClass().getResource(enemy.getUrl(false))));
        lblImage.setIconTextGap(0);
        lblImage.setBorder(null);
        lblImage.setText(enemy.getNom() + " : lvl " + enemy.getNiveau_ID());
        lblImage.setSize(300, 300);
        lblImage.setLocation(750, 10);
        lblImage.setName("lblImageEnemy");        
        
        pnlGame.add(lblImage);
    }
    
    public void showInfosCombat(){
        JLabel lblNumeroTour = new JLabel("<html><h2>Tour " + leCombat.getTour().getNumeroTour() + "</h2></html>");
        lblNumeroTour.setName("lblNumeroTour");
        lblNumeroTour.setSize(60, 60);
        lblNumeroTour.setLocation(pnlGame.getWidth() / 2 - lblNumeroTour.getWidth() / 2, 10);
        
        pnlGame.add(lblNumeroTour);
    }
    
    private void loadComponentMap() {
        componentMap = new HashMap<String,Component>();
        Component[] components = pnlGame.getComponents();
        for (int i=0; i < components.length; i++) {
            componentMap.put(components[i].getName(), components[i]);
        }
    }

    public Component getComponentByName(String name) {
        if (componentMap.containsKey(name)) {
            return (Component) componentMap.get(name);
        }
        else return null;
    }
    
    private void setControlComp(boolean val) {
        for (int i = 1; i < 5; i++){
            this.getComponentByName("btnCompJoueur" + i).setEnabled(val);
        }
    }
    
    public void enemyTurn(){        
        if (SystemStats.isHit(enemy, perso)){
            BLL_Competence bllComp = new BLL_Competence();

            Competence c = bllComp.getCompetenceByClasse(enemy.getClasse_ID()).get(new Random().nextInt(4));

            if (c.getEffet_ID() != 3 && c.getEffet_ID() != 0){
                leCombat.addEffetJoueur((new BLL_Effet()).getEffetById(c.getEffet_ID()));
            }
            else if(c.getEffet_ID() == 3){
                leCombat.addEffetEnemy((new BLL_Effet()).getEffetById(c.getEffet_ID()));
            }
            
            int degat = c.getDegat() + SystemStats.bonusAttaque(enemy, perso);
            
            JOptionPane.showMessageDialog(this, "L'ennemi utilise " + c.getLibelle() + " et inflige " + degat + " points de dégats");
            
            JProgressBar life = ((JProgressBar)this.getComponentByName("lifebarJoueur"));

            life.setValue(life.getValue() - degat);
            life.setString(life.getValue() + "/" + life.getMaximum());

        }
        else{
            JOptionPane.showMessageDialog(this, "L'ennemi rate son attaque !");
        }
        
        if (!processEffet(perso, leCombat.getEffetJoueur()) && ((JProgressBar)getComponentByName("lifebarJoueur")).getValue() > 0){
            enemyTurn();
        }
    }
    
    public boolean processEffet(Personnage p, List<Effet> lesEffets){
        boolean canPlay = true; //false si etourdit
        int dmg = 0;
        HashMap<String, Integer> mapEffet = new HashMap<>();
        
        //Recupere la bonne JProgressBar en fonction du personnage en paramètre
        String lifeBar = p.getPersonnage_ID() == perso.getPersonnage_ID()?"lifebarJoueur":"lifebarEnemy";
        
        if (((JProgressBar)getComponentByName(lifeBar)).getValue() > 0){ //Si le joueur est toujours en vie
            for (Effet e : lesEffets){
                switch(e.getEffet_ID()){
                    case 0: //Pas d'effet
                        
                        break;
                    case 1: //Empoisonnement
                        if (mapEffet.get("Empoisonnement") == null || mapEffet.get("Empoisonnement") == 0){
                            mapEffet.put("Empoisonnement", 2);
                        }
                        else{
                            mapEffet.put("Empoisonnement", mapEffet.get("Empoisonnement") + 2);
                        }
                        dmg += 2;
                        break;
                    case 2: //Etourdissement
                        canPlay = false;
                        if (mapEffet.get("Etourdissement") == null){
                            mapEffet.put("Etourdissement", 0);
                        }
                        
                        break;
                    case 3: //Regeneration 
                        if (mapEffet.get("Regeneration") == null || mapEffet.get("Regeneration") == 0){
                            mapEffet.put("Regeneration", -3);
                        }
                        else{
                            mapEffet.put("Regeneration", mapEffet.get("Regeneration") - 3);
                        }
                        
                        dmg -= 3;
                        break;
                    case 4: //Saignement
                        if (mapEffet.get("Saignement") == null || mapEffet.get("Saignement") == 0){
                            mapEffet.put("Saignement", 1);
                        }
                        else{
                            mapEffet.put("Saignement", mapEffet.get("Saignement") + 1);
                        }
                        
                        dmg += 1;
                        break;

                    case 5: //Brulure
                        if (mapEffet.get("Brulure") == null || mapEffet.get("Brulure") == 0){
                            mapEffet.put("Brulure", 4);
                        }
                        else{
                            mapEffet.put("Brulure", mapEffet.get("Brulure") + 4);
                        }
                        
                        dmg += 4;
                        break;
                    default:
                        JOptionPane.showMessageDialog(this, "L'effet numéro " + e.getEffet_ID() + " n'existe pas", "Erreur", JOptionPane.WARNING_MESSAGE);
                        break;
                }
            }
        }
        
//        Iterator it = mapEffet.entrySet().iterator();
        
//        while(it.hasNext()){
//            Map.Entry pair = (Map.Entry)it.next();
//            
//            JOptionPane.showMessageDialog(this, p.getNom() + " prend ");
//        }
        
        for (String s : mapEffet.keySet()){
            switch(s){
                case "Empoisonnement":
                    JOptionPane.showMessageDialog(this, p.getNom() + " vomit et perd " + mapEffet.get("Empoisonnement") + " PV.");
                    break;
                case "Etourdissement":
                    JOptionPane.showMessageDialog(this, p.getNom() + " est etourdi, il ne peux pas jouer.");
                    break;
                case "Regeneration":
                    JOptionPane.showMessageDialog(this, p.getNom() + " se sent revivre ! Il recupère " + mapEffet.get("Regeneration") * -1 + " PV.");
                    break;
                case "Saignement":
                    JOptionPane.showMessageDialog(this, p.getNom() + " pisse le sang ! Il perd " + mapEffet.get("Saignement") + " PV");
                    break;
                case "Brulure":
                    JOptionPane.showMessageDialog(this, p.getNom() + " a un peu trop chaud, c'est surement dû au flammes sur son corps. Il perd " + mapEffet.get("Brulure") + " PV");
                    break;
            }
        }
        
        ((JProgressBar)getComponentByName(lifeBar)).setValue(
            ((JProgressBar)getComponentByName(lifeBar)).getValue() - dmg
        );
        ((JProgressBar)getComponentByName(lifeBar)).setString(
            ((JProgressBar)getComponentByName(lifeBar)).getValue() + "/" + ((JProgressBar)getComponentByName(lifeBar)).getMaximum()
        );
        
        return canPlay;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlGame = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlGame.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnlGameLayout = new javax.swing.GroupLayout(pnlGame);
        pnlGame.setLayout(pnlGameLayout);
        pnlGameLayout.setHorizontalGroup(
            pnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1121, Short.MAX_VALUE)
        );
        pnlGameLayout.setVerticalGroup(
            pnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(pnlGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CombatUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CombatUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CombatUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CombatUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CombatUI dialog = new CombatUI();
                                
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlGame;
    // End of variables declaration//GEN-END:variables
}
