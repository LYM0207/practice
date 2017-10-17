import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.util.SuffixConstants;

public class ServletSound extends HttpServlet {

	private File file;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		if (file.getName().endsWith(".au") || file.getName().endsWith(".snd")) {
			resp.setContentType("audio/basic");
		} else if (file.getName().endsWith(".wma")) {
			resp.setContentType("audio/x-ms-wma");
		}

		DataInputStream in = new DataInputStream(new BufferedInputStream(
				new FileInputStream(file)));
		ServletOutputStream out = resp.getOutputStream();

		byte[] data = new byte[512];
		while (true) {
			int x = in.read(data, 0, data.length);

			if (x == -1)
				break;

			out.write(data, 0, x);
			out.flush(); // 버퍼에 있는 값을 비워줘야한다.그래야 화면에 나옴.
		}
		in.close();
		out.close();
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		String path = this.getInitParameter("dir");
		String filename = this.getInitParameter("filename");

		ServletContext context = this.getServletContext();
		path = context.getRealPath(path);

		file = new File(path, filename);
	}

}
