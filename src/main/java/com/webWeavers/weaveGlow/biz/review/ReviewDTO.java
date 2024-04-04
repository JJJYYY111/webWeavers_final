package com.webWeavers.weaveGlow.biz.review;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewDTO {

	private int reviewPK;
	private int buyProductPK;
	private Date reviewRegdate;
	private int reviewScope;
	private String reviewContent;
	private String reviewImg;

	private String searchCondition;
	
	// 상품상세페이지
	private int reviewLike;		// User_상품상세페이지 사용자의 리뷰따봉 유무
	private int reviewLikeCnt;
	private String memberID;
	private String memberNickname;
	private int gradePK;
	private int productPK;
	private String productName;
	private String productImg;

}