package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class GuiStaff extends JPanel {

	
	private JButton btnSchedule,btnBand,btnContact,btnSecurity;
	private JPanel pnlStart;
	
	public GuiStaff(){
		
	this.setBackground(Color.DARK_GRAY);
	pnlStart=showStartScreen();
	
	JLabel empty=new JLabel();
	empty.setPreferredSize(new Dimension(400,200));
	empty.setBackground(Color.DARK_GRAY);
	
	this.setLayout(new BorderLayout());
	this.add(BorderLayout.CENTER,pnlStart);
	this.add(BorderLayout.NORTH,empty);
	
	
}
public void clearPanel(){
	this.removeAll();
}
public JPanel showStartScreen(){
	JPanel pnlStart = new JPanel();
	pnlStart.setBackground(Color.DARK_GRAY);
	pnlStart.setPreferredSize(new Dimension(800,400));
	
//	btnSchedule=new JButton("Spelscheman");
//	btnSchedule.setPreferredSize(new Dimension(300,60));
//	btnSchedule.setBackground(Color.GRAY);
//	btnSchedule.addActionListener(new ScheduleListener());
//	
//	btnBand=new JButton("Band");
//	btnBand.setPreferredSize(new Dimension(300,60));
//	btnBand.setBackground(Color.GRAY);
//	btnBand.addActionListener(new BandListener());		
	
	btnContact=new JButton("Kontaktpersoner");
	btnContact.setPreferredSize(new Dimension(300,60));
	btnContact.setBackground(Color.GRAY);
	
	btnSecurity=new JButton("Säkerhetsansvariga");
	btnSecurity.setPreferredSize(new Dimension(300,60));
	btnSecurity.setBackground(Color.GRAY);
	
	pnlStart.add(btnContact);
	pnlStart.add(btnSecurity);	
	pnlStart.add(btnSchedule);
	pnlStart.add(btnBand);
		
	
	return pnlStart;
}
private class BandListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnBand){
			pnlStart.setBackground(Color.RED);
			//Hämta o visa Band JPanel.
			//Hämta information från databas.
			//Ta bort /Disable förra panelen.
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









public static void main(String [] args){
	JFrame f= new JFrame("Festivalinfo-Personal");
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.add(new GuiVisitor());
	f.setLocation(500, 100);
	f.setSize(800, 600);
	f.setVisible(true);
	f.setResizable(false);
	
	
	
}










}
