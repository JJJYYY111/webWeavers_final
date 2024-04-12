package com.webWeavers.weaveGlow.biz.product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("productDAO")
public class ProductDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	// ------------------------------------- UserPage -------------------------------------
	
	// User_메인페이지
	// 정렬별 상품목록 쿼리문 반환 함수
	private static String selectAllMainPageProductList(String sortType) {
		String query = 
				"WITH P_BUYCNT AS (\r\n"	// 상품별 총 구매수량 정보 CTE --> 인기순 정렬을 하기위함 
				+ "	SELECT PRODUCT_PK, SUM(BUYPRODUCT_CNT) CNT\r\n"
				+ "	FROM BUYPRODUCT\r\n"
				+ "	GROUP BY PRODUCT_PK\r\n"
				+ "), P_WISHCNT AS (\r\n"	// 상품별 찜 개수 정보 CTE --> 추천순 정렬을 하기위함 
				+ "	SELECT PRODUCT_PK, COUNT(WISHLIST_PK) CNT\r\n"
				+ "	FROM WISHLIST\r\n"
				+ "	GROUP BY PRODUCT_PK\r\n"
				+ ")\r\n"
				+ "SELECT\r\n"
				+ "	P.PRODUCT_PK, P.PRODUCT_NAME, P.PRODUCT_PRICE, P.PRODUCT_IMG,\r\n"
				+ "	COALESCE(PB.CNT, 0) AS sales,\r\n"	// 상품별 총 판매량
				+ "	COALESCE(PW.CNT, 0) AS wish,\r\n"	// 상품별 총 찜개수
				+ "	CASE WHEN W.WISHLIST_PK IS NOT NULL THEN 1 ELSE 0 END AS HasWPK\r\n"	// 상품별 사용자의 찜 유무 확인
				+ "FROM PRODUCT P\r\n"
				+ "LEFT JOIN WISHLIST W ON P.PRODUCT_PK = W.PRODUCT_PK AND W.MEMBER_ID = ?\r\n"
				+ "LEFT JOIN P_BUYCNT PB ON PB.PRODUCT_PK = P.PRODUCT_PK\r\n"
				+ "LEFT JOIN P_WISHCNT PW ON PW.PRODUCT_PK = P.PRODUCT_PK\r\n"			
				+ "ORDER BY " + sortType + " DESC, P.PRODUCT_PK ASC LIMIT 8";	// sortType - 정렬조건
		
		return query;
	}
	
	// User_상품목록페이지
	// 카테고리 및 정렬별 상품목록 쿼리문 반환 함수
	private static String selectAllProductListPage(ProductDTO productDTO) {
		String query = 
				// 상품별 카테고리 정보 CTE
				"WITH P_CATEGORY AS (\r\n"
				+ "	SELECT\r\n"
				+ "		CZ.PRODUCT_PK, C.CATEGORY_NAME,\r\n"
				+ "		GROUP_CONCAT(S.SUBCATEGORY_NAME ORDER BY S.SUBCATEGORY_PK) AS SUBCATEGORY_NAME\r\n"
				+ "	FROM CATEGORIZATION CZ\r\n"
				+ "	INNER JOIN SUBCATEGORY S ON CZ.SUBCATEGORY_PK = S.SUBCATEGORY_PK\r\n"
				+ "	INNER JOIN CATEGORY C ON S.CATEGORY_PK = C.CATEGORY_PK\r\n"
				+ "	WHERE 1 = 1";
		
		// 카테고리 및 서브카테고리 조건
		if(productDTO.getCategoryPK() > 0) {
			// System.out.println("[로그] 카테고리");
			query += "	AND C.CATEGORY_PK = " + productDTO.getCategoryPK();
		}
		if(productDTO.getSubCategoryPK() > 0) {
			// System.out.println("[로그] 서브카테고리");
			query += "	AND S.SUBCATEGORY_PK = " + productDTO.getSubCategoryPK();
		}
		
		// 쿼리 SELECT절
		query +=  "\r\nGROUP BY CZ.PRODUCT_PK, C.CATEGORY_NAME\r\n"
				+ "), P_BUYCNT AS (\r\n"	// 상품별 총 구매수량 정보 CTE --> 인기순 정렬을 하기위함 
				+ "	SELECT PRODUCT_PK, SUM(BUYPRODUCT_CNT) CNT\r\n"
				+ "	FROM BUYPRODUCT\r\n"
				+ "	GROUP BY PRODUCT_PK\r\n"
				+ ")\r\n"
				+ "SELECT\r\n"	// 메인 SELECT절
				+ "	P.PRODUCT_PK, P.PRODUCT_NAME, P.PRODUCT_PRICE, P.PRODUCT_IMG, P.PRODUCT_REGDATE,\r\n"	// 상품정보
				+ "	PC.CATEGORY_NAME, PC.SUBCATEGORY_NAME,\r\n"	// 상품별 카테고리 정보
				+ "	COALESCE(PB.CNT, 0) AS SALES,\r\n"	// 상품별 총 판매량
				+ "	CASE WHEN W.WISHLIST_PK IS NOT NULL THEN 1 ELSE 0 END AS HasWPK\r\n"		// 로그인한 회원의 찜 유무
				+ "FROM PRODUCT P\r\n"
				+ "LEFT JOIN WISHLIST W ON P.PRODUCT_PK = W.PRODUCT_PK AND W.MEMBER_ID = ?\r\n"	// WISHLIST 조인
				+ "INNER JOIN P_CATEGORY PC ON P.PRODUCT_PK = PC.PRODUCT_PK\r\n"				// 카테고리 CTE 조인
				+ "LEFT JOIN P_BUYCNT PB ON P.PRODUCT_PK = PB.PRODUCT_PK\r\n"					// 판매량 CTE 조인
				+ "GROUP BY\r\n"
				+ "	P.PRODUCT_PK, P.PRODUCT_NAME, P.PRODUCT_PRICE, P.PRODUCT_IMG, P.PRODUCT_REGDATE,\r\n"	// GROUP BY
				+ "	PC.CATEGORY_NAME, PC.SUBCATEGORY_NAME, SALES, HasWPK\r\n";
		
		// 정렬 조건 (인기순)
		if(productDTO.getSortType().equals("sales")) {
			query += "ORDER BY SALES DESC, P.PRODUCT_PK ASC";
		}
		// 정렬 조건 (신상순)
		if(productDTO.getSortType().equals("regdate")) {
			query += "ORDER BY P.PRODUCT_REGDATE DESC, P.PRODUCT_PK ASC";
		}
		// 정렬 조건 (낮은가격순)
		if(productDTO.getSortType().equals("rowPrice")) {
			query += "ORDER BY P.PRODUCT_PRICE ASC, P.PRODUCT_PK ASC";
		}
		
		System.out.println(query);
		
		return query;
	}
	
	// User_상품검색페이지
	private static final String SELECTALL_SEARCHNAME = "SELECT\r\n"
			+ "	P.PRODUCT_PK, P.PRODUCT_NAME, P.PRODUCT_PRICE, P.PRODUCT_IMG,\r\n"
			+ "	CASE WHEN W.WISHLIST_PK IS NOT NULL THEN 1 ELSE 0 END AS HasWPK\r\n"
			+ "FROM PRODUCT P\r\n"
			+ "LEFT JOIN WISHLIST W ON P.PRODUCT_PK = W.PRODUCT_PK AND W.MEMBER_ID = ?\r\n"
			+ "WHERE P.PRODUCT_NAME LIKE CONCAT('%',?,'%')\r\n"
			+ "ORDER BY P.PRODUCT_PK DESC";
	// User_상품상세페이지
	private static final String SELECTONE_USER_PRODUCT = "SELECT\r\n"
			+ "	P.PRODUCT_PK, P.PRODUCT_NAME, P.PRODUCT_PRICE, P.PRODUCT_IMG, P.PRODUCT_DETAILIMG,\r\n"
			+ "	CASE WHEN W.WISHLIST_PK IS NOT NULL THEN 1 ELSE 0 END AS HasWPK\r\n"
			+ "FROM PRODUCT P\r\n"
			+ "LEFT JOIN WISHLIST W ON P.PRODUCT_PK = W.PRODUCT_PK AND W.MEMBER_ID = ?\r\n"
			+ "WHERE P.PRODUCT_PK = ?";
	
	// ------------------------------------- AdminPage_상품관리 -------------------------------------	
	// Admin_상품현황페이지
	private static final String SELECTALL_ADMIN_PRODUCT = "SELECT\r\n"
			+ "	PRODUCT_PK, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_QUANTITY, PRODUCT_STATUS\r\n"
			+ "FROM PRODUCT\r\n"
			+ "ORDER BY PRODUCT_PK ASC\r\n";
	// Admin_상품총갯수조회
	private static final String SELECTONE_ADMIN_TOTALPRODUCTNUM = "SELECT COUNT(*) AS total_count FROM PRODUCT WHERE PRODUCT_STATUS = 1"; 
	// Admin_상품수정페이지
	private static final String SELECTONE_UPDATE = "SELECT\r\n"
			+ "	PRODUCT_PK, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_REGDATE,\r\n"
			+ "	PRODUCT_DETAILIMG, PRODUCT_IMG, PRODUCT_STATUS, PRODUCT_QUANTITY\r\n"
			+ "FROM PRODUCT\r\n"
			+ "WHERE PRODUCT_PK = ?";
	// Admin_상품등록페이지 (Product Insert 후 --> Categorization Insert 할 때 해당 ProductPK 필요)
	private static final String SELECTONE_INSERT = "SELECT PRODUCT_PK FROM PRODUCT WHERE PRODUCT_PK = (SELECT MAX(PRODUCT_PK) FROM PRODUCT)";
	// Admin_상품등록페이지
	private static final String INSERT = "INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_DETAILIMG, PRODUCT_IMG, PRODUCT_STATUS, PRODUCT_QUANTITY) VALUES (?, ?, ?, ?, ?, ?)";
	// Admin_상품수정페이지
	private static final String UPDATE = "UPDATE PRODUCT SET PRODUCT_NAME = ?, PRODUCT_PRICE = ?, PRODUCT_DETAILIMG = ?, PRODUCT_IMG = ?, PRODUCT_STATUS = ?, PRODUCT_QUANTITY = ? WHERE PRODUCT_PK = ?";
	private static final String UPDATE_BYSELLING = "UPDATE PRODUCT SET PRODUCT_QUANTITY = PRODUCT_QUANTITY - ? WHERE PRODUCT_PK = ?";
//	private static final String DELETE = "";
	
	// ------------------------------------- AdminPage_매출관리 -------------------------------------
	
	// -----------------------------전일 매출, 월별 매출 -----------------------------------

		// 3시간별 비교 매출 총합
		private static final String SELECTALL_TODAYSALES_THREEHOUR = 
				" SELECT TOTAL.TEMP, SUM(PRODUCT_PRICE * BUYPRODUCT_CNT) AS SALES_TOTAL	"
				+ "FROM ( SELECT P.PRODUCT_PK, P.PRODUCT_PRICE, B.BUYPRODUCT_CNT, (FLOOR(DATE_FORMAT(S.SERIAL_REGDATE, '%H') / 3) * 3) AS TEMP "
				+ "FROM PRODUCT P "
				+ "JOIN BUYPRODUCT B ON P.PRODUCT_PK = B.PRODUCT_PK "
				+ "JOIN SERIAL S ON S.SERIAL_PK = B.SERIAL_PK "
				+ "WHERE DATE(S.SERIAL_REGDATE) = CURDATE()) AS TOTAL " // 구매시간을 기준으로 시간을 계산 = 오늘날짜만
				+ "GROUP BY TEMP "; // 시간대별로 그룹화 

		// 전날 3시간별 비교 매출총합 
		private static final String SELECTALL_PVDAYSALES_THREEHOUR = 
				"SELECT PV_TOTAL.TEMP, SUM(PRODUCT_PRICE * BUYPRODUCT_CNT) AS SALES_TOTAL "
				+ "FROM (SELECT P.PRODUCT_PK, P.PRODUCT_PRICE, B.BUYPRODUCT_CNT, (FLOOR(DATE_FORMAT(S.SERIAL_REGDATE, '%H') / 3) * 3) AS TEMP " // 시간을 3시간 간격으로 그룹화하여 TEMP열 생성
				+ "FROM PRODUCT P JOIN BUYPRODUCT B ON P.PRODUCT_PK = B.PRODUCT_PK "
				+ "JOIN SERIAL S ON S.SERIAL_PK = B.SERIAL_PK "
				+ "WHERE DATE(S.SERIAL_REGDATE) = DATE_SUB(CURDATE(), INTERVAL 1 DAY)) AS PV_TOTAL " // 구매시간을 기준으로 시간을 계산 = 하루 전 날짜만
				+ "GROUP BY TEMP "; 

		// 월별 매출 차트 
		private static final String SELECTALL_MONTHLY_SALES = 
				"SELECT DATE_FORMAT(S.SERIAL_REGDATE, '%Y-%m') AS MONTH, "
				+ "SUM(PRODUCT_PRICE * BUYPRODUCT_CNT) AS SALES_TOTAL " 
				+ "FROM PRODUCT P "
				+ "JOIN BUYPRODUCT B ON P.PRODUCT_PK = B.PRODUCT_PK " // PRODUCT 테이블과 BUYPRODUCT 테이블을 PRODUCT_PK를 기준으로 조인
				+ "JOIN SERIAL S ON S.SERIAL_PK = B.SERIAL_PK " // SERIAL 테이블과 BUYPRODUCT 테이블을 SERIAL_PK를 기준으로 조인
				+ "WHERE YEAR(S.SERIAL_REGDATE) = YEAR(CURDATE()) " // 구매일자가 현재 년도와 같은지
				+ "GROUP BY DATE_FORMAT(S.SERIAL_REGDATE, '%Y-%m') "; // 결과를 월별로 그룹화

		    
		 // 카테고리별 매출 구하고 백분율로 나누기(도넛차트) 
		private static final String SELECTALL_CATEGORY_SALES ="SELECT S.CATEGORY_PK, "
				+ "SUM(TEMP2.BUYPRODUCT_CNT * TEMP2.PRODUCT_PRICE)AS CATEGORY_TOTAL "
				+ "FROM (SELECT B.BUYPRODUCT_CNT, P.PRODUCT_PRICE, (SELECT CZ.SUBCATEGORY_PK FROM CATEGORIZATION CZ WHERE CZ.PRODUCT_PK = P.PRODUCT_PK LIMIT 1) AS TEMP "
				+ "FROM BUYPRODUCT B JOIN PRODUCT P ON B.PRODUCT_PK = P.PRODUCT_PK) TEMP2 "
				+ "JOIN SUBCATEGORY S ON TEMP2.TEMP = S.SUBCATEGORY_PK "
				+ "GROUP BY S.CATEGORY_PK "; 
	
		// 검색조건별 매출현황 쿼리문 반환 함수		
		private static String selectAllProductSalesQuery(ProductDTO productDTO) {

		// 상품별 카테고리 정보 조회 CTE
		String query = "WITH P_CATEGORY AS (\r\n"
				+ "SELECT\r\n"
				+ "		CZ.PRODUCT_PK,\r\n"
				+ "		C.CATEGORY_NAME,\r\n"
				+ "		GROUP_CONCAT(S.SUBCATEGORY_NAME ORDER BY S.SUBCATEGORY_PK) AS SUBCATEGORY_NAME\r\n"	// 여러 ROW로 되어 있는 데이터(GROUP BY를 통해 그룹핑 된 결과)를 하나의 ROW로 묶어서 출력
				+ "FROM CATEGORIZATION CZ\r\n"
				+ "INNER JOIN SUBCATEGORY S ON CZ.SUBCATEGORY_PK = S.SUBCATEGORY_PK\r\n"
				+ "INNER JOIN CATEGORY C ON S.CATEGORY_PK = C.CATEGORY_PK\r\n"
				+ "WHERE 1 = 1";
		
		// 카테고리 및 서브카테고리 조건
		if(productDTO.getCategoryPK() > 0) {
			//System.out.println("[로그] 카테고리");
			query += "	AND C.CATEGORY_PK = " + productDTO.getCategoryPK();
		}
		if(productDTO.getSubCategoryPK() > 0) {
			//System.out.println("[로그] 서브카테고리");
			query += "	AND S.SUBCATEGORY_PK = " + productDTO.getSubCategoryPK();
		}
		
		// 쿼리 SELECT절 (상품정보, 매출정보, 카테고리정보)
		query +=  "\r\nGROUP BY CZ.PRODUCT_PK, C.CATEGORY_NAME\r\n"
				+ ")\r\n"
				+ "SELECT\r\n"
				+ "	P.PRODUCT_PK,\r\n"
				+ "	P.PRODUCT_NAME,\r\n"
				+ "	P.PRODUCT_PRICE,\r\n"
				+ "	COALESCE(SUM(B.BUYPRODUCT_CNT), 0) AS TOTAL_CNT,\r\n"	// 상품별 전체 구매개수 (NULL이면 0반환)
				+ "	COALESCE((P.PRODUCT_PRICE * SUM(B.BUYPRODUCT_CNT)), 0) AS TOTAL_PRICE,\r\n"	// 상품별 전체 매출 (NULL이면 0반환)
				+ "	GROUP_CONCAT(S.SERIAL_REGDATE ORDER BY S.SERIAL_PK) AS SERIAL_REGDATE,\r\n"
				+ "	PC.CATEGORY_NAME,\r\n"
				+ "	PC.SUBCATEGORY_NAME\r\n"
				+ "FROM PRODUCT P\r\n"
				+ "LEFT JOIN BUYPRODUCT B ON P.PRODUCT_PK = B.PRODUCT_PK\r\n"		// 구매개수 추출
				+ "LEFT JOIN SERIAL S ON B.SERIAL_PK = S.SERIAL_PK\r\n"				// 주문일 추출
				+ "INNER JOIN P_CATEGORY PC ON P.PRODUCT_PK = PC.PRODUCT_PK\r\n"	// 카테고리 정보 추출
				+ "WHERE 1 = 1";
		
		// 시작일 및 종료일 조건
		if(productDTO.getStartDate() != "" && productDTO.getStartDate() != null) {
			System.out.println("로그1 들어옴");
			query += "	AND S.SERIAL_REGDATE >= '" + productDTO.getStartDate() + "'";
		}
		if(productDTO.getEndDate() != "" && productDTO.getEndDate() != null) {
			System.out.println("로그2 들어옴");
			query += "	AND S.SERIAL_REGDATE <= DATE_ADD('" + productDTO.getEndDate() + "', INTERVAL 1 DAY)";
		}
		
		// 쿼리 GROUP BY, ORDER BY 부분
		query += "\r\nGROUP BY P.PRODUCT_PK, P.PRODUCT_NAME, P.PRODUCT_PRICE, PC.CATEGORY_NAME, PC.SUBCATEGORY_NAME\r\n"
				+ "ORDER BY TOTAL_PRICE DESC";
		
		System.out.println(query);
		
		return query;
	}

	// Admin_전일대비매출페이지, 월별매출페이지
	// 일별 및 월별 매출현황 쿼리문 반환 함수	
	public String selectAllDailyAndMonthlySales(String searchCondition){
		
		String query = "WITH P_CATEGORY AS (\r\n"
				+ "	SELECT\r\n"
				+ "		CZ.PRODUCT_PK,\r\n"
				+ "		C.CATEGORY_NAME\r\n"
				+ "	FROM CATEGORIZATION CZ\r\n"
				+ "	INNER JOIN SUBCATEGORY S ON CZ.SUBCATEGORY_PK = S.SUBCATEGORY_PK\r\n"
				+ "	INNER JOIN CATEGORY C ON S.CATEGORY_PK = C.CATEGORY_PK\r\n"
				+ "	GROUP BY CZ.PRODUCT_PK, C.CATEGORY_NAME\r\n"
				+ ")\r\n"
				+ "SELECT\r\n"
				+ "	P.PRODUCT_PK,\r\n"
				+ "	P.PRODUCT_NAME,\r\n"
				+ "	P.PRODUCT_PRICE,\r\n"
				+ "	COALESCE(SUM(B.BUYPRODUCT_CNT), 0) AS TOTAL_CNT,\r\n"
				+ "	COALESCE((P.PRODUCT_PRICE * SUM(B.BUYPRODUCT_CNT)), 0) AS TOTAL_PRICE,\r\n"
				+ "	GROUP_CONCAT(S.SERIAL_REGDATE  ORDER BY S.SERIAL_PK) AS SERIAL_REGDATE,\r\n"
				+ "	PC.CATEGORY_NAME\r\n"
				+ "FROM PRODUCT P\r\n"
				+ "LEFT JOIN BUYPRODUCT B ON P.PRODUCT_PK = B.PRODUCT_PK\r\n"
				+ "LEFT JOIN SERIAL S ON B.SERIAL_PK = S.SERIAL_PK\r\n"
				+ "INNER JOIN P_CATEGORY PC ON P.PRODUCT_PK = PC.PRODUCT_PK\r\n";
		
		// Admin_전일대비매출페이지 (당일 매출 Top10 상품목록)
		if(searchCondition.equals("adminDailySales")) {
			query += "WHERE DATE(S.SERIAL_REGDATE) = CURRENT_DATE()\r\n";
		}
		// Admin_월별매출페이지 (당월 매출 Top10 상품목록)
		if(searchCondition.equals("adminMonthlySales")) {
			query += "WHERE YEAR(S.SERIAL_REGDATE) = YEAR(CURRENT_DATE()) AND MONTH(S.SERIAL_REGDATE) = MONTH(CURRENT_DATE())\r\n";
		}
		
		// 쿼리 GROUP BY, ORDER BY, LIMIT 부분
		query += "GROUP BY P.PRODUCT_PK, P.PRODUCT_NAME, P.PRODUCT_PRICE, PC.CATEGORY_NAME\r\n"
				+ "ORDER BY TOTAL_PRICE DESC\r\n"
				+ "LIMIT 10";

		return query;
	}
	
	// 이번 달 총 매출
			private static final String SELECTONE_SALES_THISMONTH =
					"SELECT SUM(B.BUYPRODUCT_CNT * P.PRODUCT_PRICE) AS MONTH_TOTAL " // 현재월의 총매출
					+ "FROM BUYPRODUCT B "
					+ "JOIN PRODUCT P ON B.PRODUCT_PK = P.PRODUCT_PK " // BUYPRODUCT 테이블과 PRODUCT 테이블을 PRODUCT_PK를 기준으로 조인
					+ "JOIN SERIAL S ON S.SERIAL_PK = B.SERIAL_PK " // BUYPRODUCT 테이블과 SERIAL 테이블을 SERIAL_PK를 기준으로 조인
					+ "WHERE YEAR(S.SERIAL_REGDATE) = YEAR(CURDATE()) " // 구매년도가 현재 년도와 같은지 확인
					+ "AND MONTH(S.SERIAL_REGDATE) = MONTH(CURDATE())"; // 구매월이 현재 월과 같은지 확인
	// 오늘 총 매출 
			private static final String SELECTONE_TODAYSALES_TOTAL =
					"SELECT SUM(B.BUYPRODUCT_CNT * P.PRODUCT_PRICE) AS TOTAL "
							+ "FROM BUYPRODUCT B 	"
							+ "JOIN PRODUCT P ON B.PRODUCT_PK = P.PRODUCT_PK " // BUYPRODUCT 테이블과 PRODUCT 테이블을 PRODUCT_PK를 기준으로 조인
							+ "JOIN SERIAL S ON S.SERIAL_PK = B.SERIAL_PK " // BUYPRODUCT 테이블과 SERIAL 테이블을 SERIAL_PK를 기준으로 조인
							+ "WHERE DATE(S.SERIAL_REGDATE) = CURDATE()";

	// 어제 총 매출 --
			private static final String SELECTONE_PVDAYSALES_TOTAL = 
					"SELECT SUM(B.BUYPRODUCT_CNT * P.PRODUCT_PRICE) AS PV_TOTAL "  
					+ "FROM BUYPRODUCT B "
					+ "JOIN PRODUCT P ON B.PRODUCT_PK = P.PRODUCT_PK " // BUYPRODUCT 테이블과 PRODUCT 테이블을 PRODUCT_PK를 기준으로 조인
					+ "JOIN SERIAL S ON S.SERIAL_PK = B.SERIAL_PK " // BUYPRODUCT 테이블과 SERIAL 테이블을 SERIAL_PK를 기준으로 조인
					+ "WHERE DATE(S.SERIAL_REGDATE) = DATE_SUB(CURDATE(), INTERVAL 1 DAY)";
	
	public List<ProductDTO> selectAll(ProductDTO productDTO) {
		System.out.println(productDTO.getMemberID());
		Object[] args1 = { productDTO.getMemberID() };
		Object[] args2 = { productDTO.getMemberID(), productDTO.getProductName() };
		
		try {
			// User_메인페이지(판매량순, 찜순)
			if (productDTO.getSearchCondition().equals("userMain")) {	// 쿼리반환함수 인자 : DTO를 줘도되지만 정렬조건만 있어도돼서 개별로줌
				return jdbcTemplate.query(selectAllMainPageProductList(productDTO.getSortType()), args1, new ProductListUserRowMapper());
			}
			// User_상품목록페이지(판매량순, 신상순, 낮은가격순)
			else if (productDTO.getSearchCondition().equals("userProductList")) {	// 쿼리반환함수 인자 : 줘야할 정보가 많아서 DTO줌
				return jdbcTemplate.query(selectAllProductListPage(productDTO), args1, new ProductListUserRowMapper());
			}			
			// User_상품검색페이지
			else if (productDTO.getSearchCondition().equals("searchName")) {
				return jdbcTemplate.query(SELECTALL_SEARCHNAME, args2, new ProductListUserRowMapper());
			}
			// Admin_상품현황페이지
			else if (productDTO.getSearchCondition().equals("adminProductList")) {
				return jdbcTemplate.query(SELECTALL_ADMIN_PRODUCT, new ProductListAdminRowMapper());
			}
			// Admin_매출현황페이지
			else if (productDTO.getSearchCondition().equals("adminProductSales")) {
				return jdbcTemplate.query(selectAllProductSalesQuery(productDTO), new ProductSalesAdminRowMapper());
			}
			// Admin_전일대비매출페이지 (당일 매출 Top10 상품목록)
			else if (productDTO.getSearchCondition().equals("adminDailySales")) {
				return jdbcTemplate.query(selectAllDailyAndMonthlySales("adminDailySales"), new ProductTop10SalesRowMapper());
			}
			// Admin_월별매출페이지 (당월 매출 Top10 상품목록)
			else if (productDTO.getSearchCondition().equals("adminMonthlySales")) {
				return jdbcTemplate.query(selectAllDailyAndMonthlySales("adminMonthlySales"), new ProductTop10SalesRowMapper());
			}
			
			// Admin_오늘 3시간별 매출(줄 그래프)
			else if (productDTO.getSearchCondition().equals("adminTodaySalesByHours")) {
				return jdbcTemplate.query(SELECTALL_TODAYSALES_THREEHOUR, new ProductSalesTodayHourAdminRowMapper());
			}
			// Admin_어제 3시간별 매출(줄 그래프)
			else if (productDTO.getSearchCondition().equals("adminPvdaySalesByHours")) {
				return jdbcTemplate.query(SELECTALL_PVDAYSALES_THREEHOUR, new ProductSalesPvdayHourAdminRowMapper());
			}
			// Admin_월별매출(막대 그래프)
			else if (productDTO.getSearchCondition().equals("adminMonthlySalesGraph")) {
				return jdbcTemplate.query(SELECTALL_MONTHLY_SALES, new ProductMonthlySalesAdminRowMapper());
			}
			// Admin_카테고리별 매출 백분율(도넛차트)
			else if (productDTO.getSearchCondition().equals("adminCategorySalesDonut")) {
				return jdbcTemplate.query(SELECTALL_CATEGORY_SALES, new ProductSalesByCategoryAdminRowMapper());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return null;
	}

	public ProductDTO selectOne(ProductDTO productDTO) {
		Object[] args1 = { productDTO.getMemberID(), productDTO.getProductPK() };
		Object[] args2 = { productDTO.getProductPK() };

		try {
			// User_상품상세페이지
			if (productDTO.getSearchCondition().equals("userProduct")) {
				return jdbcTemplate.queryForObject(SELECTONE_USER_PRODUCT, args1, new ProductUserRowMapper());
			}
			// Admin_상품수정페이지
			else if (productDTO.getSearchCondition().equals("adminProduct")) {
				return jdbcTemplate.queryForObject(SELECTONE_UPDATE, args2, new ProductAdminRowMapper());
			}
			// Admin_상품등록페이지 (Product Insert 후 --> Categorization Insert 할 때 해당 ProductPK 필요)
			else if (productDTO.getSearchCondition().equals("productInsert")) {
				return jdbcTemplate.queryForObject(SELECTONE_INSERT, new ProductInsertRowMapper());
			}
			// Admin_판매중인 상품 총 갯수
			else if (productDTO.getSearchCondition().equals("adminSalesProductTotalNum")) {
				return jdbcTemplate.queryForObject(SELECTONE_ADMIN_TOTALPRODUCTNUM, new ProductInsertRowMapper());
			}
			// Admin_오늘 총 매출
			else if (productDTO.getSearchCondition().equals("adminTodaySales")) {
				return jdbcTemplate.queryForObject(SELECTONE_TODAYSALES_TOTAL, new ProductSalesTotalTodayAdminRowMapper());
			}
			// Admin_어제 총 매출
			else if (productDTO.getSearchCondition().equals("adminPvdaySales")) {
				return jdbcTemplate.queryForObject(SELECTONE_PVDAYSALES_TOTAL, new ProductSalesTotalPvdayAdminRowMapper());
			}
			// Admin_이번달 총 매출
			else if (productDTO.getSearchCondition().equals("adminSalesThisMonth")) {
				return jdbcTemplate.queryForObject(SELECTONE_SALES_THISMONTH, new ProductSalesThisMonthAdminRowMapper());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return null;
	}

	// Admin_상품등록페이지
	public boolean insert(ProductDTO productDTO) {
		try {
			int result = jdbcTemplate.update(INSERT, productDTO.getProductName(), productDTO.getProductPrice(),
					productDTO.getProductDetailImg(), productDTO.getProductImg(), productDTO.getProductStatus(),
					productDTO.getProductQuantity());
			if (result <= 0) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	// Admin_상품수정페이지
	public boolean update(ProductDTO productDTO) {
		int result = 0;
		try {
			if(productDTO.getSearchCondition().equals("updateAdminProduct")) {
				result = jdbcTemplate.update(UPDATE, productDTO.getProductName(), productDTO.getProductPrice(),
						productDTO.getProductDetailImg(), productDTO.getProductImg(), productDTO.getProductStatus(),
						productDTO.getProductQuantity(), productDTO.getProductPK());
			}
			// 판매된 상품수만큼 재고수량 수정
			else if(productDTO.getSearchCondition().equals("updateBySelling")) {
				result = jdbcTemplate.update(UPDATE_BYSELLING, productDTO.getProductQuantity(), productDTO.getProductPK());
			}
			if (result <= 0) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

//	private boolean delete(ProductDTO productDTO) {
//		return false;
//	}

}

// selectAll_User_메인페이지, 상품목록페이지, 상품이름검색페이지
class ProductListUserRowMapper implements RowMapper<ProductDTO> {
	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductDTO data = new ProductDTO();
		data.setProductPK(rs.getInt("PRODUCT_PK"));
		data.setProductName(rs.getString("PRODUCT_NAME"));
		data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
		data.setProductImg(rs.getString("PRODUCT_IMG"));
		data.setWish(rs.getInt("HasWPK"));
		
		return data;
	}
}

// selectOne_User_상품상세페이지
class ProductUserRowMapper implements RowMapper<ProductDTO> {
	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductDTO data = new ProductDTO();
		data.setProductPK(rs.getInt("PRODUCT_PK"));
		data.setProductName(rs.getString("PRODUCT_NAME"));
		data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
		data.setProductDetailImg(rs.getString("PRODUCT_DETAILIMG"));
		data.setProductImg(rs.getString("PRODUCT_IMG"));
		data.setWish(rs.getInt("HasWPK"));
		
		return data;
	}
}

//selectAll_Admin_매출관리>매출현황페이지
class ProductSalesAdminRowMapper implements RowMapper<ProductDTO> {
	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductDTO data = new ProductDTO();
		data.setProductPK(rs.getInt("PRODUCT_PK"));
		data.setProductName(rs.getString("PRODUCT_NAME"));
		data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
		data.setTotalCnt(rs.getInt("TOTAL_CNT"));
		data.setTotalPrice(rs.getInt("TOTAL_PRICE"));
		data.setCategoryName(rs.getString("CATEGORY_NAME"));
		data.setSubCategoryName(rs.getString("SUBCATEGORY_NAME"));

		return data;
	}
}

//selectAll_Admin_매출관리>전일대비/월별매출페이지 (당일/당월 매출 Top10 상품목록)
class ProductTop10SalesRowMapper implements RowMapper<ProductDTO> {
	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductDTO data = new ProductDTO();
		data.setProductPK(rs.getInt("PRODUCT_PK"));
		data.setProductName(rs.getString("PRODUCT_NAME"));
		data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
		data.setTotalCnt(rs.getInt("TOTAL_CNT"));
		data.setTotalPrice(rs.getInt("TOTAL_PRICE"));
		data.setCategoryName(rs.getString("CATEGORY_NAME"));

		return data;
	}
}

//selectAll_Admin_상품현황페이지
class ProductListAdminRowMapper implements RowMapper<ProductDTO> {
	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductDTO data = new ProductDTO();
		data.setProductPK(rs.getInt("PRODUCT_PK"));
		data.setProductName(rs.getString("PRODUCT_NAME"));
		data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
		data.setProductQuantity(rs.getInt("PRODUCT_QUANTITY"));
		data.setProductStatus(rs.getInt("PRODUCT_STATUS"));
		
		return data;
	}
}

// selectOne_Admin_상품상세페이지(상품수정페이지)
class ProductAdminRowMapper implements RowMapper<ProductDTO> {
	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductDTO data = new ProductDTO();
		data.setProductPK(rs.getInt("PRODUCT_PK"));
		data.setProductName(rs.getString("PRODUCT_NAME"));
		data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
		data.setProductQuantity(rs.getInt("PRODUCT_QUANTITY"));
		data.setProductStatus(rs.getInt("PRODUCT_STATUS"));
		data.setProductRegdate(rs.getDate("PRODUCT_REGDATE"));
		data.setProductDetailImg(rs.getString("PRODUCT_DETAILIMG"));
		data.setProductImg(rs.getString("PRODUCT_IMG"));
		
		return data;
	}
}

//selectOne_Admin_상품등록페이지
class ProductInsertRowMapper implements RowMapper<ProductDTO> {
	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductDTO data = new ProductDTO();
		data.setProductPK(rs.getInt("PRODUCT_PK"));
		
		return data;
	}
}

class ProductSalesTotalNumAdminRowMapper implements RowMapper<ProductDTO> {
	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductDTO data = new ProductDTO();
		data.setTotalPrice(rs.getInt("TOTAL_PRODUCTNUM"));
		
		return data;
	}
}

class ProductSalesTotalTodayAdminRowMapper implements RowMapper<ProductDTO> {
	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductDTO data = new ProductDTO();
		data.setTotalPrice(rs.getInt("TOTAL"));
		
		return data;
	}
}

class ProductSalesTotalPvdayAdminRowMapper implements RowMapper<ProductDTO> {
	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductDTO data = new ProductDTO();
		data.setPvtotalPrice(rs.getInt("PV_TOTAL"));
		
		return data;
	}
}
class ProductSalesThisMonthAdminRowMapper implements RowMapper<ProductDTO> {
	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductDTO data = new ProductDTO();
		data.setTotalPrice(rs.getInt("MONTH_TOTAL"));
		
		return data;
	}
}

class ProductSalesTodayHourAdminRowMapper implements RowMapper<ProductDTO> {
	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductDTO data = new ProductDTO();
		data.setTotalTemp(rs.getInt("TEMP"));
		data.setTotalPrice(rs.getInt("SALES_TOTAL"));
		
		return data;
	}
}

class ProductSalesPvdayHourAdminRowMapper implements RowMapper<ProductDTO> {
	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductDTO data = new ProductDTO();
		data.setPvtotalTemp(rs.getInt("TEMP"));
		data.setTotalPrice(rs.getInt("SALES_TOTAL"));
		return data;
	}
}

class ProductMonthlySalesAdminRowMapper implements RowMapper<ProductDTO> {
	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductDTO data = new ProductDTO();
		data.setMonth(rs.getString("MONTH"));
		data.setTotalPrice(rs.getInt("SALES_TOTAL"));
		
		return data;
	}
}

class ProductSalesByCategoryAdminRowMapper implements RowMapper<ProductDTO> {
	@Override
	public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductDTO data = new ProductDTO();
		data.setCategoryPK(rs.getInt("CATEGORY_PK"));
		data.setCategoryTotal(rs.getInt("CATEGORY_TOTAL"));
		
		return data;
	}
}