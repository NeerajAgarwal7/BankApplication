package com.mindtree.bankapp.service;

import java.util.List;

import com.mindtree.bankapp.entity.Employee;
import com.mindtree.bankapp.exceptions.serviceExceptions.BankServiceException;

public interface employeeServiceInterface {

	String addEmployee(Employee emp) throws BankServiceException;
	Employee fetchEmployee(int id) throws BankServiceException;
	List<Employee> fetchAllEmployee() throws BankServiceException;
	String changeEmployeeRecords(Employee updEmployee) throws BankServiceException;
	List<Employee> getEmployeesByDept(int code, String dept) throws BankServiceException;
}
