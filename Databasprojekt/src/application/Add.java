package application;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

public class Add {
	private Connection con;
	private GetInfo info;
	private String connection;
	public Add(String connection){
		this.connection=connection;
		info=new GetInfo(connection);
		try {
			connection();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Kunde inte ansluta till Databasen.");
			e.printStackTrace();
		}
	}
	public void connection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("driver true");
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(
					"jdbc:mysql://"+connection+"/ae6961",
					"AE6961", "Ibanez2011");
			if (!con.isClosed()) {
				System.out.println("Successfully connected to "
						+ "MySQL server using TCP/IP...");
			}
			PreparedStatement statement = (PreparedStatement) con
					.prepareStatement("select BandNamn from band");

			ResultSet result = statement.executeQuery();

			result.next();
			System.out.println(result.getString(1));
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Exception: " + e.getMessage());
		} 
			
		
	}
	
	
	public void addBand(){	
		ArrayList<String> arr=info.getContacts();
		String [] str= new String[arr.size()];
		for(int i=0;i<str.length;i++){
			str[i]=arr.get(i);
		}
		PreparedStatement statement1;
		String bandname,country,genre,contactID;
		bandname=JOptionPane.showInputDialog("Skriv in Bandets namn.");
		country=JOptionPane.showInputDialog("Skriv in Bandets Land");
		genre=JOptionPane.showInputDialog("Skriv in Bandets Genre.");
		JFrame frame = new JFrame("Välj en Kontakt.");
		contactID= (String) JOptionPane.showInputDialog(frame, 
		        "Välj Bandets kontaktperson",
		        "Välj en Kontakt.",
		        JOptionPane.QUESTION_MESSAGE, 
		        null, 
		        str, 
		        str[0]);
		try {
			String [] parts=contactID.split(",");
			contactID=parts[0];
			statement1 = (PreparedStatement) con
					.prepareStatement("INSERT INTO band(Bandnamn,Land,Genre,Kontakt)"
							+ "Values ('"+bandname+"','"+country+"','"+genre+"','"+contactID+"')");
			statement1.execute();
			
			//******TEST*** SKA TAS BORT SEDAN
			PreparedStatement statement4 = (PreparedStatement) con
					.prepareStatement("select * from band");

			ResultSet result4 = statement4.executeQuery();
			while(result4.next()){		
			System.out.println(result4.getString(1)+" "+result4.getString(2));			
			}
			JOptionPane.showMessageDialog(null, bandname+" Tillagt.");
			} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Hoppsan,något gick snett.Finns bandet redan? Testa igen!");
			e.printStackTrace();
		}
		
		
	}
	public void addContact(){
		PreparedStatement statement1;
		String name;
		name=JOptionPane.showInputDialog("Skriv in Kontaktpersonens namn.");
		try {
			statement1 = (PreparedStatement) con
					.prepareStatement("INSERT INTO kontaktperson(Kontaktnamn)"
							+ "Values ('"+name+"')");
			statement1.execute();
			
			//******TEST*** SKA TAS BORT SEDAN
			PreparedStatement statement4 = (PreparedStatement) con
					.prepareStatement("select * from kontaktperson");

			ResultSet result4 = statement4.executeQuery();
			while(result4.next()){		
			System.out.println(result4.getString(1)+" "+result4.getString(2));
			
			}} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Hoppsan,något gick snett. Finns id.t redan? Testa igen!");
			e.printStackTrace();
		}
	}
	public void addStage(){
		PreparedStatement statement1;
		String name;
		name=JOptionPane.showInputDialog("Skriv in Scenens namn.");
		int nbr=Integer.parseInt(JOptionPane.showInputDialog("Skriv in Publik Antal (Siffror)."));
		try {
			statement1 = (PreparedStatement) con
					.prepareStatement("INSERT INTO scen(Scennamn,Publik_Antal)"
							+ "Values ('"+name+"','"+nbr+"')");
			statement1.execute();
			
			} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Hoppsan,något gick snett. Finns id.t redan? Testa igen!");
			e.printStackTrace();
		}
	}
	public void changeBandContact(String bandid){
		ArrayList<String> arr=info.getContacts();
		String [] str= new String[arr.size()];
		for(int i=0;i<str.length;i++){
			str[i]=arr.get(i);
		}
		JFrame frame = new JFrame("Välj en ny Kontakt.");
		String contactID= (String) JOptionPane.showInputDialog(frame, 
		        "Välj en ny kontaktperson",
		        "Välj en ny Kontakt.",
		        JOptionPane.QUESTION_MESSAGE, 
		        null, 
		        str, 
		        str[0]);
		
			String [] parts=contactID.split(",");
			contactID=parts[0];
			
		PreparedStatement statement1;
	
		
		try {
			statement1 = (PreparedStatement) con
					.prepareStatement("UPDATE band "
							+ "SET Kontakt='"+contactID+"' "
							+ "WHERE Bandnamn='"+bandid+"'");
			statement1.execute();			
			} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Hoppsan,något gick snett.Testa igen!");
			e.printStackTrace();
		}		
		
	}
	
	public void addMember(String band){
		PreparedStatement statement1,statement;
		String name,id,information;
		name=JOptionPane.showInputDialog("Skriv in Bandmedlemmens namn.");
		
		information=JOptionPane.showInputDialog("Skriv in Bandmedlemmens information.");
		
		try {
			System.out.println(band);
			statement = (PreparedStatement) con
					.prepareStatement("Select BandID from band Where Bandnamn='"+band+"'");
			statement.execute();
			ResultSet result = statement.executeQuery();
			result.next();
			String str=result.getString(1);
			System.out.println(str+band);
			statement1 = (PreparedStatement) con
					.prepareStatement("INSERT INTO bandmedlem(MedlemsNamn,Information,Band)"
							+ "Values ('"+name+"','"+information+"','"+str+"')");
			statement1.execute();
			
			//******TEST*** SKA TAS BORT SEDAN
			PreparedStatement statement4 = (PreparedStatement) con
					.prepareStatement("select * from bandmedlem");

			ResultSet result4 = statement4.executeQuery();
			while(result4.next()){		
			System.out.println(result4.getString(1)+" "+result4.getString(2));			
			}
			JOptionPane.showMessageDialog(null,"Medlem Tillagd.");} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Hoppsan,något gick snett. Testa igen!");
			e.printStackTrace();
		}
	}
	public void addAct(){
		PreparedStatement statement1;
		String stage,start,end,day,band,security;
		String [] arr=info.getStages();
		JFrame frame = new JFrame("Välj Scen.");
		stage= (String) JOptionPane.showInputDialog(frame, 
		        "Välj Scen för Spelningen",
		        "Välj en Scen.",
		        JOptionPane.QUESTION_MESSAGE, 
		        null, 
		        arr, 
		        arr[0]);
		String [] parts=stage.split(",");
		stage=parts[0];
		
		start=JOptionPane.showInputDialog("Skriv in StartTid.");
		end=JOptionPane.showInputDialog("Skriv in SlutTid.");
		day=JOptionPane.showInputDialog("Skriv in Dag.");
		String [] arr2=new String [info.getBands().size()];
		for(int i=0;i<arr2.length;i++){
			arr2[i]=info.getBands().get(i);
		}
		JFrame frame2 = new JFrame("Välj Band.");
		band= (String) JOptionPane.showInputDialog(frame2, 
		        "Välj Band för Spelningen",
		        "Välj ett Band.",
		        JOptionPane.QUESTION_MESSAGE, 
		        null, 
		        arr2, 
		        arr2[0]);
		band=info.getBandID(band);
		
		String [] arr3=new String [info.getSecurity().size()];
		for(int i=0;i<arr3.length;i++){
			arr3[i]=info.getSecurity().get(i);
		}
		JFrame frame3 = new JFrame("Välj Säkerhetsvakt.");
		security= (String) JOptionPane.showInputDialog(frame3, 
		        "Välj Vakt för Spelningen",
		        "Välj en Vakt.",
		        JOptionPane.QUESTION_MESSAGE, 
		        null, 
		        arr3, 
		        arr3[0]);
		parts=security.split(",");
		security=parts[0];
		try {
			statement1 = (PreparedStatement) con
					.prepareStatement("INSERT INTO speltid(Scen,Starttid,Sluttid,Dag,Akt,Vakt)"
							+ "Values ('"+stage+"','"+start+"','"+end+"','"+day+"','"+band+"','"+security+"')");
			statement1.execute();
			
			//******TEST*** SKA TAS BORT SEDAN
			PreparedStatement statement4 = (PreparedStatement) con
					.prepareStatement("select * from speltid");

			ResultSet result4 = statement4.executeQuery();
			while(result4.next()){		
			System.out.println(result4.getString(1)+" "+result4.getString(2));
			
			}} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Hoppsan,något gick snett. Saknas Information,Finns Bandet?"
					+ " Testa igen!");
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Spelning Tillagd.");
	}
	public void addSecurity(){
		PreparedStatement statement1;
		String name,id;
		name=JOptionPane.showInputDialog("Skriv in Säkerhetsansvarigs namn.");
		id=JOptionPane.showInputDialog("Skriv in Säkerhetsansvarigs ID.");
		try {
			statement1 = (PreparedStatement) con
					.prepareStatement("INSERT INTO sakerhetsansvarig(Namn,SakerhetsID)"
							+ "Values ('"+name+"','"+id+"')");
			statement1.execute();
			
			//******TEST*** SKA TAS BORT SEDAN
			PreparedStatement statement4 = (PreparedStatement) con
					.prepareStatement("select * from sakerhetsansvarig");

			ResultSet result4 = statement4.executeQuery();
			while(result4.next()){		
			System.out.println(result4.getString(1)+" "+result4.getString(2));
			
			}
			JOptionPane.showMessageDialog(null, "Säkerhetsansvarig Tillagd.");
			} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Hoppsan,något gick snett. Finns Id:t redan? Testa igen!");
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Add q=new Add("195.178.232.7:4040");
		try {
			
//			q.addBand();
//			q.addContact();
//			q.addMember("Mutallica");
//			q.addSecurity();
//			q.addAct();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
