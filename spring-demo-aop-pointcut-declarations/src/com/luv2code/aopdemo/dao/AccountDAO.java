package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Component
public class AccountDAO {
	
	public void addAccount(Account theAccount, Boolean vipFlag) {
		System.out.println(getClass() + ": doing my DB work: ADDING AN ACCOUNT");
	}
	
	public Boolean doWork() {
		System.out.println(getClass() + ": doWork");
		return false;
	}
}
