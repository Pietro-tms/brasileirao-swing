

import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Random;
import javax.swing.DefaultListModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author strim
 */
public class TelaBrasileirao extends javax.swing.JFrame {

    DefaultListModel lista = new DefaultListModel();
    String cabecalio1[] = {"Local", " ", "X", " ", "Visitante"};
    String cabecalio2[] = {"Time", "P", "V", "E", "D", "GP", "GN", "SG"};
    DefaultTableModel tabela1 = new DefaultTableModel(cabecalio1, 0);
    DefaultTableModel tabela2 = new DefaultTableModel(cabecalio2, 0);
    Time times[] = new Time[20];
    Jogo jogos[] = new Jogo[380];

    /**
     * Creates new form TelaBrasileirao
     */
    public TelaBrasileirao() {
        initComponents();
        pnl.setVisible(false);
        setTitle("Brasileirão - Versão Pietro");
        carregarTimes();
        preencheLista();
        setResizable(false);
        tbl1.setRowSelectionAllowed(false);
        tbl1.setColumnSelectionAllowed(false);
        tbl1.setCellSelectionEnabled(false);
        btnLimpar.setEnabled(false);
        ordenar();
    }

    public Time[] carregarTimes() {
        times[0] = new Time("America-MG");
        times[1] = new Time("Athletic Club");
        times[2] = new Time("Atlético-GO");
        times[3] = new Time("Avaí");
        times[4] = new Time("Botafogo-SP");
        times[5] = new Time("Ceará");
        times[6] = new Time("CRB");
        times[7] = new Time("Criciúma");
        times[8] = new Time("Cuiabá");
        times[9] = new Time("Fortaleza");
        times[10] = new Time("Goiás");
        times[11] = new Time("Juventude");
        times[12] = new Time("Londrina");
        times[13] = new Time("Náutico");
        times[14] = new Time("Novorizontino");
        times[15] = new Time("Operário-PR");
        times[16] = new Time("Ponte Preta");
        times[17] = new Time("São Bernardo");
        times[18] = new Time("Sport");
        times[19] = new Time("Vila Nova");
        return times;
    }

    public void preencheLista() {

        for (Time time : times) {
            lista.addElement(time.getNome());
        }
    }

    public void gerarJogos() {

        Random random = new Random();

        int pos = 0;

        for (int j = 0; j < 20; j++) {

            for (int k = 0; k < 20; k++) {

                if (j != k) {

                    int gol1 = random.nextInt(6) + 1;
                    int gol2 = random.nextInt(6) + 1;

                    jogos[pos] = new Jogo(
                            times[j],
                            times[k],
                            gol1,
                            gol2
                    );

                    pos++;
                }
            }
        }
    }

    public void preencheTabela1() {

        for (Jogo jogo : jogos) {

            tabela1.addRow(new Object[]{
                jogo.getTime1().getNome(),
                jogo.getGol1(),
                " ",
                jogo.getGol2(),
                jogo.getTime2().getNome()

            });
        }
    }

    public void calcResultados() {

        // Pontos e vitórias e empates
        for (Jogo jogo : jogos) {

            // gols pró
            jogo.getTime1().setGp(
                    jogo.getTime1().getGp() + jogo.getGol1()
            );

            jogo.getTime2().setGp(
                    jogo.getTime2().getGp() + jogo.getGol2()
            );

            // gols negativos
            jogo.getTime1().setGn(
                    jogo.getTime1().getGn() + jogo.getGol2()
            );

            jogo.getTime2().setGn(
                    jogo.getTime2().getGn() + jogo.getGol1()
            );

            // saldo de gols
            jogo.getTime1().setSg(
                    jogo.getTime1().getGp() - jogo.getTime1().getGn()
            );

            jogo.getTime2().setSg(
                    jogo.getTime2().getGp() - jogo.getTime2().getGn()
            );

            if (jogo.getGol1() > jogo.getGol2()) {

                // pontos
                jogo.getTime1().setP(
                        jogo.getTime1().getP() + 3
                );

                // vitórias
                jogo.getTime1().setV(
                        jogo.getTime1().getV() + 1
                );

                // derrotas
                jogo.getTime2().setD(
                        jogo.getTime2().getD() + 1
                );

            } else if (jogo.getGol1() < jogo.getGol2()) {

                // pontos
                jogo.getTime2().setP(
                        jogo.getTime2().getP() + 3
                );

                // vitórias
                jogo.getTime2().setV(
                        jogo.getTime2().getV() + 1
                );

                // derrotas
                jogo.getTime1().setD(
                        jogo.getTime1().getD() + 1
                );

            } else {

                // pontos
                jogo.getTime1().setP(
                        jogo.getTime1().getP() + 1
                );

                jogo.getTime2().setP(
                        jogo.getTime2().getP() + 1
                );

                // empates
                jogo.getTime1().setE(
                        jogo.getTime1().getE() + 1
                );

                jogo.getTime2().setE(
                        jogo.getTime2().getE() + 1
                );
            }
        }

    }

    public void preencheTabela2() {
        for (Time time : times) {

            tabela2.addRow(new Object[]{
                time.getNome(),
                time.getP(),
                time.getV(),
                time.getE(),
                time.getD(),
                time.getGp(),
                time.getGn(),
                time.getSg()
            });
        }
    }

    public void ordenar() {
        DefaultTableModel modelo = (DefaultTableModel) tbl2.getModel();

        TableRowSorter<DefaultTableModel> sorter
                = new TableRowSorter<>(modelo);

        tbl2.setRowSorter(sorter);

        sorter.toggleSortOrder(1);
        sorter.toggleSortOrder(1);
    }

    public void limpar() {
        pnl.setVisible(false);
        tabela1.setRowCount(0);
        tabela2.setRowCount(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        btnGerar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        pnl = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl1 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl2 = new javax.swing.JTable();

        jMenu1.setText("jMenu1");

        jMenuItem1.setText("jMenuItem1");

        jMenu2.setText("File");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Edit");
        jMenuBar1.add(jMenu3);

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        jList1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jList1.setModel(lista);
        jScrollPane2.setViewportView(jList1);

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel1.setText("Times");

        btnGerar.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        btnGerar.setText("Gerar");
        btnGerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarActionPerformed(evt);
            }
        });

        btnLimpar.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        tbl1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        tbl1.setModel(tabela1);
        tbl1.setToolTipText("");
        tbl1.setColumnSelectionAllowed(true);
        jScrollPane3.setViewportView(tbl1);
        tbl1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        tbl2.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        tbl2.setModel(tabela2);
        jScrollPane1.setViewportView(tbl2);

        javax.swing.GroupLayout pnlLayout = new javax.swing.GroupLayout(pnl);
        pnl.setLayout(pnlLayout);
        pnlLayout.setHorizontalGroup(
            pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlLayout.setVerticalGroup(
            pnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btnGerar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(pnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addGap(18, 18, 18)
                        .addComponent(btnGerar)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpar))
                    .addComponent(pnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarActionPerformed
        // TODO add your handling code here:
        pnl.setVisible(true);
        gerarJogos();
        preencheTabela1();
        calcResultados();
        preencheTabela2();
        btnGerar.setEnabled(false);
        btnLimpar.setEnabled(true);

    }//GEN-LAST:event_btnGerarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        // TODO add your handling code here:
        btnGerar.setEnabled(true);
        btnLimpar.setEnabled(false);
        limpar();
    }//GEN-LAST:event_btnLimparActionPerformed

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
            java.util.logging.Logger.getLogger(TelaBrasileirao.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaBrasileirao.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaBrasileirao.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaBrasileirao.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaBrasileirao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGerar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel pnl;
    private javax.swing.JTable tbl1;
    private javax.swing.JTable tbl2;
    // End of variables declaration//GEN-END:variables
}
