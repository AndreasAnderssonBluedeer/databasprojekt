package application;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class QuestionController {
	private ArrayList<String> bands = new ArrayList<>();
	private Connection con;
	public QuestionController(){
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
	
	public static void main(String [] args) {
		QuestionController q = new QuestionController();
		
	}

}
