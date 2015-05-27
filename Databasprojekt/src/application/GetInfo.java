package application;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class GetInfo {
	private ArrayList<String> bands = new ArrayList<>();
	private ArrayList<String> contacts = new ArrayList<>();
	private ArrayList<String> security = new ArrayList<>();
	private ArrayList<String> schedule = new ArrayList<>();
	private ArrayList<String> members = new ArrayList<>();
	private Connection con;
	public GetInfo(){
		try {
			connection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void connection()  throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
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

            PreparedStatement statement = (PreparedStatement) con.prepareStatement("select BandNamn from band");
    		
    		ResultSet result = statement.executeQuery();
    		
    			result.next();
    			System.out.println(result.getString(1));
    			result.next();
    			System.out.println(result.getString(1));
    		

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Exception: " + e.getMessage());
        } 
            } 
            
    
    public ArrayList<String> getBands() throws Exception{
    	bands = new ArrayList<>();
		PreparedStatement statement = (PreparedStatement) con.prepareStatement("select * from band");
		
		ResultSet result = statement.executeQuery();
		while(result.next()){
			bands.add(result.getString(1));
		}
    	return bands;
    }
    public ArrayList<String> getMembers(String band) {

    	members = new ArrayList<>();
		PreparedStatement statement;
		try {
			statement = (PreparedStatement) con.prepareStatement("select * from bandmedlem where Band='"+band+"'");
			
			ResultSet result = statement.executeQuery();
			System.out.println(band+3);
			while(result.next()){
				members.add("ID: "+result.getString(1)+" Namn: "+result.getString(2)+" Information: "+result.getString(3));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<members.size();i++){
			System.out.println(members.get(i));
		}
    	return members;
    }
    public String getBandInfo(String bandname) {
    	String bandinfo="?";
		
		try {
			PreparedStatement statement = (PreparedStatement) con.prepareStatement("select * from band where Bandnamn='"+bandname+"'");
			
			ResultSet result;
			result = statement.executeQuery();
		
			result.next();
			bandinfo="Band: "+result.getString(1)+" Land: "+result.getString(2)+
					" Genre: "+result.getString(3)+" Kontakt: "+result.getString(4);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return bandinfo;
    	
    }
    public ArrayList<String> getContacts() {
    	contacts = new ArrayList<>();
		PreparedStatement statement;
		try {
			statement = (PreparedStatement) con.prepareStatement("select * from kontaktperson");
		
		
		ResultSet result = statement.executeQuery();
		while(result.next()){
			contacts.add(result.getString(1)+", "+result.getString(2));
		}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Kunde inte hämta info från Databasen!");
			e.printStackTrace();
		}
    	return contacts;
    } 
    public ArrayList<String> getSecurity() {
    	security = new ArrayList<>();
		PreparedStatement statement;
		try {
			statement = (PreparedStatement) con.prepareStatement("select * from sakerhetsansvarig");
		
		
		ResultSet result = statement.executeQuery();
		while(result.next()){
			security.add(result.getString(1)+", "+result.getString(2));
		}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Kunde inte hämta info från Databasen!");
			e.printStackTrace();
		}
    	return security;
    }
    public ArrayList<String> getSchedule(String day) {
    	schedule = new ArrayList<>();
		PreparedStatement statement;
		try {
			statement = (PreparedStatement) con.prepareStatement("select * from speltid Where Dag='"+day+"'");
		
		
		ResultSet result = statement.executeQuery();
		while(result.next()){
			schedule.add(result.getString(1)+", "+result.getString(2)+", "+result.getString(3)+", "+
		result.getString(5)+", "+result.getString(6));
		}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Kunde inte hämta info från Databasen!");
			e.printStackTrace();
		}
    	return schedule;
    } 
    public static void main(String [] args) {
		GetInfo q = new GetInfo();
		
	}

}
