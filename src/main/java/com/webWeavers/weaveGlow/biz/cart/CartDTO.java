package com.webWeavers.weaveGlow.biz.cart;

import lombok.Data;

@Data
public class CartDTO {

	private int cartPK;
	private int productPK;
	private String memberID;
	private int cartCnt;
	
	private String searchCondition;	

	// 장바구니페이지
	private String productName;
	private String productImg;
	private int productPrice;

}
