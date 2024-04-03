package com.webWeavers.weaveGlow.controller.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webWeavers.weaveGlow.biz.cart.CartDTO;
import com.webWeavers.weaveGlow.biz.cart.CartService;

import jakarta.servlet.http.HttpSession;
	

@Controller
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@RequestMapping("/async/cartInsert")
	public @ResponseBody String cartInsert(CartDTO cartDTO, HttpSession session) {
		System.out.println("async/cartInsert진입");
		if((String)session.getAttribute("sessionMid")==null) {
			return "false";
		}
		cartDTO.setMemberID((String)session.getAttribute("sessionMid"));
		cartDTO.setSearchCondition("cartCheck");
		CartDTO data = cartService.selectOne(cartDTO);
		
		if(data == null) { // 만약 검색한 상품이 장바구니 테이블에 존재하지 않는다면
			return String.valueOf(cartService.insert(cartDTO));
		}
		cartDTO.setSearchCondition("cntAdd"); // cDTO에 검색조건을 저장
		return String.valueOf(cartService.update(cartDTO));
	}
	
	@RequestMapping("/async/cartUpdate")// ("/async/idcheck" 혹은 "/async/pwcheck")
	public @ResponseBody String cartUpdate(CartDTO cartDTO, HttpSession session) {
		cartDTO.setMemberID((String)session.getAttribute("sessionMid"));
		cartDTO.setSearchCondition("cntUpdate");
        return String.valueOf(cartService.update(cartDTO));
	}
	
	@RequestMapping("/cart")
	public String cart(CartDTO cartDTO, HttpSession session, Model model) {
		
		// 장바구니에 담긴 물건 출력
		cartDTO.setMemberID((String)session.getAttribute("sessionMid"));;
		List<CartDTO> cdatas = cartService.selectAll(cartDTO);
		if(cdatas.isEmpty()) { // 장바구니에 물품이 없을경우 메세지를 보냄
			model.addAttribute("msg", "장바구니가 비었습니다.");
			return "user/cart";
		}
		else { // 장바구니에 물건이 존재할경우 해당 목록을 보냄
			model.addAttribute("cdatas", cdatas);
			return "user/cart";
		}
	}
	
	// 이거 안쓸듯....
	@RequestMapping("/cartDelete") // ("/async/idcheck" 혹은 "/async/pwcheck")
	public String cartDelete(CartDTO cartDTO) {
		// 장바구니에서 상품번호, 사용자 ID를 가져와 CartDTO에 설정
		cartDTO.setSearchCondition("deleteOne");
		if(!cartService.delete(cartDTO)) {
			return "redirect:/error";
		}
		return "redirect:/cart";
	}
	
	@RequestMapping("/cartDeleteSelected") // ("/async/idcheck" 혹은 "/async/pwcheck")
	public String cartDeleteSelected(CartDTO cartDTO, 
										@RequestParam("selectedProducts") List<Integer> selectedProducts) {
		if(selectedProducts.size()<=0) {
			return "user/cart";
		}
		cartDTO.setSearchCondition("deleteOne");
		for(int data : selectedProducts) {
			cartDTO.setCartPK(data);
			if(!cartService.delete(cartDTO)) {
				return "redirect:/error";
			}
		}
		return "redirect:/cart";
	}
	
}
