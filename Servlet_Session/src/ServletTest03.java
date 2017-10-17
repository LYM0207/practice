import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ServletTest03 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String data1 = (String)req.getAttribute("data1");
		HttpSession session = req.getSession();
		
		String data2 = (String)session.getAttribute("data2");
		
		Calendar myDate = (Calendar)session.getAttribute("data3");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String data3 = format.format(myDate.getTime());
		
		resp.setContentType("text/html;charset=utf-8");
		
		PrintWriter out  = resp.getWriter();
		
		out.println("<html><body>");
		out.println("data1 = " + data1 + "<br/>");
		out.println("data2 = " + data2 + "<br/>");
		out.println("data3 = " + data3 + "<br/><br/>");
		out.println("</body></html>");
	
	}
	
}
