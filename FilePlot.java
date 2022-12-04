import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JPanel;

public class FilePlot extends JPanel {
    private static final long serialVersionUID = 1L;
	/**
     * Create the panel.
     */
    int[] listOfValues;
    String[] listOfNames;
    String title;
    public FilePlot(String[] listOfNames, int[] listOfValues) {
        this.listOfValues = listOfValues;
        this.listOfNames = listOfNames;
        title = "Number of Students vs Attendance Date";
    } // BarChart() constructor

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(listOfValues == null || listOfValues.length == 0) {
            return;
        } // if
        int mini = 0;
        int maxi = 0;

        Dimension d = getSize();
        int width = d.width;
        int height = d.height;

        int barWidth = width / listOfValues.length;

        for(int i = 0; i < listOfValues.length; i++) {
            if(mini > listOfValues[i])
                mini = listOfValues[i];
            if(maxi < listOfValues[i])
                maxi = listOfValues[i];
        } // for

        //setting font for labels
        Font f_label = new Font("Times New Roman", Font.BOLD, 12);
        FontMetrics f_labelmetrics = g.getFontMetrics(f_label);
        //setting font for title
        Font f_title = new Font("Times New Roman", Font.BOLD, 22);
        FontMetrics f_titlemetrics = g.getFontMetrics(f_title);

        int titleWidth = f_titlemetrics.stringWidth(title);
        int yaxis = f_titlemetrics.getAscent();
        int xaxis = (width - titleWidth) / 2;
        g.setFont(f_title);
        g.drawString(title, xaxis, yaxis);

        int start = f_titlemetrics.getHeight();
        int end = f_labelmetrics.getHeight();
        if(maxi == mini)
            return;

        double scale = (height - start - end) / (maxi - mini);
        yaxis = height - f_labelmetrics.getDescent();
        g.setFont(f_label);

        for(int i = 0; i < listOfValues.length; i++) {
            int xValue = i * barWidth + 1;
            int yValue = start;
            int barHeight = (int) (listOfValues[i] * scale);
            if(listOfValues[i] >= 0) {
                yValue += (int) ((maxi - listOfValues[i]) * scale);
            } // if
            else {
                yValue += (int) (maxi * scale);
                barHeight = -barHeight;
            } // else

            g.setColor(Color.blue);
            g.fillRect(xValue, yValue, barWidth - 2, barHeight);
            g.setColor(Color.black);
            g.drawRect(xValue, yValue, barWidth - 2, barHeight);
//            int chartX = ;
//            int chartY = barHeight - AXIS_OFFSET;

            int labelWidth = f_labelmetrics.stringWidth(listOfNames[i]);
            xaxis = i * barWidth + (barWidth - labelWidth) / 2;
            g.drawString(listOfNames[i],xaxis, yaxis);

        } // for
    } // paintComponent()
} // class BarChart

 