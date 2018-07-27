package com.luv2code.springdemo;

import org.springframework.stereotype.Component;

@Component("Coachie")
public class BasketballCoach implements Coach {

	@Override
	public String getDailyWorkout() {
		return "Train on your shootouts";
	}

	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return null;
	}

}
