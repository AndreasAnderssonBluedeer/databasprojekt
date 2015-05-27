package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;






public class GuiVisitor extends JPanel {
	
	private ArrayList<JButton> btnBandList,btnMemberList,btnSecurityList,btnContactList,btnDayList;
	private GetInfo info = new GetInfo();
	
	private ArrayList<String> bands = new ArrayList <>();
	private ArrayList<String> schedule = new ArrayList <>();
	private ArrayList<String> members = new ArrayList <>();
	
	private JButton btnSchedule,btnBand,btnThursday,btnFriday,btnSaturday;
	private JPanel pnlStart,pnlMain,pnlBands,pnlSchedule,pnlThursday,pnlFriday,pnlSaturday,pnlBandMembers;
	private JLabel lblSchedule;
	private String bandname="Bandnamn:";
	private String strBand,strMember,strTime,strDay;
	
	public GuiVisitor(){

		pnlStart=showStartScreen();
		pnlBands=showBands();
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
		JPanel pnlBandMenu=new JPanel();
		pnlBandMenu.setBackground(Color.DARK_GRAY);
		pnlBandMenu.setPreferredSize(new Dimension(800,600));
		try {
			bands=info.getBands();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		
		return pnlSchedule;
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
	    jsp.setPreferredSize(new Dimension(600,350));
	    
	    
	    pnlCompleteDay.add(jsp);
		
		btnDayList=new ArrayList<>();
		
		
			
			for(int i=0;i<schedule.size();i++){	//Kommer sedan bli bandlistans längd*
				btnDayList.add(new JButton(schedule.get(i).substring(0, schedule.get(i).length() - 5)));	//Band+i ==Databas-BandNamn
				btnDayList.get(i).setPreferredSize(new Dimension(400,35));
				btnDayList.get(i).setBackground(Color.GRAY);
				btnDayList.get(i).addActionListener(new ScheduleListener());
				pnlDay.add(btnDayList.get(i));
			}
		
		return pnlCompleteDay;
	}
	public JPanel showBandMembers(String name){
		this.bandname=name;
		pnlBandMembers=new JPanel();
		pnlBandMembers.setBackground(Color.DARK_GRAY);
		pnlBandMembers.setPreferredSize(new Dimension(800,600));
		
		pnlBandMembers.add(getHomeBtn());
		
		String [] parts=info.getBandInfo(bandname).split("Kontakt");
		JLabel lblTitle=new JLabel(parts[0]);
		lblTitle.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));	
		lblTitle.setHorizontalAlignment(JLabel.CENTER);
		lblTitle.setForeground(Color.LIGHT_GRAY);
		lblTitle.setPreferredSize(new Dimension(800,40));
		
		pnlBandMembers.add(lblTitle);
		
		
		members=info.getMembers(bandname);
		
		btnMemberList=new ArrayList<>();
		for(int i=0;i<members.size();i++){	//Kommer sedan bli bandlistans längd*
			btnMemberList.add(new JButton(members.get(i)));	//Band+i ==Databas-BandNamn
			btnMemberList.get(i).setPreferredSize(new Dimension(700,60));
			btnMemberList.get(i).setBackground(Color.GRAY);
			btnMemberList.get(i).setHorizontalAlignment(JLabel.LEFT);
			
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
			
				pnlMain.add(pnlBands);
				pnlMain.revalidate();
				repaintMenu(pnlMain);
				//Hämta information från databas.		
			}
			
		}
		
	}
	private class ScheduleListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
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
				
			}
			
		}
		
	
	private class HomeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
				clearPanel(pnlMain);	
				pnlMain.add(pnlStart);
				pnlMain.revalidate();
				repaintMenu(pnlMain);
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
				clearPanel(pnlMain);	
				pnlMain.add(showBandMembers(strBand));
				pnlMain.revalidate();
				
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
