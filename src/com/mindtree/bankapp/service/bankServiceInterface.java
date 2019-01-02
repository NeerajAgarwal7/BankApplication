package com.mindtree.bankapp.service;

import java.util.List;

import com.mindtree.bankapp.entity.Bank;
import com.mindtree.bankapp.exceptions.serviceExceptions.BankServiceException;

public interface bankServiceInterface {
	
	String addBank(Bank bank) throws BankServiceException;
	Bank fetchBank(int id) throws BankServiceException;
	List<Bank> fetchAllBanks() throws BankServiceException;
	String changeBankRecords(Bank upBank) throws BankServiceException;
	
}
