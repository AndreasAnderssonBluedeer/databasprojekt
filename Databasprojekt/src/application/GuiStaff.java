package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class GuiStaff extends JPanel {

	
	private JButton btnSchedule,btnBand,btnContact,btnSecurity,btnHome, btnAdd, btnRemove;
	private JPanel pnlStart,pnlMain,pnlBands,pnlContacts,pnlSecurity;
	
	public GuiStaff(){	
	
		
		
		pnlStart=showStartScreen();
		pnlBands=showBands();
		pnlContacts=showContacts();
		pnlSecurity=showSecurity();
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
	
	btnContact=new JButton("Kontaktpersoner");
	btnContact.setPreferredSize(new Dimension(300,60));
	btnContact.setBackground(Color.GRAY);
	btnContact.addActionListener(new ContactListener());
	
	btnSecurity=new JButton("Säkerhetsansvariga");
	btnSecurity.setPreferredSize(new Dimension(300,60));
	btnSecurity.setBackground(Color.GRAY);
	btnSecurity.addActionListener(new SecurityListener());
	
	
	
	pnlStart.add(emptyStart);
	pnlStart.add(btnContact);
	pnlStart.add(btnSecurity);	
	pnlStart.add(btnSchedule);
	pnlStart.add(btnBand);
		
	
	return pnlStart;
}

public JPanel showBands(){
	JPanel pnlBand=new JPanel();
	pnlBand.setBackground(Color.DARK_GRAY);
	pnlBand.setPreferredSize(new Dimension(800,600));
	
	btnAdd = new JButton("Add");
	btnRemove = new JButton("Remove");
	btnAdd.addActionListener(new addBandListener());
	btnRemove.addActionListener(new removeBandListener());
	
	pnlBand.add(getHomeBtn());
	pnlBand.add(btnAdd);
	pnlBand.add(btnRemove);
	
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
public JPanel showContacts(){
	JPanel pnlContact=new JPanel();
	pnlContact.setBackground(Color.DARK_GRAY);
	pnlContact.setPreferredSize(new Dimension(800,600));
	
	btnAdd = new JButton("Add");
	btnRemove = new JButton("Remove");
	btnAdd.addActionListener(new addContactListener());
	btnRemove.addActionListener(new removeContactListener());
	
	pnlContact.add(getHomeBtn());
	pnlContact.add(btnAdd);
	pnlContact.add(btnRemove);
	
	JLabel lblTitle=new JLabel("Kontaktpersoner:");
	lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));	
	lblTitle.setHorizontalAlignment(JLabel.RIGHT);
	lblTitle.setForeground(Color.LIGHT_GRAY);
	lblTitle.setPreferredSize(new Dimension(450,80));

	
	pnlContact.add(lblTitle);

	
	ArrayList<JButton> btnContactList=new ArrayList<>();
	for(int i=0;i<20;i++){	//Kommer sedan bli bandlistans längd*
		btnContactList.add(new JButton("911014-164"+i+"| FÖRNAMN EFTERNAMN "+i+"|Band "+i));	//Band+i ==Databas-BandNamn
		btnContactList.get(i).setPreferredSize(new Dimension(600,20));
		btnContactList.get(i).setBackground(Color.GRAY);
		pnlContact.add(btnContactList.get(i));
	}
	
	return pnlContact;
}
public JPanel showSecurity(){
	JPanel pnlSecurity=new JPanel();
	pnlSecurity.setBackground(Color.DARK_GRAY);
	pnlSecurity.setPreferredSize(new Dimension(800,600));
	
	btnAdd = new JButton("Add");
	btnRemove = new JButton("Remove");
	btnAdd.addActionListener(new addSecurityListener());
	btnRemove.addActionListener(new removeSecurityListener());
	
	pnlSecurity.add(getHomeBtn());
	pnlSecurity.add(btnAdd);
	pnlSecurity.add(btnRemove);
	
	JLabel lblTitle=new JLabel("Säkerhetsansvariga:");
	lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));	
	lblTitle.setHorizontalAlignment(JLabel.RIGHT);
	lblTitle.setForeground(Color.LIGHT_GRAY);
	lblTitle.setPreferredSize(new Dimension(550,80));

	
	pnlSecurity.add(lblTitle);

	
	ArrayList<JButton> btnSecurityList=new ArrayList<>();
	for(int i=0;i<20;i++){	//Kommer sedan bli bandlistans längd*
		btnSecurityList.add(new JButton("911014-164"+i+"| FÖRNAMN EFTERNAMN "+i));	//Band+i ==Databas-BandNamn
		btnSecurityList.get(i).setPreferredSize(new Dimension(350,20));
		btnSecurityList.get(i).setBackground(Color.GRAY);
		pnlSecurity.add(btnSecurityList.get(i));
	}
	
	return pnlSecurity;
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
private class ContactListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnContact){
			clearPanel();
			
			pnlMain.add(pnlContacts);
			pnlMain.revalidate();
			repaintMenu();
			//Hämta information från databas.
			//Ta bort /Disable förra panelen.
		}
		
	}
	
}
private class SecurityListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnSecurity){
			clearPanel();
			
			pnlMain.add(pnlSecurity);
			pnlMain.revalidate();
			repaintMenu();
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

private class addContactListener implements ActionListener{

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

private class removeContactListener implements ActionListener{

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

private class addBandListener implements ActionListener{

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

private class removeBandListener implements ActionListener{

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

private class addSecurityListener implements ActionListener{

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

private class removeSecurityListener implements ActionListener{

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
	JFrame f= new JFrame("Festivalinfo-Personal");
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.add(new GuiStaff());
	f.setLocation(500, 100);
	f.setSize(800, 600);
	f.setVisible(true);
	f.setResizable(false);
	
	
	
}










}
