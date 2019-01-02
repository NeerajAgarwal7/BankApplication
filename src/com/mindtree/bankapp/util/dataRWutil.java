package com.mindtree.bankapp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.mindtree.bankapp.dao.BankDao;
import com.mindtree.bankapp.dao.DaoImpl.BankDaoImpl;
import com.mindtree.bankapp.entity.Bank;
import com.mindtree.bankapp.entity.Customer;
import com.mindtree.bankapp.entity.Employee;
import com.mindtree.bankapp.exceptions.BankAppException;

public class dataRWutil {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static BankDao BD = new BankDaoImpl();

	public static Bank readBankData() throws BankAppException {

		Bank newBank = new Bank();
		try {
			newBank = new Bank();
			System.out.print("Enter bank code : ");
			newBank.setBankCode(Integer.parseInt(bf.readLine()));
			System.out.print("Enter bank name : ");
			newBank.setName(bf.readLine());
			System.out.print("Enter bank IFSC code : ");
			newBank.setIfsc(bf.readLine());
			System.out.println("Enter branch name : ");
			newBank.setBranch(bf.readLine());
		} catch (IOException e) {
			throw new BankAppException("Cannot read data",e);
		} catch (NumberFormatException e) {
			throw new BankAppException("Enter valid bank code",e);
		}
		return newBank;
	}

	public static void displayBank(Bank i) {
		System.out.format("%-16d%-16s%-16s%-16s", i.getBankCode(), i.getName(), i.getIfsc(), i.getBranch());
		System.out.println("");
	}

	public static void displayBankList(List<Bank> resBank) {
		System.out.format("%-16s%-16s%-16s%-16s", "Code", "Name", "IFSC", "Branch");
		System.out.println("");
		System.out.println("******************************************************************");
		resBank.forEach(i -> {displayBank(i);});
		System.out.println("******************************************************************");
	}

	public static Employee readEmployeeData() throws BankAppException {

		Employee newEmployee = null;
		try {
			newEmployee = new Employee();
			System.out.print("Enter Employee id : ");
			newEmployee.setEmpId(Integer.parseInt(bf.readLine()));
			System.out.print("Enter Employee name : ");
			newEmployee.setName(bf.readLine());
			System.out.print("Enter salary of employee : ");
			newEmployee.setSalary(Double.parseDouble(bf.readLine()));
			System.out.println("Enter Department name : ");
			newEmployee.setDepartment(bf.readLine());
			System.out.println("Enter employee designation : ");
			newEmployee.setDesignation(bf.readLine());
			System.out.println("Enter employee gender : ");
			newEmployee.setGender(bf.readLine());
			System.out.println("Enter date of joining : ");
			newEmployee.setDateOfJoining(bf.readLine());
			System.out.println("Enter employee's bank code : ");
			newEmployee.setBank(BD.getBankDetailById(Integer.valueOf(bf.readLine())));
		} catch (IOException e) {
			throw new BankAppException("Cannot read data",e);
		} catch (NumberFormatException e) {
			throw new BankAppException("Enter valid input!",e);
		}
		return newEmployee;
	}

	public static void displayEmployee(Employee i) {
		System.out.format("%-16d%-16s%-16f%-16s%-16s%-16s%-16s%-16d", i.getEmpId(), i.getName(), i.getSalary(),
				i.getDepartment(), i.getDesignation(), i.getGender(), i.getDateOfJoining(), i.getBank().getBankCode());
		System.out.println("");
	}

	public static void displayEmployeeList(List<Employee> resEmployee) {

		System.out.format("%-16s%-16s%-16s%-16s%-16s%-16s%-16s%-16s", "Emp_id", "Name", "Salary", "Department",
				"Designation", "Gender", "DOJ", "Bank code");
		System.out.println("");
		System.out.println(
				"*****************************************************************************************************************************");
		resEmployee.forEach(i -> {
			displayEmployee(i);
		});
		System.out.println(
				"*****************************************************************************************************************************");

	}

	public static Customer readCustomerData() throws BankAppException {
		Customer newCustomer = null;
		try {
			newCustomer = new Customer();
			System.out.print("Enter Customer id : ");
			newCustomer.setAccountNumber(Long.parseLong(bf.readLine()));
			System.out.print("Enter Customer name : ");
			newCustomer.setName(bf.readLine());
			System.out.print("Enter Customer gender : ");
			newCustomer.setGender((bf.readLine()));
			System.out.print("Enter Customer location : ");
			newCustomer.setLocation(bf.readLine());
			System.out.print("Enter Customer Pan ID : ");
			newCustomer.setPanId(bf.readLine());
			System.out.print("Enter customer DOB : ");
			newCustomer.setDateOfBirth(bf.readLine());
			System.out.print("Enter date of account creation : ");
			newCustomer.setCreatedOn(bf.readLine());
			System.out.print("Enter Customer's Phone no : ");
			newCustomer.setPhoneNumber(Long.parseLong(bf.readLine()));
			System.out.print("Enter customer's bank code : ");
			newCustomer.setBank(BD.getBankDetailById(Integer.valueOf(bf.readLine())));

			while (true) {
				System.out.print("Enter customer age : ");
				int age = Integer.parseInt(bf.readLine());
				if (age > 16 && age <= 100) {
					newCustomer.setAge(age);
					break;
				}else {
					System.out.println("age should be between 16 and 100");
					continue;
				}
			}

			System.out.print("Enter customer's bank balance : ");
			newCustomer.setBalance(Double.parseDouble(bf.readLine()));
		} catch (IOException e) {
			throw new BankAppException("Cannot read data",e);
		} catch (NumberFormatException e) {
			throw new BankAppException("Enter valid data",e);
		}
		return newCustomer;
	}

	public static void displayCustomer(Customer i) {
		System.out.format("%-16d%-16s%-16s%-16s%-16s%-16s%-16s%-16d%-16d%-16d%-16f", i.getAccountNumber(), i.getName(),
				i.getGender(), i.getLocation(), i.getPanId(), i.getDateOfBirth(), i.getCreatedOn(), i.getPhoneNumber(),
				i.getBank().getBankCode(), i.getAge(), i.getBalance());
		System.out.println("");
	}

	public static void displayCustomerList(List<Customer> resCustomer) {
		System.out.format("%-16s%-16s%-16s%-16s%-16s%-16s%-16s%-16s%-16s%-16s%-16s", "acc_no", "Name", "Gender",
				"Location", "PAN", "DOB", "Created_on", "Ph_Num", "Bank code", "Age", "Bank Balance");
		System.out.println("");
		System.out.println(
				"********************************************************************************************************************************************************************************");
		resCustomer.forEach(i -> {
			displayCustomer(i);
		});
		System.out.println(
				"********************************************************************************************************************************************************************************");
	}
}
