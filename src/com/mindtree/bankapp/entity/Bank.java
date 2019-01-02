package com.mindtree.bankapp.entity;

public class Bank {

	private int bankCode;
	private String name;
	private String ifsc;
	private String branch;

	public Bank() {
	}

	public int getBankCode() {
		return this.bankCode;
	}

	public void setBankCode(int bankCode) {
		this.bankCode = bankCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	
}
