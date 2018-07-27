package com.luv2code.springdemo;

import java.util.Random;

public class RandomFortuneService implements FortuneService {
	
	private String[] fortunes = {"Today you'll have a great health", "Unexpected happiness will fall on you","Greatness awaits you"};
	private Random myRandom = new Random();

	@Override
	public String getFortune() {

		int index = myRandom.nextInt(fortunes.length);
		return fortunes[index];
	}

}
