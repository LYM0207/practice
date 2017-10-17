
//20170925   이용민//

package bs.com.control;

import java.util.ArrayList;

import bs.com.dao.UserDAO;
import bs.com.model.UserVO;

public class UserInfoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UserDAO ud = new UserDAO();

		//입력 기능
		UserVO uv = ud.insertUser();
		
		//수정 기능
		ud.updateUser();
		
		//검색 기능
		ArrayList li = ud.searchName();
		
		//삭제 기능
		ud.deleteUser();
	}

}
