import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Restore2 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String data1 = (String)req.getAttribute("V1");
		ArrayList data2 = (ArrayList)req.getAttribute("V2");
		
		String data3 = req.getParameter("data3");
		String data4 = req.getParameter("data4");
		
		resp.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = resp.getWriter();
		
		out.println("<html><body>");
		out.println("data1 = " + data1 + "<br/>");
		out.println("data2 =");
		for (int i = 0; i<data2.size(); i++) {
			out.println(data2.get(i) + "&nbsp; &nbsp;");
		}
		
		out.println("<br/>");
		out.println("data3 = " + data3 + "<br/>");
		out.println("data4 = " + data4 + "<br/>");
		out.println("</body></html>");
		out.close();
		
	}
	

}
