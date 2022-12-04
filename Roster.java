import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout.Alignment;

public class Roster extends javax.swing.JFrame {


    private static final long serialVersionUID = 1L;
	public Roster() {
        initComponents();
    }

    /***
     * @author abhay kunj shilpi
     */
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        load = new javax.swing.JMenuItem();
        add = new javax.swing.JMenuItem();

        add.addActionListener(new ActionListener() {
            String bracs="";

            /***
             * @author kunj abhay shilpi
             * @param e
             */
            public void actionPerformed(ActionEvent e) {
                System.out.println(System.getProperty("os.name"));
                if(System.getProperty("os.name").equals( "Mac OS X")){
                     bracs="//";
                }
                else{
                     bracs="\\";
                }
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnValue = fileChooser.showOpenDialog(null);
                if(returnValue == JFileChooser.APPROVE_OPTION) {
                    File directoryPath = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    FilenameFilter filter = new FilenameFilter() {

                        /***
                         *
                         * @param file
                         * @param name
                         * @return
                         */
                        public boolean accept(File file, String name) {
                            return name.matches("^\\d{8}\\sattendance\\.csv$");
                            //matches files like: 12061998 attendance.csv
                        } // accept()
                    }; // FilenameFilter

                    String[] attendanceFileList = directoryPath.list(filter);

                    FileAdd aobj = new FileAdd();
                    aobj.merge( attendanceFileList, jTable1, directoryPath, bracs);
                } // if APPROVE_OPTION
            }
        });
        save = new javax.swing.JMenuItem();
        plot = new javax.swing.JMenuItem();

        plot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int numberOfBars = jTable1.getColumnCount() - 4;
                String[] listOfNames = new String[numberOfBars];
                int[] listOfValues = new int[numberOfBars];

                for(int i = 4; i < jTable1.getColumnCount(); i++) {
                    listOfNames[i - 4] = jTable1.getColumnName(i);
                } // i for list of col names

                //list of entries is summation of row: +1 for each nonzero value in the row
                for(int i = 4; i < jTable1.getColumnCount(); i++) {
                    int totalForThisRow = 0;
                    for(int j = 0; j < jTable1.getRowCount(); j++) {
                        int currentVal = (int)jTable1.getValueAt(j, i);
                        if(currentVal != 0) {
                            totalForThisRow++;
                        } // if
                    } // j for - ROW
                    listOfValues[i - 4] = totalForThisRow;
                } // i for - COLUMN

                JFrame frame = new JFrame();

                FilePlot fobj= new FilePlot(listOfNames, listOfValues);

                frame.setMinimumSize(new Dimension(500, 400));
                frame.getContentPane().add(fobj);
                frame.setVisible(true);

            } // actionPerformed
        });

        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {

                }
        ));
        jScrollPane2.setViewportView(jTable1);

        jMenu1.setText("File");

        load.setText("Load Roster");
        load.addMouseListener(new java.awt.event.MouseAdapter() {

            /***
             *
             * @param evt
             */
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loadMouseClicked(evt);
            }
        });
        load.addActionListener(new java.awt.event.ActionListener() {

            /***
             *
             * @param evt
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadActionPerformed(evt);
            }
        });
        jMenu1.add(load);

        add.setText("Add Attendance");
        jMenu1.add(add);

        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {

            /***
             *
             * @param evt
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        jMenu1.add(save);

        plot.setText("Plot");
        jMenu1.add(plot);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("About");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {

            /**
             *
             * @param evt
             */
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenu2.addActionListener(new java.awt.event.ActionListener() {

            /***
             *
             * @param evt
             */
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(27)
                                .addComponent(jScrollPane2)
                                .addGap(40))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(100, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(16))
        );
        getContentPane().setLayout(layout);

        pack();
    }

    /***
     * @author shilpi
     * @param evt
     */
    private void loadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadActionPerformed

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("1 supported extension", "csv");
        fileChooser.setFileFilter(filter);
        int selected= fileChooser.showOpenDialog(null);
        if(selected == JFileChooser.APPROVE_OPTION) {

            File file = fileChooser.getSelectedFile();
            String csvpath = file.getAbsolutePath();
            System.out.print(csvpath);

            String[] colNames = { "ID", "FirstName", "Lastname", "ASURITE"};

            FileLoad lobj = new FileLoad();
            lobj.load(colNames, jTable1, file);
            }
//            finally {
//                System.out.println("ROW COUNT: " + jTable1.getRowCount());
//                System.out.println("COLUMN COUNT: " + jTable1.getColumnCount());
//                for(int i = 0; i < jTable1.getRowCount(); i++) {
//                    for(int j = 0; j < jTable1.getColumnCount(); j++) {
//                        String value = jTable1.getValueAt(i, j).toString();
//                       // System.out.println("value at this spot: " + value);
//                    } // j for
//                } // i for
//
//            } // finally


    }//event_loadActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    /***
     * @author kunj
     * @param evt
     */
    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {
        String info = "Team members:\nKunj Mehta\nAbhay Agarwal\nShilpi Parikh";
        JOptionPane.showMessageDialog(this, info, "Team Information", 1);
    }//event_jMenu2MouseClicked

    /***
     *
     * @param evt
     */
    private void loadMouseClicked(java.awt.event.MouseEvent evt) {
        JFileChooser chosenFile = new JFileChooser();
        chosenFile.showOpenDialog(null);
    }//event_loadMouseClicked

    /***
     * @author kunj
     * @param evt
     */
    private void saveActionPerformed(java.awt.event.ActionEvent evt) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify path to save");

        int result = fileChooser.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            FileSave hobj = new FileSave();
            hobj.save(fileToSave, jTable1);

        } // if
    }//event_saveActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Roster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Roster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Roster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Roster.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Roster().setVisible(true);

            }
        });
    }

    // Variables declaration
    private javax.swing.JMenuItem add;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JMenuItem load;
    private javax.swing.JMenuItem plot;
    private javax.swing.JMenuItem save;
    // End of variables declaration
}
