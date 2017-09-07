package com.sjgj.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BackUser implements Serializable{
private int id;
private String backusername;
private String password;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getBackusername() {
	return backusername;
}
public void setBackusername(String backusername) {
	this.backusername = backusername;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

}
