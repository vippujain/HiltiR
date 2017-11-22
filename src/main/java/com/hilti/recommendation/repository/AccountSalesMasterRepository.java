package com.hilti.recommendation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hilti.recommendation.model.AccountSalesMaster;

public interface AccountSalesMasterRepository extends MongoRepository<AccountSalesMaster, String>{

	AccountSalesMaster findByAccountId(String accountId);
}
