package com.webWeavers.weaveGlow.biz.likeReview;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("addressService")
public class LikeReviewServiceImpl implements LikeReviewService {

	@Autowired
	private LikeReviewDAO addressDAO;

	@Override
	public List<LikeReviewDTO> selectAll(LikeReviewDTO addressDTO) {
		return addressDAO.selectAll(addressDTO);
	}

	@Override
	public LikeReviewDTO selectOne(LikeReviewDTO addressDTO) {
		return addressDAO.selectOne(addressDTO);
	}

	@Override
	public boolean insert(LikeReviewDTO addressDTO) {
		return addressDAO.insert(addressDTO);
	}

	@Override
	public boolean update(LikeReviewDTO addressDTO) {
		return addressDAO.update(addressDTO);
	}

	@Override
	public boolean delete(LikeReviewDTO addressDTO) {
		return addressDAO.delete(addressDTO);
	}

}
