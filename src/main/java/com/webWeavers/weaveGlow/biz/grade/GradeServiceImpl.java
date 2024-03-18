package com.webWeavers.weaveGlow.biz.grade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("gradeService")
public class GradeServiceImpl implements GradeService {

	@Autowired
	private GradeDAO gradeDAO;

//	@Override
//	public List<GradeDTO> selectAll(GradeDTO gradeDTO) {
//		return gradeDAO.selectAll(gradeDTO);
//	}
//
//	@Override
//	public GradeDTO selectOne(GradeDTO gradeDTO) {
//		return gradeDAO.selectOne(gradeDTO);
//	}

//	@Override
//	public boolean insert(GradeDTO gradeDTO) {
//		return gradeDAO.insert(gradeDTO);
//	}

//	@Override
//	public boolean update(GradeDTO gradeDTO) {
//		return gradeDAO.update(gradeDTO);
//	}

//	@Override
//	public boolean delete(GradeDTO gradeDTO) {
//		return gradeDAO.delete(gradeDTO);
//	}

}
