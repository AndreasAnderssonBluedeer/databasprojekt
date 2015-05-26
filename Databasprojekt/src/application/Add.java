package application;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

public class Add {
	private Connection con;
	
	public Add(){
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
					"jdbc:mysql://94.254.94.236:51515/andreas&david",
					"Andreas", "jody");
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
		PreparedStatement statement1;
		String bandname,country,genre,contactID;
		bandname=JOptionPane.showInputDialog("Skriv in Bandets namn.");
		country=JOptionPane.showInputDialog("Skriv in Bandets Land");
		genre=JOptionPane.showInputDialog("Skriv in Bandets Genre.");
		contactID=JOptionPane.showInputDialog("Skriv in Bandets Kontaktperson(KontaktID).");
		try {
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
			
			}} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Hoppsan,något gick snett. Testa igen!");
			e.printStackTrace();
		}
		
	}
	public void addContact(){
		PreparedStatement statement1;
		String name,id;
		name=JOptionPane.showInputDialog("Skriv in Kontaktpersonens namn.");
		id=JOptionPane.showInputDialog("Skriv in Kontaktpersonen ID.");
		try {
			statement1 = (PreparedStatement) con
					.prepareStatement("INSERT INTO kontaktperson(Kontaktnamn,KontaktID)"
							+ "Values ('"+name+"','"+id+"')");
			statement1.execute();
			
			//******TEST*** SKA TAS BORT SEDAN
			PreparedStatement statement4 = (PreparedStatement) con
					.prepareStatement("select * from kontaktperson");

			ResultSet result4 = statement4.executeQuery();
			while(result4.next()){		
			System.out.println(result4.getString(1)+" "+result4.getString(2));
			
			}} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Hoppsan,något gick snett. Testa igen!");
			e.printStackTrace();
		}
	}
	public void addMember(String band){
		PreparedStatement statement1;
		String name,id,information;
		name=JOptionPane.showInputDialog("Skriv in Bandmedlemmens namn.");
		id=JOptionPane.showInputDialog("Skriv in Bandmedlemmens ID.");
		information=JOptionPane.showInputDialog("Skriv in Bandmedlemmens information.");
		
		try {
			statement1 = (PreparedStatement) con
					.prepareStatement("INSERT INTO bandmedlem(MedlemsNamn,MedlemsID,Information,Band)"
							+ "Values ('"+name+"','"+id+"','"+information+"','"+band+"')");
			statement1.execute();
			
			//******TEST*** SKA TAS BORT SEDAN
			PreparedStatement statement4 = (PreparedStatement) con
					.prepareStatement("select * from bandmedlem");

			ResultSet result4 = statement4.executeQuery();
			while(result4.next()){		
			System.out.println(result4.getString(1)+" "+result4.getString(2));
			
			}} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Hoppsan,något gick snett. Testa igen!");
			e.printStackTrace();
		}
	}
	public void addAct(){
		PreparedStatement statement1;
		String stage,start,end,day,band,security;
		stage=JOptionPane.showInputDialog("Skriv in Scenens namn.");
		start=JOptionPane.showInputDialog("Skriv in StartTid.");
		end=JOptionPane.showInputDialog("Skriv in SlutTid.");
		day=JOptionPane.showInputDialog("Skriv in Dag.");
		band=JOptionPane.showInputDialog("Skriv in Bandets namn.");
		security=JOptionPane.showInputDialog("Skriv in Säkerhetsansvarigs ID.");
		try {
			statement1 = (PreparedStatement) con
					.prepareStatement("INSERT INTO speltid(Scennamn,Starttid,Sluttid,Dag,Akt,Brandvakt)"
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
			
			}} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Hoppsan,något gick snett. Testa igen!");
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Add q=new Add();
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
