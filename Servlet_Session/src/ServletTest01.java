import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ServletTest01 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setAttribute("data1", "홍길동");
		
		HttpSession session = req.getSession(true);
		session.setAttribute("data2", "Java");
		
		Calendar myDate = Calendar.getInstance();
		session.setAttribute("data3", myDate);
		
		ServletContext context = this.getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/Servlet02");
		
		dispatcher.forward(req, resp);
	}

	
	
}
