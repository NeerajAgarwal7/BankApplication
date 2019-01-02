package com.mindtree.bankapp.dao;

import java.util.List;

import com.mindtree.bankapp.entity.Customer;
import com.mindtree.bankapp.exceptions.daoExceptions.BankDaoException;

public interface CustomerDao {

	String addCustomerDetails(Customer customer) throws  BankDaoException;
	Customer getCustomerDetailById(int acc_no) throws  BankDaoException;
	List<Customer> getAllCustomerDetails() throws BankDaoException;
	String updateCustomer(Customer updCustomer) throws  BankDaoException;
	Customer getHighBankBalance() throws BankDaoException;
	String genderRation() throws  BankDaoException;
}
