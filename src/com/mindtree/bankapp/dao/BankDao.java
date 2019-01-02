package com.mindtree.bankapp.dao;

import java.util.List;

import com.mindtree.bankapp.entity.Bank;
import com.mindtree.bankapp.exceptions.daoExceptions.BankDaoException;

public interface BankDao {

	String addBankDetails(Bank bank) throws BankDaoException;
	List<Bank> getAllBankDetails() throws BankDaoException;
	String updateBank(Bank updateBank) throws BankDaoException;
	Bank getBankDetailById(int bankCode) throws BankDaoException;
	
}
