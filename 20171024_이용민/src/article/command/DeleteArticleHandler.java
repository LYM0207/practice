package article.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import article.service.ArticleData;
import article.service.ArticleNotFoundException;
import article.service.DeleteArticleService;
import article.service.DeleteRequest;
import article.service.ModifyRequest;
import article.service.PermissionDeniedException;
import article.service.ReadArticleService;
import auth.service.User;

public class DeleteArticleHandler implements CommandHandler {

	private ReadArticleService readService = new ReadArticleService();
	private DeleteArticleService deleteService = new DeleteArticleService();
	private static final String FORM_VIEW = "/WEB-INF/view/deleteSuccess.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		// TODO Auto-generated method stub

		// 게시글 번호를 파라미터로 가져온다
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		// TODO Auto-generated method stub

		// 게시글 번호
		String noVal = req.getParameter("no").trim();
		int no = Integer.parseInt(noVal);

		// 게시글 번호의 자료 조회
		ArticleData articleData = readService.getArticle(no, false);
		User authUser = (User) req.getSession().getAttribute("authUser");

		// 게시글 작성자와 로그인 ID와 다르면 에러출력
		if (!canModify(authUser, articleData)) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}

		DeleteRequest deleteReq = new DeleteRequest(no);

		try {
			// 게시글 삭제 Service
			deleteService.delete(deleteReq);
			return FORM_VIEW;

		} catch (ArticleNotFoundException e) {
			// TODO: handle exception
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			// TODO: handle exception
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}

	private boolean canModify(User authUser, ArticleData articleData) {
		String writerId = articleData.getArticle().getWriter().getId();
		return authUser.getId().equals(writerId);
	}

}
