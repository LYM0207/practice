import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Board extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = req.getSession();
		
		String title = "";
		String name = (String)session.getAttribute("memberName");
		String pw = "";
		String contents = "";
		String filepath = "";
		String filename = "";

		boolean check = ServletFileUpload.isMultipartContent(req);

		if (check) {
			ServletContext context = this.getServletContext();
			filepath = context.getRealPath("/upload");
			File dir = new File(filepath);
			if (!dir.exists())
				dir.mkdir();

			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(10 * 1024);
				factory.setRepository(dir);

				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(10 * 1024 * 1024);
				upload.setHeaderEncoding("utf-8");

				ArrayList items = (ArrayList) upload.parseRequest(req);

				for (int i = 0; i < items.size(); i++) {
					FileItem item = (FileItem) items.get(i);
					String value = item.getString("utf-8");

					if (item.isFormField()) {
						if (item.getFieldName().equals("title"))
							title = value;
						else if (item.getFieldName().equals("name"))
							name = value;
						else if (item.getFieldName().equals("pw"))
							pw = value;
						else if (item.getFieldName().equals("contents"))
							contents = value;
					} else {
						if (item.getFieldName().equals("attach"))
							filename = item.getName();
						
						if (filename == null || filename.trim().length() == 0)
							continue;

						File file = new File(filepath, filename);
						item.write(file);
					}
				}
			} catch (SizeLimitExceededException e) {
				e.printStackTrace();
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println("<html><body>");

		Connection conn = null;
		PreparedStatement pstmt = null;

		String query = "insert into bboard values (null, ?, ?, ?, ?, ?)";

		DBconn db=new DBconn();
		conn = db.dbconn();
//		try {
//			Context context = new InitialContext();
//			DataSource source = (DataSource) context
//					.lookup("java:comp/env/jdbc/myconn");
//			conn = source.getConnection();
//		} catch (Exception e) {
//			// TODO: handle exception
//			out.print("DB접속 실패");
//		}

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, title);
			pstmt.setString(2, name);
			pstmt.setString(3, pw);
			pstmt.setString(4, contents);
			pstmt.setString(5, filepath + "\\" + filename);

			int res = pstmt.executeUpdate();

			pstmt.close();

			if (res > 0) {
				out.print("DB 저장 성공<br/>");
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.print("DB저장 실패 ㅠㅠ");
			// TODO: handle exception
		}

		
		out.println("제   목 : " + title + "<br/>");
		out.println("작성자 : " + name + "<br/>");
		out.println("비   번 : " + pw + "<br/>");
		out.println("내   용 : " + contents + "<br/>");
		out.println("사진 저장 경로 : " + filepath + "<br/>");
		out.println("파일 이름 : " + filename + "<br/>");
		out.println("</body></html>");

		out.close();

	}

}
