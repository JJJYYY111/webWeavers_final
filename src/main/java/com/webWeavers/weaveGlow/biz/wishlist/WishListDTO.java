package com.webWeavers.weaveGlow.biz.wishlist;

public class WishListDTO {

	private int wishListPK;
	private String memberID;
	private int productPK;

	private String productImg;
	private String productName;

	public int getWishListPK() {
		return wishListPK;
	}

	public void setWishListPK(int wishListPK) {
		this.wishListPK = wishListPK;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public int getProductPK() {
		return productPK;
	}

	public void setProductPK(int productPK) {
		this.productPK = productPK;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "WishListDTO [wishListPK=" + wishListPK + ", memberID=" + memberID + ", productPK=" + productPK
				+ ", productImg=" + productImg + ", productName=" + productName + "]";
	}

}