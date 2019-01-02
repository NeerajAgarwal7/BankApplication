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
import com.mindtree.bankapp.dao.EmployeeDao;
import com.mindtree.bankapp.entity.Employee;
import com.mindtree.bankapp.exceptions.daoExceptions.BankDaoException;
import com.mindtree.bankapp.util.DbUtil;

public class EmployeeDaoImpl implements EmployeeDao {

	Connection con = null;
	static BankDao BD = new BankDaoImpl();

	@Override
	public String addEmployeeDetails(Employee employee) throws BankDaoException {

		String query = "insert into employee values(?,?,?,?,?,?,?,?)";
		con = DbUtil.getConnection();

		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, employee.getEmpId());
			ps.setString(2, employee.getName());
			ps.setDouble(3, employee.getSalary());
			ps.setString(4, employee.getDepartment());
			ps.setString(5, employee.getDesignation());
			ps.setString(6, employee.getGender());
			ps.setString(7, employee.getDateOfJoining());
			ps.setInt(8, employee.getBank().getBankCode());

			int x = ps.executeUpdate();

			if (x == 1)
				return employee.getName() + " details added!!";
			else
				return "Could not add employee details";
		} catch (SQLException e) {
			throw new BankDaoException("Cannot add data", e);
		}
	}

	@Override
	public Employee getEmployeeDetailById(int empId) throws BankDaoException {
		List<Employee> resultEmp = getAllEmployeeDetails().stream().filter(i -> i.getEmpId() == empId)
				.collect(Collectors.toList());
		return resultEmp.get(0);
	}

	@Override
	public List<Employee> getAllEmployeeDetails() throws BankDaoException {
		List<Employee> resultEmployee = new LinkedList<>();
		con = DbUtil.getConnection();
		String query = "Select * from Employee";
		try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {

			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmpId(rs.getInt(1));
				emp.setName(rs.getString(2));
				emp.setSalary(rs.getDouble(3));
				emp.setDepartment(rs.getString(4));
				emp.setDesignation(rs.getString(5));
				emp.setGender(rs.getString(6));
				emp.setDateOfJoining(rs.getString(7));
				emp.setBank(BD.getBankDetailById(rs.getInt(8)));
				resultEmployee.add(emp);
			}
		} catch (SQLException e) {
			throw new BankDaoException("Cannot fetch data", e);
		}

		return resultEmployee;
	}

	@Override
	public String updateEmployee(Employee updEmployee) throws BankDaoException {

		con = DbUtil.getConnection();
		String st = "Update Employee set salary=?,department=?,designation=? where Emp_id=?";
		try (PreparedStatement ps = con.prepareStatement(st)) {
			ps.setDouble(1, updEmployee.getSalary());
			ps.setString(2, updEmployee.getDepartment());
			ps.setString(3, updEmployee.getDesignation());
			ps.setInt(4, updEmployee.getEmpId());

			int x = ps.executeUpdate();
			if (x == 1)
				return "Employee details updated successfully";
			else
				return "Employee with code = " + updEmployee.getEmpId() + " does not exist";
		} catch (SQLException e) {
			throw new BankDaoException("Cannot update data", e);
		}
	}

	@Override
	public List<Employee> getEmployeeByDepartment(int code, String dept) throws BankDaoException {

		List<Employee> resultEmployee = new LinkedList<>();
		con = DbUtil.getConnection();

		String query = "Select * from Employee where bank=" + code + " and department='" + dept + "'";
		try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {

			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmpId(rs.getInt(1));
				emp.setName(rs.getString(2));
				emp.setSalary(rs.getDouble(3));
				emp.setDepartment(rs.getString(4));
				emp.setDesignation(rs.getString(5));
				emp.setGender(rs.getString(6));
				emp.setDateOfJoining(rs.getString(7));
				emp.setBank(BD.getBankDetailById(rs.getInt(8)));
				resultEmployee.add(emp);
			}
		} catch (SQLException e) {
			throw new BankDaoException("Cannot fetch data",e);
		}
		return resultEmployee;
	}
}