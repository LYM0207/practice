package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	//연결된 Connection을 끊기위한 메서드들
	
	public static void close(ResultSet rs){
		if(rs != null){
			try{
				rs.close();
			}catch(SQLException e){
				
			}
		}
	}
	
	public static void close(Statement stmt){
		if(stmt != null){
			try{
				stmt.close();
			}catch(SQLException e){
				
			}
		}
	}
	
	
	public static void close(Connection conn){
		if(conn != null){
			try{
				conn.close();
			}catch(SQLException e){
				
			}
		}
	}
	public static void rollback(Connection conn){
		if(conn != null){
			try{
				conn.rollback();
			}catch(SQLException e){
				
			}
		}
	}

}
