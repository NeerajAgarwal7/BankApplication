package com.mindtree.bankapp.MainApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.mindtree.bankapp.entity.Bank;
import com.mindtree.bankapp.entity.Customer;
import com.mindtree.bankapp.entity.Employee;
import com.mindtree.bankapp.exceptions.BankAppException;
import com.mindtree.bankapp.exceptions.serviceExceptions.BankServiceException;
import com.mindtree.bankapp.service.bankServiceInterface;
import com.mindtree.bankapp.service.customerServiceInterface;
import com.mindtree.bankapp.service.employeeServiceInterface;
import com.mindtree.bankapp.service.serviceImplementation.bankServiceImpl;
import com.mindtree.bankapp.service.serviceImplementation.customerServiceImpl;
import com.mindtree.bankapp.service.serviceImplementation.employeeServiceImpl;
import com.mindtree.bankapp.util.dataRWutil;

public class MainApp {

	static bankServiceInterface BI = new bankServiceImpl();
	static employeeServiceInterface EI = new employeeServiceImpl();
	static customerServiceInterface CI = new customerServiceImpl();
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws BankAppException {

		System.out.println("*******************************************************************");
		System.out.println("*********************<><><>WELCOME<><><>***************************");
		System.out.println("*******************************************************************");
		while (true) {
			System.out.println("1 - Admin\n2 - User\n3 - Exit");
			System.out.println("Enter your choice");
			int choice = 0;
			try {
				choice = Integer.parseInt(bf.readLine());
			} catch (NumberFormatException e) {
				throw new BankAppException("Enter valid data", e);
			} catch (IOException e) {
				throw new BankAppException("connot read data", e);
			}
			switch (choice) {
			case 1:
				AdminMenu();
				break;
			case 2:
				UserMenu();
				break;
			case 3:
				System.out.println("Bye...");
				System.exit(0);
			default:
				System.out.println("Invalid Input");
			}
		}
	}

	private static void UserMenu() throws BankAppException {

		System.out.println("*******************************************************************");
		System.out.println("**************************WELCOME USER*****************************");
		System.out.println("*******************************************************************");

		while (true) {
			System.out.println("1 - Get details of customer with highest bank balance\n"
					+ "2 - Get gender ratio of employees\n" + "3 - Get Employee details of a bank by branch\n"
					+ "4 - Go to previous menu\n" + "5 - Exit");
			int choice = 0;
			try {
				choice = Integer.valueOf(bf.readLine());
			} catch (NumberFormatException e) {
				throw new BankAppException("Enter valid data", e);
			} catch (IOException e) {
				throw new BankAppException("cannot read data", e);
			}

			switch (choice) {
			case 1:
				Customer resCus;
				try {
					resCus = CI.highBalance();
					System.out.format("%-16s%-16s%-16s%-16s%-16s%-16s%-16s%-16s%-16s%-16s%-16s", "acc_no", "Name",
							"Gender", "Location", "PAN", "DOB", "Created_on", "Ph_Num", "Bank code", "Age",
							"Bank Balance");
					System.out.println("");
					System.out.println(
							"********************************************************************************************************************************************************************************");
					dataRWutil.displayCustomer(resCus);
					System.out.println(
							"********************************************************************************************************************************************************************************");
					break;
				} catch (BankServiceException e1) {
					System.out.println(e1.getMessage() + "\n" + e1.getCause());
				}

			case 2:
				try {
					System.out.println(CI.getGenderRatio());
				} catch (BankServiceException e1) {
					System.out.println(e1.getMessage() + "\n" + e1.getCause());
				}
				break;

			case 3:
				try {
					System.out.print("Enter bank code : ");
					int code = Integer.parseInt(bf.readLine());
					System.out.print("Enter department name : ");
					String dept = bf.readLine();

					List<Employee> resEmployee = EI.getEmployeesByDept(code, dept);
					if (!resEmployee.isEmpty())
						dataRWutil.displayEmployeeList(resEmployee);
					else
						System.out.println("No data available for the given input");

				} catch (NumberFormatException e) {
					throw new BankAppException("Enter valid data", e);
				} catch (IOException e) {
					throw new BankAppException("Cannot read data", e);
				} catch (BankServiceException e) {
					System.out.println(e.getMessage() + "\n" + e.getCause());
				}
				break;

			case 4:
				return;

			case 5:
				System.out.println("Bye...");
				System.exit(0);

			default:
				System.out.println("Inalid input!");
			}
		}

	}

	private static void AdminMenu() throws BankAppException {
		System.out.println("*******************************************************************");
		System.out.println("************************WELCOME ADMIN******************************");
		System.out.println("*******************************************************************");

		while (true) {
			System.out.println(
					"1 - Bank data handling\n2 - Employee data handling\n3 - Customer data handling\n4 - Go back to previous menu\n5 - Exit");
			System.out.println("Enter your choice");

			int choice = 0;
			try {
				choice = Integer.parseInt(bf.readLine());
			} catch (NumberFormatException e) {
				throw new BankAppException("Enter valid data", e);
			} catch (IOException e) {
				throw new BankAppException("Cannot read data", e);
			}

			switch (choice) {
			case 1:
				BankMenu();
				break;
			case 2:
				EmployeeMenu();
				break;
			case 3:
				CustomerMenu();
				break;
			case 4:
				return;
			case 5:
				System.out.println("Bye...");
				System.exit(0);
			default:
				System.out.println("Invalid Input");
			}
		}
	}

	private static void CustomerMenu() throws BankAppException {

		System.out.println("*******************************************************************");
		System.out.println("**********************CUSTOMER INTERFACE***************************");
		System.out.println("*******************************************************************");
		while (true) {
			System.out.println(
					"1 - add Customer\n2 - fetch Customer by acc_no\n3 - fetch all Customer records\n4 - update Customer records\n5 - Go to previous menu\n6 - Exit");
			System.out.println("Enter your choice");

			int choice = 0;
			try {
				choice = Integer.parseInt(bf.readLine());
			} catch (NumberFormatException e) {
				throw new BankAppException("Enter valid data", e);
			} catch (IOException e) {
				throw new BankAppException("Cannot read data", e);
			}

			switch (choice) {
			case 1:
				Customer customer;
				try {
					customer = dataRWutil.readCustomerData();
					System.out.println(CI.addCustomer(customer));
					break;
				} catch (BankServiceException e) {
					System.out.println(e.getMessage() + "\n" + e.getCause());
				} catch (BankAppException e2) {
					System.out.println(e2.getMessage() + "\n" + e2.getCause());
				}

			case 2:
				try {
					System.out.print("Enter Customer Account no : ");
					int id = Integer.valueOf(bf.readLine());
					Customer resCustomer = CI.fetchCustomer(id);

					System.out.format("%-16s%-16s%-16s%-16s%-16s%-16s%-16s%-16s%-16s%-16s%-16s", "acc_no", "Name",
							"Gender", "Location", "PAN", "DOB", "Created_on", "Ph_Num", "Bank code", "Age",
							"Bank Balance");
					System.out.println("");
					System.out.println(
							"********************************************************************************************************************************************************************************");
					dataRWutil.displayCustomer(resCustomer);
					System.out.println(
							"********************************************************************************************************************************************************************************");
				} catch (NumberFormatException e) {
					throw new BankAppException("Enter valid data", e);
				} catch (IOException e) {
					throw new BankAppException("Cannot read data", e);
				} catch (BankServiceException e) {
					System.out.println(e.getMessage() + "\n" + e.getCause());
				}
				break;

			case 3:
				List<Customer> resCustomer;
				try {
					resCustomer = CI.fetchAllCustomer();
					dataRWutil.displayCustomerList(resCustomer);
					break;
				} catch (BankServiceException e) {
					System.out.println(e.getMessage() + "\n" + e.getCause());
				}

			case 4:
				try {
					Customer updCustomer = new Customer();
					System.out.print("Enter acc_num of Customer whose details have to be changed : ");
					updCustomer.setAccountNumber(Long.parseLong(bf.readLine()));
					System.out.print("Enter location : ");
					updCustomer.setLocation(bf.readLine());
					System.out.print("Enter Pnhone num");
					updCustomer.setPhoneNumber(Long.parseLong(bf.readLine()));
					System.out.print("Enter bank balance : ");
					updCustomer.setBalance(Double.parseDouble(bf.readLine()));
					System.out.println(CI.changeCustomerRecords(updCustomer));
				} catch (NumberFormatException e) {
					throw new BankAppException("Enter valid data", e);
				} catch (IOException e) {
					throw new BankAppException("Cannot read data", e);
				} catch (BankServiceException e) {
					System.out.println(e.getMessage() + "\n" + e.getCause());
				}
				break;

			case 5:
				return;

			case 6:
				System.out.println("Bye...");
				System.exit(0);

			default:
				System.out.println("Enter valid choice");
			}
		}
	}

	private static void EmployeeMenu() throws BankAppException {
		System.out.println("*******************************************************************");
		System.out.println("**********************EMPLOYEE INTERFACE***************************");
		System.out.println("*******************************************************************");

		while (true) {
			System.out.println(
					"1 - add Employee\n2 - fetch Employee by emp_id\n3 - fetch all Employee records\n4 - update Employee records\n5 - Go to previous menu\n6 - Exit");
			System.out.println("Enter your choice");

			int choice = 0;
			try {
				choice = Integer.parseInt(bf.readLine());
			} catch (NumberFormatException e) {
				throw new BankAppException("Enter valid data", e);
			} catch (IOException e) {
				throw new BankAppException("Cannot read data", e);
			}

			switch (choice) {
			case 1:
				Employee emp;
				try {
					emp = dataRWutil.readEmployeeData();
					System.out.println(EI.addEmployee(emp));
					break;
				} catch (BankAppException e2) {
					System.out.println(e2.getMessage() + "\n" + e2.getCause());
				}

			case 2:
				System.out.println("Enter Employee Id : ");
				try {
					int id = Integer.parseInt(bf.readLine());
					Employee resEmployee = EI.fetchEmployee(id);

					System.out.format("%-16s%-16s%-16s%-16s%-16s%-16s%-16s%-16s", "Emp_id", "Name", "Salary",
							"Department", "Designation", "Gender", "DOJ", "Bank code");
					System.out.println("");
					System.out.println(
							"*****************************************************************************************************************************");
					dataRWutil.displayEmployee(resEmployee);
					System.out.println(
							"*****************************************************************************************************************************");

				} catch (NumberFormatException e) {
					throw new BankAppException("Enter valid data", e);
				} catch (IOException e) {
					throw new BankAppException("Cannot read data", e);
				} catch (BankServiceException e) {
					System.out.println(e.getMessage() + "\n" + e.getCause());
				}
				break;

			case 3:
				List<Employee> resEmployee;
				try {
					resEmployee = EI.fetchAllEmployee();
					dataRWutil.displayEmployeeList(resEmployee);
					break;
				} catch (BankServiceException e2) {
					System.out.println(e2.getMessage() + "\n" + e2.getCause());
				}

			case 4:
				try {
					Employee updEmployee = new Employee();
					System.out.print("Enter the Employee ID of employee whose details have to be updated");
					updEmployee.setEmpId(Integer.parseInt(bf.readLine()));
					System.out.print("Enter salary : ");
					updEmployee.setSalary(Double.parseDouble(bf.readLine()));
					System.out.print("Enter department : ");
					updEmployee.setDepartment(bf.readLine());
					System.out.print("Enter designation");
					updEmployee.setDesignation(bf.readLine());

					EI.changeEmployeeRecords(updEmployee);
				} catch (NumberFormatException e) {
					throw new BankAppException("Enter valid data", e);
				} catch (IOException e) {
					throw new BankAppException("Cannot read data", e);
				} catch (BankServiceException e) {
					System.out.println(e.getMessage() + "\n" + e.getCause());
				}
				break;

			case 5:
				return;

			case 6:
				System.out.println("Bye...");
				System.exit(0);

			default:
				System.out.println("Enter valid choice");
			}
		}

	}

	static void BankMenu() throws BankAppException {
		System.out.println("*******************************************************************");
		System.out.println("************************BANK INTERFACE*****************************");
		System.out.println("*******************************************************************");

		while (true) {
			System.out.println(
					"1 - add bank\n2 - fetch bank by code\n3 - fetch all bank records\n4 - update bank records\n5 - Go to previous menu\n6 - Exit");
			System.out.println("Enter your choice");

			int choice = 0;
			try {
				choice = Integer.parseInt(bf.readLine());
			} catch (NumberFormatException e) {
				throw new BankAppException("Enter valid data", e);
			} catch (IOException e) {
				throw new BankAppException("Cannot read data", e);
			}

			switch (choice) {
			case 1:
				Bank bank;
				try {
					bank = dataRWutil.readBankData();
					System.out.println(BI.addBank(bank));
					break;
				} catch (BankAppException e1) {
					System.out.println(e1.getMessage() + "\n" + e1.getCause());
				}

			case 2:
				System.out.println("Enter bank code : ");
				try {
					int id = Integer.parseInt(bf.readLine());
					Bank resBank = BI.fetchBank(id);
					if (resBank != null) {
						System.out.format("%-16s%-16s%-16s%-16s", "Code", "Name", "IFSC", "Branch");
						System.out.println("");
						System.out.println("******************************************************************");
						dataRWutil.displayBank(resBank);
						System.out.println("******************************************************************");
					} else throw new NullPointerException();
				} catch (IOException e) {
					System.out.println("Cannot read data");
				} catch (NullPointerException e) {
					System.out.println("No data");
				} catch (BankServiceException e) {
					System.out.println(e.getMessage() + "\n" + e.getCause());
				}
				break;

			case 3:
				List<Bank> resBank;
				try {
					resBank = BI.fetchAllBanks();
					dataRWutil.displayBankList(resBank);
					break;
				} catch (BankServiceException e) {
					System.out.println(e.getMessage() + "\n" + e.getCause());
				}

			case 4:
				System.out.print("Enter code of bank whose data has to be updated");
				Bank upBank;
				try {
					upBank = dataRWutil.readBankData();
					System.out.println(BI.changeBankRecords(upBank));
					break;
				} catch (BankAppException e) {
					System.out.println(e.getMessage() + "\n" + e.getCause());
				}

			case 5:
				return;

			case 6:
				System.out.println("Bye...");
				System.exit(0);

			default:
				System.out.println("Enter valid choice");
			}
		}
	}
}