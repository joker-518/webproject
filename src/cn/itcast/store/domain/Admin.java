package cn.itcast.store.domain;

public class Admin {
	private String aid;
	private String name;
	private String password;
	
	public Admin(String aid, String name, String password) {
		super();
		this.aid = aid;
		this.name = name;
		this.password = password;
	}
	
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Admin(){
		
	}


}
