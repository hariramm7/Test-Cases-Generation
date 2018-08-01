package com.instance;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class FindingAdequateInstance {

	public void getStaticInstance(String className){
		try {
			Class<?> clazz = Class.forName(className);
			Constructor<?> ctor = clazz.getConstructor(String.class);
			Object object = ctor.newInstance(new Object[] { "ctorArgument" });
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Object getNewInstance(Class<?> ref){
		Object obj = null;
		try {
			obj = (Object) ref.newInstance();
		}  catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	
	 static Object createObject(String className) {
	      Object object = null;
	      try {
	          Class<?> classDefinition = Class.forName(className);
	          object = classDefinition.newInstance();
	      } catch (InstantiationException e) {
	          System.out.println(e);
	      } catch (IllegalAccessException e) {
	          System.out.println(e);
	      } catch (ClassNotFoundException e) {
	          System.out.println(e);
	      }
	      return object;
	   }
}
