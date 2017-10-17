import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ServletTest04 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = "";
		String id = "";
		String pw = "";
		String filename = "";

		boolean check = ServletFileUpload.isMultipartContent(req);

		if (check) {
			ServletContext context = this.getServletContext();
			String path = context.getRealPath("/upload");
			File dir = new File(path);
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
						if (item.getFieldName().equals("name"))
							name = value;
						else if (item.getFieldName().equals("id"))
							id = value;
						else if (item.getFieldName().equals("pw"))
							pw = value;
					} else {
						if (item.getFieldName().equals("picture"))
							filename = item.getName();
						if (filename == null || filename.trim().length() == 0)
							continue;

						filename = filename.substring(filename
								.lastIndexOf("\\" )+ 1);

						File file = new File(dir, filename);
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
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();

			out.println("<html><body>");
			out.println("이름 : " + name + "<br/>");
			out.println("아이디 : " + id + "<br/>");
			out.println("비번 : " + pw + "<br/>");
			out.println("사진 저장 경로 : " + dir + "<br/>");
			out.println("파일 이름 : " + filename + "<br/>");
			out.println("</body></html>");
			
			out.close();
		}

	}

}
