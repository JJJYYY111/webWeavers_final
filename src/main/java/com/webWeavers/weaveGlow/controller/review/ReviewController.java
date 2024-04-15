package com.webWeavers.weaveGlow.controller.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	// 리뷰작성페이지로 이동하는 메서드
	@GetMapping("/reviewWrite")
	public String reviewWrite(BuyProductDTO buyProductDTO, Model model) {
		buyProductDTO = buyProductService.selectOne(buyProductDTO); 
		if(buyProductDTO == null) {
			return "redirect:/error"; 
		}
		model.addAttribute("buyProductDTO", buyProductDTO);
		return "user/reviewWrite";
	}
	
	// 리뷰수정페이지로 이동하는 메서드
	@GetMapping("/reviewEdit")
	public String reviewEdit(ReviewDTO reviewDTO, Model model) {
		reviewDTO = reviewService.selectOne(reviewDTO);
		if(reviewDTO == null) {
			return "redirect:/error";
		}
		model.addAttribute("reviewDTO", reviewDTO);
		
		return "user/reviewEdit";
	}
	
	// 작성한 리뷰목록페이지로 이동하는 메서드
	@GetMapping("/reviewList")
	public String reviewList(ReviewDTO reviewDTO, HttpSession session, Model model) {
		reviewDTO.setMemberID((String)session.getAttribute("sessionMid"));
		reviewDTO.setSearchCondition("myReview");
		model.addAttribute("rdatas", reviewService.selectAll(reviewDTO));
		return "user/reviewList";
	}
	
	// 리뷰작성기능을 수행하는 메서드
	@PostMapping("/reviewInsert")
	public String reviewInsert(ReviewDTO reviewDTO, @RequestParam("reviewImgFile") MultipartFile multipartFile) {
		reviewDTO.setReviewImg(imageService.imageInsertFolder(multipartFile, "review"));
		if(!reviewService.insert(reviewDTO)) {
			return "redirect:/error";
		}
		return "redirect:/reviewList";
	}
	
	// 리뷰수정기능을 수행하는 메서드
	@PostMapping("/reviewUpdate")
	public String reviewUpdate(ReviewDTO reviewDTO, @RequestParam("reviewImgFile") MultipartFile multipartFile) {
		reviewDTO.setReviewImg(imageService.imageInsertFolder(multipartFile, "review"));
		if(!reviewService.update(reviewDTO)) {
			return "redirect:/error";
		}
		return "redirect:/reviewList";
	}
	
	// 특정리뷰에 '좋아요'기능을 수행하는 메서드
	@PostMapping("/async/reviewLike")
	public @ResponseBody String reviewLike(ReviewLikeDTO reviewLikeDTO, HttpSession session) {
		reviewLikeDTO.setMemberID((String)session.getAttribute("sessionMid"));
		ReviewLikeDTO data = reviewLikeService.selectOne(reviewLikeDTO);
		if(data == null) {								
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
	
	// 상품의 리뷰들을 요청(최신순, 좋아요순)에 맞게 받아오는 메서드
	@PostMapping("/async/reviewOrderedList")
	public @ResponseBody String reviewOrderedList(ReviewDTO reviewDTO, HttpSession session, Gson gson) {
		reviewDTO.setMemberID((String)session.getAttribute("sessionMid"));
		return gson.toJson(reviewService.selectAll(reviewDTO));
	}
}
	
