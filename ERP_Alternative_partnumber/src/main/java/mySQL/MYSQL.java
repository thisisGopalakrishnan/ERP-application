package mySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.testng.annotations.Test;


public class MYSQL {
	@Test
	private void sql_connection() throws SQLException {
		
		Connection con = null;

		
		// Database connection URL
		String url = "jdbc:mysql://13.232.10.202:3306/ecinv";  // Schema is 'ecinv'
		String user = "ecinvuser";
		String password = "ecuser123";  // You must know or securely fetch this

        // Create connection
		
		con = DriverManager.getConnection(url,user,password);
		if (con!=null) {
			 System.out.println("✅ Database Connected Successfully!");
		}
		else {
			  System.out.println("❌ Failed to connect to Database.");
		}
		
		
		Statement statement = con.createStatement();
		String query= "select * from allocationregister";
		ResultSet exeQuery = statement.executeQuery(query);
		
		
	while(exeQuery.next()) {
		int id = exeQuery.getInt("alloc_id");
		String name = exeQuery.getString("alloc_disp_name");	
		if (id==7) {
			String jira = exeQuery.getString("jira_id");
			System.out.println("Jira ID  "+jira );
		}
		
	}
		
		
		
	
		
		
//        conn = DriverManager.getConnection(url, user, password);
//        if (conn != null) {
//            System.out.println("✅ Database Connected Successfully!");
//        } else {
//            System.out.println("❌ Failed to connect to Database.");
//        }
//        // Create Statement and Execute Query
//        java.sql.Statement stmt =  conn.createStatement();
//        String query = "select alloc_id from allocationregister";
//        	
//        ResultSet rs = stmt.executeQuery(query);
//        int columnCount = rs.getMetaData().getColumnCount();
//
//        while (rs.next()) {
//            for (int i = 1; i <= columnCount; i++) {
//                String columnName = rs.getMetaData().getColumnName(i);
//                String value = rs.getString(i);
//                System.out.print(columnName + ": " + value + "  ");
//            }
//            System.out.println(); // new line per row
//        }       
//		

	}
	
	

}
