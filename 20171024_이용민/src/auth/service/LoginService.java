package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class LoginService {
	
	private MemberDao memberDao = new MemberDao();
	
	//회원정보 조회 
	public User login(String id, String pasword) {
		try(Connection conn = ConnectionProvider.getConnection()){
			
			Member member = memberDao.selectById(conn, id);
			if(member == null){
				throw new LoginFailException();
			}
			if(!member.matchPassword(pasword)){
				throw new LoginFailException();
			}
			return new User(member.getId(), member.getName());
			
		}catch (SQLException e) {
			throw new RuntimeException();
		}
	} 
}
