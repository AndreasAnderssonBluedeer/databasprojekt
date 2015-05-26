package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;







public class GuiStaff extends JPanel {

	private QuestionController controller = new QuestionController();
	private ArrayList<String> bands = new ArrayList <>();
	private JButton btnSchedule,btnBand,btnContact,btnSecurity,btnHome,
			btnThursday,btnFriday,btnSaturday,btnAddTime,btnRemoveTime,
			btnRemoveBand,btnAddBand,btnAddContact,btnRemoveContact,btnAddBandContact
			,btnAddSecurity,btnRemoveSecurity,btnAddStageSecurity,btnAddMember,btnRemoveMember;
	
	private JPanel pnlStart,pnlMain,pnlBandMenu,pnlContacts,pnlSecurity,
				   pnlSchedule,pnlThursday,pnlFriday,pnlSaturday,pnlBandMembers;
	
	private JLabel lblSchedule;
	
	private String bandname;

	private boolean removeTime,removeBand,removeMember,removeContact,removeSecurity;

	
	public GuiStaff(){	
		try {
			controller.Connection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pnlStart=showStartScreen();
		pnlBandMenu=showBands();
		pnlContacts=showContacts();
		pnlSecurity=showSecurity();
		pnlSchedule=showSchedule();
		pnlThursday=getDaySchedule("Torsdag");
		pnlFriday=getDaySchedule("Fredag");
		pnlSaturday=getDaySchedule("Lördag");
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
	public void clearPanel(JPanel pnl){
		pnl.removeAll();
		repaintMenu(pnl);
	}
	public void repaintMenu(JPanel pnl){
		pnl.repaint();
	}
	public JPanel getDaySchedule(String day){
		JPanel pnlCompleteDay=new JPanel();
		pnlCompleteDay.setBackground(Color.DARK_GRAY);
		
		JPanel pnlDay=new JPanel();
		pnlDay.setBackground(Color.DARK_GRAY);
		pnlDay.setLayout(new GridLayout(0, 1));
		pnlCompleteDay.add(pnlDay);
		
		int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
	    int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER; 
	    JScrollPane jsp=new JScrollPane(pnlDay,v,h);
	    jsp.setPreferredSize(new Dimension(600,350));
	    
	    
	    pnlCompleteDay.add(jsp);
		
		ArrayList<JButton> btnDayList=new ArrayList<>();
		
		if(day.equals("Torsdag")){	//Hämta databas info för specifik dag.
			
			for(int i=0;i<20;i++){	//Kommer sedan bli bandlistans längd*
				btnDayList.add(new JButton("Torsdag StartTid:17.00 SlutTid:18.00 Band: "+i));	//Band+i ==Databas-BandNamn
				btnDayList.get(i).setSize(new Dimension(400,35));
				btnDayList.get(i).setBackground(Color.GRAY);
				btnDayList.get(i).addActionListener(new ScheduleListener());
				pnlDay.add(btnDayList.get(i));
			}
		}if(day.equals("Fredag")){
			
			for(int i=0;i<15;i++){	//Kommer sedan bli bandlistans längd*
				btnDayList.add(new JButton("Fredag StartTid:17.00 SlutTid:18.00 Band: "+i));	//Band+i ==Databas-BandNamn
				btnDayList.get(i).setSize(new Dimension(400,35));
				btnDayList.get(i).setBackground(Color.GRAY);
				btnDayList.get(i).addActionListener(new ScheduleListener());
				pnlDay.add(btnDayList.get(i));
			}
		}if(day.equals("Lördag")){
			
			for(int i=0;i<40;i++){	//Kommer sedan bli bandlistans längd*
				btnDayList.add(new JButton("Lördag StartTid:17.00 SlutTid:18.00 Band: "+i));	//Band+i ==Databas-BandNamn
				btnDayList.get(i).setSize(new Dimension(400,35));
				btnDayList.get(i).setBackground(Color.GRAY);
				btnDayList.get(i).addActionListener(new ScheduleListener());
				pnlDay.add(btnDayList.get(i));
			}
		}
		
		return pnlCompleteDay;
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
public JPanel showSchedule(){
	JPanel pnlSchedule=new JPanel();
	pnlSchedule.setBackground(Color.DARK_GRAY);
	pnlSchedule.setPreferredSize(new Dimension(800,600));
	
	pnlSchedule.add(getHomeBtn());
	
	lblSchedule=new JLabel("Spelscheman:");
	lblSchedule.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));	
	lblSchedule.setHorizontalAlignment(JLabel.RIGHT);
	lblSchedule.setForeground(Color.LIGHT_GRAY);
	lblSchedule.setPreferredSize(new Dimension(450,80));
	
	pnlSchedule.add(lblSchedule);
	
	Dimension dim=new Dimension(200,60);
	
	btnThursday=new JButton("Torsdag");
	btnThursday.setPreferredSize(dim);
	btnThursday.setBackground(Color.GRAY);
	btnThursday.addActionListener(new ScheduleListener());
	pnlSchedule.add(btnThursday);
	
	btnFriday=new JButton("Fredag");
	btnFriday.setPreferredSize(dim);
	btnFriday.setBackground(Color.GRAY);
	btnFriday.addActionListener(new ScheduleListener());
	pnlSchedule.add(btnFriday);
	
	btnSaturday=new JButton("Lördag");
	btnSaturday.setPreferredSize(dim);
	btnSaturday.setBackground(Color.GRAY);
	btnSaturday.addActionListener(new ScheduleListener());
	pnlSchedule.add(btnSaturday);
	
	btnAddTime=new JButton("Lägg till Speltid");
	btnAddTime.setPreferredSize(new Dimension(200,40));
	btnAddTime.setBackground(Color.GRAY);
	btnAddTime.addActionListener(new ScheduleListener());
	pnlSchedule.add(btnAddTime);
	
	btnRemoveTime=new JButton("Ta bort Speltid");
	btnRemoveTime.setPreferredSize(new Dimension(200,40));
	btnRemoveTime.setBackground(Color.GRAY);
	btnRemoveTime.addActionListener(new ScheduleListener());
	pnlSchedule.add(btnRemoveTime);
	
	return pnlSchedule;
}

public JPanel showBands(){
		
		try {
			bands = controller.getBands();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		JPanel pnlBandMenu=new JPanel();
		pnlBandMenu.setBackground(Color.DARK_GRAY);
		pnlBandMenu.setPreferredSize(new Dimension(800,600));
		
		pnlBandMenu.add(getHomeBtn());
		
		JPanel pnlBands=new JPanel();
		pnlBands.setBackground(Color.DARK_GRAY);
		pnlBands.setLayout(new GridLayout(0, 3));
		
		
		JLabel lblTitle=new JLabel("BAND:");
		lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));	
		lblTitle.setHorizontalAlignment(JLabel.RIGHT);
		lblTitle.setForeground(Color.LIGHT_GRAY);
		lblTitle.setPreferredSize(new Dimension(550,80));
		
		pnlBandMenu.add(lblTitle);
		
		btnAddBand=new JButton("Lägg till Band");
		btnAddBand.setPreferredSize(new Dimension(350,40));
		btnAddBand.setBackground(Color.GRAY);
		btnAddBand.addActionListener(new BandListener());
		pnlBandMenu.add(btnAddBand);
		
		btnRemoveBand=new JButton("Ta bort Band");
		btnRemoveBand.setPreferredSize(new Dimension(350,40));
		btnRemoveBand.setBackground(Color.GRAY);
		btnRemoveBand.addActionListener(new BandListener());
		pnlBandMenu.add(btnRemoveBand);
		
		int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
	    int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER; 
	    JScrollPane jsp=new JScrollPane(pnlBands,v,h);
	    jsp.setPreferredSize(new Dimension(700,350));
	    
	    
	    pnlBandMenu.add(jsp);
		
		ArrayList<JButton> btnBandList=new ArrayList<>();
		for(int i=0;i<bands.size();i++){	//Kommer sedan bli bandlistans längd*
			btnBandList.add(new JButton(bands.get(i)));	//Band+i ==Databas-BandNamn
			btnBandList.get(i).setSize(new Dimension(250,30));
			btnBandList.get(i).setBackground(Color.GRAY);
			btnBandList.get(i).addActionListener(new MemberListener());
			pnlBands.add(btnBandList.get(i));
		}
		
		return pnlBandMenu;

	
}
public JPanel showContacts(){
	JPanel pnlContact=new JPanel();
	pnlContact.setBackground(Color.DARK_GRAY);
	pnlContact.setPreferredSize(new Dimension(800,600));
	
	
	
	pnlContact.add(getHomeBtn());
	
	
	JLabel lblTitle=new JLabel("Kontaktpersoner:");
	lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));	
	lblTitle.setHorizontalAlignment(JLabel.RIGHT);
	lblTitle.setForeground(Color.LIGHT_GRAY);
	lblTitle.setPreferredSize(new Dimension(450,80));

	
	pnlContact.add(lblTitle);

	
	Dimension dimBtn=new Dimension(200,40);
	
	btnAddContact=new JButton("Lägg till Kontaktperson");
	btnAddContact.setPreferredSize(dimBtn);
	btnAddContact.setBackground(Color.GRAY);
	btnAddContact.addActionListener(new ContactListener());
	pnlContact.add(btnAddContact);
	
	btnRemoveContact=new JButton("Ta bort Kontaktperson");
	btnRemoveContact.setPreferredSize(dimBtn);
	btnRemoveContact.setBackground(Color.GRAY);
	btnRemoveContact.addActionListener(new ContactListener());
	pnlContact.add(btnRemoveContact);
	
	btnAddBandContact=new JButton("Lägg till Bandkontakt");
	btnAddBandContact.setPreferredSize(dimBtn);
	btnAddBandContact.setBackground(Color.GRAY);
	btnAddBandContact.addActionListener(new ContactListener());
	pnlContact.add(btnAddBandContact);
	
	JPanel pnl=new JPanel();
	pnl.setBackground(Color.DARK_GRAY);
	pnl.setLayout(new GridLayout(0, 1));
	
	int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
    int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER; 
    JScrollPane jsp=new JScrollPane(pnl,v,h);
    jsp.setPreferredSize(new Dimension(600,350));
    
    
    
	
	ArrayList<JButton> btnContactList=new ArrayList<>();
	for(int i=0;i<20;i++){	//Kommer sedan bli Kontaktlistans längd*
		btnContactList.add(new JButton("911014-164"+i+"| FÖRNAMN EFTERNAMN "+i+"|Band "+i));
		btnContactList.get(i).setSize(new Dimension(600,30));
		btnContactList.get(i).setBackground(Color.GRAY);
		btnContactList.get(i).addActionListener(new ContactListener());
		pnl.add(btnContactList.get(i));
	}
	pnlContact.add(jsp);
	
	return pnlContact;
}
public JPanel showSecurity(){

	JPanel pnlSecurity=new JPanel();
	pnlSecurity.setBackground(Color.DARK_GRAY);
	pnlSecurity.setPreferredSize(new Dimension(800,600));
	
	
	
	pnlSecurity.add(getHomeBtn());
	
	
	JLabel lblTitle=new JLabel("Säkerhetsansvariga:");
	lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));	
	lblTitle.setHorizontalAlignment(JLabel.RIGHT);
	lblTitle.setForeground(Color.LIGHT_GRAY);
	lblTitle.setPreferredSize(new Dimension(450,80));

	
	pnlSecurity.add(lblTitle);

	
	Dimension dimBtn=new Dimension(200,40);
	
	btnAddSecurity=new JButton("Lägg till Säkerhetsansvarig");
	btnAddSecurity.setPreferredSize(dimBtn);
	btnAddSecurity.setBackground(Color.GRAY);
	btnAddSecurity.addActionListener(new SecurityListener());
	pnlSecurity.add(btnAddSecurity);
	
	btnRemoveSecurity=new JButton("Ta bort Säkerhetsansvarig");
	btnRemoveSecurity.setPreferredSize(dimBtn);
	btnRemoveSecurity.setBackground(Color.GRAY);
	btnRemoveSecurity.addActionListener(new SecurityListener());
	pnlSecurity.add(btnRemoveSecurity);
	
	btnAddStageSecurity=new JButton("Lägg till Scenansvarig");
	btnAddStageSecurity.setPreferredSize(dimBtn);
	btnAddStageSecurity.setBackground(Color.GRAY);
	btnAddStageSecurity.addActionListener(new SecurityListener());
	pnlSecurity.add(btnAddStageSecurity);
	
	JPanel pnl=new JPanel();
	pnl.setBackground(Color.DARK_GRAY);
	pnl.setLayout(new GridLayout(0, 1));
	
	int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
    int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER; 
    JScrollPane jsp=new JScrollPane(pnl,v,h);
    jsp.setPreferredSize(new Dimension(600,350));
	
	ArrayList<JButton> btnSecurityList=new ArrayList<>();
	for(int i=0;i<20;i++){	//Kommer sedan bli bandlistans längd*
		btnSecurityList.add(new JButton("911014-164"+i+"| FÖRNAMN EFTERNAMN "+i));	//Band+i ==Databas-BandNamn
		btnSecurityList.get(i).setPreferredSize(new Dimension(350,30));
		btnSecurityList.get(i).setBackground(Color.GRAY);
		btnSecurityList.get(i).addActionListener(new SecurityListener());
		pnl.add(btnSecurityList.get(i));
	}
	pnlSecurity.add(jsp);
	
	return pnlSecurity;
}
public JPanel showBandMembers(String name){
	this.bandname=name;
	pnlBandMembers=new JPanel();
	pnlBandMembers.setBackground(Color.DARK_GRAY);
	pnlBandMembers.setPreferredSize(new Dimension(800,600));
	
	pnlBandMembers.add(getHomeBtn());
	btnAddMember=new JButton("Lägg till Bandmedlem");
	btnAddMember.setPreferredSize(new Dimension(200,20));
	btnAddMember.setBackground(Color.GRAY);
	btnAddMember.addActionListener(new UpdateMemberListener());
	pnlBandMembers.add(btnAddMember);
	
	btnRemoveMember=new JButton("Ta bort Bandmedlem");
	btnRemoveMember.setPreferredSize(new Dimension(200,20));
	btnRemoveMember.setBackground(Color.GRAY);
	btnRemoveMember.addActionListener(new UpdateMemberListener());
	pnlBandMembers.add(btnRemoveMember);
	
	JLabel lblTitle=new JLabel(bandname+": ");
	lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));	
	lblTitle.setHorizontalAlignment(JLabel.CENTER);
	lblTitle.setForeground(Color.LIGHT_GRAY);
	lblTitle.setPreferredSize(new Dimension(700,30));
	
	pnlBandMembers.add(lblTitle);
	
	
	
	JLabel lblCountry=new JLabel("Land: Sverige");
	lblCountry.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));	
	lblCountry.setForeground(Color.LIGHT_GRAY);
	lblCountry.setHorizontalAlignment(JLabel.CENTER);
	lblCountry.setPreferredSize(new Dimension(380,30));
	
	pnlBandMembers.add(lblCountry);
	
	JLabel lblGenre=new JLabel("Genre: Rock");
	lblGenre.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));	
	lblGenre.setHorizontalAlignment(JLabel.CENTER);
	lblGenre.setForeground(Color.LIGHT_GRAY);
	lblGenre.setPreferredSize(new Dimension(380,30));
	
	pnlBandMembers.add(lblGenre);
	
	
	
	ArrayList<JButton> btnMemberList=new ArrayList<>();
	for(int i=0;i<10;i++){	//Kommer sedan bli bandlistans längd*
		btnMemberList.add(new JButton("BandMedlem "+i));	//Band+i ==Databas-BandNamn
		btnMemberList.get(i).setPreferredSize(new Dimension(500,30));
		btnMemberList.get(i).setBackground(Color.GRAY);
		btnMemberList.get(i).addActionListener(new UpdateMemberListener());
		
		pnlBandMembers.add(btnMemberList.get(i));
	}
	
	return pnlBandMembers;
}

public void clearSchedule(){
	pnlSchedule.remove(pnlFriday);
	pnlSchedule.remove(pnlThursday);
	pnlSchedule.remove(pnlSaturday);
}
	
	

private class BandListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnBand){		
			clearPanel(pnlMain);
		
			pnlMain.add(pnlBandMenu);
			pnlMain.revalidate();
			
			//Hämta information från databas.		
		}
		if(e.getSource()==btnAddBand){
			JOptionPane.showMessageDialog(null,"Band Tillagt.");
		}
		if(e.getSource()==btnRemoveBand){
			removeBand=true;
			JOptionPane.showMessageDialog(null,"Tryck på det band som ska tas bort.");
		}
		
	}
	
}

private class ScheduleListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if(removeTime){
			removeTime=false;
			JOptionPane.showMessageDialog(null,"Speltid Raderad.");
		}
		if(e.getSource()==btnSchedule){
			clearPanel(pnlMain);
			
			pnlMain.add(pnlSchedule);
			pnlMain.revalidate();
		
			
			}
			if(e.getSource()==btnThursday){
				clearPanel(pnlMain);
				clearSchedule();
				
				pnlMain.add(pnlSchedule);
				lblSchedule.setText("Spelschema Torsdag:");
				pnlSchedule.add(pnlThursday);
				
				pnlMain.revalidate();
			
			}
			if(e.getSource()==btnFriday){

				clearPanel(pnlMain);
				clearSchedule();
				pnlMain.add(pnlSchedule);
				lblSchedule.setText("Spelschema Fredag:");
				pnlSchedule.add(pnlFriday);
				
				pnlMain.revalidate();
			
			}
			if(e.getSource()==btnSaturday){
				
				clearPanel(pnlMain);				
				clearSchedule();
				
				pnlMain.add(pnlSchedule);
				lblSchedule.setText("Spelschema Lördag:");
				pnlSchedule.add(pnlSaturday);
				
				pnlMain.revalidate();
			
			}
			if(e.getSource()==btnAddTime){
				//KALLA PÅ QUESTIONCONTROLLER, visa i JOP?
				JOptionPane.showMessageDialog(null, "Speltid Tillagd.");
			}
			if(e.getSource()==btnRemoveTime){
				//KALLA PÅ QUESTIONCONTROLLER, visa i JOP?
				removeTime=true;
				JOptionPane.showMessageDialog(null, "Tryck på den Speltid som ska raderas.");
			}
			
		}
		
	}

private class ContactListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if(removeContact){
			removeContact=false;	//Uppdatera listor o databas
			JOptionPane.showMessageDialog(null, "Kontaktperson raderad.");
		}
		if(e.getSource()==btnContact){
			clearPanel(pnlMain);
			
			pnlMain.add(pnlContacts);
			pnlMain.revalidate();
			
			//Hämta information från databas.
			//Ta bort /Disable förra panelen.
		}
		if(e.getSource()==btnAddContact){
			JOptionPane.showMessageDialog(null, "Kontaktperson Tillagd.");
		}
		if(e.getSource()==btnRemoveContact){
			removeContact=true;
			JOptionPane.showMessageDialog(null, "Tryck på den Kontaktperson som ska bort.");
		}
		if(e.getSource()==btnAddBandContact){
			JOptionPane.showMessageDialog(null, "Bandkontakt tillagd.");
		}
		
	}
	
}
private class SecurityListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if(removeSecurity){
			removeSecurity=false;	//Uppdatera listor o databas
			JOptionPane.showMessageDialog(null, "Säkerhetsansvarig raderad.");
		}
		if(e.getSource()==btnSecurity){
			clearPanel(pnlMain);
			
			pnlMain.add(pnlSecurity);
			pnlMain.revalidate();
			
			//Hämta information från databas.
			//Ta bort /Disable förra panelen.
		}
		if(e.getSource()==btnAddSecurity){
			JOptionPane.showMessageDialog(null, "Säkerhetsansvarig Tillagd.");
		}
		if(e.getSource()==btnRemoveSecurity){
			removeSecurity=true;
			JOptionPane.showMessageDialog(null, "Tryck på den säkerhetsanvarig som ska bort.");
		}
		if(e.getSource()==btnAddStageSecurity){
			JOptionPane.showMessageDialog(null, "Scenansvarig Tillagd.");
		}
		
	}
	
}
private class HomeListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
			clearPanel(pnlMain);
			pnlMain.add(pnlStart);
			pnlMain.revalidate();
			
			//Hämta information från databas.
			//Ta bort /Disable förra panelen.
		
		
	}
	
}
private class MemberListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if(removeBand){
			removeBand=false;
			//Tillkalla radera från databas, uppdatera panel och bandlista.
			JOptionPane.showMessageDialog(null, "Band raderat.");
		}else{
			clearPanel(pnlMain);	
			pnlMain.add(showBandMembers("Valt Band"));
			pnlMain.revalidate();
			
			//Hämta information från databas.
			//Ta bort /Disable förra panelen.
		
		}
	}
	
}
private class UpdateMemberListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if(removeMember){
			//Ta bort knapp
			//Uppdatera lista
			removeMember=false;
			JOptionPane.showMessageDialog(null, "Medlem raderad.");
		}
		if(e.getSource()==btnAddMember){
			JOptionPane.showMessageDialog(null,"Medlem Tillagd.");
			
			//Hämta information från databas.
			
		
		}
		if(e.getSource()==btnRemoveMember){
			removeMember=true;
			JOptionPane.showMessageDialog(null,"Tryck på den medlem som ska tas bort");
			
			//Hämta information från databas.
			
		
		}
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
