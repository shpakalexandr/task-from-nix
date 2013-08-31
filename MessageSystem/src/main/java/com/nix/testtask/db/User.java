package com.nix.testtask.db;

public class User {
	private int userid;
	private String userfirstname;
	private String userlastname;
	private String usernickname;
	private String userpassword;
	private int userroleid;
	private int userenabled;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUserfirstname() {
		return userfirstname;
	}

	public void setUserfirstname(String userfirstname) {
		this.userfirstname = userfirstname;
	}

	public String getUserlastname() {
		return userlastname;
	}

	public void setUserlastname(String userlastname) {
		this.userlastname = userlastname;
	}

	public String getUsernickname() {
		return usernickname;
	}

	public void setUsernickname(String usernickname) {
		this.usernickname = usernickname;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public int getUserroleid() {
		return userroleid;
	}

	public void setUserroleid(int userroleid) {
		this.userroleid = userroleid;
	}

	public int getUserenabled() {
		return userenabled;
	}

	public void setUserenabled(int userenabled) {
		this.userenabled = userenabled;
	}

}
