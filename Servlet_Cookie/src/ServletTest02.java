import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletTest02 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		req.setCharacterEncoding("utf-8");
		
		String id = req.getParameter("id");
		String pass = req.getParameter("pw");
		String id_rem = req.getParameter("id_rem");
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		out.println("<html><body><center><h2>");
		out.println(id + "(" + pass + ") Login Success!");
		out.println("</h2></center></body></html>");
		out.println("");
		
		if(id_rem != null  && id_rem.equals("chk")){
			Cookie id_cookie = new Cookie("id", java.net.URLEncoder.encode(id, "UTF-8"));
			id_cookie.setComment("ID saved");
			id_cookie.setPath("/");  // 경로를 루트로 잡음 (모든 웹페이지에서 사용가능 하다는 뜻)
			id_cookie.setMaxAge(60*60*24*365);   //쿠키 수명 1년
			resp.addCookie(id_cookie);			// 쿠키 저장
		}

	}

}
