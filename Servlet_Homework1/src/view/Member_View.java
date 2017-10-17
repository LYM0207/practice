package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import memberVO.Member;

public class Member_View extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Member mb = (Member)req.getAttribute("member");
		
		resp.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = resp.getWriter();
		
		out.println("<html><body>");
		out.println("ID = " + mb.getId() + "<br/>");
		out.println("PW = " + mb.getPw() + "<br/>");
		out.println("NAME = " + mb.getName() + "<br/>");
		out.println("HOBBY = " + mb.getHobby()+ "<br/>");
		out.println("</body></html>");
	
		out.close();
		
	}

}
