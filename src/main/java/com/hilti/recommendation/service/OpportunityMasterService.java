package com.hilti.recommendation.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hilti.recommendation.model.OpportunityMaster;
import com.hilti.recommendation.model.OpportunityScore;
import com.hilti.recommendation.repository.OpportunityMasterRepository;

@Service
public class OpportunityMasterService {

	@Autowired
	private OpportunityMasterRepository opportunityMasterRepository;

	public List<OpportunityMaster> save(List<OpportunityMaster> opportunityMasters) {
		for (OpportunityMaster opportunityMaster : opportunityMasters) {
			opportunityMasterRepository.save(opportunityMaster);
		}
		return opportunityMasters;

	}

	public List<OpportunityMaster> findAll() {
		return opportunityMasterRepository.findAll();

	}
	public List<OpportunityMaster> findOne(String accountId){
		return opportunityMasterRepository.findByAccountId(accountId);
	}
	public List<OpportunityScore> getOpportunityScore(String accountId) {

		List<OpportunityScore> opportunityScores = new ArrayList<>();

		List<OpportunityMaster> opportunityMasters = opportunityMasterRepository.findByAccountId(accountId);

		for (OpportunityMaster opportunityMaster : opportunityMasters) {

			List<OpportunityMaster> selectedList = new ArrayList<>();

			List<OpportunityMaster> temp = null;

			temp = opportunityMasterRepository.findByCategoryAndSales12AndSize(opportunityMaster.getCategory(),
					opportunityMaster.getSales12(), opportunityMaster.getSize());
			if (!temp.isEmpty()) {
				selectedList.addAll(temp);
			}
			temp = opportunityMasterRepository.findByCategoryAndSales12AndAccountCategory(
					opportunityMaster.getCategory(), opportunityMaster.getSales12(),
					opportunityMaster.getAccountCategory());
			if (!temp.isEmpty()) {
				selectedList.addAll(temp);
			}

			temp = opportunityMasterRepository.findByCategoryAndAccountCategoryAndSize(opportunityMaster.getCategory(),
					opportunityMaster.getAccountCategory(), opportunityMaster.getSize());
			if (!temp.isEmpty()) {
				selectedList.addAll(temp);
			}

			OpportunityScore opportunityScore = new OpportunityScore();
			List<OpportunityMaster> uniqueList = new ArrayList<>();
			Set<OpportunityMaster> uniqueSet = new HashSet<OpportunityMaster>();
			Map<String, OpportunityMaster> map = new LinkedHashMap<String, OpportunityMaster>();
			for (OpportunityMaster item : selectedList) {
			  map.put(item.getAccountId(), item);
			}
			
			uniqueList.addAll(map.values());
			
			if (!uniqueList.isEmpty()) {
				for (OpportunityMaster opportunityMaster2 : uniqueList) {

					if (!opportunityMaster2.getAccountId().equals(opportunityMaster.getAccountId())) 
							uniqueSet.add(opportunityMaster2);
					}
				}

				int highScore = 0;
				int lowScore = 0;
				int medium = 0;
				Iterator<OpportunityMaster> uniqueDataIterator = uniqueSet.iterator();
				while (uniqueDataIterator.hasNext()) {
					OpportunityMaster calculateOnOpportunity = uniqueDataIterator.next();
					if (Double.parseDouble(calculateOnOpportunity.getSales12()) == Double
							.parseDouble(opportunityMaster.getSales12())) {
						medium = medium + 1;
					} else if (Double.parseDouble(calculateOnOpportunity.getSales12()) < Double
							.parseDouble(opportunityMaster.getSales12())) {
						lowScore = lowScore + 1;
					} else if (Double.parseDouble(calculateOnOpportunity.getSales12()) > Double
							.parseDouble(opportunityMaster.getSales12())) {
						highScore = highScore + 1;
					}
				}

				opportunityScore.setCategoryName(opportunityMaster.getCategory());
				
				if((highScore==0 && medium==0 && lowScore==0) || (highScore==medium && highScore==lowScore))
				{
					opportunityScore.setScore("M");
				}
				else if(highScore > medium && highScore > lowScore)	{
					opportunityScore.setScore("H");
				}
				else if(medium > highScore && medium > lowScore){
					opportunityScore.setScore("M");
				}
				else if(lowScore > highScore && lowScore > medium){
					opportunityScore.setScore("L");
				}
				else if(highScore==medium){
					opportunityScore.setScore("H");
				}
				else{
					opportunityScore.setScore("M");
				}
			opportunityScores.add(opportunityScore);
		}
		return opportunityScores;
}
}
