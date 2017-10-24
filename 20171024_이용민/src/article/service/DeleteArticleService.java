package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import article.dao.ArticleDao;
import article.model.Article;

public class DeleteArticleService {

	private ArticleDao articleDao = new ArticleDao();

	// 게시글 삭제 서비스
	public void delete(DeleteRequest deleteReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Article article = articleDao.selectById(conn,
					deleteReq.getArticleNumber());

			// 게시글 번호에 해당하는 글이 없으면 에러
			if (article == null) {
				throw new ArticleNotFoundException();
			}

			// 게시글 정보 삭제
			articleDao.deleteArticle(conn, deleteReq.getArticleNumber());
			articleDao.deleteArticleContent(conn, deleteReq.getArticleNumber());
			conn.commit();

		} catch (SQLException e) {
			// TODO: handle exception
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (PermissionDeniedException e) {
			// TODO: handle exception
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}

}
