package com.example.spring.myapp.dto;

import lombok.ToString;

@ToString
public class MemberDto {

	private String id; //아이디
	private String pwd; //비밀번호
	private String name; //성명
	private String email; //이메일
	private int auth; //시퀀스

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

}
