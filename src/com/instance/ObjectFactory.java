package com.instance;

import java.util.ArrayList;
import java.util.List;

import com.design.MethodDesign;
import com.utils.ExportTestcasesToExcel;

public class ObjectFactory {
	AdequateSequenceOfMethods adequateSequenceOfMethods;
	FindingAdequateInstance findingAdequateInstance;
	List<Object> listOfObjects;
	MethodDesign methodDesign;
	ExportTestcasesToExcel exportTestcasesToExcel;
	
	public AdequateSequenceOfMethods createAdequateSequenceOfMethodsObject() {
		// TODO Auto-generated method stub
		return new AdequateSequenceOfMethods();
	}
	
	public FindingAdequateInstance createFindingAdequateInstanceObject() {
		// TODO Auto-generated method stub
		return new FindingAdequateInstance();
	}
	
	public ArrayList<Object> createListOfObjects() {
		// TODO Auto-generated method stub
		return new ArrayList<Object>();
	}
	
	public MethodDesign createMethodDesignObjects() {
		// TODO Auto-generated method stub
		return new MethodDesign();
	}

	public ExportTestcasesToExcel createExportTestcasesToExcelObjects() {
		// TODO Auto-generated method stub
		return new ExportTestcasesToExcel();
	}
}
