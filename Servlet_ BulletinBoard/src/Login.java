import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = req.getSession(true);
		ServletContext context = null;
		RequestDispatcher dispatcher =null;
		
		req.setCharacterEncoding("utf-8");

		String id = req.getParameter("id");
		String pw = req.getParameter("pw");

		DBconn db = new DBconn();
		Connection conn = db.dbconn();
		PreparedStatement pstmt = null;

		String sql = "select * from memberjoin ";
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String memberId = rs.getString("id");
				String memberPw = rs.getString("pw");
				String memberName = rs.getString("name");
				
				if(id.equals(memberId) && pw.equals(memberPw)){
					System.out.println("로그인 성공");
					session.setAttribute("memberName", memberName);
					context = this.getServletContext();
					dispatcher = context.getRequestDispatcher("/myboard");
					dispatcher.forward(req, resp);
					
				}

//				mb = new MemberVO();
//				mb.setId(memberId);
//				mb.setPw(memberPw);
//				mb.setName(memberName);
//
//				infoList.add(mb);
			}
			
//			for (int i = 0; i < infoList.size(); i++) {
//				mb = infoList.get(i);
//				if(mb.getId().equals(id)){
//					if(mb.getPw().equals(pw)){
//						
//					}
//				}
//				
//			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
