package com.webWeavers.weaveGlow.controller.wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webWeavers.weaveGlow.biz.wishlist.WishListDTO;
import com.webWeavers.weaveGlow.biz.wishlist.WishListService;

import jakarta.servlet.http.HttpSession;

@Controller
public class WishlistController {
	@Autowired
	WishListService wishListService;
	
	// 해당 상품에 찜('좋아요')을 수행하는 메서드
	@PostMapping("/async/likeButton")
	public @ResponseBody String likeButton(WishListDTO wishListDTO, HttpSession session) { 
		
		// 파라미터값을 바인딩한 커맨드객체에 사용자의 ID를 추가하여 해당상품에 찜여부를 조회
		wishListDTO.setMemberID((String)session.getAttribute("sessionMid"));	
		WishListDTO data = wishListService.selectOne(wishListDTO);
		if(data == null) { // 찜이 되어있지 않다면 데이터를 추가						
			if(!wishListService.insert(wishListDTO)) {
				return "0"; // 실패하면 '0'을 리턴
			}
			return "1"; // 성공하면 '1'을 리턴
		}
		else {
			if(!wishListService.delete(wishListDTO)) { // 찜이 되어있다면 데이터를 삭제
				return "0"; // 실패하면 '0'을 리턴
			}
			return "2"; // 성공하면 '2'을 리턴
		}
	}
	
	// 찜('좋아요')목록으로 이동하는 메서드
	@GetMapping("/wishList")
	public String wishList(WishListDTO wishListDTO, HttpSession session, Model model) {
		
		// 사용자의 ID값을 통해 해당 사용자의 찜목록을 조회하여 Model에 추가하고 페이지 이동
		wishListDTO.setMemberID((String)session.getAttribute("sessionMid"));
		model.addAttribute("wdatas", wishListService.selectAll(wishListDTO));
		return "user/wishList";
	}
}
