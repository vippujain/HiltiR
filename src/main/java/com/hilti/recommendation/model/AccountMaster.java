package com.hilti.recommendation.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "account_master")
public class AccountMaster {


	@Id
	private String id;

	
	private String accountId;
	
	private String accountName ;
	private String region;
	private String market;
	private String area;
	
	
	private String category;
	
	private String size;
	private String sales12;
	private double score;
	private String rank;
	
	public String getId() {
		return id;
	}
	public String getRank() {
		return rank;
	}
	public AccountMaster setRank(String rank) {
		this.rank = rank;
		return this ;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getSales12() {
		return sales12;
	}
	public void setSales12(String sales12) {
		this.sales12 = sales12;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	
	

}
