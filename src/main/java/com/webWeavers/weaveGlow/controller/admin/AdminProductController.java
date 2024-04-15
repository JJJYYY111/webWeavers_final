package com.webWeavers.weaveGlow.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.webWeavers.weaveGlow.biz.categorization.CategorizationDTO;
import com.webWeavers.weaveGlow.biz.categorization.CategorizationService;
import com.webWeavers.weaveGlow.biz.category.CategoryDTO;
import com.webWeavers.weaveGlow.biz.imageupload.ImageService;
import com.webWeavers.weaveGlow.biz.product.ProductDTO;
import com.webWeavers.weaveGlow.biz.product.ProductService;
import com.webWeavers.weaveGlow.biz.subcategory.SubCategoryDTO;

@Controller
public class AdminProductController {
	
	@Autowired
	ProductService productService;
	@Autowired
	ImageService imageService;
	@Autowired
	CategorizationService categorizationService;

	// 관리자페이지 - 상품목록으로 이동하는 메서드
	@GetMapping("/adminProductStatus")
	public String adminProductStatus(ProductDTO productDTO, Model model) {
		productDTO.setSearchCondition("adminProductList");
		model.addAttribute("productDatas", productService.selectAll(productDTO));
		return "admin/adminProductStatus";
	}

	// 관리자페이지 - 상품등록페이지로 이동하는 메서드
	@GetMapping("/adminProductRegistration")
	public String adminProductRegistration() {
		return "admin/adminProductRegistration";
	}

	// 관리자페이지 - 상품등록기능을 수행하는 메서드
	@PostMapping("/adminProductInsert")
	public String adminProductInsert(ProductDTO productDTO, CategorizationDTO categorizationDTO,
			@RequestParam("productImage") MultipartFile productImage,
			@RequestParam("productDetailImage") MultipartFile productDetailImage,
			@RequestParam("subCategoryName") List<Integer> subCategoryNames) {
		// 1번기능 상품추가
		productDTO.setProductImg(imageService.imageInsertFolder(productImage, "product"));
		productDTO.setProductDetailImg(imageService.imageInsertFolder(productDetailImage, "product"));
		System.out.println(productDTO);
		productService.insert(productDTO);

		// 2번기능 방금 등록한 상품 productPK번호 받아와서 카테고리에 추가하도록 사전설정
		productDTO.setSearchCondition("productInsert");
		categorizationDTO.setProductPK(productService.selectOne(productDTO).getProductPK());

		// 3번기능 카테고리 추가
		for (int data : subCategoryNames) {
			categorizationDTO.setSubCategoryPK(data);
			System.out.println(categorizationDTO);
			categorizationService.insert(categorizationDTO);
		}
		return "redirect:/adminProductStatus";
	}

	// 관리자페이지 - 상품정보수정페이지로 이동하는 메서드
	@GetMapping("/adminProductStatusChange")
	public String adminProductStatusChange(ProductDTO productDTO, CategoryDTO categoryDTO,
			CategorizationDTO categorizationDTO, Model model) {
		productDTO.setSearchCondition("adminProduct");
		model.addAttribute("productDTO", productService.selectOne(productDTO));
		model.addAttribute("categorizationDTO", categorizationService.selectOne(categorizationDTO));
		return "admin/adminProductStatusChange";
	}

	// 관리자페이지 - 상품정보수정을 수행하는 메서드
	@PostMapping("/adminProductUpdate")
	public String adminProductUpdate(ProductDTO productDTO, CategorizationDTO categorizationDTO,
			@RequestParam("productImage") MultipartFile productImage,
			@RequestParam("productDetailImage") MultipartFile productDetailImage,
			@RequestParam("subCategoryName") List<Integer> subCategoryNames) {

		productDTO.setSearchCondition("adminProduct");
		ProductDTO data = productService.selectOne(productDTO);
		productDTO.setProductImg(data.getProductImg());
		productDTO.setProductDetailImg(data.getProductDetailImg());
		if (!productImage.isEmpty()) {
			productDTO.setProductImg(imageService.imageUpdateFolder(productImage, data.getProductImg(), "product"));
		}
		if (!productDetailImage.isEmpty()) {
			productDTO.setProductDetailImg(
					imageService.imageUpdateFolder(productDetailImage, data.getProductDetailImg(), "product"));
		}
		productDTO.setSearchCondition("updateAdminProduct");
		productService.update(productDTO);

		categorizationService.delete(categorizationDTO);
		for (int subcategoryName : subCategoryNames) {
			categorizationDTO.setSubCategoryPK(subcategoryName);
			System.out.println(categorizationDTO);
			categorizationService.insert(categorizationDTO);
		}
		return "redirect:/adminProductStatus";
	}

	// 관리자페이지 - 상품수정페이지에 해당 상품의 카테고리분류를 나타내기위한 메서드
	@PostMapping("/async/adminCategoryCheckbox")
	public @ResponseBody String adminCategoryCheckbox(CategorizationDTO categorizationDTO, Gson gson,
			SubCategoryDTO subCategoryDTO) {
		return gson.toJson(categorizationService.selectAll(categorizationDTO));
	}
}
