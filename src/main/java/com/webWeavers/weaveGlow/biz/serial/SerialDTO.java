package com.webWeavers.weaveGlow.biz.serial;

import java.util.Date;

import lombok.Data;

@Data
public class SerialDTO {

	private int serialPK;
	private String memberID;
	private Date serialRegdate;
	private String serialStatus;
	private String serialDeliveryAddress;
	
	private String searchCondition;
	
	private String memberName;
	private int buyProductCnt;				// 주문현황페이지_~~ 상품 외 ? 개수 , 주문현황상세페이지_주문 상품별 구매 개수
	private int totalPrice;			// 주문현황페이지_주문별 총 금액 , 주문현황상세페이지_주문 상품별 총 금액	
	private String productName;			// 주문현황페이지_대표상품명 , 주문현황상세페이지_주문 상품별 이름
}