package article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import article.model.ArticleContent;
import jdbc.JdbcUtil;

public class ArticleContentDao {

	// 게시글 내용 입력
	public ArticleContent insert(Connection conn, ArticleContent content)
			throws SQLException {

		PreparedStatement pstmt = null;

		String sql = "insert into " + " article_content " + "("
				+ "  article_no" + ", content" + ") " + "values " + "(?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, content.getNumber());
			pstmt.setString(2, content.getContent());

			int insertedCount = pstmt.executeUpdate();

			if (insertedCount > 0) {
				return content;
			} else {
				return null;
			}

		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	// 게시글 내용 조회
	public ArticleContent selectById(Connection conn, int no)
			throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from article_content where article_no = ? ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			ArticleContent content = null;

			if (rs.next()) {
				content = new ArticleContent(rs.getInt("article_no"),
						rs.getString("content"));
			}

			return content;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

	}

	// 게시글 수정 메서드
	public int update(Connection conn, int no, String content)
			throws SQLException {

		String sql = "update article_content set content = ? "
				+ " where article_no = ? ";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, content);
			pstmt.setInt(2, no);
			return pstmt.executeUpdate();
		}
	}

}
