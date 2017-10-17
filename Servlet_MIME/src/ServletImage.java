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

public class ServletImage extends HttpServlet {
	
	private File image;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		resp.setContentType("image/bmp");
		
		DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(image)));
		ServletOutputStream out =resp.getOutputStream();
		
		byte[] data = new byte[512];
		while(true){
			int x = in.read(data, 0, data.length);
			
			if(x == -1)
				break;
			
			out.write(data, 0, x);
			out.flush(); //버퍼에 있는 값을 비워줘야한다.그래야 화면에 나옴.
		}
		in.close();
		out.close();
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		ServletContext context = this.getServletContext();
		String path = context.getRealPath("/image");
		
		image = new File(path,"secon_son.bmp");
	}

}
