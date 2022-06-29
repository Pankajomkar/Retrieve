package com.example.Authenticator.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.example.Authenticator.model.RetrieveResponse;

@Service
public class RetrieveServiceImpl implements RetrieveService {

	@Override
	public RetrieveResponse getList(int pagestart, int limit) {

		RetrieveResponse resp = new RetrieveResponse();
		List<Integer> finRes = new ArrayList<>();

		/*
		 * Gets the hardcoded list
		 */	
		List<Integer> elementList = getHardCodedElements();

		
		/*
		 * Pagenation logic
		 */	
		int j = 0;
		if (pagestart == 1) {
			j = 0;
		} else {
			j = 100 * pagestart;
			limit = j + limit;
		}

		for (int i = j; i < limit; i++) {
			finRes.add(elementList.get(i));
		}

		/*
		 * Response mapping
		 */	
		resp.setNextPage(pagestart + 1);
		resp.setMsgs(finRes);
		resp.setLimit(limit);
		resp.setPagestart(pagestart);
		return resp;
	}

	private List<Integer> getHardCodedElements() {

		int[] arr = IntStream.rangeClosed(1, 1000).toArray();
		return Arrays.stream(arr).boxed().collect(Collectors.toList());

	}

}
