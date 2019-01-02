package com.mindtree.bankapp.service.serviceImplementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import com.mindtree.bankapp.dao.BankDao;
import com.mindtree.bankapp.dao.DaoImpl.BankDaoImpl;
import com.mindtree.bankapp.entity.Bank;
import com.mindtree.bankapp.exceptions.daoExceptions.BankDaoException;
import com.mindtree.bankapp.exceptions.serviceExceptions.BankServiceException;
import com.mindtree.bankapp.service.bankServiceInterface;

public class bankServiceImpl implements bankServiceInterface {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

	static BankDao BD = new BankDaoImpl();

	@Override
	public String addBank(Bank bank) throws BankServiceException {

		String msg = null;
		try {
			msg = BD.addBankDetails(bank);
		} catch (BankDaoException e) {
			throw new BankServiceException(e.getMessage(),e.getCause());
		}

		return msg;
	}

	@Override
	public Bank fetchBank(int id) throws BankServiceException {

		try {
			Bank resBank = BD.getBankDetailById(id);
			return resBank;
		} catch (BankDaoException e) {
			throw new BankServiceException(e.getMessage(),e.getCause());
		}
	}

	public List<Bank> fetchAllBanks() throws BankServiceException {

		List<Bank> resBank = new LinkedList<>();
		try {
			resBank = BD.getAllBankDetails();

		}catch (BankDaoException e) {
			throw new BankServiceException(e.getMessage(),e.getCause());
		}
		return resBank;
	}

	@Override
	public String changeBankRecords(Bank updBank) throws BankServiceException {

		try {
			String str = BD.updateBank(updBank);
			return str;
		} catch (BankDaoException e) {
			throw new BankServiceException(e.getMessage(),e.getCause());
		}
	}

}
