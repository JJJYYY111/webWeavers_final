package com.webWeavers.weaveGlow.biz.product;

import java.util.Date;

import lombok.Data;

@Data
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
	
	private String memberID;
	private int wish;
	private int pageNum;
	private int categoryPK;			// 매출현황 (필터_카테고리 선택, 쿼리문 조건"?"에 필요)
	private int subCategoryPK;		// 매출현황 (필터_서브카테고리 선택, 쿼리문 조건"?"에 필요)
	private String categoryName;	// 매출현황 (카테고리 이름)
	private String subCategoryName;	// 매출현황 (카테고리 이름)
	private String startDate;		// 매출현황 (필터_조회기간 시작날짜)	자료형 Date로하면 형변환 필요함
	private String endDate;			// 매출현황 (필터_조회기간 마지막날짜)
	private int totalCnt;			// 매출현황 (총 판매 개수)
	private int totalPrice;			// 매출현황 (총 판매 금액)

}