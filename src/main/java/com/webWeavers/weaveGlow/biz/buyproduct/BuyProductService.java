package com.webWeavers.weaveGlow.biz.buyproduct;

import java.util.List;

public interface BuyProductService {
	List<BuyProductDTO> selectAll(BuyProductDTO buyProductDTO);
	BuyProductDTO selectOne(BuyProductDTO buyProductDTO);
	
	boolean insert(BuyProductDTO buyProductDTO);
//	boolean update(BuyProductDTO buyProductDTO);
//	boolean delete(BuyProductDTO buyProductDTO);
}
