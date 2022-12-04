import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileSave {

    public void save(File fileToSave, JTable jTable1){
        try {
            FileWriter fw = new FileWriter(fileToSave);
            BufferedWriter bw = new BufferedWriter(fw);
            for(int i = 0 ; i < jTable1.getColumnCount() ; i++)
            {
                bw.write(jTable1.getColumnName(i).toString() + ",");

            }
            bw.newLine();

            for(int i = 0; i < jTable1.getRowCount(); i++) {
                for(int j = 0; j < jTable1.getColumnCount(); j++) {
                    bw.write(jTable1.getValueAt(i, j).toString() + ",");
                }
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
