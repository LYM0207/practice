package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class ChangePasswordService {

	private MemberDao memberDao = new MemberDao();

	// 패스워드 변경
	public void changePassword(String userId, String curPwd, String newPwd) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Member member = memberDao.selectById(conn, userId);

			// 멤버 존재하지 않을 경우
			if (member == null) {
				throw new MemberNotFoundException();
			}
			
			// 패스워드가 일치 하지 않을 경우
			if (!member.matchPassword(curPwd)) {
				throw new InvalidPasswordException();
			}

			member.changePassword(newPwd);
			memberDao.update(conn, member);
			conn.commit();

		} catch (SQLException e) {
			// TODO: handle exception
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);

		} finally {
			JdbcUtil.close(conn);
		}
	}
}
