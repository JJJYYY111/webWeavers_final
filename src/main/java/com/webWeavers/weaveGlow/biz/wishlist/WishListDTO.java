package com.webWeavers.weaveGlow.biz.wishlist;

import lombok.Data;

@Data
public class WishListDTO {

	private int wishListPK;
	private String memberID;
	private int productPK;

	private String productImg;
	private String productName;

}