package framesystem;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import kadai.MySQL;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class FrameSystemView extends Frame implements ActionListener,WindowListener{


	private Button button1 = new Button("表示");
	CardLayout cardlayout;
	Panel panelA;
	Panel panelB;
	

	
	public FrameSystemView(FrameSystemController controller) {
		// TODO Auto-generated constructor stub
		panelA =new Panel();
		panelB = new Panel();
		
		addWindowListener(this);
		setTitle("Graph");
		cardlayout= new CardLayout();
		setLayout(cardlayout);
		panelB.add(button1,BorderLayout.CENTER);
		add(panelB); 
		add(panelA); 
		button1.addActionListener(this); 

		

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == button1){
			int id,number;
			String year;
			ResultSet rs;
			MySQL mysql = new MySQL();
			rs = mysql.selectAll();
			DefaultCategoryDataset data = new DefaultCategoryDataset();
			try {
				
				while(rs.next()){
				id = rs.getInt("id");
				year = rs.getString("year");
			    number = rs.getInt("number");
			    data.addValue(number,"出生数",year); 
	 			panelA.add(new Label("id:"+id+" / year"+year+" / number"+number)); 
				}

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			    JFreeChart chart = 
			      ChartFactory.createLineChart("出生率",
			                                   "年度",
			                                   "万(人)",
			                                   data,
			                                   PlotOrientation.VERTICAL,
			                                   true,
			                                   false,
			                                   false);

			    ChartPanel cpanel = new ChartPanel(chart);
			    panelA.add(cpanel);
			    cardlayout.next(this);

		}
	}

}

