import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class ServletFile extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		File dir = new File(this.getServletContext().getRealPath("/upload"));
		if(!dir.exists())
			dir.mkdir();
		String filename = "";
		
		if(ServletFileUpload.isMultipartContent(req)) {
			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(10 * 1024);
				factory.setRepository(dir);
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(10 * 1024 * 1024);
				upload.setHeaderEncoding("utf-8");
				ArrayList items = (ArrayList)upload.parseRequest(req);
				
				FileItem item = (FileItem)items.get(0);
				if(!item.isFormField() && item.getFieldName().equals("attach_file")) {
					filename = item.getName();
					if(filename != null && filename.trim().length() != 0) {
						filename = filename.substring(filename.lastIndexOf("\\") + 1);
						File file = new File(dir, filename);
						item.write(file);
						
					}//
				}//
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		String extern = filename.substring(filename.lastIndexOf("."));
		String mime = "";
		
		if(extern.equalsIgnoreCase(".doc"))
			mime = "application/msword";
		else if(extern.equalsIgnoreCase(".xls"))
			mime = "application/vnd.ms-excel";
		else if(extern.equalsIgnoreCase(".ppt"))
			mime = "application/vnd.ms-powerpoint";
		else
			mime = "application/octet-stream";
		
		resp.setContentType(mime);
		
		ServletOutputStream out = resp.getOutputStream();
		
		DataInputStream in = new DataInputStream
				(new BufferedInputStream
						(new FileInputStream
								(new File(dir, filename))));
		
		byte[] data = new byte[1024];
		
		while(true) {
			int len = in.read(data, 0, data.length);
			
			if(len == -1)
				break;
			
			out.write(data, 0, len);
			out.flush();
		}
		
		in.close();
		out.close();
	}

}
