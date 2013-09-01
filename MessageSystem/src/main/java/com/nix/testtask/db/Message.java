package com.nix.testtask.db;

public class Message {
	private int messageid;
	private int whosendmessageid;
	private String whosendmessagenickname;
	private int whoreceivemessageid;
	private String whoreceivemessagenickname;
	private String messagetitle;
	private String messagebody;
	private Boolean displaytosender;
	private Boolean displaytoreceiver;

	public int getMessageid() {
		return messageid;
	}
	public void setMessageid(int messageid) {
		this.messageid = messageid;
	}
	public int getWhosendmessageid() {
		return whosendmessageid;
	}
	public void setWhosendmessageid(int whosendmessageid) {
		this.whosendmessageid = whosendmessageid;
	}
	public String getWhosendmessagenickname() {
		return whosendmessagenickname;
	}
	public void setWhosendmessagenickname(String whosendmessagenickname) {
		this.whosendmessagenickname = whosendmessagenickname;
	}
	public int getWhoreceivemessageid() {
		return whoreceivemessageid;
	}
	public void setWhoreceivemessageid(int whoreceivemessageid) {
		this.whoreceivemessageid = whoreceivemessageid;
	}
	public String getWhoreceivemessagenickname() {
		return whoreceivemessagenickname;
	}
	public void setWhoreceivemessagenickname(String whoreceivemessagenickname) {
		this.whoreceivemessagenickname = whoreceivemessagenickname;
	}
	public String getMessagetitle() {
		return messagetitle;
	}
	public void setMessagetitle(String messagetitle) {
		this.messagetitle = messagetitle;
	}
	public String getMessagebody() {
		return messagebody;
	}
	public void setMessagebody(String messagebody) {
		this.messagebody = messagebody;
	}
	public Boolean getDisplaytosender() {
		return displaytosender;
	}
	public void setDisplaytosender(Boolean displaytosender) {
		this.displaytosender = displaytosender;
	}
	public Boolean getDisplaytoreceiver() {
		return displaytoreceiver;
	}
	public void setDisplaytoreceiver(Boolean displaytoreceiver) {
		this.displaytoreceiver = displaytoreceiver;
	}
}
