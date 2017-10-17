import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class ServletTest02 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");

		String subject = req.getParameter("subject");
		String author = req.getParameter("author");
		String contents = req.getParameter("contents");

		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();

		out.println("<html><body><center><h3>");

		Connection conn = null;
		PreparedStatement pstmt = null;

		String query = "insert into servlettest values (null, ?, ?, ?)";

		try {
			Context context = new InitialContext();
			DataSource source = (DataSource)context
					.lookup("java:comp/env/jdbc/myconn");
			conn = source.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, subject);
			pstmt.setString(2, author);
			pstmt.setString(3, contents);

			int res = pstmt.executeUpdate();

			pstmt.close();
			if (res > 0) {
				// out.println("Success save!");

				String query2 = "select * from servlettest";
				pstmt = conn.prepareStatement(query2);
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					int no = rs.getInt("no");
					String subject2 = rs.getString("subject");
					String author2 = rs.getString("author");
					String contents2 = rs.getString("contents");

					out.print(no + "  " + subject2 + "  " + author2 + "  "
							+ contents2+ "<br/>");
				}
			}

			pstmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
			out.println("SQL erro : " + e.getMessage());
		}

		out.println("</h3></center></body></html>");
	}

}
