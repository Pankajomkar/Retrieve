package com.example.Authenticator.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RetrieveResponse {
	
	public int nextPage;	
	public int pagestart;	
	public int limit;	
	public List<Integer> msgs;

}
