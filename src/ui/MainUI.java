/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import bean.*;
import bll.*;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import javax.swing.*;

import system.SystemCombat;
import system.SystemDirectory;

/**
 *
 * @author b78877
 */
public class MainUI extends JFrame {

    private Personnage perso;
    private Personnage enemy;
    //objet HashMap contenant tout les components de la frame
    private HashMap componentMap;
    
    private SystemCombat leCombat;

    
    /**
     * Creates new form MainUI
     */
    public MainUI() {
        initComponents();
        
        loadMenuPerso();
    }
    
    private void loadMenuPerso(){
        clearPanel(pnlGame);
        
        lblPersoEnCours.setText("");
        
        BLL_Personnage bllPerso = new BLL_Personnage();
        BLL_Classe bllClasse = new BLL_Classe();
        BLL_Race bllRace = new BLL_Race();
        
        List<Personnage> lesPersos = bllPerso.getAllPersonnages();
        
        int x = pnlGame.getX();
        int y = pnlGame.getY();
        
        for (Personnage p : lesPersos){
            Classe classePerso = bllClasse.getClasseById(p.getClasse_ID());
            Race racePerso = bllRace.getRaceById(p.getRace_ID());
            
            JButton btn = new JButton("<html>"+ p.getNom().toUpperCase() + " Niveau " + p.getNiveau_ID() +"<br>Race : " + racePerso.getLibelle() + "<br>Classe : " + classePerso.getLibelle() + "<br>Experience : " + p.getExperience() +"/" + p.getNiveau_ID() * 1000 + "</html>");
            btn.setBounds(x, y, 200, 100);
            
            btn.setName("btnPersonnage" + p.getPersonnage_ID());
            
            if (x < 600){
                x = btn.getX() + btn.getWidth() + 50;
                y = btn.getY();
            }
            else{
                x = pnlGame.getX();
                y += 120; 
            }
            
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Redirection vers le menu principal avec le perso selectionné                    
                    lblPersoEnCours.setText("Personnage chargé : " + p.getNom() + " (Race : " + racePerso.getLibelle() + " ; Classe : " + classePerso.getLibelle() + ")");
                    perso = p;
                    perso.setUrl(perso.getClasse_ID(), perso.getRace_ID(), true);

                    MapUI map = new MapUI(perso);
                    map.setVisible(true);
                }
            });
            
            pnlGame.add(btn);
        }
        loadComponentMap();
    }
    
    public void clearPanel(JPanel panel){
        panel.removeAll();
        panel.updateUI();
    }
    
//    public void loadGame(){
//        this.setTitle("COMBAT");
//        clearPanel(pnlGame);
//        
//        showInfosJoueur();
//        
//        //Si il y a bien 4 compétences comme prévu, il faut séparer compétence/jeu à x ~= 315 avec un separator ou autre
//        enemy = new Personnage("Gandalf", new Random().nextInt(4) + 1, new Random().nextInt(4) + 1, perso.getNiveau_ID()<6?new Random().nextInt(4)+1:new Random().nextInt(4) + 6, 0, false);
//        
//        leCombat = new SystemCombat(perso, enemy);
//        
//        showInfosEnemy();
//        
//        showInfosCombat();
//        
//        loadComponentMap();        
//        
//        if (leCombat.startFight()){
//            this.setControlComp(true);
//        }
//        else{
//            enemyTurn();
//            this.setControlComp(true);
//        }
//    }

//    public void showInfosJoueur(){
//        BLL_Competence bllComp = new BLL_Competence();
//        BLL_Effet bllEffet = new BLL_Effet();
//        
//        int x = 10;
//        int y = 380;
//        
//        int i = 1;
//        
//        //Creation boutons des competences
//        for (Competence comp : bllComp.getCompetenceByClasse(perso.getClasse_ID())){
//            JButton btn = new JButton(comp.getLibelle());
//            btn.setBounds(x, y, 150, 50); 
//            
//            btn.setName("btnCompJoueur" + i);
//            i++;
//            
//            x += 155;
//            
//            //On passe a la ligne a la 3e competence (1ere : x=10&y=0, 2e : x=265&y=0, 3e : x=10&y=55, 4e : x=265&y=55)
//            if (x == 320){
//                y += 55;
//                x = 10;
//            }
//            
//            Effet effet = bllEffet.getEffetById(comp.getEffet_ID());
//            
//            btn.setToolTipText(
//                "<html><div>"
//                    + "<span style='color:red'><b>" + comp.getDegat() + " dgt</b></span><br>"
//                    + "<span>" + effet.getLibelle() + " :<br>" + effet.getHtmlDesc() + "</span>"
//                + "</div></html>"
//            );
//            
//            //Définit l'id de la competence sur l'action du bouton
//            btn.setActionCommand(comp.getCompetence_ID() + "");
//            
//            btn.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    if (SystemStats.isHit(perso, enemy)){
//                        BLL_Competence bllComp = new BLL_Competence();
//
//                        Competence comp = bllComp.getCompetenceById(Integer.parseInt(e.getActionCommand()));
//                        
//                        if (comp.getEffet_ID() != 0 && comp.getEffet_ID() != 3){ //0 = Pas d'effet et 3 = Soin
//                            leCombat.addEffetEnemy((new BLL_Effet()).getEffetById(comp.getEffet_ID()));
//                        }
//                        else if(comp.getEffet_ID() == 3){
//                            leCombat.addEffetJoueur((new BLL_Effet()).getEffetById(comp.getEffet_ID()));
//                        }
//                        
//                        int degat = comp.getDegat() + SystemStats.bonusAttaque(perso, enemy);
//
//                        JOptionPane.showMessageDialog(MainUI.this, "Tu utilise la competence " + comp.getLibelle() + " et inflige " + degat + " points de dégats");
//                        
//                        ((JProgressBar)MainUI.this.getComponentByName("lifebarEnemy")).setValue(((JProgressBar)MainUI.this.getComponentByName("lifebarEnemy")).getValue() - degat);
//                        ((JProgressBar)MainUI.this.getComponentByName("lifebarEnemy")).setString(((JProgressBar)MainUI.this.getComponentByName("lifebarEnemy")).getValue() + "/" + ((JProgressBar)MainUI.this.getComponentByName("lifebarEnemy")).getMaximum());
//                    }
//                    else{
//                        JOptionPane.showMessageDialog(MainUI.this, "Vous ratez votre attaque !");
//                    }
//                    
//                    //Application des effets
//                    if(processEffet(enemy, leCombat.getEffetEnemy())){
//                        MainUI.this.enemyTurn();
//                    }
//                    
//                    ((JLabel)MainUI.this.getComponentByName("lblNumeroTour")).setText("<html><h2>Tour " + leCombat.tourSuivant() + "</h2></html>");
//                }
//            });
//            
//            pnlGame.add(btn);
//        }
//        
//        //Création et affichage de la barre de vie du joueur
//        JProgressBar lifebarJoueur = new JProgressBar(0, ((new BLL_Race()).getRaceById(perso.getRace_ID()).getPv() + perso.getNiveau_ID() * 5));
//        lifebarJoueur.setBounds(10, 320, 305, 30);
//        lifebarJoueur.setForeground(Color.green);
//        lifebarJoueur.setBackground(Color.red);
//        lifebarJoueur.setStringPainted(true);
//        lifebarJoueur.setString(lifebarJoueur.getMaximum() + "/" + lifebarJoueur.getMaximum());
//        lifebarJoueur.setValue(lifebarJoueur.getMaximum());
//        lifebarJoueur.setName("lifebarJoueur");
//        
//        lifebarJoueur.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (((JProgressBar)e.getSource()).getValue() <= 0){
//                    JOptionPane.showMessageDialog(MainUI.this, "LOOSE");
//                    
//                    loadMenuPerso();
//                }
//            }
//        });
//        
//        pnlGame.add(lifebarJoueur);
//        
//        JLabel lblImage = new JLabel(""); 
//        lblImage.setIcon(new ImageIcon(perso.getUrl(true)));
//        lblImage.setIconTextGap(0);
//        lblImage.setBorder(null);
//        lblImage.setText(perso.getNom() + " : lvl " + perso.getNiveau_ID());
//        lblImage.setSize(300, 300);
//        lblImage.setLocation(50, 10);
//        lblImage.setName("lblImageJoueur");        
//        
//        pnlGame.add(lblImage);
//    }
//    
//    public void showInfosEnemy(){
//        BLL_Competence bllComp = new BLL_Competence();
//        BLL_Effet bllEffet = new BLL_Effet();
//        
//        int x = 710;
//        int y = 380;
//        
//        int i = 1;
//        for (Competence comp : bllComp.getCompetenceByClasse(enemy.getClasse_ID())){
//            JButton btn = new JButton(comp.getLibelle());
//            
//            //Nom pour componentMap
//            btn.setName("btnCompEnemy" + i);
//            i++;
//            
//            btn.setBounds(x, y, 150, 50); 
//            
//            x += 155;
//            
//            if (x == 1020){
//                y += 55;
//                x = 710;
//            }
//            
//            Effet effet = bllEffet.getEffetById(comp.getEffet_ID());
//            
//            btn.setToolTipText(
//                "<html><div>"
//                    + "<span style='color:red'><b>" + comp.getDegat() + " dgt</b></span><br>"
//                    + "<span>" + effet.getLibelle() + " :<br>" + effet.getHtmlDesc() + "</span>"
//                + "</div></html>"
//            );
//            
//            btn.setEnabled(false);
//            
//            pnlGame.add(btn);
//        }
//        
//        JProgressBar lifebarEnemy = new JProgressBar(0, ((new BLL_Race()).getRaceById(enemy.getRace_ID()).getPv() + enemy.getNiveau_ID() * 5));
//        lifebarEnemy.setBounds(710, 320, 305, 30);
//        lifebarEnemy.setForeground(Color.green);
//        lifebarEnemy.setBackground(Color.red);
//        lifebarEnemy.setStringPainted(true);
//        lifebarEnemy.setString(lifebarEnemy.getMaximum() + "/" + lifebarEnemy.getMaximum());
//        lifebarEnemy.setValue(lifebarEnemy.getMaximum());
//        lifebarEnemy.setName("lifebarEnemy");
//        
//        lifebarEnemy.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (((JProgressBar)e.getSource()).getValue() <= 0){
//                    JOptionPane.showMessageDialog(MainUI.this, "WIN : Vous gagnez " + (enemy.getNiveau_ID() * 100 + 500) + " points d'experience");
//                    
//                    perso.addExperience((enemy.getNiveau_ID() * 100) + 500);
//                    
//                    (new BLL_Personnage()).updatePersonnage(perso);
//                                        
//                    loadMenuPerso();
//                }
//            }
//        });
//        
//        pnlGame.add(lifebarEnemy);
//        
//        JLabel lblImage = new JLabel(""); 
//        lblImage.setIcon(new ImageIcon(enemy.getUrl(false)));
//        lblImage.setIconTextGap(0);
//        lblImage.setBorder(null);
//        lblImage.setText(enemy.getNom() + " : lvl " + enemy.getNiveau_ID());
//        lblImage.setSize(300, 300);
//        lblImage.setLocation(750, 10);
//        lblImage.setName("lblImageEnemy");        
//        
//        pnlGame.add(lblImage);
//    }
    
//    public void showInfosCombat(){
//        JLabel lblNumeroTour = new JLabel("<html><h2>Tour " + leCombat.getTour().getNumeroTour() + "</h2></html>");
//        lblNumeroTour.setName("lblNumeroTour");
//        lblNumeroTour.setSize(60, 60);
//        lblNumeroTour.setLocation(pnlGame.getWidth() / 2 - lblNumeroTour.getWidth() / 2, 10);
//        
//        pnlGame.add(lblNumeroTour);
//    }
    
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
    
//    private void setControlComp(boolean val) {
//        for (int i = 1; i < 5; i++){
//            this.getComponentByName("btnCompJoueur" + i).setEnabled(val);
//        }
//    }
    
//    public void enemyTurn(){        
//        if (SystemStats.isHit(enemy, perso)){
//            BLL_Competence bllComp = new BLL_Competence();
//
//            Random randomizer = new Random();
//
//            Competence c = bllComp.getCompetenceByClasse(enemy.getClasse_ID()).get(randomizer.nextInt(4));
//
//            if (c.getEffet_ID() != 3 && c.getEffet_ID() != 0){
//                leCombat.addEffetJoueur((new BLL_Effet()).getEffetById(c.getEffet_ID()));
//            }
//            else if(c.getEffet_ID() == 3){
//                leCombat.addEffetEnemy((new BLL_Effet()).getEffetById(c.getEffet_ID()));
//            }
//            
//            int degat = c.getDegat() + SystemStats.bonusAttaque(enemy, perso);
//
//            ((JProgressBar)this.getComponentByName("lifebarJoueur")).setValue(
//                ((JProgressBar)this.getComponentByName("lifebarJoueur")).getValue() - degat
//            );
//            ((JProgressBar)this.getComponentByName("lifebarJoueur")).setString(
//                ((JProgressBar)this.getComponentByName("lifebarJoueur")).getValue() + "/" + ((JProgressBar)this.getComponentByName("lifebarJoueur")).getMaximum()
//            );
//
//            JOptionPane.showMessageDialog(this, "L'ennemi utilise " + c.getLibelle() + " et inflige " + degat + " points de dégats");
//        }
//        else{
//            JOptionPane.showMessageDialog(this, "L'ennemi rate son attaque !");
//        }
//        
//        if (!processEffet(perso, leCombat.getEffetJoueur())){
//            enemyTurn();
//        }
//    }
    
//    public boolean processEffet(Personnage p, List<Effet> lesEffets){
//        boolean canPlay = true; //false si etourdit
//        int dmg = 0;
//        
//        for (Effet e : lesEffets){
//            switch(e.getEffet_ID()){
//                case 0: //Pas d'effet
//                    break;
//                case 1: //Empoisonnement
//                    JOptionPane.showMessageDialog(this, p.getNom() + " vomit... Il perd 2 PV.");
//                    dmg += 2;
//                    break;
//                case 2: //Etourdissement
//                    canPlay = false;
//                    JOptionPane.showMessageDialog(this, p.getNom() + " est etourdi, il ne peux pas jouer.");
//                    break;
//                case 3: //Regeneration 
//                    JOptionPane.showMessageDialog(this, p.getNom() + " se sent revivre ! Il recupère 3 PV.");
//                    dmg -= 3;
//                    break;
//                case 4: //Saignement
//                    JOptionPane.showMessageDialog(this, p.getNom() + " pisse le sang ! Il perd 1 PV");
//                    dmg += 1;
//                    break;
//                    
//                case 5:
//                    JOptionPane.showMessageDialog(this, p.getNom() + " a un peu trop chaud, c'est surement dû au flammes sur son corps. Il perd 4 PV");
//                    dmg += 4;
//                    break;
////                case 6:
////                    
////                    break;
//                default:
//                    JOptionPane.showMessageDialog(this, "L'effet numéro " + e.getEffet_ID() + " n'existe pas", "Erreur", JOptionPane.WARNING_MESSAGE);
//                    break;
//            }
//        }
//        
//        //Recupere la bonne JProgressBar en fonction du personnage en paramètre
//        String lifeBar = p.getPersonnage_ID() == perso.getPersonnage_ID()?"lifebarJoueur":"lifebarEnemy";
//        
//        ((JProgressBar)getComponentByName(lifeBar)).setValue(
//            ((JProgressBar)getComponentByName(lifeBar)).getValue() - dmg
//        );
//        ((JProgressBar)getComponentByName(lifeBar)).setString(
//            ((JProgressBar)getComponentByName(lifeBar)).getValue() + "/" + ((JProgressBar)getComponentByName(lifeBar)).getMaximum()
//        );
//        
//        return canPlay;
//    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnNew = new javax.swing.JButton();
        pnlGame = new javax.swing.JPanel();
        lblPersoEnCours = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        btnNew.setText("Nouveau");
        btnNew.setToolTipText("");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        pnlGame.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnlGameLayout = new javax.swing.GroupLayout(pnlGame);
        pnlGame.setLayout(pnlGameLayout);
        pnlGameLayout.setHorizontalGroup(
            pnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1070, Short.MAX_VALUE)
        );
        pnlGameLayout.setVerticalGroup(
            pnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );

        btnDelete.setText("Supprimer");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94)
                        .addComponent(lblPersoEnCours)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pnlGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew)
                    .addComponent(lblPersoEnCours)
                    .addComponent(btnDelete))
                .addGap(18, 18, 18)
                .addComponent(pnlGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        if (lblPersoEnCours.getText().length() > 0){
            if(JOptionPane.showConfirmDialog(this, "Voulez vous enregister le personnage en cours avant d'un créer un nouveau ?", "Attention", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(Frame.getFrames()[0], "T'as cru que ça sauvegardais vraiment ? On est en 2018, ça sauvegarde tout seul avant même que tu le veuille.", "Bouffon", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        lblPersoEnCours.setText("");
        
        loadMenuPerso();
        
        if ((new BLL_Personnage()).getAllPersonnages().size() == 12){
            JOptionPane.showMessageDialog(this, "Vous avez trop de personnage ! Supprimez en un avant d'en créer un nouveau.", "Attention", JOptionPane.WARNING_MESSAGE);
        }
        else{
            CreationPersonnageUI creationDialog = new CreationPersonnageUI(this, rootPaneCheckingEnabled);
            creationDialog.setVisible(true);
            creationDialog.setTitle("Création d'un nouveau personnage");
        }
    }//GEN-LAST:event_btnNewActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        if (lblPersoEnCours.getText().length() == 0){
            loadMenuPerso();
        }
    }//GEN-LAST:event_formWindowGainedFocus

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        SuppressionPersonnage uiSuppr = new SuppressionPersonnage(this, true, (new BLL_Personnage()).getAllPersonnages());
        uiSuppr.setVisible(true);
    }//GEN-LAST:event_btnDeleteActionPerformed

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
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SystemDirectory().createDir();

                new MainUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JLabel lblPersoEnCours;
    private javax.swing.JPanel pnlGame;
    // End of variables declaration//GEN-END:variables

    
}
