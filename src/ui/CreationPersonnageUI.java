/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import bean.*;
import bll.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author b78877
 */
public class CreationPersonnageUI extends javax.swing.JDialog {
    
    private Personnage perso = new Personnage();

    /**
     * Creates new form CreationPersonnageUI
     */
    public CreationPersonnageUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
//        BLL_Classe bllClasse = new BLL_Classe();
//        BLL_Race bllRace = new BLL_Race();
        
        List<Race> lesRaces = (new BLL_Race()).getAllRaces();
        List<Classe> lesClasses = (new BLL_Classe()).getAllClasses();
        
        int x = 10;
        
        for(Race r : lesRaces){
            JButton btn = new JButton(r.getLibelle());
            btn.setActionCommand(r.getRace_ID() + "");
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    perso.setRace_ID(Integer.parseInt(((JButton)e.getSource()).getActionCommand()));
                    lblChoosenRace.setText(((JButton)e.getSource()).getText());
                                        
                    showStatsRace(perso.getRace_ID());
                }
            });
            btn.setVisible(true);
            btn.setBounds(x, pnlRace.getY() + 5, 80, 70);
            x += 85;
            this.pnlRace.add(btn);
        }
        
        x = 10;
        
        for(Classe c : lesClasses){
            JButton btn = new JButton(c.getLibelle());
            btn.setActionCommand(c.getClasse_ID() + "");
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    perso.setClasse_ID(Integer.parseInt(((JButton)e.getSource()).getActionCommand()));
                    lblChoosenClasse.setText(((JButton)e.getSource()).getText());
                    
                    showCompetence(perso.getClasse_ID());
                    showStatsClasse(perso.getClasse_ID());
                }
            });
            btn.setVisible(true);
            btn.setBounds(x, pnlClasse.getY() + 5, 90, 70);
            x += 95;
            this.pnlClasse.add(btn);
        }
        
        //JScrollPane scrlClasse = new JScrollPane(pnlClasse);
//        scrlClasse.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        scrlClasse.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
//        scrlClasse.setBounds(pnlClasse.getX(), pnlClasse.getY() + pnlClasse.getHeight(), pnlClasse.getWidth(), pnlClasse.getHeight());
    }
    /**
     * Affiche les competences de la classe sélectionnée dans les JLabels prévus à cet effet
     * 
     * @param classeId 
     */
    public void showCompetence(int classeId){
        List<Competence> lesComp = (new BLL_Competence()).getCompetenceByClasse(classeId);
        BLL_Effet bllEffet = new BLL_Effet();
        
        int i = 0;
        String infos = "";
        
        for (Competence c : lesComp){
            Effet effet = bllEffet.getEffetById(c.getEffet_ID());
            
            infos = "<html><b style='font-size:150%'>" + c.getLibelle()  + "</b>"
                    + "<br>Dégats : " + c.getDegat() 
                    + "<br>Effet : <b>" + effet.getLibelle() + "</b></html>";
            
            ((JLabel) pnlComp.getComponent(i)).setText(infos);
            ((JLabel) pnlComp.getComponent(i)).setToolTipText("<html>" + effet.getHtmlDesc() + "</html>");
                    
            i++;            
        }
    }
    
    /**
     * Affiche les spécialités de la race sélectionnée dans les JLabels prévus à cet effet
     * 
     * @param raceId 
     */
    public void showStatsRace(int raceId){
        Race r = (new BLL_Race()).getRaceById(raceId);
                
        lblStatsRace.setText(
                "<html><ul>"
                    + "<li>PV : " + (r.getPv() + 5) + "</li>"
                    + "<li>Esquive : " + r.getEsquive() + "</li>"
                    + "<li>Précision : " + r.getPrecision() +"</li>"
                + "</ul></html>"
        );
    }
    
    public void showStatsClasse(int classId){
        Classe c = (new BLL_Classe()).getClasseById(classId);
        
        lblStatsClasse.setText(
                "<html><ul>"
                    + "<li>Attaque : " + c.getAttaque() + "</li>"
                    + "<li>Defense : " + c.getDefense()+ "</li>"
                    + "<li>Vitesse : " + c.getVitesse()+ "</li>"
                + "</ul></html>"
        );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlRace = new javax.swing.JPanel();
        pnlClasse = new javax.swing.JPanel();
        javax.swing.JButton btnValider = new javax.swing.JButton();
        lblRace = new javax.swing.JLabel();
        lblClasse = new javax.swing.JLabel();
        lblChoosenRace = new javax.swing.JLabel();
        lblChoosenClasse = new javax.swing.JLabel();
        pnlSpec = new javax.swing.JPanel();
        lblStatsRace = new javax.swing.JLabel();
        lblStatsClasse = new javax.swing.JLabel();
        pnlComp = new javax.swing.JPanel();
        lblComp1 = new javax.swing.JLabel();
        lblComp3 = new javax.swing.JLabel();
        lblComp2 = new javax.swing.JLabel();
        lblComp4 = new javax.swing.JLabel();
        lblNomPerso = new javax.swing.JLabel();
        txtNomPerso = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlRace.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlRace.setName("Races"); // NOI18N

        javax.swing.GroupLayout pnlRaceLayout = new javax.swing.GroupLayout(pnlRace);
        pnlRace.setLayout(pnlRaceLayout);
        pnlRaceLayout.setHorizontalGroup(
            pnlRaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 442, Short.MAX_VALUE)
        );
        pnlRaceLayout.setVerticalGroup(
            pnlRaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 136, Short.MAX_VALUE)
        );

        pnlClasse.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnlClasseLayout = new javax.swing.GroupLayout(pnlClasse);
        pnlClasse.setLayout(pnlClasseLayout);
        pnlClasseLayout.setHorizontalGroup(
            pnlClasseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlClasseLayout.setVerticalGroup(
            pnlClasseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 136, Short.MAX_VALUE)
        );

        btnValider.setText("Valider");
        btnValider.setToolTipText("");
        btnValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValiderActionPerformed(evt);
            }
        });

        lblRace.setText("Races :");

        lblClasse.setText("Classes :");

        lblChoosenRace.setForeground(new java.awt.Color(255, 0, 0));

        lblChoosenClasse.setForeground(new java.awt.Color(255, 0, 0));

        pnlSpec.setBorder(javax.swing.BorderFactory.createTitledBorder("Specialité"));
        pnlSpec.setPreferredSize(new java.awt.Dimension(400, 200));

        lblStatsRace.setMaximumSize(new java.awt.Dimension(50, 30));

        javax.swing.GroupLayout pnlSpecLayout = new javax.swing.GroupLayout(pnlSpec);
        pnlSpec.setLayout(pnlSpecLayout);
        pnlSpecLayout.setHorizontalGroup(
            pnlSpecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSpecLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblStatsRace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88)
                .addComponent(lblStatsClasse)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSpecLayout.setVerticalGroup(
            pnlSpecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSpecLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSpecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStatsClasse)
                    .addComponent(lblStatsRace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlComp.setBorder(javax.swing.BorderFactory.createTitledBorder("Compétences"));
        pnlComp.setPreferredSize(new java.awt.Dimension(500, 200));

        lblComp1.setMaximumSize(new java.awt.Dimension(50, 14));

        lblComp3.setMaximumSize(new java.awt.Dimension(50, 14));

        lblComp2.setMaximumSize(new java.awt.Dimension(50, 14));

        lblComp4.setMaximumSize(new java.awt.Dimension(50, 14));

        javax.swing.GroupLayout pnlCompLayout = new javax.swing.GroupLayout(pnlComp);
        pnlComp.setLayout(pnlCompLayout);
        pnlCompLayout.setHorizontalGroup(
            pnlCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCompLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblComp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblComp3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(157, 157, 157)
                .addGroup(pnlCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblComp4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblComp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(331, Short.MAX_VALUE))
        );
        pnlCompLayout.setVerticalGroup(
            pnlCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCompLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblComp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblComp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69)
                .addGroup(pnlCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblComp3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblComp4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(97, Short.MAX_VALUE))
        );

        lblNomPerso.setText("Nom : ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblNomPerso)
                        .addGap(18, 18, 18)
                        .addComponent(txtNomPerso, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 809, Short.MAX_VALUE)
                        .addComponent(btnValider))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(pnlSpec, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                            .addComponent(pnlRace, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lblRace)
                                .addGap(18, 18, 18)
                                .addComponent(lblChoosenRace)))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblClasse)
                                .addGap(18, 18, 18)
                                .addComponent(lblChoosenClasse))
                            .addComponent(pnlClasse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlComp, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRace)
                    .addComponent(lblChoosenRace)
                    .addComponent(lblClasse)
                    .addComponent(lblChoosenClasse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlClasse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlRace, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlComp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlSpec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnValider)
                    .addComponent(lblNomPerso)
                    .addComponent(txtNomPerso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValiderActionPerformed
        if (perso.getClasse_ID() == 0){
            JOptionPane.showMessageDialog(this, "Veuillez choisir une classe", "Attention", JOptionPane.WARNING_MESSAGE);
        }
        else if(perso.getRace_ID() == 0){
            JOptionPane.showMessageDialog(this, "Veuillez choisir une race", "Attention", JOptionPane.WARNING_MESSAGE);
        }
        else if (txtNomPerso.getText().length() == 0){
            JOptionPane.showMessageDialog(this, "Veuillez choisir un nom", "Attention", JOptionPane.WARNING_MESSAGE);
        }
        else{
            perso.setNom(txtNomPerso.getText());
            perso.setUrl(perso.getClasse_ID(), perso.getRace_ID(), true);
            
            BLL_Personnage bllPerso = new BLL_Personnage();
            bllPerso.newPersonnage(perso);
            
            this.dispose();
        }
    }//GEN-LAST:event_btnValiderActionPerformed

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
            java.util.logging.Logger.getLogger(CreationPersonnageUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreationPersonnageUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreationPersonnageUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreationPersonnageUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CreationPersonnageUI dialog = new CreationPersonnageUI(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel lblChoosenClasse;
    private javax.swing.JLabel lblChoosenRace;
    private javax.swing.JLabel lblClasse;
    private javax.swing.JLabel lblComp1;
    private javax.swing.JLabel lblComp2;
    private javax.swing.JLabel lblComp3;
    private javax.swing.JLabel lblComp4;
    private javax.swing.JLabel lblNomPerso;
    private javax.swing.JLabel lblRace;
    private javax.swing.JLabel lblStatsClasse;
    private javax.swing.JLabel lblStatsRace;
    private javax.swing.JPanel pnlClasse;
    private javax.swing.JPanel pnlComp;
    private javax.swing.JPanel pnlRace;
    private javax.swing.JPanel pnlSpec;
    private javax.swing.JTextField txtNomPerso;
    // End of variables declaration//GEN-END:variables
}
