package com.webWeavers.weaveGlow.biz.review;

import java.util.Date;

public class ReviewDTO {

	private int reviewPK;
	private int buyProductPK;
	private Date reviewRegdate;
	private int reviewScope;
	private String reviewContent;
	private String reviewImg;

	private String memberID;
	private String memberNickname;
	private int gradePK;
	private int productPK;
	private String productName;
	private String productImg;

	private String searchCondition;

	public int getReviewPK() {
		return reviewPK;
	}

	public void setReviewPK(int reviewPK) {
		this.reviewPK = reviewPK;
	}

	public int getBuyProductPK() {
		return buyProductPK;
	}

	public void setBuyProductPK(int buyProductPK) {
		this.buyProductPK = buyProductPK;
	}

	public Date getReviewRegdate() {
		return reviewRegdate;
	}

	public void setReviewRegdate(Date reviewRegdate) {
		this.reviewRegdate = reviewRegdate;
	}

	public int getReviewScope() {
		return reviewScope;
	}

	public void setReviewScope(int reviewScope) {
		this.reviewScope = reviewScope;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public String getReviewImg() {
		return reviewImg;
	}

	public void setReviewImg(String reviewImg) {
		this.reviewImg = reviewImg;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public int getGradePK() {
		return gradePK;
	}

	public void setGradePK(int gradePK) {
		this.gradePK = gradePK;
	}

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

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	@Override
	public String toString() {
		return "ReviewDTO [reviewPK=" + reviewPK + ", memberID=" + memberID + ", buyProductPK=" + buyProductPK
				+ ", reviewContent=" + reviewContent + ", reviewScope=" + reviewScope + ", reviewImg=" + reviewImg
				+ ", reviewRegdate=" + reviewRegdate + ", memberNickname=" + memberNickname + ", gradePK=" + gradePK
				+ ", productName=" + productName + ", productPK=" + productPK + ", productImg=" + productImg
				+ ", searchCondition=" + searchCondition + "]";
	}

}