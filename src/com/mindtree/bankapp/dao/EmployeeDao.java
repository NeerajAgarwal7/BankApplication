package com.mindtree.bankapp.dao;

import java.util.List;

import com.mindtree.bankapp.entity.Employee;
import com.mindtree.bankapp.exceptions.daoExceptions.BankDaoException;

public interface EmployeeDao {
	
	String addEmployeeDetails(Employee employee) throws  BankDaoException;
	Employee getEmployeeDetailById(int empId) throws BankDaoException ;
	List<Employee> getAllEmployeeDetails() throws BankDaoException ;
	String updateEmployee(Employee updEmployee) throws BankDaoException ;
	List<Employee> getEmployeeByDepartment(int code,String dept) throws BankDaoException ;
	
}
