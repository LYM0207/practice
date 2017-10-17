import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Restore extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String data1 = new String("JAVA");
		ArrayList<String> data2 = new ArrayList<String>();
		
		data2.add("12345");
		data2.add("abcde");
		
		//request영역에 저장
		req.setAttribute("V1", data1);
		req.setAttribute("V2", data2);
		
		ServletContext context = this.getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/Servlet01_request?data3=http01&data4=http02");
		dispatcher.forward(req, resp);
		
	}

	
	
}
