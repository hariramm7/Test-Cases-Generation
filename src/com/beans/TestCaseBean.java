package com.beans;

public class TestCaseBean {

	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getTestcaseElig() {
		return testcaseElig;
	}
	public void setTestcaseElig(String testcaseElig) {
		this.testcaseElig = testcaseElig;
	}
	public String getParamVal() {
		return paramVal;
	}
	public void setParamVal(String paramVal) {
		this.paramVal = paramVal;
	}
	public String getReturnTypeVal() {
		return returnTypeVal;
	}
	public void setReturnTypeVal(String returnTypeVal) {
		this.returnTypeVal = returnTypeVal;
	}
	private String className;
	private String methodName;
	private String testcaseElig;
	private String paramVal;
	private String returnTypeVal;
	public MethodBean getMethodBean() {
		return methodBean;
	}
	public void setMethodBean(MethodBean methodBean) {
		this.methodBean = methodBean;
	}
	private MethodBean methodBean;
	
	
}
