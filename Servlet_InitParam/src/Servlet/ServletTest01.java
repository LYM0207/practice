package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletTest01 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		
		
		/******************* 중요한 부분!!!!!!****************************************/
		ServletConfig config = this.getServletConfig();
		String start = config.getInitParameter("start");
		String end = config.getInitParameter("end");
		/************************************************************************/
		
		
		int start_nu = Integer.parseInt(start);
		int end_nu = Integer.parseInt(end);

		int sum = 0;
		for (int i = start_nu; i <= end_nu; i++) {
			sum += i;
		}
		
		resp.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = resp.getWriter();
		
		out.println("<html><body><center>");
		out.println(start_nu + " ~ " + end_nu+ "사이의 합 : ");
		out.println(sum +"입니다");
		out.println("</center></body></html>");

		out.close();

	}

}
