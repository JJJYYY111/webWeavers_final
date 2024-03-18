package com.webWeavers.weaveGlow.biz.reviewlike;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("reviewLikeService")
public class ReviewLikeServiceImpl implements ReviewLikeService {

	@Autowired
	private ReviewLikeDAO reviewLikeDAO;

//	@Override
//	public List<ReviewLikeDTO> selectAll(ReviewLikeDTO reviewLikeDTO) {
//		return reviewLikeDAO.selectAll(reviewLikeDTO);
//	}

//	@Override
//	public ReviewLikeDTO selectOne(ReviewLikeDTO reviewLikeDTO) {
//		return reviewLikeDAO.selectOne(reviewLikeDTO);
//	}

	@Override
	public boolean insert(ReviewLikeDTO reviewLikeDTO) {
		return reviewLikeDAO.insert(reviewLikeDTO);
	}

//	@Override
//	public boolean update(ReviewLikeDTO reviewLikeDTO) {
//		return reviewLikeDAO.update(reviewLikeDTO);
//	}
	
	@Override
	public boolean delete(ReviewLikeDTO reviewLikeDTO) {
		return reviewLikeDAO.delete(reviewLikeDTO);
	}

}
