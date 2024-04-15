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
		
		wishListDTO.setMemberID((String)session.getAttribute("sessionMid"));	
		WishListDTO data = wishListService.selectOne(wishListDTO);
		if(data == null) {								
			if(!wishListService.insert(wishListDTO)) {
				return "0";
			}
			return "1";
		}
		else {
			if(!wishListService.delete(wishListDTO)) {
				return "0";
			}
			return "2";
		}
	}
	
	// 찜('좋아요')목록으로 이동하는 메서드
	@GetMapping("/wishList")
	public String wishList(WishListDTO wishListDTO, HttpSession session, Model model) {
		wishListDTO.setMemberID((String)session.getAttribute("sessionMid"));
		model.addAttribute("wdatas", wishListService.selectAll(wishListDTO));
		
		return "user/wishList";
	}
}
