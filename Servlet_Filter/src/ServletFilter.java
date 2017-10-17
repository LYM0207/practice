import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletFilter extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = req.getParameter("name");
		String tel = req.getParameter("tel");
		String addr = req.getParameter("address");
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		out.print("<html><head>");
		out.print("<style type='text/css'>");
		out.print(".n_border {border : none};");
		out.print("</style></head><body>");
		out.print("전송된 이름");
		out.print("<input type='text' class='n_border' value='"+name+"'/><br/>");
		out.print("전송된 전화번호");
		out.print("<input type='text' class='n_border' value='"+tel+"'/><br/>");
		out.print("전송된 주소");
		out.print("<input type='text' class='n_border' value='"+addr+"'/><br/>");
		out.print("</body></html>");
		
		out.close();
	}
}
