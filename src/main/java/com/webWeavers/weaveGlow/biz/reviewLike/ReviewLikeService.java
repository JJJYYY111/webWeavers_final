package com.webWeavers.weaveGlow.biz.reviewLike;

import java.util.List;

public interface ReviewLikeService {
	
	List<ReviewLikeDTO> selectAll(ReviewLikeDTO reviewLikeDTO);
	ReviewLikeDTO selectOne(ReviewLikeDTO reviewLikeDTO);
	
	boolean insert(ReviewLikeDTO reviewLikeDTO);
	boolean update(ReviewLikeDTO reviewLikeDTO);
	boolean delete(ReviewLikeDTO reviewLikeDTO);
	
}
