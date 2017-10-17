import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletGraph extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = this.getServletContext().getRealPath("/data");
		String filename = this.getInitParameter("result");

		File dir = new File(path);
		if (!dir.exists())
			dir.mkdir();

		File file = new File(dir, filename);

		int agree = 0;
		int disagree = 0;

		if (file.exists()) {
			DataInputStream f_in = new DataInputStream(new BufferedInputStream(
					new FileInputStream(file)));
			try {
				agree = f_in.readInt();
				disagree = f_in.readInt();
			} catch (Exception e) {
				// TODO: handle exception
				agree = 0;
				disagree = 0;
				e.printStackTrace();
			}
			f_in.close();

		}
		String choice = req.getParameter("choice");
		
		if(choice != null && choice.equals("yes"))
			agree++;
		else if(choice != null && choice.equals("no"))
			disagree++;
		
		DataOutputStream f_out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
		
		f_out.writeInt(agree);
		f_out.writeInt(disagree);
		
		f_out.close();
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		out.println("<html><body><center>");
		out.println("<h3>투표결과</h3>");
		out.println("<img src='Servlet05?agree="+agree + "&disagree=" + disagree + "'/>");
		out.println("</center></body></html>");
	
	}
}
