package beans;

import java.io.Serializable;

public class User implements Serializable {
	
	public int id;
	public String username;
	public String password;
	public String resident;  //住户（暂时和username相同）

}
