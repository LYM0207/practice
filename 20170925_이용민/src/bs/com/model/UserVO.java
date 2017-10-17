//20170925   이용민//

package bs.com.model;

public class UserVO {

	private String name;
	private int age;

	public UserVO() {
		// TODO Auto-generated constructor stub
	}

	public UserVO(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
