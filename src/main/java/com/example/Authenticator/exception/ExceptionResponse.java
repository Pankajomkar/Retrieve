package com.example.Authenticator.exception;

import java.util.Date;

import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.ToString;

@ToString
@ResponseBody
public class ExceptionResponse {
	
	@JsonProperty("ErrorCode")
	public String errorCode;
	
	@JsonProperty("ErrorMessage")
	public String errorMessage;
	
	@JsonProperty("ErrorDescription")
	public String errorDescription;

	public ExceptionResponse(String code , String message, String description) {
		this.errorCode = code;
		this.errorMessage = message;
		this.errorDescription = description;

	}

}
