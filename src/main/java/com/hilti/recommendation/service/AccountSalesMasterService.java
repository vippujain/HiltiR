package com.hilti.recommendation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.hilti.recommendation.model.AccountSalesMaster;
import com.hilti.recommendation.repository.AccountSalesMasterRepository;

@Service
public class AccountSalesMasterService {

	
	
	@Autowired
	private AccountSalesMasterRepository accountSalesMasterRepository;

	public List<AccountSalesMaster> save(List<AccountSalesMaster> accountSalesMasters) {
		for (AccountSalesMaster accountSalesMaster : accountSalesMasters) {
				accountSalesMasterRepository.save(accountSalesMaster);
		}
		return accountSalesMasters;

	}
	public List<AccountSalesMaster> getData() {
		return (List<AccountSalesMaster>)accountSalesMasterRepository.findAll();
	}
	@SuppressWarnings("unchecked")
	public List<AccountSalesMaster> findOne(String accountId) {
		return (List<AccountSalesMaster>)accountSalesMasterRepository.findOne(accountId);
		/*return accountSalesMasterRepository.findOne(
		        	Query.query(Criteria.where("accountId").is(accountId)),
		        	AccountSalesMaster.class
		    );*/
	}
}
