package com.luv2code.springdemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class FileFortuneService implements FortuneService {
	
	private List<String> data = new ArrayList<String>();
	private Random myRandom = new Random();
	private String fileName = "C:/Users/mattstro/eclipse-workspace-UDEMY/spring-demo-annotations/src/fortunes.txt";
	
	public FileFortuneService() {

	}
	
	// define my init method
	@PostConstruct
	public void doInit() {
		File file = new File(fileName);
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String tempLine;
			while((tempLine = br.readLine()) != null) {
				data.add(tempLine);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(data.size());
	}
	
	
	@Override
	public String getFortune() {
		int index = myRandom.nextInt(data.size());
		String myFortune = data.get(index);
		// TODO Auto-generated method stub
		return "FileFortuneService: "+myFortune;
	}

}
