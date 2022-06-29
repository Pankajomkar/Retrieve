package com.example.Authenticator.service;

import com.example.Authenticator.model.RetrieveResponse;

public interface RetrieveService {

	public RetrieveResponse getList(int pagestart,int limit);
}
