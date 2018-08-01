package com.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.beans.Constants;
import com.beans.InstanceBean;
import com.beans.MethodBean;
import com.beans.ParameterTypes;
import com.beans.TestCaseBean;
import com.instance.AdequateSequenceOfMethods;
import com.instance.FindingAdequateInstance;
import com.instance.ObjectFactory;


public class Utilities {

	public ObjectFactory getObjectFactory() {
		return objectFactory;
	}

	public void setObjectFactory(ObjectFactory objectFactory) {
		this.objectFactory = objectFactory;
	}

	private InstanceBean instanceBean;
	private ObjectFactory objectFactory;
	
	public void generateAdequateInstance(JTextArea jtaStoredInstances) {
		// TODO Auto-generated method stub
		List<Class<?>> classes = this.getInstanceBean().getClasses();
		
		List<Object> listOfObjects = this.objectFactory.createListOfObjects();
		
		for (int i = 0; i < classes.size(); i++) {
			Class<?> clazz = classes.get(i);
			System.out.println(clazz.getName());
			jtaStoredInstances.append("Class["+(i + 1)+"]"+clazz.getName() + "\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			FindingAdequateInstance findingAdequateInstance = this.getObjectFactory().createFindingAdequateInstanceObject();

			Object object =  findingAdequateInstance.getNewInstance(clazz);
			jtaStoredInstances.append(Messages.getString("utilities.gen.ins")+object.getClass().getCanonicalName() + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
			listOfObjects.add(object);
			
		}
		
		this.getInstanceBean().setListOfObjects(listOfObjects);
		jtaStoredInstances.append(Messages.getString("utilities.msg.1") + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
		JOptionPane.showMessageDialog(null, Messages.getString("utilities.msg.2")); //$NON-NLS-1$
		
	}

	public InstanceBean getInstanceBean() {
		return instanceBean;
	}

	public void setInstanceBean(InstanceBean instanceBean) {
		this.instanceBean = instanceBean;
	}

	public void storeClasses(List<Class<?>> classes) {
		// TODO Auto-generated method stub
		this.getInstanceBean().setClasses(classes);
	}

	public HashMap<Class<?>, List<MethodBean>> generateMethodCalls() {
		// TODO Auto-generated method stub
		List<Object> listOfObjects = this.getInstanceBean().getListOfObjects();
		
		AdequateSequenceOfMethods adequateSequenceOfMethods = this.getObjectFactory().createAdequateSequenceOfMethodsObject();
		HashMap<Class<?>, List<MethodBean>> mapAllMethods = new HashMap<Class<?>, List<MethodBean>>();
		
		if(listOfObjects != null){
			
			for (int i = 0; i < listOfObjects.size(); i++) {
				Class<?> clazz = listOfObjects.get(i).getClass();
				
				List<MethodBean> methodBeanLst =  adequateSequenceOfMethods.searchSequenceOfMethods(clazz);
				
				mapAllMethods.put(clazz, methodBeanLst);
			}
		}
		return mapAllMethods;
	}

	public String getParameterString(List<ParameterTypes> parameterTypeLst) {
		// TODO Auto-generated method stub
		
		String paramerterStr = "";
		
		for (int i = 0; i < parameterTypeLst.size(); i++) {
			paramerterStr = paramerterStr + "[ "+parameterTypeLst.get(i).getParameterType()+" ]";
		}
		
		return paramerterStr;
	}
	
	public String getCurrentDate() {
		// TODO Auto-generated method stub
		Date curDate = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy_hh_mm");
		String dateToStr = format.format(curDate);

		return dateToStr;
	}

	public void generateTestcases() {
		// TODO Auto-generated method stub
		ArrayList header = new ArrayList();
		
		header.add("Class Name");
		header.add("Method Name");
		header.add("Modifiers Name");
		header.add("Parameter Type");
		header.add("Return Type");
		header.add("Test cases");
		
		List<TestCaseBean> testCaseBeanLst = this.getInstanceBean().getListOfTestCaseBean();
		List<ArrayList> data = new ArrayList();
		for (TestCaseBean testCaseBean : testCaseBeanLst) {
			ArrayList<String> testcase = null;
		if(testCaseBean.getTestcaseElig().compareTo(Constants.TESTCASE_ELIGIBILITY_YES) == 0){
			
			if(testCaseBean.getParamVal().compareTo(Constants.VALIDATION_YES) ==0){
				List<ParameterTypes> parameterTypeLst = testCaseBean.getMethodBean().getParameterTypes();
			List<String> testcases = generateTestcases(parameterTypeLst);
			System.out.println(testcases);
			
			for (String string : testcases) {
				testcase = new ArrayList();
				testcase.add(testCaseBean.getClassName());
				testcase.add(testCaseBean.getMethodName());
				testcase.add(testCaseBean.getMethodBean().getModifiersName());
				testcase.add(getParameterString(parameterTypeLst));
				testcase.add(testCaseBean.getMethodBean().getReturnType().toString());
				testcase.add(string);
				data.add(testcase);
			}
			}
			}
		}
		
		ExportTestcasesToExcel testcasesToExcel = this.getObjectFactory().createExportTestcasesToExcelObjects();
		String fileName = "Generated_Testcases/testcases"+ getCurrentDate() +".xls";
		testcasesToExcel.export(header, data, "Test Cases", fileName);
		JOptionPane.showMessageDialog(null, "Testcases generation has been completed successfully.\n Testcases are stored at "+ fileName+ "\n Thank you.");		
	}

	private List<String> generateTestcases(List<ParameterTypes> parameterTypeLst) {
		// TODO Auto-generated method stub
		List<String> tstcases = new ArrayList();
		
		for (ParameterTypes parameterTypes : parameterTypeLst) {
			Class<?> param = parameterTypes.getParameterType();
			String tstCse = "";
			//System.out.println("param.getClass().getName():"+parameterTypes.getParameterType());
			
			//System.out.println("param.getClass().getName():"+param.getClass().getName());
			
			if(param.toString().compareTo("class java.lang.String")==0){
				tstCse = "( "+ param.getClass().getName() + Constants.EQUALS + "\"\""+ ") ";
				tstCse = tstCse + "Validating the  empty string";
				tstcases.add(tstCse);
				
				tstCse = "( "+ param.getClass().getName() + Constants.EQUALS + Constants.NULL+ ") ";
				tstCse = tstCse + "Validating the  null string";
				tstcases.add(tstCse);
				
				tstCse = "( "+ param.getClass().getName() + Constants.EQUALS + "fkfjgfutrotrkemwmdlfopphy6gmdkwswjwig" + ") ";
				tstCse = tstCse + "Validating the  random string";
				tstcases.add(tstCse);
				
				tstCse = "( "+ param.getClass().getName() + Constants.EQUALS + "!@#fkf&*()%^&jig" + ") ";
				tstCse = tstCse + "Validating the string with special charactors";
				tstcases.add(tstCse);
			}else if(param.toString().compareTo("class java.lang.Character")==0 || param.toString().compareTo("char") == 0){
				tstCse = "( "+ param.getClass().getName() + Constants.EQUALS + "\"\""+ ") ";
				tstCse = tstCse + "Validating the  empty Character";
				tstcases.add(tstCse);
				
				tstCse = "( "+ param.getClass().getName() + Constants.EQUALS + Constants.NULL+ ") ";
				tstCse = tstCse + "Validating the  null Character";
				tstcases.add(tstCse);
				
				tstCse = "( "+ param.getClass().getName() + Constants.EQUALS + "f" + ") ";
				tstCse = tstCse + "Validating the  random string";
				tstcases.add(tstCse);
				
				tstCse = "( "+ param.getClass().getName() + Constants.EQUALS + "@" + ") ";
				tstCse = tstCse + "Validating the Character with special charactors";
				tstcases.add(tstCse);
			}else if(param.toString().compareTo("class java.lang.Integer")==0  || param.toString().compareTo("int") == 0){
				tstCse = "( "+ param.getClass().getName() + Constants.EQUALS + "0"+ ") ";
				tstCse = tstCse + "Validating the zero Integer";
				tstcases.add(tstCse);
				
				tstCse = "( "+ param.getClass().getName() + Constants.EQUALS + Constants.NULL+ ") ";
				tstCse = tstCse + "Validating the  null Integer";
				tstcases.add(tstCse);
				
				tstCse = "( "+ param.getClass().getName() + Constants.EQUALS + "56879544216" + ") ";
				tstCse = tstCse + "Validating the  random Integer";
				tstcases.add(tstCse);
				
				tstCse = "( "+ param.getClass().getName() + Constants.EQUALS + "!@#789&*()%^&458" + ") ";
				tstCse = tstCse + "Validating the Integer with special charactors";
				tstcases.add(tstCse);
			}else if(param.toString().compareTo("class java.lang.Double")==0  || param.toString().compareTo("double") == 0){
				tstCse = "( "+ param.getClass().getName() + Constants.EQUALS + "0D"+ ") ";
				tstCse = tstCse + "Validating the zero Double";
				tstcases.add(tstCse);
				
				tstCse = "( "+ param.getClass().getName() + Constants.EQUALS + Constants.NULL+ ") ";
				tstCse = tstCse + "Validating the null Double";
				tstcases.add(tstCse);
				
				tstCse = "( "+ param.getClass().getName() + Constants.EQUALS + "56879544216D" + ") ";
				tstCse = tstCse + "Validating the random Double";
				tstcases.add(tstCse);
				
				tstCse = "( "+ param.getClass().getName() + Constants.EQUALS + "!@#789&*()%^&458" + ") ";
				tstCse = tstCse + "Validating the Double with special charactors";
				tstcases.add(tstCse);
			}else if(param.toString().compareTo("class java.lang.Long")==0   || param.toString().compareTo("long") == 0){
				tstCse = "( "+ param.getClass().getName() + Constants.EQUALS + "0L"+ ") ";
				tstCse = tstCse + "Validating the zero Long";
				tstcases.add(tstCse);
				
				tstCse = "( "+ param.getClass().getName() + Constants.EQUALS + Constants.NULL+ ") ";
				tstCse = tstCse + "Validating the null Long";
				tstcases.add(tstCse);
				
				tstCse = "( "+ param.getClass().getName() + Constants.EQUALS + "56879544216L" + ") ";
				tstCse = tstCse + "Validating the random Long";
				tstcases.add(tstCse);
				
				tstCse = "( "+ param.getClass().getName() + Constants.EQUALS + "!@#789&*()%^&458" + ") ";
				tstCse = tstCse + "Validating the Long with special charactors";
				tstcases.add(tstCse);
			}else if(param.toString().compareTo("class java.lang.Boolean")==0   || param.toString().compareTo("boolean") == 0){
				
				tstCse = "( "+ param.getClass().getName() + Constants.EQUALS + Constants.NULL+ ") ";
				tstCse = tstCse + "Validating the null Boolean";
				tstcases.add(tstCse);
				
				tstCse = "( "+ param.getClass().getName() + Constants.EQUALS + "false" + ") ";
				tstCse = tstCse + "Validating the random Boolean";
				tstcases.add(tstCse);
				
			}else {
				tstCse = "( "+ param.getClass().getName() + Constants.EQUALS + Constants.NULL+ ") ";
				tstCse = tstCse + "Validating the null with "+ param.toString() +"class.";
				tstcases.add(tstCse);
			}
			
		}
		return tstcases;
	}
}
