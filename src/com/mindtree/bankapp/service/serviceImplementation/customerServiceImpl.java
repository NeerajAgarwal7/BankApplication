package com.mindtree.bankapp.service.serviceImplementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import com.mindtree.bankapp.dao.CustomerDao;
import com.mindtree.bankapp.dao.DaoImpl.CustomerDaoImpl;
import com.mindtree.bankapp.entity.Customer;
import com.mindtree.bankapp.exceptions.daoExceptions.BankDaoException;
import com.mindtree.bankapp.exceptions.serviceExceptions.BankServiceException;
import com.mindtree.bankapp.service.customerServiceInterface;

public class customerServiceImpl implements customerServiceInterface {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

	static CustomerDao CD = new CustomerDaoImpl();

	@Override
	public String addCustomer(Customer customer) throws BankServiceException {
		try {
			return CD.addCustomerDetails(customer);
		}catch (BankDaoException e) {
			throw new BankServiceException(e.getMessage(),e.getCause());
		}
	}

	@Override
	public Customer fetchCustomer(int id) throws BankServiceException {
		
		Customer resCustomer = new Customer();
		try {
			resCustomer = CD.getCustomerDetailById(id);
		}catch (BankDaoException e) {
			throw new BankServiceException(e.getMessage(),e.getCause());
		}

		return resCustomer;

	}

	@Override
	public List<Customer> fetchAllCustomer() throws BankServiceException {
		List<Customer> resCustomer = new LinkedList<>();
		try {
			resCustomer = CD.getAllCustomerDetails();
		}catch (BankDaoException e) {
			throw new BankServiceException(e.getMessage(),e.getCause());
		}
		return resCustomer;
	}

	@Override
	public String changeCustomerRecords(Customer updCustomer) throws BankServiceException {

		System.out.println("Enter acc_num of Customer whose details have to be changed : ");
		try {
			return CD.updateCustomer(updCustomer);
		}catch (BankDaoException e) {
			throw new BankServiceException(e.getMessage(),e.getCause());
		}
	}

	
	@Override
	public Customer highBalance() throws BankServiceException {
		
		try {
			Customer resCus = CD.getHighBankBalance();
			return resCus;	
		} catch (BankDaoException e) {
			throw new BankServiceException(e.getMessage(),e.getCause());
		}
	}

	@Override
	public String getGenderRatio() throws BankServiceException {
		try {
			return (CD.genderRation());
		}catch (BankDaoException e) {
			throw new BankServiceException(e.getMessage(),e.getCause());
		}
	}

}
