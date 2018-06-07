/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import bean.Map;
import bean.Personnage;
import bean.PositionPerso;
import bll.BLL_Map;
import bll.BLL_PositionPerso;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author b78877
 */
public class MapUI extends javax.swing.JFrame {

    private Map laMap;
    private int indexMap;
    
    private Personnage perso;
    
    private int persoX;
    private int persoY;
        
    /**
     * Creates new form MapUI
     */
    public MapUI(){
        initComponents();
    }
    
    public MapUI(Personnage perso) {
        initComponents();
        
        this.perso = perso;
        
        this.setSize(1000, 1050);
        this.setResizable(true);
        this.setTitle("Map 1");
        
        pnlMap.setSize(1000, 1050);
        
        PositionPerso pp = (new BLL_PositionPerso()).getPositionPerso(perso.getPersonnage_ID());
        
        laMap = new Map();
        laMap = (new BLL_Map()).getMapByNum((pp != null)?pp.getIndexMap():0);
                
        if (pp != null){
            persoX = pp.getX();
            persoY = pp.getY();
            indexMap = pp.getIndexMap();
            this.setTitle("Map " + indexMap);
        }
        else{
            indexMap = 0;
            persoX = 1;
            persoY = 1;
        }
        
        loadTableau();
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    public void loadTableau(){
        int y = 0;
        int x = 0;
        
        this.setTitle("Map " + indexMap);
        
        for (int i = 0; i<10; i++){
            for (int j = 0; j<10; j++){
                JLabel lbl = new JLabel((persoX==j && persoY==i)?"@":laMap.getPositionValue(j, i));
                lbl.setName(i + ":" + j);
                
                Font f = new Font("Serif", Font.BOLD, 50);
                
                lbl.setFont(f);
                
                y = 0;
                x = 20;
                
                switch(i){
                    case 0:
                        y += 0;
                        break;
                    case 1:
                        y += 100;
                        break;
                    case 2:
                        y += 200;
                        break;
                    case 3:
                        y += 300;
                        break;
                    case 4:
                        y += 400;
                        break;
                    case 5:
                        y += 500;
                        break;
                    case 6:
                        y += 600;
                        break;
                    case 7:
                        y += 700;
                        break;
                    case 8:
                        y += 800;
                        break;
                    case 9:
                        y += 900;
                        break;
                    default:
                        break;
                }
                
                switch(j){
                    case 0:
                        x += 0;
                        break;
                    case 1:
                        x += 100;
                        break;
                    case 2:
                        x += 200;
                        break;
                    case 3:
                        x += 300;
                        break;
                    case 4:
                        x += 400;
                        break;
                    case 5:
                        x += 500;
                        break;
                    case 6:
                        x += 600;
                        break;
                    case 7:
                        x += 700;
                        break;
                    case 8:
                        x += 800;
                        break;
                    case 9:
                        x += 900;
                        break;
                    default:
                        break;
                }
                lbl.setSize(80, 80);
                lbl.setLocation(x, y);
                
                pnlMap.add(lbl);
            }
        }
    }
    
    public void reload(){
        pnlMap.removeAll();
        loadTableau();
        pnlMap.validate();
        pnlMap.repaint();
    }
    
    public void savePosPers(){
        (new BLL_PositionPerso()).savePosPers(persoX, persoY, indexMap, perso.getPersonnage_ID());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMap = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(500, 500));
        setMinimumSize(new java.awt.Dimension(500, 500));
        setPreferredSize(new java.awt.Dimension(500, 500));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout pnlMapLayout = new javax.swing.GroupLayout(pnlMap);
        pnlMap.setLayout(pnlMapLayout);
        pnlMapLayout.setHorizontalGroup(
            pnlMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        pnlMapLayout.setVerticalGroup(
            pnlMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        boolean moved = true;
        BLL_Map bllMap = new BLL_Map();
        
        switch(evt.getKeyCode()){
            case KeyEvent.VK_UP:
                if (persoY - 1 < 0 ){
                    indexMap -= 3;
                    laMap = bllMap.getMapByNum(indexMap);
                    persoY = 9;
                }
                else if (laMap.getPositionValue(persoX, persoY - 1).equals("#")){
                    persoY--;
                }
                else if (laMap.getPositionValue(persoX, persoY - 1).equals("X")){
                    JOptionPane.showMessageDialog(this, "Bonjour ! Je suis un X totalement inutile ! Comment vas tu ?");
                    moved = false;
                }
                else{
                    moved = false;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (persoY + 1 > 9){
                    indexMap += 3;
                    laMap = bllMap.getMapByNum(indexMap);
                    persoY = 0;
                }
                else if (laMap.getPositionValue(persoX, persoY + 1).equals("#")){
                    persoY++;
                }
                else if (laMap.getPositionValue(persoX, persoY + 1).equals("X")){
                    JOptionPane.showMessageDialog(this, "Bonjour ! Je suis un X totalement inutile ! Comment vas tu ?");
                    moved = false;
                }
                else{
                    moved = false;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (persoX - 1 < 0){
                    indexMap--;
                    laMap = bllMap.getMapByNum(indexMap);
                    persoX = 9;
                }
                else if (laMap.getPositionValue(persoX - 1, persoY).equals("#")){
                    persoX--;
                }
                else if (laMap.getPositionValue(persoX - 1, persoY).equals("X")){
                    JOptionPane.showMessageDialog(this, "Si tu vas sur en X = 3 ; Y = 4 sur la map 6 tu auras une surprise !");
                    moved = false;
                }
                else{
                    moved = false;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (persoX + 1 > 9){
                    indexMap++;
                    laMap = bllMap.getMapByNum(indexMap);
                    persoX = 0;
                }
                else if (laMap.getPositionValue(persoX + 1, persoY).equals("#")){
                    persoX++;
                }
                else if (laMap.getPositionValue(persoX + 1, persoY).equals("X")){
                    JOptionPane.showMessageDialog(this, "Bonjour ! Je suis un X totalement inutile ! Comment vas tu ?");
                    moved = false;
                }
                else{
                    moved = false;
                }
                break;
        }
        
        if (persoX == 3 && persoY == 4 && indexMap == 6){
            JOptionPane.showMessageDialog(this, "Kesstufoula ?... Une surprise ?... Faut aller en X = 8; Y = 5 de la map 2 si t'en veux une !");
        }
        if (persoX == 8 && persoY == 5 && indexMap == 2){
            String reponse = JOptionPane.showInputDialog(this, ""
                    + "<html>"
                        + "Bonjour, je suis une surprise !<br> "
                        + "Un plaisir de vous rencontrer, mais pour obtenir une récompense tu vas devoir répondre à une enigme !<br><br> "
                        + "'X apercoit Y qui l'interpelle : \"J'apporte une terrible nouvelle : Ma trompette ne fonctionne plus ! Comment vais-je faire pour mon concert ce soir ??\"'<br><br>"
                        + "X, d'un élan de courage se propose alors de remplacer cette instrument perdu : ..."
                    + "</html>"
            );
            
            if (reponse.toUpperCase().equals("POUET")){
                JOptionPane.showMessageDialog(this, "<html>Well played !<br> Tu as terminé ce fantastique RPG ! Tu peux bien évidemment continuer de te balader dans cet open world presque pas vide,<br> tu peux aussi répondre une nouvelle fois à l'enigme afin de crâner devant tes copains pendant la récré !</html>");
            }
        }
        
        if (moved){
            Random randCombat = new Random();
            if (randCombat.nextInt(9) == 1){
                CombatUI combat = new CombatUI(perso);
                combat.setVisible(true);
            }
        }
        reload();
    }//GEN-LAST:event_formKeyPressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        savePosPers();
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(MapUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MapUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MapUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MapUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MapUI dialog = new MapUI();
                
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlMap;
    // End of variables declaration//GEN-END:variables
}