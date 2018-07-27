package com.luv2code.springdemo.controller;

import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@RequestMapping("/")
	public String home(Device device) {
		if(device.isNormal()) {
			return "Device is NORMAL";
		}
		else if(device.isMobile()) {
			return "Device is MOBILE";
		}
		else if(device.isTablet()) {
			return "Device is TABLET";
		}
		else {
			return "Error: Device is not recognized";
		}
		
	}
}
