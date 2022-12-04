import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileLoad {

    public void load(String[] colNames, JTable jTable1, File file) {
        try {
        BufferedReader br = new BufferedReader(new FileReader(file));
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(colNames);
        jTable1.setModel(model);
        // jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        Object[] lines = br.lines().toArray();
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].toString().trim();
            Object[] dataRow = line.split(",");
            model.addRow(dataRow);

            //System.out.println(dataRow);
            //System.out.println(line);
        }
    } catch (
            FileNotFoundException e) {
        e.printStackTrace();

    }
}
}
