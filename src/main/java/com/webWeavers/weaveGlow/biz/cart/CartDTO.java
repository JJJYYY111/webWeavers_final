package com.webWeavers.weaveGlow.biz.cart;

public class CartDTO {

	private int cartPK;
	private int productPK;
	private String memberID;
	private int cartCnt;

	private String productName;
	private String productImg;
	private int productPrice;

	private String searchCondition;

	public int getCartPK() {
		return cartPK;
	}

	public void setCartPK(int cartPK) {
		this.cartPK = cartPK;
	}

	public int getProductPK() {
		return productPK;
	}

	public void setProductPK(int productPK) {
		this.productPK = productPK;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public int getCartCnt() {
		return cartCnt;
	}

	public void setCartCnt(int cartCnt) {
		this.cartCnt = cartCnt;
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

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	@Override
	public String toString() {
		return "CartDTO [cartPK=" + cartPK + ", memberID=" + memberID + ", productPK=" + productPK + ", cartCnt="
				+ cartCnt + ", productName=" + productName + ", productPrice=" + productPrice + ", productImg="
				+ productImg + ", searchCondition=" + searchCondition + "]";
	}

}
