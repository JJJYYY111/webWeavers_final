package com.webWeavers.weaveGlow.biz.checkout;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webWeavers.weaveGlow.biz.buyproduct.BuyProductDTO;
import com.webWeavers.weaveGlow.biz.buyproduct.BuyProductService;
import com.webWeavers.weaveGlow.biz.cart.CartDTO;
import com.webWeavers.weaveGlow.biz.cart.CartService;
import com.webWeavers.weaveGlow.biz.product.ProductDTO;
import com.webWeavers.weaveGlow.biz.product.ProductService;
import com.webWeavers.weaveGlow.biz.serial.SerialDTO;
import com.webWeavers.weaveGlow.biz.serial.SerialService;

@Service
public class CheckoutService {
	@Autowired
	BuyProductService buyProductService;
	@Autowired
	CartService cartService;
	@Autowired
	SerialService serialService;
	@Autowired
	ProductService productService;

	@Transactional(rollbackFor = Exception.class)
	public String checkoutPayment(List<CartDTO> datas, String mid, SerialDTO serialDTO) throws Exception {
		BuyProductDTO buyProductDTO = new BuyProductDTO();
		ProductDTO productDTO = new ProductDTO();
		CartDTO cartDTO = new CartDTO();

		try {
			serialDTO.setMemberID(mid);
			serialService.insert(serialDTO);

			for (int i = 0; i < datas.size(); i++) {
				buyProductDTO.setProductPK(datas.get(i).getProductPK());
				buyProductDTO.setBuyProductCnt(datas.get(i).getCartCnt());
				buyProductService.insert(buyProductDTO);
			}

			productDTO.setSearchCondition("updateBySelling");
			for (int i = 0; i < datas.size(); i++) {
				productDTO.setProductPK(datas.get(i).getProductPK());
				productDTO.setProductQuantity(datas.get(i).getCartCnt());
				productService.update(productDTO);
			}

			cartDTO.setSearchCondition("deleteOne");
			for (CartDTO data : datas) {
				cartDTO.setCartPK(data.getCartPK());
				cartService.delete(cartDTO);
			}

		} catch (Exception e) {
			throw e;
		}
		return "1";
	}
}
