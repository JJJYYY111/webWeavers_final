package com.webWeavers.weaveGlow.controller.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webWeavers.weaveGlow.biz.categorization.CategorizationDTO;
import com.webWeavers.weaveGlow.biz.categorization.CategorizationService;
import com.webWeavers.weaveGlow.biz.product.ProductDTO;
import com.webWeavers.weaveGlow.biz.product.ProductService;
import com.webWeavers.weaveGlow.biz.subcategory.SubCategoryDTO;
import com.webWeavers.weaveGlow.biz.subcategory.SubCategoryService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CommonController {

	@Autowired
	ProductService productService;
	@Autowired
	SubCategoryService subCategoryService;
	@Autowired
	CategorizationService categorizationService;
	
	// contact페이지로 이동하는 메서드
	@GetMapping("/contact")
	public String contact() {
		System.out.println("contact진입");
		return "user/contact";
	}
	
	// index기능을 수행하는 메서드
	@GetMapping("/")
	public String root(ProductDTO productDTO) {
		System.out.println("로그 : index진입");
		productDTO.setSearchCondition("userMain");
		productDTO.setSortType("sales");
		System.out.println("로그[상품테이블데이터확인]: "+productService.selectAll(productDTO));
		// 상품테이블에 데이터가 존재하지 않을경우 크롤링메서드로 이동
		if (productService.selectAll(productDTO).size() <= 0) {
			return "redirect:/crawling";
		}
		return "redirect:/main";
	}
	
	// 상품테이블에 상품이 존재하지 않을 경우 웹크롤링을 통해 상품테이블에 상품을 넣는 메서드
	@GetMapping("/crawling")
	public String Crawling(SubCategoryDTO subCategoryDTO, CategorizationDTO categorizationDTO) {
		List<String> urlDatas = new ArrayList<String>();
		String tonymolySkinCareProductListURL = "https://tonymoly.com/ctgr/subcategory_product_list.do?i_sCategorycd1=L01&i_sCategorynm1=%EA%B8%B0%EC%B4%88&i_sCategorycd2=L01";
		urlDatas.add(tonymolySkinCareProductListURL+"M01&i_sCategorynm2=%EC%8A%A4%ED%82%A8%EC%BC%80%EC%96%B4&i_sCategorycd3=L01M01S01&i_sCategorynm3=%EC%8A%A4%ED%82%A8");
		urlDatas.add(tonymolySkinCareProductListURL+"M01&i_sCategorynm2=%EC%8A%A4%ED%82%A8%EC%BC%80%EC%96%B4&i_sCategorycd3=L01M01S02&i_sCategorynm3=%EB%A1%9C%EC%85%98/%EC%97%90%EB%A9%80%EC%A0%BC");
		urlDatas.add(tonymolySkinCareProductListURL+"M01&i_sCategorynm2=%EC%8A%A4%ED%82%A8%EC%BC%80%EC%96%B4&i_sCategorycd3=L01M01S03&i_sCategorynm3=%EC%97%90%EC%84%BC%EC%8A%A4/%EC%84%B8%EB%9F%BC/%EC%95%B0%ED%94%8C");
		urlDatas.add(tonymolySkinCareProductListURL+"M01&i_sCategorynm2=%EC%8A%A4%ED%82%A8%EC%BC%80%EC%96%B4&i_sCategorycd3=L01M01S04&i_sCategorynm3=%ED%81%AC%EB%A6%BC");
		urlDatas.add(tonymolySkinCareProductListURL+"M01&i_sCategorynm2=%EC%8A%A4%ED%82%A8%EC%BC%80%EC%96%B4&i_sCategorycd3=L01M01S05&i_sCategorynm3=%EC%95%84%EC%9D%B4%EC%BC%80%EC%96%B4");
		urlDatas.add(tonymolySkinCareProductListURL+"M01&i_sCategorynm2=%EC%8A%A4%ED%82%A8%EC%BC%80%EC%96%B4&i_sCategorycd3=L01M01S07&i_sCategorynm3=%EB%AF%B8%EC%8A%A4%ED%8A%B8/%EB%B6%80%EC%8A%A4%ED%84%B0");
		urlDatas.add(tonymolySkinCareProductListURL+"M01&i_sCategorynm2=%EC%8A%A4%ED%82%A8%EC%BC%80%EC%96%B4&i_sCategorycd3=L01M01S08&i_sCategorynm3=%EC%84%B8%ED%8A%B8");
		urlDatas.add(tonymolySkinCareProductListURL+"M02&i_sCategorynm2=%ED%81%B4%EB%A0%8C%EC%A7%95&i_sCategorycd3=L01M02S1&i_sCategorynm3=%ED%81%B4%EB%A0%8C%EC%A7%95%ED%8F%BC");
		urlDatas.add(tonymolySkinCareProductListURL+"M02&i_sCategorynm2=%ED%81%B4%EB%A0%8C%EC%A7%95&i_sCategorycd3=L01M02S2&i_sCategorynm3=%ED%81%B4%EB%A0%8C%EC%A7%95%ED%81%AC%EB%A6%BC/%EB%A1%9C%EC%85%98/%EC%9B%8C%ED%84%B0");
		urlDatas.add(tonymolySkinCareProductListURL+"M02&i_sCategorynm2=%ED%81%B4%EB%A0%8C%EC%A7%95&i_sCategorycd3=L01M02S3&i_sCategorynm3=%ED%81%B4%EB%A0%8C%EC%A7%95%EC%A0%A4/%EC%98%A4%EC%9D%BC/%ED%8B%B0%EC%8A%88");
		urlDatas.add(tonymolySkinCareProductListURL+"M02&i_sCategorynm2=%ED%81%B4%EB%A0%8C%EC%A7%95&i_sCategorycd3=L01M02S4&i_sCategorynm3=%EB%A6%BD&%EC%95%84%EC%9D%B4%20%EB%A6%AC%EB%AC%B4%EB%B2%84");
		urlDatas.add(tonymolySkinCareProductListURL+"M03&i_sCategorynm2=%EB%A7%88%EC%8A%A4%ED%81%AC,%20%ED%8C%A9&i_sCategorycd3=L01M03S01&i_sCategorynm3=%EB%A7%88%EC%82%AC%EC%A7%80/%EC%9B%8C%EC%8B%9C%EC%98%A4%ED%94%84%20%ED%8C%A9");
		urlDatas.add(tonymolySkinCareProductListURL+"M03&i_sCategorynm2=%EB%A7%88%EC%8A%A4%ED%81%AC,%20%ED%8C%A9&i_sCategorycd3=L01M03S02&i_sCategorynm3=%ED%95%84%EB%A7%81/%EC%8A%A4%ED%81%AC%EB%9F%BD");
		urlDatas.add(tonymolySkinCareProductListURL+"M03&i_sCategorynm2=%EB%A7%88%EC%8A%A4%ED%81%AC,%20%ED%8C%A9&i_sCategorycd3=L01M03S03&i_sCategorynm3=%EC%8B%9C%ED%8A%B8%EB%A7%88%EC%8A%A4%ED%81%AC");
		urlDatas.add(tonymolySkinCareProductListURL+"M03&i_sCategorynm2=%EB%A7%88%EC%8A%A4%ED%81%AC,%20%ED%8C%A9&i_sCategorycd3=L01M03S04&i_sCategorynm3=%EC%BD%94%ED%8C%A9");
		
		List<SubCategoryDTO> subCategoryDatas = subCategoryService.selectAll(subCategoryDTO);
		List<String> productNameCheck = new ArrayList<String>();
		List<ProductDTO> datas = new ArrayList<ProductDTO>();
		String weburl;
		Document doc = null;
		Elements elems = null;
		for (int i=0; i < subCategoryDatas.size(); i ++ ) {
			weburl = urlDatas.get(i);
			try {
				doc = Jsoup.connect(weburl).get();
			} catch (IOException e) {
				e.printStackTrace();
			}
			elems = doc.select("li.prd_list");
			Iterator<Element> itr = elems.iterator();
			while (itr.hasNext()) {
				ProductDTO productDTO = new ProductDTO();
				Element el = itr.next();
				String name = el.select("span.prod-name").text();
				String price1 = el.select("div.selling-price-wrap > em.price-after").text().replace(",", "").replace("원", "");
				String img = el.select("img.over").attr("src");
				String detailImg = "https://tonymoly.com" + el.select("a.link.thumb").attr("href");
				int price = Integer.parseInt(price1);
				categorizationDTO.setSubCategoryPK(subCategoryDatas.get(i).getSubCategoryPK());
				if(productNameCheck.contains(name)) {
					categorizationDTO.setProductPK(productNameCheck.indexOf(name)+1);
					categorizationService.insert(categorizationDTO);
					continue;
				}
				else {
					categorizationDTO.setProductPK(datas.size() + 1);
					categorizationService.insert(categorizationDTO);
				}
				productNameCheck.add(name);
				productDTO.setProductName(name);
				productDTO.setProductPrice(price);
				productDTO.setProductImg(img);
				productDTO.setProductDetailImg(detailImg);
				productDTO.setProductQuantity(50);
				productDTO.setProductStatus(1);
				datas.add(productDTO);
			}
		}
		for (int i = 0; i < datas.size(); i++) {
			System.out.println(datas.get(i).getProductDetailImg());
			try {
				doc = Jsoup.connect(datas.get(i).getProductDetailImg()).get();
			} catch (IOException e) {
				e.printStackTrace();
			}
			elems = doc.select("div#section_detail_view img:first-child");
			String detailImg = elems.attr("src");
			datas.get(i).setProductDetailImg(detailImg);
			if (!productService.insert(datas.get(i))) {
				return "redirect:/error";
			}
		}
		return "redirect:/main";
	}
	
	// 메인페이지로 이동하는 메서드
	@GetMapping("/main")
	public String main(ProductDTO productDTO, HttpSession session, Model model) {
		// preparedstatement를 사용하기위해 ID값을 받아오고 해당 쿼리를 사용하기 위한 검색조건 설정
		productDTO.setMemberID((String) session.getAttribute("sessionMid"));
		productDTO.setSearchCondition("userMain");
		
		// 메인페이지에 출력할 상품목록을 조회하여 model에 저장
		productDTO.setSortType("wish");
		model.addAttribute("wdatas", productService.selectAll(productDTO));

		productDTO.setSortType("sales");
		model.addAttribute("sdatas", productService.selectAll(productDTO));
		
		return "user/main";
	}
}
