package com.testing.classes;

public class EmployeeSalary {

	private static String emp1 = "1";
	private static String emp2 = "2";
	private static String emp3 = "3";
	private static String emp4 = "4";
	private static String emp5 = "5";
	private static String emp6 = "6";
	
	
	
	public int getSalary(String empName){
		int sal = calculateSalary(empName);
		return sal;
	}



	private int calculateSalary(String empName) {
		// TODO Auto-generated method stub
		if(empName.equals(emp1))		return 25000;
		else if(empName.equals(emp2)) return 30000;
		else if(empName.equals(emp3)) return 35000;
		else if(empName.equals(emp4)) return 38000;
		else if(empName.equals(emp5)) return 42000;
		else if(empName.equals(emp6)) return 64000;
		else return 0;
	}
	
}
