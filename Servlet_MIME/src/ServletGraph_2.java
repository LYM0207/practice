import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;


public class ServletGraph_2 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int agree = Integer.parseInt(req.getParameter("agree"));
		int disagree = Integer.parseInt(req.getParameter("disagree"));
		int tot = agree + disagree;
		
		Graphics2D g = null;
		
		try {
			int width = 300;
			int height = 200;
			
			BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			
			g = img.createGraphics();
			
			int x_start = 30;
			int agree_y_start = height * 1 / 4;
			int disagree_y_start = height * 3 / 5;
			int g_height = 25;
			int space = 5;
			int y_space = 20;
			
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(0, 0, width, height);
			g.setColor(Color.BLACK);
			g.drawLine(x_start, space, x_start, height - y_space);
			g.drawLine(x_start, height - y_space, width - space, height - y_space);
			g.drawString("찬성", space, height * 1 / 3);
			g.drawString("반대", space, height * 2 / 3);
			g.drawString("0%", x_start, height - space);
			g.drawString("50%", x_start + ((width - (x_start + space)) / 2), height - space);
			g.drawString("100%", width - 30, height - space);
			
			g.setColor(Color.RED);
			g.fillRect(x_start, agree_y_start, (width - (x_start + space)) * agree / tot, g_height);
			
			g.setColor(Color.GREEN);
			g.fillRect(x_start, disagree_y_start, (width - (x_start + space)) * disagree / tot, g_height);
			
			resp.setContentType("image/jpeg");
			
			ServletOutputStream out = resp.getOutputStream();
			
			JPEGImageEncoder encode = JPEGCodec.createJPEGEncoder(out);
			
			encode.encode(img);
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(g != null)
				g.dispose();
		}
	}

}
