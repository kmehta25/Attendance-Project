import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JPanel;

public class BarChart extends JPanel {

	/**
	 * Create the panel.
	 */
	private int[] values;
	private String[] names;
	private String title;
	
	public BarChart(String[] xList, int[] yList) {
		setLayout(null);
		values = yList;
		names = xList;
		title = "Attendance plot";
	} // BarChart() constructor
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(values == null || values.length == 0) {
			return;
		} // if
		int minValue = 0;
		int maxValue = 0;
		
		for(int i = 0; i < values.length; i++) {
			if(minValue > values[i])
				minValue = values[i];
			if(maxValue < values[i])
				maxValue = values[i];
		} // for
		Dimension d = getSize();
		int width = d.width;
		int height = d.height;
		
		int barWidth = width/values.length;
		
		Font titleFont = new Font("Times New Roman", Font.BOLD, 20);
		FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
		Font labelFont = new Font("Times New Roman", Font.PLAIN, 10);
		FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);
		
		int titleWidth = titleFontMetrics.stringWidth(title);
		int y = titleFontMetrics.getAscent();
		int x = (width -titleWidth) / 2;
		g.setFont(titleFont);
		g.drawString(title, x, y);
		
		int top = titleFontMetrics.getHeight();
		int bottom = labelFontMetrics.getHeight();
		if(maxValue == minValue)
			return;
		
		double scale = (height - top - bottom)/(maxValue - minValue);
		y = height - labelFontMetrics.getDescent();
		g.setFont(labelFont);
		
		for(int i = 0; i < values.length; i++) {
			int xValue = i * barWidth + 1;
			int yValue = top;
			int barHeight = (int) (values[i] * scale);
			if(values[i] >= 0) {
				yValue += (int) ((maxValue - values[i]) * scale);
			} // if
			else {
				yValue += (int) (maxValue * scale);
		        barHeight = -barHeight;
			} // else
			
			g.setColor(Color.blue);
			g.fillRect(xValue, yValue, barWidth - 2, barHeight);
			g.setColor(Color.black);
			g.drawRect(xValue, yValue, barWidth - 2, barHeight);
			
			int labelWidth = labelFontMetrics.stringWidth(names[i]);
			x = i * barWidth + (barWidth - labelWidth) / 2;
			g.drawString(names[i],x, y);

		} // for
	} // paintComponent	
} // classBarChart

 