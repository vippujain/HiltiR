package com.hilti.recommendation.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "account_sales_master")
public class AccountSalesMaster {

	@Id
	private String id;

	private String accountId;
	
	
	private String category;
	private String subCategory;
	private String sales12;
	private String sales24;
	public String getId() {
		return id;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String catagory) {
		this.category = catagory;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public String getSales12() {
		return sales12;
	}
	public void setSales12(String sales12) {
		this.sales12 = sales12;
	}
	public String getSales24() {
		return sales24;
	}
	public void setSales24(String sales24) {
		this.sales24 = sales24;
	}
	
	
}
