package com.webWeavers.weaveGlow.biz.product;

import java.util.Date;

public class ProductDTO {

	private int productPK;
	private String productName;
	private int productPrice;
	private Date productRegdate;
	private String productDetailImg;
	private String productImg;
	private int productStatus;
	private int productQuantity;

	private String searchCondition;
	private int wish;
	private String memberID;

	public int getProductPK() {
		return productPK;
	}

	public void setProductPK(int productPK) {
		this.productPK = productPK;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public Date getProductRegdate() {
		return productRegdate;
	}

	public void setProductRegdate(Date productRegdate) {
		this.productRegdate = productRegdate;
	}

	public String getProductDetailImg() {
		return productDetailImg;
	}

	public void setProductDetailImg(String productDetailImg) {
		this.productDetailImg = productDetailImg;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public int getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(int productStatus) {
		this.productStatus = productStatus;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public int getWish() {
		return wish;
	}

	public void setWish(int wish) {
		this.wish = wish;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	@Override
	public String toString() {
		return "ProductDTO [PK=" + productPK + ", Name=" + productName + ", Price=" + productPrice + ", Regdate="
				+ productRegdate + ", " + "DetailImg=" + productDetailImg + ", Img=" + productImg + ", Status="
				+ productStatus + ", Quantity=" + productQuantity + ", " + "searchCondition=" + searchCondition
				+ ", wish=" + wish + ", memberID=" + memberID + "]";
	}

}
