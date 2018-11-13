package com.sxt.model;

import java.io.Serializable;
import java.util.Arrays;

public class Request implements  Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String className;
	private String classMethod;
	private Object [] args;
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassMethod() {
		return classMethod;
	}
	public void setClassMethod(String classMethod) {
		this.classMethod = classMethod;
	}
	public Object[] getArgs() {
		return args;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}
	public Request(String className, String classMethod, Object[] args) {
		super();
		this.className = className;
		this.classMethod = classMethod;
		this.args = args;
	}
	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Request [className=" + className + ", classMethod=" + classMethod + ", args=" + Arrays.toString(args)
				+ "]";
	}
	
}
