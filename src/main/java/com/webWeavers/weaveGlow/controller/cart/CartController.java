package com.webWeavers.weaveGlow.controller.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
		CartDTO data = cartService.selectOne(cartDTO);
		
		if(data == null) { // 만약 검색한 상품이 장바구니 테이블에 존재하지 않는다면
			return String.valueOf(cartService.insert(cartDTO));
		}
		cartDTO.setSearchCondition("cntAdd"); // cDTO에 검색조건을 저장
		return String.valueOf(cartService.update(cartDTO));
	}
	
	// 기능고장 ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
	
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
	
	
	@RequestMapping("/cartDelete") // ("/async/idcheck" 혹은 "/async/pwcheck")
	public String cartDelete(CartDTO cartDTO, HttpSession session, Model model) {
		// 장바구니에서 상품번호, 사용자 ID를 가져와 CartDTO에 설정
		cartDTO.setMemberID((String)session.getAttribute("sessionMid"));
		
		// 선택삭제?
		
		// CartDTO에서 상품삭제 방식을 구분하기위해 작성
		cartDTO.setSearchCondition("요소삭제");
		
		// 받아온 flag의 값으로 구분하여 성공 여부를 판단
		if(cartService.delete(cartDTO)) {
			//성공하였을 경우 장바구니로 이동
			return "user/cart";
		}
		else {
			//실패하였을 경우 에러페이지로 이동
			return "redirect:/error";
		}
	}
	
}
