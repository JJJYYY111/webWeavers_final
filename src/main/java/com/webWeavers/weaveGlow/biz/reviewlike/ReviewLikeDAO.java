package com.webWeavers.weaveGlow.biz.reviewlike;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("reviewLikeDAO")
public class ReviewLikeDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
//	private static final String SELECTALL = "";
//	private static final String SELECTONE = "";
	// 리뷰 좋아요 추가
	private static final String INSERT = "REVIEWLIKE (MEMBER_ID, REVIEW_PK) VALUES (?, ?)";
//	private static final String UPDATE = "";
	// 리뷰 좋아요 취소
	private static final String DELETE = "REVIEWLIKE WHERE MEMBER_ID = ? AND REVIEW_PK = ?"; 
	
//	private List<ReviewLikeDTO> selectAll(ReviewLikeDTO reviewLikeDTO) {
//		return null;
//	}

//	private ReviewLikeDTO selectOne(ReviewLikeDTO reviewLikeDTO) {
//		return null;
//	}

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
