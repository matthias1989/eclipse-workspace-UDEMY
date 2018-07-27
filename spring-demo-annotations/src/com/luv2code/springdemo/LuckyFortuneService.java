package com.luv2code.springdemo;

public class LuckyFortuneService implements FortuneService {

	@Override
	public String getFortune() {
		return "Luck awaits you!";
	}

}
