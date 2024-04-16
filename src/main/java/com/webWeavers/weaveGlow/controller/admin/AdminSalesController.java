package com.webWeavers.weaveGlow.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.webWeavers.weaveGlow.biz.product.ProductDTO;
import com.webWeavers.weaveGlow.biz.product.ProductService;

@Controller
public class AdminSalesController {
	
	@Autowired
	ProductService productService;

	// 관리자페이지 - 총매출현황페이지로 이동하는 메서드
	@GetMapping("/adminSalesStatus")
	public String adminSalesStatus(ProductDTO productDTO, Model model) {
		// 검색조건을 통해 총매출현황을 조회하여 Model에 추가하고 페이지이동
		productDTO.setSearchCondition("adminProductSales");
		model.addAttribute("productSaleDatas", productService.selectAll(productDTO));
		return "admin/adminSalesStatus";
	}

	// 관리자페이지 - 특정상품의 판매현황을 검색하기위한 메서드
	@PostMapping("/async/adminSearchSales")
	public @ResponseBody String adminSearchSales(ProductDTO productDTO, Gson gson) {
		// 검색조건을 통해 특정상품의 판매현황을 조회하고 Json객체로 변환한값을 리턴
		productDTO.setSearchCondition("adminProductSales");
		return gson.toJson(productService.selectAll(productDTO));
	}

	// 관리자페이지 - 일일매출현황페이지로 이동하는 메서드
	@GetMapping("/adminDailySalesStatus")
	public String adminDailySalesStatus(ProductDTO productDTO, Model model) {
		// 검색조건을 통해 일일매출현황을 조회하여 Model에 추가하고 페이지이동
		productDTO.setSearchCondition("adminDailySales");
		model.addAttribute("productDatas", productService.selectAll(productDTO));
		return "admin/adminDailySalesStatus";
	}

	// 관리자페이지 - 금일매출현황그래프를 나타내기위한 메서드
	@PostMapping("/async/adminTodaySalesGraph")
	public @ResponseBody String adminTodaySalesGraph(ProductDTO productDTO, Gson gson) {
		// 검색조건을 통해 금일매출현황그래프에 대한 데이터를 조회하고 Json객체로 변환한값을 리턴
		productDTO.setSearchCondition("adminTodaySalesByHours");
		return gson.toJson(productService.selectAll(productDTO));
	}

	// 관리자페이지 - 전일매출현황그래프를 나타내기위한 메서드
	@PostMapping("/async/adminYesterdaySalesGraph")
	public @ResponseBody String adminYesterdaySalesGraph(ProductDTO productDTO, Gson gson) {
		// 검색조건을 통해 전일매출현황그래프에 대한 데이터를 조회하고 Json객체로 변환한값을 리턴
		productDTO.setSearchCondition("adminPvdaySalesByHours");
		return gson.toJson(productService.selectAll(productDTO));
	}

	// 관리자페이지 - 월별매출현황페이지로 이동하는 메서드
	@GetMapping("/adminMonthlySalesStatus")
	public String adminMonthlySalesStatus(ProductDTO productDTO, Model model) {
		// 검색조건을 통해 월별매출현황을 조회하여 Model에 추가하고 페이지이동
		productDTO.setSearchCondition("adminMonthlySales");
		model.addAttribute("productDatas", productService.selectAll(productDTO));
		return "admin/adminMonthlySalesStatus";
	}

	// 관리자페이지 - 월별매출현황그래프를 나타내기위한 메서드
	@PostMapping("/async/adminMonthlySalesGraph")
	public @ResponseBody String adminMonthlySalesGraph(ProductDTO productDTO, Gson gson) {
		// 검색조건을 통해 월별매출현황그래프에 대한 데이터를 조회하고 Json객체로 변환한값을 리턴
		productDTO.setSearchCondition("adminMonthlySalesGraph");
		return gson.toJson(productService.selectAll(productDTO));
	}

	// 관리자페이지 - 카테고리별 매출현황을 도넛그래프로 나타내기위한 메서드
	@PostMapping("/async/adminDonutGraph")
	public @ResponseBody String adminDonutGraph(ProductDTO productDTO, Gson gson) {
		// 검색조건을 통해 카테고리별 매출현황그래프에 대한 데이터를 조회하고 Json객체로 변환한값을 리턴
		productDTO.setSearchCondition("adminCategorySalesDonut");
		return gson.toJson(productService.selectAll(productDTO));
	}
}
