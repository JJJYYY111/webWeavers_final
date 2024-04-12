package com.webWeavers.weaveGlow.controller.review;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.webWeavers.weaveGlow.biz.buyproduct.BuyProductDTO;
import com.webWeavers.weaveGlow.biz.buyproduct.BuyProductService;
import com.webWeavers.weaveGlow.biz.imageupload.ImageService;
import com.webWeavers.weaveGlow.biz.review.ReviewDTO;
import com.webWeavers.weaveGlow.biz.review.ReviewService;
import com.webWeavers.weaveGlow.biz.reviewlike.ReviewLikeDTO;
import com.webWeavers.weaveGlow.biz.reviewlike.ReviewLikeService;
import com.webWeavers.weaveGlow.biz.wishlist.WishListDTO;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	@Autowired
	BuyProductService buyProductService;
	@Autowired
	ImageService imageService;
	@Autowired
	ReviewLikeService reviewLikeService;

	@RequestMapping("/reviewWrite")
	public String reviewWrite(BuyProductDTO buyProductDTO, Model model) {
		buyProductDTO = buyProductService.selectOne(buyProductDTO); // bDTO를 사용하여 bDAO의 검색기능인 R(selectOne)기능을 사용하여 해당 구매한 상품이 존재하는지 확인
		if(buyProductDTO == null) { // 만약 해당 구매한 상품이 존재하지 않는다면
			return "redirect:/error"; // 에러페이지로 경로를 설정하며 return으로 아래의 코드를 즉시 종료
		}
		model.addAttribute("buyProductDTO", buyProductDTO);	//해당 data객체를 request에 저장
		return "user/reviewWrite";
	}
	
	@RequestMapping("/reviewEdit")
	public String reviewEdit(ReviewDTO reviewDTO, Model model) {
		System.out.println(reviewDTO);
		reviewDTO = reviewService.selectOne(reviewDTO);
		if(reviewDTO == null) {
			return "redirect:/error";
		}
		model.addAttribute("reviewDTO", reviewDTO);
		
		return "user/reviewEdit";
	}
	
	@RequestMapping("/reviewList")
	public String reviewList(ReviewDTO reviewDTO, HttpSession session, Model model) {
		reviewDTO.setMemberID((String)session.getAttribute("sessionMid"));
		reviewDTO.setSearchCondition("myReview");
		
		model.addAttribute("rdatas", reviewService.selectAll(reviewDTO));
		return "user/reviewList";
	}
	
	
	@RequestMapping("/reviewInsert")
	public String reviewInsert(ReviewDTO reviewDTO, @RequestParam("reviewImgFile") MultipartFile multipartFile) {
		reviewDTO.setReviewImg(imageService.imageInsert(multipartFile, "review"));
		if(!reviewService.insert(reviewDTO)) {
			return "redirect:/error";
		}
		return "redirect:/reviewList";
	}
	
	@RequestMapping("/reviewUpdate")
	public String reviewUpdate(ReviewDTO reviewDTO, @RequestParam("reviewImgFile") MultipartFile multipartFile) {
		reviewDTO.setReviewImg(imageService.imageInsert(multipartFile, "review"));
		System.out.println(reviewDTO);
		if(!reviewService.update(reviewDTO)) {
			return "redirect:/error";
		}
		return "redirect:/reviewList";
	}
	
	@RequestMapping("/async/reviewLike")
	public @ResponseBody String reviewLike(ReviewLikeDTO reviewLikeDTO, HttpSession session) {
		reviewLikeDTO.setMemberID((String)session.getAttribute("sessionMid"));	// wDTO에 세션ID 저장 (로그인 상태, 로그아웃 상태 체크)
		ReviewLikeDTO data = reviewLikeService.selectOne(reviewLikeDTO);					// selectOne()을 통해 리턴값(객체) 저장
		if(data == null) {								// 찜이 안된 상태라면
			if(!reviewLikeService.insert(reviewLikeDTO)) {
				return "0";
			}
			return "1";
		}
		else {
			if(!reviewLikeService.delete(reviewLikeDTO)) {
				return "0";
			}
			return "2";
		}
	}
	
	@RequestMapping("/async/reviewOrderedList")
	public @ResponseBody String reviewOrderedList(ReviewDTO reviewDTO, HttpSession session, Gson gson) {
		reviewDTO.setMemberID((String)session.getAttribute("sessionMid"));
		List<ReviewDTO> datas = reviewService.selectAll(reviewDTO);
		System.out.println(datas);
		return gson.toJson(datas);
	}
}
	
