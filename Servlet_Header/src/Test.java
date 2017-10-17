import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Test extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		String name = req.getParameter("name");
		String[] skill = req.getParameterValues("skill");
		
		Enumeration enu = req.getHeaderNames();
		Vector names = new Vector();
		Vector values = new Vector();
		
		while(enu.hasMoreElements()){
			String header_name = ((String) enu.nextElement());
			String header_value = req.getHeader(header_name);
			
			names.add(header_name);
			values.add(header_value);
		}
		
		resp.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = resp.getWriter();
		
		out.println("<html><body>");
		out.println("전달받은 데이터<br/>");
		out.println("name : " + name + "<br/>");
		out.println("skill : ");
		
		for (int i = 0; i < skill.length; i++) {
			out.println(skill[i]+ "&nbsp;");
		}
		
		out.println("<br/><br/><br/><br/><br/>");
		out.println("전달받은 헤더 정보<br/>");
		
		for (int i = 0; i < names.size(); i++) {
			String header_name = (String)(names.elementAt(i));
			String header_value = (String)(values.elementAt(i));
			
			out.println(header_name+ " : ");
			out.println(header_value+" <br/> ");
		}
	
		out.println("</body></html>");
		out.close();
		
		
	}
	

}

//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
//public class Test02 extends HttpServlet {
//
//	private int cnt = 0;
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		
//		resp.setContentType("text/html;charset=utf-8");
//		resp.setHeader("refresh", "3");
//		
//		PrintWriter out = resp.getWriter();
//		out.println("<html><body>");
//		out.println("cnt = " + cnt++);
//		out.println("</body></html>");
//		out.close();
//		
//	}
//	
//	
//}

