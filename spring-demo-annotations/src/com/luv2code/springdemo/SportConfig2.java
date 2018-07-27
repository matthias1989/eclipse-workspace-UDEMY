package com.luv2code.springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig2 {
	
	@Bean
	public FortuneService luckyFortuneService() {
		return new LuckyFortuneService();
	}
	
	@Bean
	public Coach baseballCoach() {
		return new BaseballCoach(luckyFortuneService());
	}
}
