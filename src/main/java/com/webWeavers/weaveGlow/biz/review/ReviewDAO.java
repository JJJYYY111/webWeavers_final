package com.webWeavers.weaveGlow.biz.review;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("reviewDAO")
public class ReviewDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// 정렬별 리뷰목록 쿼리문 반환 함수_상품상세페이지
	private static String selectAllQuery(String sortType) {
		String query = "WITH RLS AS (SELECT REVIEW_PK, COUNT(REVIEWLIKE_PK) REVIEWLIKE_CNT FROM REVIEWLIKE GROUP BY REVIEW_PK)\r\n"
				+ "SELECT\r\n"
				+ "	P.PRODUCT_PK, R.REVIEW_PK, R.REVIEW_REGDATE, R.REVIEW_SCOPE, R.REVIEW_CONTENT, R.REVIEW_IMG,\r\n"
				+ "	RLS.REVIEWLIKE_CNT, M.MEMBER_NICKNAME, M.GRADE_PK\r\n"
				+ "FROM PRODUCT P\r\n"
				+ "LEFT JOIN BUYPRODUCT B ON P.PRODUCT_PK = B.PRODUCT_PK\r\n"
				+ "INNER JOIN SERIAL S ON B.SERIAL_PK = S.SERIAL_PK\r\n"
				+ "LEFT JOIN MEMBER M ON S.MEMBER_ID = M.MEMBER_ID\r\n"
				+ "INNER JOIN REVIEW R ON B.BUYPRODUCT_PK = R.BUYPRODUCT_PK\r\n"
				+ "LEFT JOIN RLS ON R.REVIEW_PK = RLS.REVIEW_PK\r\n"
				+ "WHERE P.PRODUCT_PK = ?\r\n"
				+ "ORDER BY ";
		// 최신 리뷰순
		if(sortType.equals("regdate")) {
			query += "R.REVIEW_REGDATE DESC;";
		}
		// 리뷰 따봉순
		else if(sortType.equals("reviewLike")) {
			query += "RLS.REVIEWLIKE_CNT DESC";
		}
		
		return query;
	}
	
//	// 상품상세페이지_리뷰 목록
//	private static final String SELECTALL_PRODUCTREVIEW = "SELECT\r\n"
//			+ "P.PRODUCT_PK, R.REVIEW_PK, R.REVIEW_REGDATE, R.REVIEW_SCOPE, R.REVIEW_CONTENT, R.REVIEW_IMG, M.MEMBER_NICKNAME, M.GRADE_PK\r\n"
//			+ "FROM PRODUCT P\r\n"
//			+ "LEFT JOIN BUYPRODUCT B ON P.PRODUCT_PK = B.PRODUCT_PK\r\n"
//			+ "INNER JOIN SERIAL S ON B.SERIAL_PK = S.SERIAL_PK\r\n"
//			+ "LEFT JOIN MEMBER M ON S.MEMBER_ID = M.MEMBER_ID\r\n"
//			+ "INNER JOIN REVIEW R ON B.BUYPRODUCT_PK = R.BUYPRODUCT_PK\r\n"
//			+ "WHERE P.PRODUCT_PK = ?\r\n"
//			+ "ORDER BY R.REVIEW_REGDATE DESC";
	
	// 마이페이지_리뷰 목록
	private static final String SELECTALL_MYREVIEW = "SELECT\r\n"
			+ "	B.BUYPRODUCT_PK, P.PRODUCT_PK, P.PRODUCT_NAME, P.PRODUCT_IMG, R.REVIEW_REGDATE, R.REVIEW_SCOPE, R.REVIEW_CONTENT, R.REVIEW_IMG\r\n"
			+ "FROM MEMBER M\r\n"
			+ "LEFT JOIN SERIAL S ON M.MEMBER_ID = S.MEMBER_ID\r\n"
			+ "INNER JOIN BUYPRODUCT B ON S.SERIAL_PK = B.SERIAL_PK\r\n"
			+ "INNER JOIN PRODUCT P ON B.PRODUCT_PK = P.PRODUCT_PK \r\n"
			+ "INNER JOIN REVIEW R ON B.BUYPRODUCT_PK = R.BUYPRODUCT_PK\r\n"
			+ "WHERE M.MEMBER_ID = ?\r\n"
			+ "ORDER BY R.REVIEW_REGDATE DESC";
	// ?
	private static final String SELECTONE = "SELECT R.REVIEW_PK, P.PRODUCT_PK, P.PRODUCT_NAME, P.PRODUCT_IMG, R.REVIEW_REGDATE, R.REVIEW_SCOPE, R.REVIEW_CONTENT, R.REVIEW_IMG FROM REVIEW R\r\n"
			+ "JOIN BUYPRODUCT B ON (R.BUYPRODUCT_PK = B.BUYPRODUCT_PK) JOIN PRODUCT P ON (B.PRODUCT_PK = P.PRODUCT_PK) WHERE R.BUYPRODUCT_PK = ?";
	// 리뷰 등록
	private static final String INSERT = "INSERT INTO REVIEW (BUYPRODUCT_PK, REVIEW_SCOPE, REVIEW_CONTENT, REVIEW_IMG) VALUES (?, ?, ?, ?)";
	// 리뷰 수정
	private static final String UPDATE = "UPDATE REVIEW SET REVIEW_SCOPE = ?, REVIEW_CONTENT = ?, REVIEW_IMG = ? WHERE REVIEW_PK = ?";
	
//	private static final String DELETE = ""; 
	
	public List<ReviewDTO> selectAll(ReviewDTO reviewDTO) {
		Object[] args1 = { reviewDTO.getProductPK() };
		Object[] args2 = { reviewDTO.getMemberID() };
		try {
			if (reviewDTO.getSearchCondition().equals("regdate")) {
				return jdbcTemplate.query(selectAllQuery("regdate"), args1, new ReviewListRowMapper());
			} else if(reviewDTO.getSearchCondition().equals("reviewLike")) {
				return jdbcTemplate.query(selectAllQuery("reviewLike"), args1, new ReviewListRowMapper());
			} else if (reviewDTO.getSearchCondition().equals("myReview")) {
				return jdbcTemplate.query(SELECTALL_MYREVIEW, args2, new ReviewMyListRowMapper());
			}
		} catch (Exception e) {
			System.out.println("reviewSelectAll오류진입");
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public ReviewDTO selectOne(ReviewDTO reviewDTO) {
		Object[] args = { reviewDTO.getBuyProductPK() };
		try {
			return jdbcTemplate.queryForObject(SELECTONE, args, new ReviewRowMapper());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean insert(ReviewDTO reviewDTO) {
		try {
			int result = jdbcTemplate.update(INSERT, reviewDTO.getBuyProductPK(), reviewDTO.getReviewScope(), reviewDTO.getReviewContent(), reviewDTO.getReviewImg());
			if (result <= 0) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean update(ReviewDTO reviewDTO) {
		try {
			int result = jdbcTemplate.update(UPDATE, reviewDTO.getReviewScope(), reviewDTO.getReviewContent(), reviewDTO.getReviewImg(), reviewDTO.getReviewPK());
			if (result <= 0) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

//	private boolean delete(ReviewDTO reviewDTO) {
//		return false;
//	}	
	
}

// selectAll_User_ProductListPage
class ReviewListRowMapper implements RowMapper<ReviewDTO> {
	@Override
	public ReviewDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReviewDTO data = new ReviewDTO();
		data.setReviewPK(rs.getInt("REVIEW_PK"));
		data.setReviewRegdate(rs.getDate("REVIEW_REGDATE"));
		data.setReviewScope(rs.getInt("REVIEW_SCOPE"));
		data.setReviewContent(rs.getString("REVIEW_CONTENT"));
		data.setReviewImg(rs.getString("REVIEW_IMG"));
		data.setReviewLikeCnt(rs.getInt("REVIEWLIKE_CNT"));
		data.setMemberNickname(rs.getString("MEMBER_NICKNAME"));
		data.setGradePK(rs.getInt("GRADE_PK"));
		return data;
	}
}

//selectAll_User_MyReviewListPage
class ReviewMyListRowMapper implements RowMapper<ReviewDTO> {
	@Override
	public ReviewDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReviewDTO data = new ReviewDTO();
		data.setBuyProductPK(rs.getInt("BUYPRODUCT_PK"));	
		data.setProductPK(rs.getInt("PRODUCT_PK"));
		data.setProductName(rs.getString("PRODUCT_NAME"));
		data.setProductImg(rs.getString("PRODUCT_IMG"));
		data.setReviewRegdate(rs.getDate("REVIEW_REGDATE"));
		data.setReviewScope(rs.getInt("REVIEW_SCOPE"));
		data.setReviewContent(rs.getString("REVIEW_CONTENT"));
		data.setReviewImg(rs.getString("REVIEW_IMG"));
		return data;
	}
}

//selectOne_?
class ReviewRowMapper implements RowMapper<ReviewDTO> {
	@Override
	public ReviewDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReviewDTO data = new ReviewDTO();
		data.setReviewPK(rs.getInt("REVIEW_PK"));
		data.setProductPK(rs.getInt("PRODUCT_PK"));
		data.setProductImg(rs.getString("PRODUCT_IMG"));
		data.setProductName(rs.getString("PRODUCT_NAME"));
		data.setReviewRegdate(rs.getDate("REVIEW_REGDATE"));
		data.setReviewScope(rs.getInt("REVIEW_SCOPE"));
		data.setReviewContent(rs.getString("REVIEW_CONTENT"));
		data.setReviewImg(rs.getString("REVIEW_IMG"));
		return data;
	}
}