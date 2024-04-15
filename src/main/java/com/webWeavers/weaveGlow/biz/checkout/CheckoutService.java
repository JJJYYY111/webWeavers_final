package com.webWeavers.weaveGlow.biz.checkout;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webWeavers.weaveGlow.biz.buyproduct.BuyProductDAO;
import com.webWeavers.weaveGlow.biz.buyproduct.BuyProductDTO;
import com.webWeavers.weaveGlow.biz.cart.CartDTO;
import com.webWeavers.weaveGlow.biz.cart.CartService;
import com.webWeavers.weaveGlow.biz.product.ProductDTO;
import com.webWeavers.weaveGlow.biz.product.ProductService;
import com.webWeavers.weaveGlow.biz.serial.SerialDAO;
import com.webWeavers.weaveGlow.biz.serial.SerialDTO;

@Service("checkoutService")
public class CheckoutService {
	@Autowired
	BuyProductDAO buyProductDAO;
	@Autowired
	SerialDAO serialDAO;
	@Autowired
	CartService cartService;
	@Autowired
	ProductService productService;

	// 결제후 DB의 데이터를 수정하는 메서드
	@Transactional(rollbackFor = Exception.class)
	public String checkoutUpdate(List<CartDTO> datas, SerialDTO serialDTO, String mid) throws Exception {
		
		try {
			serialDTO.setMemberID(mid);
			serialDAO.insert(serialDTO);
			System.out.println("serialDAO수행");

			BuyProductDTO buyProductDTO = new BuyProductDTO();
			for (CartDTO data : datas) {
				buyProductDTO.setProductPK(data.getProductPK());
				buyProductDTO.setBuyProductCnt(data.getCartCnt());
				buyProductDAO.insert(buyProductDTO);
				System.out.println("buyProductDAO수행");
				throw new RuntimeException();
			}
			
			// 메모리 누수 발견해서 코드 레벨 이동
			ProductDTO productDTO = new ProductDTO();
			productDTO.setSearchCondition("updateBySelling");
			//	  의존성 발견해서 DTO 선언문 이동할 수도있음!															
			//    Service 인터페이스가 없어서 발생한 이슈 == 메서드 시그니쳐를 강제하지않아서 발생한 이슈
			for (CartDTO data : datas) {
//				ProductDTO productDTO = new ProductDTO();
//				productDTO.setSearchCondition("updateBySelling");
				productDTO.setProductPK(data.getProductPK());
				productDTO.setProductQuantity(data.getCartCnt());
				productService.update(productDTO);
			}

			CartDTO cartDTO = new CartDTO();
			cartDTO.setSearchCondition("deleteOne");
			for (CartDTO data : datas) {
				cartDTO.setCartPK(data.getCartPK());
				cartService.delete(cartDTO);
			}

		} catch (Exception e) {
			System.out.println("checkoutService에러캐치");
			throw e;
		}
		return "1";
	}
}
