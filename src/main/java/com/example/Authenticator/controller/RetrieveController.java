package com.example.Authenticator.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Authenticator.exception.SessionExpiredException;
import com.example.Authenticator.model.RetrieveResponse;
import com.example.Authenticator.service.RetrieveService;

@RestController
@RequestMapping("/v1/")
public class RetrieveController {

	@Autowired
	public RetrieveService service;

	@GetMapping(value = "/user/messages", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> getMessages(
			@RequestParam(name = "sessionid") String sessionid,
			@RequestParam(name = "pagestart") int pagestart,
			@RequestParam(name = "limit") int limit) throws Exception {

		RetrieveResponse finalResponse = null;
		
		System.out.println("session id:" + sessionid);
		System.out.println("pagestart:" + pagestart);
		
		if (sessionCheck(sessionid)) {
			/*
			 * If session token is valid then we invoke service layer
			 */			
			finalResponse = service.getList(pagestart, limit);
		} else {
			
			throw new SessionExpiredException("Session expired please retry with valid token");
		}
		return new ResponseEntity<>(finalResponse, HttpStatus.OK);
	}

	/*
	 * This method will help in checking if  the session is expired or not
	 */
	private boolean sessionCheck(String sessionid) throws Exception {

		Calendar date = Calendar.getInstance();
		long timeInSecs = date.getTimeInMillis();
		Date currDate = new Date(timeInSecs);
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMddhhmmssMs");
		String currSessionId = formatter.format(currDate);
		Date datecurr;
		try {
			datecurr = new SimpleDateFormat("yyMMddhhmmssMs").parse(currSessionId);
		} catch (ParseException e) {
			throw new Exception(" Exception");
		}
		System.out.println("Current Date now: " + currSessionId + "\t" + datecurr);
		Date dateReq;
		try {
			dateReq = new SimpleDateFormat("yyMMddhhmmssMs").parse(sessionid);
		} catch (ParseException e) {
			throw new Exception(" Exception");
		}
		System.out.println("Session id Date : " + sessionid + "\t" + dateReq);

		if (datecurr.compareTo(dateReq) >= 0) {
			System.out.println("token is expired");
			return false;
		} else if (datecurr.compareTo(dateReq) < 0) {
			System.out.println("still token is not yet expired");
		}
		return true;

	}

}