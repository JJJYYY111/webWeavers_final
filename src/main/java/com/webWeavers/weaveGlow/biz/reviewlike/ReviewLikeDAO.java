package com.webWeavers.weaveGlow.biz.reviewlike;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("reviewLikeDAO")
public class ReviewLikeDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
//	private static final String SELECTALL = "";
	
	// 사용자 리뷰 좋아요 유무
	private static final String SELECTONE = "SELECT REVIEWLIKE_PK FROM REVIEWLIKE WHERE MEMBER_ID = ? AND REVIEW_PK = ?";
	// 리뷰 좋아요 추가
	private static final String INSERT = "REVIEWLIKE (MEMBER_ID, REVIEW_PK) VALUES (?, ?)";
//	private static final String UPDATE = "";
	// 리뷰 좋아요 취소
	private static final String DELETE = "REVIEWLIKE WHERE MEMBER_ID = ? AND REVIEW_PK = ?"; 
	
//	private List<ReviewLikeDTO> selectAll(ReviewLikeDTO reviewLikeDTO) {
//		return null;
//	}

	public ReviewLikeDTO selectOne(ReviewLikeDTO reviewLikeDTO) {
		Object[] args = { reviewLikeDTO.getMemberID(), reviewLikeDTO.getReviewPK() };
		try {
			return jdbcTemplate.queryForObject(SELECTONE, args, new ReviewLikeListRowMapper());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean insert(ReviewLikeDTO reviewLikeDTO) {
		try {
			int result = jdbcTemplate.update(INSERT, reviewLikeDTO.getMemberID(), reviewLikeDTO.getReviewPK());
			if(result <= 0) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

//	private boolean update(ReviewLikeDTO reviewLikeDTO) {
//		return false;
//	}

	public boolean delete(ReviewLikeDTO reviewLikeDTO) {
		try {
			int result = jdbcTemplate.update(DELETE, reviewLikeDTO.getMemberID(), reviewLikeDTO.getReviewPK());
			if(result <= 0 ) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}	
	
}

//selectOne
class ReviewLikeListRowMapper implements RowMapper<ReviewLikeDTO> {
	@Override
	public ReviewLikeDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReviewLikeDTO data = new ReviewLikeDTO();
		data.setReviewLikePK(rs.getInt("REVIEWLIKE_PK"));
		return data;
	}
}
