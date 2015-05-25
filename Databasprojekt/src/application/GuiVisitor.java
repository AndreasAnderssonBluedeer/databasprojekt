package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;




public class GuiVisitor extends JPanel {
	
	private JButton btnSchedule,btnBand;
	private JPanel pnlStart,pnlMain,pnlBands;
	
	public GuiVisitor(){

		pnlStart=showStartScreen();
		pnlBands=showBands();
		pnlMain=this;	
		
		pnlMain.add(pnlStart);
		
		
	}
	public JButton getHomeBtn(){
		JButton btn=new JButton("Till Startmeny");
		btn.setBackground(Color.GRAY);
		btn.setPreferredSize(new Dimension(150,20));
		btn.addActionListener(new HomeListener());
		return btn;
	}
	public void clearPanel(){
		pnlMain.removeAll();
		repaintMenu();
	}
	public void repaintMenu(){
		pnlMain.repaint();
	}
	public JPanel showStartScreen(){
		JPanel pnlStart = new JPanel();
		pnlStart.setBackground(Color.DARK_GRAY);
		pnlStart.setPreferredSize(new Dimension(800,600));
		
		JLabel emptyStart=new JLabel();
		emptyStart.setPreferredSize(new Dimension(800,200));
		emptyStart.setBackground(Color.DARK_GRAY);
		
		btnSchedule=new JButton("Spelscheman");
		btnSchedule.setPreferredSize(new Dimension(300,60));
		btnSchedule.setBackground(Color.GRAY);
		btnSchedule.addActionListener(new ScheduleListener());
		
		btnBand=new JButton("Band");
		btnBand.setPreferredSize(new Dimension(300,60));
		btnBand.setBackground(Color.GRAY);
		btnBand.addActionListener(new BandListener());				
		
		pnlStart.add(emptyStart);
		pnlStart.add(btnSchedule);
		pnlStart.add(btnBand);
			
		
		return pnlStart;
	}
	public JPanel showBands(){
		JPanel pnlBand=new JPanel();
		pnlBand.setBackground(Color.DARK_GRAY);
		pnlBand.setPreferredSize(new Dimension(800,600));
		
		pnlBand.add(getHomeBtn());
		
		JLabel lblTitle=new JLabel("BAND:");
		lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));	
		lblTitle.setHorizontalAlignment(JLabel.RIGHT);
		lblTitle.setForeground(Color.LIGHT_GRAY);
		lblTitle.setPreferredSize(new Dimension(600,80));
		
		pnlBand.add(lblTitle);
		
		ArrayList<JButton> btnBandList=new ArrayList<>();
		for(int i=0;i<21;i++){	//Kommer sedan bli bandlistans längd*
			btnBandList.add(new JButton("Band"+i));	//Band+i ==Databas-BandNamn
			btnBandList.get(i).setPreferredSize(new Dimension(250,20));
			btnBandList.get(i).setBackground(Color.GRAY);
			pnlBand.add(btnBandList.get(i));
		}
		
		return pnlBand;
	}
	private class BandListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnBand){		
				clearPanel();
			
				pnlMain.add(pnlBands);
				pnlMain.revalidate();
				repaintMenu();
				//Hämta information från databas.		
			}
			
		}
		
	}
	private class ScheduleListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnSchedule){
				pnlStart.setBackground(Color.GREEN);
				//Hämta o visa Spelschema JPanel.
				//Hämta information från databas.
				//Ta bort /Disable förra panelen.
			}
			
		}
		
	}
	private class HomeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
				clearPanel();	
				pnlMain.add(pnlStart);
				pnlMain.revalidate();
				repaintMenu();
				//Hämta information från databas.
				//Ta bort /Disable förra panelen.
			
			
		}
		
	}
	
	public static void main(String [] args){
		JFrame f= new JFrame("Festivalinfo-Besökare");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new GuiVisitor());
		f.setLocation(500, 100);
		f.setSize(800, 600);
		f.setVisible(true);
		f.setResizable(false);
		
		
		
	}










}
