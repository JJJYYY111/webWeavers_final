package com.webWeavers.weaveGlow.biz.buyproduct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("buyProductService")
public class BuyProductServiceImpl implements BuyProductService {

	@Autowired
	private BuyProductDAO buyProductDAO;

	@Override
	public List<BuyProductDTO> selectAll(BuyProductDTO buyProductDTO) {
		return buyProductDAO.selectAll(buyProductDTO);
	}

	@Override
	public BuyProductDTO selectOne(BuyProductDTO buyProductDTO) {
		return buyProductDAO.selectOne(buyProductDTO);
	}

	@Override
	public boolean insert(BuyProductDTO buyProductDTO) {
		return buyProductDAO.insert(buyProductDTO);
	}

//	@Override
//	public boolean update(BuyProductDTO buyProductDTO) {
//		return buyProductDAO.update(buyProductDTO);
//	}
//
//	@Override
//	public boolean delete(BuyProductDTO buyProductDTO) {
//		return buyProductDAO.delete(buyProductDTO);
//	}
}
