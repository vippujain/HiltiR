package com.hilti.recommendation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hilti.recommendation.model.OpportunityMaster;
import com.hilti.recommendation.model.OpportunityScore;
import com.hilti.recommendation.service.OpportunityMasterService;

@RestController
@RequestMapping(value = "hilti/opportunity/master", produces = "application/json")
public class OpportunityMasterController {

	@Autowired
	private OpportunityMasterService opportunityMasterService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public List<OpportunityMaster> save(@RequestBody List<OpportunityMaster> opportunityMasters) {
		return opportunityMasterService.save(opportunityMasters);
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public List<OpportunityScore> getOpportunityScore(String accountId) {
		return opportunityMasterService.getOpportunityScore(accountId);
	}
	
	@RequestMapping(value = "/findall", method = RequestMethod.GET)
	public List<OpportunityMaster> findAll() {
		return opportunityMasterService.findAll();
	}
	
	@RequestMapping(value = "/findOne", method = RequestMethod.GET)
	public List<OpportunityMaster> findOne(String accountId) {
		return opportunityMasterService.findOne(accountId);
	}

}
