package com.nix.testtask.db;

public class Message {
	private int messageid;
	private int whosendmessage;
	private int whoreceivemessage;
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
	public int getWhosendmessage() {
		return whosendmessage;
	}
	public void setWhosendmessage(int whosendmessage) {
		this.whosendmessage = whosendmessage;
	}
	public int getWhoreceivemessage() {
		return whoreceivemessage;
	}
	public void setWhoreceivemessage(int whoreceivemessage) {
		this.whoreceivemessage = whoreceivemessage;
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
