package com.mindtree.bankapp.service;

import java.util.List;

import com.mindtree.bankapp.entity.Customer;
import com.mindtree.bankapp.exceptions.serviceExceptions.BankServiceException;

public interface customerServiceInterface {
	String addCustomer(Customer customer) throws BankServiceException;
	Customer fetchCustomer(int id) throws BankServiceException;
	List<Customer> fetchAllCustomer() throws BankServiceException;
	String changeCustomerRecords(Customer updCustomer) throws BankServiceException;
	Customer highBalance() throws BankServiceException;
	String getGenderRatio() throws BankServiceException;
}
