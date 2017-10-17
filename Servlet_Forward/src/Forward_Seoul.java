import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Forward_Seoul extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");

		String name = req.getParameter("name");
		String id = req.getParameter("id");
		String pass = req.getParameter("pw");
		String area = req.getParameter("area");

		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();

		out.println("<html><body>");
		out.println("This page is Seoul Area<br/>");
		out.println("Name : " + id + "<br/>");
		out.println("ID : " + id + "<br/>");
		out.println("PW : " + pass + "<br/>");
		out.println("Area : " + area + "<br/>");
		out.println("</body></html>");

	}

}
