import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletTest03 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	req.setCharacterEncoding("utf-8");
	
	ServletInputStream p_in = req.getInputStream();
	
	resp.setContentType("text/html;charset=utf-8");
	
	ServletOutputStream p_out = resp.getOutputStream();
	
	p_out.write("전송된 데이터 출력 시작!!<br/>".getBytes());
	
	while(true){
		int x = p_in.read();
		if(x==-1)break;	// 데이터의 끝
		p_out.write((char)x);	//데이터 끝까지 찍어라
	}
	
	p_out.write("<br/>전송된 데이터 출력 끝!!".getBytes());
	
	p_in.close();
	p_out.close();
	
	}
	
}
