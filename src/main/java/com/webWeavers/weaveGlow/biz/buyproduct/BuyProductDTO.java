package com.webWeavers.weaveGlow.biz.buyproduct;

import java.sql.Date;

import lombok.Data;

@Data
public class BuyProductDTO {
	
	private int buyProductPK;
	private int productPK;
	private int serialPK;
	private int buyProductCnt;
	
	private String searchCondition;	
	
	// 구매목록페이지
	private String memberID;
	private Date serialRegdate;
	private String serialDeliveryAddress;
	private String productName;
	private int productPrice;
	private String productImg;
	private int reviewCheck;

	
}
