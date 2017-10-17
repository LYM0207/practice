import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardTest01 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");

		String area = req.getParameter("area");

		ServletContext context = this.getServletContext();
		RequestDispatcher dispatcher = null;

		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		////////////////////////  1.   dispatcher의  include   //////////////////////////////////
		out.println("<html><body>");
		out.println("Seoul page insert!");
		dispatcher = context.getRequestDispatcher("/Forward_Seoul");
		dispatcher.include(req, resp);
		
		out.println("<br/><br/>");
		out.println("Busan page insert!");
		dispatcher = context.getRequestDispatcher("/Forward_Busan");
		dispatcher.include(req, resp);

		out.println("</body></html>");
		out.close();
		
		/////////////////////////   2.   dispatcher의 forward   ////////////////////////////////
//		if (area == null) {
//			resp.sendError(512, "Radio button error!");
//			return;
//		} else if (area.equals("Seoul")) {
//			dispatcher = context.getRequestDispatcher("/Forward_Seoul");  // 상대 주소만 된다.
//		} else if (area.equals("Busan")) {
//			dispatcher = context.getRequestDispatcher("/Forward_Busan");
//		}
//		
//		dispatcher.forward(req, resp);
//		
	}

}
