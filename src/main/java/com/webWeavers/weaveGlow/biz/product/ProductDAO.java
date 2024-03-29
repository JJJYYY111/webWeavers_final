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
	// User_메인페이지, 상품목록페이지
	// 정렬별 상품목록 쿼리문 반환 함수
	private static String selectAllProductListQuery(String sortType) {
		
		String query = "WITH WS AS (SELECT PRODUCT_PK, COUNT(WISHLIST_PK) CNT FROM WISHLIST GROUP BY PRODUCT_PK),\r\n"	// 상품별 찜 개수 CTE
				+ "BS AS ( SELECT PRODUCT_PK, SUM(BUYPRODUCT_CNT) SALES FROM BUYPRODUCT GROUP BY PRODUCT_PK)\r\n"		// 상품별 판매량 CTE
				+ "SELECT P.PRODUCT_PK, P.PRODUCT_NAME, P.PRODUCT_PRICE, P.PRODUCT_IMG, P.PRODUCT_REGDATE, BS.SALES, WS.CNT,\r\n"
				+ "	CASE WHEN W.WISHLIST_PK IS NOT NULL THEN 1 ELSE 0 END AS HasWPK\r\n"
				+ "FROM PRODUCT P\r\n"
				+ "LEFT JOIN WISHLIST W ON P.PRODUCT_PK = W.PRODUCT_PK AND W.MEMBER_ID = ?\r\n"
				+ "LEFT JOIN  BS ON P.PRODUCT_PK = BS.PRODUCT_PK\r\n"
				+ "LEFT JOIN WS ON P.PRODUCT_PK = WS.PRODUCT_PK\r\n"
				+ "ORDER BY\r\n";
		
		// 메인페이지,상품목록페이지(판매량순)
		if(sortType.equals("sales")) {
			query += "BS.SALES DESC, ";
		}
		// 상품목록페이지(신상순)
		else if(sortType.equals("regdate")) {
			query += "P.PRODUCT_REGDATE DESC, ";
		}
		// 메인페이지(찜순)
		else if(sortType.equals("wish")) {
			query += "WS.CNT DESC, ";
		}
		// 상품목록페이지(낮은가격순)
		else if(sortType.equals("rowPrice")) {
			query += "P.PRODUCT_PRICE ASC, ";
		}
		
		query += "P.PRODUCT_PK DESC";
		
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
//			+ "LIMIT ?, 10";	// 페이징처리 (앞단 페이지 번호 필요) (?행부터 10개 데이터 추출 / 0,10,20,30,40,... 이렇게 번호 넘어오면 좋을것같음) -> 앞단에서 처리 예정
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
//	private static final String DELETE = "";
	
	// ------------------------------------- AdminPage_매출관리 -------------------------------------
	
	// Admin_매출현황페이지
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
			query += "	AND C.CATEGORY_PK = " + productDTO.getCategoryPK();
		}
		if(productDTO.getSubCategoryPK() > 0) {
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
		if(productDTO.getEndDate() != "" && productDTO.getStartDate() != null) {
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
	
	public List<ProductDTO> selectAll(ProductDTO productDTO) {
		Object[] args1 = { productDTO.getMemberID() };
		Object[] args2 = { productDTO.getMemberID(), productDTO.getProductName() };
		
		try {
			// User_상품목록페이지(판매량순)
			if (productDTO.getSearchCondition().equals("sales")) {
				return jdbcTemplate.query(selectAllProductListQuery("sales"), args1, new ProductListUserRowMapper());
			}
			// User_상품목록페이지(신상순)
			else if (productDTO.getSearchCondition().equals("regdate")) {
				return jdbcTemplate.query(selectAllProductListQuery("regdate"), args1, new ProductListUserRowMapper());
			}
			// User_상품목록페이지(찜순)
			else if (productDTO.getSearchCondition().equals("wish")) {
				return jdbcTemplate.query(selectAllProductListQuery("wish"), args1, new ProductListUserRowMapper());
			}
			// User_상품목록페이지(낮은가격순)
			else if (productDTO.getSearchCondition().equals("rowPrice")) {
				return jdbcTemplate.query(selectAllProductListQuery("rowPrice"), args1, new ProductListUserRowMapper());
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
		try {
			int result = jdbcTemplate.update(UPDATE, productDTO.getProductName(), productDTO.getProductPrice(),
					productDTO.getProductDetailImg(), productDTO.getProductImg(), productDTO.getProductStatus(),
					productDTO.getProductQuantity(), productDTO.getProductPK());
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