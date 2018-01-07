package com.droozhbooking.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@EnableAutoConfiguration	
public class HomeController {
	
	@RequestMapping("/")
	String home() {
		 return ""+
"<HTML>"+
"<HEAD>"+
"<TITLE>Home | droozhbooking.com</TITLE>"+
"</HEAD>"+
"<BODY>"+
"<H1>This is DroozhBooking.com</H1>"+
"<H2>Check another controller below</H2>"+
"<a href='/droozhbooking.com/api/v1/address/id/1'>Check Address entety #1</a>"+
"<br>all are good :)"+
"</BODY>"+
"</HTML>";
		 }

}
