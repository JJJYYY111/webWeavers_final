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
	// 정렬별 상품목록 쿼리문 반환 함수
	private static String selectAllQuery(String sortType, int limitNum) {
		String query = "WITH WS AS (SELECT PRODUCT_PK, COUNT(WISHLIST_PK) CNT FROM WISHLIST GROUP BY PRODUCT_PK LIMIT " + limitNum + "),\r\n"	// 상품별 찜 개수 (WISHLIST 서브쿼리)
				+ "BS AS ( SELECT PRODUCT_PK, SUM(BUYPRODUCT_CNT) SALES FROM BUYPRODUCT GROUP BY PRODUCT_PK LIMIT " + limitNum + ")\r\n"	// 상품별 판매량 (BUYPRODUCT 서브쿼리)
				+ "SELECT P.PRODUCT_PK, P.PRODUCT_NAME, P.PRODUCT_PRICE, P.PRODUCT_IMG, P.PRODUCT_REGDATE, BS.SALES, WS.CNT,\r\n"
				+ "	CASE WHEN W.WISHLIST_PK IS NOT NULL THEN 1 ELSE 0 END AS HasWPK\r\n"
				+ "FROM PRODUCT P\r\n"
				+ "LEFT JOIN WISHLIST W ON P.PRODUCT_PK = W.PRODUCT_PK AND W.MEMBER_ID = ?\r\n"
				+ "LEFT JOIN  BS ON P.PRODUCT_PK = BS.PRODUCT_PK\r\n"
				+ "LEFT JOIN WS ON P.PRODUCT_PK = WS.PRODUCT_PK\r\n"
				+ "ORDER BY\r\n";
		// 판매량순_메인페이지, 상품목록페이지
		if(sortType.equals("sales")) {
			query += "BS.SALES DESC,";
		}
		// 신상순_상품목록페이지
		else if(sortType.equals("regdate")) {
			query += "P.PRODUCT_REGDATE DESC,";
		}
		// 찜순_메인페이지
		else if(sortType.equals("wish")) {
			query += "WS.CNT DESC,";
		}
		// 낮은 가격순_상품목록페이지
		else if(sortType.equals("rowPrice")) {
			query += "P.PRODUCT_PRICE ASC,";
		}
		query += "P.PRODUCT_PK DESC LIMIT " + limitNum;
		
		return query;
	}
	
	// 상품이름검색
	private static final String SELECTALL_SEARCHNAME = "SELECT\r\n"
			+ "	P.PRODUCT_PK, P.PRODUCT_NAME, P.PRODUCT_PRICE, P.PRODUCT_IMG,\r\n"
			+ "	CASE WHEN W.WISHLIST_PK IS NOT NULL THEN 1 ELSE 0 END AS HasWPK\r\n"
			+ "FROM PRODUCT P\r\n"
			+ "LEFT JOIN WISHLIST W ON P.PRODUCT_PK = W.PRODUCT_PK AND W.MEMBER_ID = ?\r\n"
			+ "WHERE P.PRODUCT_NAME LIKE CONCAT('%',?,'%')\r\n"
			+ "ORDER BY P.PRODUCT_PK DESC";
	// 상품상세페이지
	private static final String SELECTONE_USER_PRODUCT = "SELECT\r\n"
			+ "	P.PRODUCT_PK, P.PRODUCT_NAME, P.PRODUCT_PRICE, P.PRODUCT_IMG, P.PRODUCT_DETAILIMG,\r\n"
			+ "	CASE WHEN W.WISHLIST_PK IS NOT NULL THEN 1 ELSE 0 END AS HasWPK\r\n"
			+ "FROM PRODUCT P\r\n"
			+ "LEFT JOIN WISHLIST W ON P.PRODUCT_PK = W.PRODUCT_PK AND W.MEMBER_ID = ?\r\n"
			+ "WHERE P.PRODUCT_PK = ?";
	
	// ------------------------------------- AdminPage -------------------------------------	
	// 상품목록_관리자상품현황페이지
	private static final String SELECTALL_ADMIN_PRODUCT = "SELECT\r\n"
			+ "	PRODUCT_PK, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_QUANTITY, PRODUCT_STATUS\r\n"
			+ "FROM PRODUCT\r\n"
			+ "ORDER BY PRODUCT_PK ASC\r\n";
//			+ "LIMIT ?, 10";	// 페이징처리 (앞단 페이지 번호 필요) (?행부터 10개 데이터 추출 / 0,10,20,30,40,... 이렇게 번호 넘어오면 좋을것같음) -> 앞단에서 처리 예정
	// 상품정보_관리자상품수정페이지
	private static final String SELECTONE_ADMIN_PRODUCT = "SELECT\r\n"
			+ "	PRODUCT_PK, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_REGDATE,\r\n"
			+ "	PRODUCT_DETAILIMG, PRODUCT_IMG, PRODUCT_STATUS, PRODUCT_QUANTITY\r\n"
			+ "FROM PRODUCT\r\n"
			+ "WHERE PRODUCT_PK = ?";
	
	// 상품등록_관리자상품등록페이지
	private static final String INSERT = "INSERT INTO PRODUCT (PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_DETAILIMG, PRODUCT_IMG, PRODUCT_STATUS, PRODUCT_QUANTITY) VALUES (?, ?, ?, ?, ?, ?)";
	// 상품정보수정_관리자상품수정페이지
	private static final String UPDATE = "UPDATE PRODUCT SET PRODUCT_NAME = ?, PRODUCT_PRICE = ?, PRODUCT_DETAILIMG = ?, PRODUCT_IMG = ?, PRODUCT_STATUS = ?, PRODUCT_QUANTITY = ? WHERE PRODUCT_PK = ?";
//	private static final String DELETE = "";

	public List<ProductDTO> selectAll(ProductDTO productDTO) {
		Object[] args1 = { productDTO.getMemberID() };
		Object[] args2 = { productDTO.getMemberID(), productDTO.getProductName() };
		
		try {
			if (productDTO.getSearchCondition().equals("sales")) {
				return jdbcTemplate.query(selectAllQuery("sales", 100), args1, new ProductListUserRowMapper());
			} else if (productDTO.getSearchCondition().equals("regdate")) {
				return jdbcTemplate.query(selectAllQuery("regdate", 100), args1, new ProductListUserRowMapper());
			} else if (productDTO.getSearchCondition().equals("wish")) {
				return jdbcTemplate.query(selectAllQuery("wish", 8), args1, new ProductListUserRowMapper());
			} else if (productDTO.getSearchCondition().equals("rowPrice")) {
				return jdbcTemplate.query(selectAllQuery("rowPrice", 100), args1, new ProductListUserRowMapper());
			} else if (productDTO.getSearchCondition().equals("searchName")) {
				return jdbcTemplate.query(SELECTALL_SEARCHNAME, args2, new ProductListUserRowMapper());
			} else if (productDTO.getSearchCondition().equals("adminProductList")) {
//				int pageNum = productDTO.getPageNum();
//				if(productDTO.getPageNum() != 0) {
//					pageNum = productDTO.getPageNum() - 1;	// 앞단에서 페이지번호 어떻게 넘겨주는지보고 계산 예정
//				}
//				Object[] args3 = { pageNum };
//				return jdbcTemplate.query(SELECTALL_ADMIN_PRODUCT, args3, new ProductListAdminRowMapper());
				return jdbcTemplate.query(SELECTALL_ADMIN_PRODUCT, new ProductListAdminRowMapper());
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
			if (productDTO.getSearchCondition().equals("userProduct")) {
				return jdbcTemplate.queryForObject(SELECTONE_USER_PRODUCT, args1, new ProductUserRowMapper());
			} else if (productDTO.getSearchCondition().equals("adminProduct")) {
				return jdbcTemplate.queryForObject(SELECTONE_ADMIN_PRODUCT, args2, new ProductAdminRowMapper());
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return null;
	}


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