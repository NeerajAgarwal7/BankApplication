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
import com.mindtree.bankapp.dao.CustomerDao;
import com.mindtree.bankapp.entity.Customer;
import com.mindtree.bankapp.exceptions.daoExceptions.BankDaoException;
import com.mindtree.bankapp.util.DbUtil;

public class CustomerDaoImpl implements CustomerDao {

	Connection con = null;
	static BankDao BD = new BankDaoImpl();

	@Override
	public String addCustomerDetails(Customer customer) throws BankDaoException {
		String query = "insert into customer values(?,?,?,?,?,?,?,?,?,?,?)";
		con = DbUtil.getConnection();

		try (PreparedStatement ps = con.prepareStatement(query)) {

			ps.setLong(1, customer.getAccountNumber());
			ps.setString(2, customer.getName());
			ps.setString(3, customer.getGender());
			ps.setString(4, customer.getLocation());
			ps.setString(5, customer.getPanId());
			ps.setString(6, customer.getDateOfBirth());
			ps.setString(7, customer.getCreatedOn());
			ps.setLong(8, customer.getPhoneNumber());
			ps.setInt(9, customer.getBank().getBankCode());
			ps.setInt(10, customer.getAge());
			ps.setDouble(11, customer.getBalance());

			int x = ps.executeUpdate();

			if (x == 1)
				return customer.getName() + " details added!!";
			else
				return "Could not add customer details";
		} catch (SQLException e) {
			throw new BankDaoException("Cannot add data", e);
		}

	}

	@Override
	public Customer getCustomerDetailById(int acc_no) throws BankDaoException {

		List<Customer> resultCus = getAllCustomerDetails().stream().filter(i -> i.getAccountNumber() == acc_no)
				.collect(Collectors.toList());
		return resultCus.get(0);
	}

	@Override
	public List<Customer> getAllCustomerDetails() throws BankDaoException {
		List<Customer> resultCustomer = new LinkedList<>();
		con = DbUtil.getConnection();
		String query = "Select * from Customer";
		try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {

			while (rs.next()) {
				Customer cus = new Customer();
				cus.setAccountNumber(rs.getInt(1));
				cus.setName(rs.getString(2));
				cus.setGender(rs.getString(3));
				cus.setLocation(rs.getString(4));
				cus.setPanId(rs.getString(5));
				cus.setDateOfBirth(rs.getString(6));
				cus.setCreatedOn(rs.getString(7));
				cus.setPhoneNumber(rs.getLong(8));
				cus.setBank(BD.getBankDetailById(rs.getInt(9)));
				cus.setAge(rs.getInt(10));
				cus.setBalance(rs.getDouble(11));
				resultCustomer.add(cus);
			}
		} catch (SQLException e) {
			throw new BankDaoException("Cannot fetch data", e);
		}
		return resultCustomer;
	}

	@Override
	public String updateCustomer(Customer updCustomer) throws BankDaoException {
		con = DbUtil.getConnection();
		String st = "Update customer set location=?,phnum=?,balance=? where account_number=?";
		try (PreparedStatement ps = con.prepareStatement(st)) {
			ps.setString(1, updCustomer.getLocation());
			ps.setLong(2, updCustomer.getPhoneNumber());
			ps.setDouble(3, updCustomer.getBalance());
			ps.setLong(4, updCustomer.getAccountNumber());

			int x = ps.executeUpdate();
			if (x == 1)
				return "Customer details updated successfully";
			else
				return "Customer with Account Number = " + updCustomer.getAccountNumber() + " does not exist";
		} catch (SQLException e) {
			throw new BankDaoException("Cannot update data", e);
		}
	}

	@Override
	public Customer getHighBankBalance() throws BankDaoException {
		Customer resultCus = new Customer();
		con = DbUtil.getConnection();
		String query = "Select account_number from customer where balance=(select max(balance) from customer)";
		try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {

			if (rs.next()) {
				resultCus = getCustomerDetailById(rs.getInt(1));
			}
			return resultCus;
		} catch (SQLException e) {
			throw new BankDaoException("Cannot retrive data", e);
		}
	}

	@Override
	public String genderRation() throws BankDaoException {

		double males = 0;
		double females = 0;
		double total = 0;
		con = DbUtil.getConnection();
		String query = "Select count(account_number) from Customer group by gender order by gender";
		try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {

			if (rs.next()) {
				females = rs.getInt(1);
			}
			if (rs.next()) {
				males = rs.getInt(1);
			}
			total = males + females;

		} catch (SQLException e) {
			throw new BankDaoException("Cannot fetch data", e);
		}
		if (total != 0)
			return "Male % = " + ((males / total) * 100) + "\nFemale % = " + ((females / total) * 100);
		return null;
	}

}
