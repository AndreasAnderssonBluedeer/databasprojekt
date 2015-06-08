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
	private ArrayList<JButton> btnBandList,btnMemberList,btnSecurityList,btnContactList,btnDayList,btnStageList;
	private GetInfo info;
	private Remove remove;
	private Add add;
	
	private ArrayList<String> bands = new ArrayList <>();
	private ArrayList<String> contacts = new ArrayList <>();
	private ArrayList<String> stages = new ArrayList <>();
	private ArrayList<String> security = new ArrayList <>();
	private ArrayList<String> schedule = new ArrayList <>();
	private ArrayList<String> members = new ArrayList <>();
	
	private JButton btnSchedule,btnBand,btnContact,btnSecurity,btnHome,
			btnThursday,btnFriday,btnSaturday,btnAddTime,btnRemoveTime,
			btnRemoveBand,btnAddBand,btnAddContact,btnRemoveContact,btnAddBandContact
			,btnAddSecurity,btnRemoveSecurity,btnAddStageSecurity,btnAddMember,btnRemoveMember,
			btnUpdateBandContact,btnStage,btnAddStage,btnRemoveStage;
	
	private JPanel pnlStart,pnlMain,pnlBandMenu,pnlContacts,pnlSecurity,
				   pnlSchedule,pnlThursday,pnlFriday,pnlSaturday,pnlBandMembers,pnlStages;
	
	private JLabel lblSchedule;
	
	private String bandname,strBand,strMember,strContact,strSecurity,strTime,strDay,strBandID,strStage;

	private boolean removeTime,removeBand,removeMember,removeContact,removeSecurity,removeStage;

	
	public GuiStaff(String connection){	
		
		info = new GetInfo(connection);
		remove=new Remove(connection);
		add=new Add(connection);
		
		pnlStart=showStartScreen();
		pnlBandMenu=showBands();
		pnlContacts=showContacts();
		pnlSecurity=showSecurity();
		pnlSchedule=showSchedule();
		pnlThursday=getDaySchedule("Torsdag");
		pnlFriday=getDaySchedule("Fredag");
		pnlSaturday=getDaySchedule("Lördag");
		pnlStages=showStages();
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
		
		schedule=info.getSchedule(day);
				
		JPanel pnlDay=new JPanel();
		pnlDay.setBackground(Color.DARK_GRAY);
		pnlDay.setLayout(new GridLayout(0, 1));
		pnlCompleteDay.add(pnlDay);
		
		int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
	    int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER; 
	    JScrollPane jsp=new JScrollPane(pnlDay,v,h);
	    jsp.setPreferredSize(new Dimension(700,350));
	    
	    
	    pnlCompleteDay.add(jsp);
		
		btnDayList=new ArrayList<>();
		
		
			
			for(int i=0;i<schedule.size();i++){	
				btnDayList.add(new JButton(schedule.get(i)));
				btnDayList.get(i).setPreferredSize(new Dimension(600,35));
				btnDayList.get(i).setBackground(Color.GRAY);
				btnDayList.get(i).addActionListener(new ScheduleListener());
				pnlDay.add(btnDayList.get(i));
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
	
	btnStage=new JButton("Scener");
	btnStage.setPreferredSize(new Dimension(300,60));
	btnStage.setBackground(Color.GRAY);
	btnStage.addActionListener(new StageListener());
	
	
	
	pnlStart.add(emptyStart);
	pnlStart.add(btnContact);
	pnlStart.add(btnSecurity);	
	pnlStart.add(btnSchedule);
	pnlStart.add(btnBand);
	pnlStart.add(btnStage);
		
	
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
			bands = info.getBands();
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
		
		btnBandList=new ArrayList<>();
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
	
	contacts=info.getContacts();
	
	pnlContact.add(getHomeBtn());
	
	
	JLabel lblTitle=new JLabel("Kontaktpersoner:");
	lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));	
	lblTitle.setHorizontalAlignment(JLabel.RIGHT);
	lblTitle.setForeground(Color.LIGHT_GRAY);
	lblTitle.setPreferredSize(new Dimension(450,80));

	
	pnlContact.add(lblTitle);

	
	Dimension dimBtn=new Dimension(300,40);
	
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
	
	JPanel pnl=new JPanel();
	pnl.setBackground(Color.DARK_GRAY);
	pnl.setLayout(new GridLayout(0, 1));
	
	int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
    int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER; 
    JScrollPane jsp=new JScrollPane(pnl,v,h);
    jsp.setPreferredSize(new Dimension(600,350));
    
    
    
	
	btnContactList=new ArrayList<>();
	for(int i=0;i<contacts.size();i++){	//Kommer sedan bli Kontaktlistans längd*
		btnContactList.add(new JButton(contacts.get(i)));
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
	
	security=info.getSecurity();
	
	
	pnlSecurity.add(getHomeBtn());
	
	
	JLabel lblTitle=new JLabel("Säkerhetsansvariga:");
	lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));	
	lblTitle.setHorizontalAlignment(JLabel.RIGHT);
	lblTitle.setForeground(Color.LIGHT_GRAY);
	lblTitle.setPreferredSize(new Dimension(450,80));

	
	pnlSecurity.add(lblTitle);

	
	Dimension dimBtn=new Dimension(300,40);
	
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
	
	JPanel pnl=new JPanel();
	pnl.setBackground(Color.DARK_GRAY);
	pnl.setLayout(new GridLayout(0, 1));
	
	int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
    int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER; 
    JScrollPane jsp=new JScrollPane(pnl,v,h);
    jsp.setPreferredSize(new Dimension(600,350));
	
	btnSecurityList=new ArrayList<>();
	for(int i=0;i<security.size();i++){	//Kommer sedan bli bandlistans längd*
		btnSecurityList.add(new JButton(security.get(i)));	//Band+i ==Databas-BandNamn
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
	
	members=info.getMembers(name);
	
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
	
	btnUpdateBandContact=new JButton("Ändra Bandkontakt");
	btnUpdateBandContact.setPreferredSize(new Dimension(200,20));
	btnUpdateBandContact.setBackground(Color.GRAY);
	btnUpdateBandContact.addActionListener(new UpdateMemberListener());
	pnlBandMembers.add(btnUpdateBandContact);
	
	JLabel lblTitle=new JLabel(info.getBandInfo(strBand));
	lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));	
	lblTitle.setHorizontalAlignment(JLabel.CENTER);
	lblTitle.setForeground(Color.LIGHT_GRAY);
	lblTitle.setPreferredSize(new Dimension(800,50));
	
	pnlBandMembers.add(lblTitle);
	
	btnMemberList=new ArrayList<>();
	for(int i=0;i<members.size();i++){	//Kommer sedan bli bandlistans längd*
		btnMemberList.add(new JButton(members.get(i)));	//Band+i ==Databas-BandNamn
		btnMemberList.get(i).setPreferredSize(new Dimension(700,60));
		btnMemberList.get(i).setBackground(Color.GRAY);
		btnMemberList.get(i).setHorizontalAlignment(JLabel.LEFT);
		btnMemberList.get(i).addActionListener(new UpdateMemberListener());
		
		pnlBandMembers.add(btnMemberList.get(i));
	}
	
	return pnlBandMembers;
}

public JPanel showStages(){
	JPanel pnlStage=new JPanel();
	pnlStage.setBackground(Color.DARK_GRAY);
	pnlStage.setPreferredSize(new Dimension(800,600));
	
	stages=info.getStageList();
	
	pnlStage.add(getHomeBtn());
	
	
	JLabel lblTitle=new JLabel("Scener:");
	lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));	
	lblTitle.setHorizontalAlignment(JLabel.RIGHT);
	lblTitle.setForeground(Color.LIGHT_GRAY);
	lblTitle.setPreferredSize(new Dimension(450,80));

	
	pnlStage.add(lblTitle);

	
	Dimension dimBtn=new Dimension(300,40);
	
	btnAddStage=new JButton("Lägg till Scen");
	btnAddStage.setPreferredSize(dimBtn);
	btnAddStage.setBackground(Color.GRAY);
	btnAddStage.addActionListener(new StageListener());
	pnlStage.add(btnAddStage);
	
	btnRemoveStage=new JButton("Ta bort Scen");
	btnRemoveStage.setPreferredSize(dimBtn);
	btnRemoveStage.setBackground(Color.GRAY);
	btnRemoveStage.addActionListener(new StageListener());
	pnlStage.add(btnRemoveStage);
	
	JPanel pnl=new JPanel();
	pnl.setBackground(Color.DARK_GRAY);
	pnl.setLayout(new GridLayout(0, 1));
	
	int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
    int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER; 
    JScrollPane jsp=new JScrollPane(pnl,v,h);
    jsp.setPreferredSize(new Dimension(600,350));
	
	btnStageList=new ArrayList<>();
	for(int i=0;i<stages.size();i++){	//Kommer sedan bli Kontaktlistans längd*
		btnStageList.add(new JButton(stages.get(i)));
		btnStageList.get(i).setSize(new Dimension(600,30));
		btnStageList.get(i).setBackground(Color.GRAY);
		btnStageList.get(i).addActionListener(new StageListener());
		pnl.add(btnStageList.get(i));
	}
	pnlStage.add(jsp);
	
	return pnlStage;
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
			add.addBand();
			clearPanel(pnlMain);
			pnlBandMenu=showBands();
			pnlMain.add(pnlBandMenu);
			pnlMain.revalidate();
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
		strTime="Vald Speltid";
		for(int i=0;i<btnDayList.size();i++){
			if(btnDayList.get(i)==e.getSource()){
				strTime=btnDayList.get(i).getText();			
			}
			
		}
		if(removeTime){
			removeTime=false;
			
			String[] parts = strTime.split(":");
			if(parts.length>2){
		
			String stage = parts[0];	
			String start= parts[1];
			
			start.split(" ");
			parts=start.split(" ");
			start=parts[1];
			System.out.println(start);
			remove.removeAct(start);	
			clearPanel(pnlMain);				
			clearSchedule();
			pnlThursday=getDaySchedule("Torsdag");
			pnlFriday=getDaySchedule("Fredag");
			pnlSaturday=getDaySchedule("Lördag");
			pnlSchedule=showSchedule();
			btnSchedule.doClick();
			}
		}
		if(e.getSource()==btnSchedule){
			clearPanel(pnlMain);
			
			pnlMain.add(pnlSchedule);
			pnlMain.revalidate();
		
			
			}
			if(e.getSource()==btnThursday){
				strDay="Torsdag";
				clearPanel(pnlMain);
				clearSchedule();
				pnlThursday=getDaySchedule("Torsdag");
				pnlMain.add(pnlSchedule);
				lblSchedule.setText("Spelschema Torsdag:");
				pnlSchedule.add(pnlThursday);
				
				pnlMain.revalidate();
			
			}
			if(e.getSource()==btnFriday){
				strDay="Fredag";
				clearPanel(pnlMain);
				clearSchedule();
				pnlFriday=getDaySchedule("Fredag");
				pnlMain.add(pnlSchedule);
				lblSchedule.setText("Spelschema Fredag:");
				pnlSchedule.add(pnlFriday);
				
				pnlMain.revalidate();
			
			}
			if(e.getSource()==btnSaturday){
				strDay="Lördag";
				clearPanel(pnlMain);				
				clearSchedule();
				pnlSaturday=getDaySchedule("Lördag");
				pnlMain.add(pnlSchedule);
				lblSchedule.setText("Spelschema Lördag:");
				pnlSchedule.add(pnlSaturday);
				
				pnlMain.revalidate();
			
			}
			if(e.getSource()==btnAddTime){
				add.addAct();
				clearPanel(pnlMain);				
				clearSchedule();
				pnlThursday=getDaySchedule("Torsdag");
				pnlFriday=getDaySchedule("Fredag");
				pnlSaturday=getDaySchedule("Lördag");
				pnlSchedule=showSchedule();
				btnSchedule.doClick();
			}
			if(e.getSource()==btnRemoveTime){
				
				removeTime=true;
				JOptionPane.showMessageDialog(null, "Tryck på den Speltid som ska raderas.");
			}
			
		}
		
	}

private class ContactListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		strContact="Vald Kontakt";
		for(int i=0;i<btnContactList.size();i++){
			if(btnContactList.get(i)==e.getSource()){
				strContact=btnContactList.get(i).getText();
			}
		}
		if(removeContact){
			removeContact=false;	//Uppdatera listor o databas
			
			String[] parts = strContact.split(",");
			String strContact = parts[0];		
			remove.removeContact(strContact);	
			pnlContacts=showContacts();
			btnContact.doClick();
		}
		if(e.getSource()==btnContact){
			clearPanel(pnlMain);
			
			pnlMain.add(pnlContacts);
			pnlMain.revalidate();
			
			//Hämta information från databas.
			//Ta bort /Disable förra panelen.
		}
		if(e.getSource()==btnAddContact){
			add.addContact();
			pnlContacts=showContacts();
			btnContact.doClick();
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
		strSecurity="Vald Kontakt";
		for(int i=0;i<btnSecurityList.size();i++){
			if(btnSecurityList.get(i)==e.getSource()){
				strSecurity=btnSecurityList.get(i).getText();
			}
		}
		if(removeSecurity){
			removeSecurity=false;	//Uppdatera listor o databas
			String[] parts = strSecurity.split(",");
			strSecurity = parts[0];		
			remove.removeSecurity(strSecurity);	
			pnlSecurity=showSecurity();
			btnSecurity.doClick();
			
		}
		if(e.getSource()==btnSecurity){
			clearPanel(pnlMain);
			
			pnlMain.add(pnlSecurity);
			pnlMain.revalidate();
			
			//Hämta information från databas.
			//Ta bort /Disable förra panelen.
		}
		if(e.getSource()==btnAddSecurity){
			add.addSecurity();
			pnlSecurity=showSecurity();
			btnSecurity.doClick();
			
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
		strBand="Valt Band";
		
		for(int i=0;i<btnBandList.size();i++){
			if(btnBandList.get(i)==e.getSource()){
				strBand=btnBandList.get(i).getText();
			}
		}
		if(removeBand){
			removeBand=false;	
			remove.removeBand(strBand);
			clearPanel(pnlMain);
			pnlBandMenu=showBands();
			pnlMain.add(pnlBandMenu);
			pnlMain.revalidate();
		}else{
			clearPanel(pnlMain);	
			pnlMain.add(showBandMembers(strBand));
			pnlMain.revalidate();
			
			//Hämta information från databas.
			//Ta bort /Disable förra panelen.
		
		}
	}
	
}
private class UpdateMemberListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		strMember="Vald Medlem";
		for(int i=0;i<btnMemberList.size();i++){
			if(btnMemberList.get(i)==e.getSource()){
				strMember=btnMemberList.get(i).getText();
			}
		}
		if(removeMember){
			
			if(strMember!=null){
			removeMember=false;
			String [] parts=strMember.split(":");
			strMember=parts[1];
			parts=strMember.split(" ");
			strMember=parts[1];
			
			remove.removeMember(strMember);
			
			clearPanel(pnlMain);	
			pnlMain.add(showBandMembers(strBand));
			pnlMain.revalidate();
		}
		}
		if(e.getSource()==btnAddMember){
			add.addMember(strBand);
			clearPanel(pnlMain);	
			pnlMain.add(showBandMembers(strBand));
			pnlMain.revalidate();
		}
		if(e.getSource()==btnUpdateBandContact){
			add.changeBandContact(strBand);
			clearPanel(pnlMain);	
			pnlMain.add(showBandMembers(strBand));
			pnlMain.revalidate();
		}
		if(e.getSource()==btnRemoveMember){
			removeMember=true;
			JOptionPane.showMessageDialog(null,"Tryck på den medlem som ska tas bort");
			
			//Hämta information från databas.
			
		
		}
	}
	
}

private class StageListener implements ActionListener{


@Override
public void actionPerformed(ActionEvent e) {
	strStage="Vald Scen";
	for(int i=0;i<btnStageList.size();i++){
		if(btnStageList.get(i)==e.getSource()){
			strStage=btnStageList.get(i).getText();
		}
	}
	if(removeStage){
		removeStage=false;	//Uppdatera listor o databas
		
		String[] parts = strStage.split(",");
		String strStage = parts[0];		
		remove.removeStage(strStage);	
		pnlStages=showStages();
		btnStage.doClick();
	}
	if(e.getSource()==btnStage){
		clearPanel(pnlMain);
		
		pnlMain.add(pnlStages);
		pnlMain.revalidate();
	}
	if(e.getSource()==btnAddStage){
		add.addStage();
		pnlStages=showStages();
		btnStage.doClick();
	}
	if(e.getSource()==btnRemoveStage){
		removeStage=true;
		JOptionPane.showMessageDialog(null, "Tryck på den Scen som ska bort.");
	}
	
}
}
public static void main(String [] args){
	JFrame frame = new JFrame("Välj anslutning.");
	String [] choice=new String [2];
	choice[0]="195.178.232.7:4040";
	choice[1]="195.178.232.16:3306";
	String ip= (String) JOptionPane.showInputDialog(frame, 
	        "Välj anslutning, :4040 för nätverk utanför MAH.",
	        "Välj extern/intern anslutning.",
	        JOptionPane.QUESTION_MESSAGE, 
	        null, 
	        choice, 
	        choice[0]);
	
	JFrame f= new JFrame("Festivalinfo-Personal");
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.add(new GuiStaff(ip));
	f.setLocation(300, 100);
	f.setSize(800, 600);
	f.setVisible(true);
	f.setResizable(false);
	
}	
	











}
