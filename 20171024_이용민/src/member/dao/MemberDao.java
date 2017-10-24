package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import jdbc.JdbcUtil;
import member.model.Member;

public class MemberDao {

	// 멤버 정보 조회
	public Member selectById(Connection conn, String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn
					.prepareStatement("select * from member where memberid=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			Member member = null;
			if (rs.next()) {
				member = new Member(rs.getString("memberid"),
						rs.getString("name"), rs.getString("password"),
						toDate(rs.getTimestamp("regdate")));
			}
			return member;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// 현재날짜
	private Date toDate(Timestamp date) {
		// TODO Auto-generated method stub
		return date == null ? null : new Date(date.getTime());
	}

	// 멤버 정보 등록
	public void insert(Connection conn, Member mem) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("insert into member values(?,?,?,?)")) {
			pstmt.setString(1, mem.getId());
			pstmt.setString(2, mem.getName());
			pstmt.setString(3, mem.getPassword());
			pstmt.setTimestamp(4, new Timestamp(mem.getRegDate().getTime()));
			pstmt.executeUpdate();
		}
	}

	// 패스워드 변경
	public void update(Connection conn, Member member) throws SQLException {
		String sql = "update member set name =?, password=? where memberid=?";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getId());
			pstmt.executeUpdate();

		}
	}

}
