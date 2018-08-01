package com.instance;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import com.beans.Constants;
import com.beans.MethodBean;
import com.beans.ParameterTypes;

public class AdequateSequenceOfMethods {
	
	public List<MethodBean> searchSequenceOfMethods(Class<?> clazz){
		
		List<MethodBean> methodBeanLst = new ArrayList<MethodBean>();
		try {
		Method m[] = clazz.getDeclaredMethods();
		String method_data[] = new String[m.length];
		for(int i = 0; i < method_data.length; i++){
			MethodBean methodBean = new MethodBean();
		    methodBean.setMethodName(m[i].getName());
		    methodBean.setReturnType(m[i].getReturnType());
		   
		    if(m[i].getModifiers() == Modifier.PUBLIC){
		    	methodBean.setModifiersName(Constants.PUBLIC);
		    }else if(m[i].getModifiers() == Modifier.PRIVATE){
		    	methodBean.setModifiersName(Constants.PRIVATE);
		    }else if(m[i].getModifiers() == Modifier.PROTECTED){
		    	methodBean.setModifiersName(Constants.PROTECTED);
		    }
		    
		    List<ParameterTypes> parameterTypeLst = new ArrayList<ParameterTypes>(); 
		    Class<?> param[] = m[i].getParameterTypes();
		    for(int j = 0; j < param.length; j++){
		    	ParameterTypes parameterTypes = new ParameterTypes();
		    	parameterTypes.setParameterType(param[j]);
		    	parameterTypeLst.add(parameterTypes);
		    }
		    methodBean.setParameterTypes(parameterTypeLst);
		    methodBeanLst.add(methodBean);
		}
		
	} catch (SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}    
		return methodBeanLst;
	}
}
