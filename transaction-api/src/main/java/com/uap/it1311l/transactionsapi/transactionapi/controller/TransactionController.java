package com.uap.it1311l.transactionsapi.transactionapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uap.it1311l.transactionsapi.transactionapi.model.CustomerInfo;
import com.uap.it1311l.transactionsapi.transactionapi.model.ItemInfo;
import com.uap.it1311l.transactionsapi.transactionapi.model.TransactionResponse;
import com.uap.it1311l.transactionsapi.transactionapi.repository.MyBatisRepo;


@RestController
public class TransactionController {
	
	@Autowired
	MyBatisRepo mybatisRepo;
	
	@PostMapping("/event/insert")
	public TransactionResponse insert(@RequestBody CustomerInfo customer, @RequestBody List<ItemInfo> items) {
		int total = 0;
		mybatisRepo.insertCustomer(customer);
		mybatisRepo.insertItem(items);
		
		for(ItemInfo item: items) {
			total =+ item.getPrice() * item.getQuantity();
		}
		
		TransactionResponse response = new TransactionResponse();
		
		response.setTotalPrice(total);
		response.setTransactionId(0);
		return response;
	}
	
}
