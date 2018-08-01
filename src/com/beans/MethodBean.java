package com.beans;

import java.util.List;


public class MethodBean {

	public String getModifiersName() {
		return modifiersName;
	}
	public void setModifiersName(String modifiersName) {
		this.modifiersName = modifiersName;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Class<?> getReturnType() {
		return returnType;
	}
	public void setReturnType(Class<?> returnType) {
		this.returnType = returnType;
	}
	public List<ParameterTypes> getParameterTypes() {
		return parameterTypes;
	}
	public void setParameterTypes(List<ParameterTypes> parameterTypes) {
		this.parameterTypes = parameterTypes;
	}
	private String modifiersName;
	private String methodName;
	private Class<?> returnType;
	private List<ParameterTypes> parameterTypes;
	
	
}
