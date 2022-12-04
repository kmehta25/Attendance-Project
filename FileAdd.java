import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;

public class FileAdd {

    public void merge(String[] attendanceFileList, JTable jTable1, File directoryPath, String bracs){
        for(String s : attendanceFileList) { // for all files matching the format,
            // add them to the JTable using a method
            DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
            TableColumn col = new TableColumn(model.getColumnCount());

            // System.out.println(col);

            String headername = s.substring(0, 2) + "/" + s.substring(2, 4) + "/" + s.substring(4, 8);
            model.addColumn(headername);
            col.setHeaderValue(headername);

//                        System.out.print("current path: ");
//                        System.out.println(directoryPath.getAbsolutePath());
            String pathOfDirectory = directoryPath.getAbsolutePath();

            String path = pathOfDirectory + bracs + s;

            File file  = new File(path);
            int count_students_added=0;
            HashSet<Object> set= new HashSet<>();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));

                Object[] lines = br.lines().toArray();
                ArrayList<Integer> attendance = new ArrayList<>();
                for(int z = 0; z < jTable1.getRowCount(); z++) {
                    int time = 0;

                    for(int i=0;i<lines.length;i++){
                        String line=lines[i].toString().trim();
                        Object[] dataRow = line.split(",");
                        if(dataRow[0].equals( jTable1.getValueAt(z, 3))) {

                            set.add(dataRow[0]);
                            time += Integer.parseInt((String)dataRow[1]);
                        } // if
                    } // for i
                    attendance.add(time);
                }

                for(int k =0; k < attendance.size(); k++) {
                    jTable1.setValueAt(attendance.get(k),k,jTable1.getColumnCount() - 1);
                }

                JOptionPane.showMessageDialog(jTable1,"number of students added"+set.size());

            } catch (FileNotFoundException f) {
                f.printStackTrace();
            }


        } // for
    }
}
