package com.beans;

import java.util.HashMap;
import java.util.List;

public class InstanceBean {

	public List<TestCaseBean> getListOfTestCaseBean() {
		return listOfTestCaseBean;
	}

	public void setListOfTestCaseBean(List<TestCaseBean> listOfTestCaseBean) {
		this.listOfTestCaseBean = listOfTestCaseBean;
	}

	public HashMap<Class<?>, List<MethodBean>> getMapAllMethods() {
		return mapAllMethods;
	}

	public void setMapAllMethods(HashMap<Class<?>, List<MethodBean>> mapAllMethods) {
		this.mapAllMethods = mapAllMethods;
	}

	public List<Object> getListOfObjects() {
		return listOfObjects;
	}

	public void setListOfObjects(List<Object> listOfObjects) {
		this.listOfObjects = listOfObjects;
	}

	List<Class<?>> classes;
	List<Object> listOfObjects;
	HashMap<Class<?>, List<MethodBean>> mapAllMethods;
	List<TestCaseBean> listOfTestCaseBean;
	
	public List<Class<?>> getClasses() {
		return classes;
	}

	public void setClasses(List<Class<?>> classes) {
		this.classes = classes;
	}
	
	
	
	
}
