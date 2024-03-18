package com.webWeavers.weaveGlow.biz.reviewLike;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("reviewLikeDAO")
public class ReviewLikeDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String SELECTALL = "";
	private static final String SELECTONE = "";
	private static final String INSERT = "";
	private static final String UPDATE = "";
	private static final String DELETE = ""; 
	
	public List<ReviewLikeDTO> selectAll(ReviewLikeDTO reviewLikeDTO) {
		return null;
	}

	public ReviewLikeDTO selectOne(ReviewLikeDTO reviewLikeDTO) {
		return null;
	}

	public boolean insert(ReviewLikeDTO reviewLikeDTO) {
		return true;
	}

	public boolean update(ReviewLikeDTO reviewLikeDTO) {
		return true;
	}

	public boolean delete(ReviewLikeDTO reviewLikeDTOO) {
		return true;
	}	
	
}

class ReviewLikeRowMapper implements RowMapper<ReviewLikeDTO> {
	@Override
	public ReviewLikeDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReviewLikeDTO data = new ReviewLikeDTO();

		return data;
	}
}