package com.hilti.recommendation.service;

import static com.hilti.recommendation.constant.HiltiRecommendationConstants.DECLINE;
import static com.hilti.recommendation.constant.HiltiRecommendationConstants.GROWTH;
import static com.hilti.recommendation.constant.HiltiRecommendationConstants.LARGE;
import static com.hilti.recommendation.constant.HiltiRecommendationConstants.MEDIUM;
import static com.hilti.recommendation.constant.HiltiRecommendationConstants.SMALL;
import static com.hilti.recommendation.constant.HiltiRecommendationConstants.STABLE;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hilti.recommendation.model.AccountMaster;
import com.hilti.recommendation.model.ModelData;
import com.hilti.recommendation.repository.AccountMasterRepository;
import com.hilti.recommendation.repository.ConfigRepository;

@Service
public class AccountMasterService {


	@Autowired
	private AccountMasterRepository accountMasterRepository;

	@Autowired
	private ConfigRepository configRepository;

	public List<AccountMaster> save(List<AccountMaster> accountMasters) {
			return (List<AccountMaster>)accountMasterRepository.save(accountMasters);
	}
	
	
	public List<AccountMaster> getData() {
		List<AccountMaster>  response = new ArrayList<>();
		List<AccountMaster> data= accountMasterRepository.findAllByOrderByScoreDesc() ;
		for(int i = 0 ; i<  data.size() ; i++) {
			
			response.add(data.get(i).setRank(String.valueOf(i+1)));
		}
		return response ;
	}

	public List<AccountMaster> config() {
		List<AccountMaster> responseData = new ArrayList<>();
		List<ModelData> modelDatas = configRepository.findAll();

		for (ModelData modelData : modelDatas) {

			List<AccountMaster> accountMasters = accountMasterRepository
					.findByRegionAndMarketAndArea(modelData.getRegion(), modelData.getMarket(), modelData.getArea());
			for (AccountMaster accountMaster : accountMasters) {

				accountMaster.setScore(calculateScore(modelData, accountMaster));
				accountMasterRepository.save(accountMaster);
				responseData.add(accountMaster);
			}
		}
		return responseData;
	}

	private Double calculateScore(ModelData modelData, AccountMaster accountMaster) {

		int sizeValue = 0;
		int categoryValue = 0;

		if (accountMaster.getSize().equalsIgnoreCase(SMALL)) {
			sizeValue = Integer.parseInt(modelData.getSmall());
		} else if (accountMaster.getSize().equalsIgnoreCase(MEDIUM)) {
			sizeValue = Integer.parseInt(modelData.getMedium());;
		} else if (accountMaster.getSize().equalsIgnoreCase(LARGE)) {
			sizeValue = Integer.parseInt(modelData.getLarge());;
		}

		if (accountMaster.getCategory().equalsIgnoreCase(GROWTH)) {
			categoryValue = Integer.parseInt(modelData.getGrowth());
		} else if (accountMaster.getCategory().equalsIgnoreCase(STABLE)) {
			categoryValue = Integer.parseInt(modelData.getStable());
		} else if (accountMaster.getCategory().equalsIgnoreCase(DECLINE)) {
			categoryValue = Integer.parseInt(modelData.getDecline());
		}
		
		return 
				(Double.parseDouble(modelData.getCategoryWeight()) * categoryValue)
						
				+ (Double.parseDouble(modelData.getSizeWeight() ) * sizeValue)
				
				+ (Double.parseDouble(modelData.getSalesWeight() ) * Double.parseDouble(accountMaster.getSales12()));

	}

}
