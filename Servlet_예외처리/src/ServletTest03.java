import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletTest03 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String n1_str = req.getParameter("n1");
		String n2_str = req.getParameter("n2");

		int n1=0, n2 = 0;

		try {

			n1 = Integer.parseInt(n1_str);
			n2 = Integer.parseInt(n2_str);
		} catch (NumberFormatException e) {
			resp.sendError(510, "정수 입력 !!");
			return;
		}

		int result = 0;

		resp.setContentType("text/html;charset=utf-8");

		PrintWriter out = resp.getWriter();

		try {
			
			result = n1 / n2;
			
		} catch (ArithmeticException e) {
			
			resp.sendError(511, "연산오류");
			return;
		}

		out.println("<html><body>");
		out.println(n1 + " / " + n2 + " = " + result);
		out.println("</body></html>");
		out.close();

	}

}
