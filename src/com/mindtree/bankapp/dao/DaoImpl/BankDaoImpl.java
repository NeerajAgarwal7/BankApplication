package com.mindtree.bankapp.dao.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.mindtree.bankapp.dao.BankDao;
import com.mindtree.bankapp.entity.Bank;
import com.mindtree.bankapp.exceptions.daoExceptions.BankDaoException;
import com.mindtree.bankapp.util.DbUtil;

public class BankDaoImpl implements BankDao {

	Connection con = null;

	public String addBankDetails(Bank bank) throws BankDaoException {

		String query = "insert into bank values(?,?,?,?)";

		con = DbUtil.getConnection();

		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, bank.getBankCode());
			ps.setString(2, bank.getName());
			ps.setString(3, bank.getIfsc());
			ps.setString(4, bank.getBranch());

			int x = ps.executeUpdate();
			con.close();

			if (x == 1)
				return bank.getName() + " details added!!";
			else
				return "Could not add bank details";
		} catch (SQLException e) {
			throw new BankDaoException("Cannot add bank data", e);
		}
	}

	@Override
	public Bank getBankDetailById(int id) throws BankDaoException {

		List<Bank> resBank = getAllBankDetails().stream().filter(i -> i.getBankCode() == id)
				.collect(Collectors.toList());
		if (resBank.isEmpty())
			return null;
		return resBank.get(0);
	}

	@Override
	public List<Bank> getAllBankDetails() throws BankDaoException {
		List<Bank> resultBank = new LinkedList<>();
		con = DbUtil.getConnection();
		String query = "Select * from Bank";
		try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {

			while (rs.next()) {
				Bank x = new Bank();
				x.setBankCode(rs.getInt(1));
				x.setName(rs.getString(2));
				x.setIfsc(rs.getString(3));
				x.setBranch(rs.getString(4));
				resultBank.add(x);
			}
			con.close();
		} catch (SQLException e) {
			throw new BankDaoException("Cannot fetch bank data",e);
		}

		return resultBank;
	}

	@Override
	public String updateBank(Bank updateBank) throws BankDaoException {

		con = DbUtil.getConnection();
		String st = "Update bank set name=?,ifsc=?,branch=? where bankcode=?";
		try (PreparedStatement ps = con.prepareStatement(st)) {
			ps.setString(1, updateBank.getName());
			ps.setString(2, updateBank.getIfsc());
			ps.setString(3, updateBank.getBranch());
			ps.setInt(4, updateBank.getBankCode());

			int x = ps.executeUpdate();
			con.close();

			if (x == 1)
				return "Bank details updated successfully";
			else
				return "bank with code = " + updateBank.getBankCode() + " does not exist";
		} catch (SQLException e) {
			throw new BankDaoException("Cannot update data",e);
		}
	}

}
