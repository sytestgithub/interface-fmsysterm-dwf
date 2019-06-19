package com.daojia.qa.entity;

public class ButtonDto {
	public String code;
	public String name;
	public Object param;
	
	public ButtonDto(String code, String name, Object param) {
		super();
		this.code = code;
		this.name = name;
		this.param = param;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public Object getParam() {
		return param;
	}
	public void setParam(Object param) {
		this.param = param;
	}
	@Override
	public String toString() {
		return "buttonDto [code=" + code + ", name=" + name + ", param=" + param + "]";
	}
	
}
