package application;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class QuestionController {
	public QuestionController(){
		
	}
	public void Connection() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("driver true");
            con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql:///92.254.94.236:51515",
                    "Andreas", "Andreas");
            if (!con.isClosed()) {
                System.out.println("Successfully connected to "
                        + "MySQL server using TCP/IP...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
    }
		
		
		/**Connection con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://:localhost:3306", "Andreas", "Andreas");
		

		
		PreparedStatement statement = (PreparedStatement) con.prepareStatement("select * from band");
		
		ResultSet result = statement.executeQuery();
		
		while(result.next()){
			System.out.println(result.getString(1));
		}*/
	
	public static void main(String [] args) throws Exception{
		QuestionController q = new QuestionController();
		q.Connection();
	}

}
