package process;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import memberVO.Member;

public class Member_Process extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		String hobby = req.getParameter("hobby");
		
		Member mb = new Member();
		
		mb.setId(id);
		mb.setPw(pw);
		mb.setName(name);
		mb.setHobby(hobby);
		
		req.setAttribute("member", mb);
		
		ServletContext context = this.getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/Homework01_View");
		dispatcher.forward(req, resp);
		
	}
	
}
