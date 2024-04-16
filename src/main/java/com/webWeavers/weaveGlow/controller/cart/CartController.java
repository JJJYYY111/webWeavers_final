package com.webWeavers.weaveGlow.controller.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webWeavers.weaveGlow.biz.cart.CartDTO;
import com.webWeavers.weaveGlow.biz.cart.CartService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

	@Autowired
	CartService cartService;

	// 장바구니에 상품을 추가하기위한 메서드
	@PostMapping("/async/cartInsert")
	public @ResponseBody String cartInsert(CartDTO cartDTO, HttpSession session) {
		// 세션에 ID값이 존재하지 않을경우 false값 리턴
		if ((String) session.getAttribute("sessionMid") == null) {
			return "false";
		}
		// 파라미터값을 바인딩한 커맨드객체에 ID값과 검색조건을 추가하고 장바구니테이블에 상품이 존재하는지 조회
		cartDTO.setMemberID((String) session.getAttribute("sessionMid"));
		cartDTO.setSearchCondition("cartCheck");
		CartDTO data = cartService.selectOne(cartDTO);

		if (data == null) { // 만약 검색한 상품이 장바구니 테이블에 존재하지 않는다면 장바구니에 상품추가
			return String.valueOf(cartService.insert(cartDTO));
		}
		cartDTO.setSearchCondition("cntAdd");
		return String.valueOf(cartService.update(cartDTO)); // 장바구니에 상품이 존재하면 수량만 수정
	}

	// 장바구니에서 상품의 수량을 조절하기위한 메서드
	@PostMapping("/async/cartUpdate")
	public @ResponseBody String cartUpdate(CartDTO cartDTO, HttpSession session) {
		// 파라미터값을 바인딩한 커맨드객체에 ID값과 검색조건을 추가한뒤 장바구니테이블의 데이터를 수정하고 성공시 'true' 실패시 'false'를 리턴
		cartDTO.setMemberID((String) session.getAttribute("sessionMid"));
		cartDTO.setSearchCondition("cntUpdate");
		return String.valueOf(cartService.update(cartDTO));
	}

	// 장바구니로 이동하는 메서드
	@GetMapping("/cart")
	public String cart(CartDTO cartDTO, HttpSession session, Model model) {
		
		// ID값을 통해 해당 유저의 장바구니목록을 조회한뒤 Model에 저장, 만약 데이터가 없을경우 출력문구 저장하고 페이지이동
		cartDTO.setMemberID((String) session.getAttribute("sessionMid"));
		List<CartDTO> cdatas = cartService.selectAll(cartDTO);
		if (cdatas.isEmpty()) { // 장바구니에 물품이 없을경우 메세지를 보냄
			model.addAttribute("msg", "장바구니가 비었습니다.");
			return "user/cart";
		}
		model.addAttribute("cdatas", cdatas);
		return "user/cart";
	}

	// 장바구니에서 특정(1개)상품을 삭제하기위한 메서드
	@GetMapping("/cartDelete") 
	public String cartDelete(CartDTO cartDTO) {
		// 파라미터값을 바인딩한 커맨드객체를 통해 장바구니의 상품을 삭제하고 페이지이동
		cartDTO.setSearchCondition("deleteOne");
		if (!cartService.delete(cartDTO)) {
			return "redirect:/error";
		}
		return "redirect:/cart";
	}
	
	// 장바구니에서 선택한 상품들을 삭제하기위한 메서드
	@PostMapping("/cartDeleteSelected") 
	public String cartDeleteSelected(CartDTO cartDTO,
			@RequestParam("selectedProducts") List<Integer> selectedProducts) {
		// 선택한 상품이 존재하지 않을 경우 아무 처리도 하지 않고 페이지 이동
		if (selectedProducts.size() <= 0) {
			return "user/cart";
		}
		// 반복문을 통해 선택한 상품들을 장바구니에서 삭제하고 페이지이동
		cartDTO.setSearchCondition("deleteOne");
		for (int data : selectedProducts) {
			cartDTO.setCartPK(data);
			if (!cartService.delete(cartDTO)) {
				return "redirect:/error";
			}
		}
		return "redirect:/cart";
	}
}
