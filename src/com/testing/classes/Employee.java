package com.testing.classes;

import java.util.Date;

public class Employee {
	
	EmployeeSalary employeeSalary =new EmployeeSalary();
	
	public void getSalary(String empName) {
		// TODO Auto-generated method stub

		employeeSalary.getSalary("1");
		employeeSalary.getSalary(empName);
	}

	public void getAddress(String empName) {
		// TODO Auto-generated method stub

	}
	
	public void getProfile(int empId, Date doj) {
		// TODO Auto-generated method stub

	}
	
	public void getEmpName() {
		// TODO Auto-generated method stub

	}
	
	
}
