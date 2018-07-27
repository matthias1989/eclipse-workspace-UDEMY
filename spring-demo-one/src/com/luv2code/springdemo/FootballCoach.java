package com.luv2code.springdemo;

public class FootballCoach implements Coach {
	
	private FortuneService fortuneService;

	
	public FootballCoach(FortuneService fortuneService) {
		System.out.println("FootballCoach: inside arg constructor");
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Show me your best trick";
	}

	@Override
	public String getDailyFortune() {
		return "Go for it: " + fortuneService.getFortune();
	}

}