package com.mindtree.bankapp.service.serviceImplementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import com.mindtree.bankapp.dao.EmployeeDao;
import com.mindtree.bankapp.dao.DaoImpl.EmployeeDaoImpl;
import com.mindtree.bankapp.entity.Employee;
import com.mindtree.bankapp.exceptions.daoExceptions.BankDaoException;
import com.mindtree.bankapp.exceptions.serviceExceptions.BankServiceException;
import com.mindtree.bankapp.service.employeeServiceInterface;

public class employeeServiceImpl implements employeeServiceInterface {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

	static EmployeeDao ED = new EmployeeDaoImpl();

	@Override
	public String addEmployee(Employee employee) throws BankServiceException {
		String msg = null;
		try {
			msg = ED.addEmployeeDetails(employee);
		} catch (BankDaoException e) {
			throw new BankServiceException(e.getMessage(), e.getCause());
		}
		return msg;
	}

	@Override
	public Employee fetchEmployee(int id) throws BankServiceException {
		Employee resEmployee = new Employee();
		try {
			resEmployee = ED.getEmployeeDetailById(id);
		} catch (BankDaoException e) {
			throw new BankServiceException(e.getMessage(), e.getCause());
		}
		return resEmployee;
	}

	@Override
	public List<Employee> fetchAllEmployee() throws BankServiceException {
		List<Employee> resEmployee = new LinkedList<>();
		try {
			resEmployee = ED.getAllEmployeeDetails();
		} catch (BankDaoException e) {
			throw new BankServiceException(e.getMessage(), e.getCause());
		}
		return resEmployee;
	}

	@Override
	public String changeEmployeeRecords(Employee updEmployee) throws BankServiceException {

		try {
			return ED.updateEmployee(updEmployee);
		} catch (BankDaoException e) {
			throw new BankServiceException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public List<Employee> getEmployeesByDept(int code, String dept) throws BankServiceException {
		List<Employee> resEmployee = new LinkedList<>();
		try {
			resEmployee = ED.getEmployeeByDepartment(code, dept);
		}catch (BankDaoException e) {
			throw new BankServiceException(e.getMessage(),e.getCause());
		}
		return resEmployee;
	}
}
