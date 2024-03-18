package com.webWeavers.weaveGlow.biz.likeReview;

import java.util.List;

public interface LikeReviewService {
	List<LikeReviewDTO> selectAll(LikeReviewDTO addressDTO);
	LikeReviewDTO selectOne(LikeReviewDTO addressDTO);
	
	boolean insert(LikeReviewDTO addressDTO);
	boolean update(LikeReviewDTO addressDTO);
	boolean delete(LikeReviewDTO addressDTO);
}
