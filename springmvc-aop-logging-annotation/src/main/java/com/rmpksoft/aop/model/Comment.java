package com.rmpksoft.aop.model;


public class Comment {
	
	private Long id;
	private String msg;
	
	

	public Comment(Long id, String msg) {
		this.id = id;
		this.msg = msg;
	}

	public Comment() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
