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

	// 상품 판매량순
	private static final String SELECTALL_SALES = "SELECT \r\n"
			+ "	P.PRODUCT_PK,\r\n"
			+ "	P.PRODUCT_NAME,\r\n"
			+ "	P.PRODUCT_PRICE,\r\n"
			+ "	P.PRODUCT_IMG,\r\n"
			+ "	MAX(CASE WHEN W.WISHLIST_PK IS NOT NULL THEN 1 ELSE 0 END) AS HasWPK,\r\n"
			+ "	SUM(B.BUYPRODUCT_CNT) AS SALES\r\n"
			+ "FROM PRODUCT P\r\n"
			+ "LEFT JOIN BUYPRODUCT B ON\r\n"
			+ "	P.PRODUCT_PK = B.PRODUCT_PK\r\n"
			+ "LEFT JOIN WISHLIST W ON\r\n"
			+ "	P.PRODUCT_PK = W.PRODUCT_PK\r\n"
			+ "	AND W.MEMBER_ID = 'oni'\r\n"
			+ "GROUP BY P.PRODUCT_PK, P.PRODUCT_NAME, P.PRODUCT_PRICE, P.PRODUCT_IMG\r\n"
			+ "ORDER BY SALES DESC, P.PRODUCT_PK DESC";
	// 상품 등록일순
	private static final String SELECTALL_REGDATE = "SELECT P.PRODUCT_PK, P.PRODUCT_NAME, P.PRODUCT_PRICE, P.PRODUCT_IMG, CASE WHEN W.WISHLIST_PK IS NOT NULL THEN 1 ELSE 0 END AS HasWPK\r\n"
			+ "FROM PRODUCT P LEFT JOIN WISHLIST W ON P.PRODUCT_PK = W.PRODUCT_PK AND W.MEMBER_ID = ?\r\n"
			+ "ORDER BY P.PRODUCT_REGDATE DESC, P.PRODUCT_PK DESC";
	// 상품 찜순
	private static final String SELECTALL_WISH = "SELECT ROWNUM, A.HasWPK, A.PRODUCT_PK, A.PRODUCT_NAME, A.PRODUCT_PRICE, A.PRODUCT_IMG, A.CNT\r\n"
			+ "FROM (SELECT CASE WHEN WISHLIST_PK IS NOT NULL THEN 1 ELSE 0 END AS HasWPK,\r\n"
			+ "P.PRODUCT_PK, P.PRODUCT_NAME,  P.PRODUCT_PRICE, P.PRODUCT_IMG, W.CNT FROM PRODUCT P\r\n"
			+ "LEFT OUTER JOIN (SELECT PRODUCT_PK, COUNT(PRODUCT_PK) CNT\r\n"
			+ "FROM WISHLIST GROUP BY PRODUCT_PK) W ON P.PRODUCT_PK = W.PRODUCT_PK\r\n"
			+ "LEFT OUTER JOIN WISHLIST ON W.PRODUCT_PK = P.PRODUCT_PK AND W.MEMBER_ID = ?\r\n"
			+ "ORDER BY NVL(W.CNT, 0) DESC, P.PRODUCT_PK DESC) A WHERE ROWNUM <= 8"; // 8개만 찜 목록 보여줌
	// 상품 낮은가격순
	private static final String SELECTALL_LOWPRICE = "SELECT P.PRODUCT_PK, P.PRODUCT_NAME, P.PRODUCT_PRICE, P.PRODUCT_IMG, NVL(W.CNT, 0) AS WCNT,\r\n"
			+ "CASE WHEN MYW.WISHLIST_PK IS NOT NULL THEN 1 ELSE 0 END AS HasWPK FROM PRODUCT P\r\n"
			+ "LEFT JOIN (SELECT PRODUCT_PK, COUNT(WISHLIST_PK) AS CNT FROM WISHLIST W GROUP BY PRODUCT_PK) W\r\n"
			+ "ON P.PRODUCT_PK = W.PRODUCT_PK LEFT JOIN WISHLIST MYW\r\n"
			+ "ON P.PRODUCT_PK = MYW.PRODUCT_PK AND MYW.MEMBER_ID = ? ORDER BY P.PRODUCT_PRICE ASC, P.PRODUCT_PK DESC";
	// 상품 이름 검색
	private static final String SELECTALL_SEARCHNAME = "SELECT P.PRODUCT_PK, P.PRODUCT_NAME, P.PRODUCT_PRICE, P.PRODUCT_IMG, CASE WHEN W.WISHLIST_PK IS NOT NULL THEN 1 ELSE 0 END AS HasWPK\r\n"
			+ "FROM PRODUCT P LEFT JOIN WISHLIST W ON P.PRODUCT_PK = W.PRODUCT_PK AND W.MEMBER_ID = ?\r\n"
			+ "WHERE P.PRODUCT_NAME LIKE '%'||?||'%' ORDER BY P.PRODUCT_PK DESC";
	// 상품 상세페이지
	private static final String SELECTONE_DETAIL = "SELECT P.PRODUCT_PK, P.PRODUCT_NAME, P.PRODUCT_PRICE, P.PRODUCT_IMG, P.PRODUCT_DETAILIMG, CASE WHEN W.WISHLIST_PK IS NOT NULL THEN 1 ELSE 0 END AS HasWPK\r\n"
			+ "FROM PRODUCT P LEFT JOIN WISHLIST W ON P.PRODUCT_PK = W.PRODUCT_PK AND W.MEMBER_ID = ?\r\n"
			+ "WHERE P.PRODUCT_PK = ?";

//	private static final String INSERT = "";
//	private static final String UPDATE = "";
//	private static final String DELETE = "";

	public List<ProductDTO> selectAll(ProductDTO productDTO) {
		Object[] args1 = { productDTO.getMemberID() };
		Object[] args2 = { productDTO.getMemberID(), productDTO.getProductName() };
		try {
			if (productDTO.getSearchCondition().equals("sales")) {
				return (List<ProductDTO>) jdbcTemplate.query(SELECTALL_SALES, args1, new ProductRowMapper1());
			} else if (productDTO.getSearchCondition().equals("regdate")) {
				return (List<ProductDTO>) jdbcTemplate.query(SELECTALL_REGDATE, args1, new ProductRowMapper1());
			} else if (productDTO.getSearchCondition().equals("wish")) {
				return (List<ProductDTO>) jdbcTemplate.query(SELECTALL_WISH, args1, new ProductRowMapper1());
			} else if (productDTO.getSearchCondition().equals("rowPrice")) {
				return (List<ProductDTO>) jdbcTemplate.query(SELECTALL_LOWPRICE, args1, new ProductRowMapper1());
			} else if (productDTO.getSearchCondition().equals("searchName")) {
				return (List<ProductDTO>) jdbcTemplate.query(SELECTALL_SEARCHNAME, args2, new ProductRowMapper1());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public ProductDTO selectOne(ProductDTO productDTO) {
		Object[] args = { productDTO.getMemberID(), productDTO.getProductPK() };
		try {
			return jdbcTemplate.queryForObject(SELECTONE_DETAIL, args, new ProductRowMapper2());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

//	private boolean insert(ProductDTO productDTO) {
//		return false;
//	}

//	private boolean update(ProductDTO productDTO) {
//		return false;
//	}

//	private boolean delete(ProductDTO productDTO) {
//		return false;
//	}

}

class ProductRowMapper1 implements RowMapper<ProductDTO> {
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

class ProductRowMapper2 implements RowMapper<ProductDTO> {
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