import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletTest01 extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String method = req.getMethod();

		if (method.equals("GET")) { // 절대 주소도 가능
			resp.sendRedirect("http://localhost:8282/Servlet_Redirect/NewFile1.html");
			return;
		} else if (method.equals("POST")) { // 상대 주소도 가능
			resp.sendRedirect("NewFile2.html");
			return;
		}

	}

}
